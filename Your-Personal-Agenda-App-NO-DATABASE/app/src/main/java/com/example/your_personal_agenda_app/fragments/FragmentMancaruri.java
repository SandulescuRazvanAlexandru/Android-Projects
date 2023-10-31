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
import com.example.your_personal_agenda_app.util.Mancare;
import com.example.your_personal_agenda_app.util.MancareAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentMancaruri extends Fragment {

    private static String MANCARURI_KEY = "mancaruri_key";

    private ListView lvMancaruri;
    private List<Mancare> listaMancaruri = new ArrayList<>();

    public FragmentMancaruri() {
        // Required empty public constructor
    }

    public static FragmentMancaruri newInstance(ArrayList<Mancare> mancaruri) {
        FragmentMancaruri fragment = new FragmentMancaruri();
        Bundle argumente = new Bundle();
        argumente.putSerializable(FragmentMancaruri.MANCARURI_KEY,mancaruri);
        fragment.setArguments(argumente);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mancaruri, container, false);
        initComponentsFragmentMancaruri(view);
        return view;
    }

    private void initComponentsFragmentMancaruri(View view) {
        lvMancaruri = view.findViewById(R.id.lv_fragment_mancaruri);
        lvMancaruri.setOnItemClickListener(mancareSelectEventListener());
        lvMancaruri.setOnItemLongClickListener(mancareDeleteEventListener());
        if (getArguments() != null) {
            listaMancaruri = (List<Mancare>)getArguments().getSerializable(MANCARURI_KEY);
        }
        if (getContext() != null) {
           MancareAdapter adapter= new MancareAdapter(getContext().getApplicationContext(),
                    R.layout.lv_mancaruri_view, listaMancaruri, getLayoutInflater());
            lvMancaruri.setAdapter(adapter);
            notifyInternalAdapterMancaruri();
        }
    }

    private AdapterView.OnItemLongClickListener mancareDeleteEventListener() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listaMancaruri.remove(position);
                notifyInternalAdapterMancaruri();
                return true;
            }
        };
    }

    private AdapterView.OnItemClickListener mancareSelectEventListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.selectedMancareIndex = position;
            }
        };
    }


    public void notifyInternalAdapterMancaruri() {
        ArrayAdapter adapter = (ArrayAdapter) lvMancaruri.getAdapter();
        adapter.notifyDataSetChanged();
    }
}