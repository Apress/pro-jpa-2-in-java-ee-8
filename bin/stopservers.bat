call ../config/setenv

call %ANT_HOME%/bin/ant -f ../config/common.xml stop_server
call %ANT_HOME%/bin/ant -f ../config/common.xml stop_db

pause
