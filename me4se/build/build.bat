cd ..
cd src
javac org/me4se/*.java org/me4se/impl/*.java java/util/*.java javax/microedition/io/*.java javax/microedition/midlet/*.java javax/microedition/lcdui/*.java javax/microedition/rms/*.java

jar cfM ../html/me4se.zip org/me4se/*.class  java/util/*.class org/me4se/impl/*.class javax/microedition/io/*.class javax/microedition/midlet/*.class javax/microedition/lcdui/*.class javax/microedition/rms/*.class
jar cfM ../html/me4se-source.zip org/me4se/*.java  java/util/*.java org/me4se/impl/*.java javax/microedition/io/*.java javax/microedition/midlet/*.java javax/microedition/lcdui/*.java javax/microedition/rms/*.java
