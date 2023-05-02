-- liquibase formatted sql

-- changeset pauline_roehr:20230426_0949_accounts_table.sql
create table platepal_accounts.account
(
    account_id    bigserial
        constraint account_pk
            primary key,
    password_hash text,
    email_address text,
    created_at    timestamp
);

