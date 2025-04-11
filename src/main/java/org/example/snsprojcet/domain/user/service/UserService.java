package org.example.snsprojcet.domain.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.common.config.PasswordEncoder;
import org.example.snsprojcet.domain.board.repository.BoardRepository;
import org.example.snsprojcet.domain.comment.repository.CommentRepository;
import org.example.snsprojcet.domain.user.dto.AllUserResponseDto;
import org.example.snsprojcet.domain.user.dto.AnotherUserResponseDto;
import org.example.snsprojcet.domain.user.dto.UserResponseDto;
import org.example.snsprojcet.domain.user.dto.UserSignUpResponseDto;
import org.example.snsprojcet.domain.user.entity.User;
import org.example.snsprojcet.domain.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.example.snsprojcet.common.config.PasswordEncoder.match;


@Service
@RequiredArgsConstructor
public class UserService {
    // repository 접근
    private final UserRepository userRepository;
    // encoder 접근
    private final PasswordEncoder passwordEncoder;
    // commentRepository 접근
    private final CommentRepository commentRepository;
    // boardRepository 접근
    private final BoardRepository boardRepository;

    // 회원 가입
    public UserSignUpResponseDto signUp(String name, Long age, String nickname, String email, String password, String introduction) {
        // 중복된 email인지 검증
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("이미 가입된 email입니다.");
        }
        // 비밀번호 암호화 실시
        String encodedPassword = passwordEncoder.encode(password);
        // User 객체 생성
        User user = new User(name, age, nickname, email, encodedPassword, introduction);
        // user 객체 저장
        User userSignUp = userRepository.save(user);
        return new UserSignUpResponseDto(userSignUp.getId(), userSignUp.getName(), userSignUp.getAge(), userSignUp.getNickname(), userSignUp.getEmail(), userSignUp.getIntroduction());

    }

    // 전체 유저 목록 조회
    public List<AllUserResponseDto> findAllUser() {
        return userRepository.findAll()
                .stream()
                .filter(User::getActivated) // activated가 true 인 회원만 조회(탈퇴하면 조회 X)
                .map(user -> new AllUserResponseDto(
                        user.getId(),
                        user.getName(),
                        user.getNickname(),
                        user.getIntroduction()
                ))
                .toList();
    }

    // 내 정보 조회
    public UserResponseDto findUserById(Long userId) {
        User findUser = userRepository.findUserByIdOrElseThrow(userId);
        return new UserResponseDto(findUser.getName(), findUser.getAge(), findUser.getNickname(), findUser.getIntroduction());
    }

    // 다른 유저 정보 조회
    public AnotherUserResponseDto findUserByNickname(String nickname) {
        User findAnotherUser = userRepository.findUserByNicknameOrElseThrow(nickname);

        return new AnotherUserResponseDto(findAnotherUser.getNickname(), findAnotherUser.getIntroduction());
    }

    // 비밀번호 수정
    @Transactional
    public void updatePassword(Long userId, String oldPassword, String newPassword) {

        User findUser = userRepository.findUserByIdOrElseThrow(userId);

        // 1. 현재 비밀번호 일치하지 않음
        if (!match(oldPassword, findUser.getPassword())) {         // 비밀번호가 해시 처리돼 있다면 PasswordEncoder.matches()를 사용해야 한다.
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        // 2. 새 비밀번호 형식이 잘못된 경우
        if (!isValidPasswordFormat(newPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호 형식이 올바르지 않습니다.");
        }

        // 3. 새 비밀번호가 기존과 같은 경우
        if (match(newPassword, findUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "현재 비밀번호와 다른 비밀번호를 입력해주세요.");
        }
        // 새로 입력받은 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(newPassword);
        findUser.updatePassword(encodedPassword);
    }

    // 비밀번호 형식 유효성 검사
    private boolean isValidPasswordFormat(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*\\W).{8,}$";   // 비밀번호는 대소문자 포함한 영문 + 숫자 + 특수문자를 최소 1글자씩 포함하며 최소 8글자 이상이어야 합니다
        return password != null && password.matches(regex);
    }

    // 회원 탈퇴
    @Transactional
    public void deleteUser(Long userId, String password) {
        // id로 유저 조회
        User userByIdOrElseThrow = userRepository.findUserByIdOrElseThrow(userId);
        // 비밀번호 검증
        if (!match(password, userByIdOrElseThrow.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");

        }
        // 탈퇴한 회원의 email일 경우 다시 탈퇴 X
        if (!userByIdOrElseThrow.getActivated()) {
            throw new RuntimeException("이미 가입된 적이 있는 email입니다.");
        }
        // 댓글 삭제
        commentRepository.deleteAll(commentRepository.findByUser(userByIdOrElseThrow));
        // 게시글 삭제
        boardRepository.deleteAll(boardRepository.findByUser(userByIdOrElseThrow));
        // 유저 삭제
        userRepository.delete(userByIdOrElseThrow);
    }

    // id 조회
    public User findById (Long id) {
        return userRepository.findUserByIdOrElseThrow(id);
    }

    // nickname 조회
    public User findByNickname (String nickname) {
        return userRepository.findUserByNicknameOrElseThrow(nickname);
    }
}
