package marcosilv7.com.colorweather.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import marcosilv7.com.colorweather.R;
import marcosilv7.com.colorweather.models.Day;

/**
 * Created by marcosilveriocastro on 26/11/17.
 */

public class DailyWeatherAdapter extends RecyclerView.Adapter {

    List<Day> dayList;
    Context context;

    public DailyWeatherAdapter(List<Day> dayList, Context context) {
        this.dayList = dayList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(context).inflate(R.layout.daily_list_item,parent,false);
        return new DailyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((DailyViewHolder)holder).onBind(position);
    }

    @Override
    public int getItemCount() {
        if(dayList == null)
            return 0;
        return dayList.size();
    }

    class DailyViewHolder extends RecyclerView.ViewHolder{

        TextView dayNameDayListItem;
        TextView weatherDescripcionDayListItem;
        TextView rainProbailityDayListItem;

        public DailyViewHolder(View itemView) {
            super(itemView);
            dayNameDayListItem = itemView.findViewById(R.id.dayNameDayListItem);
            weatherDescripcionDayListItem = itemView.findViewById(R.id.weatherDescripcionDayListItem);
            rainProbailityDayListItem = itemView.findViewById(R.id.rainProbailityDayListItem);
        }

        public void onBind(int position){
            Day day = dayList.get(position);
            dayNameDayListItem.setText(day.getDayName());
            weatherDescripcionDayListItem.setText(day.getWeatherDescripcion());
            rainProbailityDayListItem.setText(day.getRainProbaility());
        }
    }
}
