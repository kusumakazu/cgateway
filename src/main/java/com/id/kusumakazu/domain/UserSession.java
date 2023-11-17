package com.id.kusumakazu.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A UserSession.
 */
@Entity
@Table(name = "user_session")
public class UserSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "email")
    private String email;

    @Column(name = "conn_id")
    private String connId;

    @Column(name = "status_conn")
    private Boolean statusConn;

    @Column(name = "player_id")
    private Long playerId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public UserSession sessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getEmail() {
        return email;
    }

    public UserSession email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConnId() {
        return connId;
    }

    public UserSession connId(String connId) {
        this.connId = connId;
        return this;
    }

    public void setConnId(String connId) {
        this.connId = connId;
    }

    public Boolean isStatusConn() {
        return statusConn;
    }

    public UserSession statusConn(Boolean statusConn) {
        this.statusConn = statusConn;
        return this;
    }

    public void setStatusConn(Boolean statusConn) {
        this.statusConn = statusConn;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public UserSession playerId(Long playerId) {
        this.playerId = playerId;
        return this;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserSession)) {
            return false;
        }
        return id != null && id.equals(((UserSession) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserSession{" +
            "id=" + getId() +
            ", sessionId='" + getSessionId() + "'" +
            ", email='" + getEmail() + "'" +
            ", connId='" + getConnId() + "'" +
            ", statusConn='" + isStatusConn() + "'" +
            ", playerId=" + getPlayerId() +
            "}";
    }
}
