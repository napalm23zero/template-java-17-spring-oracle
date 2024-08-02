package com.hustletech.template.auth.adapter.service;

import org.springframework.stereotype.Service;

import com.hustletech.template.domain.entity.Endpoint;
import com.hustletech.template.domain.entity.Role;
import com.hustletech.template.domain.entity.RoleEndpoint;
import com.hustletech.template.domain.repository.EndpointRepository;
import com.hustletech.template.domain.repository.RoleEndpointRepository;
import com.hustletech.template.domain.repository.RoleRepository;
import com.hustletech.template.domain.service.RoleEndpointService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final EndpointRepository endpointRepository;
    private final RoleEndpointRepository roleEndpointRepository;
    private final RoleEndpointService roleEndpointService;

    public Role createRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }

    public Endpoint createEndpoint(String url) {
        Endpoint endpoint = new Endpoint();
        endpoint.setUrl(url);
        return endpointRepository.save(endpoint);
    }

    public void assignEndpointToRole(Long roleId, Long endpointId) {
        Role role = roleRepository.findById(roleId).orElseThrow();
        Endpoint endpoint = endpointRepository.findById(endpointId).orElseThrow();
        RoleEndpoint roleEndpoint = new RoleEndpoint();
        roleEndpoint.setRole(role);
        roleEndpoint.setEndpoint(endpoint);
        roleEndpointRepository.save(roleEndpoint);
        roleEndpointService.loadRoleEndpoints(); // Refresh the cache
    }
}