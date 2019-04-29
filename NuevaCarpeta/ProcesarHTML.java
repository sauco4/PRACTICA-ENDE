package HTML;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcesarHTML {

	public static void main(String[] args) throws IOException {
			
			Scanner sc=new Scanner(System.in);
			System.out.println("Introduce el nombre del fichero");
			String fichero=sc.next();
			if(fichero.substring((fichero.length()-4), fichero.length()).equals("html")) {
				
			
			
			
					FileReader fr=null;
					try {
						fr=new FileReader(".\\"+fichero);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
					BufferedReader entrada=new BufferedReader(fr);
					String cadena="";
					try {
						cadena=entrada.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					String codigo=cadena;
					int contE=0;
					int contDiv=0;
					int contImg=0;
					boolean HTML5=false;
					boolean Div=false;
					
					PrintWriter salida= new PrintWriter(".\\informe.txt");
					
					while(cadena!=null) {
						//System.out.println(cadena);
						cadena=entrada.readLine();
						codigo=codigo+cadena;
						if(cadena!= null && cadena.contains("<a href"))
							contE++;
						if(cadena!= null && cadena.contains("<div"))
							contDiv++;
						if(cadena!= null && cadena.contains("<img src"))
							contImg++;
						
						
					}
					salida.println("El resultado de analizar el fichero "+fichero+" es: ");
					//System.out.println(codigo);*/
					
					
					

					fr.close();
					
					if(ComprobarEtiquetas(codigo)) {
						salida.println("Contiene todas las etiquetas básicas.");
					}
					
					
					
					salida.println("El numero de enlaces es: "+contE);
					salida.println("El numero de divs es: "+contDiv);
					salida.println("El numero de Imágenes es: "+contImg);
					
					if(codigo.contains("header")||codigo.contains("footer")|| codigo.contains("section") || codigo.contains("article")) {
						HTML5=true;
					}
					if(HTML5)
						salida.println("Contiene etiquetas de HTML5");
					if(codigo.contains("<div")) {
						Div=true;
					}
					if(Div)
						salida.println("Contiene Divs");
					
					
					System.out.println(codigo);

					salida.close();
			}
			else {
				System.out.println("Error no es un archivo HTML");
			}
	}

	public static boolean ComprobarEtiquetas(String codigo) {
		boolean correcto = false;
		
		Pattern pat = Pattern.compile(".*<html.*>.*<head>.*<title>.*</title>.*</head><body.*>.*</body></html>.*");
		Matcher mat = pat.matcher(codigo);
		
		if(mat.matches()) {
			//System.out.println("Contiene todas las etiquetas básicas.");
			correcto=true;
		}
		
		return correcto;

		
	}
}
