/* 
 * Copyright@ 2015-2018 http://www.nullah.cn/ rights reserved.
 */
package cn.nullah.common.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * TODO
 * 
 * @Project : common-util
 * @Author : Administrator
 * @Date : 2015年12月31日
 */
public class FtpUtil {
	public static void main2(String[] args) throws Exception {

		Socket socket = new Socket("url", 21);

		// get the is and os, how to work about them.
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();

		// read info from the input stream, it is the response from server.
		byte[] buffer = new byte[10000];
		int length = is.read(buffer);
		String s = new String(buffer, 0, length);
		System.out.println(s);

		// send the user
		String str = "USER user/n";
		os.write(str.getBytes());
		// get the response.
		length = is.read(buffer);
		s = new String(buffer, 0, length);
		System.out.println(s);

		// send the password.
		str = "PASS password/n";
		os.write(str.getBytes());
		// get the response.
		length = is.read(buffer);
		s = new String(buffer, 0, length);
		System.out.println(s);

		// send command.
		str = "CWD path/n";
		os.write(str.getBytes());
		// get the response.
		length = is.read(buffer);
		s = new String(buffer, 0, length);
		System.out.println(s);

		// set mode
		str = "EPSV ALL/n";
		os.write(str.getBytes());
		// get the response.
		length = is.read(buffer);
		s = new String(buffer, 0, length);
		System.out.println(s);

		// 得到被动监听信息
		str = "EPSV/n";
		os.write(str.getBytes());
		// 得到返回值
		length = is.read(buffer);
		s = new String(buffer, 0, length);
		System.out.println(s);

		// 取得FTP被动监听的端口号
		System.out.println("debug----------------");
		String portlist = s.substring(s.indexOf("(|||") + 4, s.indexOf("|)"));
		System.out.println(portlist);

		// 实例化ShowList线程类，链接FTP被动监听端口号
		ShowList sl = new ShowList();
		sl.port = Integer.parseInt(portlist);
		sl.start();
		// 执行LIST命令
		str = "LIST/n";
		os.write(str.getBytes());
		// 得到返回值 length = is.read(buffer);
		s = new String(buffer, 0, length);
		System.out.println(s);

		// 关闭链接
		is.close();
		os.close();
		socket.close();
	}
}

// 得到被动链接信息类，这个类是多线程的
class ShowList extends Thread {
	public int port = 0;

	public void run() {
		try {
			Socket socket = new Socket("192.168.0.1", this.port);
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			byte[] buffer = new byte[10000];
			int length = is.read(buffer);
			String s = new String(buffer, 0, length);
			System.out.println(s);
			// 关闭链接
			is.close();
			os.close();
			socket.close();
		} catch (Exception ex) {
		}
	}
}
