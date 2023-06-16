package com.bigtreetc.sample.task.jobs.sendmail;

import com.bigtreetc.sample.task.domain.helper.SendMailHelper;
import com.bigtreetc.sample.task.domain.model.SendMailQueue;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
@Slf4j
public class SendMailQueueProcessor implements ItemProcessor<SendMailQueue, SendMailQueue> {

  @Value("${spring.mail.properties.mail.from}")
  String fromAddress;

  @Autowired SendMailHelper sendMailHelper;

  @Override
  public SendMailQueue process(SendMailQueue sendMailQueue) throws Exception {
    val to = sendMailQueue.getTo();
    val cc = sendMailQueue.getCc();
    val bcc = sendMailQueue.getBcc();
    val subject = sendMailQueue.getSubject();
    val body = sendMailQueue.getBody();
    try {
      sendMailHelper.sendMail(fromAddress, to, cc, bcc, subject, body);
      sendMailQueue.setSentAt(LocalDateTime.now());
    } catch (Exception e) {
      // skip
      log.warn("cloud not send mail. [id={}]", sendMailQueue.getId());
      return null;
    }
    return sendMailQueue;
  }
}
