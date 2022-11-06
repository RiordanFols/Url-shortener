package ru.chernov.urlshortener.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length
import org.springframework.security.core.GrantedAuthority
import ru.chernov.urlshortener.annotation.Lombok
import ru.chernov.urlshortener.entity.id.UserRoleId

@Table(name = "user_role")
@Entity
@Lombok
@IdClass(value = UserRoleId::class)
class UserRole : GrantedAuthority {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private var user: User? = null

    @Id
    @NotNull
    @Length(max = 50)
    private var role: String? = null

    override fun getAuthority(): String? {
        return role
    }
}