package marcosilv7.com.tareaacademica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ServicesActivity extends AppCompatActivity {

    RecyclerView rv;
    List<Service> serviceList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        rv = (RecyclerView)findViewById(R.id.serviciosRecyclerView);
        LinearLayoutManager llm;
        llm = new LinearLayoutManager(ServicesActivity.this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        iniciarServicios();
        inicializarAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    void iniciarServicios(){
        serviceList = new ArrayList<>();
        serviceList.add(new Service("Cursos y Webinars sobre tecnología BPM",R.drawable.servicio4));
        serviceList.add(new Service("Equipo de Consultoría",R.drawable.servicio1));
        serviceList.add(new Service("Servicio de Soporte",R.drawable.servicio2));
        serviceList.add(new Service("Contacte con nuestro equipo",R.drawable.servicio3));
    }

    private void inicializarAdapter(){
        RVAdapter adapter = new RVAdapter(serviceList);
        rv.setAdapter(adapter);
    }
}


