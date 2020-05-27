package com.shashi.servlets;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.shashi.utility.DBConnection;
@SuppressWarnings("serial")
public class UserViewTrainFwd extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck[] = req.getCookies();
		if(ck!=null) {
			String uName = ck[0].getValue();
			if(!uName.equals("")||uName!=null) {
				try {
					Connection con = DBConnection.getCon();
					PreparedStatement ps = con.prepareStatement("Select * from train6");
					ResultSet rs = ps.executeQuery();
					if(rs.next()) 
					{
						RequestDispatcher rd = req.getRequestDispatcher("UserViewTrains.html");
						rd.include(req, res);
						pw.println("<div class='tab'>" + 
								"		<p1 class='menu'>" + 
								"	Hello "+uName+" ! Selamat Datang di Web kami!" + 
								"		</p1>" + 
								"	</div>");
						pw.println("<div class='main'><p1 class='menu'>Kereta yang tersedia</p1></div>");
						pw.println("<div class='tab'><table><tr><th>Nama Kereta</th><th>Nomor Kereta</th>"
								+ "<th>Dari Stasiun</th><th>Ke Stasiun</th><th>Kursi Tersedia</th><th>Harga (IDR)</th><th>Pesan</th></tr>");
						long trainNo;
						String fromStn;
						String toStn;
						do {
							 trainNo = rs.getLong("tr_no");
							 fromStn = rs.getString("from_stn");
							 toStn = rs.getString("to_stn");
								pw.println(""
								+ "<tr> "
								+ ""
								+ "<td><a href='view?trainNo="+trainNo+"&fromStn="+fromStn+"&toStn="+toStn+"'>"+rs.getString("tr_name")+"</a></td>"
								+ "<td>"+trainNo+"</td>"
								+ "<td>"+fromStn+"</td>"
								+ "<td>"+toStn+"</td>"
								+ "<td>"+rs.getLong("available")+"</td>"
								+ "<td>"+rs.getLong("fare")+" RS</td>"
								+ "<td><a href='booktrainbyref?trainNo="+trainNo+"&fromStn="+fromStn+"&toStn="+toStn+"'><div class='red'>Pesan Sekarang</div></a></td></tr>"
							);
						}while(rs.next());
						pw.println("</table></div>");
					}
					else {
						RequestDispatcher rd = req.getRequestDispatcher("UserViewTrains.html");
						rd.include(req, res);
						pw.println("<div class='main'><p1 class='menu red'> Tidak ada Kereta</p1></div>");
					}
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
