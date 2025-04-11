# sns

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
