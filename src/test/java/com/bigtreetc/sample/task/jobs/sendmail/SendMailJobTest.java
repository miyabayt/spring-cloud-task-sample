package com.bigtreetc.sample.task.jobs.sendmail;

import static org.assertj.core.api.Assertions.assertThat;

import com.bigtreetc.sample.task.Application;
import com.bigtreetc.sample.task.BaseTestContainerTest;
import com.bigtreetc.sample.task.BatchConfig;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {Application.class, BatchConfig.class, SendMailJobConfig.class})
class SendMailJobTest extends BaseTestContainerTest {

  @Autowired
  @Qualifier("sendMailJob")
  Job job;

  @Autowired JobLauncher jobLauncher;

  @Autowired JobRepository jobRepository;

  private JobLauncherTestUtils testUtils;

  @BeforeEach
  public void before() {
    testUtils = new JobLauncherTestUtils();
    testUtils.setJobLauncher(jobLauncher);
    testUtils.setJobRepository(jobRepository);
    testUtils.setJob(job);
  }

  @Test
  @DisplayName("メール送信バッチが起動すること")
  void test1() throws Exception {
    val jobParameter = testUtils.getUniqueJobParameters();
    val execution = testUtils.getJobLauncher().run(job, jobParameter);

    val actual = execution.getStatus().name();
    val expected = BatchStatus.COMPLETED.name();
    assertThat(actual).isEqualTo(expected);
  }
}
