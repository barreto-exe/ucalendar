package com.teamihc.ucalendar.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.backend.Herramientas;
import com.teamihc.ucalendar.backend.entidades.Evento;

import java.util.ArrayList;
import java.util.Date;

public class AgendaRVAdapter extends RecyclerView.Adapter<AgendaRVAdapter.AgendaAdapter>
{
    private Activity activity;
    private ArrayList<Date> fechasEventos;
    
    public AgendaRVAdapter(Activity activity)
    {
        this.activity = activity;
        this.fechasEventos = Evento.obtenerFechasEventosGuardados();
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
        //Colocar fecha del grupo de eventos
        Date date = fechasEventos.get(position);
        holder.txtFechaEvento.setText(Herramientas.formatearDiaFechaCalendario(date));
        
        //Aqui lleno la informacion del evento como tal relacionado a esa fecha, es decir el nombre
        ArrayList<String> eventos = new ArrayList<>();
        for(int i = 0; i < 3; i++)
        {
            eventos.add("Evento " + Math.random());
        }
        
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
        TextView txtFechaEvento;
        
        public AgendaAdapter(@NonNull View itemView)
        {
            super(itemView);
            view = itemView;
            rvHijo = itemView.findViewById(R.id.recyclerHijo);
            txtFechaEvento = itemView.findViewById(R.id.txtFechaEvento);
        }
    }
}
