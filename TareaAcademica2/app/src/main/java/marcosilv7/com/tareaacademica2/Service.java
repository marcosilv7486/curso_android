package marcosilv7.com.tareaacademica2;

import android.graphics.drawable.Drawable;

/**
 * Created by marcosilveriocastro on 4/11/17.
 */

public class Service {

    private String nombre;
    private int imagen;

    public Service(String nombre, int imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
