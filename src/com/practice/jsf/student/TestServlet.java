/**********************************************************************/
/*                                                                    */
/*						Student Tracker								  */
/*																	  */
/*		 My intention with those codes was to create a web app with	  */
/* 		 JSF. The codes here presented was written watching Chad	  */ 
/* 		 Darby's course at Udemy. For more information please visit	  */
/* 		 https://www.udemy.com/jsf-tutorial/     					  */
/*																	  */
/*                                                                    */
/**********************************************************************/

package com.practice.jsf.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

	//DataSource: Used to organize connection pooling inside Tomcat
	@Resource(name = "jdbc/student_tracker")
	private DataSource dataSource;

	protected void doGet(HttpServletRequest request,
				   HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try
		{
			myConn = dataSource.getConnection();

			String sql = "select * from student;";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			while (myRs.next())
			{
				String email = myRs.getString("email");
				out.println(email);
				System.out.println(email);
			}
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
			out.println(exc.getMessage());
		}
	}
}
