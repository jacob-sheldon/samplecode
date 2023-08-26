package com.jacob.email;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/*
 *@title MailServiceTest
 *@description
 *@author Jacob Sheldon
 *@version 1.0
 *@create 7/28/23 11:45 AM
 */
@SpringBootTest
class MailServiceTest {
    @Autowired
    private MailService mailService;

    @Resource
    private TemplateEngine templateEngine;

    @Test
    void sendSimpleMail() {
        mailService.sendSimpleMail("shizhiang@126.com","测试spring boot imail-主题",
                "测试spring boot imail - 内容");
    }

    @Test
    void sendHtmlMail() {
        Context context = new Context();
        context.setVariable("id","ispringboot");

        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail("shizhiang@126.com","这是一封HTML模板邮件",emailContent);
    }
}