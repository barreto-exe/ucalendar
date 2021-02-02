package com.teamihc.ucalendar.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.backend.entidades.Evento;

import java.util.ArrayList;

public class AgendaRVAdapter
        extends RecyclerView.Adapter<AgendaRVAdapter.AgendaAdapter> {

    private CardView cardView;
    ArrayList<String> listpadre;
    Activity activity;

    public AgendaRVAdapter(Activity activity, ArrayList<String> listpadre) {
        this.activity = activity;
        this.listpadre = listpadre;

    }


    @NonNull
    @Override
    public AgendaAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.view_recycler_hijo), parent, false);
        return new AgendaAdapter(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AgendaAdapter holder, int position) {
        //Con esto solo lleno la fecha del evento
        holder.fecha_evento.setText(listpadre.get(position));

        //Aqui lleno la informacion del evento como tal relacionado a esa fecha, es decir el nombre
        ArrayList<String> listahijo = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            listahijo.add("Evento " + i);
        }

        //Inicializo el adapter del recycler hijo
        RecyclerHijoAdapter adapterHijo = new RecyclerHijoAdapter(listahijo);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        holder.rvHijo.setLayoutManager(layoutManager);
        holder.rvHijo.setAdapter(adapterHijo);
    }

    @Override
    public int getItemCount() {
        return listpadre.size();
    }

    public class AgendaAdapter extends RecyclerView.ViewHolder {
        private View view;
        RecyclerView rvHijo;
        TextView fecha_evento;

        public AgendaAdapter(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            rvHijo = itemView.findViewById(R.id.recyclerHijo);
            fecha_evento = (TextView) itemView.findViewById(R.id.fecha_evento);
        }
    }
}
