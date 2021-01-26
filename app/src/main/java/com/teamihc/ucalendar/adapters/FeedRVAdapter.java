package com.teamihc.ucalendar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.teamihc.ucalendar.R;

import java.util.ArrayList;

public class FeedRVAdapter{ /* extends RecyclerView.Adapter<FeedRVAdapter.FeedAdapter> implements View.OnClickListener{

  //ArrayList<> de la info del feed
    LayoutInflater inflater;
    View.OnClickListener listener;
    public FeedRVAdapter() {
        //Aqui se le agrega la lista del feed
    }

    @NonNull
    @Override
    public FeedAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate((R.layout.view_info_evento), parent, false);
        view.setOnClickListener(this);
        return new FeedAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapter holder, int position) {
        holder.asignarDatos();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onClick(View v) {

    }



    public class FeedAdapter extends RecyclerView.ViewHolder {
        CardView cardView;

        public FeedAdapter(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.infoEventoFeed);
        }

        public void asignarDatos() {
            TextView nombreEvento = (TextView) cardView.findViewById(R.id.nombreEvento);
            TextView nombreCreador = (TextView) cardView.findViewById(R.id.nombreCreador);
            TextView descripcion = (TextView) cardView.findViewById(R.id.descripcion);
            TextView cantInteresados = (TextView) cardView.findViewById(R.id.cantInteresados);
            ImageView img_test = (ImageView) cardView.findViewById(R.id.img_test);
            ToggleButton btnLike = (ToggleButton) cardView.findViewById(R.id.btnLike);
            ToggleButton btnGuardar = (ToggleButton) cardView.findViewById(R.id.btnGuardar);
            Button btnVerMas = (Button) cardView.findViewById(R.id.btnVerMas);


        }
    }*/
}
