package com.example.your_personal_agenda_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.your_personal_agenda_app.util.FelMancare;
import com.example.your_personal_agenda_app.util.Mancare;
import com.google.android.material.textfield.TextInputEditText;

public class AdaugareMancaruriActivity extends AppCompatActivity {

    public static final String SHARED_PREF_MANCARE_FILE_NAME = "mancareSharedPref";
    public static final String NEW_MANCARE_KEY = "new_mancare_key";

    public static final String CHKBX_STATE = "chkbxState";
    public static final String DESCRIERE_MANCARE = "descriereMancare";
    public static final String FEL_MANCARE = "felMancare";
    public static final String GRAMAJ_MANCARE = "gramajMancare";
    public static final String PROTEINE_MANCARE = "proteineMancare";
    public static final String CARBOHIDRATI_MANCARE = "carbohidratiMancare";
    public static final String GRASIMI_MANCARE = "grasimiMancare";

    private TextInputEditText tietDescriereMancare;
    private TextInputEditText tietGramajMancare;
    private Spinner spnFelMancare;
    private EditText etProteineMancare;
    private EditText etCarbohidratiMancare;
    private EditText etGrasimiMancare;
    private ProgressBar pbCaloriiMancare;
    private CheckBox chkbxPreferinteMancare;
    private Button btnSalvareMancare;
    private SharedPreferences preferences;

    private Intent intent;
    private Mancare mancare = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_mancaruri);
        initComponentsAddMancare();
        intent = getIntent();
        if (intent.hasExtra(NEW_MANCARE_KEY))
        {
            mancare = (Mancare) intent.getSerializableExtra(NEW_MANCARE_KEY);
            buildViewsFromMancare(mancare);
        }
        else
        {
            getMancareDetailsFromSharedPreference();
        }
    }

    private void initComponentsAddMancare() {
        tietDescriereMancare = findViewById(R.id.tiet_add_mancaruri_descriere);
        tietGramajMancare = findViewById(R.id.tiet_add_mancaruri_gramaj);
        spnFelMancare = findViewById(R.id.spn_add_mancaruri_fel);
        etProteineMancare = findViewById(R.id.et_add_mancaruri_proteine);
        etCarbohidratiMancare = findViewById(R.id.et_add_mancaruri_carbohidrati);
        etGrasimiMancare = findViewById(R.id.et_add_mancaruri_grasimi);
        pbCaloriiMancare = findViewById(R.id.pb_add_mancaruri_calorii);
        chkbxPreferinteMancare = findViewById(R.id.chkbx_add_mancaruri_preferinta);
        btnSalvareMancare = findViewById(R.id.btn_add_mancaruri_salvare);
        btnSalvareMancare.setOnClickListener(salvareMancareEventListener());
        populateFelMancareAdapter();

        preferences = getSharedPreferences(SHARED_PREF_MANCARE_FILE_NAME, MODE_PRIVATE);
    }

    private View.OnClickListener salvareMancareEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    Mancare mancare = createMancareFromView();
                    intent.putExtra(NEW_MANCARE_KEY,mancare);
                    setResult(RESULT_OK, intent);
                    if(chkbxPreferinteMancare.isChecked())
                    {
                        saveMancareDetailsToSharedPreference();
                    }
                    else
                    {
                        saveChkBxStateToSharedPreference();
                    }
                    finish();
                }
            }
        };
    }

    private Mancare createMancareFromView() {
        String descriereMancare = tietDescriereMancare.getText().toString();
        String fel =spnFelMancare.getSelectedItem().toString().toUpperCase();
        FelMancare felMancare = FelMancare.MIC_DEJUN;
        if(!(fel.equals("MIC-DEJUN")))
        {
            felMancare = FelMancare.valueOf(fel);
        }
        double gramajMancare = Double.parseDouble(tietGramajMancare.getText().toString());
        double proteinaMancare = 0;
        double carbohidratiMancare = 0;
        double grasimiMancare = 0;
        if(etProteineMancare.getText() != null && etProteineMancare.getText().toString().trim().length() != 0
                && !(etProteineMancare.getText().toString().trim().equals(getString(R.string.test_minus_sign)))
                && !(etProteineMancare.getText().toString().trim().equals(getString(R.string.test_dot_sign)))
                && !(etProteineMancare.getText().toString().trim().equals(getString(R.string.test_comma_sign)))
                && (etProteineMancare.getText().toString().trim().length() != 0 && Double.parseDouble(etProteineMancare.getText().toString().trim()) > 0))
        {
            proteinaMancare = Double.parseDouble(etProteineMancare.getText().toString());
        }
        if(etCarbohidratiMancare.getText() != null && etCarbohidratiMancare.getText().toString().trim().length() != 0
                && !(etCarbohidratiMancare.getText().toString().trim().equals(getString(R.string.test_minus_sign)))
                && !(etCarbohidratiMancare.getText().toString().trim().equals(getString(R.string.test_dot_sign)))
                && !(etCarbohidratiMancare.getText().toString().trim().equals(getString(R.string.test_comma_sign)))
                && (etCarbohidratiMancare.getText().toString().trim().length() != 0 && Double.parseDouble(etCarbohidratiMancare.getText().toString().trim()) > 0))
        {
            carbohidratiMancare = Double.parseDouble(etCarbohidratiMancare.getText().toString());
        }
        if(etGrasimiMancare.getText() != null && etGrasimiMancare.getText().toString().trim().length() != 0
                && !(etGrasimiMancare.getText().toString().trim().equals(getString(R.string.test_minus_sign)))
                && !(etGrasimiMancare.getText().toString().trim().equals(getString(R.string.test_dot_sign)))
                && !(etGrasimiMancare.getText().toString().trim().equals(getString(R.string.test_comma_sign)))
                && (etGrasimiMancare.getText().toString().trim().length() != 0 && Double.parseDouble(etGrasimiMancare.getText().toString().trim()) > 0))
        {
            grasimiMancare = Double.parseDouble(etGrasimiMancare.getText().toString());
        }
        double caloriiMancare = proteinaMancare*4 + carbohidratiMancare*4 + grasimiMancare*9;
        return new  Mancare(descriereMancare,felMancare,gramajMancare,proteinaMancare,carbohidratiMancare,grasimiMancare,caloriiMancare,MainActivity.selectedPersoanaIndex);
    }

    private void buildViewsFromMancare(Mancare mancare)
    {
        if (mancare == null)
        {
            return;
        }
        tietDescriereMancare.setText(mancare.getDescriereMancare());
        selectFelMancare(mancare);
        tietGramajMancare.setText(String.valueOf(mancare.getGramajMancare()));
        etProteineMancare.setText(String.valueOf(mancare.getProteineMancare()));
        etCarbohidratiMancare.setText(String.valueOf(mancare.getCarbohidratiMancare()));
        etGrasimiMancare.setText(String.valueOf(mancare.getGrasimiMancare()));
    }

    private void selectFelMancare(Mancare mancare) {
        ArrayAdapter adapter = (ArrayAdapter) spnFelMancare.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            String item = (String) adapter.getItem(i);
            if (item != null && !(item.equals("Mic-Dejun")) && item.toUpperCase().equals(String.valueOf(mancare.getFelMancare())))
            {
                spnFelMancare.setSelection(i);
                break;
            }
        }
    }

    private void getMancareDetailsFromSharedPreference() {
        int chkbxState = preferences.getInt(CHKBX_STATE, 0);
        if(chkbxState != 0)
        {
            String descriereMancare = preferences.getString(DESCRIERE_MANCARE, "");
            int felMancare = preferences.getInt(FEL_MANCARE,0);
            float gramajMancare = preferences.getFloat(GRAMAJ_MANCARE, 0.1f);
            float proteineMancare = preferences.getFloat(PROTEINE_MANCARE, 0.0f);
            float carbohidratiMancare = preferences.getFloat(CARBOHIDRATI_MANCARE, 0.0f);
            float grasimiMancare = preferences.getFloat(GRASIMI_MANCARE, 0.0f);
            chkbxPreferinteMancare.setChecked(true);
            tietDescriereMancare.setText(descriereMancare);
            spnFelMancare.setSelection(felMancare);
            tietGramajMancare.setText(String.valueOf(gramajMancare));
            etProteineMancare.setText(String.valueOf(proteineMancare));
            etCarbohidratiMancare.setText(String.valueOf(carbohidratiMancare));
            etGrasimiMancare.setText(String.valueOf(grasimiMancare));
        }
    }

    private void saveMancareDetailsToSharedPreference() {
        int chkbxState = 1;
        String descriereMancare = tietDescriereMancare.getText().toString();
        int felMancare = spnFelMancare.getSelectedItemPosition();
        float gramajMancare = Float.parseFloat(tietGramajMancare.getText().toString());
        float proteineMancare = 0;
        float carbohidratiMancare = 0;
        float grasimiMancare = 0;
        if(etProteineMancare.getText() != null && etProteineMancare.getText().toString().trim().length() != 0
                && !(etProteineMancare.getText().toString().trim().equals(getString(R.string.test_minus_sign)))
                && !(etProteineMancare.getText().toString().trim().equals(getString(R.string.test_dot_sign)))
                && !(etProteineMancare.getText().toString().trim().equals(getString(R.string.test_comma_sign)))
                && (etProteineMancare.getText().toString().trim().length() != 0 && Double.parseDouble(etProteineMancare.getText().toString().trim()) > 0))
        {
            proteineMancare = Float.parseFloat(etProteineMancare.getText().toString());
        }
        if(etCarbohidratiMancare.getText() != null && etCarbohidratiMancare.getText().toString().trim().length() != 0
                && !(etCarbohidratiMancare.getText().toString().trim().equals(getString(R.string.test_minus_sign)))
                && !(etCarbohidratiMancare.getText().toString().trim().equals(getString(R.string.test_dot_sign)))
                && !(etCarbohidratiMancare.getText().toString().trim().equals(getString(R.string.test_comma_sign)))
                && (etCarbohidratiMancare.getText().toString().trim().length() != 0 && Double.parseDouble(etCarbohidratiMancare.getText().toString().trim()) > 0))
        {
            carbohidratiMancare = Float.parseFloat(etCarbohidratiMancare.getText().toString());
        }
        if(etGrasimiMancare.getText() != null && etGrasimiMancare.getText().toString().trim().length() != 0
                && !(etGrasimiMancare.getText().toString().trim().equals(getString(R.string.test_minus_sign)))
                && !(etGrasimiMancare.getText().toString().trim().equals(getString(R.string.test_dot_sign)))
                && !(etGrasimiMancare.getText().toString().trim().equals(getString(R.string.test_comma_sign)))
                && (etGrasimiMancare.getText().toString().trim().length() != 0 && Double.parseDouble(etGrasimiMancare.getText().toString().trim()) > 0))
        {
            grasimiMancare = Float.parseFloat(etGrasimiMancare.getText().toString());
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CHKBX_STATE,chkbxState);
        editor.putString(DESCRIERE_MANCARE, descriereMancare);
        editor.putInt(FEL_MANCARE,felMancare);
        editor.putFloat(GRAMAJ_MANCARE, gramajMancare);
        editor.putFloat(PROTEINE_MANCARE, proteineMancare);
        editor.putFloat(CARBOHIDRATI_MANCARE,carbohidratiMancare);
        editor.putFloat(GRASIMI_MANCARE, grasimiMancare);
        editor.apply();
    }

    private void saveChkBxStateToSharedPreference(){
        int chkbxState = 0;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CHKBX_STATE,chkbxState);
        editor.apply();
    }

    private boolean validate() {
        if (tietDescriereMancare.getText() == null || tietDescriereMancare.getText().toString().trim().length() == 0) {
            Toast.makeText(getApplicationContext(), R.string.add_mancare_invalid_description_error, Toast.LENGTH_LONG).show();
            return false;
        }
        if (tietGramajMancare.getText() == null || tietGramajMancare.getText().toString().trim().length() == 0 || tietGramajMancare.getText().toString().trim().equals(getString(R.string.test_minus_sign))
                || tietGramajMancare.getText().toString().trim().equals(getString(R.string.test_dot_sign)) || tietGramajMancare.getText().toString().trim().equals(getString(R.string.test_comma_sign))
                || Double.parseDouble(tietGramajMancare.getText().toString().trim()) <= 0) {
            Toast.makeText(getApplicationContext(), R.string.add_mancare_invalid_weight_error, Toast.LENGTH_LONG).show();
            return false;
        }
        if (
                //===proteine===///
                (etProteineMancare.getText() == null || etProteineMancare.getText().toString().trim().length() == 0
                || etProteineMancare.getText().toString().trim().equals(getString(R.string.test_minus_sign))
                || etProteineMancare.getText().toString().trim().equals(getString(R.string.test_dot_sign))
                || etProteineMancare.getText().toString().trim().equals(getString(R.string.test_comma_sign))
                || (etProteineMancare.getText().toString().trim().length() != 0 && Double.parseDouble(etProteineMancare.getText().toString().trim()) <= 0))

            && //===carbohidrati===//
                    (etCarbohidratiMancare.getText() == null || etCarbohidratiMancare.getText().toString().trim().length() == 0
                    || etCarbohidratiMancare.getText().toString().trim().equals(getString(R.string.test_minus_sign))
                    || etCarbohidratiMancare.getText().toString().trim().equals(getString(R.string.test_dot_sign))
                    || etCarbohidratiMancare.getText().toString().trim().equals(getString(R.string.test_comma_sign))
                    || (etCarbohidratiMancare.getText().toString().trim().length() != 0 && Double.parseDouble(etCarbohidratiMancare.getText().toString().trim()) <= 0))

            && //===grasimi===//
                    (etGrasimiMancare.getText() == null || etGrasimiMancare.getText().toString().trim().length() == 0
                    || etGrasimiMancare.getText().toString().trim().equals(getString(R.string.test_minus_sign))
                    || etGrasimiMancare.getText().toString().trim().equals(getString(R.string.test_dot_sign))
                    || etGrasimiMancare.getText().toString().trim().equals(getString(R.string.test_comma_sign))
                    || (etGrasimiMancare.getText().toString().trim().length() != 0 && Double.parseDouble(etGrasimiMancare.getText().toString().trim()) <= 0))
        )
        {
                    Toast.makeText(getApplicationContext(), R.string.add_mancare_invalid_macronutrients_error, Toast.LENGTH_LONG).show();
                    return false;
        }
        return true;
    }

    private void populateFelMancareAdapter() {
        //populare spinner cu adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.spn_add_mancaruri_fel,
                android.R.layout.simple_spinner_dropdown_item);
        spnFelMancare.setAdapter(adapter);
    }

}