#!/usr/bin/env bash

ARGS=$#
ARG=$1
ONLY=$2

if [[ "$ARG" == "--help" ]]; then
    echo "Usage: bash install.sh [--only \"test1 test2\"]"
    exit
fi

install() {
    if [[ ${ARGS} -eq 0 || "$ARG" == "--only" && "$ONLY" =~ "$1" ]]; then
        rm -rf $1
        git clone https://github.com/SecuritySquad/$1.git
        cd $1
        bash install.sh
        cd ..
    fi
}

cd ..

install webifier-resolver

install webifier-test-virusscan

install webifier-test-header-inspection

install webifier-test-portscan

install webifier-test-ipscan

install webifier-test-linkchecker

install webifier-test-certificatechecker

install webifier-test-phishingdetector

docker rmi $(docker images --filter "dangling=true" -q)

cd webifier-tester
./gradlew :buildAll
cd ..
cd run
rm -f webifier-tester-all-*.jar
cp ../webifier-tester/build/libs/webifier-tester-all-*.jar .