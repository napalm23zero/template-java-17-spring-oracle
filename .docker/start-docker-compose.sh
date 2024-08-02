#!/bin/bash

# Welcome, developer. This script will detect your OS and set the HOME variable accordingly.

# Detect your OS
if [[ "$OSTYPE" == "linux-gnu"* ]]; then # Linux so you're a penguin.
  HOME="/home/$USER"
elif [[ "$OSTYPE" == "darwin"* ]]; then # MacOS, so you're rich.
  HOME="/Users/$USER"
else
  echo "Unsupported OS. Seriously, what are you even using?"
  exit 1
fi

# Export the HOME variable.
export HOME

# Run Docker Compose with our environment file and compose file. No excuses now.
docker-compose --env-file .env -f docker-compose.yaml up template-java-17-spring-build
