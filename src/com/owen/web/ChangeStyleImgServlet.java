package com.owen.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns="/changeStyle")
public class ChangeStyleImgServlet extends BaseServlet
{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		
		HttpSession session = request.getSession(true);
		String img = (String)session.getAttribute("curImg");
		String flag = request.getParameter("flag");
		double width =(double) session.getAttribute("width");
		double height = (double) session.getAttribute("height");
		
		
		response.setContentType("text/javascript;charset=gbk");
		//获取输出流
		PrintWriter out = response.getWriter();
		changeImg(width, height, session, flag, out);
		out.println("$('#show').attr('src' , 'uploadfiles/" + img + "')"
				+ ".attr('width','"+ width +"')"
				+ ".attr('height','"+ height + "');");
	}
	
	/**
	 * 调整图片大
	 * @param width
	 * @param height
	 * @param session
	 * @param flag
	 * @param out
	 */
	public void changeImg(double width, double height, HttpSession session, String flag, PrintWriter out)
	{
		if (IMG_CHANGE_STYLE_BIG.equals(flag) && ifChangeBig(width, height, out))
		{
			width *= IMG_CAHNGE_BIG_FLOAT;
			height *= IMG_CAHNGE_BIG_FLOAT;
			session.setAttribute("width", width);
			session.setAttribute("height", height);
		}
		else if (IMG_CHANGE_STYLE_SMALL.equals(flag) && ifChangeSmall(width, height, out))
		{
			width *= Math.cos(IMG_CHANGE_SMALL_FLOAT);
			height *= Math.cos(IMG_CHANGE_SMALL_FLOAT);
			session.setAttribute("width", width);
			session.setAttribute("height", height);
		}
	}
	
	/**
	 * 是否再次变大
	 * @param width
	 * @param height
	 * @param out
	 * @return
	 */
	public boolean ifChangeBig(double width, double height, PrintWriter out)
	{
		if (width > IMG_CHANGE_BIG_BIG_FLOAT || height > IMG_CHANGE_BIG_BIG_FLOAT)
		{
			out.println("alert('图片已经是最大了啦！')");
			return false;
		}
		return true;
	}
	
	/**
	 * 是否再次变小
	 * @param width
	 * @param height
	 * @param out
	 * @return
	 */
	public boolean ifChangeSmall(double width, double height, PrintWriter out)
	{
		if (width < IMG_CHANGE_SMALL_WIDTH || height < IMG_CHANGE_SMALL_height)
		{
			out.println("alert('图片已经是最小了啦！')");
			return false;
		}
		return true;
	}
}
