#!/bin/bash
rm -rf target
mkdir target
javac src/java/edu/school21/printer/*/*.java -d ./target
java -cp ./target edu/school21/printer/app/Program . 0 /Users/hvayon/Desktop/Java_Bootcamp._Day04-0/src/ex00/ImagesToChar/src/java/edu/school21/printer/it.bmp