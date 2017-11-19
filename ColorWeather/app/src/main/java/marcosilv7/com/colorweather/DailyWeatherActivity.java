package marcosilv7.com.colorweather;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DailyWeatherActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_weather);

        ArrayList<String> daysArrays = new ArrayList<>();
        daysArrays.add("Lunes");
        daysArrays.add("Martes");
        daysArrays.add("Miercoles");
        daysArrays.add("Jueves");
        daysArrays.add("Viernes");
        daysArrays.add("Sabado");
        daysArrays.add("Domingo");
        ArrayAdapter<String> daysAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,daysArrays);
        setListAdapter(daysAdapter);
    }
}
