begin
    dbms_scheduler.create_job(job_name => 'EMAIL_UPDATE_JOB',
                              job_type => 'STORED_PROCEDURE',
                              job_action => 'BEGIN IF can_run_load > 0 THEN load_data; END IF; END;',
                              start_date => systimestamp,
                              end_date => null,
                              repeat_interval => 'Freq=MINUTELY;Interval=3',
                              enabled => true,
                              auto_drop => true,
                              comments => 'update_users_emails');
end;

BEGIN
    dbms_scheduler.drop_job(job_name => 'EMAIL_UPDATE_JOB');
END;
