@echo off
echo Starting Docker Compose services...
docker-compose up -d
if %errorlevel% neq 0 (
    echo Failed to start Docker Compose services.
    exit /b %errorlevel%
)
echo Docker Compose services started successfully.
pause