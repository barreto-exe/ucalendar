package com.teamihc.ucalendar.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.activities.DetallesEventoActivity;
import com.teamihc.ucalendar.activities.MainActivity;
import com.teamihc.ucalendar.backend.entidades.Evento;

import java.util.ArrayList;

public class RecyclerHijoAdapter extends RecyclerView.Adapter<RecyclerHijoAdapter.HijoAdapter>
{
    private ArrayList<Evento> eventos;
    
    public RecyclerHijoAdapter(ArrayList<Evento> eventos)
    {
        this.eventos = eventos;
    }
    
    @NonNull
    @Override
    public HijoAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_evento_agenda, parent, false);
        return new HijoAdapter(v);
    }
    
    @Override
    public void onBindViewHolder(@NonNull HijoAdapter holder, int position)
    {
        Evento evento = eventos.get(position);
        evento.setPosicionLista(position);
        holder.asignarDatos(evento);
    }
    
    @Override
    public int getItemCount()
    {
        return eventos.size();
    }
    
    public class HijoAdapter extends RecyclerView.ViewHolder
    {
        private View view;
        private CardView cardColor, cardInfoEventoAgenda;
        private TextView txtNombreEvento;
        
        public HijoAdapter(@NonNull View view)
        {
            super(view);
            this.view = view;
        }
        
        public void asignarDatos(Evento evento)
        {
            inicializarComponentes();
    
            txtNombreEvento.setText(evento.getNombre());
            cardColor.setCardBackgroundColor(Color.parseColor(evento.getColor()));
            cardColor.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    MainActivity mainActivity = ((MainActivity) view.getContext());
                    Intent intent = new Intent(mainActivity, DetallesEventoActivity.class);
                    intent.putExtra("evento", evento);
                    mainActivity.startActivity(intent);
                }
            });
            cardInfoEventoAgenda.setCardBackgroundColor(Color.argb(0,0,0,0));
            cardInfoEventoAgenda.setElevation(0);
        }
        
        private void inicializarComponentes()
        {
            txtNombreEvento = view.findViewById(R.id.txtNombreEvento);
            cardColor = view.findViewById(R.id.cardColor);
            cardInfoEventoAgenda = view.findViewById(R.id.cardInfoEventoAgenda);
        }
    }
}
