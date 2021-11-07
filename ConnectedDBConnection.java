
/***********************************************************************************************************************
*                                                                                                                      *
*   created by: Morgan Pothoff                                                                                         *
*   on 2021.11.02                                                                                                      *
*                                                                                                                      *
*   DESCRIPTION: This file provides simplified interfacing with the SQL module. It mainly does so by creating a        *
*    connected connection object with uniform class methods that take the query string for the following three query   *
*    types:                                                                                                            *
*     1. SELECT                                                                                                        *
*     2. INSERT                                                                                                        *
*     3. UPDATE                                                                                                        *
*    Notably, the connection does not automatically close itself. This should be done by the user (but hasn't been in  *
*    in this case :) )                                                                                                 *
*                                                                                                                      *
***********************************************************************************************************************/


import java.sql.*;


public class ConnectedDBConnection {
	private Connection _connection;
	private String _domain = "localhost";
	private int _port = 3306;
	private String _database = "";
	private String _user = "";
	private String _password = "";


	ConnectedDBConnection() throws Exception {
		String connection_url =	String.format("jdbc:mysql://%s:%d/%s", _domain, _port, _database);
		// Solution for:
		//   > java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost:3306/SEProjectTest
		//  found at:
		//   https://javarevisited.blogspot.com/2016/09/javasqlsqlexception-no-suitable-driver-mysql-jdbc-localhost.html
		//  .jar from https://dev.mysql.com/downloads/file/?id=507327
		_connection = DriverManager.getConnection(connection_url, _user, _password);
	}


	public Connection connection() {
		return _connection;
	}


	ResultSet insert(String query) throws Exception {
		Statement statement = _connection.createStatement();
		statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		return statement.getGeneratedKeys();
	}


	ResultSet select(String query) throws Exception {
		Statement statement = _connection.createStatement();
		return statement.executeQuery(query);
	}


	int update(String query) throws Exception {
		Statement statement = _connection.createStatement();
		return statement.executeUpdate(query);
	}
}

