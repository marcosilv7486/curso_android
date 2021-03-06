package marcosilv7.com.colorweather.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marcosilveriocastro on 26/11/17.
 */

public class Minute implements Parcelable {
    private String title;
    private String rainProbability;

    public Minute(){

    }

    protected Minute(Parcel in) {
        title = in.readString();
        rainProbability = in.readString();
    }

    public static final Creator<Minute> CREATOR = new Creator<Minute>() {
        @Override
        public Minute createFromParcel(Parcel in) {
            return new Minute(in);
        }

        @Override
        public Minute[] newArray(int size) {
            return new Minute[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRainProbability() {
        return rainProbability;
    }

    public void setRainProbability(String rainProbability) {
        this.rainProbability = rainProbability;
    }

    @Override
    public String toString() {
        return "Minute{" +
                "title='" + title + '\'' +
                ", rainProbability='" + rainProbability + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(rainProbability);
    }
}
