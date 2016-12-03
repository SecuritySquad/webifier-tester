#!/usr/bin/env bash

install() {
    git clone https://github.com/SecuritySquad/$1.git
    cd $1
    sh install.sh
    cd ..
}

cd ..

docker rmi $(docker images -q)

install webifier-resolver

install webifier-test-virusscan

cd webifier-tester
./gradlew :buildAll
cd ..
mkdir -p run
cd run
cp ../webifier-tester/build/libs/webifier-tester-all-*.jar .