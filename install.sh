#!/usr/bin/env bash

install() {
    rm -rf $1
    git clone https://github.com/SecuritySquad/$1.git
    cd $1
    sh install.sh
    cd ..
}

cd ..

install webifier-resolver

install webifier-test-virusscan

install webifier-test-header-inspection

install webifier-test-portscan

docker rmi $(docker images --filter "dangling=true" -q)

cd webifier-tester
./gradlew :buildAll
cd ..
cd run
rm -f webifier-tester-all-*.jar
cp ../webifier-tester/build/libs/webifier-tester-all-*.jar .