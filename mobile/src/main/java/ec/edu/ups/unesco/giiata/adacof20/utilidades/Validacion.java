/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.ups.unesco.giiata.adacof20.utilidades;

import android.annotation.SuppressLint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Clase Validacion 
 * En esta clase se implementa todo tipo de validaciones 
 * sea para verificar cadenas de texto que cumplan ciertos
 * requisitos como son un nombre, un apellido o si una imagen proviene
 * de la carpeta assets, de la tarjeta  interna, entre otras validaciones
 * mas
 * @version Adacof 1.0
 * @category Educacion Movil 
 * @author Marco Capón y Edisson Guinansaca
 *
 */
@SuppressLint("NewApi")

public class Validacion {

    String error;
    public Validacion(){
        error = "";
    }
    /**
     * este metodo sirve para validar si una imagen es png, jpg
     * @param texto es el path o la ruta en la cual se encuentra
     * la imagen
     * @return un valor boolean verdadero si es una fotografia
     * 
     */
    public boolean  esFotografia(String texto){
        
        if((texto.endsWith(".png"))|(texto.endsWith(".jpg"))){
           System.out.println("Coincide ES FOTOGRAFIA");
           
           return true;
       }else{
           System.out.println("No coincide FOTOGRAFIA");

           return false;
       }             
   }
    /**
     * este metodo valida si una imagen es proveniente de una galeria
     * @param texto es la ruta de la imagen a analizar
     * @return un valor boolean verdadero si es una imagen proveniente
     * de una galeria 
     */
    public boolean  esImagenGaleria(String texto){
        
        if((texto.startsWith("content://media/external/images/media"))){
           System.out.println("IMAGEN DE LA GALERIA");
           
           return true;
       }else{
           System.out.println("No coincide");

           return false;
       }             
   }
    /**
     * metodo que analiza si un recurso es video
     * @param texto es el tipo de recurso a analizar
     * @return verdadero si es un video el recurso analizado
     */
    public boolean  esVideo(String texto){
        
        if((texto.endsWith("VIDEO"))){
           System.out.println("ES VIDEO");
           
           return true;
       }else{
           System.out.println("No coincide");

           return false;
       }             
   }
    /**
     * metodo que verifica si la imagen tiene una etiqueta de image
     * @param texto es la tipo de recurso a analizar
     * @return un dato boolean verdadero que notifica que es una imagen
     */
    public boolean  esImagen(String texto){
        
        if((texto.endsWith("IMAGEN"))){
           System.out.println("ES IMAGEN");
           
           return true;
       }else{
           System.out.println("No coincide");

           return false;
       }             
   }

    /**
     * metodo que verifica si el recurso es una oracion
     * @param texto es la tipo de recurso a analizar
     * @return un dato boolean verdadero que notifica que es una oracion
     */
    public boolean  esOracion(String texto){
        
        if((texto.endsWith("ORACION"))){
           System.out.println("ES UNA ORACION");
           
           return true;
       }else{
           System.out.println("No coincide no es una oracion");

           return false;
       }             
   }
    
    
    /**
     * metodo que verifica si el recurso es una oracion
     * @param texto es la tipo de recurso a analizar
     * @return un dato boolean verdadero que notifica que es una oracion
     */
    public boolean  esPartesOracion(String texto){
        
        if((texto.endsWith("SUJETO"))||(texto.endsWith("VERBO")||(texto.endsWith("COMPLEMENTO")))){
           System.out.println("ES PARTE DE LA ORACION");
           
           return true;
       }else{
           System.out.println("No coincide no es una seccion de la oracion");

           return false;
       }             
   }
    
    /**
     * metodo que verifica si la imagen tiene una etiqueta de image
     * @param texto es la tipo de recurso a analizar
     * @return un dato boolean verdadero que notifica que es una imagen
     */
    public boolean  esCarpetaImagenesAssets(String texto){
        //imagenes/m
        if((texto.startsWith("imagenes/m"))||(texto.startsWith("imagenes/p"))||(texto.startsWith("imagenes/b"))||(texto.startsWith("imagenes/v"))
        		||(texto.startsWith("imagenes/k"))||(texto.startsWith("imagenes/ccopia"))||(texto.startsWith("imagenes/q"))||(texto.startsWith("imagenes/g"))
        		||(texto.startsWith("imagenes/j"))||(texto.startsWith("imagenes/ch"))||(texto.startsWith("imagenes/f"))||(texto.startsWith("imagenes/l"))
        		||(texto.startsWith("imagenes/d"))||(texto.startsWith("imagenes/t"))||(texto.startsWith("imagenes/c"))||(texto.startsWith("imagenes/s"))
        		||(texto.startsWith("imagenes/z"))||(texto.startsWith("imagenes/r"))||(texto.startsWith("imagenes/n"))||(texto.startsWith("imagenes/eniie"))
        		||(texto.startsWith("consonantes/m"))||(texto.startsWith("consonantes/p"))||(texto.startsWith("consonantes/b"))||(texto.startsWith("consonantes/v"))
        		||(texto.startsWith("consonantes/k"))||(texto.startsWith("consonantes/ccopia"))||(texto.startsWith("consonantes/q"))||(texto.startsWith("consonantes/g"))
        		||(texto.startsWith("consonantes/j"))||(texto.startsWith("consonantes/ch"))||(texto.startsWith("consonantes/f"))||(texto.startsWith("consonantes/l"))
        		||(texto.startsWith("consonantes/d"))||(texto.startsWith("consonantes/t"))||(texto.startsWith("consonantes/c"))||(texto.startsWith("consonantes/s"))
        		||(texto.startsWith("consonantes/z"))||(texto.startsWith("consonantes/r"))||(texto.startsWith("consonantes/n"))||(texto.startsWith("consonantes/eniie"))
        		||(texto.startsWith("modulouno/soplo/imagenes/"))||(texto.startsWith("modulouno/respiracion/imagenes/"))||(texto.startsWith("modulodos/logocineticos/"))
        		||(texto.startsWith("modulocinco/rompecabezas/imagenes/"))){
        	
           System.out.println("ES IMAGEN assets carpeta");
           
           return true;
       }else{
           System.out.println("No coincide");

           return false;
       }             
   }
    
    
    
    
    /**
     * metodo que verifica si la imagen tiene una etiqueta de image
     * @param texto es la tipo de recurso a analizar
     * @return un dato boolean verdadero que notifica que es una imagen 
     */
    public boolean  esCarpetaSonidosAssets(String texto){
        //imagenes/m
        if((texto.startsWith("sonidos/m"))||(texto.startsWith("sonidos/p"))||(texto.startsWith("sonidos/b"))||(texto.startsWith("sonidos/v"))
        		||(texto.startsWith("sonidos/k"))||(texto.startsWith("sonidos/ccopia"))||(texto.startsWith("sonidos/q"))||(texto.startsWith("sonidos/g"))
        		||(texto.startsWith("sonidos/j"))||(texto.startsWith("sonidos/ch"))||(texto.startsWith("sonidos/f"))||(texto.startsWith("sonidos/l"))
        		||(texto.startsWith("sonidos/d"))||(texto.startsWith("sonidos/t"))||(texto.startsWith("sonidos/c"))||(texto.startsWith("sonidos/s"))
        		||(texto.startsWith("sonidos/z"))||(texto.startsWith("sonidos/r"))||(texto.startsWith("sonidos/n"))||(texto.startsWith("sonidos/eniie"))
        		||(texto.startsWith("modulocinco/cantaconoraciones/sonidos/"))||(texto.startsWith("sonidos/respuesta/"))||(texto.startsWith("premiacion/sonidos/"))){
        	
           System.out.println("ES sonidos assets carpeta");
           
           return true;
       }else{
           System.out.println("No coincide");

           return false;
       }             
   }

    
 	
		/**
		 * 
		 * @param patron es fonema a aprender
		 * @param palabra es la que se va a analizar
		 * @return verdadero si conuncide la expresion
		 */
	public boolean expresionRegular(String patron, String palabra){
		Pattern pa = Pattern.compile(patron);
		Matcher ma = pa.matcher(palabra);
		return (ma.find())? true : false; 
	}
	
	public int[] devolverSeccionPalabra(String palabra, String fonema){
		Pattern pa = Pattern.compile(fonema);
		Matcher ma = pa.matcher(palabra);
		int [] valoresSecciones = new int [2]; 		
		if(ma.find()){
			valoresSecciones [0] = ma.start(); 
			valoresSecciones [1] = ma.end(); 
		}
		return valoresSecciones; 
	}
    
    
    /**
     * metodo que verifica si el archivo de audio tiene una etiqueta de audio
     * @param texto es la tipo de recurso a analizar
     * @return un dato boolean verdadero que notifica que es un
     * archifo de audio
     */
    public boolean esAudio(String texto){
        
        if((texto.endsWith("AUDIO"))){
           System.out.println("ES AUDIO");
           
           return true;
       }else{
           System.out.println("No coincide");

           return false;
       }             
   }
    
    /**
     * metodo que verifica si el archivo de audio tiene una etiqueta de audio
     * @param texto es la tipo de recurso a analizar
     * @return un dato boolean verdadero que notifica que es un
     * archifo de audio
     */
    public boolean esSonido(String texto){
        
        if((texto.endsWith("SONIDO"))){
           System.out.println("SONIDO");
           
           return true;
       }else{
           System.out.println("No coincide");

           return false;
       }             
   }
    
    /**
     * este metodo sirve para validar si una imagen es png, jpg
     * @param texto es el path o la ruta en la cual se encuentra
     * la imagen
     * @return un valor boolean verdadero si es una imagen
     * 
     */
    public boolean  esImagenPNGJPG(String texto){
        
        if((texto.endsWith(".jpg"))&&(texto.endsWith(".png"))){
           System.out.println("ES IMAGEN");
           
           return true;
       }else{
           System.out.println("No coincide");

           return false;
       }             
   }
    /**
     * metodo que verifica si el recurso a analizar es un archivo
     * gif 
     * @param texto es la ruta de recurso a analizar
     * @return retorna un valor de tipo booelan que afirma que el recurso 
     * es un archivo gif
     */
    public boolean  esImagenGIF(String texto){
        
        if(texto.endsWith("GIF")){
           System.out.println("ES UN GIF");
           
           return true;
       }else{
           System.out.println("No coincide");

           return false;
       }             
   }
    /**
     * este metodo verifica si la imagen o el recurso de audio
     * proviene de la carpeta de los assets
     * @param texto ruta del recurso a analizar
     * @returnun dato boolean verdadero si la imagen provienes
     * de los assets
     */
    public boolean  esImagenAsets(String texto){
        
        if((texto.startsWith("modulo"))){
           System.out.println("IMAGEN DE LOS ASSETS");
           
           return true;
       }else{
           System.out.println("No coincide");

           return false;
       }             
   }

    
    /**
     * este metodo verifica si es que fue tomada desde una camara
     * @param texto es la ruta de la imagen a analizar
     * @return un dato boolean verdadero si es una imagen que ha sido
     * tomada de una camara
     */
    public boolean  esImagencamara(String texto){
        
         if(((texto.startsWith("/mnt/")))||((texto.startsWith("/storage/")))){
            System.out.println("Coincide ES FOTOGRAFIA de la camara");
            
            return true;
        }else{
            System.out.println("No coincide FOTOGRAFIA de la camara");

            return false;
        }             
    }
    
    /**
     * este metodo verifica si es que la ruta del sonido es de la
     * tarjeta
     * @param texto es la ruta de la imagen a analizar
     * @return un dato boolean verdadero si es el sonido es desde 
     * la tarjeta sd card
     */
    public boolean  esSondioSdcard(String texto){
        
         if(texto.startsWith("/mnt/")){
            System.out.println("Coincide ES sonido desde el sdcard");
            
            return true;
        }else{
            System.out.println("No coincide el sonido desde el sd card");

            return false;
        }             
    }
    
    
    
    /**
     * este metodo verifica si es que la ruta del sonido es de raw
     * @param texto es la ruta de la imagen a analizar
     * @return un dato boolean verdadero si es el sonido es desde 
     * la tarjeta sd card
     */
    public boolean  esSonidoRaw(String texto){
        
         if(texto.startsWith("android.resource://")){
            System.out.println("Coincide ES sonido desde el RAW");
            
            return true;
        }else{
            System.out.println("No coincide el sonido desde del RAW");

            return false;
        }             
    }
    
    /**
     * metodo que verifica si se escribe un nombre o apellido
     *  sin caraceteres especiales, o incluyen numeros en las cadenas
     *  analizadas
     * @param texto es la cadena con el nombre o apellido a analizr 
     * @return un dato boolean verdadero si es que esta cumpliendo con 
     * las restricciones inpuestas en la expresion regular
     */
    public boolean  CadenaValidas(String texto){

    	//validar u00F1 para � 
    	//validar u00D1 para �
    	
       String patron="([a-z|A-Z|\\u00F1|\\u00D1|\\u00C1|\\u00E1|\\u00C9|\\u00E9|\\u00CD|\\u00ED|\\u00D3|\\u00D3|\\u00F3|\\u00DA|\\u00FA|\\u00DC|\\u00FC]{3,60}([\\s][a-z|A-Z|\\u00F1|\\u00D1|\\u00C1|\\u00E1|\\u00C9|\\u00E9|\\u00CD|\\u00ED|\\u00D3|\\u00D3|\\u00F3|\\u00DA|\\u00FA|\\u00DC|\\u00FC]{3,60})?)$";
       Pattern p = Pattern.compile(patron);
        //Busca patron en el texto
        Matcher matcher = p.matcher(texto);

        if(matcher.matches()){
            System.out.println("Coincide");
            return true;
        }else{
            System.out.println("No coincide");
            return false;
        }             
    }
 
    /**
     * metodo que verifica si un valor esta vacio
     * @param dato es el dato analizado
     * @return verdadero si un valor es vacio
     */
    public boolean noVacio(String dato){
    	
        if(dato.trim().isEmpty()){
            error="Ingrese un valor en el campo";
            return true;
        }else{
            return false;
        }
    }

 
    
    /**
 
     */
    public boolean  esletraAprendida(String texto, String patronBase){
        
        System.out.println("datos a analizar  "+texto+"   "+patronBase);
         if(texto.startsWith(patronBase)){
            System.out.println("Coincide con la letra "+texto+"   "+patronBase);
            
            return true;
        }else{
            System.out.println("No coincide con la letra");

            return false;
        }             
    }
    public String getError(){
        return error;
    }


    /**
    
     */
    public boolean  esCodigoAprendida(int codigo, int codigoBase){
        

         if(codigoBase==codigo){

            
            return true;
        }else{
            System.out.println("No coincide con la letra");

            return false;
        }             
    }
 

}
