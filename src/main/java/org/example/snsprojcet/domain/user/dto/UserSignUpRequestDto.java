package org.example.snsprojcet.domain.user.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserSignUpRequestDto {
    // 이름
    private final String name;

    // 나이
    private final Long age;

    // 닉네임
    private final String nickname;

    // 이메일
    // @Email 은 간단하지만 빈 문자열을 통과 시킨다.(@Pattern을 통한 정규식 검사를 더 많이 사용)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private final String email;

    // 비밀번호
    private final String password;

    // 소개글
    private final String introduction;


    public UserSignUpRequestDto(String name, Long age, String nickname, String email, String password, String introduction) {
        this.name = name;
        this.age = age;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.introduction = introduction;
    }
}
