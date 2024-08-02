package com.hustletech.template.domain.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "role_endpoint")
public class RoleEndpoint {

    @EmbeddedId
    private RoleEndpointId id;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "endpoint_id", insertable = false, updatable = false)
    private Endpoint endpoint;

    public RoleEndpoint(Role role, Endpoint endpoint) {
        this.id = new RoleEndpointId(role.getId(), endpoint.getId());
        this.role = role;
        this.endpoint = endpoint;
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    public static class RoleEndpointId implements Serializable {
        @Column(name = "role_id")
        private Long roleId;

        @Column(name = "endpoint_id")
        private Long endpointId;

        public RoleEndpointId(Long roleId, Long endpointId) {
            this.roleId = roleId;
            this.endpointId = endpointId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(roleId, endpointId);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            RoleEndpointId that = (RoleEndpointId) obj;
            return Objects.equals(roleId, that.roleId) && Objects.equals(endpointId, that.endpointId);
        }
    }
}
