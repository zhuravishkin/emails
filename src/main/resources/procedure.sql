CREATE OR REPLACE PROCEDURE load_data AS
BEGIN
    MERGE
    INTO SAM.SUPERNATURAL spntrl
    USING (SELECT win.phone_number, win.email, win.ACTION
           FROM DEAN.WINCHESTER win,
                (SELECT MAX(snap_date) as maxdate, phone_number
                 FROM DEAN.WINCHESTER
                 group by phone_number) maxresults
           where win.phone_number = maxresults.phone_number
             and win.snap_date = maxresults.maxdate)
        whchstr
    ON
        (spntrl.PHONE_NUMBER = whchstr.PHONE_NUMBER)
    WHEN MATCHED THEN
        update
        set spntrl.EMAIL  = whchstr.EMAIL,
            spntrl.ACTION = whchstr.ACTION
    WHEN NOT MATCHED THEN
        INSERT (PHONE_NUMBER,
                EMAIL,
                ACTION)
        VALUES (whchstr.PHONE_NUMBER,
                whchstr.EMAIL,
                whchstr.ACTION);
    delete from SAM.SUPERNATURAL where action = 'delete';
    EXECUTE IMMEDIATE 'TRUNCATE TABLE DEAN.WINCHESTER';
    UPDATE DEAN.CTL_BATCHES_3
    SET batch_status = 3,
        navi_user    = USER,
        navi_date    = SYSDATE
    WHERE batch_status = 1;
    COMMIT;
END;

create or replace NONEDITIONABLE PROCEDURE PROCEDURE1 AS
BEGIN
    MERGE
    INTO SAM.SUPERNATURAL spntrl
    USING (SELECT phone_number, email, ACTION
           FROM (SELECT win.phone_number, win.email, win.ACTION, row_number()
                   over (partition by win.phone_number, win.snap_date order by win.phone_number) rn
                 FROM DEAN.WINCHESTER win,
                      (SELECT MAX(snap_date) as maxdate, phone_number
                       FROM DEAN.WINCHESTER
                       group by phone_number) maxresults
                 where win.phone_number = maxresults.phone_number
                   and win.snap_date = maxresults.maxdate)
           where rn = 1)
        whchstr
    ON
        (spntrl.PHONE_NUMBER = whchstr.PHONE_NUMBER)
    WHEN MATCHED THEN
        update
        set spntrl.EMAIL  = whchstr.EMAIL,
            spntrl.ACTION = whchstr.ACTION
    WHEN NOT MATCHED THEN
        INSERT (PHONE_NUMBER,
                EMAIL,
                ACTION)
        VALUES (whchstr.PHONE_NUMBER,
                whchstr.EMAIL,
                whchstr.ACTION);
    delete from SAM.SUPERNATURAL where action = 'delete';
    EXECUTE IMMEDIATE 'TRUNCATE TABLE DEAN.WINCHESTER';
    UPDATE DEAN.CTL_BATCHES_3
    SET batch_status = 3,
        navi_user    = USER,
        navi_date    = SYSDATE
    WHERE batch_status = 1;
    COMMIT;
END;
