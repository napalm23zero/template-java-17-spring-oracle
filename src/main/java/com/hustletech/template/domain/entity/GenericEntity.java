package com.hustletech.template.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(GenericEntity.GenericEntityListener.class)
public abstract class GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    public static class GenericEntityListener {

        @PrePersist
        public void prePersist(GenericEntity entity) {
            LocalDateTime now = LocalDateTime.now();
            entity.setCreateDate(now);
            entity.setUpdateDate(now);
        }

        @PreUpdate
        public void preUpdate(GenericEntity entity) {
            entity.setUpdateDate(LocalDateTime.now());
        }
    }
}
