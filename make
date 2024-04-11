#!/bin/bash
#there a better way? seems simple enough
shopt -s globstar
rm -rf menu.jar build
[[ $1 = clean ]] && exit
javac -Xlint:-options --release 8 -d build src/**/*.java
jar --create --file menu.jar --manifest src/META-INF/MANIFEST.MF -C build/ .
