package ec.edu.ups.unesco.giiata.adacof20.utilidades;

import java.util.Locale;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

public class ReproduccionTexto implements TextToSpeech.OnInitListener {

	/**
	 * Atributo de tipo TextToSpeech
	 * sirve para poder controlar la reproduccion del soniso
	 */
	private static TextToSpeech textToSpeech;
	
	
	public ReproduccionTexto(Context contexto){
		textToSpeech = new TextToSpeech(contexto, this);
	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			Locale locSpanish = new Locale("spa", "MEX");
			int result = textToSpeech.setLanguage(locSpanish);
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("error", "This Language is not supported");
			} else {
				 
			}
		} else {
			Log.e("error", "Initilization Failed!");
		}
	}
   
	/**
	 * Speaks the string using the specified queuing strategy and speech
	 * parameters.
	 */
	public void convierteTextoaReproduccion(String texto) {
		if (texto.length()>0) {
			textToSpeech.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
		}
	}

	public void detenerTemporalmenteReproduccion(){
//		if(textToSpeech != null){
//			textToSpeech.stop(); 
//		}
		if(textToSpeech.isSpeaking() == true){
			textToSpeech.stop(); 
		}
	}
	
	public void limpiarRecursosTTS(){
		textToSpeech.shutdown(); 
	}
	
//	public void reproduccionFinalizada(){
//		if(textToSpeech.isSpeaking()){
//			textToSpeech.stop();
//			textToSpeech.shutdown();
//		}
//	}

}