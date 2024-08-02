package com.hustletech.template.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hustletech.template.domain.entity.Endpoint;

public interface EndpointRepository extends JpaRepository<Endpoint, Long> {
    boolean existsByUrl(String url);
}
