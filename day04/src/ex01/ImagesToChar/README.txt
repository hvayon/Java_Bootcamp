rm -rf target
mkdir target
cp -r src/resources target/resources
javac src/java/edu/school21/printer/*/*.java -d ./target
jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target edu -C src resources
chmod u+x target/images-to-chars-printer.jar
java -jar target/images-to-chars-printer.jar . 0