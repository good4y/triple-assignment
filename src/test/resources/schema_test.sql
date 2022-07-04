drop table if exists user cascade;
drop table if exists point cascade;
drop table if exists review cascade;

create table user
(
    user_id     binary(16)         not null
        primary key,
    total_point smallint default 0 not null
);

create table review
(
    review_id          binary(16)   not null
        primary key,
    attached_photo_ids varchar(255) null,
    content            varchar(255) null,
    place_id           binary(16)   null,
    user_id            binary(16)   null
);

create table point
(
    point_id     binary(16)  not null
        primary key,
    action       varchar(10) null,
    created_time datetime(6) null,
    place_id     binary(16)  null,
    point_change tinyint(2)     null,
    review_id    binary(16)  null,
    userid       binary(16)  null,
    is_first     boolean    not null
);



