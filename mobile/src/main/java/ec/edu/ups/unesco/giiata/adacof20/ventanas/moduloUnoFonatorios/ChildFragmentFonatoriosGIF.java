package ec.edu.ups.unesco.giiata.adacof20.ventanas.moduloUnoFonatorios;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.airbnb.lottie.LottieAnimationView;

import java.io.IOException;
import java.io.InputStream;

import ec.edu.ups.unesco.giiata.adacof20.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * The child fragment is no different than any other fragment other than it is now being maintained by
 * a child FragmentManager.
 */
public class ChildFragmentFonatoriosGIF extends Fragment implements OnClickListener {

    public static final String POSITION_KEY = "FragmentPositionKey";
    public static final String TITULO_KEY = "titulo";

    ImageView play ;

    private int position=0;
    String titulo;

    private VideoView videoView;
     private SeekBar seekBar;


    public static ChildFragmentFonatoriosGIF newInstance(Bundle args) {
        ChildFragmentFonatoriosGIF fragment = new ChildFragmentFonatoriosGIF();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        titulo =getArguments().getString(TITULO_KEY);
        position = getArguments().getInt(POSITION_KEY);
        final View root = inflater.inflate(R.layout.fragment_fonatorios_nube, container, false);
        seekBar = (SeekBar) root.findViewById(R.id.seekBar);

        TextView textview = (TextView) root.findViewById(R.id.textViewPosition);
        textview.setText(titulo);
        textview.setOnClickListener(this);


        final LottieAnimationView animationView = (LottieAnimationView) root.findViewById(R.id.animacion1);




        switch (position) {



            case 1:

                animationView.setAnimation("velas.json");

                break;



            case 2:

                animationView.setAnimation("cloud_disconnection.json");

                break;




        }




        play=(ImageView)  root.findViewById(R.id.play);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                Toast.makeText(root.getContext(), "Changing seekbar's progress"+ progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(root.getContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

//                animationView.setSpeed(progress);
                Toast.makeText(root.getContext(), "Stopped tracking seekbar"+progress, Toast.LENGTH_SHORT).show();
            }
        });













        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationView.playAnimation();



                play.setImageResource(R.drawable.seletor_btn_pause);



            }
        });


        return root;
    }


    public String loadJSONFromAsset(String archivo) {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open(archivo);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Clicked Position: " + position, Toast.LENGTH_LONG).show();
    }

    private MediaPlayer.OnPreparedListener videoViewListener =
            new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    /*
                     * Se indica al reproductor multimedia que el vídeo
                     * se reproducirá en un loop (on repeat).
                     */

                    mediaPlayer.setLooping(true);

                    if (position == 0) {
                        /*
                         * Si tenemos una posición en savedInstanceState,
                         * el vídeo debería comenzar desde aquí.
                         */
                      //  videoView.pause();
                    } else {
                        /*
                         * Si venimos de un Activity "resumed",
                         * la reproducción del vídeo será pausada.
                         */
                        videoView.start();

                    }
                }
            };

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        /* Usamos onSaveInstanceState para guardar la posición de
           reproducción del vídeo en caso de un cambio de orientación. */
        savedInstanceState.putInt("Position",
                videoView.getCurrentPosition());
        videoView.pause();
    }



}
