package com.bigtreetc.sample.task;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

@EnableTask
@SpringBootApplication
@Slf4j
public class Application {

  public static void main(String[] args) {
    try {
      val application = new SpringApplication(Application.class);
      application.setWebApplicationType(WebApplicationType.NONE);

      val context = application.run(args);
      val exitCode = SpringApplication.exit(context);
      System.exit(exitCode);
    } catch (Throwable t) {
      log.error("failed to run. ", t);
      System.exit(1);
    }
  }
}
