package org.example.snsprojcet.friend.controller;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.friend.dto.FriendRequestDto;
import org.example.snsprojcet.friend.dto.FriendResponseDto;
import org.example.snsprojcet.friend.entity.Friend;
import org.example.snsprojcet.friend.entity.User;
import org.example.snsprojcet.friend.service.FriendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // 친구 요청 보내기
    @PostMapping("/request")
    public ResponseEntity<FriendResponseDto> sendFriendRequest(@RequestBody FriendRequestDto dto, Principal principal) {
        User user = getLoginUser(principal); // 로그인 사용자 받아오기
        User receiver = getUserById(dto.getReceiverId()); // 수신자 유저 받아오기

        Friend friend = friendService.sendFriendRequest(user, receiver);
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
    public ResponseEntity<String> deleteFriend(@PathVariable Long friendId, Principal principal) {
        User user = getLoginUser(principal);
        friendService.deleteFriend(friendId, user);
        return ResponseEntity.ok("친구가 삭제되었습니다.");
    }

    // 보낸 요청 목록
    @GetMapping("/sent")
    public ResponseEntity<List<FriendResponseDto>> sentRequests(Principal principal) {
        User user = getLoginUser(principal);
        List<FriendResponseDto> list =
                friendService
                        .getSentRequests(user)
                        .stream()
                        .map(FriendResponseDto::new)
                        .collect(Collectors.toList()
                );
        return ResponseEntity.ok(list);
    }

    // 받은 요청 목록
    @GetMapping("/received")
    public ResponseEntity<List<FriendResponseDto>> receivedRequests(Principal principal) {
        User user = getLoginUser(principal);
        List<FriendResponseDto> list = friendService.getReceivedRequests(user)
                .stream().map(FriendResponseDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{friendId}/list")
    public ResponseEntity<List<FriendResponseDto>> getFriendList(Principal principal) {
        User user = getLoginUser(principal);
        List<User> friends = friendService.getFriendList(user);

        List<FriendResponseDto> list = friends.stream()
                .map(FriendResponseDto::fromUser)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    // 실제 구현 시에는 로그인 유저 정보나 UserService에서 받아와야 함 수정 해야하는 사항 이름 가져오기
    private User getLoginUser(Principal principal) {
        //  UserService에서 가져와야 함
        return new User(principal.getName());
        // userservice.findByUserName()그런식으로
    }
    //임시 수신자 정보 가져오기
    private User getUserById(Long id) {
        // 실제로는 UserService 또는 Repository 통해 조회
        // 필요 시 수정 userservice.findById()든
        return new User(id);
    }
}
