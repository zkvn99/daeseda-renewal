package com.experiment.daeseda_renewal.global.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

  private static final Logger log = LoggerFactory.getLogger(LoginCheckInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
      throws Exception {

    HttpSession session = req.getSession(false);
    boolean loggedIn = (session != null && session.getAttribute("userId") != null); // 실제 키로!
    if (loggedIn) {
      return true;
    }

    String uri = req.getRequestURI();
    String qs = req.getQueryString();
    String redirectTo = (qs == null) ? uri : uri + "?" + qs;

    if (uri.startsWith("/api/")) {
      res.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
      res.setContentType("application/json;charset=UTF-8");
      res.getWriter()
         .write("{\"message\":\"UNAUTHORIZED\",\"redirect\":\"/users/login?redirect="
                    + URLEncoder.encode(redirectTo, StandardCharsets.UTF_8) + "\"}");
      return false;
    } else {
      FlashMap flash = new FlashMap();
      flash.put("alertType", "warning");
      flash.put("alertMsg", "로그인이 필요한 서비스입니다.");

      FlashMapManager flashManager = RequestContextUtils.getFlashMapManager(req);
      if (flashManager != null) {
        flashManager.saveOutputFlashMap(flash, req, res);
      }

      res.sendRedirect("/users/login");
      return false;
    }
  }

}
