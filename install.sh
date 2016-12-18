#!/usr/bin/env bash

install() {
    rm -rf $1
    git clone https://github.com/SecuritySquad/$1.git
    cd $1
    sh install.sh
    cd ..
}

cd ..

docker rmi $(docker images -q)

rm -rf run

install webifier-resolver

install webifier-test-virusscan

install webifier-test-header-inspection

install webifier-test-portscan

cd webifier-tester
./gradlew :buildAll
cd ..
mkdir -p run
cd run
cp ../webifier-tester/build/libs/webifier-tester-all-*.jar .