package ru.chernov.urlshortener.entity.user

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length
import org.springframework.security.core.GrantedAuthority
import ru.chernov.urlshortener.entity.id.UserRoleId


@Table(name = "user_role")
@Entity
@IdClass(value = UserRoleId::class)
class UserRole : GrantedAuthority {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null

    @Id
    @NotNull
    @Length(max = 50)
    var role: String? = null


    override fun getAuthority(): String? {
        return role
    }

}