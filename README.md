# sns

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
