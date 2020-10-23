package BF;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
//Written by Ralph with the help of Youtube. https://www.youtube.com/watch?v=RQ2v0CSV4tY
public class Server {
	ServerSocket ss;
	ArrayList <ServerConnection> connections= new ArrayList<ServerConnection>();
	boolean shouldRun=true;
	
	public static void main(String[] args) {
		new Server();
	}
	
	public Server() {
		try {
			ss=new ServerSocket(3333);
			while(shouldRun) {
				Socket s=ss.accept();
				ServerConnection sc=new ServerConnection(s,this);
				sc.start();
				connections.add(sc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
//Written by Ralph with the help of Youtube. https://www.youtube.com/watch?v=RQ2v0CSV4tY