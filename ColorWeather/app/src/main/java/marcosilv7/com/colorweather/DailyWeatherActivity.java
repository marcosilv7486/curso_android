package marcosilv7.com.colorweather;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import marcosilv7.com.colorweather.adapters.DailyWeatherAdapter;
import marcosilv7.com.colorweather.models.Day;

public class DailyWeatherActivity extends Activity {

    @BindView(R.id.dailyRecyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.noDataWeatherDailyTextView)
    TextView noDataWeatherDailyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_weather);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        ArrayList<Day> days = intent.getParcelableArrayListExtra(MainActivity.DAYS_ARRAYLIST);
        if(days==null || days.isEmpty()){
            noDataWeatherDailyTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            DailyWeatherAdapter adapter = new DailyWeatherAdapter(days,this);
            recyclerView.setAdapter(adapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            noDataWeatherDailyTextView.setVisibility(View.GONE);//DESAPARECE COMPLEMETANENTE
        }
    }
}
