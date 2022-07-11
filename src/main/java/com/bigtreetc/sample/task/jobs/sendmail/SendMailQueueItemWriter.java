package com.bigtreetc.sample.task.jobs.sendmail;

import com.bigtreetc.sample.task.domain.model.SendMailQueue;
import com.bigtreetc.sample.task.domain.repository.SendMailQueueRepository;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

@RequiredArgsConstructor
@Slf4j
public class SendMailQueueItemWriter implements ItemWriter<SendMailQueue> {

  @NonNull final SendMailQueueRepository sendMailQueueRepository;

  @Override
  public void write(List<? extends SendMailQueue> sendMailQueues) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug("{} items to write.", sendMailQueues.size());
    }
    sendMailQueueRepository.saveAllAndFlush(sendMailQueues);
  }
}
