package com.owen.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owen.exception.AlbumException;

@WebServlet(urlPatterns="/proLogin")
public class ProLoginServlet extends BaseServlet
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
			// 清空id为user、pass输入框的内容
						out.println("$('#user,#pass').val('');");
						if (name != null && pass != null && as.userLogin(name , pass))
						{
							HttpSession session = request.getSession(true);
							session.setAttribute("curUser" , name);
							out.println("alert('您已经登录成功！')");
							out.println("$('#noLogin').hide(500)");
							out.println("$('#hasLogin').show(500)");
							out.println("$('#change1').show(500)");
							// 调用获取相片列表的方法
							out.println("onLoadHandler();");
						}
						else
						{
							out.println("alert('您输入的用户名、密码不符，请重试！')");
						}
		}
		catch (AlbumException e)
		{
			out.println("alert('" + e.getMessage() + "请更新用户名、密码重试！')");
		}
	}
	
}
