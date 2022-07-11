package com.bigtreetc.sample.task.jobs.sendmail;

import com.bigtreetc.sample.task.base.listener.BaseJobExecutionListener;

public class SendMailJobExecutionListener extends BaseJobExecutionListener {

  @Override
  protected String getBatchId() {
    return "BATCH_001";
  }

  @Override
  protected String getBatchName() {
    return "メール送信";
  }
}
