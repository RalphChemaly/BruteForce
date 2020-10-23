package BF;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Stream;
//Written by Ralph with the help of Youtube. https://www.youtube.com/watch?v=RQ2v0CSV4tY
public class ServerConnection  extends Thread{
	
	Socket socket;
	Server server;
	DataInputStream din;
	DataOutputStream dout;
	boolean shouldRun=true;
	int counter = 0;
	long start = System.currentTimeMillis();
	
	public ServerConnection(Socket socket,Server server) {
		super("ServerConnectionThread");
		this.socket=socket;
		this.server=server;
	}
	
	public void sendStringToClient(String text) {
		try {
			dout.writeUTF(text);
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		try {
			din=new DataInputStream(socket.getInputStream());
			dout= new DataOutputStream(socket.getOutputStream());
			
			while(shouldRun) {
				while(din.available() ==0) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				String textIn = din.readUTF();
				String[] x = textIn.split(",");
				System.out.println(x[0]);
				System.out.println(x[1]);
				System.out.println(x[2]);
			    Thread.sleep(100);
				if (x[0].equals("login")) {
					int userid = SqlQueries.login(x[1], x[2]);
					if (userid == -1) {
						String authentication = "Incorrect password please try again";
						sendStringToClient(authentication);
						// Jenan And Diana
						if (authentication.equals("Incorrect password please try again")){
							counter++;
							if (counter == 10){
								long end = System.currentTimeMillis();
								if (end-start > 6000){
									System.out.println("ERROR!" + x[1] +" is under attack!");
									socket.close();
								}
							}
						}
						// Jenan And Diana
					}
					else {
						System.out.println("Access Granted");
						String authentication = "Login Succeded";
						sendStringToClient(authentication);
					}
				}
				else {
					String error = "Make sure you choose between register and login";
					sendStringToClient(error);
				}
			}
			din.close();
			dout.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
//Written by Ralph with the help of Youtube. https://www.youtube.com/watch?v=RQ2v0CSV4tY