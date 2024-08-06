#!/bin/bash

# This script sets the HOME variable and runs Docker Compose.
# Detect the OS and ensure compatibility.

# Detect the OS and set the HOME variable accordingly.
if [[ "$OSTYPE" == "linux-gnu"* ]]; then
  HOME="/home/$USER"
elif [[ "$OSTYPE" == "darwin"* ]]; then
  HOME="/Users/$USER"
else
  echo "Unsupported OS detected. Exiting."
  exit 1
fi

# Export the HOME variable.
export HOME

# Run Docker Compose with the specified environment file and compose file.
docker-compose --env-file .env -f docker-compose.yaml up --build
