package com.owen.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owen.exception.AlbumException;

@WebServlet(urlPatterns="/proRegist")
public class ProRegistServlet extends BaseServlet
{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String name = request.getParameter("user");
		String pass = request.getParameter("pass");
		response.setContentType("text/javascript;charset=gbk");
		//获取输出流
		PrintWriter out = response.getWriter();
		try
		{
			out.println("$('#user,#pass').val('');");
			if (name != null && pass != null && as.registUser(name, pass)>0)
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("curUser", name);
				out.println("alert('恭喜您，您已经注册成功!');");
				out.println("$('#noLogin').hide(500)");
				out.println("$('#hasLogin').show(500)");
				out.println("$('#change1').show(500)");
				//调用获取相片列表的方法
				out.println("onLoadHandler();");
			}
			else
			{
				out.println("alert('您注册出现失败，请选择合适的用户名重试！')");
			}
		}
		catch (AlbumException ex)
		{
			out.println("alert('" + ex.getMessage() + "请更换用户名重试！');");
		}
	}
	
}
