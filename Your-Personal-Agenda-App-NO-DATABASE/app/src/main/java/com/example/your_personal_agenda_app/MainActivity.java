package com.example.your_personal_agenda_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.your_personal_agenda_app.AsyncTask.AsyncTaskRunner;
import com.example.your_personal_agenda_app.AsyncTask.Callback;
import com.example.your_personal_agenda_app.Network.HTTPManager;
import com.example.your_personal_agenda_app.fragments.FragmentActivitati;
import com.example.your_personal_agenda_app.fragments.FragmentMancaruri;
import com.example.your_personal_agenda_app.fragments.FragmentPersoane;
import com.example.your_personal_agenda_app.util.Activitate;
import com.example.your_personal_agenda_app.util.Mancare;
import com.example.your_personal_agenda_app.util.Persoana;
import com.example.your_personal_agenda_app.util.PersoanaJSONParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_PERSOANA_REQUEST_CODE = 100;
    private static final int NEW_MANCARE_REQUEST_CODE = 200;
    private static final int NEW_ACTIVITATE_REQUEST_CODE = 300;
    private static final int UPDATE_PERSOANA_REQUEST_CODE = 400;
    private static final int UPDATE_ACTIVITATE_REQUEST_CODE = 500;
    private static final int UPDATE_MANCARURI_REQUEST_CODE = 600;

    public static final String PERSOANE_URL = "https://jsonkeeper.com/b/DMUX";
    public static int selectedPersoanaIndex = -1;
    public static int selectedMancareIndex = -1;
    public static int selectedActivitateIndex = -1;

    DrawerLayout drawerLayout;
    private FloatingActionButton fab_add_persoane;
    private FloatingActionButton fab_add_mancaruri;
    private FloatingActionButton fab_add_activitati;
    private FloatingActionButton fab_update_persoane;
    private FloatingActionButton fab_update_mancaruri;
    private FloatingActionButton fab_update_activitati;
    private NavigationView navigationView;
    private Fragment currentFragment;

    private List<Persoana> persoane = new ArrayList<>();
    private AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        getPersoaneFromHttp(savedInstanceState);
        configNavigation();
    }

    private void getPersoaneFromHttp(Bundle savedInstanceState) {
        Callable<String> asyncOperation = new HTTPManager(PERSOANE_URL);
        Callback<String> mainThreadOperation = receivePersoaneFromHTTP(savedInstanceState);
        asyncTaskRunner.executeAsync(asyncOperation, mainThreadOperation);
    }

    private Callback<String> receivePersoaneFromHTTP(final Bundle savedInstanceState) {
        return new Callback<String>() {
            @Override
            public void runResultOnUiThread(String result) {
                persoane.addAll(PersoanaJSONParser.fromJson(result));
                openDefaultFragment(savedInstanceState);
            }
        };
    }

    private void configNavigation() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void initComponents() {
        fab_add_persoane=findViewById(R.id.fab_main_activity_add_persoane);
        fab_add_mancaruri=findViewById(R.id.fab_main_activity_add_mancaruri);
        fab_add_activitati=findViewById(R.id.fab_main_activity_add_activitati);
        fab_update_persoane=findViewById(R.id.fab_main_activity_update_persoane);
        fab_update_mancaruri=findViewById(R.id.fab_main_activity_update_mancaruri);
        fab_update_activitati=findViewById(R.id.fab_main_activity_update_activitati);
        navigationView=findViewById(R.id.nav_view);
        fab_add_persoane.setOnClickListener(addNewPersoanaEventListener());
        fab_add_mancaruri.setOnClickListener(addNewMancareEventListener());
        fab_add_activitati.setOnClickListener(addNewActivitateEventListener());
        fab_update_persoane.setOnClickListener(updatePersoanaEventListener());
        fab_update_mancaruri.setOnClickListener(updateMancareEventListener());
        fab_update_activitati.setOnClickListener(updateActivitatiEventListener());
        navigationView.setNavigationItemSelectedListener(addNavigationMenuItemSelectedEvent());
    }

    private View.OnClickListener addNewPersoanaEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(currentFragment instanceof FragmentPersoane))
                {
                    Toast.makeText(getApplicationContext(), R.string.add_person_wrong_screen_message,Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), AdaugarePersoaneActivity.class);
                    startActivityForResult(intent,NEW_PERSOANA_REQUEST_CODE);
                }

            }
        };
    }

    private View.OnClickListener updatePersoanaEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPersoanaIndex == -1)
                {
                    Toast.makeText(getApplicationContext(), R.string.update_persoana_not_selected_message,Toast.LENGTH_LONG).show();
                }
                else if(!(currentFragment instanceof FragmentPersoane))
                {
                    Toast.makeText(getApplicationContext(), R.string.update_persoana_wrong_screen_message,Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), AdaugarePersoaneActivity.class);
                    intent.putExtra(AdaugarePersoaneActivity.NEW_PERSOANA_KEY, persoane.get(selectedPersoanaIndex));
                    startActivityForResult(intent, UPDATE_PERSOANA_REQUEST_CODE);
                }
            }
        };
    }

    private View.OnClickListener addNewMancareEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedPersoanaIndex == -1)
                {
                    Toast.makeText(getApplicationContext(), R.string.add_mancare_person_not_selected_message, Toast.LENGTH_LONG).show();
                }
                else if( !(currentFragment instanceof FragmentMancaruri))
                {
                    Toast.makeText(getApplicationContext(), R.string.add_mancare_wrong_screen_message,Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), AdaugareMancaruriActivity.class);
                    startActivityForResult(intent,NEW_MANCARE_REQUEST_CODE);
                }
            }
        };
    }

    private View.OnClickListener updateMancareEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMancareIndex == -1)
                {
                    Toast.makeText(getApplicationContext(), R.string.update_mancare_not_selected_message,Toast.LENGTH_LONG).show();
                }
                else if (!(currentFragment instanceof FragmentMancaruri))
                {
                    Toast.makeText(getApplicationContext(), R.string.update_mancare_wrong_screen_message,Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), AdaugareMancaruriActivity.class);
                    intent.putExtra(AdaugareMancaruriActivity.NEW_MANCARE_KEY, persoane.get(selectedPersoanaIndex).getListaMancaruriPersoana().get(selectedMancareIndex));
                    startActivityForResult(intent, UPDATE_MANCARURI_REQUEST_CODE);
                }
            }
        };
    }

    private View.OnClickListener addNewActivitateEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedPersoanaIndex == -1)
                {
                    Toast.makeText(getApplicationContext(), R.string.add_activitate_person_not_selected_message, Toast.LENGTH_LONG).show();
                }
                else if (!(currentFragment instanceof FragmentActivitati))
                {
                    Toast.makeText(getApplicationContext(), R.string.add_activitate_wrong_screen_message,Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), AdaugareActivitatiActivity.class);
                    startActivityForResult(intent,NEW_ACTIVITATE_REQUEST_CODE);
                }
            }
        };
    }

    private View.OnClickListener updateActivitatiEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedActivitateIndex == -1)
                {
                    Toast.makeText(getApplicationContext(), R.string.update_activitate_not_selected_message,Toast.LENGTH_LONG).show();
                }
                else if (!(currentFragment instanceof FragmentActivitati))
                {
                    Toast.makeText(getApplicationContext(), R.string.update_activitate_wrong_screen_message,Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), AdaugareActivitatiActivity.class);
                    intent.putExtra(AdaugareActivitatiActivity.NEW_ACTIVITATE_KEY, persoane.get(selectedPersoanaIndex).getListaActivitatiPersoana().get(selectedActivitateIndex));
                    startActivityForResult(intent, UPDATE_ACTIVITATE_REQUEST_CODE);
                }
            }
        };
    }

    private NavigationView.OnNavigationItemSelectedListener addNavigationMenuItemSelectedEvent() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.main_nav_persoane) {
                    currentFragment = FragmentPersoane.newInstance((ArrayList<Persoana>) persoane);
                    Toast.makeText(getApplicationContext(), getString(R.string.main_activity_option,item.getTitle()), Toast.LENGTH_LONG).show();
                    openFragment();
                } else if (item.getItemId() == R.id.main_nav_mancaruri) {
                    if(selectedPersoanaIndex == -1)
                    {
                        Toast.makeText(getApplicationContext(), R.string.mancaruri_person_not_selected_message, Toast.LENGTH_LONG).show();
                        openFragment();
                    }
                    else{
                    currentFragment = FragmentMancaruri.newInstance(persoane.get(selectedPersoanaIndex).getListaMancaruriPersoana());
                    Toast.makeText(getApplicationContext(), getString(R.string.main_activity_option,item.getTitle()), Toast.LENGTH_LONG).show();
                    openFragment();}
                } else if(item.getItemId() == R.id.main_nav_activitati)
                {
                    if(selectedPersoanaIndex == -1)
                    {
                        Toast.makeText(getApplicationContext(), R.string.activitati_person_not_selected_message, Toast.LENGTH_LONG).show();
                        openFragment();
                    }
                    else{
                        currentFragment = FragmentActivitati.newInstance(persoane.get(selectedPersoanaIndex).getListaActivitatiPersoana());
                        Toast.makeText(getApplicationContext(), getString(R.string.main_activity_option,item.getTitle()), Toast.LENGTH_LONG).show();
                        openFragment();}
                } else if (item.getItemId() == R.id.main_nav_rapoarte) {
                    Intent intent = new Intent(getApplicationContext(), RapoarteActivity.class);
                    startActivity(intent);
                } else if(item.getItemId() == R.id.main_nav_tutorial)
                {
                    Intent intent = new Intent(getApplicationContext(), TutorialActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.main_nav_detalii) {
                    Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                    startActivity(intent);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_PERSOANA_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Persoana persoana = (Persoana) data.getSerializableExtra(AdaugarePersoaneActivity.NEW_PERSOANA_KEY);
            if (persoana != null) {
                Toast.makeText(getApplicationContext(), R.string.new_person_added_message,
                        Toast.LENGTH_LONG).show();
                persoane.add(persoana);
                if (currentFragment instanceof FragmentPersoane) {
                    ((FragmentPersoane) currentFragment).notifyInternalAdapterPersoane();
                }
            }
        }
        else if(requestCode == UPDATE_PERSOANA_REQUEST_CODE && resultCode == RESULT_OK && data != null )
        {
            Persoana persoana = (Persoana) data.getSerializableExtra(AdaugarePersoaneActivity.NEW_PERSOANA_KEY);
            if(persoana != null)
            {
                Toast.makeText(getApplicationContext(),getString(R.string.update_persoana_success_message,persoana.getNumePersoana()),Toast.LENGTH_LONG).show();
                persoane.get(selectedPersoanaIndex).setNumePersoana(persoana.getNumePersoana());
                persoane.get(selectedPersoanaIndex).setVarstaPersoana(persoana.getVarstaPersoana());
                persoane.get(selectedPersoanaIndex).setGenPersoana(persoana.getGenPersoana());
                persoane.get(selectedPersoanaIndex).setGreutatePersoana(persoana.getGreutatePersoana());
                persoane.get(selectedPersoanaIndex).setInaltimePersoana(persoana.getInaltimePersoana());
                persoane.get(selectedPersoanaIndex).setDataPersoana(persoana.getDataPersoana());
                if (currentFragment instanceof FragmentPersoane) {
                    ((FragmentPersoane) currentFragment).notifyInternalAdapterPersoane();
                }
            }
        }
        if (requestCode == NEW_MANCARE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Mancare mancare = (Mancare) data.getSerializableExtra(AdaugareMancaruriActivity.NEW_MANCARE_KEY);
            if (mancare != null) {
                Toast.makeText(getApplicationContext(), getString(R.string.add_mancare_persoana_message,persoane.get(selectedPersoanaIndex).getNumePersoana()),
                        Toast.LENGTH_LONG).show();
                persoane.get(selectedPersoanaIndex).getListaMancaruriPersoana().add(mancare);
                if (currentFragment instanceof FragmentMancaruri) {
                    ((FragmentMancaruri) currentFragment).notifyInternalAdapterMancaruri();
                }
            }
        }
        else if (requestCode == UPDATE_MANCARURI_REQUEST_CODE && resultCode == RESULT_OK && data != null)
        {
            Mancare mancare = (Mancare) data.getSerializableExtra(AdaugareMancaruriActivity.NEW_MANCARE_KEY);
            if( mancare != null)
            {
                Toast.makeText(getApplicationContext(),getString(R.string.update_mancare_success_message,mancare.getDescriereMancare()),Toast.LENGTH_LONG).show();
                persoane.get(selectedPersoanaIndex).getListaMancaruriPersoana().get(selectedMancareIndex).setDescriereMancare(mancare.getDescriereMancare());
                persoane.get(selectedPersoanaIndex).getListaMancaruriPersoana().get(selectedMancareIndex).setGramajMancare(mancare.getGramajMancare());
                persoane.get(selectedPersoanaIndex).getListaMancaruriPersoana().get(selectedMancareIndex).setFelMancare(mancare.getFelMancare());
                persoane.get(selectedPersoanaIndex).getListaMancaruriPersoana().get(selectedMancareIndex).setProteineMancare(mancare.getProteineMancare());
                persoane.get(selectedPersoanaIndex).getListaMancaruriPersoana().get(selectedMancareIndex).setCarbohidratiMancare(mancare.getCarbohidratiMancare());
                persoane.get(selectedPersoanaIndex).getListaMancaruriPersoana().get(selectedMancareIndex).setGrasimiMancare(mancare.getGrasimiMancare());
                persoane.get(selectedPersoanaIndex).getListaMancaruriPersoana().get(selectedMancareIndex).setCaloriiMancare(mancare.getCaloriiMancare());
                if (currentFragment instanceof FragmentMancaruri) {
                    ((FragmentMancaruri) currentFragment).notifyInternalAdapterMancaruri();
                }
            }
        }
        if (requestCode == NEW_ACTIVITATE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Activitate activitate = (Activitate) data.getSerializableExtra(AdaugareActivitatiActivity.NEW_ACTIVITATE_KEY);
            if (activitate != null) {
                Toast.makeText(getApplicationContext(), getString(R.string.add_activitate_persoana_message,persoane.get(selectedPersoanaIndex).getNumePersoana()),
                        Toast.LENGTH_LONG).show();
                persoane.get(selectedPersoanaIndex).getListaActivitatiPersoana().add(activitate);
                if (currentFragment instanceof FragmentActivitati) {
                    ((FragmentActivitati) currentFragment).notifyInternalAdapterActivitati();
                }
            }
        }
        else if (requestCode == UPDATE_ACTIVITATE_REQUEST_CODE && resultCode == RESULT_OK && data != null)
        {
            Activitate activitate = (Activitate) data.getSerializableExtra(AdaugareActivitatiActivity.NEW_ACTIVITATE_KEY);
            if( activitate != null)
            {
                Toast.makeText(getApplicationContext(),getString(R.string.update_activitate_success_message,activitate.getTipActivitate()),Toast.LENGTH_LONG).show();
                persoane.get(selectedPersoanaIndex).getListaActivitatiPersoana().get(selectedActivitateIndex).setTipActivitate(activitate.getTipActivitate());
                persoane.get(selectedPersoanaIndex).getListaActivitatiPersoana().get(selectedActivitateIndex).setOreActivitate(activitate.getOreActivitate());
                persoane.get(selectedPersoanaIndex).getListaActivitatiPersoana().get(selectedActivitateIndex).setMinuteActivitate(activitate.getMinuteActivitate());
                persoane.get(selectedPersoanaIndex).getListaActivitatiPersoana().get(selectedActivitateIndex).setCaloriiArseActivitate(activitate.getCaloriiArseActivitate());
                if (currentFragment instanceof FragmentActivitati) {
                    ((FragmentActivitati) currentFragment).notifyInternalAdapterActivitati();
                }
            }
        }
    }

    private void openDefaultFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            currentFragment = FragmentPersoane.newInstance((ArrayList<Persoana>)persoane);
            openFragment();
            navigationView.setCheckedItem(R.id.main_nav_persoane);
        }
    }

    private void openFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_main_activity_lista, currentFragment)
                .commit();
    }

}