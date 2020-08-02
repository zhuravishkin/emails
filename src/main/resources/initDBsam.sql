drop table supernatural;
drop sequence supernatural_seq;

create table supernatural
(
    id           number(10)   not null,
    phone_number varchar2(50) not null,
    email        varchar2(50) not null,
    action       varchar2(50) not null
);

alter table supernatural
    add (constraint supernatural_pk primary key (id));

create sequence supernatural_seq start with 1;

CREATE OR REPLACE TRIGGER supernatural_trigger
    BEFORE INSERT
    ON supernatural
    FOR EACH ROW

BEGIN
    SELECT supernatural_seq.NEXTVAL
    INTO :new.id
    FROM dual;
END;

delete
from supernatural;

insert into supernatural (phone_number, email, action)
select '79306661000', 'dean@gmail.com', 'create'
from dual
union all
select '79306661001', 'sam@gmail.com', 'create'
from dual
union all
select '79306661002', 'castiel@gmail.com', 'create'
from dual
union all
select '79306661003', 'crowley@gmail.com', 'create'
from dual;
