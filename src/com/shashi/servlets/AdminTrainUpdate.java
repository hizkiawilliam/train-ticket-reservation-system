package com.shashi.servlets;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.shashi.utility.DBConnection;
@SuppressWarnings("serial")
public class AdminTrainUpdate extends HttpServlet{
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
						long trNo = Long.parseLong(req.getParameter("trainnumber"));
						PreparedStatement ps = con.prepareStatement("select * from train6 where tr_no=?");
						ps.setLong(1, trNo);
						ResultSet rs = ps.executeQuery();
						if(rs.next()) {
						RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");
						rd.include(req, res);
						pw.println("<div class='tab'>Perbaharuan Jadwal Kereta</div>");
						pw.println("<div class='tab'>"
								+ "<table><form action='updatetrainschedule' method='post'>"
								+ "<tr><td>Nomor Kereta :</td><td><input type='text' name='trainno' value='"+rs.getLong("tr_no")+"'></td></tr>"
								+ "<tr><td>Nama Kereta :</td><td><input type='text' name='trainname' value='"+rs.getString("tr_name")+"'></td></tr>"
								+ "<tr><td>Dari Stasiun :</td><td><input type='text' name='fromstation' value='"+rs.getString("from_stn")+"'></td></tr>"
								+ "<tr><td>Ke Stasiun :</td><td><input type='text' name='tostation' value='"+rs.getString("to_stn")+"'></td></tr>"
								+ "<tr><td>Kursi Tersedia :</td><td><input type='text' name='available' value='"+rs.getLong("available")+"'></td></tr>"
								+ "<tr><td>Harga (IDR) :</td><td><input type='text' name='fare' value='"+rs.getLong("fare")+"'></td></tr>"
								+ "<tr><td></td><td><input type='submit' name='submit' value='Update Train Schedule'></td></tr>"
								+ "</form></table>"
								+ "</div>");
						}
						else {
							RequestDispatcher rd = req.getRequestDispatcher("AdminUpdateTrain.html");
							rd.include(req, res);
							pw.println("<div class='tab'>Kereta tidak tersedia!</div>");
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
