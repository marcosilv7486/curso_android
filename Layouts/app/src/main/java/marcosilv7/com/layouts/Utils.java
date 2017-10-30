package marcosilv7.com.layouts;

import java.util.Random;

/**
 * Created by marcosilveriocastro on 30/10/17.
 */

public class Utils {
    public static int generarNuevoAleatorio(int numeroMaximo){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        return random.nextInt(numeroMaximo);
    }
}
