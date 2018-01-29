
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;



/**
*	Parsowanie.
*
*	Klasa zawierajaca metody do parsowania plikow konfiguracyjnych.
*/
public class Parsowanie {

		private int a;
		private String st;
		private Properties properties;
		
		String plik;
		private int rozmiar1, rozmiar2;
		private int maxLevel;
		private int plansza[][] ;
/**
*	Konstruktor
*
*/
		public Parsowanie()
		{
		    //Plik z konfiguracj¹
			//przysz³y obiekt Properties
			properties = new Properties();
			
			System.setProperty("file.encoding", "UTF-8");
		}
	 



	    public void loadProperties(File f){
	        //Strumieñ wejœciowy
	        InputStream is;
	        try {
	            is = new FileInputStream(f);
	            //³adujemy nasze ustawienia
	            properties.load(is);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    /**
	    *zwraca wartosc klucza properties, rzuca wyjatek, jesli zle podana nazwa klucza
	    */
	    private void parsujv(String s)
	    {
	    	try {
	    	a = Integer.parseInt(properties.getProperty(s));
	    	} catch (IllegalArgumentException a) {
	    		System.out.println("Sprawdz pisownie klucza properties"+ a);
	    	}	
	    }
	    private void parsujvstring(String s)
	    {
	    	try {
	    	st = properties.getProperty(s);
	    	} catch (IllegalArgumentException a) {
	    		System.out.println("Sprawdz pisownie klucza properties"+ a);
	    	}	
	    }
	    /**
	    *wywoluje metode prywatana, bo nie moglem zroib dla returna try -kacza
	    */
		public int parsuj(String s)
	    {
	    	parsujv(s);
    		return a;
	    }
		public String parsuj_string(String s)
		{
			parsujvstring(s);
			return st;
		}
	   public Properties getProperties() {
			return properties;
		}
	   
	   /** metoda sluzy do odczytu ukladu planszy, zapisanego jako uklad zer, jedynek i jeszcze jakichs cyferek*/
	   public int[][] odczytZPliku(int a)
		{
			//if (a<=maxLevel)
				plik = "konfigura"+a+".txt";
			
			
			File file = new File(plik);
		      try {
				Scanner in = new Scanner(file);
				//while (in.hasNextInt())
				//{
				rozmiar1 = in.nextInt();
				rozmiar2 = in.nextInt();
				plansza = new int [rozmiar1][rozmiar2];
				for (int i= 0; i<rozmiar1; i++)
				{
					for (int j=0; j< rozmiar2; j++)
					{
						plansza[i][j] = in.nextInt();
					}
				}
				//}
			      in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return plansza;
		}
	   
		public int getwysokosc()
		{
			return rozmiar1;
		}
		public int getszerokosc()
		{
			return rozmiar2;
		}
	
}
