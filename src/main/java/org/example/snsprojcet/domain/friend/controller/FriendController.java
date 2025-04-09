package org.example.snsprojcet.domain.friend.controller;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.friend.dto.FriendRequestDto;
import org.example.snsprojcet.domain.friend.dto.FriendResponseDto;
import org.example.snsprojcet.domain.friend.entity.Friend;
import org.example.snsprojcet.domain.friend.service.FriendService;
import org.example.snsprojcet.domain.user.entity.User;
import org.example.snsprojcet.domain.user.service.UserService;
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
    public ResponseEntity<FriendResponseDto> sendFriendRequest(@RequestBody FriendRequestDto dto, User user) {
        User loginUser  = userService.getLoginUser(user); // 로그인 사용자 받아오기
        User receiver = userService.getUserById(dto.getReceiverId()); // 수신자 유저 받아오기

        Friend friend = friendService.sendFriendRequest(loginUser, receiver);
        return ResponseEntity.ok(new FriendResponseDto(friend));
    }

    // 친구 수락
    @PostMapping("/{friendId}/accept")
    public ResponseEntity<String> accept(@PathVariable Long friendId) {
        friendService.acceptFriendRequest(friendId);
        return ResponseEntity.ok("친구 요청을 수락했습니다.");
    }

    // 친구 거절
    @PostMapping("/{friendId}/reject")
    public ResponseEntity<String> reject(@PathVariable Long friendId) {
        friendService.rejectFriendRequest(friendId);
        return ResponseEntity.ok("친구 요청을 거절했습니다.");
    }
    // 친구 삭제
    @DeleteMapping("/{friendId}")
    public ResponseEntity<String> deleteFriend(@PathVariable Long friendId, User user) {
        User loginUser  = userService.getLoginUser(user);
        friendService.deleteFriend(friendId, loginUser);
        return ResponseEntity.ok("친구가 삭제되었습니다.");
    }

    // 보낸 요청 목록
    @GetMapping("/sent")
    public ResponseEntity<List<FriendResponseDto>> sentRequests(User user) {
        User loginUser = userService.getLoginUser(user);
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
    @GetMapping("/received")
    public ResponseEntity<List<FriendResponseDto>> receivedRequests(User user) {
        User loginUser = userService.getLoginUser(user);
        List<FriendResponseDto> list = friendService.getReceivedRequests(loginUser)
                .stream().map(FriendResponseDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    //친구목록
    @GetMapping("/list")
    public ResponseEntity<List<FriendResponseDto>> getFriendList(User user) {
        User loginUser = userService.getLoginUser(user);
        List<User> friends = friendService.getFriendList(loginUser);

        List<FriendResponseDto> list = friends.stream()
                .map(FriendResponseDto::fromUser)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }
}
