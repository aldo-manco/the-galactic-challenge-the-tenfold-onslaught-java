#!/bin/bash
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Set Java executable (modify if needed)
JAVA_EXEC="java"

# Set classpath
CLASSPATH="$SCRIPT_DIR/out/production/SpaceInvaders"

# Run the game
"$JAVA_EXEC" -classpath "$CLASSPATH" org.aldomanco.display.Display
