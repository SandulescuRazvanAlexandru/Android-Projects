package eu.ase.ro.seminar12;

import android.os.Bundle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import eu.ase.ro.seminar12.util.ChartView;
import eu.ase.ro.seminar12.util.Coach;

public class ChartActivity extends AppCompatActivity {
    public static final String COACHES = "coaches_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Coach> coaches = (List<Coach>) getIntent().getSerializableExtra(COACHES);

        setContentView(new ChartView(getApplicationContext(), getSource(coaches)));
    }

    private Map<String, Integer> getSource(List<Coach> coaches) {
        if (coaches == null || coaches.isEmpty()) {
            return null;
        }
        Map<String, Integer> source = new HashMap<>();
        for (Coach coach : coaches) {
            if (source.containsKey(coach.getRole())) {
                Integer currentValue = source.get(coach.getRole());
                Integer newValue = (currentValue != null ? currentValue : 0) + 1;
                source.put(coach.getRole(), newValue);
            } else {
                source.put(coach.getRole(), 1);
            }
        }
        return source;
    }
}