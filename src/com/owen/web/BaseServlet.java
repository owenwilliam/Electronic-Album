package com.owen.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.owen.service.AlbumService;

public class BaseServlet extends HttpServlet
{
	protected AlbumService as;
	/**
	 * 图片放大时，需要要给服务 提示字符
	 */
	protected static String IMG_CHANGE_STYLE_BIG = "big";
	/**
	 * 图片缩小需要给服务器的提示字符
	 */
	protected static String IMG_CHANGE_STYLE_SMALL = "small";
	/**
	 * 放大的比例
	 */
	protected static double IMG_CAHNGE_BIG_FLOAT = 1.1f;
	/**
	 * 缩小的比例
	 */
	protected static double IMG_CHANGE_SMALL_FLOAT = 0.9f;
	/**
	 * 最大放大的比例
	 */
	protected static double IMG_CHANGE_BIG_BIG_FLOAT = 1000.0f;
	/**
	 * 最小缩小的宽度比例
	 */
	protected static double IMG_CHANGE_SMALL_WIDTH = 100.0f;
	/**
	 * 最小缩小高度比例
	 */
	protected static double IMG_CHANGE_SMALL_height = 300.0f;
	/**
	 * 图片默认的宽度
	 */
	protected static double IMG_WIDTH = 500f;
	/**
	 * 图片默认的高度
	 */
	protected static double IMG_HEIGHT = 400f;
	
	
    /**
     * 定义初始化方法，获取Spring容器的引用
     */
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		
		super.init(config);
		ApplicationContext ctx = WebApplicationContextUtils
				      .getWebApplicationContext(getServletContext());
		as = (AlbumService)ctx.getBean("albumService");
		
	}
	
}
