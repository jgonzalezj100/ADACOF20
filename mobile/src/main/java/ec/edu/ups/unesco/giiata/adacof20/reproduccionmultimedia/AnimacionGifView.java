package ec.edu.ups.unesco.giiata.adacof20.reproduccionmultimedia;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

import ec.edu.ups.unesco.giiata.adacof20.R;


/**
 * Clase AnimacionGifView: esta clase sirve para el control
 * del mediaplayer en una animacion
 * @version Adacof 1.0
 * @category Educacion Movil 
 * @author Marco Capón y Edisson Guinansaca
 *
 */
public class AnimacionGifView extends View {
	/**
	 * Este atributo es de tipo InputStream
	 * recibe la direccion en la que se encuentra el recurso gif 
	 */
	public InputStream gifInputStream;
 
 	/**
	 * Este atributo es de tipo long
	 * Indica el momento en que inicia la reproduccion de la animacion
	 * del recurso gif
	 */
	private long inicioGifMovie;	
	
	/**
	 * Este atributo es de tipo String
	 * Guarda la direccion en donde se guarda el recurso gif
	 */
	public static String imagen = "";
 
	/**
	 * atributo de tipo final int
	 * sirve para ingresar el tiempo que transcurre de pasar de una imagen a otra
	 */
	private static final int tiempoXFrame = 1000;
	private int mMovieResourceId;

	/**
	 * atributo de tipo Movie
	 * sirve para cargar el recurso gif 
	 * y mostrar en la pantalla
	 */
	private Movie movieGif;
	 
	/**
	 * atributo de tipo int
	 * sirve para guardar el tiempo actual de la animacion
	 */
	private int tiempoActualReproduccion = 0;
	/**
	* sirve para guardar la posicion en donde estara colocado
	* el recurso con respecto al eje x
	*/
	private float distanciaEjeX;
	
	/**
	* sirve para guardar la posicion en donde estara colocado
	* el recurso con respecto al eje y
	*/
	private float distanciaEjeY;
	/**
	* Factor de escala para adaptarse la animación con respecto a sus límites.
	*/
	private float factorEscala;
	/**
	* sirve para guardar el ancho del recurso gif escalada
	*/
	private int distanciaMovieAncho;
	/**
	* sirve para guardar la altura del recurso gif escalada
	*/
	private int distanciaMovieAlto;
	/**
	 * recurso de tipo volatile boolean
	 * sirve para guardar el estado si la reproducion esta 
	 * pausada (true )o no (false)
	 */
	private volatile boolean estadoPausa = false;
	
	/**
	 * recurso de tipo boolean
	 * sirve para guardar el estado si la imagen
	 * esta visible (true )o no (false)
	 */
	public boolean mVisible = true;

	public TypedArray arregloOpciones ;
	
	/**
	 * Es el constructor de la clase AnimaciongifView
	 * @param context es el contexto de la aplicacion
	 */


		/*
		public AnimacionGifView (Context context, AttributeSet attrs) {
			//this(context, attrs, R.styleable.AppTheme_gifMoviewViewStyle);
		}*/
	
		public AnimacionGifView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
	
			inicioValores(context, attrs, defStyle);
		}

		/**
		* Sirve para inicializar  los elementos que permiten controlar la
		* reproduccion de la animacion
		*/			
		private void inicioValores(Context context, AttributeSet attrs, int defStyle) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				setLayerType(View.LAYER_TYPE_SOFTWARE, null);
			}
				/*arregloOpciones = context.obtainStyledAttributes(attrs, R.styleable.GifMoviewView, defStyle,
				R.style.ec_edu_ups_adacof_vista_reproduccionmultimedia_AnimacionGifView);
				mMovieResourceId = arregloOpciones.getResourceId(R.styleable.GifMoviewView_gif, -1); 
				estadoPausa = arregloOpciones.getBoolean(R.styleable.GifMoviewView_pausa, false);
				arregloOpciones.recycle();
				*/
 
		}
		/**
		 * metodo que sirve para inicializar el movie con el recurso
		 * @param context
		 */
		public void setMovieResource(Context context) {
 
						System.out.println("cargando " + imagen);
			try {
				gifInputStream = context.getAssets().open(imagen);
				movieGif = Movie.decodeStream(gifInputStream);	 
	     		 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			requestLayout();
		}
		public void setMovie(Movie movie) {
			this.movieGif = movie;
			requestLayout();
		}
		public Movie getMovie() {
			return movieGif;
		}
		public void setMovieTime(int time) {
			tiempoActualReproduccion = time;
			invalidate();
		}
		public void setPausa(boolean paused) {
			this.estadoPausa = paused;
			/**
			* Calcular nuevo tiempo de inicio de la película, por lo que se reanuda desde el mismo
			*/
			if (!paused) {
				inicioGifMovie = android.os.SystemClock.uptimeMillis() - tiempoActualReproduccion;
			}
				invalidate();
		}
		public boolean getPausa() {
			return this.estadoPausa;
		}
		@Override
		protected void onMeasure(int anchoImagen, int altoImagen) {
				if (movieGif != null) {
						int anchoOrigen = movieGif.width();
						int altoOrigen = movieGif.height();
						/*
						* calcula la escala horizontal
						*/
						float escalaHorizontalmente = 1f;
						int escalaAncho = MeasureSpec.getMode(anchoImagen);
						if (escalaAncho != MeasureSpec.UNSPECIFIED) {
							int maximoAncho = MeasureSpec.getSize(anchoImagen);
							if (anchoOrigen > maximoAncho) {
								escalaHorizontalmente= (float) anchoOrigen / (float) maximoAncho;
							}
						}
						/*
						* calcula la escala vertical
						*/
						float escalaVertical = 1f;
						int escalaAlto = MeasureSpec.getMode(altoImagen);
						if (escalaAlto != MeasureSpec.UNSPECIFIED) {
							int maximumHeight = MeasureSpec.getSize(altoImagen);
							if (altoOrigen > maximumHeight) {
								escalaVertical = (float) altoOrigen / (float) maximumHeight;
							}
						}
						/*
						* Calcula la escala global
						*/
						factorEscala = 1f / Math.max(escalaHorizontalmente, escalaVertical);
						distanciaMovieAncho = (int) (anchoOrigen * factorEscala);
						distanciaMovieAlto = (int) (altoOrigen * factorEscala);
						setMeasuredDimension(distanciaMovieAncho, distanciaMovieAlto);
				} else {
				/*
				* establecer el tamaño mínimo disponible.
				*/
					setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
				}
		}
		@Override
		protected void onLayout(boolean changed, int l, int t, int r, int b) {
			super.onLayout(changed, l, t, r, b);
				/*
				* Calcular la distancia de izquierda y  la de arriba para dibujar 
				* el grafico en el centro
				*/
				distanciaEjeX = (getWidth() - distanciaMovieAncho) / 2f;
				distanciaEjeY = (getHeight() - distanciaMovieAlto) / 2f;
				mVisible = getVisibility() == View.VISIBLE;
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
				if (movieGif != null) {
					if (!estadoPausa) {
						actualizarTiempoAnimacion();
						dibujoMoviePagina(canvas);
						invalidateVista();
					} else {
						dibujoMoviePagina(canvas);
					}
				}
		}
		/**
		* Invalida la vista sólo si es visible.
		*/
		
		private void invalidateVista() {
			if(mVisible) {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
					postInvalidateOnAnimation();
				} else {
					invalidate();
				}
			}
		}
		/**
		*Calcular el tiempo de animación actual
		*/
		private void actualizarTiempoAnimacion() {
			long tiempoIn = android.os.SystemClock.uptimeMillis();
			if (inicioGifMovie == 0) {
				inicioGifMovie = tiempoIn;
			}
				int duracion = movieGif.duration();
				if (duracion == 0) {
				duracion = tiempoXFrame;
			}
			tiempoActualReproduccion = (int) ((tiempoIn - inicioGifMovie) % duracion);
		}
		/**
		* Metodo que sirve para dibujar el actual frame del gif
		*/
		private void dibujoMoviePagina(Canvas canvas) {
			/*
			movieGif.setTime(tiempoActualReproduccion);
			canvas.save(Canvas.MATRIX_SAVE_FLAG);
			canvas.scale(factorEscala, factorEscala);
			movieGif.draw(canvas, distanciaEjeX / factorEscala, distanciaEjeY / factorEscala);
			canvas.restore();
		*/
		}
		
		@Override
		public void onScreenStateChanged(int screenState) {
			super.onScreenStateChanged(screenState);
			mVisible = screenState == SCREEN_STATE_ON;
			invalidateVista();
		}
		
		@Override
		protected void onVisibilityChanged(View changedView, int visibility) {
			super.onVisibilityChanged(changedView, visibility);
			mVisible = visibility == View.VISIBLE;
			invalidateVista();
		}
		@Override
		protected void onWindowVisibilityChanged(int visibility) {
			super.onWindowVisibilityChanged(visibility);
			mVisible = visibility == View.VISIBLE;
			invalidateVista();
		}
		
		public void limpioDatos(){
			invalidateVista();
			arregloOpciones.recycle();
		}
}
