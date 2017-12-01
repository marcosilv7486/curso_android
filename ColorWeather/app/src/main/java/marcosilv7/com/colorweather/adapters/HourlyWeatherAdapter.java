package marcosilv7.com.colorweather.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import marcosilv7.com.colorweather.R;
import marcosilv7.com.colorweather.models.Hour;

/**
 * Created by marcosilveriocastro on 27/11/17.
 */

public class HourlyWeatherAdapter extends RecyclerView.Adapter {

    private List<Hour> data;
    private Context context;

    public HourlyWeatherAdapter(List<Hour> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.hourly_list_item,parent,false);
        return new HourlyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((HourlyViewHolder)holder).onBind(position);
    }

    @Override
    public int getItemCount() {
        if(data == null)
            return 0;
        return data.size();
    }

    class HourlyViewHolder extends RecyclerView.ViewHolder{

        TextView titleHourListItem;
        TextView weatherDescripcionHourListItem;

        public HourlyViewHolder(View itemView) {
            super(itemView);
            titleHourListItem = itemView.findViewById(R.id.titleHourListItem);
            weatherDescripcionHourListItem = itemView.findViewById(R.id.weatherDescripcionHourListItem);
        }

        public void onBind(int position){
            Hour hour = data.get(position);
            titleHourListItem.setText(hour.getTitle());
            weatherDescripcionHourListItem.setText(hour.getWeatherDescription());
        }
    }
}
