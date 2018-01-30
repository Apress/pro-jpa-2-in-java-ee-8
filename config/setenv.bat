@echo off
rem === If these are not already set in your environment then uncomment and set these
rem === two to the directory appropriate to your environment.
rem
rem JAVA_HOME=
rem ANT_HOME=
rem
set ANT_HOME=c:\apache-ant-1.10.1

if "%JAVA_HOME%"=="" echo "Need to set JAVA_HOME environment variable in <EXAMPLES_HOME>\config\setEnv.bat"
if "%ANT_HOME%"=="" echo "Need to set ANT_HOME environment variable in <EXAMPLES_HOME>\config\setEnv.bat"


