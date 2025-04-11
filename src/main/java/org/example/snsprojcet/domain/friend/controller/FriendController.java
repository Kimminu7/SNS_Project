package org.example.snsprojcet.domain.friend.controller;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.auth.filter.LoginUser;
import org.example.snsprojcet.domain.friend.dto.FriendRequestDto;
import org.example.snsprojcet.domain.friend.dto.FriendResponseDto;
import org.example.snsprojcet.domain.friend.service.FriendService;
import org.example.snsprojcet.domain.user.entity.User;
import org.example.snsprojcet.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;
    private final UserService userService;

    // 친구 요청 보내기
    @PostMapping("/request")
    public ResponseEntity<String> sendFriendRequest(@RequestBody FriendRequestDto dto, @LoginUser User user) {
        User request = userService.findByNickname(dto.getRequestNickname());// 송신자 사용자 받아오기
        User receiver = userService.findByNickname(dto.getReceiverNickname());// 수신자 유저 받아오기

        friendService.sendFriendRequest(request, receiver);
        return ResponseEntity.ok("친구 요청이 성공적으로 전송되었습니다.");
    }

    // 친구 수락 로그인
    @PostMapping("/{friendid}/accept")
    public ResponseEntity<String> accept(@PathVariable Long friendId, @LoginUser User loginUser) {

        friendService.acceptFriendRequest(friendId, loginUser);
        return ResponseEntity.ok("친구 요청을 수락했습니다.");
    }

    // 친구 거절
    @PostMapping("/{friendid}/reject")
    public ResponseEntity<String> reject(@PathVariable Long friendId, @LoginUser User loginUser) {
        friendService.rejectFriendRequest(friendId, loginUser);
        return ResponseEntity.ok("친구 요청을 거절했습니다.");
    }

    // 보낸 요청 목록
    @GetMapping("/requestlist")
    public ResponseEntity<List<FriendResponseDto>> sentRequests(@LoginUser User loginUser) {
        List<FriendResponseDto> list =
                friendService
                        .getSentRequests(loginUser)
                        .stream()
                        .map(FriendResponseDto::new)
                        .collect(Collectors.toList()
                );
        return ResponseEntity.ok(list);
    }

    // 받은 요청 목록
    @GetMapping("/receivedlist")
    public ResponseEntity<List<FriendResponseDto>> receivedRequests(@LoginUser User loginUser) {
        List<FriendResponseDto> list = friendService.getReceivedRequests(loginUser)
                .stream().map(FriendResponseDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    //친구목록
    @GetMapping("/friendlist")
    public ResponseEntity<List<String>> FriendList(@LoginUser User loginUser) {
        // 친구 목록 조회
        List<String> friendList = friendService.findAllFriendList(loginUser.getNickname());
        return new ResponseEntity<>(friendList, HttpStatus.OK);
    }

    // 친구 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFriend(@LoginUser User loginUser, @RequestParam String nickname) {
        User friend = userService.findByNickname(nickname);

        // 친구 관계 삭제
        boolean success = friendService.deleteFriend(loginUser, friend);

        if (success) {
            return ResponseEntity.ok("친구가 삭제되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("친구 삭제에 실패했습니다.");
        }
    }
}
