package snake;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class CzytajTablice {
	 public static int wiersze = 4;
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