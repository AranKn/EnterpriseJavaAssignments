package com.javastack.JDBCCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.javastack.JDBCStatement.JDBCStatement;

public class JdbcCrud 
{
	private static void createTable(String name,Connection connection,String [] attributes) throws SQLException
	{
		Statement statement=connection.createStatement();
		statement.execute("CREATE table "+name+"("+attributes[0]+" int,"+attributes[1]+" VARCHAR(25),"+attributes[2]+" VARCHAR(30))");	
	}
	
	private static void insertData(Connection connection,Scanner scanner,String tableName) throws SQLException
	{
		System.out.print("Enter the number of records to be created : ");
		int id=0;
		String query="INSERT into "+tableName+" values",
			   name=null,
			   email=null;
		int n=scanner.nextInt();
		for(int t=0;t<n;t++)
		{
			System.out.print("Enter Student Id    : ");
			id=scanner.nextInt();
			System.out.print("Enter Student Name  : ");
			name=scanner.next();
			System.out.print("Enter Student Email : ");
			email=scanner.next();
			query+= (t==n-1) ?"("+id+",'"+name+"','"+email+"')":"("+id+",'"+name+"','"+email+"'),";			
		} 
		Statement statement=connection.createStatement();
		int count=statement.executeUpdate(query);
		System.out.println(count==n?"All the given records are inserted into"+tableName+"table successfully" : "Please check your query");
	}
	
	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Scanner scanner=new Scanner(System.in);
		
		//Q1.1 Creating a table and Inserting few records into the table
		connection=JDBCStatement.getConnection();
		System.out.println("Enter the name of the table to be Created : ");
		String tableName=scanner.nextLine();
//		createTable(tableName,connection,new String []{"Student_Id","Student_Name","Student_Email"});
//		insertData(connection,scanner,tableName);
		
		//Q1.2 Read and display all the records in from the table
//		read(connection,tableName);
		
		//Q1.3 Read and display all the records in from the table
		updateRecord(connection,"",8);
	}

	private static void updateRecord(Connection connection,String tableName,int y) throws SQLException {
		Statement statement=connection.createStatement();	
		System.out.println("Before Updation");
		read(connection,tableName);
	}

	private static void read(Connection connection,String tableName) throws SQLException {
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery("SELECT * from "+tableName);
		System.out.println("Student_ID\tStudentName\tStudentEmail");
		while(resultSet.next())
		{
			System.out.print(resultSet.getInt(1)+"\t\t");
			System.out.print(resultSet.getString(2)+"\t\t");
			System.out.print(resultSet.getString(3)+"\t\t");
			System.out.println();
		}
	}
}