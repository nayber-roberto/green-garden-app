package com.example.greengarden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MiJardinActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageButton btnBack;
    private ImageButton btnFooterHome, btnFooterJardin, btnFooterRecursos, btnFooterActividades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_jardin);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MiJardinActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

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
                    Intent intent = new Intent(MiJardinActivity.this, MiJardinActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.nav_recursos) {
                    Intent intent = new Intent(MiJardinActivity.this, UsoDeRecursosActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.nav_actividades) {
                    Toast.makeText(MiJardinActivity.this, "Opción en Mantenimiento, intente más tarde", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.nav_proveedores) {
                    Intent intent = new Intent(MiJardinActivity.this, ProveedoresActivity.class);
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
        btnFooterActividades = findViewById(R.id.btnFooterActividades);

        btnFooterHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MiJardinActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnFooterJardin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MiJardinActivity.this, MiJardinActivity.class);
                startActivity(intent);
            }
        });

        btnFooterRecursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MiJardinActivity.this, UsoDeRecursosActivity.class);
                startActivity(intent);
            }
        });

        btnFooterActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MiJardinActivity.this, "Opción en mantenimiento, intenta más tarde.", Toast.LENGTH_SHORT).show();
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