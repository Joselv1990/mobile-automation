#!/usr/bin/env bash
# Downloads the SauceLabs Swag Labs sample app into ./apps
set -euo pipefail

VERSION="2.7.1"
FILE="Android.SauceLabs.Mobile.Sample.app.${VERSION}.apk"
URL="https://github.com/saucelabs/sample-app-mobile/releases/download/${VERSION}/${FILE}"
DIR="$(cd "$(dirname "$0")/.." && pwd)/apps"
TARGET="${DIR}/${FILE}"

mkdir -p "$DIR"

if [[ -f "$TARGET" ]]; then
  echo "App already present: $TARGET"
else
  echo "Downloading ${FILE} ..."
  curl -L -o "$TARGET" "$URL"
  echo "Saved to $TARGET"
fi
