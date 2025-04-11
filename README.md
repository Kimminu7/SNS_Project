# sns

## 프로젝트 설명 (일정 API)
이 프로젝트는 소셜 네트워크의 뉴스피드 기능을 중심으로 구현된 웹 서비스입니다. 사용자는 게시물을 작성하고, 친구를 추가하고, 댓글이나 좋아요로 상호작용할 수 있습니다. 로그인 필터 기반의 인증 처리, 친구 기능의 양방향 관계 설정 등 실제 SNS의 핵심 기능을 직접 설계하고 구현하는 것을 목표로 했습니다.

### 주요 구현 기능
- 회원 기능

  - 회원 가입, 로그인, 회원 정보 수정

  - 비밀번호 유효성 검사, 로그인 필터로 인증 처리

- 게시물 기능

  - 게시물 작성, 수정, 조회

- 좋아요 기능 구현

- 댓글 기능

  - 댓글 작성, 수정, 삭제

  - 댓글은 게시물과 유저와의 연관관계를 가짐

  - 댓글 작성자만 수정/삭제 가능

- 친구 기능

  - 친구 요청, 수락, 삭제 기능

  - 친구 목록 조회 (수신/송신)

  - 친구 수락 및 삭제 시 양방향 관계 동기화 처리

  - 친구 요청 여부 확인 및 중복 방지
 
### 기술적 포인트

- 인증: 필터를 이용한 로그인 인증 구현 (WebConfig, LoginFilter)

- 유효성 검사: @Pattern과 Service 계층 조건 검사 방식.

- 연관 관계: User ↔ Friend, User ↔ Post, User ↔ Comment 등 양방향/단방향 매핑 조절

### 기술 스택
- Java 17 
- Spring Boot
- MySQL
- Spring Data JPA
- Postman (API 테스트용)
- 필터 기반 로그인 처리, 세션 기반 인증
- IntelliJ IDEA
- Lombok, Jakarta Validation
- GitHub


## API 명세서

<img width="580" alt="image" src="https://github.com/user-attachments/assets/3023b619-ec2e-44ff-803b-1febf37c2393" />
<img width="552" alt="image" src="https://github.com/user-attachments/assets/069d35d2-d830-48f6-8ac7-7c9ee82417e6" />
<img width="593" alt="image" src="https://github.com/user-attachments/assets/0bec12f7-4e28-427e-9467-01654182db3d" />
<img width="772" alt="image" src="https://github.com/user-attachments/assets/e6344951-dcc3-44a8-b465-e673bca32e5c" />
<img width="795" alt="image" src="https://github.com/user-attachments/assets/880d0a31-32ff-44f8-9d13-971caedc2812" />
<img width="771" alt="image" src="https://github.com/user-attachments/assets/f7415f65-01c3-4866-900d-5bb87cb55452" />

## ERD 작성
![sns - 2025-04-11 02_33_18 - 2025-04-11 02_33_48](https://github.com/user-attachments/assets/ce527ee9-358f-4b34-9da6-983ee4e3e9e6)

## SQL 작성
```
CREATE TABLE `likes` (
	`id`	BIGINT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`board_id`	BIGINT	NOT NULL,
	`user_id`	BIGINT	NOT NULL
);

CREATE TABLE `friend` (
	`id`	BIGINT	NOT NULL	DEFAULT AUTO_INCREMENT	COMMENT '친구ID',
	`userrequest_id`	BIGINT	NOT NULL,
	`userresponse_id`	BIGINT	NOT NULL
);

CREATE TABLE `comment` (
	`id`	BIGINT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`created_at`	LocalDateTime	NULL	DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	LocalDateTime	NULL	DEFAULT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`content`	string	NOT NULL,
	`board_id`	BIGINT	NOT NULL,
	`user_id`	BIGINT	NOT NULL
);

CREATE TABLE `user` (
	`id`	BIGINT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`name`	string	NOT NULL,
	`age`	Long	NOT NULL,
	`nickname`	string	NOT NULL,
	`email`	string	NOT NULL,
	`password`	string	NOT NULL,
	`introduction`	string	NOT NULL,
	`activated`	Boolean	NULL	COMMENT '1-> true, 0-> false'
);

CREATE TABLE `board` (
	`id`	BIGINT	NOT NULL	DEFAULT AUTO_INCREMENT,
	`title`	string	NOT NULL,
	`contents`	string	NOT NULL,
	`created_at`	LocalDateTime	NULL	DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	LocalDateTime	NULL	DEFAULT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`user_id`	BIGINT	NOT NULL
);

ALTER TABLE `likes` ADD CONSTRAINT `PK_LIKES` PRIMARY KEY (`id`);

ALTER TABLE `friend` ADD CONSTRAINT `PK_FRIEND` PRIMARY KEY (`id`);

ALTER TABLE `comment` ADD CONSTRAINT `PK_COMMENT` PRIMARY KEY (`id`);

ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (`id`);

ALTER TABLE `board` ADD CONSTRAINT `PK_BOARD` PRIMARY KEY (`id`);

```

## DDD 구조 사용
```
snsproject
├── common                  # 공통 설정
│   └── config             # 전역 설정 파일들 (예: WebConfig, PasswordEncoder 등)
│
├── domain                 # 도메인 계층 (기능별로 분리)
│
│   ├── auth               # 인증 및 필터, 세션, 인증 관련 기능
│   │   ├── controller
│   │   ├── dto
│   │   ├── filter
│   │   ├── resolver
│   │   └── service
│
│   ├── board              # 게시글 기능
│   │   ├── controller
│   │   ├── dto
│   │   ├── entity
│   │   ├── repository
│   │   └── service
│
│   ├── comment            # 댓글 기능
│   │   ├── controller
│   │   ├── dto
│   │   ├── entity
│   │   ├── repository
│   │   └── service
│
│   ├── friend             # 친구 기능
│   │   ├── controller
│   │   ├── dto
│   │   ├── entity
│   │   ├── enums          # FriendStatus 등 상태 enum
│   │   ├── repository
│   │   └── service
│
│   ├── likes              # 좋아요 기능
│   │   ├── controller
│   │   ├── entity
│   │   ├── repository
│   │   └── service
│
│   └── user               # 사용자 기능
│       ├── controller
│       ├── dto            # 회원가입, 정보 수정, 탈퇴 관련 DTO
│       ├── entity
│       ├── repository
│       └── service
└──────
```
