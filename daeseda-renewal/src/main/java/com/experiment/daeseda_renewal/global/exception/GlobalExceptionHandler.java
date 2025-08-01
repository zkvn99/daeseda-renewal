package com.experiment.daeseda_renewal.global.exception;

import com.experiment.daeseda_renewal.constant.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    // 비즈니스 예외 핸들러
    @ExceptionHandler(BusinessException.class)
    public String handleException(BusinessException ex, Model model) {
        ErrorCode errorCode = ex.getErrorCode();
        model.addAttribute("errorMessage", errorCode.getMessage());
        log.error("로직 예외 발생", ex);
        return "error/address-error";
    }

    @ExceptionHandler(Exception.class)
    public String handleUnexpectedException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "알 수 없는 오류가 발생했습니다.");
        log.error("예외 발생", ex);
        return "error/global-error";
    }

}
