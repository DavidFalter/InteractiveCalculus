package InteractiveCalculus.Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	
	public Connection getConnection()
	{
		String connectionUrl ="jdbc:mysql://localhost:3306/interactivecalculusaid";
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try {
				connection = DriverManager.getConnection(connectionUrl, "root", null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InstantiationException e) { 	 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
		
	}
	public void create(User user)	
	{
		String sql = "insert into users (un, pw, email) values (?, ?, ?)";
		
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassWord());
			statement.setString(3, user.getEmail());
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			closeConnection(connection);
		}
	}
	
	public List<User> selectAll()
	{
		List<User> users = new ArrayList<User>();
		
		String sql = "select * from users";
	
		Connection connection =  getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				int id = results.getInt("id");
				String username = results.getString("un");
				String password = results.getString("pw");
				String privilege = results.getString("email");
				User user = new User(id, username, password, privilege);
				users.add(user);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{		
		closeConnection(connection);
		}
		return users;
	}

	public void closeConnection(Connection connection)
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		UserDAO dao = new UserDAO();
		List<User> users = dao.selectAll();
		System.out.println(users.size());


	}

}