create table device
(
    id                       varchar(255) default random_uuid() not null,
    consumed_power           integer                            not null,
    device_name              varchar(255),
    functionality_percentage integer                            not null,
    room                     varchar(255),
    state                    integer,
    primary key (id)
);
create table sensor
(
    id           varchar(255) default random_uuid() not null,
    room         varchar(255),
    sensor_state varchar(255),
    sensor_type  varchar(255),
    primary key (id)
);
create table sensor_connected_devices
(
    sensor_id            varchar(255) not null,
    connected_devices_id varchar(255) not null
);
alter table sensor_connected_devices
    add constraint UK_nl1fxvhtd6aw9w9v620uwdmcv unique (connected_devices_id);
alter table sensor_connected_devices
    add constraint FKbh0mny6lgvjqdo553y4hovq10 foreign key (connected_devices_id) references device;
alter table sensor_connected_devices
    add constraint FKsexxoib16ehw7n48hmb70e95v foreign key (sensor_id) references sensor;

create sequence hibernate_sequence start 1000 increment 1;
