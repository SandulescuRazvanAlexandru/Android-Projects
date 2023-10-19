package com.example.your_personal_agenda_app.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.your_personal_agenda_app.R;


public class PersoanaAdapter extends ArrayAdapter<Persoana> {

    private Context context;
    private List<Persoana> persoane;
    private LayoutInflater inflater;
    private int resource;
    private static DateConverter converter = new DateConverter();

    public PersoanaAdapter(@NonNull Context context, int resource,
                          @NonNull List<Persoana> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.persoane = objects;
        this.inflater = inflater;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Persoana persoana = persoane.get(position);
        if (persoana != null) {
            addNumePersoana(view, persoana.getNumePersoana());
            addDataPersoana(view, persoana.getDataPersoana());
            addGenPersoana(view, persoana.getGenPersoana());
            addVarstaPersoana(view, persoana.getVarstaPersoana());
            addGreutatePersoana(view, persoana.getGreutatePersoana());
            addInaltimePersoana(view, persoana.getInaltimePersoana());
        }
        return view;
    }

    private void addNumePersoana(View view, String numePersoana) {
        TextView textView = view.findViewById(R.id.tv_lv_persoane_view_nume);
        populateTextViewContent(textView, numePersoana);
    }

    private void addDataPersoana(View view, Date dataPersoana) {
        TextView textView = view.findViewById(R.id.tv_lv_persoane_view_data);
        String data = converter.toString(dataPersoana);
        populateTextViewContent(textView, data);
    }

    private void addGenPersoana(View view, GenPersoana genPersoana) {
        TextView textView = view.findViewById(R.id.tv_lv_persoane_view_gen);
        String gen = String.valueOf(genPersoana);
        populateTextViewContent(textView, gen);
    }

    private void addVarstaPersoana(View view, int varstaPersoana) {
        TextView textView = view.findViewById(R.id.tv_lv_persoane_view_varsta);
        String varsta = "";
        if(varstaPersoana>1)
        {
            varsta = context.getString(R.string.tv_lv_persoane_view_varsta_diferit1,varstaPersoana);
        }
        else
        {
            varsta = context.getString(R.string.tv_lv_persoane_view_varsta_egal1,varstaPersoana);
        }
        populateTextViewContent(textView, varsta);
    }

    private void addGreutatePersoana(View view, double greutatePersoana) {
        TextView textView = view.findViewById(R.id.tv_lv_persoane_view_greutate);
        String greutate = context.getString(R.string.tv_lv_persoane_view_greutate_text,greutatePersoana);
        populateTextViewContent(textView, greutate);
    }

    private void addInaltimePersoana(View view, double inaltimePersoana) {
        TextView textView = view.findViewById(R.id.tv_lv_persoane_view_inaltime);
        String inaltime = context.getString(R.string.tv_lv_persoane_view_inaltime_text,inaltimePersoana);
        populateTextViewContent(textView, inaltime);
    }

    private void populateTextViewContent(TextView textView, String value) {
        if (value != null && !value.trim().isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText(R.string.lv_row_view_no_content);
        }
    }
}