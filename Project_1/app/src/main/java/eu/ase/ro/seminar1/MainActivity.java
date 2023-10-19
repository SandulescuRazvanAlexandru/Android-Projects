package eu.ase.ro.seminar1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String COUNTER_KEY = "counter_key";
    TextView tvMessage;
    Button btnCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializare variabilele java cu id-urile componentelor vizuale din activity_main.xml (ne uitam dupa android:id)
        tvMessage = findViewById(R.id.main_message);
        btnCount = findViewById(R.id.btn_count);
        //atasare eveniment de click pe buton
        btnCount.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //preluam valoarea curenta din TextView
                int current = Integer.parseInt(tvMessage
                        .getText().toString());
                //modificam valoarea
                current++;
                //setam noua valoare pe TextView
                //ATENTIE: Niciodata pe proprietatile de tip text din componentele vizuale nu
                //setam valori numerice (int, long, float, double) fara o conversie la string.
                //Daca facem lucrul acesta Android o sa cauta o cheie din strings.xml
                // si o sa arunce eroare de executie atunci cand nu o gaseste
                tvMessage.setText(String.valueOf(current));
            }
        });
        Log.i("MainActivity_ex", "onCreate call");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity_ex", "onPause call");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity_ex", "onResume call");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity_ex", "onDestroy call");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //se apeleaza inainte de onDestroy
        //salvam informatiile pe care dorim sa le repunem pe ecranul dispozitivului mobil
        outState.putString(COUNTER_KEY, tvMessage.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //se apeleaza dupa onCreate
        //Bundle savedInstanceState este aceeasi variabila din memorie cu Bundle outState din onSaveInstanceState
        //se citeste informatia salvata in onSaveInstanceState
        tvMessage.setText(savedInstanceState.getString(COUNTER_KEY));
    }
}