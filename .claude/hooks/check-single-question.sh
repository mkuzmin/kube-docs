#!/bin/sh
# Block AskUserQuestion calls with more than 1 question.
count=$(jq '.tool_input.questions | length')
if [ "$count" -gt 1 ]; then
  echo "Ask only one question at a time."
  exit 2
fi
