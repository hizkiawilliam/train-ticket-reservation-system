package com.shashi.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
@SuppressWarnings("serial")
public class UserProfile extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck[] = req.getCookies();
		if(ck!=null) {
			String uName = ck[0].getValue();
			String pWord = ck[1].getValue();
			if(!uName.equals("")||uName!=null) {
				RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
				rd.include(req, res);
				pw.println("<div class='tab'>" + 
						"		<p1 class='menu'>" + 
						"	Hello "+uName+" ! Selamat Datang di Web kami!" + 
						"		</p1>" + 
						"	</div>");
				pw.println("<div class='main'><p1 class='menu'><a href='viewuserprofile'>Lihat Profile</a></p1>"
						+ "<p1 class='menu'><a href='edituserprofile'>Edit Profile</a></p1>"
						+ "<p1 class='menu'><a href='changeuserpassword'>Ganti Password</a></p1>"
						+ "</div>");
				pw.println("<div class='tab yellow'>Hallo "+uName+", Selamat datang di Web Pemesanan Tiket Kereta!<br/><br/>Disini anda dapat lihat profile, ddit profile, dan ganti PassWord.<br/>"+"</div>");
				
			}
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Harap Login Terlebih Dahulu!</p1></div>");
		}
	}

}
