package com.api.resource;

import com.api.config.property.ApiProperty;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
@RequestMapping("/tokens")
public class TokenResource {
    private final ApiProperty apiProperty;

    @DeleteMapping("/revoke")
    public void revoke(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(apiProperty.getSeguranca().isEnableHttps());
        cookie.setPath(request.getContextPath() + "/oauth/token");
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }
}
