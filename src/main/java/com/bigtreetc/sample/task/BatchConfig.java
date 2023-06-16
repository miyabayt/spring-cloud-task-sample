package com.bigtreetc.sample.task;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Slf4j
@EnableJpaAuditing
@EnableJpaRepositories
@Configuration
public class BatchConfig {

  @Bean
  public LocalValidatorFactoryBean beanValidator(MessageSource messageSource) {
    val bean = new LocalValidatorFactoryBean();
    bean.setValidationMessageSource(messageSource);
    return bean;
  }

  @Bean
  public ThreadPoolTaskExecutor taskExecutor() {
    val executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(2);
    executor.setMaxPoolSize(64);
    executor.setWaitForTasksToCompleteOnShutdown(true);
    return executor;
  }
}
