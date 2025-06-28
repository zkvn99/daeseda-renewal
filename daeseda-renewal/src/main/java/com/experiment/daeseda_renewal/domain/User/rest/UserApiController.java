package com.experiment.daeseda_renewal.domain.User.rest;

import com.experiment.daeseda_renewal.domain.User.UserService;
import com.experiment.daeseda_renewal.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final MailService mailService;
    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/check-email")
    public ResponseEntity<Map<String, Boolean>> checkEmailDuplicate(@RequestParam String email) {
        boolean isDuplicate = userService.isEmailDuplicate(email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("duplicate", isDuplicate);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mailAuthentication")
    public ResponseEntity<String> mailAuthentication(@RequestParam String email) throws Exception {
        boolean isDuplicate = userService.isEmailDuplicate(email);

        if(isDuplicate) {
            String code = mailService.sendMessage(email);
            redisTemplate.opsForValue().set("EMAIL_CODE" + email, code);
            return ResponseEntity.ok(code);
        }

        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("잘못된 형식입니다.");
    }
}
