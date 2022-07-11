package com.bigtreetc.sample.task;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
@EnableJpaAuditing
@EnableJpaRepositories
@Configuration
public class JobConfig {

  @Bean
  public ThreadPoolTaskExecutor taskExecutor() {
    val executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(2);
    executor.setMaxPoolSize(64);
    executor.setWaitForTasksToCompleteOnShutdown(true);
    return executor;
  }
}
