package com.experiment.daeseda_renewal.domain.user;

import com.experiment.daeseda_renewal.constant.SignupStatus;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm() {
        return "/user/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserDto userDTO, Model model) {
        SignupStatus status = userService.signUp(userDTO);

        model.addAttribute("status", status.getMessage());

        if (status == SignupStatus.SUCCESS) {
            return "redirect:/";
        } else {
            return "/user/signup";
        }
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDto userDTO, HttpSession session) {
        UserDto loginResult = userService.login(userDTO);
        if (loginResult != null) {
            session.setAttribute("userId", loginResult.getId());
            session.setAttribute("email", loginResult.getEmail());
            session.setAttribute("name", loginResult.getName());
            return "redirect:/";
        } else {
            return "/user/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @GetMapping("/my-page")
    public String myPage(HttpSession session) {
        if (session.getAttribute("email") == null) {
            return "redirect:/login";  // 로그인 상태가 아니면 로그인 페이지로 리디렉션
        }
        return "/user/my-page";  // 마이페이지를 반환
    }

    @GetMapping("/find-id")
    public String findIdForm(){
        return "/user/find-id";
    }

    @PostMapping("/find-id")
    public String findId(@RequestParam("name") String name, Model model) {
        String email = userService.findEmailByName(name);
        if (email != null) {
            model.addAttribute("email", "이메일: " + email);
        } else {
            model.addAttribute("email", "이름에 해당하는 계정을 찾을 수 없습니다.");
        }
        return "/user/find-id";
    }

    @GetMapping("/find-pw")
    public String findPasswordForm(){
        return "/user/find-pw";
    }

    @PostMapping("/find-pw")
    public String findPassword(@RequestParam("email") String email, Model model) {
        if (userService.isEmailDuplicate(email)) {
            model.addAttribute("password", "비밀번호 재설정");
        } else {
            model.addAttribute("password", "이메일에 해당하는 계정을 찾을 수 없습니다.");
        }
        return "/user/find-pw";
    }
}
