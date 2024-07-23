package com.hustletech.template.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hustletech.template.domain.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}