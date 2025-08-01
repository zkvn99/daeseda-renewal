package com.experiment.daeseda_renewal.global.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 주소 예외 발생 시 화면
    @ExceptionHandler(AddressException.class)
    public String handleAddressException(AddressException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/address-error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "알 수 없는 오류가 발생했습니다.");
        return "error/global-error";
    }

}
