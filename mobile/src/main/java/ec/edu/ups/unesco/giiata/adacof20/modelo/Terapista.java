package ec.edu.ups.unesco.giiata.adacof20.modelo;

import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Terapista extends Usuario {

    String nombreCentro;
    String cedular;
    String direccionCentro;



    public Terapista() {
    }

    public Terapista(String nombre, String email, String uid, String nombreCentro, String cedular, String direccionCentro) {

        super(nombre,email,uid);
        this.nombreCentro = nombreCentro;
        this.cedular = cedular;
        this.direccionCentro = direccionCentro;
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public String getCedular() {
        return cedular;
    }

    public void setCedular(String cedular) {
        this.cedular = cedular;
    }

    public String getDireccionCentro() {
        return direccionCentro;
    }

    public void setDireccionCentro(String direccionCentro) {
        this.direccionCentro = direccionCentro;
    }


    @Override
    public String getNombre() {
        return super.getNombre();
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getUid() {
        return super.getUid();
    }

    @Override
    public void setUid(String uid) {
        super.setUid(uid);
    }

    @Override
    public Uri getPhotoUrl() {
        return super.getPhotoUrl();
    }

    @Override
    public void setPhotoUrl(Uri photoUrl) {
        super.setPhotoUrl(photoUrl);
    }

    @Override
    public String toString() {
        return "Terapista{" +
                "nombreCentro='" + nombreCentro + '\'' +
                ", cedular='" + cedular + '\'' +
                ", direccionCentro='" + direccionCentro + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", uid='" + uid + '\'' +
                ", photoUrl=" + photoUrl +
                '}';
    }
}
