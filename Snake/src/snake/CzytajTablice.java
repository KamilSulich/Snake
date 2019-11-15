package snake;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CzytajTablice {
	
	File f1=new File("C:\\wynikiWeza.txt"); //Creation of File Descriptor for input file
    int linecount=0;            //Intializing linecount as zero
    FileReader fr=new FileReader(f1);  //Creation of File Reader object
    BufferedReader br = new BufferedReader(fr);    //Creation of File Reader object
            
	
	 public static int wiersze = 2;
	 public static int Kolumny = 2;
	 public static String [][] Wyniki = new String[wiersze][Kolumny];

   public void CzytajTabliceZpliku() throws Exception 
   {
	  
      Scanner sc = new Scanner(new BufferedReader(new FileReader("C:\\wynikiWeza.txt")));
      
      while(sc.hasNextLine()) {
         for (int i=0; i<Wyniki.length; i++)
         {
            String[] linia = sc.nextLine().trim().split(" ");
            for (int j=0; j<linia.length; j++) 
            {
            	Wyniki[i][j] = (linia[j]);
            }
         }
      }
      sc.close();
   }
}