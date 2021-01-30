package com.teamihc.ucalendar.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.backend.Herramientas;
import com.teamihc.ucalendar.backend.entidades.Evento;

import java.util.ArrayList;

public class CalendarioRVAdapter
        extends RecyclerView.Adapter<CalendarioRVAdapter.CalendarioAdapter>
{
    
    private ArrayList<Evento> eventos;
    private CardView cardView;
    
    public CalendarioRVAdapter(ArrayList<Evento> eventos)
    {
        this.eventos = eventos;
    }
    
    @NonNull
    @Override
    public CalendarioAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_evento_calendario, parent, false);
        return new CalendarioAdapter(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull CalendarioAdapter holder, int position)
    {
        holder.asignarDatos(eventos.get(position));
    }
    
    @Override
    public int getItemCount()
    {
        return eventos.size();
    }
    
    public class CalendarioAdapter extends RecyclerView.ViewHolder
    {
        private View view;
        TextView txtFechaEvento, txtNombreEvento;
        CardView cardColor, cardInfoEventoCalendario;
        
        public CalendarioAdapter(@NonNull View itemView)
        {
            super(itemView);
            view = itemView;
            cardView = (CardView) itemView.findViewById(R.id.cardInfoEventoCalendario);
        }
        
        public void asignarDatos(Evento evento)
        {
            inicializarComponentes();
            
            txtFechaEvento.setText(Herramientas.formatearDiaFechaCalendario(evento.getFechaInicio()));
            txtNombreEvento.setText(evento.getNombre());
            cardColor.setCardBackgroundColor(Color.parseColor(evento.getColor()));
            cardColor.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //Aquí el cambio de pantalla!!!
                }
            });
            
            cardInfoEventoCalendario.setCardBackgroundColor(Color.argb(0,0,0,0));
            cardInfoEventoCalendario.setElevation(0);
        }
        
        private void inicializarComponentes()
        {
            txtFechaEvento = cardView.findViewById(R.id.txtFechaEvento);
            txtNombreEvento = cardView.findViewById(R.id.txtNombreEvento);
            cardColor = cardView.findViewById(R.id.cardColor);
            cardInfoEventoCalendario = cardView.findViewById(R.id.cardInfoEventoCalendario);
        }
    }
}
