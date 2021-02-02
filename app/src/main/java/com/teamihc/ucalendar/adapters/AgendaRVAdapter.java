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
import com.teamihc.ucalendar.fragments.MuestraEventos;

import java.util.ArrayList;

public class AgendaRVAdapter extends RecyclerView.Adapter<AgendaRVAdapter.AgendaAdapter>
{
    private CardView cardView;
    private Activity activity;
    private ArrayList<String> fechasEventos;
    
    public AgendaRVAdapter(Activity activity, ArrayList<String> fechasEventos)
    {
        this.activity = activity;
        this.fechasEventos = fechasEventos;
    }
    
    @NonNull
    @Override
    public AgendaAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.view_recycler_hijo), parent, false);
        return new AgendaAdapter(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull AgendaAdapter holder, int position)
    {
        //Con esto solo lleno la fecha del evento
        holder.fecha_evento.setText(fechasEventos.get(position));
        
        //Aqui lleno la informacion del evento como tal relacionado a esa fecha, es decir el nombre
        ArrayList<String> eventos = new ArrayList<>();
        
        //Inicializo el adapter del recycler hijo
        RecyclerHijoAdapter adapterHijo = new RecyclerHijoAdapter(eventos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        holder.rvHijo.setLayoutManager(layoutManager);
        holder.rvHijo.setAdapter(adapterHijo);
    }
    
    @Override
    public int getItemCount()
    {
        return fechasEventos.size();
    }
    
    
    public class AgendaAdapter extends RecyclerView.ViewHolder
    {
        private View view;
        RecyclerView rvHijo;
        TextView fecha_evento;
        
        public AgendaAdapter(@NonNull View itemView)
        {
            super(itemView);
            view = itemView;
            rvHijo = itemView.findViewById(R.id.recyclerHijo);
            fecha_evento = (TextView) itemView.findViewById(R.id.fecha_evento);
        }
    }
}
