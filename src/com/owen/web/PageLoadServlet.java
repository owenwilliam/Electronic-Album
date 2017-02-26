package com.owen.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns="/pageLoad")
public class PageLoadServlet extends BaseServlet
{
	public void service(HttpServletRequest request
			, HttpServletResponse response)throws IOException,ServletException
		{
			response.setContentType("text/javascript;charset=gbk");
			// 获取输出流
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession(true);
			String name = (String)session.getAttribute("curUser");
			// 如果name不为null，表明用户已经登录
			if (name != null)
			{
				// 隐藏id为noLogin的元素(用户登录面板)
				out.println("$('#noLogin').hide()");
				// 隐藏id为hasLogin的元素(用户控制面板)
				out.println("$('#hasLogin').show()");
				out.println("$('#hasLogin').append('<h3>"+name+"已经登录成功，下面是您的相册</h3>')");
				out.println("$('#change1').show()");
				// 调用获取相片列表的方法
				out.println("onLoadHandler();");
				// 取出HttpSession中的curImg属性
				String curImg = (String)session.getAttribute("curImg");
				double width =session.getAttribute("width") == null ? IMG_WIDTH :(double) session.getAttribute("width");
				double height = session.getAttribute("height") == null ? IMG_HEIGHT : (double) session.getAttribute("height") ;
				// 重新显示用户正在浏览的相片
				if (curImg != null)
				{
					out.println("$('#show').attr('src' , 'uploadfiles/" + curImg + "')"
							+ ".attr('width','"+ width +"')"
							+ ".attr('height','"+ height +"');");
				}
			}
		}
}
