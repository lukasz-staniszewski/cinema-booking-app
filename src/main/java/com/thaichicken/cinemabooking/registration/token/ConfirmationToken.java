package com.thaichicken.cinemabooking.registration.token;

import com.thaichicken.cinemabooking.model.ClientEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "confirmation_token", schema = "public", catalog = "pis-db")
public class ConfirmationToken {

    @SequenceGenerator(
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence"
    )

    private int id;

    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
        nullable = false,
        name = "client_id"
    )
    private ClientEntity client;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt, ClientEntity client) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiredAt;
        this.client = client;
    }
}
