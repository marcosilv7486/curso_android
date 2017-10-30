package marcosilv7.com.layouts;

import android.graphics.Color;

/**
 * Created by marcosilveriocastro on 30/10/17.
 */

public class GeneradorCita {
    private Cita[] citas;
    private int[] colorsArray = {Color.CYAN,Color.RED,Color.BLACK,Color.GRAY,Color.YELLOW,Color.GREEN,Color.MAGENTA};
    public GeneradorCita(){
        citas = new Cita[5];
        citas[0] = new Cita();
        citas[0].setAutor("Walt Disney");
        citas[0].setTexto("Aprendí que lo difícil no es llegar a la cima, sino jamás dejar de subir");

        citas[1] = new Cita();
        citas[1].setAutor("Albert Einstein");
        citas[1].setTexto("La imaginación es más importante que el conocimiento");

        citas[2] = new Cita();
        citas[2].setAutor("Steve Jobs");
        citas[2].setTexto("Tu tiempo es limitado, asÌ que no lo desperdicies viviendo la vida de otra persona");

        citas[3] = new Cita();
        citas[3].setAutor("Albert Camus");
        citas[3].setTexto("El Éxito es fácil de obtener. Lo difícil es merecerlo.");

        citas[4] = new Cita();
        citas[4].setAutor("Irving Berlin");
        citas[4].setTexto("El sabio no dice lo que sabe, y el necio no sabe lo que dice.");
    }
    public Cita obtenerCitaAleatorio(){
        int numeroRandom = Utils.generarNuevoAleatorio(citas.length);
        int color = colorsArray[Utils.generarNuevoAleatorio(citas.length)];
        Cita cita = citas[numeroRandom];
        cita.setColor(color);
        return cita;
    }
}
