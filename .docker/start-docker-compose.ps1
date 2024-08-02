# This script will make Docker Compose behave by setting the HOME variable. 
# Because Docker can't figure out your OS and username by itself. Yet.

# Detect the OS because we can't trust Docker to do it.
if ($PSVersionTable.PSVersion.Major -ge 6 -or $PSVersionTable.PSEdition -eq 'Desktop') {
    $os = [System.Environment]::OSVersion.Platform
    if ($os -eq 'Win32NT') {
        # Windows, so you're normal. Welcome to the club.
        $env:HOME = "$env:USERPROFILE"
    } else {
        Write-Host "Unsupported OS. What are you, a time traveler?"
        exit 1
    }
} else {
    Write-Host "Unsupported PowerShell version. Upgrade to PowerShell Core or newer."
    exit 1
}

# Run Docker Compose with our environment file and compose file. Time to rock and roll.
docker-compose --env-file .env -f docker-compose.yaml up template-java-17-spring-build
