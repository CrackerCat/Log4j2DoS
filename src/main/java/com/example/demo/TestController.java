package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Controller
public class TestController {
    private static final Logger logger = LogManager.getLogger(TestController.class);

    @RequestMapping("/cve")
    @ResponseBody
    public String cve(String userId) {
        try {
            String id = new String(Base64.getDecoder().decode(userId));
            ThreadContext.put("loginId", id);
            // 记录该用户已登录
            logger.info("user login");
            // 其他业务逻辑
            // ...
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(String message) {
        try {
            // Base64解码
            String data = new String(Base64.getDecoder().decode(message));
            logger.error("message:" + data);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }
}
