package com.teamihc.ucalendar.adapters;

import android.content.Intent;
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
import com.google.gson.Gson;
import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.activities.DetallesEventoActivity;
import com.teamihc.ucalendar.activities.MainActivity;
import com.teamihc.ucalendar.backend.entidades.Evento;
import com.teamihc.ucalendar.controls.LikeableImageView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeedRVAdapter extends RecyclerView.Adapter<FeedRVAdapter.FeedAdapter>
{
    ArrayList<Evento> eventos;
    View.OnClickListener listener;
    private int p;
    
    public FeedRVAdapter(ArrayList<Evento> eventos)
    {
        this.eventos = eventos;
    }
    
    @NonNull
    @Override
    public FeedAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_info_evento, parent, false);
        p = 0;
        return new FeedAdapter(view, this);
    }
    
    @Override
    public void onBindViewHolder(@NonNull FeedAdapter holder, int position)
    {
        Evento evento = eventos.get(position);
        evento.setPosicionLista(position);
        holder.asignarDatos(evento);
        p = position;
    }
    
    @Override
    public int getItemCount()
    {
        return eventos.size();
    }
    
    
    public class FeedAdapter extends RecyclerView.ViewHolder
    {
        private View view;
        private CardView cardView;
    
        FeedRVAdapter adapter;
        
        TextView nombreEvento;
        TextView nombreCreador;
        TextView descripcion;
        TextView cantLikeInteresados;
        LikeableImageView imgEvento;
        CircleImageView imgCreador;
        ToggleButton btnLike;
        ToggleButton btnGuardar;
        Button btnVerMas;
        
        public FeedAdapter(@NonNull View itemView, FeedRVAdapter adapter)
        {
            super(itemView);
            view = itemView;
            cardView = (CardView) itemView.findViewById(R.id.infoEventoFeed);
            this.adapter = adapter;
        }
        
        public void asignarDatos(Evento evento)
        {
            inicializarComponentes();
            //Evento de like
            btnLike.setChecked(evento.getTieneLike());
            btnLike.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    evento.toggleLike(v.getContext());
                    adapter.notifyItemChanged(evento.getPosicionLista());
                }
            });
            
            //Evento de guardar
            btnGuardar.setChecked(evento.getTieneGuardado());
            btnGuardar.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    evento.toggleGuardar(v.getContext());
                    adapter.notifyItemChanged(evento.getPosicionLista());
                }
            });
            
            //Botón ver más
            btnVerMas.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    MainActivity mainActivity = ((MainActivity) view.getContext());
                    Intent intent = new Intent(mainActivity, DetallesEventoActivity.class);
                    Gson g = new Gson();
                    String eventoJson = g.toJson(eventos.get(p));
                    intent.putExtra("evento", eventoJson);
                    mainActivity.startActivity(intent);
                }
            });
            
            nombreEvento.setText(evento.getNombre());
            nombreCreador.setText(evento.getNombreCreador());
            descripcion.setText(evento.getDescripcion());
            Glide.with(view).load(evento.getFoto()).into(imgEvento);
            Glide.with(view).load(evento.getFotoCreador()).into(imgCreador);
            cantLikeInteresados.setText(evento.getCantidadLikes() + " ME GUSTA - " + evento.getCantidadGuardados() + " INTERESADOS");
        }
        
        private void inicializarComponentes()
        {
            nombreEvento = (TextView) cardView.findViewById(R.id.nombreEvento);
            nombreCreador = (TextView) cardView.findViewById(R.id.nombreCreador);
            descripcion = (TextView) cardView.findViewById(R.id.txtDescripcion);
            cantLikeInteresados = (TextView) cardView.findViewById(R.id.txtCantLikesInteresados);
            imgEvento = (LikeableImageView) cardView.findViewById(R.id.imgEvento);
            imgCreador = (CircleImageView) cardView.findViewById(R.id.imgCreador);
            btnLike = (ToggleButton) cardView.findViewById(R.id.btnLike);
            btnGuardar = (ToggleButton) cardView.findViewById(R.id.btnGuardar);
            btnVerMas = (Button) cardView.findViewById(R.id.btnVerMas);
            
            //Atachar botón de like al doble tap de la imagen
            imgEvento.setToggleButton(btnLike);
        }
    }
}
