# This script sets the HOME variable and runs Docker Compose.

# Ensure the PowerShell version is supported.
if ($PSVersionTable.PSVersion.Major -ge 6 -or $PSVersionTable.PSEdition -eq 'Desktop') {
    $os = [System.Environment]::OSVersion.Platform
    if ($os -eq 'Win32NT') {
        # Set HOME variable for Windows.
        $env:HOME = "$env:USERPROFILE"
    } else {
        Write-Host "Unsupported OS detected. Exiting."
        exit 1
    }
} else {
    Write-Host "Unsupported PowerShell version. Please upgrade to PowerShell Core or newer."
    exit 1
}

# Run Docker Compose with the specified environment file and compose file.
docker-compose --env-file .env -f docker-compose.yaml up --build
