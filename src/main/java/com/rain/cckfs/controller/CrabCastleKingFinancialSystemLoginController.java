package com.rain.cckfs.controller;

import com.rain.cckfs.mapper.CrabCastleKingFinancialSystemUserMapper;
import com.rain.cckfs.pojo.Return;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LightRain
 * @Description: 登录授权控制器
 * @DateTime: 2023-04-03 02:33
 * @Version：1.0
 **/
@RestController
@RequestMapping("/login")
public class CrabCastleKingFinancialSystemLoginController {
    private final CrabCastleKingFinancialSystemUserMapper mapper;

    public CrabCastleKingFinancialSystemLoginController(CrabCastleKingFinancialSystemUserMapper mapper) {
        this.mapper = mapper;
    }

    @RequestMapping(value = "/success", method = {RequestMethod.GET, RequestMethod.POST})
    public Return loginSuccess(HttpSession session, String username, String captcha) {
        //获取生成的验证码
        String code = (String) session.getAttribute("kaptchaCode");
        //匹配验证码
        if (captcha.equals(code)) {
            session.setAttribute("User", mapper.getUserName(username));
            return Return.builder().code(200).message("授权成功").status(Return.SUCCESS).build();
        }
        return Return.builder().code(403).message("验证码错误").status(Return.FAILED).build();
    }

    @RequestMapping(value = "/failure", method = {RequestMethod.GET, RequestMethod.POST})
    public Return loginFailure() {
        return Return.builder().code(401).message("授权失败").status(Return.FAILED).build();
    }

    /**
     * @Author: LightRain
     * @Date: 6/4/2023 下午 3:52
     * @Param: [request, response]
     * @Return: java.lang.String
     * @Description: 退出登录
     * @since 17
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
}