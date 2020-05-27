package com.shashi.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
@SuppressWarnings("serial")
public class AdminLogoutServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck[] = req.getCookies();
		if(ck!=null) {
			Cookie ck1 = new Cookie("ckname","");
			ck1.setMaxAge(0);
			res.addCookie(ck1);
			Cookie ck2 = new Cookie("ckpwd","");
			ck2.setMaxAge(0);
			res.addCookie(ck2);
			RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Anda telah berhasil keluar!</p1></div>");
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Anda sudah keluar!</p1></div>");
		}
	}
}
