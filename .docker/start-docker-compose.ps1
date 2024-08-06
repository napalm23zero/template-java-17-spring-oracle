# This script sets the HOME variable and runs Docker Compose, because automation is life.

# Ensure the PowerShell version is supported, because we can't live in the past.
if ($PSVersionTable.PSVersion.Major -ge 6 -or $PSVersionTable.PSEdition -eq 'Desktop') {
    $os = [System.Environment]::OSVersion.Platform
    if ($os -eq 'Win32NT') {
        # Set HOME variable for Windows, because paths matter.
        $env:HOME = "$env:USERPROFILE"
    } else {
        Write-Host "Unsupported OS detected. Exiting." # Because unsupported OS is a dealbreaker.
        exit 1
    }
} else {
    Write-Host "Unsupported PowerShell version. Please upgrade to PowerShell Core or newer." # Because old versions are so last year.
    exit 1
}

# Run Docker Compose with the specified environment file and compose file, because why do it manually?
docker-compose --env-file .env -f docker-compose.yaml up --build
