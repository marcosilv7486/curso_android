package marcosilv7.com.colorweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import marcosilv7.com.colorweather.adapters.HourlyWeatherAdapter;
import marcosilv7.com.colorweather.adapters.MinutelyWeatherAdapter;
import marcosilv7.com.colorweather.models.Hour;
import marcosilv7.com.colorweather.models.Minute;

public class HourlyWeatherActivity extends Activity {

    @BindView(R.id.hourlyReciclerView)
    RecyclerView hourlyReciclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_weather);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        ArrayList<Hour> hours = intent.getParcelableArrayListExtra(MainActivity.HOURS_ARRAYLIST);
        HourlyWeatherAdapter minutelyWeatherAdapter = new HourlyWeatherAdapter(hours,this);
        hourlyReciclerView.setAdapter(minutelyWeatherAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        hourlyReciclerView.setLayoutManager(layoutManager);
        hourlyReciclerView.setHasFixedSize(true);//Indica que los tamaños no van a cambiar o tamaño fijo
    }
}
