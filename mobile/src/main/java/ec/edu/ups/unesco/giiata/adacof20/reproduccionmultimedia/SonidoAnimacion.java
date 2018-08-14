package ec.edu.ups.unesco.giiata.adacof20.reproduccionmultimedia;


import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.FileDescriptor;
import java.io.FileInputStream;

import ec.edu.ups.unesco.giiata.adacof20.utilidades.Validacion;


/**
 * Clase Sonido Animacion
 * Esta clase se utiliza para controlar la reproducción de los sonidos 
 * cuando se inicia la animación o se detiene la misma  
 * @version Adacof 1.0
 * @category Educacion Movil 
 * @author Marco Capón y Edisson Guinansaca
 */
public class SonidoAnimacion {
	/**
	 * atributo de tipo MediaPlayer
	 * sirve para controlar el sonido de la reproduccion
	 */
	public MediaPlayer mpsound=null;
	/**
	 * atributo de tipo context
	 * sirve para obtener la información global sobre un entorno de aplicación
	 */
	Context contextomusic;
	
	/**
	 * atributo de tipo boolean
	 * indica si se esta activada la bandera de reproduccion del sonidos 
	 */
	boolean banderaiIsplaying=false;
	/**
	 * Este atributo es de tipo Uri
     * En este atributo estara contenida el identificador del sonido elegido
	 */
	Uri uri;

	/**
	 * Constructor que toma como entrada el contexto 
	 * y el identificador de archivos mp3 en bruto
	 */
	
	
	/**
     * atributo de tipo Transfromacion
     * sirve para realizar algunas transformaciones sobre 
     * el tiempo que dura un sonido
     */
    private ec.edu.ups.siia.adacof.utilidades.Transformacion utils= new ec.edu.ups.siia.adacof.utilidades.Transformacion();
	
    //VALIDACION 
	/**
	 * llamo a la clase validacion parac comparar y ver que imagenes son sacadas de la galeria
	 * o tambien puede verse si son tomadas con la camara
	 */
    public Validacion val=new Validacion();
	
	///public boolean 
    
	
	public SonidoAnimacion(Context context, Uri ruta) {

		/**
		 * Crea una instancia del reproductor 
		 * multimedia mediante el archivo mp3 pasado
		 */
		//mpsound = MediaPlayer.create(context, id);
		mpsound = MediaPlayer.create(context, ruta);
		contextomusic=context;
		uri=ruta;
	}
	
	
	/**
	 * Constructor que toma como entrada el contexto 
	 * 
	 */
	public SonidoAnimacion(Context context) {
		contextomusic=context;
	}
	
	
	/**
	 * Constructor que toma como entrada el contexto 
	 * 
	 */
	public SonidoAnimacion() {
		
	}
	

	/**
	 * Cuando se llama a este método, el sonido se reproduce 
	 * en un bucle hasta que se llama al método detenerSonido
	 * Usado para las animaciones
	 */
	public void startsound() {
		mpsound.start();
		mpsound.setLooping(true);
	}

	/**
	 * Cuando se llama a este método, la reproducción del sonido es detenida
	 */
	public void stopsound() {
		if (mpsound != null) {
			if (mpsound.isPlaying()) {
				mpsound.stop();
				mpsound.setLooping(false);
			}

			/**
			 * Finalmente liberar todo y limpiar el objeto MediaPlayer
			 */
			mpsound.release();
			mpsound = null;
		}

	}
	
	
	/**
	 * Cuando se llama a este método, la reproducción del sonido es detenida
	 */
	public void pauseSound() {
		if (mpsound != null) {
 			mpsound.pause();
		}

	}
 
	
	/**
	 * metodo que sirve para para iniciar la reproduccion de la animacion
	 */
	public boolean inicioReproduccion(String rutaSonido) {

		try {

			System.out.println(" OK SONIDO  INGRESO  " + rutaSonido);
			if (mpsound != null) {
				mpsound.stop();
				mpsound = new MediaPlayer();
				 
				System.out.println(" OK SONIDO PARA SI ESTA ABIERTO");

			}

			if (val.esCarpetaSonidosAssets(rutaSonido)) {
				// ***************Sonido******************
				mpsound = new MediaPlayer();
				String path = rutaSonido;
				AssetFileDescriptor descriptor = contextomusic.getAssets().openFd(path);
				 
				mpsound.setDataSource(
						descriptor.getFileDescriptor(),
						descriptor.getStartOffset(), descriptor.getLength());
				mpsound
						.setAudioStreamType(android.media.AudioManager.STREAM_MUSIC);
				descriptor.close();
				mpsound.prepare();
				
				System.out.println(" OK SONIDO  CARPETA ASSETS");

			}
			if (val.esSonidoRaw(rutaSonido)) {
				// ***************Sonido******************

				Uri uri = Uri.parse("" + rutaSonido);

				mpsound = new MediaPlayer();
				mpsound = MediaPlayer.create(
						contextomusic, uri);
				mpsound
						.setAudioStreamType(android.media.AudioManager.STREAM_MUSIC);
				System.out.println(" OK SONIDO  CARPETA RAW");

			}

			if (val.esSondioSdcard(rutaSonido)) {

				FileDescriptor fd = null;
				FileInputStream fis = new FileInputStream(rutaSonido);
				fd = fis.getFD();

				mpsound = new MediaPlayer();
				mpsound.setDataSource(fd);
				mpsound.prepare();
				System.out.println(" OK SONIDO TABLET");

			}

			return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return false;

		}
	}
	/**
	 * metodo que reproduce el sonido por una sola vez
	 */
	public void startSound() {
 
			mpsound.start();
	}	
	
	public void destroySound() {

		if (mpsound != null) {
			mpsound.stop();
		}
		
	}	
	
	public String getDuracionReproduccionTotal(){
		long totalDuration = mpsound.getDuration();
        return utils.tiempoenMilisegundos(totalDuration);
	}
	
	public String getDuracionActual(){
        long currentDuration = mpsound.getCurrentPosition();
         return  utils.tiempoenMilisegundos(currentDuration);
	}
	
	
	public long getTiempoDuracionReproduccionTotal(){
		return mpsound.getDuration();
	}
	
	public long geTiempotDuracionActual(){
        return mpsound.getCurrentPosition();
	}
	public int geTiempotDuracionActualEntero(){
        return mpsound.getCurrentPosition();
	}
	public boolean getEstaReproduciendo(){
        if (mpsound.isPlaying()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean getEstaActivo(){
        if (mpsound!= null) {
			return true;
		}else {
			return false;
		}
	} 
	
	public void nuevaPosicion(int posicionAudio){
		mpsound.seekTo(posicionAudio);
	} 
}
