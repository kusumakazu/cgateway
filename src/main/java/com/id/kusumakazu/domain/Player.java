package com.id.kusumakazu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.id.kusumakazu.domain.enumeration.PlayerType;

import com.id.kusumakazu.domain.enumeration.Faction;

/**
 * A Player.
 */
@Entity
@Table(name = "player")
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "character_name", nullable = false)
    private String characterName;

    @Enumerated(EnumType.STRING)
    @Column(name = "character_type")
    private PlayerType characterType;

    @Enumerated(EnumType.STRING)
    @Column(name = "character_faction")
    private Faction characterFaction;

    @Column(name = "experience")
    private Long experience;

    @Column(name = "character_level")
    private Integer characterLevel;

    @Column(name = "last_location")
    private String lastLocation;

    @Column(name = "last_location_coordinate")
    private String lastLocationCoordinate;

    @Column(name = "last_login")
    private Instant lastLogin;

    @OneToOne
    @JoinColumn(unique = true)
    private PlayerDetl playerDetl;

    @OneToOne
    @JoinColumn(unique = true)
    private PlayerClass playerClass;

    @OneToOne
    @JoinColumn(unique = true)
    private PlayerCurrency playerCurrency;

    @OneToMany(mappedBy = "player")
    private Set<PlayerInventory> playerInventories = new HashSet<>();

    @OneToMany(mappedBy = "player")
    private Set<StorageInventory> storageInventories = new HashSet<>();

    @OneToMany(mappedBy = "player")
    private Set<WeaponDetl> weaponDetls = new HashSet<>();

    @OneToMany(mappedBy = "player")
    private Set<ArmorDetl> armorDetls = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "players", allowSetters = true)
    private GameAccount gameAccount;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public Player characterName(String characterName) {
        this.characterName = characterName;
        return this;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public PlayerType getCharacterType() {
        return characterType;
    }

    public Player characterType(PlayerType characterType) {
        this.characterType = characterType;
        return this;
    }

    public void setCharacterType(PlayerType characterType) {
        this.characterType = characterType;
    }

    public Faction getCharacterFaction() {
        return characterFaction;
    }

    public Player characterFaction(Faction characterFaction) {
        this.characterFaction = characterFaction;
        return this;
    }

    public void setCharacterFaction(Faction characterFaction) {
        this.characterFaction = characterFaction;
    }

    public Long getExperience() {
        return experience;
    }

    public Player experience(Long experience) {
        this.experience = experience;
        return this;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public Integer getCharacterLevel() {
        return characterLevel;
    }

    public Player characterLevel(Integer characterLevel) {
        this.characterLevel = characterLevel;
        return this;
    }

    public void setCharacterLevel(Integer characterLevel) {
        this.characterLevel = characterLevel;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public Player lastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
        return this;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }

    public String getLastLocationCoordinate() {
        return lastLocationCoordinate;
    }

    public Player lastLocationCoordinate(String lastLocationCoordinate) {
        this.lastLocationCoordinate = lastLocationCoordinate;
        return this;
    }

    public void setLastLocationCoordinate(String lastLocationCoordinate) {
        this.lastLocationCoordinate = lastLocationCoordinate;
    }

    public Instant getLastLogin() {
        return lastLogin;
    }

    public Player lastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }

    public PlayerDetl getPlayerDetl() {
        return playerDetl;
    }

    public Player playerDetl(PlayerDetl playerDetl) {
        this.playerDetl = playerDetl;
        return this;
    }

    public void setPlayerDetl(PlayerDetl playerDetl) {
        this.playerDetl = playerDetl;
    }

    public PlayerClass getPlayerClass() {
        return playerClass;
    }

    public Player playerClass(PlayerClass playerClass) {
        this.playerClass = playerClass;
        return this;
    }

    public void setPlayerClass(PlayerClass playerClass) {
        this.playerClass = playerClass;
    }

    public PlayerCurrency getPlayerCurrency() {
        return playerCurrency;
    }

    public Player playerCurrency(PlayerCurrency playerCurrency) {
        this.playerCurrency = playerCurrency;
        return this;
    }

    public void setPlayerCurrency(PlayerCurrency playerCurrency) {
        this.playerCurrency = playerCurrency;
    }

    public Set<PlayerInventory> getPlayerInventories() {
        return playerInventories;
    }

    public Player playerInventories(Set<PlayerInventory> playerInventories) {
        this.playerInventories = playerInventories;
        return this;
    }

    public Player addPlayerInventory(PlayerInventory playerInventory) {
        this.playerInventories.add(playerInventory);
        playerInventory.setPlayer(this);
        return this;
    }

    public Player removePlayerInventory(PlayerInventory playerInventory) {
        this.playerInventories.remove(playerInventory);
        playerInventory.setPlayer(null);
        return this;
    }

    public void setPlayerInventories(Set<PlayerInventory> playerInventories) {
        this.playerInventories = playerInventories;
    }

    public Set<StorageInventory> getStorageInventories() {
        return storageInventories;
    }

    public Player storageInventories(Set<StorageInventory> storageInventories) {
        this.storageInventories = storageInventories;
        return this;
    }

    public Player addStorageInventory(StorageInventory storageInventory) {
        this.storageInventories.add(storageInventory);
        storageInventory.setPlayer(this);
        return this;
    }

    public Player removeStorageInventory(StorageInventory storageInventory) {
        this.storageInventories.remove(storageInventory);
        storageInventory.setPlayer(null);
        return this;
    }

    public void setStorageInventories(Set<StorageInventory> storageInventories) {
        this.storageInventories = storageInventories;
    }

    public Set<WeaponDetl> getWeaponDetls() {
        return weaponDetls;
    }

    public Player weaponDetls(Set<WeaponDetl> weaponDetls) {
        this.weaponDetls = weaponDetls;
        return this;
    }

    public Player addWeaponDetl(WeaponDetl weaponDetl) {
        this.weaponDetls.add(weaponDetl);
        weaponDetl.setPlayer(this);
        return this;
    }

    public Player removeWeaponDetl(WeaponDetl weaponDetl) {
        this.weaponDetls.remove(weaponDetl);
        weaponDetl.setPlayer(null);
        return this;
    }

    public void setWeaponDetls(Set<WeaponDetl> weaponDetls) {
        this.weaponDetls = weaponDetls;
    }

    public Set<ArmorDetl> getArmorDetls() {
        return armorDetls;
    }

    public Player armorDetls(Set<ArmorDetl> armorDetls) {
        this.armorDetls = armorDetls;
        return this;
    }

    public Player addArmorDetl(ArmorDetl armorDetl) {
        this.armorDetls.add(armorDetl);
        armorDetl.setPlayer(this);
        return this;
    }

    public Player removeArmorDetl(ArmorDetl armorDetl) {
        this.armorDetls.remove(armorDetl);
        armorDetl.setPlayer(null);
        return this;
    }

    public void setArmorDetls(Set<ArmorDetl> armorDetls) {
        this.armorDetls = armorDetls;
    }

    public GameAccount getGameAccount() {
        return gameAccount;
    }

    public Player gameAccount(GameAccount gameAccount) {
        this.gameAccount = gameAccount;
        return this;
    }

    public void setGameAccount(GameAccount gameAccount) {
        this.gameAccount = gameAccount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }
        return id != null && id.equals(((Player) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Player{" +
            "id=" + getId() +
            ", characterName='" + getCharacterName() + "'" +
            ", characterType='" + getCharacterType() + "'" +
            ", characterFaction='" + getCharacterFaction() + "'" +
            ", experience=" + getExperience() +
            ", characterLevel=" + getCharacterLevel() +
            ", lastLocation='" + getLastLocation() + "'" +
            ", lastLocationCoordinate='" + getLastLocationCoordinate() + "'" +
            ", lastLogin='" + getLastLogin() + "'" +
            "}";
    }
}
