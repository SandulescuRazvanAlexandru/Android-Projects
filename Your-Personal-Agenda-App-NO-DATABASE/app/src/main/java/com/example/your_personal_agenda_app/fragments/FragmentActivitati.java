package com.example.your_personal_agenda_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.your_personal_agenda_app.MainActivity;
import com.example.your_personal_agenda_app.R;
import com.example.your_personal_agenda_app.util.Activitate;
import com.example.your_personal_agenda_app.util.ActivitateAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentActivitati extends Fragment {

    private static String ACTIVITATI_KEY = "activitati_key";

    private ListView lvActivitati;
    private List<Activitate> listaActivitati = new ArrayList<>();

    public FragmentActivitati() {
        // Required empty public constructor
    }

    public static FragmentActivitati newInstance(ArrayList<Activitate> activitati) {
        FragmentActivitati fragment = new FragmentActivitati();
        Bundle argumente = new Bundle();
        argumente.putSerializable(FragmentActivitati.ACTIVITATI_KEY,activitati);
        fragment.setArguments(argumente);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activitati, container, false);
        initComponentsFragmentMancaruri(view);
        return view;
    }

    private void initComponentsFragmentMancaruri(View view) {
        lvActivitati = view.findViewById(R.id.lv_fragment_activitati);
        lvActivitati.setOnItemClickListener(activitateSelectEventListener());
        lvActivitati.setOnItemLongClickListener(activitateDeleteEventListener());
        if (getArguments() != null) {
            listaActivitati = (List<Activitate>)getArguments().getSerializable(ACTIVITATI_KEY);
        }
        if (getContext() != null) {
            ActivitateAdapter adapter= new ActivitateAdapter(getContext().getApplicationContext(),
                    R.layout.lv_activitati_view, listaActivitati, getLayoutInflater());
            lvActivitati.setAdapter(adapter);
            notifyInternalAdapterActivitati();
        }
    }

    private AdapterView.OnItemLongClickListener activitateDeleteEventListener() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listaActivitati.remove(position);
                notifyInternalAdapterActivitati();
                return true;
            }
        };
    }

    private AdapterView.OnItemClickListener activitateSelectEventListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.selectedActivitateIndex = position;
            }
        };
    }


    public void notifyInternalAdapterActivitati() {
        ArrayAdapter adapter = (ArrayAdapter) lvActivitati.getAdapter();
        adapter.notifyDataSetChanged();
    }
}