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
import com.example.your_personal_agenda_app.util.Persoana;
import com.example.your_personal_agenda_app.util.PersoanaAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentPersoane extends Fragment {

    private static String PERSOANE_KEY = "persoane_key";

    private ListView lvPersoane;
    private List<Persoana> listaPersoane = new ArrayList<>();

    public FragmentPersoane() {
        // Required empty public constructor
    }

    public static FragmentPersoane newInstance(ArrayList<Persoana> persoane) {
        FragmentPersoane fragment = new FragmentPersoane();
        Bundle argumente = new Bundle();
        argumente.putSerializable(FragmentPersoane.PERSOANE_KEY,persoane);
        fragment.setArguments(argumente);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_persoane, container, false);
        initComponentsFragmentPersoane(view);
        return view;
    }

    private void initComponentsFragmentPersoane(View view) {
        lvPersoane = view.findViewById(R.id.lv_fragment_persoane);
        lvPersoane.setOnItemClickListener(persoanaSelectEventListener());
        lvPersoane.setOnItemLongClickListener(persoanaDeleteEventListener());
        if (getArguments() != null) {
            listaPersoane = (List<Persoana>)getArguments().getSerializable(PERSOANE_KEY);
        }
        if (getContext() != null) {
            PersoanaAdapter adapter = new PersoanaAdapter(getContext().getApplicationContext(),
                    R.layout.lv_persoane_view, listaPersoane, getLayoutInflater());
            lvPersoane.setAdapter(adapter);
            notifyInternalAdapterPersoane();
        }
    }

    private AdapterView.OnItemClickListener persoanaSelectEventListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               MainActivity.selectedPersoanaIndex = position;
            }
        };
    }

    private AdapterView.OnItemLongClickListener persoanaDeleteEventListener() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listaPersoane.remove(position);
                notifyInternalAdapterPersoane();
                return true;
            }
        };
    }


    public void notifyInternalAdapterPersoane() {
        ArrayAdapter adapter = (ArrayAdapter) lvPersoane.getAdapter();
        adapter.notifyDataSetChanged();
    }
}