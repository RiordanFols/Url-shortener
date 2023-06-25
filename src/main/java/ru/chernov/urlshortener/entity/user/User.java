package ru.chernov.urlshortener.entity.user;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.chernov.urlshortener.converter.user.UserStatusConverter;
import ru.chernov.urlshortener.enums.user.UserRole;
import ru.chernov.urlshortener.enums.user.UserStatus;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;


@Data
@Entity
@Table(name = "usr")
public class User implements UserDetails {

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

    @Convert(converter = UserStatusConverter.class)
    @NotNull
    private UserStatus status;

    @NotNull
    private LocalDateTime registeredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_level")
    @NotNull
    private UserLevel level;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(UserRole.BASIC);
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return status == UserStatus.ACTIVE;
    }


    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id.equals(user.id)
                && username.equals(user.username)
                && password.equals(user.password)
                && status == user.status
                && registeredAt.equals(user.registeredAt)
                && level.equals(user.level);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, status, registeredAt, level);
    }

}
