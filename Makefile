
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


all:
	javac -cp '.:mysql-connector-java.jar' *.java


run:
	java -cp '.:mysql-connector-java.jar' GUIApplication


test:
	java -cp '.:mysql-connector-java.jar' Test
