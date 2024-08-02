package com.hustletech.template.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "endpoints")
public class Endpoint extends GenericEntity {

    private String url;

    public Endpoint(String url) {
        this.url = url;
    }
}
