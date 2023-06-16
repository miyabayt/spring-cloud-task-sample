package com.bigtreetc.sample.task.base.listener;

import com.bigtreetc.sample.task.base.context.BatchContextHolder;
import com.bigtreetc.sample.task.base.util.DateUtils;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class LoggingJobExecutionListener implements JobExecutionListener {

  private static final DateTimeFormatter YYYY_MM_DD_HHmmss =
      DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

  @Override
  public void afterJob(JobExecution jobExecution) {
    val context = BatchContextHolder.getContext();
    val jobName = jobExecution.getJobInstance().getJobName();
    val jobStatus = jobExecution.getStatus();
    val startTime = jobExecution.getStartTime();
    val endTime = jobExecution.getEndTime();
    log.info("*********************************************");
    log.info("* ジョブ名\t\t: {}", jobName);
    log.info("* ステータス\t\t: {}", jobStatus);
    log.info("* 対象件数\t\t: {}", context.getTotalCount());
    log.info("* 処理件数\t\t: {}", context.getProcessCount());
    log.info("* エラー件数\t\t: {}", context.getErrorCount());
    log.info("* 開始時刻\t\t: {}", DateUtils.format(startTime, YYYY_MM_DD_HHmmss));
    log.info("* 終了時刻\t\t: {}", DateUtils.format(endTime, YYYY_MM_DD_HHmmss));
    log.info("*********************************************");
  }
}
