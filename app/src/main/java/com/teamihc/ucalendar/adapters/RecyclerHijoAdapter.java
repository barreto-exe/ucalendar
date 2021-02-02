package com.teamihc.ucalendar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamihc.ucalendar.R;

import java.util.ArrayList;

public class RecyclerHijoAdapter extends RecyclerView.Adapter<RecyclerHijoAdapter.HijoAdapter> {

    ArrayList<String> listahijo;


    public RecyclerHijoAdapter(ArrayList<String> listahijo) {
        this.listahijo = listahijo;

    }

    @NonNull
    @Override
    public HijoAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_evento_agenda,parent,false);
        return  new HijoAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HijoAdapter holder, int position) {
        //Asigno el nombre del evento de la lista que se llena en la AgendaRVAdapter
        holder.nombreEvento.setText(listahijo.get(position));
    }

    @Override
    public int getItemCount() {
        return listahijo.size();
    }

    public class HijoAdapter extends RecyclerView.ViewHolder {

        TextView nombreEvento;

        public HijoAdapter(@NonNull View itemView) {
            super(itemView);
            nombreEvento= (TextView) itemView.findViewById(R.id.nombreEvento);

        }
    }
}
