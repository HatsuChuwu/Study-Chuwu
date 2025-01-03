chcp 65001 >nul
@echo off
set /p minutes=请输入关机时间（分钟）: 
if %minutes% LEQ 0 (
    echo 输入的时间必须大于0。
    pause
    exit /b
)
set /a seconds=%minutes% * 60
echo 计算机将在 %minutes% 分钟后关机。
shutdown -s -t %seconds%
pause