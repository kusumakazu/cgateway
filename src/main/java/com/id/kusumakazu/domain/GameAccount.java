package com.id.kusumakazu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GameAccount.
 */
@Entity
@Table(name = "game_account")
public class GameAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 15)
    @Column(name = "account_name", length = 15, nullable = false, unique = true)
    private String accountName;

    @OneToMany(mappedBy = "gameAccount")
    private Set<Player> players = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "gameAccounts", allowSetters = true)
    private MasterAccount masterAccount;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public GameAccount accountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public GameAccount players(Set<Player> players) {
        this.players = players;
        return this;
    }

    public GameAccount addPlayer(Player player) {
        this.players.add(player);
        player.setGameAccount(this);
        return this;
    }

    public GameAccount removePlayer(Player player) {
        this.players.remove(player);
        player.setGameAccount(null);
        return this;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public MasterAccount getMasterAccount() {
        return masterAccount;
    }

    public GameAccount masterAccount(MasterAccount masterAccount) {
        this.masterAccount = masterAccount;
        return this;
    }

    public void setMasterAccount(MasterAccount masterAccount) {
        this.masterAccount = masterAccount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GameAccount)) {
            return false;
        }
        return id != null && id.equals(((GameAccount) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GameAccount{" +
            "id=" + getId() +
            ", accountName='" + getAccountName() + "'" +
            "}";
    }
}
