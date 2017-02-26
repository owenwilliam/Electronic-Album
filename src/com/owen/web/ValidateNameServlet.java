package com.owen.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.owen.exception.AlbumException;

@WebServlet(urlPatterns="/validateName")
public class ValidateNameServlet extends BaseServlet
{
	public void service(HttpServletRequest request
			, HttpServletResponse response)throws IOException,ServletException
		{
			String name = request.getParameter("user");
			response.setContentType("text/javascript;charset=gbk");
			// 获取输出流
			PrintWriter out = response.getWriter();
			try
			{
				if (name != null)
				{
					if (as.validateName(name))
					{
						out.println("alert('恭喜您，该用户名还未使用，你可使用该用户名！');");
					}
					else
					{
						out.println("alert('对不起，该用户名已被他人占用！');");
						out.println("$('#user').val('');");
					}
				}
				else
				{
					out.println("alert('验证用户名出现异常，请更换用户名重试！');");
				}
			}
			catch (AlbumException ex)
			{
				out.println("alert('" + ex.getMessage() + "请更换用户名重试！');");
			}
		}
}
