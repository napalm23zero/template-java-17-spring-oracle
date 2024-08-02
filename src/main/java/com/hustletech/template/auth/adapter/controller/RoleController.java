package com.hustletech.template.auth.adapter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hustletech.template.auth.adapter.service.RoleService;
import com.hustletech.template.domain.entity.Role;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody String roleName) {
        Role role = roleService.createRole(roleName);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }

    @PostMapping("/{roleId}/endpoints/{endpointId}")
    public ResponseEntity<Void> assignEndpointToRole(@PathVariable Long roleId, @PathVariable Long endpointId) {
        roleService.assignEndpointToRole(roleId, endpointId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}