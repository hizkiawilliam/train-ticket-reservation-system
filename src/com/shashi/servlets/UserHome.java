package com.shashi.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
@SuppressWarnings("serial")
public class UserHome extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck[] = req.getCookies();
		if(ck!=null) {
			String uName = ck[0].getValue();
			if(!uName.equals("")||uName!=null) {
				RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
				rd.include(req, res);
				pw.println("<div class='tab'>" + 
						"		<p1 class='menu'>" + 
						"	Hello "+uName+" ! Selamat Datang di Web kami!" + 
						"		</p1>" + 
						"	</div>");
				pw.println("<div class='main'><p1 class='menu'>User Home</p1></div>");
				pw.println("<div class='tab'>Hello "+uName+"! Disini anda dapat memeriksa detail kereta, jadwal Kereta, harga tiket, dan informasi lainnya."+"</div>");
			}
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Harap Login Terlebih Dahulu!</p1></div>");
		}
	}

}
