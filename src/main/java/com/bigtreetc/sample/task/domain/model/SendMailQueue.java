package com.bigtreetc.sample.task.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

@Getter
@Setter
@Entity
@Table(name = "send_mail_queue")
public class SendMailQueue implements Persistable<UUID> {

  @Id
  @Type(type = "uuid-char")
  UUID id;

  @Column(name = "from_address")
  String from;

  @Column(name = "to_address")
  String to;

  @Column(name = "cc_address")
  String cc;

  @Column(name = "bcc_address")
  String bcc;

  LocalDateTime sentAt;

  String subject;

  String body;

  // 作成者
  @CreatedBy String createdBy;

  // 作成日時
  @CreatedDate LocalDateTime createdAt;

  // 更新者
  @LastModifiedBy String updatedBy;

  // 更新日時
  @LastModifiedDate LocalDateTime updatedAt;

  // 楽観的排他制御で使用する改定番号
  @Version Integer version;

  @Override
  public boolean isNew() {
    return getCreatedAt() == null;
  }
}
