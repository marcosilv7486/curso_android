package marcosilv7.com.colorweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import marcosilv7.com.colorweather.models.Day;
import marcosilv7.com.colorweather.models.Hour;
import marcosilv7.com.colorweather.models.Minute;

public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getName();
    public static final String HOURLY = "hourly";
    public static final String DATA = "data";
    public static final String SUMMARY = "summary";
    public static final String ICON = "icon";
    public static final String DAILY = "daily";
    public static final String CURRENTLY = "currently";
    public static final String TEMPERATURE = "temperature";
    public static final String TEMPERATURE_MAX = "temperatureMax";
    public static final String TEMPERATURE_MIN = "temperatureMin";
    public static final String TIME = "time";
    public static final String EEEE = "EEEE";
    public static final String PRECIP_PROBABILITY = "precipProbability";
    public static final String MINUTELY = "minutely";
    public static final String TIME_ZONE = "timezone";
    public static final String DAYS_ARRAYLIST = "DAYS_ARRAYLIST";
    public static final String HOURS_ARRAYLIST = "HOURS_ARRAYLIST";
    public static final String MINUTES_ARRAYLIST = "MINUTES_ARRAYLIST";

    ArrayList<Day> days;
    ArrayList<Hour> hours;
    ArrayList<Minute> minutes;

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
        days = new ArrayList<>();
        hours = new ArrayList<>();
        minutes = new ArrayList<>();

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
                            days = getDailyWeatherFromJson(response);
                            hours = getHourlyWeatherFromJson(response);
                            minutes = getMinutelyWeatherFromJson(response);
                            descriptionTextView.setText(currentWeather.getDescription());
                            iconImageView.setImageDrawable(currentWeather.getIconDrawableResource());
                            hightTempTextView.setText(String.format("H: %s",currentWeather.getHighestTemperature()));
                            lowerTempTextView.setText(String.format("L: %s",currentWeather.getLowestTemperature()));
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
                Toast.makeText(MainActivity.this,"Error de Conexion",Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @OnClick(R.id.dailyWeatherTextView)
    public void dailyWeatherClick() {
        Intent dailyActivityIntent = new Intent(MainActivity.this, DailyWeatherActivity.class);
        dailyActivityIntent.putParcelableArrayListExtra(DAYS_ARRAYLIST,days);
        startActivity(dailyActivityIntent);
    }

    @OnClick(R.id.hourlyWeatherTextView)
    public void hourlyWeatherClick() {
        Intent hourlyActivityIntent = new Intent(MainActivity.this, HourlyWeatherActivity.class);
        hourlyActivityIntent.putParcelableArrayListExtra(HOURS_ARRAYLIST,hours);
        startActivity(hourlyActivityIntent);
    }

    @OnClick(R.id.minutelyWeatherTextView)
    public void minutelyWeatherClick() {
        Intent minutelyActivityIntent = new Intent(MainActivity.this, MinutelyWeatherActivity.class);
        minutelyActivityIntent.putParcelableArrayListExtra(MINUTES_ARRAYLIST,minutes);
        startActivity(minutelyActivityIntent);
    }

    private CurrentWeather getCurrentWeatherFromJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        JSONObject jsonWithCurrently = jsonObject.getJSONObject(CURRENTLY);
        JSONObject jsonWithDailyWeather = jsonObject.getJSONObject(DAILY);
        JSONArray jsonWithDailyWeatherData = jsonWithDailyWeather.getJSONArray(DATA);
        JSONObject jsonWithTodayData = jsonWithDailyWeatherData.getJSONObject(0);

        String summary = jsonWithCurrently.getString(SUMMARY);
        String icon = jsonWithCurrently.getString(ICON);
        String temperature = Math.round(jsonWithCurrently.getDouble(TEMPERATURE)) + "";
        String maxTemperature = Math.round(jsonWithTodayData.getDouble(TEMPERATURE_MAX)) + "";
        String minTemperature = Math.round(jsonWithTodayData.getDouble(TEMPERATURE_MIN)) + "";

        CurrentWeather currentWeather = new CurrentWeather(MainActivity.this);
        currentWeather.setCurrentTemperature(temperature);
        currentWeather.setDescription(summary);
        currentWeather.setIconTemperature(icon);
        currentWeather.setHighestTemperature(maxTemperature);
        currentWeather.setLowestTemperature(minTemperature);
        return currentWeather;
    }

    private ArrayList<Day> getDailyWeatherFromJson(String json) throws JSONException{
        DateFormat dateFormat = new SimpleDateFormat(EEEE);
        days= new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json);
        String timeZone = jsonObject.getString(TIME_ZONE);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        JSONObject jsonWithDailyWeather = jsonObject.getJSONObject(DAILY);
        JSONArray jsonWithDailyWeatherData = jsonWithDailyWeather.getJSONArray(DATA);
        for(int i =0 ; i<jsonWithDailyWeatherData.length() ; i++){
            Day day = new Day();
            JSONObject dayJson = jsonWithDailyWeatherData.getJSONObject(i);
            String dayName = dateFormat.format(dayJson.getDouble(TIME)*1000);
            String rainProbability="Rain probability: "+ Math.round(dayJson.getDouble(PRECIP_PROBABILITY)*100)+"%";
            String weatherDescripcion =  dayJson.getString(SUMMARY);
            day.setDayName(dayName);
            day.setRainProbaility(rainProbability);
            day.setWeatherDescripcion(weatherDescripcion);
            days.add(day);
        }
        return days;
    }

    private ArrayList<Hour> getHourlyWeatherFromJson(String json) throws JSONException{
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        hours = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json);
        String timeZone = jsonObject.getString(TIME_ZONE);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        JSONObject jsonWithHourlyWeather = jsonObject.getJSONObject(HOURLY);
        JSONArray jsonWithHourlyWeatherData = jsonWithHourlyWeather.getJSONArray(DATA);
        for(int i =0 ; i<jsonWithHourlyWeatherData.length() ; i++){
            Hour hour = new Hour();
            JSONObject dayJson = jsonWithHourlyWeatherData.getJSONObject(i);
            String title = dateFormat.format(dayJson.getDouble(TIME)*1000);
            String summary =  dayJson.getString(SUMMARY);
            hour.setTitle(title);
            hour.setWeatherDescription(summary);
            hours.add(hour);
        }
        return hours;
    }

    private ArrayList<Minute> getMinutelyWeatherFromJson(String json) throws JSONException{
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        minutes = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json);
        String timeZone = jsonObject.getString(TIME_ZONE);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        JSONObject jsonWithMinutelyWeather = jsonObject.getJSONObject(MINUTELY);
        JSONArray jsonWithMinutelyWeatherData = jsonWithMinutelyWeather.getJSONArray(DATA);
        for(int i =0 ; i<jsonWithMinutelyWeatherData.length() ; i++){
            Minute minute = new Minute();
            JSONObject dayJson = jsonWithMinutelyWeatherData.getJSONObject(i);
            String title = dateFormat.format(dayJson.getDouble(TIME)*1000);
            String rainProbability="Rain probability: "+Math.round(dayJson.getDouble(PRECIP_PROBABILITY)*100)+"%";
            minute.setTitle(title);
            minute.setRainProbability(rainProbability);
            minutes.add(minute);
        }
        return minutes;
    }
}
