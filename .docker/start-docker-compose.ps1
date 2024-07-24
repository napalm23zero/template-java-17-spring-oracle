# This script will make Docker Compose behave by setting the HOME variable. 
# Because Docker can't figure out your OS and username by itself. Yet.

# Detect the OS because we can't trust Docker to do it.
if ($IsWindows) { # Windows, so you're normal. Welcome to the club.
  $HOME = "$env:USERPROFILE"
} else {
  Write-Host "Unsupported OS. What are you, a time traveler?"
  exit 1
}

# Set the HOME variable. It's 2024, why are we still doing this manually?
$env:HOME = $HOME

# Run Docker Compose with our environment file and compose file. Time to rock and roll.
docker-compose --env-file .docker/.env -f .docker/docker-compose.yaml up template-java-17-spring-build
