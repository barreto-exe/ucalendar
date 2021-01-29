package com.teamihc.ucalendar.fragments;


import android.app.Fragment;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.adapters.CalendarioRVAdapter;
import com.teamihc.ucalendar.backend.Herramientas;
import com.teamihc.ucalendar.backend.entidades.Evento;

import java.util.ArrayList;
import java.util.Date;

public class CalendarioFragment extends Fragment
{
    CompactCalendarView calendarView;
    TextView txtMesActual, calendarioIzquierda, calendarioDerecha;
    
    private RecyclerView recyclerView;
    private CalendarioRVAdapter adapter;
    private ArrayList<Evento> listaEventos;

    // Crear un Layout con una imagen de bienvenida para cuando el usuario no tenga guardado ningún evento
    //private LinearLayout bienvenida;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendario, container, false);
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
        txtMesActual = getView().findViewById(R.id.txtMesActual);
        calendarView = getView().findViewById(R.id.calendarView);
        calendarView.setFirstDayOfWeek(Calendar.SUNDAY);
        calendarView.setUseThreeLetterAbbreviation(true);
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener()
        {
            @Override
            public void onDayClick(Date dateClicked)
            {
            }
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth)
            {
                //Actualizar nombre de mes y año
                txtMesActual.setText(Herramientas.formatearMesYearCalendario(firstDayOfNewMonth));
            }
        });
    
        calendarioIzquierda = getView().findViewById(R.id.calendarioIzquierda);
        calendarioIzquierda.setText("<");
        calendarioIzquierda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                calendarView.scrollLeft();
            }
        });
        calendarioDerecha = getView().findViewById(R.id.calendarioDerecha);
        calendarioDerecha.setText(">");
        calendarioDerecha.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                calendarView.scrollRight();
            }
        });
        
        //Colocar nombre de mes y año actual
        txtMesActual.setText(Herramientas.formatearMesYearCalendario(calendarView.getFirstDayOfCurrentMonth()));
        
        // RecyclerView
        recyclerView = getView().findViewById(R.id.eventosDelmes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.getLayoutManager().setMeasurementCacheEnabled(false);

        listaEventos = new ArrayList<Evento>();
        
        // Insertar función que carga en la lista los eventos actualizados
        adapter = new CalendarioRVAdapter(listaEventos);
        recyclerView.setAdapter(adapter);
    }
}