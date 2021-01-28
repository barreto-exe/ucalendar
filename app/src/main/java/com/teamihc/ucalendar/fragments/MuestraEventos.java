package com.teamihc.ucalendar.fragments;

import com.teamihc.ucalendar.adapters.FeedRVAdapter;
import com.teamihc.ucalendar.backend.entidades.Evento;

import java.util.ArrayList;

/**
 * Interfaz que se implementa en Fragments que contienen ArrayLists de la clase Evento y además
 * hacen uso de dicha lista para alimentar un RecyclerView.
 */
public interface MuestraEventos
{
    FeedRVAdapter adapter = null;
    ArrayList<Evento> eventos = null;
    void setEventos(ArrayList<Evento> eventos);
}
