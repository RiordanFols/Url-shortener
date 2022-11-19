package ru.chernov.urlshortener.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import ru.chernov.urlshortener.entity.id.UserRoleId;


@Table(name = "user_role")
@Entity
@IdClass(value = UserRoleId.class)
public class UserRole implements GrantedAuthority {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @NotNull
    @Length(max = 50)
    private String role;


    @Override
    public String getAuthority() {
        return role;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }

}
