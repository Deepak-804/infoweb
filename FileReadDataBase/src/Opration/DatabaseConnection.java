package Opration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;
import com.mysql.jdbc.DatabaseMetaData;
import Employee.Employee;
public class DatabaseConnection {
	public void commit(Vector<Employee> emp) throws Exception {
		Properties pro=new Properties();
		pro.load(new FileInputStream("driver.properties"));
		String url=pro.getProperty("url");
		String user=pro.getProperty("username");
		String password=pro.getProperty("password");
		String driver=pro.getProperty("driver");
		String table="deepu";
		Class.forName(driver);
		Connection conn = null;
		conn = DriverManager.getConnection(url,user,password);
		//method for create table---
		DatabaseMetaData dbm = (DatabaseMetaData) conn.getMetaData();
		ResultSet rs = dbm.getTables(null, null, table, null);
		if (rs.next()) {
			System.out.println("Table is alread created");
		} else {
			CreatTable(conn,table);
		}	
		if(emp.size()>0){
			String pre_query="insert into "+table+" values (?,?,?)";
			boolean flag=false;
			for(Employee a:emp) {
				if(a==null) {
					continue;
				}else {
					int id=a.getEmpid();
					String name=a.getName();
					String address=a.getAddress();
					PreparedStatement ps=conn.prepareStatement(pre_query);
					ps.setInt(1,id);
					ps.setString(2,name);
					ps.setString(3,address);
					ps.execute();
					flag=true;
				}

			}
			if(flag) {
				System.out.println("data insert sucessfully");
			}else {
				System.out.println("not insert data in database ");
			}
		}
		conn.close();
	}
	private void CreatTable(Connection conn, String table) throws Exception {
		String query="create table "+table+"(id int,name varchar(50),address varchar(50))";
		Statement smt =conn.createStatement();
		smt.executeUpdate(query);
		System.out.println("table create successfully ");

	}
	public static boolean tableExist(Connection conn, String tableName) throws Exception {
		boolean tExists = false;
		DatabaseMetaData dbm = (DatabaseMetaData) conn.getMetaData();
		ResultSet rs = dbm.getTables(null, null, "employee", null);
		if (rs.next()) {
			tExists=true;
		} else {
			tExists=false;
		}
		return tExists;
	}
}
