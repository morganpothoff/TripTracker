
########################################################################################################################
#                                                                                                                      #
#   created by: Morgan Pothoff                                                                                         #
#   on 2021.11.02                                                                                                      #
#                                                                                                                      #
#   DESCRIPTION: This makefile provides an easy way to compile and run the Java code. Because of the required jar file #
#    for DB accessing (since it seems to be the most common SQL Java interface), the classpath must be updated for     #
#    both compilation and running. This is handled by this file to prevent unnecessary retyping of the commands        #
#    through CLI and possibly forgetting them.                                                                         #
#                                                                                                                      #
########################################################################################################################


# Compiles everything adding mysql-connector-java.jar to the class path. 
all:
	javac -cp '.:mysql-connector-java.jar' *.java
	# For windows use
	#  javac -cp '.;mysql-connector-java.jar' *.java


# Runs everything adding mysql-connector-java.jar to the class path.
run:
	java -cp '.:mysql-connector-java.jar' GUIApplication
	# For windows use
	#  java -cp '.;mysql-connector-java.jar' GUIApplication

