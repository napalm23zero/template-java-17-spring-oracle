package com.hustletech.template.domain.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.hustletech.template.domain.entity.RoleEndpoint;
import com.hustletech.template.domain.repository.RoleEndpointRepository;

@Service
public class RoleEndpointService {
    private final RoleEndpointRepository roleEndpointRepository;
    private Map<String, Set<String>> roleEndpointMap = new ConcurrentHashMap<>();

    public RoleEndpointService(RoleEndpointRepository roleEndpointRepository) {
        this.roleEndpointRepository = roleEndpointRepository;
        loadRoleEndpoints();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadRoleEndpoints() {
        roleEndpointMap.clear();
        List<RoleEndpoint> roleEndpoints = roleEndpointRepository.findAll();
        for (RoleEndpoint roleEndpoint : roleEndpoints) {
            roleEndpointMap
                    .computeIfAbsent(roleEndpoint.getRole().getName(), k -> new HashSet<>())
                    .add(roleEndpoint.getEndpoint().getUrl());
        }
    }

    public boolean isEndpointAllowed(String roleName, String url) {
        return roleEndpointMap.getOrDefault(roleName, Collections.emptySet()).contains(url);
    }
}