package com.teamihc.ucalendar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.backend.entidades.Evento;
import com.teamihc.ucalendar.controls.LikeableImageView;

import java.util.ArrayList;

public class FeedRVAdapter extends RecyclerView.Adapter<FeedRVAdapter.FeedAdapter> implements View.OnClickListener
{
    ArrayList<Evento> eventos;
    View.OnClickListener listener;
    
    public FeedRVAdapter(ArrayList<Evento> eventos)
    {
        this.eventos = eventos;
    }
    
    @NonNull
    @Override
    public FeedAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_info_evento, parent, false);
        view.setOnClickListener(this);
        return new FeedAdapter(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull FeedAdapter holder, int position)
    {
        holder.asignarDatos(eventos.get(position));
    }
    
    @Override
    public int getItemCount()
    {
        return eventos.size();
    }
    
    @Override
    public void onClick(View v)
    {
    
    }
    
    
    public class FeedAdapter extends RecyclerView.ViewHolder
    {
        private View view;
        CardView cardView;
        
        public FeedAdapter(@NonNull View itemView)
        {
            super(itemView);
            view = itemView;
            cardView = (CardView) itemView.findViewById(R.id.infoEventoFeed);
        }
        
        public void asignarDatos(Evento evento)
        {
            TextView nombreEvento = (TextView) cardView.findViewById(R.id.nombreEvento);
            TextView nombreCreador = (TextView) cardView.findViewById(R.id.nombreCreador);
            TextView descripcion = (TextView) cardView.findViewById(R.id.descripcion);
            LikeableImageView imgEvento = (LikeableImageView) cardView.findViewById(R.id.imgEvento);
            TextView cantInteresados = (TextView) cardView.findViewById(R.id.cantInteresados);
            ToggleButton btnLike = (ToggleButton) cardView.findViewById(R.id.btnLike);
            ToggleButton btnGuardar = (ToggleButton) cardView.findViewById(R.id.btnGuardar);
            Button btnVerMas = (Button) cardView.findViewById(R.id.btnVerMas);
            
            imgEvento.setToggleButton(btnLike);
            
            nombreEvento.setText(evento.getNombre());
            nombreCreador.setText(evento.getNombreCreador());
            descripcion.setText(evento.getDescripcion());
            Glide.with(view).load(evento.getFoto()).into(imgEvento);
            
            
        }
    }
}
