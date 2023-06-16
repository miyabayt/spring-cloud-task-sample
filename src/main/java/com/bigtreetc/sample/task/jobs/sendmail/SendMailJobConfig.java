package com.bigtreetc.sample.task.jobs.sendmail;

import com.bigtreetc.sample.task.base.listener.LoggingStepExecutionListener;
import com.bigtreetc.sample.task.domain.model.SendMailQueue;
import com.bigtreetc.sample.task.domain.repository.SendMailQueueRepository;
import lombok.val;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/** メール送信バッチ */
@Configuration
public class SendMailJobConfig {

  @Bean
  public SendMailQueueItemReader itemReader(SendMailQueueRepository sendMailQueueRepository) {
    val reader = new SendMailQueueItemReader(sendMailQueueRepository);
    reader.setPageSize(100); // 100件ずつ取得するサンプル
    return reader;
  }

  @Bean
  public SendMailQueueProcessor processor() {
    return new SendMailQueueProcessor();
  }

  @Bean
  public SendMailQueueItemWriter itemWriter(SendMailQueueRepository sendMailQueueRepository) {
    return new SendMailQueueItemWriter(sendMailQueueRepository);
  }

  @Bean
  public Step sendMailStep(
      JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      SendMailQueueItemReader itemReader,
      SendMailQueueProcessor processor,
      SendMailQueueItemWriter itemWriter) {
    return new StepBuilder("sendMailStep", jobRepository)
        .listener(new LoggingStepExecutionListener())
        .<SendMailQueue, SendMailQueue>chunk(100, transactionManager)
        .reader(itemReader)
        .processor(processor)
        .writer(itemWriter)
        .build();
  }

  @Bean
  public Job sendMailJob(JobRepository jobRepository, @Qualifier("sendMailStep") Step step) {
    return new JobBuilder("sendMailJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .listener(new SendMailJobExecutionListener())
        .flow(step)
        .end()
        .build();
  }
}
