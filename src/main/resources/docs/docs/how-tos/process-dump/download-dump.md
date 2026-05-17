---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/how-tos/process-dump/download-dump/
tags:
- etg-docs
- how-tos
- process-dump
title: Download dump
---

# Download dump

ETG API V3

How-tos

Process dump

Download dump

# Download dump

#how-tos

⚠️

- Each download URL returned by the API is temporary and valid for **1 hour** from the moment it is issued. Always request a fresh link before downloading the dump.
- As the dump size can exceed 2 GB, it might take a while to download it.

Node.jsPython

```
 1import fs from 'fs';
 2import https from 'https';
 3
 4// url is the URL address taken from the dump receiving calls
 5// fileName is the path to store the dump
 6function downloadDump(url, fileName) {
 7  let localFile = fs.createWriteStream(fileName);
 8  const request = https.get(url, function (response) {
 9    response.on('data', (d) => {
10      localFile.write(d);
11    });
12  });
13};
14
15await downloadDump(url, fileName);
```

```
 1from urllib.request import urlretrieve
 2
 3
 4# url is the URL address taken from the dump receiving calls
 5# file_name is the path to store the dump
 6def download_dump(url, file_name):
 7    path, headers = urlretrieve(url, file_name)
 8    return path
 9
10
11if __name__ == "__main__":
12    path = download_dump(dump_url)
```
