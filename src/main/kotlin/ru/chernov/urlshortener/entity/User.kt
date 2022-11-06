package ru.chernov.urlshortener.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.chernov.urlshortener.annotation.Lombok

@Table(name = "usr")
@Entity
@Lombok
class User : UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "usr_sequence", sequenceName = "usr_sequence", allocationSize = 1)
    private var id: Long? = null

    @NotNull
    @Length(max = 30)
    private var username: String? = null

    @NotNull
    @Length(max = 100)
    private var password: String? = null

    @NotNull
    private var active: Boolean = true

    @NotNull
    @Length(max = 36)
    private var token: String? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private var userRoles: MutableSet<UserRole>? = null


    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return userRoles
    }


    override fun getPassword(): String? {
        return password
    }


    override fun getUsername(): String? {
        return username
    }


    override fun isAccountNonExpired(): Boolean {
        return true
    }


    override fun isAccountNonLocked(): Boolean {
        return true
    }


    override fun isCredentialsNonExpired(): Boolean {
        return true
    }


    override fun isEnabled(): Boolean {
        return active
    }

}