# Jira Claude Bot — ETG API Support Assistant

Spring Boot сервис, который:
1. Принимает номер Jira тикета
2. Получает текст тикета через Jira API
3. Отправляет вопрос в Claude с ETG API документацией
4. Постит ответ как **internal comment** в Jira

---

## Структура проекта

```
src/main/
├── java/com/etg/jirabot/
│   ├── JiraBotApplication.java
│   ├── config/AppConfig.java          # WebClient beans
│   ├── controller/BotController.java  # REST endpoint
│   └── service/
│       ├── BotService.java            # Пайплайн
│       ├── JiraService.java           # Jira API
│       ├── AnthropicService.java      # Claude API
│       └── DocumentationLoader.java  # Загружает .md файлы
└── resources/
    ├── application.properties
    └── docs/                          # ← Сюда кладёшь .md файлы документации
        ├── overview.md
        ├── authorization.md
        └── ...
```

---

## Настройка

### 1. Добавь документацию

Скопируй все `.md` файлы документации в:
```
src/main/resources/docs/
```

### 2. Переменные окружения

Установи в Railway (или в `.env` для локальной разработки):

| Переменная         | Описание                                                    |
|--------------------|-------------------------------------------------------------|
| `JIRA_BASE_URL`    | `https://yourcompany.atlassian.net`                         |
| `JIRA_EMAIL`       | Email аккаунта Jira                                         |
| `JIRA_API_TOKEN`   | Создать на https://id.atlassian.com/manage-profile/security/api-tokens |
| `ANTHROPIC_API_KEY`| Твой API ключ Anthropic                                     |
| `APP_SECRET_TOKEN` | Произвольный секретный токен для защиты эндпоинта           |

### 3. Деплой на Railway

```bash
# 1. Создай репо и запушь код
git init
git add .
git commit -m "initial"
git remote add origin https://github.com/yourname/jira-claude-bot.git
git push -u origin main

# 2. В Railway: New Project → Deploy from GitHub repo
# 3. Добавь переменные окружения в Railway dashboard
# 4. Railway автоматически задеплоит через Dockerfile
```

После деплоя Railway даст тебе URL вида: `https://jira-claude-bot-production.up.railway.app`

---

## Использование

### Вызов вручную (curl)

```bash
curl -X POST https://your-app.railway.app/api/answer-ticket \
  -H "Content-Type: application/json" \
  -H "X-Secret-Token: your_secret_token" \
  -d '{"issueKey": "PROJ-123"}'
```

### Настройка Jira Automation

1. Зайди в **Jira Settings → Automation → Create rule**
2. **Trigger**: выбери когда срабатывает (например: "Issue created", или "Label added: ai-answer", или "Transition: In Review")
3. **Action**: Add component → **Send web request**
   - URL: `https://your-app.railway.app/api/answer-ticket`
   - Method: `POST`
   - Headers:
     - `Content-Type: application/json`
     - `X-Secret-Token: your_secret_token`
   - Body:
     ```json
     {"issueKey": "{{issue.key}}"}
     ```
4. Сохрани и протестируй

---

## Health check

```bash
curl https://your-app.railway.app/api/health
# {"status":"ok"}
```

---

## Как работает internal comment

Jira internal comment в REST API 3 задаётся через поле `visibility`:
```json
{
  "visibility": {
    "type": "role",
    "value": "Service Desk Team"
  }
}
```
Это значит комментарий виден только агентам поддержки, но не внешним пользователям.

> **Примечание**: для обычного Jira Software (не Service Desk) "internal" комментарии не поддерживаются нативно через visibility. В этом случае можно убрать поле `visibility` и просто постить обычный комментарий, или добавить префикс `[INTERNAL]` к тексту.

---

## Локальная разработка

```bash
# Создай src/main/resources/docs/ и положи туда .md файлы

export JIRA_BASE_URL=https://yourcompany.atlassian.net
export JIRA_EMAIL=you@company.com
export JIRA_API_TOKEN=your_token
export ANTHROPIC_API_KEY=sk-ant-...
export APP_SECRET_TOKEN=mysecret

mvn spring-boot:run
```
