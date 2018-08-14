package ec.edu.ups.unesco.giiata.adacof20.ventanas.moduloLogocineticos;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import ec.edu.ups.unesco.giiata.adacof20.R;

/**
 * The child fragment is no different than any other fragment other than it is now being maintained by
 * a child FragmentManager.
 */
public class ChildFragmentLogocineticosAnimacion extends Fragment implements OnClickListener {

    public static final String POSITION_KEY = "FragmentPositionKey";
    public static final String TITULO_KEY = "titulo";

    private int position=0;
    String titulo;

    private VideoView videoView;


    public static ChildFragmentLogocineticosAnimacion newInstance(Bundle args) {
        ChildFragmentLogocineticosAnimacion fragment = new ChildFragmentLogocineticosAnimacion();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        titulo =getArguments().getString(TITULO_KEY);
        position = getArguments().getInt(POSITION_KEY);
        View root = inflater.inflate(R.layout.fragment_logocineticos, container, false);


        TextView textview = (TextView) root.findViewById(R.id.textViewPosition);
        textview.setText(titulo);
        textview.setOnClickListener(this);



        return root;
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
