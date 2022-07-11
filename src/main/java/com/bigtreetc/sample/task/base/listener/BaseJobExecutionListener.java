package com.bigtreetc.sample.task.base.listener;

import com.bigtreetc.sample.task.base.util.DateUtils;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

@Slf4j
public abstract class BaseJobExecutionListener extends JobExecutionListenerSupport {

  private static final DateTimeFormatter YYYY_MM_DD_HHmmss =
      DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

  @Override
  public void beforeJob(JobExecution jobExecution) {}

  @Override
  public void afterJob(JobExecution jobExecution) {
    val batchId = getBatchId();
    val batchName = getBatchName();
    val jobStatus = jobExecution.getStatus().getBatchStatus();
    val startTime = jobExecution.getStartTime();
    val endTime = jobExecution.getEndTime();
    log.info("*********************************************");
    log.info("* バッチID   : {}", batchId);
    log.info("* バッチ名   : {}", batchName);
    log.info("* ステータス : {}", jobStatus);
    log.info("* 開始時刻   : {}", DateUtils.format(startTime, YYYY_MM_DD_HHmmss));
    log.info("* 終了時刻   : {}", DateUtils.format(endTime, YYYY_MM_DD_HHmmss));
    log.info("*********************************************");
  }

  protected abstract String getBatchId();

  protected abstract String getBatchName();
}
