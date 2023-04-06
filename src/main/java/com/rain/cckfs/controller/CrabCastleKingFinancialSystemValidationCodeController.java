package com.rain.cckfs.controller;


import cn.hutool.core.codec.Base64;
import com.google.code.kaptcha.Producer;
import com.rain.cckfs.pojo.Return;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: LightRain
 * @Description: 验证码
 * @DateTime: 2023-04-04 14:21
 * @Version：1.0
 **/
@Controller
public class CrabCastleKingFinancialSystemValidationCodeController {

    private final Producer kaptchaProducer;
    private final Producer kaptchaProducerMath;

    public CrabCastleKingFinancialSystemValidationCodeController(Producer kaptchaProducer, Producer kaptchaProducerMath) {
        this.kaptchaProducer = kaptchaProducer;
        this.kaptchaProducerMath = kaptchaProducerMath;
    }

    /**
     * @Author: LightRain
     * @Date: 4/4/2023 下午 3:51
     * @Param: [request, response]
     * @Return: void
     * @Description: 生成验证码图片
     * @since 17
     */
//    @GetMapping("/createKaptchaCodeImg")
//    public void createKaptchaCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request.getSession().removeAttribute("kaptchaCode");
//        response.setHeader("Cache-Control", "no-store");
//        response.setContentType("image/jpeg");
//        //文本验证码
//        String text = kaptchaProducer.createText();
//        //图片验证码
//        BufferedImage image = kaptchaProducer.createImage(text);
//        //保存验证码到session
//        request.getSession().setAttribute("kaptchaCode", text);
//        jakarta.servlet.ServletOutputStream outputStream = response.getOutputStream();
//        //设置写出图片的格式
//        ImageIO.write(image, "jpg", outputStream);
//        //关闭输出流
//        IOUtils.closeQuietly(outputStream);
//    }

    @GetMapping("/createKaptchaCodeImg")
    @ResponseBody
    public Return createKaptchaCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("kaptchaCode");
        // 设置浏览器缓存机制
        response.setHeader("Cache-Control", "no-store, no-cache");
        // 设置返回响应类型
        response.setContentType("image/jpeg");
        // 生成验证码
        String code = kaptchaProducer.createText();
        //保存验证码到session
        request.getSession().setAttribute("kaptchaCode", code);
        // 生成图片验证码
        BufferedImage image = kaptchaProducer.createImage(code);
        // 转为Base64
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", stream);
        String imgString = "data:image/gif;base64," + Base64.encode(stream.toByteArray());
        stream.flush();
        stream.close();
        // 将数据存入并将其返回
        return Return.builder().code(200).message(imgString).status(Return.SUCCESS).build();
    }

    /**
     * @Author: LightRain
     * @Date: 4/4/2023 下午 3:51
     * @Param: [request, response]
     * @Return: void
     * @Description: 生成验证码图片（有边框）
     * @since 17
     */
    @GetMapping("/createKaptchaCodeImg2")
    public void createKaptchaCode2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("kaptchaCode");
        response.setHeader("Cache-Control", "no-store");
        response.setContentType("image/jpeg");
        //文本验证码
        String text = kaptchaProducer.createText();
        //图片验证码
        BufferedImage image = kaptchaProducerMath.createImage(text);
        //保存验证码到session
        request.getSession().setAttribute("kaptchaCodeMath", text);
        ServletOutputStream outputStream = response.getOutputStream();
        //设置写出图片的格式
        ImageIO.write(image, "jpg", outputStream);
        //关闭输出流
        IOUtils.closeQuietly(outputStream);
    }
}