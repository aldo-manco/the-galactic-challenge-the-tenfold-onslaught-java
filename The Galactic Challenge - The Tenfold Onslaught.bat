@echo off
setlocal

:: Determine the script's directory
set "SCRIPT_DIR=%~dp0"

:: Set Java executable (modify if necessary)
set "JAVA_EXEC=java"

:: Set classpath to the 'out/production/SpaceInvaders' folder relative to the script
set "CLASSPATH=%SCRIPT_DIR%out\production\SpaceInvaders"

:: Run the game
"%JAVA_EXEC%" -classpath "%CLASSPATH%" org.aldomanco.display.Display

endlocal
