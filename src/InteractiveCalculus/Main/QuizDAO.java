package InteractiveCalculus.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizDAO {

	public Connection getConnection() 
	{
		String connectionUrl = "jdbc:mysql://localhost:3306/interactivecalculusaid";
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionUrl, "root", null);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public void closeConnection(Connection connection)
	{
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public QuizQuestion findQuestion(int id)
	{
		QuizQuestion theQuestion = null;
		
		String sql = "select * from questions where id=?";
		
		Connection connection = getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			if (results.next())
			{
				id = results.getInt("id");
				String question = results.getString("question");
				String a1 = results.getString("a1");
				String a2 = results.getString("a2");
				String a3 = results.getString("a3");
				String a4 = results.getString("a4");
				int correct = results.getInt("correct");
				int section = results.getInt("section");
				theQuestion = new QuizQuestion(id,question,a1,a2,a3,a4,correct,section);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		
		return theQuestion;
	}
	
	public static void main(String[] args) {
	}

}
