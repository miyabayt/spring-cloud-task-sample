package com.bigtreetc.sample.task.jobs.sendmail;

import com.bigtreetc.sample.task.domain.model.SendMailQueue;
import com.bigtreetc.sample.task.domain.repository.SendMailQueueRepository;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.data.domain.PageRequest;

/** 誕生メールの対象を検索する */
@RequiredArgsConstructor
public class SendMailQueueItemReader extends AbstractPagingItemReader<SendMailQueue> {

  @NonNull final SendMailQueueRepository sendMailQueueRepository;

  @Override
  protected void doReadPage() {
    if (results == null) {
      results = new CopyOnWriteArrayList<>();
    } else {
      results.clear();
    }

    val page = getPage(); // 1ページは0になる
    val perpage = getPageSize();
    val pageable = PageRequest.of(page, perpage);

    val pages = sendMailQueueRepository.findBySentAtIsNull(pageable);
    if (!pages.isEmpty()) {
      results.addAll(pages.getContent());
    }
  }

  @Override
  protected void doJumpToPage(int itemIndex) {}
}
