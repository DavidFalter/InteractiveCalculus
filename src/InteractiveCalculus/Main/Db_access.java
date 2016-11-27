package InteractiveCalculus.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Db_access {
	

	public Connection getConnection(){
		
		
		String username = "root";
		String password = "1234";
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
	
	public void closeConnection(Connection connection){
	try {
		connection.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	
	public void create(Forum_table application){
	
		String sql = "insert into applications (name, price) values (? , ?) ";
		
		Connection connection = getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, application.getName());
			statement.setString(2, application.getPrice());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		closeConnection(connection);
		}
		
	}
	
	public void create2(Forum_post application, int oid,String oname){
		
		String sql = "insert into forum_post (post_author, post_body, op_id, thread_name) values (? , ?, ?, ?) ";
		
		
		Connection connection = getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, application.getPost_author());
			statement.setString(2, application.getPost_body());
			statement.setInt(3, oid);
			statement.setString(4, oname);
			
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		closeConnection(connection);
		}
		
	}
	
	
	public List<Forum_table> selectAll(){
	List<Forum_table> applications = new ArrayList<Forum_table>();
	
	String sql = "select * from applications";
	
	Connection connection = getConnection();
	
	try {
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet results = statement.executeQuery();
		
		while(results.next()){
			int id = results.getInt("id");
			String name = results.getString("name");
			String price = results.getString("price");
			Forum_table application = new Forum_table(id,name,price);
			applications.add(application);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		closeConnection(connection);
	}
	

	return applications;
	}
	
	
	public List<Forum_post> selectAll2(){
	List<Forum_post> app2 = new ArrayList<Forum_post>();
	
	String sql = "select * from forum_post";
	
	Connection connection = getConnection();
	
	try {
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet results = statement.executeQuery();
		
		while(results.next()){
			int id = results.getInt("post_id");
			String name = results.getString("post_author");
			String body = results.getString("post_body");
			int oid = results.getInt("op_id");
			String oname = results.getString("thread_name");
			Forum_post application = new Forum_post(id,name, body, oid, oname);
			app2.add(application);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		closeConnection(connection);
	}
	

	return app2;
	}
	
	
	public void remove(int id){
		String sql = "delete from applications where id=?";
		
		Connection connection = getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(connection);	
		}
		
	}
	
	
	public void removee(int id){
		String sql = "delete from forum_post where post_id=?";
		
		Connection connection = getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(connection);	
		}
		
	}

	
	public Forum_table selectOne(int id){
		Forum_table app = null;
		
		String sql = "select * from applications where id=?";
		
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			if (results.next()){
				id = results.getInt("id");
				String name = results.getString("name");
				String price = results.getString("price");
				app = new Forum_table(id, name, price);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection(connection);	
		}
		
		return app;
	}
	
	
	
	public Forum_table selectOnename(int id){
		Forum_table app = null;
		
		String sql = "select * from applications where id=?";
		
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			if (results.next()){
				String name = results.getString("name");
				app = new Forum_table(name);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection(connection);	
		}
		
		return app;
	}
	
	
	
	public static void main(String[] args) {
		Db_access dao = new Db_access();

		Forum_post app1 = new Forum_post("Flo", "yd");
		dao.create2(app1, 0, "");
	}


}
