package marcosilv7.com.colorweather.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marcosilveriocastro on 26/11/17.
 */

public class Day implements Parcelable {
    private String dayName;
    private String weatherDescripcion;
    private String rainProbaility;

    public Day(){

    }

    protected Day(Parcel in) {
        dayName = in.readString();
        weatherDescripcion = in.readString();
        rainProbaility = in.readString();
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getWeatherDescripcion() {
        return weatherDescripcion;
    }

    public void setWeatherDescripcion(String weatherDescripcion) {
        this.weatherDescripcion = weatherDescripcion;
    }

    public String getRainProbaility() {
        return rainProbaility;
    }

    public void setRainProbaility(String rainProbaility) {
        this.rainProbaility = rainProbaility;
    }

    @Override
    public String toString() {
        return "Day{" +
                "dayName='" + dayName + '\'' +
                ", weatherDescripcion='" + weatherDescripcion + '\'' +
                ", rainProbaility='" + rainProbaility + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dayName);
        dest.writeString(weatherDescripcion);
        dest.writeString(rainProbaility);
    }
}
