package ec.edu.ups.unesco.giiata.adacof20.reproduccionmultimedia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ec.edu.ups.unesco.giiata.adacof20.R;
import ec.edu.ups.unesco.giiata.adacof20.utilidades.Reconstruccion;
import ec.edu.ups.unesco.giiata.adacof20.utilidades.ReproduccionTexto;
import ec.edu.ups.unesco.giiata.adacof20.utilidades.Validacion;


/**
 * Esta clase se utiliza para mostrar la reproduccion de la animacion con
 * recursos gifs
 * 
 * @version Adacof 1.0
 * @category Educacion Movil
 * @author Marco Capón y Edisson Guinansaca
 */
public class ReproduceAnimacion extends Activity implements OnClickListener {

	// DATOS DE LA CARATULA DEL NINO
	/**
	 * componente de tipo de ImageView se carga la foto del nino
	 */
	public ImageView imgViewFotoNino;

	/**
	 * componente de tipo TextView se carga el nombre del nino con el cual se va
	 * a trabajar
	 */
	public TextView txtviewNombreNino;

	// VALIDACION

	/**
	 * atributo de tipo validacion llamo a la clase validacion parac comparar y
	 * ver que imagenes son sacadas de la galeria o tambien puede verse si son
	 * tomadas con la camara
	 */
	public Validacion val = new Validacion();

	// componentes
	/**
	 * atributo de tipo AnimacionGifView este componente servira para visualizar
	 * el recurso gif que se a cargado
	 */
	AnimacionGifView pantallaGif;

	/**
	 * atributo de tipo Button sirve para iniciar la reproduccion de sonido de
	 * audio
	 */
	ImageButton btnPlay;

	/**
	 * atributo de tipo Button sirve para parar la reproduccion de sonido de
	 * audio
	 */

	ImageButton btnStop;

	// arreglo de direcciones
	/**
	 * Este atributo es de tipo ArrayList<String> Sirve para almacenar la
	 * direccione en donde se encuentran el recurso gif y el recurso de sonido
	 */
	ArrayList<String> direcciones;

	// control del sonido
	/**
	 * Este atributo es de tipo SonidoAnimacion Sirve para controlar la
	 * reproduccion delson
	 */
	public SonidoAnimacion asound;

	/**
	 * atributo de tipo boolean sirve como bandera para saber si esta esta
	 * pulsado el boton del play
	 */
	public boolean botonPulsado;

	/**
	 * atributo de tipo Textview Sirve para presentar el nombre del ejercicio
	 * con el cual se esta trabajando
	 * 
	 */
	public TextView nombreEjercicio;

	/**
	 * atributo de tipo String variable en la cual se recupera el nombre del
	 * nino recuperado desde los shared preferences
	 */
	public String shnombreNino;

	/**
	 * atributo de tipo String variable en la cual se recupera la direccion
	 * desde donde esta ubicada la foto del niiino
	 */
	public String shfotoNino;

	// Shared Preferences
	/**
	 * atributo de tipo Sharedpreferences Interfaz para acceder y modificar los
	 * datos de preferencia devueltos por getSharedPreferences
	 */
	SharedPreferences sharedPreferences;

	// donde se guardara la direccion o path de la foto del nino
	/**
	 * Este atributo es de tipo File Sirve para obtener la direccion en la cual
	 * sera guarda las fotos de los ninos
	 */
	public File path;
	/**
	 * Este atributo es de tipo Uri En este atributo estara contenida el
	 * identificador de la imagen elegida de la galeria de imagenes
	 */
	public Uri uriNino;
	// la imagen de nino

	/**
	 * Este atributo es tipo Bitmap En esta variable se guarda un mapa de bits
	 * de la imagen del nino tomada por la camara
	 */
	public Bitmap bitmap;

	// grupos
	/**
	 * Este es un atributo de tipo String Aqui se guarda el nombre del grupo al
	 * cual pertenece el ejercicio
	 */
	public String grupos;

	// menu foto
	/**
	 * Este atributo es de tipo MenuItem Sirve para cargar los datos del Nino en
	 * el Action Bar, en este caso contendra la foto y el nombre perteneciente
	 * al Nino
	 */
	MenuItem itemFoto;

	// VARIABLES DEL TOGGLE

	/**
	 * Atributo de tipo string donde se guarda el titulo que esta ubicado en el
	 * action bar
	 */
	String TituloActionBar = "";

	/**
	 * atributo de tipo de DrawerLayout este sirve para abrir y cerrar el menu
	 */

	DrawerLayout DrawerLayoutDinamicoCajaComponentes;

	/**
	 * atributo de tipo de Listview este sirve para la navegacion del menu
	 */

	ListView DrawerListaOpciones;

	/**
	 * variable de tipo ActionBarDrawerToggle ActionBarDrawerToggle indica la
	 * prescencia de navegacion del Navigation Drawer en el action bar
	 */
	ActionBarDrawerToggle ABDToggle;

	/**
	 * esta variable dirve para colocar un tipo de fuente
	 */
	public static Typeface tipofuente;

	// ///////////////////////////////////////////MOD/////////////////////////////////////////////////////
	// coneccion con la base de datos
	/**
	 * Este atributo es de tipo BDHelper Esta clase sirve para la coneccion con
	 * la base de datos
	 */
	/**
	 * gstEval Gestion de la tabla Evaluacion a traves de insertar, modificar o
	 * eliminar.
	 */
	/**
	 * gstRes Gestion de la tabla Resultados a traves de insertar, modificar o
	 * eliminar.
	 */

	Reconstruccion recons = new Reconstruccion();

	/**
	 * atributo de la clase guardoPreferencias Sirve para guardar el nombre de
	 * la clase actual
	 */


	/**
	 * atributo de tipo Reproduccion Texto sirve para ayudar a emitir el mensaje
	 * de ayuda
	 */
	public ReproduccionTexto repTexto;


	/**
	 * atributo de tipo int para saber cual es la categoria con la cual esta
	 * tabajando el terapista
	 */

	public int categoriaTrabajo;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		repTexto = new ReproduccionTexto(getApplicationContext());
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		categoriaTrabajo = sharedPreferences.getInt("idcategoria", 0);
		tipofuente = Typeface.createFromAsset(getAssets(),
				"fonts/ComicRelief.ttf");
		datosInicio();
		setContentView(R.layout.activity_reproduce_animacion);
		inicioComponentes();


	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Este metodo inicia la coneccion con la base de datos para acceder a los
	 * datos respecto a los ninos con los cuales se esta trabajando durante la
	 * terapia
	 */


	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {


		return true;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.reproduce_animacion_gif, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// permite cargar los pats en donde es sacado el recurso gif
	public void datosInicio() {
		direcciones = getIntent().getExtras().getStringArrayList("direcciones");
		AnimacionGifView.imagen = "" + direcciones.get(0).toString();

	}

	/**
	 * Metodo que sirve para llemar los datos de la caratua en el action bar
	 */


	/**
	 * metodo que inica todos los componentes para comenzar a reproducir la
	 * animacion del gifviewa con sus respectivos controles
	 */
	public void inicioComponentes() {
		nombreEjercicio = (TextView) findViewById(R.id.txtVideoAnimacionReproduceTitulo);
		nombreEjercicio.setTypeface(tipofuente, Typeface.BOLD);
		pantallaGif = (AnimacionGifView) findViewById(R.id.videoAnimacionGifview);
		pantallaGif.setMovieResource(getApplicationContext());
		pantallaGif.setPausa(!pantallaGif.getPausa());

		btnPlay = (ImageButton) findViewById(R.id.btnReproduceVideoAnimacionPlay);
		btnPlay.setOnClickListener(this);
		btnStop = (ImageButton) findViewById(R.id.btnReproduceVideoAnimacionStop);
		btnStop.setOnClickListener(this);
		botonPulsado = false;
		asound = new SonidoAnimacion(ReproduceAnimacion.this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btnReproduceVideoAnimacionPlay:

			pantallaGif.setPausa(!pantallaGif.getPausa());
			inicioReproduccion();

			break;

		case R.id.btnReproduceVideoAnimacionStop:
			if (botonPulsado) {
				pantallaGif.setPausa(!pantallaGif.getPausa());
				pararReproduccion();
			}

			break;

		default:
			break;
		}

	}

	/**
	 * metodo que sirve para para iniciar la reproduccion de la animacion
	 */
	public void inicioReproduccion() {

		if (asound.inicioReproduccion(getIntent().getStringExtra(
				"audioanimacion"))) {
			asound.startsound();
			btnPlay.setEnabled(false);
			btnStop.setEnabled(true);
			botonPulsado = true;
		}

	}

	/**
	 * metodo que sirve para para parar la reproduccion de la animacion
	 *
	 */
	public void pararReproduccion() {
		asound.pauseSound();
		btnPlay.setEnabled(true);
		btnStop.setEnabled(false);
		botonPulsado = false;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {

//			repTexto.reproduccionFinalizada();

			if (botonPulsado) {
				asound.stopsound();
			}

			if (bitmap != null) {
				bitmap.recycle();
			}


			if (categoriaTrabajo==3) {
				ReproduceAnimacion.this.finish();
				onBackPressed();
			}else{
				ReproduceAnimacion.this.finish();
				onBackPressed();
			}

		}
		return super.onKeyDown(keyCode, event);
	}

	public void onStop() {
		super.onStop();
		if (botonPulsado) {
			asound.stopsound();
		}
	}

	public void onDestroy() {
//		repTexto.reproduccionFinalizada();
		if (botonPulsado) {
			asound.stopsound();
		}

		super.onDestroy();
	}

	public void onResume(){
		super.onResume();
		repTexto = new ReproduccionTexto(getApplicationContext());
	}

	protected void onPause() {
		repTexto.detenerTemporalmenteReproduccion();
		super.onPause();
	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		ABDToggle.syncState();
	}

	/** Handling the touch event of app icon */

	/**
	 * metodo para llevar al menu de principal de los modulos
	 */

	public void menuModulosAplicacion() {


		/*
		android.content.Intent intent = new android.content.Intent(
				ReproduceAnimacion.this, MenuModulos.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("EXIT", true);
		ReproduceAnimacion.this.startActivity(intent);

		pantallaGif.limpioDatos();

		*/
		System.gc();
		System.gc();


		System.out.println("LISTO PARA CERRAR ");
		if (botonPulsado) {
			asound.stopsound();
		}
		System.out.println("LISTO PARA CERRAR 2");

		if (categoriaTrabajo==3) {
			ReproduceAnimacion.this.finish();
			System.out.println("LISTO PARA CERRAR  COMOP LOGOC 1");


		}else{


			System.out.println("LISTO PARA CERRAR  COMOP FONA 1 O 2");
			ReproduceAnimacion.this.finish();
			System.out.println("AAAA");
			}
		onBackPressed();
	}

	/**
	 * metodo para llevar al menu de gestion de datos de los ninos
	 */




	/**
	 * Metodo que sirve para trasladar a la pantalla de la caratula del tar
	 */



	// ///////////////////////////////////MOD//////////////////////////////////////////////////////


	// ////////////////////////////////////////////MODSECONDSTAGE////////////////////////////////////////
	/**
	 * 
	 * @param diaN
	 *            int Valor que contiene el dia de nacimiento del niño.
	 * @param mesN
	 *            int Valor que contiene el mes de nacimiento del niño.
	 * @param anioN
	 *            int Valor que contiene el año de nacimiento del niño.
	 * @param diaA
	 *            int Valor que contiene el dia actual.
	 * @param mesA
	 *            int Valor que contiene el mes actual.
	 * @param anioA
	 *            int Valor que contiene el año actual.
	 * @return edadCronologica String Valor de la edad en años y meses.
	 */
	private String calcularEdadExacta(int diaN, int mesN, int anioN, int diaA,
			int mesA, int anioA) {
		// int dia = 0;
		int mes = 0;
		int anio = 0;

		if (diaA < diaN) {
			// dia=diaA-diaN+30;
			mesA--;
		} else {
			// dia=diaA-diaN;
		}

		if (mesA < mesN) {
			mes = mesA - mesN + 12;
			anioA--;
		} else {
			mes = mesA - mesN;
		}

		anio = anioA - anioN;

		String cadAnios = (anio == 1) ? "año" : "años";
		String cadMeses = (mes == 1) ? "mes" : "meses";
		// String cadDias = (dia==1)? "dia" : "dias";
		// anio+" "+cadAnios+" "+mes+" "+cadMeses+" "+dia+" "+cadDias
		String edadCronologica = anio + " " + cadAnios + " " + mes + " "
				+ cadMeses;
		return edadCronologica;
	}
	// //////////////////////////////////////////////////////////////////////////////////////////////////

}
