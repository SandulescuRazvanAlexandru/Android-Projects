package eu.ase.ro.seminar2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import static android.widget.ArrayAdapter.createFromResource;

public class AddActivity extends AppCompatActivity {
    private Spinner spnFaculties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //initializare spinner cu componenta vizuala din activity_add.xml
        spnFaculties = findViewById(R.id.spn_add_faculties);
        //creare adapter care asigura convertirea unei colectii de string-uri
        // la o colectie de view (TextView)
        ArrayAdapter<CharSequence> adapter = createFromResource(getApplicationContext(),
                R.array.add_faculties,
                android.R.layout.simple_spinner_dropdown_item);
        //atasam adapter catre spinner
        spnFaculties.setAdapter(adapter);
    }
}