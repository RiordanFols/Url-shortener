package ru.chernov.urlshortener.entity.id;

import lombok.Data;
import ru.chernov.urlshortener.entity.user.User;

import java.io.Serializable;
import java.util.Objects;


@Data
public class UserRoleId implements Serializable {
    private User user;
    private String role;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoleId that)) return false;
        return user.getId().equals(that.user.getId())
                && role.equals(that.role);
    }


    @Override
    public int hashCode() {
        return Objects.hash(user, role);
    }

}
