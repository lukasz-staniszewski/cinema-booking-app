package com.thaichicken.cinemabooking.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "client", schema = "public", catalog = "pis-db")
public class ClientEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientGenerator")
    @SequenceGenerator(name = "clientGenerator", sequenceName = "client_id_seq", allocationSize = 1)
    @Column(name = "client_id", nullable = false)
    private int clientId;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "surname", nullable = false, length = 30)
    private String surname;

    @Column(name = "email", nullable = false, length = 60)
    private String email;

    @Column(name = "phone", nullable = false, length = 9)
    private String phone;

    @Column(name = "password", nullable = true)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "client_role", nullable = true, length = 9)
    private ClientRole clientRole;

    @Column(name = "locked", nullable = true)
    private Boolean locked = false;

    @Column(name = "enabled", nullable = true)
    private Boolean enabled = false;

    public ClientEntity(int clientId, String name, String surname, String email, String phone, String password, ClientRole clientRole) {
        this.clientId = clientId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.clientRole = clientRole;
    }

    public ClientEntity(String name, String surname, String email, String phone, String password, ClientRole clientRole) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.clientRole = clientRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(clientRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {return enabled;}
}
