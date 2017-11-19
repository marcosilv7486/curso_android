package marcosilv7.com.colorweather;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getName();

    @BindView(R.id.dailyWeatherTextView)
    TextView dailyWeatherTextView;
    @BindView(R.id.hourlyWeatherTextView)
    TextView hourlyWeatherTextView;
    @BindView(R.id.minutelyWeatherTextView)
    TextView minutelyWeatherTextView;

    @BindView(R.id.iconImageView)
    ImageView iconImageView;
    @BindView(R.id.hightTempTextView)
    TextView hightTempTextView;
    @BindView(R.id.lowerTempTextView)
    TextView lowerTempTextView;
    @BindView(R.id.currentTempTextView)
    TextView currentTempTextView;
    @BindView(R.id.descriptionTextView)
    TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.darksky.net/forecast/8920ac6e7e17abf9e841a6ce058cd715/37.8267,-122.4233?units=si";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            CurrentWeather currentWeather = getCurrentWeatherFromJson(response);
                            descriptionTextView.setText(currentWeather.getDescription());
                            iconImageView.setImageDrawable(currentWeather.getIconDrawableResource());
                            hightTempTextView.setText(String.format("H: %s",currentWeather.getHighestTemperature()));
                            lowerTempTextView.setText(String.format("L: %s,",currentWeather.getLowestTemperature()));
                            currentTempTextView.setText(currentWeather.getCurrentTemperature());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG,e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @OnClick(R.id.dailyWeatherTextView)
    public void dailyWeatherClick() {
        Intent dailyActivityIntent = new Intent(MainActivity.this, DailyWeatherActivity.class);
        startActivity(dailyActivityIntent);
    }

    @OnClick(R.id.hourlyWeatherTextView)
    public void hourlyWeatherClick() {
        Intent hourlyActivityIntent = new Intent(MainActivity.this, HourlyWeatherActivity.class);
        startActivity(hourlyActivityIntent);
    }

    @OnClick(R.id.minutelyWeatherTextView)
    public void minutelyWeatherClick() {
        Intent minutelyActivityIntent = new Intent(MainActivity.this, MinutelyWeatherActivity.class);
        startActivity(minutelyActivityIntent);
    }

    private CurrentWeather getCurrentWeatherFromJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        JSONObject jsonWithCurrently = jsonObject.getJSONObject("currently");
        JSONObject jsonWithDailyWeather = jsonObject.getJSONObject("daily");
        JSONArray jsonWithDailyWeatherData = jsonWithDailyWeather.getJSONArray("data");
        JSONObject jsonWithTodayData = jsonWithDailyWeatherData.getJSONObject(0);

        String summary = jsonWithCurrently.getString("summary");
        String icon = jsonWithCurrently.getString("icon");
        String temperature = Math.round(jsonWithCurrently.getDouble("temperature")) + "";
        String maxTemperature = Math.round(jsonWithTodayData.getDouble("temperatureMax")) + "";
        String minTemperature = Math.round(jsonWithTodayData.getDouble("temperatureMin")) + "";

        CurrentWeather currentWeather = new CurrentWeather(MainActivity.this);
        currentWeather.setCurrentTemperature(temperature);
        currentWeather.setDescription(summary);
        currentWeather.setIconTemperature(icon);
        currentWeather.setHighestTemperature(maxTemperature);
        currentWeather.setLowestTemperature(minTemperature);
        return currentWeather;
    }
}
