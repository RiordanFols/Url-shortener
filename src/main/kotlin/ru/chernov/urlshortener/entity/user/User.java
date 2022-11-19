package ru.chernov.urlshortener.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "usr")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_sequence")
    @SequenceGenerator(name = "usr_sequence", sequenceName = "usr_sequence", allocationSize = 1)
    private Long id;

    @NotNull
    @Length(max = 30)
    private String username;

    @NotNull
    @Length(max = 100)
    private String password;

    @NotNull
    private Boolean active;

    @NotNull
    private UUID token;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserRole> userRoles;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Boolean getActive() {
        return active;
    }


    public void setActive(Boolean active) {
        this.active = active;
    }


    public UUID getToken() {
        return token;
    }


    public void setToken(UUID token) {
        this.token = token;
    }


    public Set<UserRole> getUserRoles() {
        return userRoles;
    }


    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

}
