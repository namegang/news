package com.news.platform.socket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;


public class ServerSocket extends WebSocketServer{

	
	 private ServerManager _serverManager;

	    public ServerSocket(ServerManager serverManager,int port) throws UnknownHostException{
	        super(new InetSocketAddress(port));     
	        _serverManager=serverManager;
	    }

	    @Override
	    public void onClose(WebSocket socket, int message,
	            String reason, boolean remote) {
	        _serverManager.UserLeave(socket);
	    }

	    @Override
	    public void onError(WebSocket socket, Exception message) {
	        System.out.println("Socket Exception:"+message.toString());
	    }

	    @Override
	    public void onMessage(WebSocket socket, String message) {
	        System.out.println("OnMessage:"+message.toString());

	        if (message.equals("1")) {
	            _serverManager.SendMessageToUser(socket, "What?");
	        }

	        String[] result=message.split(":");
	        if (result.length==2) {
	            if (result[0].equals("user")) {
	                _serverManager.UserLogin(result[1], socket);
	            }
	        }
	    }

	    @Override
	    public void onOpen(WebSocket socket, ClientHandshake handshake) {
	        System.out.println("Some one Connected...");
	    }

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			
		}
	
}
