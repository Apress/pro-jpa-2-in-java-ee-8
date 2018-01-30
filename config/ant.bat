@echo off
rem === Called from each example 3 levels down ===

call ../../../config/setenv.bat

call %ANT_HOME%/bin/ant %1

