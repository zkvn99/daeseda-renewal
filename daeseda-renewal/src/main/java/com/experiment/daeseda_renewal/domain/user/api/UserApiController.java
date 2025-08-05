package com.experiment.daeseda_renewal.domain.user.api;

import com.experiment.daeseda_renewal.domain.user.UserService;
import com.experiment.daeseda_renewal.domain.user.dto.CreateUserRequest;
import com.experiment.daeseda_renewal.domain.user.dto.LoginRequest;
import com.experiment.daeseda_renewal.domain.user.dto.LoginResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {

  private final UserService userService;
  //  private final MailService mailService;
  private final RedisTemplate<String, Object> redisTemplate;

  @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody CreateUserRequest request) {
    userService.signUp(request);
    return ResponseEntity.ok("회원가입 성공");
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpSession session) {
    LoginResponse loginResult = userService.login(request);
    if (loginResult != null) {
      session.setAttribute("userId", loginResult.getUserId());
      session.setAttribute("email", loginResult.getEmail());
      session.setAttribute("name", loginResult.getName());
      return ResponseEntity.ok("로그인 성공");
    } else {
      return ResponseEntity.badRequest()
                           .build();
    }
  }

  @GetMapping("/check-email")
  public ResponseEntity<Map<String, Boolean>> checkEmailDuplicate(@RequestParam String email) {
    boolean isDuplicate = userService.isEmailDuplicate(email);
    Map<String, Boolean> response = new HashMap<>();
    response.put("duplicate", isDuplicate);
    return ResponseEntity.ok(response);
  }

//  @PostMapping("/mail-authentication")
//  public ResponseEntity<String> mailAuthentication(@RequestParam String email) throws Exception {
//    boolean isDuplicate = userService.isEmailDuplicate(email);
//
//    if (isDuplicate) {
//      String code = mailService.sendMessage(email);
//      redisTemplate.opsForValue()
//                   .set("EMAIL_CODE" + email, code);
//      return ResponseEntity.ok(code);
//    }
//
//    return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
//                         .body("잘못된 형식입니다.");
//  }
}
