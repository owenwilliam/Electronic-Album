package com.owen.exception;

public class AlbumException extends RuntimeException
{
	/**
	 * 提供一个无参数的构造器
	 */
	public AlbumException()
	{
	}
	/**
	 * 提供一个带字符串参数的构造器
	 * @param msg
	 */
	public AlbumException(String msg)
	{
		super(msg);
	}
}
