package com.shashi.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.shashi.utility.DBConnection;

import java.sql.*;
@SuppressWarnings("serial")
public class AdminViewLinkFwd extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
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
					PreparedStatement ps = con.prepareStatement("Select * from train6 where tr_no=? and from_stn=? and to_stn=?");
					ps.setLong(1,Long.parseLong(req.getParameter("trainNo")));
					ps.setString(2, req.getParameter("fromStn"));
					ps.setString(3, req.getParameter("toStn"));
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");
						rd.include(req, res);
						pw.println("<div class='main'><p1 class='menu'>Detail Kereta</p1></div>");
						pw.println("<div class='tab'>"
								+ "<table>"
								+ "<tr><td class='blue'>Nama Kereta :</td><td>"+rs.getString("tr_name")+"</td></tr>"
								+ "<tr><td class='blue'>Nomor Kereta :</td><td>"+rs.getLong("tr_no")+"</td></tr>"
								+ "<tr><td class='blue'>Dari Stasiun :</td><td>"+rs.getString("from_Stn")+"</td></tr>"
								+ "<tr><td class='blue'>Ke Stasiun :</td><td>"+rs.getString("to_Stn")+"</td></tr>"
								+ "<tr><td class='blue'>Kursi Tersedia:</td><td>"+rs.getLong("available")+"</td></tr>"
								+ "<tr><td class='blue'>Harga (IDR) :</td><td>"+rs.getLong("fare")+" RS</td></tr>"
								+ "</table>"
								+ "</div>");
					}
					else {
						RequestDispatcher rd = req.getRequestDispatcher("AdminSearchTrains.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>Kereta no."+req.getParameter("trainnumber")+" tidak tersedia!</p1></div>");
					}
				}
				catch(Exception e) {}
				
			}
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Harap Login Terlebih Dahulu!</p1></div>");
		}
	}

}
