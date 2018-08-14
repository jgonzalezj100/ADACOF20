package ec.edu.ups.unesco.giiata.adacof20.modelo;

import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by chino on 15/07/2017.
 */
@IgnoreExtraProperties
public class Usuario {

    private static Usuario usuarioSingleton = new Usuario();

    String nombre,email,uid;

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    String tipoUsuario;

    Uri photoUrl;

    public Usuario(String nombre,String email,String uid) {
        this.nombre=nombre;
        this.email=email;
        this.uid=uid;

    }

    public Usuario(){


    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Uri getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(Uri photoUrl) {
        this.photoUrl = photoUrl;
    }

    public static Usuario getUsuarioSingleton() {
        return usuarioSingleton;
    }

    public static void setUsuarioSingleton(Usuario usuarioSingleton) {
        Usuario.usuarioSingleton = usuarioSingleton;
    }


}
