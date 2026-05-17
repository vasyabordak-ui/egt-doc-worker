---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/how-tos/process-dump/read-dump/
tags:
- etg-docs
- how-tos
- process-dump
title: Read dump
---

# Read dump

ETG API V3

How-tos

Process dump

Read dump

# Read dump

#how-tos

⚠️

As the decompressed dump size can exceed 20 GB:

- You have to read the dump line by line.
- It might take a while.

Node.jsPython

```
 1import fs from 'fs';
 2
 3let stream = fs.createReadStream(newFileName, { flags: 'r', encoding: 'utf-8' });
 4let buffer = '';
 5let hotel;
 6
 7// line is the current processed line
 8async function processLine(line) {
 9  if (line[line.length - 1] == '\r') {
10    line = line.substr(0, line.length - 1);
11  }
12  if (line.length > 0) {
13    hotel = JSON.parse(line);
14    // TODO: your logic
15  }
16}
17
18async function readLineByLine() {
19  let position;
20  while ((position = buffer.indexOf('\n')) >= 0) {
21    if (position == 0) {
22      buffer = buffer.slice(1);
23      continue;
24    }
25    await processLine(buffer.slice(0, position));
26    buffer = buffer.slice(position + 1);
27  }
28}
29
30async function readDump() {
31  stream.on('data', function (functionData) {
32    buffer += functionData.toString();
33    readLineByLine();
34  });
35}
36
37await readDump();
```

```
 1# decompressed_file_name is the decompressed dump file name
 2def read_dump(decompressed_file_name):
 3    with open(decompressed_file_name, "r") as f:
 4        s = f.readline()
 5        while s:
 6            hotel = json.loads(s)
 7            # TODO: your logic
 8            s = f.readline()
 9
10
11if __name__ == "__main__":
12    read_dump(decompressed_dump_name)
```
