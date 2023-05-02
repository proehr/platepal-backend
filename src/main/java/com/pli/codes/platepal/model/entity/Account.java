package com.pli.codes.platepal.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "account", schema = "platepal_accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountId")
    private Long accountId;

    @Setter
    private String emailAddress;

    @Column(name = "password_hash")
    @Setter
    private String password;

    @CreationTimestamp
    private Timestamp createdAt;

    public Account(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

}
