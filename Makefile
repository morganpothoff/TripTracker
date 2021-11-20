
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
	javac -cp 'Build:Source/mysql-connector-java.jar' Source/GUI/* Source/DBClasses/* Source/*.java -d Build
	# For windows use
	#  javac -cp 'Build;Source/mysql-connector-java.jar' Source/GUI/* Source/DBClasses/* Source/*.java -d Build


# Runs everything adding mysql-connector-java.jar to the class path.
run:
	java -cp 'Build:Source/mysql-connector-java.jar' TripTracker
	# For windows use
	#  java -cp 'Build;Source/mysql-connector-java.jar' TripTracker
