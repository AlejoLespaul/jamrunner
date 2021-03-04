# JAM RUNNER

## BUILD
```bash
docker build -t ${image} . 
```

## API 
```
Method: post
URL: /api/js
Body: 
{
    "repo": "https://github.com/the-code-challenge/chunkArrayInGroups",
    "name": "af"
}
```
## RESPONSE
```json
{
  "testPassed":4,
  "testFailed":0,
  "performance":"17ms",
  "lines":7
}
```