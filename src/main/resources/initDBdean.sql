drop table winchester;

create table winchester
(
    snap_date    date,
    phone_number varchar2(50) not null,
    email        varchar2(50) not null,
    action       varchar2(50) not null
)
    partition by range (snap_date) interval (numtodsinterval(1, 'day'))
(
    partition p_data values less than (to_date('2020-02-23 00:00:00', 'yyyy-mm-dd hh24:mi:ss'))
);

delete
from winchester;

insert into winchester (snap_date, phone_number, email, action)
select to_date('2020-02-05 21:05:40', 'yyyy-mm-dd hh24:mi:ss'), '79306661004', 'habib@gmail.com', 'create'
from dual
union all
select to_date('2020-02-05 21:05:40', 'yyyy-mm-dd hh24:mi:ss'), '79306661005', 'konor@gmail.com', 'create'
from dual
union all
select to_date('2020-02-05 21:05:40', 'yyyy-mm-dd hh24:mi:ss'), '79306661006', 'kobe@gmail.com', 'create'
from dual
union all
select to_date('2020-02-06 21:05:40', 'yyyy-mm-dd hh24:mi:ss'), '79306661006', 'kobe_bryant@gmail.com', 'delete'
from dual
union all
select to_date('2020-02-06 21:05:40', 'yyyy-mm-dd hh24:mi:ss'), '79306661007', 'john@gmail.com', 'delete'
from dual
union all
select to_date('2020-02-07 21:05:40', 'yyyy-mm-dd hh24:mi:ss'), '79306661007', 'john_wich@gmail.com', 'delete'
from dual;

drop table CTL_BATCHES_3;

create table CTL_BATCHES_3
(
    batch_id     NUMBER                        not null,
    batch_status NUMBER        default 0       not null,
    start_date   DATE                          not null,
    end_date     DATE                          not null,
    navi_date    DATE          default SYSDATE not null,
    navi_user    VARCHAR2(120) default USER    not null,
    cnt_row      VARCHAR2(80)
);

insert into CTL_BATCHES_3 (batch_id, batch_status, start_date, end_date, navi_date, navi_user, cnt_row)
select 1,
       1,
       to_date('2020-02-05 21:05:40', 'yyyy-mm-dd hh24:mi:ss'),
       to_date('2020-02-05 21:05:40', 'yyyy-mm-dd hh24:mi:ss'),
       sysdate,
       user,
       '6'
from dual;
