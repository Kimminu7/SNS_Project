package org.example.snsprojcet.domain.profile.controller;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.profile.dto.UserResponseDto;
import org.example.snsprojcet.domain.profile.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long profile) {
        UserResponseDto userResponseDto = userService.findById(profile);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

}
