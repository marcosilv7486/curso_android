package marcosilv7.com.tareaacademica2;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by marcosilveriocastro on 4/11/17.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ServicioViewHolder> {

    public static class ServicioViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView nombreServicio;
        ImageView imagenServicio;

        ServicioViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            nombreServicio = (TextView)itemView.findViewById(R.id.nombreServicioCardView);
            imagenServicio = (ImageView)itemView.findViewById(R.id.imagenServicioCardView);
        }
    }

    List<Service> servicios;

    RVAdapter(List<Service> servicios){
        this.servicios = servicios;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ServicioViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_cardview, viewGroup, false);
        ServicioViewHolder pvh = new ServicioViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ServicioViewHolder personViewHolder, int i) {
        personViewHolder.nombreServicio.setText(servicios.get(i).getNombre());
        personViewHolder.imagenServicio.setImageResource(servicios.get(i).getImagen());
    }

    @Override
    public int getItemCount() {
        return servicios.size();
    }
}
