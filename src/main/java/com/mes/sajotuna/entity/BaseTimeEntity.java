package com.mes.sajotuna.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter @Setter
public abstract class BaseTimeEntity {

    @CreatedDate // 엔티티가 생성되어 저장될 때 시간 자동 저장
    @Column(updatable = false)
    private LocalDateTime regTime;

    //    조회한 엔티티의 값을 변경할 때 시간 자동저장
    @LastModifiedDate
    private LocalDateTime updateTime;

}