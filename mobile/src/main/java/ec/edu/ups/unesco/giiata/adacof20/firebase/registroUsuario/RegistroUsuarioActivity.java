package ec.edu.ups.unesco.giiata.adacof20.firebase.registroUsuario;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import ec.edu.ups.unesco.giiata.adacof20.MainActivity;
import ec.edu.ups.unesco.giiata.adacof20.R;
import ec.edu.ups.unesco.giiata.adacof20.login.email.RegistroActivity;
import ec.edu.ups.unesco.giiata.adacof20.modelo.Terapista;

public class RegistroUsuarioActivity extends AppCompatActivity {

    EditText nombreCentro,telefonoCentro,direccionCentro,nombreTerapista;
    Button guardar;

    String tipoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("");

        database.setPersistenceEnabled(true);


       final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        nombreCentro=(EditText) findViewById(R.id.nombreCentro);
        telefonoCentro=(EditText) findViewById(R.id.telefonoCentro);
        direccionCentro=(EditText) findViewById(R.id.direccionCentro);
        guardar=(Button) findViewById(R.id.guardar);
        nombreTerapista=(EditText) findViewById(R.id.nombreTerapista);


        nombreCentro.setVisibility(View.INVISIBLE);
        telefonoCentro.setVisibility(View.INVISIBLE);
        direccionCentro.setVisibility(View.INVISIBLE);


        Spinner spinner = (Spinner) findViewById(R.id.tipoUsuario);
        String[] letra = {"TERAPISTA","PADRE DE FAMILIA"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letra));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                Toast.makeText(adapterView.getContext(),
                        (String) adapterView.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();
                if( ((String) adapterView.getItemAtPosition(pos)).equalsIgnoreCase("TERAPISTA")){


                    tipoUsuario="TERAPISTA";
                    nombreCentro.setVisibility(View.VISIBLE);
                    telefonoCentro.setVisibility(View.VISIBLE);
                    direccionCentro.setVisibility(View.VISIBLE);



                }else{




                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });



        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            if(tipoUsuario.equalsIgnoreCase("TERAPISTA")){

                String nombreT,nombreC,telefono,dirreccion;


                nombreT=nombreTerapista.getText().toString();
                nombreC=nombreCentro.getText().toString();
                telefono=telefonoCentro.getText().toString();
                dirreccion=direccionCentro.getText().toString();



                if (TextUtils.isEmpty(nombreT)) {
                    Toast.makeText(getApplicationContext(), "Ingrese un Nombre de Terapsta!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(nombreC)) {
                    Toast.makeText(getApplicationContext(), "Ingrese el Nombre de su Centro!", Toast.LENGTH_SHORT).show();
                    return;
                }


                Terapista terapista=new Terapista(nombreT,user.getEmail(),user.getUid(),nombreC,telefono,dirreccion);


                System.out.println(terapista.toString());

                DatabaseReference usersRef = ref.child("usuarios");

                String key = ref.child("usuarios").push().getKey();

                Map<String, Terapista> users = new HashMap<>();
                users.put("Terapista "+key,terapista);

                usersRef.setValue(users, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {

                            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(RegistroUsuarioActivity.this);
                            dialogo1.setTitle("Importante");
                            dialogo1.setMessage("Error no se pudo guardar por favor intente nuevamente");
                            dialogo1.setCancelable(false);
                            dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogo1, int id) {

                                }
                            });

                            dialogo1.show();

                            System.out.println(" " + databaseError.getMessage());
                        } else {

                            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(RegistroUsuarioActivity.this);
                            dialogo1.setTitle("Importante");
                            dialogo1.setMessage("Datos Grabados Correctamente");
                            dialogo1.setCancelable(false);
                            dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogo1, int id) {

                                    startActivity(new Intent(RegistroUsuarioActivity.this, MainActivity.class));
                                    finish();

                                }
                            });

                            dialogo1.show();
                            System.out.println("");
                        }
                    }
                });






            }else{




            }





            }
        });




    }









}
