package ec.edu.ups.unesco.giiata.adacof20;

/**
 * Created by chino on 15/07/2017.
 */
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;

import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



import de.hdodenhof.circleimageview.CircleImageView;
import ec.edu.ups.unesco.giiata.adacof20.login.LoginActivity;
import ec.edu.ups.unesco.giiata.adacof20.modelo.Terapista;
import ec.edu.ups.unesco.giiata.adacof20.modelo.Usuario;
import ec.edu.ups.unesco.giiata.adacof20.opencv.OpenCvFragment;
import ec.edu.ups.unesco.giiata.adacof20.reconocimientoImagenes.ReconocerImagen;
import ec.edu.ups.unesco.giiata.adacof20.reconocimientoTexto.Reconocer;
import ec.edu.ups.unesco.giiata.adacof20.ventanas.moduloConcienciaFonologica.FragmentMenuConciencia;
import ec.edu.ups.unesco.giiata.adacof20.ventanas.moduloLogocineticos.FragmentMenuLogocineticos;
import ec.edu.ups.unesco.giiata.adacof20.ventanas.moduloUnoFonatorios.FragmentMenuFonatorios;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private  static  final String TAG="MainActivity";
    private TextView nameTextView,username;
    private TextView emailTextView;
    private TextView uidTextView;
    Button opencv;
    DatabaseReference dataBase1;



    private DrawerLayout drawerLayout;


    private String drawerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        if ( FirebaseDatabase.getInstance()== null) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
            dataBase1 = database.getReference();

        }else{



        }



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        TextView tusuario = (TextView) header.findViewById(R.id.username);

        TextView tcorreo = (TextView) header.findViewById(R.id.email);

        CircleImageView imagenPerfil=(CircleImageView) header.findViewById(R.id.circle_image);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("KeyHash:", "entro");




        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("usuarios");



        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue()==null) {


                }
                else {
                    System.out.println(dataSnapshot.getValue().toString());


                    GenericTypeIndicator<Terapista> t = new GenericTypeIndicator<Terapista>() {};
                    Terapista terapista = dataSnapshot.getValue(t);





                    System.out.println(terapista.toString());


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "ec.edu.ups.unesco.giiata.adacof20",
                    PackageManager.GET_SIGNATURES);

            Log.d("KeyHash:", "entro");

            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }




        if (user != null) {

            Usuario u=new Usuario();

            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            String uid = user.getUid();

            tusuario.setText(name);

            tcorreo.setText(email);



            try {
                Log.e("UId", photoUrl.toString());


                imagenPerfil.setImageURI(photoUrl);


            }catch (Exception e)
            {


            }


            u.setNombre(name);
            u.setEmail(email);
            u.setPhotoUrl(photoUrl);
            u.setUid(uid);

            Usuario.setUsuarioSingleton(u);





            //nameTextView.setText(name);
            //emailTextView.setText(email);

            //uidTextView.setText(uid);


        } else {
            goLoginScreen();
        }


    }



    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);



    }




    private Bitmap get_imagen(String url) {
        Bitmap bm = null;
        try {


            URL _url = new URL(url);
            URLConnection con = _url.openConnection();
            con.connect();
            InputStream is = con.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e(e.getMessage(), "Mensaje de error");
        }
        return bm;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_opciones_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void logout() {
        FirebaseAuth.getInstance().signOut();

        LoginManager.getInstance().logOut();


        goLoginScreen();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        boolean fragmentTransaction = false;






        //noinspection SimplifiableIfStatement
        int id = item.getItemId();


        if(id==R.id.nav_imagen){


            startActivity(new Intent(MainActivity.this, ReconocerImagen.class));

        }


        if (id == R.id.nav_log_out) {

            logout();

        }

        if(id==R.id.nav_fonotarios){
            Fragment fragment = null;
            System.out.println("Entro");
            fragment = new FragmentMenuFonatorios();
            fragmentTransaction = true;

            if(fragmentTransaction) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmento, fragment)
                        .commit();

                item.setChecked(true);
                // getSupportActionBar().setTitle(item.getTitle());
            }
        }
        if(id==R.id.nav_logocineticos){
            Fragment fragment = null;
            System.out.println("Entro");
            fragment = new FragmentMenuLogocineticos();
            fragmentTransaction = true;

            if(fragmentTransaction) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmento, fragment)
                        .commit();

                item.setChecked(true);
                // getSupportActionBar().setTitle(item.getTitle());
            }
        }


        if(id== R.id.nav_concienca){

            Fragment fragment = null;
            System.out.println("Entro");
            fragment = new FragmentMenuConciencia();
            fragmentTransaction = true;

            if(fragmentTransaction) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmento, fragment)
                        .commit();

                item.setChecked(true);
                 getSupportActionBar().setTitle(item.getTitle());
            }


        }

        if(id == R.id.nav_productos){



          //  startActivity(new Intent(MainActivity.this, Reconocer.class));


            Fragment fragment = null;
            System.out.println("Entro");
            fragment = new Reconocer();
            fragmentTransaction = true;

            if(fragmentTransaction) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmento, fragment)
                        .commit();

                item.setChecked(true);
               // getSupportActionBar().setTitle(item.getTitle());
            }
        }


        getSupportActionBar().setTitle(item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}