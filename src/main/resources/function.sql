CREATE OR REPLACE FUNCTION can_run_load RETURN NUMBER IS
    f_can_run NUMBER;
BEGIN
    SELECT COUNT(1)
    INTO f_can_run
    FROM DEAN.CTL_BATCHES_3
    WHERE batch_status = 1;
    RETURN f_can_run;
END can_run_load;

select can_run_load
from dual;
