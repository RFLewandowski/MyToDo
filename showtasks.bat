call runcrud
if "%ERRORLEVEL%" == "0" goto runchrome
echo.
echo Cannot run chrome browser - breaking work
goto fail

:runchrome
start "" "http://localhost:8080/crud/v1/tasks"
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.