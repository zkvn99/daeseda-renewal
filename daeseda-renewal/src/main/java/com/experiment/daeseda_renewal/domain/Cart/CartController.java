package com.experiment.daeseda_renewal.domain.Cart;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity,
                            HttpSession session, RedirectAttributes redirectAttributes) {
        Long userId = (Long) session.getAttribute("userId");
        cartService.addToCart(userId, productId, quantity);

        redirectAttributes.addFlashAttribute("message", "상품이 장바구니에 추가되었습니다!");

        return "redirect:/products/list";
    }

    @GetMapping("/list")
    public String viewCart(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("cartItems", cartService.getCartItems(userId));
        return "cart/list";
    }
}
