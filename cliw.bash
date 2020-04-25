#!/usr/bin/env bash

cli=./build/cli/bin/cli
[ -x "$cli" ] || ./gradlew :cli:installDist || exit 1
./build/cli/bin/cli $@

#gradlew :cli:run --args="--verbose info"
