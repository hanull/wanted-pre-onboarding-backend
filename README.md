## 개발 환경

- java 11
- spring boot 2.7.16
- h2

## 구현 목록

### 1. 채용공고 등록

- Request
  
  ```json
  POST /api/jobPostings
  
  {
      "companyId": 1,
      "position": "백엔드 주니어 개발자22",
      "reward": 1000000,
      "jobDescription": "백엔드 주니어 개발자를 채용합니다2222.",
      "skilll": "java"
  }
  ```

- Response

  ```json
  HTTP/1.1 200 OK
  ```

### 2. 채용공고 수정

- Request

  ```json
  PUT /api/jobPostings
  
  {
      "position": "백엔드 시니어 개발자",
      "reward": 9999999,
      "jobDescription": "시니어 개발자 채용합니다.",
      "skilll": "java"
  }
  ```

- Response

  ```json
  HTTP/1.1 200 OK
  ```

### 3. 채용공고 삭제

- Request

  ```json
  DELETE /api/jobPostings/{id}
  ```

- Response

  ```json
  HTTP/1.1 200 OK
  ```

### 4. 채용공고 조회

- Request

  ```json
  GET /api/jobPostings
  ```

- Response

  ```json
  HTTP/1.1 200 OK
  
  [
      {
          "id": 1,
          "companyName": "원티드",
          "position": "백엔드 주니어 개발자",
          "reward": 1000000,
          "skill": "java"
      },
  		{
          "id": 2,
          "companyName": "우아한형제들",
          "position": "백엔드 시니어 개발자",
          "reward": 9999999,
          "skill": "python"
      }
  ]
  ```

### 5. 채용공고 상세조회

- Request

  ```json
  GET /api/jobPostings/{id}
  ```

- Response

  ```json
  HTTP/1.1 200 OK
  
  {
      "id": 2,
      "companyName": "우아한형제들",
      "position": "백엔드 시니어 개발자",
      "reward": 9999999,
      "skill": "python"
  }
  ```
