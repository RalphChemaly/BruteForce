package BF;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadAFile {
  public static void main(String[] args) {
    try {
      String[] passwords = new String[1000];
      File myObj = new File("names.txt");
      Scanner myReader = new Scanner(myObj);
      int i=0;
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
		passwords[i]=data;
		System.out.println(passwords[i]);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
