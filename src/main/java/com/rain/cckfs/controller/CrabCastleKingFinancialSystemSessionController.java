package com.rain.cckfs.controller;

import com.rain.cckfs.pojo.Return;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: LightRain
 * @Description: session状态控制
 * @DateTime: 2023-04-04 02:00
 * @Version：1.0
 **/
@Controller
public class CrabCastleKingFinancialSystemSessionController {
    @GetMapping("session/invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String sessionInvalid(HttpSession session) {
        session.removeAttribute("User");
        return "session已失效，请重新认证";
    }

    @RequestMapping("session/status")
    @ResponseBody
    public Return sessionStatus(HttpSession session) {
        if (session.getAttribute("User") != null) {
            return Return.builder().code(200).message("授权成功").status(Return.SUCCESS).build();
        }
        return Return.builder().code(401).message("尚未授权").status(Return.FAILED).build();
    }

}
