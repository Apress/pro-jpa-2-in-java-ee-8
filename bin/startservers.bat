call ../config/setenv

call %ANT_HOME%/bin/ant -f ../config/common.xml start_db
call %ANT_HOME%/bin/ant -f ../config/common.xml start_server


pause
