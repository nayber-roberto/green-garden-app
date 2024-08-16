package com.example.greengarden;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UsoDeRecursosActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    private ImageButton btnAgua, btnEnergia, btnBack;
    private Button btnVerEstadisticas;
    private TextView textViewDate;
    private ImageButton btnFooterHome, btnFooterJardin, btnFooterRecursos, btnFooterActividades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usoderecursos);

        btnAgua = findViewById(R.id.btnAgua);
        btnEnergia = findViewById(R.id.btnEnergia);
        btnVerEstadisticas = findViewById(R.id.btnVerEstadisticas);
        btnBack = findViewById(R.id.btnBack);

        btnAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsoDeRecursosActivity.this, WaterActivity.class);
                startActivity(intent);
            }
        });

        btnEnergia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsoDeRecursosActivity.this, ElectricityActivity.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsoDeRecursosActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnVerEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsoDeRecursosActivity.this, DisplayDataActivity.class);
                startActivity(intent);
            }
        });

        textViewDate = findViewById(R.id.textViewDate);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", new Locale("es", "CO"));
        String currentDate = sdf.format(new Date());
        textViewDate.setText(currentDate);
        String formattedDate = currentDate.substring(0, 1).toUpperCase() + currentDate.substring(1).toLowerCase();
        textViewDate.setText(formattedDate);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Ocultar el título de la aplicación
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Cerrar el Drawer al seleccionar un elemento
                drawerLayout.closeDrawer(GravityCompat.START);

                // Determinar qué elemento del menú fue seleccionado
                int id = item.getItemId();

                // Realizar el Intent correspondiente
                if (id == R.id.nav_mi_jardin) {
                    Intent intent = new Intent(UsoDeRecursosActivity.this, MiJardinActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.nav_recursos) {
                    Intent intent = new Intent(UsoDeRecursosActivity.this, UsoDeRecursosActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.nav_actividades) {
                    Toast.makeText(UsoDeRecursosActivity.this, "Opción en Mantenimiento, intente más tarde", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.nav_proveedores) {
                    Intent intent = new Intent(UsoDeRecursosActivity.this, ProveedoresActivity.class);
                    startActivity(intent);
                    return true;
                }

                return false;
            }
        });

        //Inicializando Botones del Footer
        btnFooterHome = findViewById(R.id.btnFooterHome);
        btnFooterJardin = findViewById(R.id.btnFooterJardin);
        btnFooterRecursos = findViewById(R.id.btnFooterRecursos);
        btnFooterActividades =findViewById(R.id.btnFooterActividades);

        btnFooterHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsoDeRecursosActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnFooterJardin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsoDeRecursosActivity.this, MiJardinActivity.class);
                startActivity(intent);
            }
        });

        btnFooterRecursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsoDeRecursosActivity.this, UsoDeRecursosActivity.class);
                startActivity(intent);
            }
        });

        btnFooterActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UsoDeRecursosActivity.this, "Opción en mantenimiento, intenta más tarde.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
