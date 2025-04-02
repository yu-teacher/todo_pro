# Todo 관리 애플리케이션

이 프로젝트는 할일(Todo) 항목을 관리하기 위한 웹 애플리케이션입니다. 사용자는 할일을 추가, 수정, 삭제하고 완료 상태를 토글할 수 있으며, 다양한 필터와 검색 기능을 통해 할일 목록을 관리할 수 있습니다.

## 기능

- 할일 추가/조회/수정/삭제 (CRUD)
- 할일 완료 상태 토글
- 할일 우선순위 설정
- 필터링 (전체/완료/미완료)
- 키워드 검색
- 웹 UI (Thymeleaf) 및 REST API 제공

## 기술 스택

- **Backend**: Spring Boot 3.4.3, Java 21
- **Database**: H2 Database (파일 기반 저장)
- **ORM**: Spring Data JPA
- **Frontend**: Thymeleaf, Bootstrap 5.3.0, jQuery 3.6.4
- **Build Tool**: Gradle

## 프로젝트 구조

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── todo/
│   │               ├── controller/
│   │               │   ├── TodoController.java        # 웹 UI 컨트롤러
│   │               │   └── TodoRestController.java    # REST API 컨트롤러
│   │               ├── domain/
│   │               │   └── Todo.java                  # 도메인 엔티티
│   │               ├── dto/
│   │               │   └── TodoDTO.java               # 데이터 전송 객체
│   │               ├── repository/
│   │               │   └── TodoRepository.java        # JPA 레포지토리
│   │               ├── service/
│   │               │   ├── TodoService.java           # 서비스 인터페이스
│   │               │   └── TodoServiceImpl.java       # 서비스 구현체
│   │               └── TodoApplication.java           # 애플리케이션 진입점
│   └── resources/
│       ├── static/                                    # 정적 자원
│       ├── templates/                                 # Thymeleaf 템플릿
│       └── application.yml                            # 애플리케이션 설정
└── test/                                              # 테스트 코드
```

## 데이터베이스 스키마

**todos 테이블**:
- `id`: Long (PK)
- `title`: String
- `description`: String
- `completed`: boolean
- `priority`: int
- `created_at`: LocalDateTime
- `updated_at`: LocalDateTime

## API 엔드포인트

### REST API

| 메소드   | 경로                     | 설명                      |
|--------|-------------------------|--------------------------|
| GET    | /api/todos              | 모든 할일 목록 조회            |
| GET    | /api/todos?filter=completed | 완료된 할일 목록 조회         |
| GET    | /api/todos?filter=active | 미완료 할일 목록 조회          |
| GET    | /api/todos/{id}         | 특정 할일 조회               |
| POST   | /api/todos              | 새 할일 생성                |
| PUT    | /api/todos/{id}         | 할일 업데이트                |
| DELETE | /api/todos/{id}         | 할일 삭제                  |
| PATCH  | /api/todos/{id}/toggle  | 할일 완료 상태 토글            |
| GET    | /api/todos/search?keyword=검색어 | 키워드로 할일 검색 |

### 웹 UI

| 경로                       | 설명                        |
|--------------------------|----------------------------|
| /todos                    | 할일 목록 페이지              |
| /todos?filter=completed   | 완료된 할일 목록 페이지         |
| /todos?filter=active      | 미완료 할일 목록 페이지          |
| /todos/{id}               | 할일 상세 페이지               |
| /todos/edit/{id}          | 할일 수정 폼                  |
| /todos/delete/{id}        | 할일 삭제 (리다이렉트)          |
| /todos/toggle/{id}        | 할일 상태 토글 (리다이렉트)       |
| /todos/search?keyword=검색어 | 할일 검색 결과 페이지           |

## 설치 및 실행 방법

### 요구사항
- JDK 21 이상
- Gradle

### 실행 방법

1. 프로젝트 클론
   ```bash
   git clone https://github.com/yu-teacher/todo_pro.git
   cd todo_pro
   ```

2. 애플리케이션 빌드
   ```bash
   ./gradlew build
   ```

3. 애플리케이션 실행
   ```bash
   ./gradlew bootRun
   ```

4. 브라우저에서 접속
    - 웹 UI: http://localhost:8080/todos
    - H2 콘솔: http://localhost:8080/h2-console
        - JDBC URL: jdbc:h2:file:./data/tododb
        - 사용자명: sa
        - 비밀번호: (빈 값)

## 데이터 영속성

이 애플리케이션은 H2 데이터베이스의 파일 모드를 사용하여 데이터를 영구적으로 저장합니다. 데이터베이스 파일은 프로젝트 루트의 `data` 디렉토리에 `tododb.mv.db` 파일로 저장됩니다.

## 주요 기능 상세

1. **할일 관리**:
    - 제목과 설명, 우선순위를 가진 할일 생성
    - 할일 목록 조회 및 필터링
    - 할일 상세 정보 조회 및 수정
    - 할일 삭제

2. **상태 관리**:
    - 할일 완료/미완료 상태 토글
    - 완료된 할일과 미완료된 할일 필터링

3. **검색 기능**:
    - 제목 기준 키워드 검색

## 라이선스

MIT License

## 기여

이슈 등록 및 PR 환영합니다.
