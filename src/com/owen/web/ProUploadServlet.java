package com.owen.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;












import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.owen.exception.AlbumException;

@WebServlet(urlPatterns="/proUpload")
public class ProUploadServlet extends BaseServlet
{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		
		Iterator iter = null;
		String title = null;
		response.setContentType("text/html;charset=gbk");
		//获取输出流
		PrintWriter out = response.getWriter();
		try
		{
			//使用Upload处理上传
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = upload.parseRequest(request);
			iter = items.iterator();
			//遍历每个表单控件对应的内容
			while (iter.hasNext())
			{
				FileItem item = (FileItem)iter.next();
				//如果该项是普通表单域
				if (item.isFormField())
				{
					String name = item.getFieldName();
					if(name.equals("title"))
					{
						title = item.getString("gbk");
					}
				}
				//如果是需要上传的文件
				else
				{
					String user = (String) request.getSession().getAttribute("curUser");
					String serverFileName = null;
					//返回文件名
					String fileName = item.getName();
					//取得文件后缀
					//String suffix = fileName.substring(fileName.lastIndexOf("."));
					//返回文件类型
					String contentType = item.getContentType();
					//只允许上传jpg、gif、png图片
					if(contentType.equals("image/pjpeg")
							|| contentType.equals("image/gif")
							|| contentType.equals("image/jpeg")
							|| contentType.equals("image/png"))
					{
						InputStream input = item.getInputStream();
						serverFileName = UUID.randomUUID().toString();
						//System.out.println("filename:"+fileName);
						//判断文件夹是否存在
						createFile(request,out);
						
						FileOutputStream output = new FileOutputStream(
								getServletContext().getRealPath("/")
								+ "uploadfiles\\" + serverFileName );
								//+ suffix);
						byte[] buffer = new byte[1024];
						int len = 0;
						while ((len = input.read(buffer)) > 0)
						{
							output.write(buffer, 0, len);
						}
						input.close();
						output.close();
						as.addPhoto(user, title, fileName, serverFileName);
								//+ suffix);
						out.write("<script type='text/javascript'>"
								+ "parent.callback('恭喜你，文件上传成功！')"
								+ "</script>");
					}
					else
					{
						out.write("<script type='text/javascript'>"
								+ "parent.callback('本系统只允许上传"
								+ "JPG、GIF、PNG图片文件，请重试！')</script>");
					}
				}
			}
		}
		catch (FileUploadException e)
		{
			e.printStackTrace();
			out.write("<script type='text/javascript'>"
					+ "parent.callback('处理上传文件出现错误，请重试！')"
					+ "</script>");
		}
		catch(AlbumException ex)
		{
			ex.printStackTrace();
		} 
	}
	
	/**
	 * 创建文件夹uploadfiles
	 * @param request
	 * @param out
	 */
	public void createFile(HttpServletRequest request, PrintWriter out)
	{
		String filePath = request.getSession().getServletContext().getRealPath("uploadfiles");
		File file =new File(filePath);    
		//如果文件夹不存在则创建    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    file .mkdir();   
		} else   
		{  
			out.println("alert('系统错误！');");  
		}  
	}
}

