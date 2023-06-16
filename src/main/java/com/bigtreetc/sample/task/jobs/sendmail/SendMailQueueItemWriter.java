package com.bigtreetc.sample.task.jobs.sendmail;

import com.bigtreetc.sample.task.domain.model.SendMailQueue;
import com.bigtreetc.sample.task.domain.repository.SendMailQueueRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@RequiredArgsConstructor
@Slf4j
public class SendMailQueueItemWriter implements ItemWriter<SendMailQueue> {

  @NonNull final SendMailQueueRepository sendMailQueueRepository;

  @Override
  public void write(@org.springframework.lang.NonNull Chunk<? extends SendMailQueue> chunk)
      throws Exception {
    if (log.isDebugEnabled()) {
      log.debug("{} items to write.", chunk.size());
    }
    sendMailQueueRepository.saveAllAndFlush(chunk.getItems());
  }
}
