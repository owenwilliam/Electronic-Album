package com.owen.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns="/showImg")
public class ShowImgServlet extends BaseServlet
{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String img = request.getParameter("img");
		//两次进行编码转换
		request.setCharacterEncoding("GBK");
		String imgName = request.getParameter("imgName");
		HttpSession session = request.getSession(true);
		//将用户正在浏览的图片放入HttpSession中
		session.setAttribute("curImg", img);
		session.setAttribute("width", IMG_WIDTH);
		session.setAttribute("height", IMG_HEIGHT);
		response.setContentType("text/javascript;charset=gbk");
		//获取输出流
		PrintWriter out = response.getWriter();
		out.println("$('#show').attr('src' , 'uploadfiles/" + img + "')"
				+ ".attr('width','"+ IMG_WIDTH +"')"
				+ ".attr('height','"+ IMG_HEIGHT +"');");
		out.println("$('#imgInf').empty();");
		out.println("$('#imgInf').append('<h4>图片名字："+imgName+"</h4>')");
	}
	
}
