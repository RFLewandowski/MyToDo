call runcrud
if "%ERRORLEVEL%" == "0" goto runchrome
echo.
echo Cannot run chrome browser - breaking work
goto fail

:runchrome
@rem find commands to run chrome
@rem and open http://localhost:8080/crud/v1/tasks
if "%ERRORLEVEL%" == "0" goto stoptomcat
echo Cannot rename file
goto end


:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.