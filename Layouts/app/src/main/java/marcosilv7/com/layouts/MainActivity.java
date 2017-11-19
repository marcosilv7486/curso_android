package marcosilv7.com.layouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static marcosilv7.com.layouts.Utils.generarNuevoAleatorio;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private static final String AUTOR = "AUTOR";
    private static final String CITA = "CITA";
    private static final String COLOR = "COLOR";
    private TextView citaTextView;
    private TextView autorTextView;
    private Button nuevaCitaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //El Bundle puede ser nullo
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        citaTextView = (TextView)findViewById(R.id.citaTextView);
        autorTextView = (TextView)findViewById(R.id.autorTextView);
        nuevaCitaButton = (Button)findViewById(R.id.nuevaCitaButton);
    }

    public void nuevaCita(View view) {
        GeneradorCita generadorCita = new GeneradorCita();
        Cita cita = generadorCita.obtenerCitaAleatorio();
        citaTextView.setText(cita.getTexto());
        citaTextView.setTextColor(cita.getColor());
        autorTextView.setText(cita.getAutor());
        autorTextView.setTextColor(cita.getColor());
        nuevaCitaButton.setBackgroundColor(cita.getColor());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(AUTOR,autorTextView.getText().toString());
        outState.putString(CITA,citaTextView.getText().toString());
        outState.putInt(COLOR,autorTextView.getCurrentTextColor());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //Solo se llama cuando tiene un Bundle asignado
        super.onRestoreInstanceState(savedInstanceState);
        citaTextView.setText(savedInstanceState.getString(CITA));
        citaTextView.setTextColor(savedInstanceState.getInt(COLOR));
        autorTextView.setText(savedInstanceState.getString(AUTOR));
        autorTextView.setTextColor(savedInstanceState.getInt(COLOR));
        nuevaCitaButton.setBackgroundColor(savedInstanceState.getInt(COLOR));

    }
}
