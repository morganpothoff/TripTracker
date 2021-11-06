

all:
	javac -cp '.:mysql-connector-java.jar' *.java


run:
	java -cp '.:mysql-connector-java.jar' Test
