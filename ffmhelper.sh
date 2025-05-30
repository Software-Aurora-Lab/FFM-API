#!/bin/bash

NATIVE_ACCESSER_PATH='in/derros/jni/UtilitiesFFM.java'
NATIVE_ACCESSER_CLASS_NAME='in.derros.jni.UtilitiesFFM.java'
PWD=`pwd`

echo
echo 'Java Native Interface Helper'
echo 'Created by RJ Fang'
echo 'Options: ./jnihelper.sh'
echo '--refresh-header     Refreshes the header'
echo '--build              Tries CMake build'
echo '--execute-java       Tests library'
echo

refresh_header () {

    cd java
    javah $NATIVE_ACCESSER_CLASS_NAME
    cd ../
    echo "Generation finished."
}

buildj () {

    rm -rf build
    mkdir build
    cd build
    cmake ../
    make
    ls
    cd ../
    echo 'Build finished.'
}

exej () {

    cd java/
    javac --enable-preview --release 23 -d out in/derros/jni/UtilitiesFFM.java
    java --enable-preview -cp out in.derros.jni.UtilitiesFFM
    cd ../

}


if [ "$1" != "" ]; then
    while [ "$1" != "" ]; do
        case $1 in
            --refresh-header        )   refresh_header
                                        ;;
            --build                 )   buildj
                                        ;;
            --execute-java | --exej )   exej
        esac
        shift
    done
else
    refresh_header
    buildj
    exej
fi
