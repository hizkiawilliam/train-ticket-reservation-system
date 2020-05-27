package com.shashi.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.shashi.utility.DBConnection;

import java.sql.*;
@SuppressWarnings("serial")
public class FareEnq extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck[] = req.getCookies();
		if(ck!=null) {
			String uName = ck[0].getValue();
			String pWord = ck[1].getValue();
			if(!uName.equals("")||uName!=null) {
				try {
					Connection con = DBConnection.getCon();
					PreparedStatement ps = con.prepareStatement("Select * from train6 where from_stn=? and to_stn=?");
					ps.setString(1,req.getParameter("fromstation"));
					ps.setString(2, req.getParameter("tostation"));
					ResultSet rs = ps.executeQuery();
					if(rs.next()) 
					{
						RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
						rd.include(req, res);
						pw.println("<div class='tab'>" + 
								"		<p1 class='menu'>" + 
								"	Hello "+uName+" ! Selamat Datang di Web kami!" + 
								"		</p1>" + 
								"	</div>");
						pw.println("<div class='main'><p1 class='menu'>Harga tiket kereta dari "+req.getParameter("fromstation")+" dan "+req.getParameter("tostation")+" adalah <p2 class=\"red\">IDR "+rs.getLong("fare")+"</p2></p1></div>");
						pw.println("<div class='tab'><table><tr><th>Nama Kereta</th><th>Nomor Kereta</th>"
								+ "<th>Dari Stn</th><th>Ke Stn</th><th>Kursi</th><th>Harga (IDR)</th></tr>");
						do {
								pw.println(""
								+ "<tr><td>"+rs.getString("tr_name")+"</td>"
								+ "<td>"+rs.getLong("tr_no")+"</td>"
								+ "<td>"+rs.getString("from_Stn")+"</td>"
								+ "<td>"+rs.getString("to_Stn")+"</td>"
								+ "<td>"+rs.getLong("available")+"</td>"
								+ "<td>"+rs.getLong("fare")+" IDR</td></tr>");
					}while(rs.next());
						pw.println("</table></div>");
						}
					else {
						RequestDispatcher rd = req.getRequestDispatcher("TrainBwStn.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>Tidak ada kereta antara "+req.getParameter("fromstation")+" dan "+req.getParameter("tostation")+"</p1></div>");					}
				}
				catch(Exception e) {}
				
			}
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Harap Login Terlebih Dahulu!</p1></div>");
		}
	}
}
