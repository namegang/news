package com.news.platform.socket;

import com.news.utils.PropUtil;


public class SocketThread extends Thread {
	ServerManager serverManager=new ServerManager();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}

	public SocketThread(ServerSocket serverScoket) {
		try {
			//读取配置文件的端口
			PropUtil p = new PropUtil();
			int port = Integer.valueOf(p.getProperty("socketPort"));
			serverManager.Start(port);
		} catch (Exception e) {
			System.out.println("SocketThread创建socket服务出错");
			e.printStackTrace();
		}
	}

	public void closeSocketServer() {
		serverManager.Stop();
	}
}
