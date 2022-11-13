package ru.chernov.urlshortener.entity.user

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Table(name = "usr")
@Entity
class User : UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_sequence")
    @SequenceGenerator(name = "usr_sequence", sequenceName = "usr_sequence", allocationSize = 1)
    var id: Long? = null

    @get:JvmName("getUsername(Jvm)")
    @NotNull
    @Length(max = 30)
    var username: String? = null

    @get:JvmName("getEntityPassword(Jvm)")
    @NotNull
    @Length(max = 100)
    var password: String? = null

    @NotNull
    var active: Boolean = false

    @NotNull
    @Length(max = 36)
    var token: String? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    var userRoles: MutableSet<UserRole>? = null


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