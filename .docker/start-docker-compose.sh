#!/bin/bash

# This script sets the HOME variable and runs Docker Compose, because automation is king.
# Detect the OS and ensure compatibility, because one size does not fit all.

# Detect the OS and set the HOME variable accordingly, because paths matter.
if [[ "$OSTYPE" == "linux-gnu"* ]]; then
  HOME="/home/$USER"
elif [[ "$OSTYPE" == "darwin"* ]]; then
  HOME="/Users/$USER"
else
  echo "Unsupported OS detected. Exiting." # Because unsupported OS is a no-go.
  exit 1
fi

# Export the HOME variable, because environment variables are everything.
export HOME

# Run Docker Compose with the specified environment file and compose file, because manual work is for the birds.
docker-compose --env-file .env -f docker-compose.yaml up --build
