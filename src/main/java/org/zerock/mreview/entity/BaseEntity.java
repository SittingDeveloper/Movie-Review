package org.zerock.mreview.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
abstract class BaseEntity {
    @CreatedDate // 최초 생성 시간
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate // 최종 수정 시간
    @Column(name = "moddate")
    private LocalDateTime modDate;
}
