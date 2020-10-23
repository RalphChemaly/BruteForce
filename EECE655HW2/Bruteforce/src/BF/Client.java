package BF;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
//Written by Ralph with the help of Youtube. https://www.youtube.com/watch?v=RQ2v0CSV4tY
public class Client {
	ClientConnection cc;
	 public static void main(String[] args){
	        // when we run we want to create a new client
	 new Client();
	    }
	    public Client(){
	        try{
	            // Create a new client socket with IP of the client and the destination PORT
	            Socket s = new Socket("localhost", 3333);
	            // Now create a new ClientConnection to the server with the info of each client
	            cc = new ClientConnection(s, this);
	            cc.start();
	            listenForInput();
	        }
	        // Two errors are possible
	        // either unknown IP address/an error that happened with the IP address
	        catch (UnknownHostException e){
	            e.printStackTrace();
	        }
	        // Handle InputOutput errors
	        catch (IOException e){
	            e.printStackTrace();
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    public String[] ReadMyPasswords() {
	    	try {
	    	      String[] pass = new String[10001];
	    	      File myObj = new File("names.txt");
	    	      Scanner myReader = new Scanner(myObj);
	    	      int i=0;
	    	      while (myReader.hasNextLine()) {
	    	        String data = myReader.nextLine();
	    			pass[i]=data;
	    			i++;
	    	      }
	    	      myReader.close();
	    	      return pass;
	    	    } catch (FileNotFoundException e) {
	    	      System.out.println("An error occurred.");
	    	      e.printStackTrace();
	    	      return null;
	    	    }
	    }
	    public void listenForInput() throws InterruptedException, IOException{
	    	System.out.println("Hello Client, do you want to login?");
			Scanner console = new Scanner(System.in);
        while (true){
            while(!console.hasNextLine()){
                try{
                    Thread.sleep(1);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
    		String command = console.nextLine(); 
    		if (command.toLowerCase().equals("login")) {
    			String[] pass = ReadMyPasswords();
    			System.out.println("Please enter your username");
    			Scanner username = new Scanner(System.in);
    			String info1 = username.nextLine();
    			String infoToServer = (command + "," + info1 + "," +pass[0]);
    			cc.SendStringToServer(infoToServer);
    			Thread.sleep(100);
    			//Alain
    			if(cc.din.readUTF().equals("Incorrect password please try again")){
    				int j = 1;
    				while(j<9999) {
        				String authentication = (command + ","+ info1 + "," + pass[j]);
        				cc.SendStringToServer(authentication);
        				Thread.sleep(100);
        				if(cc.din.readUTF().equals("Incorrect password please try again")) {
        					j++;
        				}
        				else if (j == 9999) {
        					System.out.println("Password not in dictionary");
        					break;
        				}
        				else {
        					System.out.println("Login Succeeded");
        					break;
        				}
        			}
    			}
    			//Alain
    			else {
    				System.out.println("Access Granted");
    			}
    		}
            if (command.toLowerCase().equals("quit")){
                break;
            }
        }
        cc.close();
    } 
	    
}
//Written by Ralph with the help of Youtube. https://www.youtube.com/watch?v=RQ2v0CSV4tY