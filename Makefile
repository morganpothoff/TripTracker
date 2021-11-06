

all:
	javac -cp '.:mysql-connector-java.jar' *.java


test:
	java -cp '.:mysql-connector-java.jar' Test



run:
	java -cp '.:mysql-connector-java.jar' GUIApplication
