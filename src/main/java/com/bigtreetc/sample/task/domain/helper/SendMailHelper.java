package com.bigtreetc.sample.task.domain.helper;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/** メール送信ヘルパー */
@Component
@Slf4j
public class SendMailHelper {

  @Autowired JavaMailSender javaMailSender;

  /**
   * メールを送信します。
   *
   * @param fromAddress
   * @param to
   * @param cc
   * @param bcc
   * @param subject
   * @param body
   */
  public void sendMail(
      String fromAddress, String to, String cc, String bcc, String subject, String body) {
    val message = new SimpleMailMessage();
    message.setFrom(fromAddress);
    message.setTo(to);
    if (cc != null && cc.length() > 0) {
      message.setCc(cc);
    }
    if (bcc != null && bcc.length() > 0) {
      message.setBcc(bcc);
    }
    message.setSubject(subject);
    message.setText(body);

    try {
      javaMailSender.send(message);
    } catch (MailException e) {
      log.error("failed to send mail.", e);
      throw e;
    }
  }

  /**
   * 指定したテンプレートのメール本文を返します。
   *
   * @param template
   * @param objects
   * @return
   */
  public String getMailBody(String template, Map<String, Object> objects) {
    val templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver());

    val context = new Context();
    if (objects != null && !objects.isEmpty()) {
      objects.forEach(context::setVariable);
    }

    return templateEngine.process(template, context);
  }

  protected ITemplateResolver templateResolver() {
    val resolver = new ClassLoaderTemplateResolver();
    resolver.setTemplateMode("TEXT");
    return resolver;
  }
}
