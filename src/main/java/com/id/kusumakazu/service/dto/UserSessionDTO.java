package com.id.kusumakazu.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.id.kusumakazu.domain.UserSession} entity.
 */
public class UserSessionDTO implements Serializable {
    
    private Long id;

    private String sessionId;

    private String email;

    private String connId;

    private Boolean statusConn;

    private Long playerId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConnId() {
        return connId;
    }

    public void setConnId(String connId) {
        this.connId = connId;
    }

    public Boolean isStatusConn() {
        return statusConn;
    }

    public void setStatusConn(Boolean statusConn) {
        this.statusConn = statusConn;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserSessionDTO)) {
            return false;
        }

        return id != null && id.equals(((UserSessionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserSessionDTO{" +
            "id=" + getId() +
            ", sessionId='" + getSessionId() + "'" +
            ", email='" + getEmail() + "'" +
            ", connId='" + getConnId() + "'" +
            ", statusConn='" + isStatusConn() + "'" +
            ", playerId=" + getPlayerId() +
            "}";
    }
}
