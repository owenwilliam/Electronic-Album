package com.owen.vo;

public class PhotoHolder
{
	   //相片的名称
		private String title;
		//相片在服务器上的文件名
		private String fileName;
        //UUId name;
		private String uuidName;
		
		/**
		 * 无参数的构造器
		 */
		public PhotoHolder()
		{
		}
		/**
		 * 初始化全部属性的构造器
		 * @param title
		 * @param fileName
		 * @param uuidName
		 */
		public PhotoHolder(String title , String fileName, String uuidName)
		{
			this.title = title;
			this.fileName = fileName;
			this.uuidName = uuidName;
		}

		/**
		 * title属性的setter和getter方法
		 * @param title
		 */
		public void setTitle(String title)
		{
			this.title = title;
		}
		public String getTitle()
		{
			return this.title;
		}

		/**
		 * fileName属性的setter和getter方法
		 * @param fileName
		 */
		public void setFileName(String fileName)
		{
			this.fileName = fileName;
		}
		public String getFileName()
		{
			return this.fileName;
		}
		public String getUuidName()
		{
			return uuidName;
		}
		public void setUuidName(String uuidName)
		{
			this.uuidName = uuidName;
		}
		
}
