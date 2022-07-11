package com.bigtreetc.sample.task.domain.repository;

import com.bigtreetc.sample.task.domain.model.SendMailQueue;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendMailQueueRepository extends JpaRepository<SendMailQueue, UUID> {

  Page<SendMailQueue> findBySentAtIsNull(Pageable pageable);
}
