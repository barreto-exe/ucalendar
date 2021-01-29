package com.teamihc.ucalendar.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.backend.Herramientas;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarioFragment extends Fragment
{
    CompactCalendarView calendarView;
    TextView txtMesActual, calendarioIzquierda, calendarioDerecha;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendario, container, false);
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
    }
}