create sequence hibernate_sequence start with 1 increment by 1;
create table passphrase
(
    passphrase_id bigint      not null,
    created       timestamp,
    name          varchar(20) not null,
    updated       timestamp,
    primary key (passphrase_id)
);
create table word
(
    word_id             bigint       not null,
    order_in_passphrase integer,
    content             varchar(255) not null,
    passphrase_id       bigint       not null,
    primary key (word_id)
);
alter table passphrase
    add constraint UK_m2w7ykq2sy0m9utotmn7ndkpv unique (name);
alter table word
    add constraint FKpsougjtl2ab3j1new78wig2rv foreign key (passphrase_id) references passphrase
