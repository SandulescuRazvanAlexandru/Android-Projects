package eu.ase.ro.seminar4;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configNavigation();
        navigationView = findViewById(R.id.main_nav_view);
        //atasare eveniment de click pe optiunile din meniul lateral
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.main_nav_home) {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.show_option, item.getTitle()),
                            Toast.LENGTH_LONG)
                            .show();
                } else if (item.getItemId() == R.id.nav_contact) {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.show_option, item.getTitle()),
                            Toast.LENGTH_LONG)
                            .show();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void configNavigation() {
        //initializare toolbar - bara de actiune
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //initializare drawer layout - panou meniu lateral
        drawerLayout = findViewById(R.id.drawer_layout);
        //legare meniu lateral cu bara actiune
        // + eveniment de deschidere
        //creare instanta utilitara
        ActionBarDrawerToggle actionBar =
                new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        //atasare eveniment
        drawerLayout.addDrawerListener(actionBar);
        //sincronizare actionBartoggle
        actionBar.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //atasare meniu clasic de activitatea principala
        getMenuInflater().inflate(R.menu.old_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.old_main_menu_add) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.show_option, item.getTitle()),
                    Toast.LENGTH_LONG)
                    .show();
        }
        return true;
    }
}