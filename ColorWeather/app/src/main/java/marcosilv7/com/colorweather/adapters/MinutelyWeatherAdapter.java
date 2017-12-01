package marcosilv7.com.colorweather.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import marcosilv7.com.colorweather.R;
import marcosilv7.com.colorweather.models.Minute;

/**
 * Created by marcosilveriocastro on 26/11/17.
 */

public class MinutelyWeatherAdapter extends RecyclerView.Adapter {

    List<Minute> data;
    Context context;

    public MinutelyWeatherAdapter(List<Minute> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.minutely_list_item,parent,false);
        return new MinuteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MinuteViewHolder)holder).onBind(position);
    }

    @Override
    public int getItemCount() {
        if(data == null)
            return 0;
        return data.size();
    }

    class MinuteViewHolder extends RecyclerView.ViewHolder{

        TextView titleMinutelyListItem;
        TextView rainProbabilityMinuteleyListItem;

        public MinuteViewHolder(View itemView) {
            super(itemView);
            titleMinutelyListItem = itemView.findViewById(R.id.titleMinutelyListItem);
            rainProbabilityMinuteleyListItem = itemView.findViewById(R.id.rainProbabilityMinuteleyListItem);
        }

        public void onBind(int position){
            Minute minute = data.get(position);
            titleMinutelyListItem.setText(minute.getTitle());
            rainProbabilityMinuteleyListItem.setText(minute.getRainProbability());
        }
    }
}
