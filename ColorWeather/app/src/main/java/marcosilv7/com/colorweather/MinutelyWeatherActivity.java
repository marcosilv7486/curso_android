package marcosilv7.com.colorweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import marcosilv7.com.colorweather.adapters.MinutelyWeatherAdapter;
import marcosilv7.com.colorweather.models.Minute;

public class MinutelyWeatherActivity extends Activity {


    @BindView(R.id.minutelyRecyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minutely_weather);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        ArrayList<Minute> minutes = intent.getParcelableArrayListExtra(MainActivity.MINUTES_ARRAYLIST);
        MinutelyWeatherAdapter minutelyWeatherAdapter = new MinutelyWeatherAdapter(minutes,this);
        recyclerView.setAdapter(minutelyWeatherAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);//Indica que los tamaños no van a cambiar o tamaño fijo
    }
}
