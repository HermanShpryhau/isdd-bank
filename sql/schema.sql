create table public.cities
(
    city_id   bigserial
        primary key,
    city_name varchar(250) not null
        unique
);

alter table public.cities
    owner to "bank-app";

create unique index cities_city_id_city_name_uindex
    on public.cities (city_id, city_name);

create table public.citizenships
(
    citizenship_id   bigserial
        primary key,
    citizenship_name varchar(250) not null
        unique
);

alter table public.citizenships
    owner to "bank-app";

create unique index citizenships_citizenship_id_citizenship_name_uindex
    on public.citizenships (citizenship_id, citizenship_name);

create table if not exists public.disabilities
(
    disability_id  bigserial
        primary key,
    disablity_name varchar(250) not null
        unique
);

alter table public.disabilities
    owner to "bank-app";

create unique index if not exists disabilities_disability_id_disablity_name_uindex
    on public.disabilities (disability_id, disablity_name);

create table public.marital_statuses
(
    marital_status_id   bigserial
        primary key,
    marital_status_name varchar(250) not null
        unique
);

alter table public.marital_statuses
    owner to "bank-app";

create unique index marital_statuses_marital_status_id_marital_status_name_uindex
    on public.marital_statuses (marital_status_id, marital_status_name);

create table public.addresses
(
    address_id   bigserial
        primary key
        unique,
    city_id      bigint       not null
        constraint addresses_cities_city_id_fk
            references public.cities,
    address_test varchar(250) not null
);

alter table public.addresses
    owner to "bank-app";

create unique index addresses_address_id_uindex
    on public.addresses (address_id);

create index addresses_city_id_index
    on public.addresses (city_id);

create table public.clients
(
    client_id             bigserial
        primary key
        unique,
    last_name             varchar(250)          not null,
    first_name            varchar(250)          not null,
    middle_name           varchar(250)          not null,
    date_of_birth         date                  not null,
    sex                   varchar(1)            not null,
    passport_series       varchar(2)            not null,
    passport_number       varchar(7)            not null,
    passport_id_number    varchar(14)           not null
        unique,
    passport_issued_by    varchar(250)          not null,
    passport_issue_date   date                  not null,
    place_of_birth        varchar(250)          not null,
    residential_address   bigint                not null
        unique
        constraint clients_addresses_address_id_fk
            references public.addresses
            on delete restrict,
    registration_address  bigint                not null
        unique
        constraint clients_addresses_address_id_fk_2
            references public.addresses,
    personal_phone_number varchar(15),
    home_phone_number     varchar(15),
    email                 varchar(250),
    place_of_work         varchar(250),
    position_at_work      varchar(250),
    citizenship_id        bigint                not null
        constraint clients_citizenships_citizenship_id_fk
            references public.citizenships
            on delete restrict,
    marital_status_id     bigint                not null
        constraint clients_marital_statuses_marital_status_id_fk
            references public.marital_statuses,
    disability_id         bigint                not null
        constraint clients_disabilities_disability_id_fk
            references public.disabilities
            on delete restrict,
    retired_flag          boolean default false not null,
    monthly_income        numeric
);

alter table public.clients
    owner to "bank-app";

create unique index clients_client_id_uindex
    on public.clients (client_id);

create index clients_last_name_first_name_middle_name_index
    on public.clients (last_name, first_name, middle_name);

create table public.account_type
(
    account_type_id   bigserial
        primary key,
    account_type_code varchar(4)   not null
        unique,
    account_type_name varchar(250) not null
        unique,
    active_flag       boolean      not null
);

alter table public.account_type
    owner to "bank-app";

create unique index account_type_account_type_id_account_type_code_account_type_nam
    on public.account_type (account_type_id, account_type_code, account_type_name);


create table public.accounts
(
    account_id      bigserial
        primary key,
    account_number  varchar(13)       not null
        unique,
    account_type_id bigint            not null
        constraint accounts_account_type_account_type_id_fk
            references public.account_type,
    account_owner   bigint
        constraint accounts_clients_client_id_fk
            references public.clients,
    account_balance numeric default 0 not null
);

alter table public.accounts
    owner to "bank-app";

create unique index accounts_account_id_account_number_uindex
    on public.accounts (account_id, account_number);

create index accounts_account_owner_index
    on public.accounts (account_owner);


create table public.transactions
(
    transaction_id         bigserial
        primary key
        unique,
    source_account_id      bigint    not null
        constraint transactions_accounts_account_id_fk
            references public.accounts
            on delete restrict,
    destination_account_id bigint    not null
        constraint transactions_accounts_account_id_fk_2
            references public.accounts
            on delete restrict,
    amount                 numeric   not null,
    transaction_timestamp  timestamp not null
);

alter table public.transactions
    owner to "bank-app";

create index transactions_destination_account_id_index
    on public.transactions (destination_account_id);

create index transactions_source_account_id_index
    on public.transactions (source_account_id);

create unique index transactions_transaction_id_uindex
    on public.transactions (transaction_id);
