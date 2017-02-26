package com.owen.web;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns="/resshowImg")
public class ResourceImgServlet extends BaseServlet
{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		HttpSession session = request.getSession(true);
		String img = (String) session.getAttribute("curImg");
		response.setContentType("text/javascript;charset=gbk");
		//获取输出流
		PrintWriter out = response.getWriter();
		out.println("$('#resimg').attr('src' , 'uploadfiles/" + img + "');");
	}
	
}
