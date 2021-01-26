package com.teamihc.ucalendar.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.activities.DetallesEventoActivity;

public class InicioFragment extends Fragment
{
    private SwipeRefreshLayout swipeRefresh;
    private Button testVerMas;
    private ImageView testImg;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        return view;
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes();
    }
    
    private void inicializarComponentes()
    {
        //Swipe-refresh
        swipeRefresh = getView().findViewById(R.id.swipe_refresh);
        swipeRefresh.setOnRefreshListener
        (
            new SwipeRefreshLayout.OnRefreshListener()
            {
                @Override
                public void onRefresh()
                {
                    cambiarImagen();
                }
            }
        );
        
        //Imagen
        testImg = getView().findViewById(R.id.img_test);
    
        //Botón ver más
        testVerMas = getView().findViewById(R.id.test_card).findViewById(R.id.btnVerMas);
        testVerMas.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(), DetallesEventoActivity.class);
                startActivity(i);
            }
        });
    }
    
    private void cambiarImagen()
    {
        Glide.with(getView()).load("https://i.imgur.com/sexA6pO.png").into(testImg);
        swipeRefresh.setRefreshing(false);
    }
}