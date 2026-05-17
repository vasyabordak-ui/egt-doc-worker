---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/how-tos/process-dump/decompress-dump/
tags:
- etg-docs
- how-tos
- process-dump
title: Decompress dump
---

# Decompress dump

ETG API V3

How-tos

Process dump

Decompress dump

# Decompress dump

#how-tos

⚠️

- The decompressed dump size can exceed 20 GB.
- Use the official instruments.

Node.jsSync PythonAsync PythonSync GolangAsync Golang

```
 1import fs from 'fs';
 2import zstd from 'simple-zstd';
 3
 4// fileName is the file name from which to decompress
 5async function decompressFile(fileName) {
 6  const newFileName = String(fileName).split('.')[0] + '.json';
 7  let localFile = fs.createReadStream(fileName)
 8    .pipe(zstd.ZSTDDecompress())
 9    .pipe(fs.createWriteStream(newFileName))
10};
11
12await decompressFile(fileName, newFileName);
```

```
 1import io
 2from pyzstd import decompress_stream
 3
 4
 5# path is the file path from which to decompress
 6def decompress_dump(path):
 7    decompressed_file = ".".join(path.split(".")[:-1])
 8    with io.open(path, "rb") as f:
 9        with io.open(decompressed_file, "wb") as g:
10            decompress_stream(f, g)
11    return decompressed_file
12
13
14if __name__ == "__main__":
15    decompressed_dump = decompress_dump(dump_path)
```

```
 1import asyncio
 2
 3# Install the dependency
 4from zstandard import ZstdDecompressor
 5import json
 6from asyncio import Semaphore
 7
 8
 9class Decoder:
10    def __init__(self, semaphore_value: int) -> None:
11        # Create the semaphore that restricts the number of active coroutines
12        self.sem = Semaphore(semaphore_value)
13        self._raw = []
14
15    # The function processes raw hotel data
16    async def _process_raw_hotels(self) -> None:
17        raw_hotels = self._raw[1:]
18        raw_hotels = [self._raw[0]] + [
19            "".join(t) for t in zip(raw_hotels[::2], raw_hotels[1::2])
20        ]
21        await self._process_hotel(*raw_hotels)
22
23    # The function works with raw hotel byte data
24    async def _process_hotel(self, *raw_hotels: str) -> None:
25        for h in raw_hotels:
26            hotel_data = json.loads(h)
27            # Implememnt your logic with the hotel data
28            # ...
29            print(f"current hotel is {hotel_data['name']}")
30
31    # The function works with with raw chunks
32    async def _process_chunk(self, chunk: bytes) -> None:
33        raw_data = chunk.decode("utf-8")
34        # Read the hotels’ JSONs one by one
35        lines = raw_data.split("\n")
36        for i, line in enumerate(lines[1:-1]):
37            if i == 0:
38                # Put the last line to the raw list
39                self._raw.append(lines[0])
40            await self._process_hotel(line)
41
42        # Put the last line to the raw list
43        self._raw.append(lines[-1])
44        # Increment the semaphore value
45        self.sem.release()
46
47    # The function parses the dump
48    async def parse_dump(self, filename: str) -> None:
49        # Open the dump
50        with open(filename, "rb") as fh:
51            dctx = ZstdDecompressor()
52            with dctx.stream_reader(fh) as reader:
53                while True:
54                    # Read the dump by 16 MB chunks
55                    chunk = reader.read(2 ** 24)
56                    if not chunk:
57                        await self._process_raw_hotels()
58                        break
59                    # Decrement the semaphore value
60                    await self.sem.acquire()
61                    # Run immediately
62                    asyncio.create_task(self._process_chunk(chunk))
63
64
65if __name__ == "__main__":
66    loop = asyncio.get_event_loop()
67    d = Decoder(semaphore_value=10)
68    loop.run_until_complete(d.parse_dump("dump_en.json.zst"))
```

```
 1package main
 2import (
 3  "bytes"
 4  "encoding/json"
 5  "io"
 6  "log"
 7  "math"
 8  "os"
 9  // Install the dependency
10  "github.com/DataDog/zstd"
11)
12
13// Not full struct
14type Hotel struct {
15  Name string `json:"name"`
16}
17
18func parseDump(filename string) {
19  // Open the dump
20  file, err: = os.Open(filename)
21  if err != nil {
22    log.Fatal(err)
23  }
24  defer file.Close()
25  reader: = zstd.NewReader(file)
26  previousLine: = make([] byte, 0)
27  // Read the dump by 16 MB chunks
28  bufferSize: = make([] byte, int(math.Pow(2, 24)))
29  for {
30    n, readErr: = reader.Read(bufferSize)
31    if readErr != nil && readErr != io.EOF {
32      log.Fatal(readErr)
33    }
34    if readErr == io.EOF {
35      break
36    }
37    rawReadData: = bufferSize[: n]\
38    // Read the hotels’ JSONs one by one
39    lines: = bytes.Split(rawReadData, [] byte("\n"))
40    for i, line: = range lines[: len(lines) - 1] {
41      if i == 0 {
42        line = append(previousLine, line...)
43      }
44      // Unmarshal the current hotel JSON
45      var hotel Hotel
46      _ = json.Unmarshal(line, & hotel)
47      // Implememnt your logic with the hotel data
48      // ...
49      log.Printf("current hotel is %s", hotel.Name)
50    }
51    lastLine: = lines[len(lines) - 1]
52    previousLine = make([] byte, len(lastLine))
53    copy(previousLine, lastLine)
54  }
55}
56
57func main() {
58  parseDump("dump_en.json.zst")
59}
```

```
  1package main
  2import (
  3  "bytes"
  4  "context"
  5  "encoding/json"
  6  "io"
  7  "log"
  8  "math"
  9  "os"
 10  "golang.org/x/sync/semaphore"
 11  // Install the dependency
 12  "github.com/DataDog/zstd"
 13)
 14  
 15// The storage with raw data
 16type Raw struct {
 17  firstLine[] byte
 18  lastLine[] byte
 19}
 20
 21// Not full struct
 22type Hotel struct {
 23  Name string `json:"name"`
 24}
 25
 26// The function copies raw data without memory leak
 27func copySlice(slice[] byte)[] byte {
 28  copiedSlice: = make([] byte, len(slice))
 29  for i,
 30  v: = range slice {
 31    copiedSlice[i] = v
 32  }
 33  return copiedSlice
 34}
 35
 36// The function works with raw hotel byte data
 37func processHotel(hotelRaw[] byte) {
 38  // Unmarshal the hotel JSON
 39  var hotel Hotel
 40  err: = json.Unmarshal(hotelRaw, & hotel)
 41  if err != nil {
 42    log.Println(err)
 43  }
 44  // Implememnt your logic with the hotel data
 45  // ...
 46  log.Printf("current hotel is %s", hotel.Name)
 47}
 48
 49// The function works with with raw chunks
 50func processChunk(chunk[] byte, sem * semaphore.Weighted, rawChan chan Raw) {
 51  defer sem.Release(1)
 52  lines: = bytes.Split(chunk, [] byte("\n"))
 53  rawChan < -Raw {
 54    firstLine: copySlice(lines[0]),
 55    lastLine: copySlice(lines[len(lines) - 1]),
 56  }
 57  for _, line: = range lines[1: len(lines) - 1] {
 58    processHotel(line)
 59  }
 60}
 61
 62// The function processes raw hotel data
 63func processRawHotels(raws[] Raw) {
 64  for i, r: = range raws {
 65    if i == 0 {
 66      processHotel(r.firstLine)
 67      continue
 68    }
 69    data: = append(raws[i - 1].lastLine, r.firstLine...)
 70    processHotel(data)
 71  }
 72}
 73
 74// The function parses the dump
 75func parseDump(filename string) {
 76  // Open the dump
 77  file, err: = os.Open(filename)
 78  if err != nil {
 79    log.Fatal(err)
 80  }
 81  defer file.Close()
 82  reader: = zstd.NewReader(file)
 83  // Read the dump by 16 MB chunks
 84  bufferSize: = make([] byte, int(math.Pow(2, 24)))
 85  ctx: = context.Background()
 86  // Set the weighted semaphore to maximum 10 async goroutines
 87  var sem = semaphore.NewWeighted(int64(10))
 88  // Make the storage firstLine and lastLine lines from a chunk
 89  rawData: = make([] Raw, 0)
 90  rawChan: = make(chan Raw)
 91  isFinished: = false
 92  for {
 93    if isFinished {
 94      break
 95    }
 96    n, readErr: = reader.Read(bufferSize)
 97    if readErr != nil && readErr != io.EOF {
 98      log.Fatal(readErr)
 99    }
100    if readErr == io.EOF {
101      isFinished = true
102    }
103    rawReadData: = bufferSize[: n]
104    actualLine: = make([] byte, len(rawReadData))
105    copy(actualLine, rawReadData)
106    // Read the hotels’ JSONs one by one
107    _ = sem.Acquire(ctx, 1)
108    go processChunk(actualLine, sem, rawChan)
109    rawData = append(rawData, < -rawChan)
110  }
111  processRawHotels(rawData)
112}
113
114func main() {
115  parseDump("dump_en.json.zst")
116}
```
