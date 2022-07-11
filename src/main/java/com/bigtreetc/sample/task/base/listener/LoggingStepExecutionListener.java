package com.bigtreetc.sample.task.base.listener;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

@Slf4j
public class LoggingStepExecutionListener extends StepExecutionListenerSupport {

  @Override
  public void beforeStep(StepExecution stepExecution) {
    val stepName = stepExecution.getStepName();
    log.info("Step:{} ---- START ----", stepName);
  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    val stepName = stepExecution.getStepName();
    log.info("Step:{} ---- END ----", stepName);
    return stepExecution.getExitStatus();
  }
}
