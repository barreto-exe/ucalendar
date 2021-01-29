package com.teamihc.ucalendar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.backend.entidades.Evento;

import java.util.ArrayList;

public class CalendarioRVAdapter
        extends RecyclerView.Adapter<CalendarioRVAdapter.CalendarioAdapter>
        implements View.OnClickListener{

    private ArrayList<Evento> listaEventos;
    private CardView cardView;

    public CalendarioRVAdapter(ArrayList<Evento> listaEventos)
    {
        this.listaEventos = listaEventos;
    }

    @Override
    public void onClick(View view) {
        // Falta programar el cambio de pantalla
    }

    @NonNull
    @Override
    public CalendarioAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_info_evento, parent, false);
        view.setOnClickListener(this);
        return new CalendarioAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarioAdapter holder, int position) {
        holder.asignarDatos(listaEventos.get(position));
    }

    @Override
    public int getItemCount() { return listaEventos.size(); }

    public class CalendarioAdapter extends RecyclerView.ViewHolder
    {
        private View view;

        public CalendarioAdapter(@NonNull View itemView)
        {
            super(itemView);
            view = itemView;
            //cardView = (CardView) itemView.findViewById(R.id.info_evento_agenda);
        }

        public void asignarDatos(Evento evento)
        {
            // Aquí se llena el CardView cuando esté listo uwu
        }
    }

    /*@Override
    public void onClick(View view) {
        // Falta programar el cambio de pantalla
    }*/
}
