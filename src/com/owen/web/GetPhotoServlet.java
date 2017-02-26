package com.owen.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owen.exception.AlbumException;
import com.owen.vo.PhotoHolder;

@WebServlet(urlPatterns="/getPhoto")
public class GetPhotoServlet extends BaseServlet
{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
		//从HttpSession中获取系统当前用户、相片列表的当前页码
		String name = (String) session.getAttribute("curUser");
		Object pageObj = session.getAttribute("curPage");
		//如果HttpSession中的curpage为null，则设置当前面页为第一页
		int curPage = pageObj == null ? 1 : (Integer) pageObj;
		response.setContentType("text/javascript;charset=gbk");
		//获取输出流
		PrintWriter out = response.getWriter();
		try
		{
			List<PhotoHolder> photos = as.getPhotoByUser(name, curPage);
			//清空id为list的元素
			out.println("var list = $('#list').empty();");
			
			for (PhotoHolder ph : photos)
			{
				out.println("list.append(\"<div align='center'>" +
						"<img  src='uploadfiles/"
						+ ph.getUuidName() +"' width='100' height='100' style='cursor:pointer' onmouseover=\\\"showImg('"
						+ ph.getUuidName()+"','"+ph.getTitle() + "');\\\"/></div>\");");
			}
		}
		catch (AlbumException ex)
		{
			out.println("alert('" + ex.getMessage() + "请重试！')");
		}
	}
	
}
