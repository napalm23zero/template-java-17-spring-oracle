package com.hustletech.template.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hustletech.template.domain.entity.Endpoint;
import com.hustletech.template.domain.entity.Role;
import com.hustletech.template.domain.entity.RoleEndpoint;
import com.hustletech.template.domain.entity.RoleEndpoint.RoleEndpointId;

public interface RoleEndpointRepository extends JpaRepository<RoleEndpoint, RoleEndpointId> {
    boolean existsByRoleAndEndpoint(Role role, Endpoint endpoint);
}
