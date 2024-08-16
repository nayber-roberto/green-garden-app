package com.example.greengarden;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    TextView textViewTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewDate, textViewFechaDia, textViewFecha, textViewDiaTiempo1, textViewDiaTiempo2, textViewDiaTiempo3;
        Button buttonExplorarMiJardin, buttonExplorarRecursos, buttonExplorarActividades, buttonExplorarProveedores;
        ImageButton btnFooterHome, btnFooterJardin, btnFooterRecursos, btnFooterActividades;

        buttonExplorarMiJardin = findViewById(R.id.buttonExplorarMiJardin);
        buttonExplorarMiJardin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MiJardinActivity.class);
                startActivity(intent);
            }
        });

        buttonExplorarRecursos = findViewById(R.id.buttonExplorarRecursos);
        buttonExplorarRecursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UsoDeRecursosActivity.class);
                startActivity(intent);
            }
        });

        buttonExplorarActividades = findViewById(R.id.buttonExplorarActividades);
        buttonExplorarActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainActivity.this, ActividadesActivity.class);
                //startActivity(intent);
                Toast.makeText(MainActivity.this, "Opción en mantenimiento, intenta más tarde.", Toast.LENGTH_SHORT).show();
            }
        });

        buttonExplorarProveedores = findViewById(R.id.buttonExplorarProveedores);
        buttonExplorarProveedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProveedoresActivity.class);
                startActivity(intent);
            }
        });

        textViewDate = findViewById(R.id.textViewDate); //Jueves, 15 de Agosto de 2024
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", new Locale("es", "CO"));
        String currentDate = sdf.format(new Date());
        textViewDate.setText(currentDate);
        String formattedDate = currentDate.substring(0, 1).toUpperCase() + currentDate.substring(1).toLowerCase();
        textViewDate.setText(formattedDate);

        textViewFechaDia = findViewById(R.id.textViewFechaDia); //Dom Lun Mar Mie Jue Vie Sab
        SimpleDateFormat sdf_2= new SimpleDateFormat("EEE", new Locale("es", "CO"));
        String currentDate_2 = sdf_2.format(new Date());
        String formattedDate_2 = currentDate_2.substring(0, 1).toUpperCase() + currentDate_2.substring(1).toLowerCase();
        textViewFechaDia.setText(formattedDate_2);

        textViewFecha = findViewById(R.id.textViewFecha); //numero de dia de la fecha actual: 14, 15, 16, etc
        SimpleDateFormat sdf_3 = new SimpleDateFormat("d", new Locale("es", "CO"));
        String currentDate_3 = sdf_3.format(new Date());
        textViewFecha.setText(currentDate_3);

        textViewDiaTiempo1 = findViewById(R.id.textViewDiaTiempo1);
        textViewDiaTiempo2 = findViewById(R.id.textViewDiaTiempo2);
        textViewDiaTiempo3 = findViewById(R.id.textViewDiaTiempo3);

        // Formato para mostrar los días
        SimpleDateFormat sdf_cd = new SimpleDateFormat("EEE", new Locale("es", "CO"));

// Fecha actual
        Calendar calendar = Calendar.getInstance();

// Día actual (t1)
        String currentDate_t1 = sdf_cd.format(calendar.getTime()).toUpperCase();
        textViewDiaTiempo1.setText(currentDate_t1);

// Día siguiente (t2)
        calendar.add(Calendar.DAY_OF_YEAR, 1); // Añade un día
        String currentDate_t2 = sdf_cd.format(calendar.getTime()).toUpperCase();
        textViewDiaTiempo2.setText(currentDate_t2);

// Dos días después (t3)
        calendar.add(Calendar.DAY_OF_YEAR, 1); // Añade otro día
        String currentDate_t3 = sdf_cd.format(calendar.getTime()).toUpperCase();
        textViewDiaTiempo3.setText(currentDate_t3);

        textViewTip = findViewById(R.id.textViewTip);
        loadDailyTips();

        //TOOL BAR Y MENU DE NAVEGACION

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
                    Intent intent = new Intent(MainActivity.this, MiJardinActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.nav_recursos) {
                    Intent intent = new Intent(MainActivity.this, UsoDeRecursosActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.nav_actividades) {
                    Toast.makeText(MainActivity.this, "Opción en Mantenimiento, intente más tarde", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.nav_proveedores) {
                    Intent intent = new Intent(MainActivity.this, ProveedoresActivity.class);
                    startActivity(intent);
                    return true;
                }

                return false;
            }
        });

        TextView textViewBienvenidaUsuario = findViewById(R.id.textViewBienvenidaUsuario);

        String text = "¡Buenos días, Nayber!";
        SpannableString spannableString = new SpannableString(text);

        // ¡Buenos días en fuente tamaño 12 regular
        spannableString.setSpan(new RelativeSizeSpan(1.4f), 0, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.NORMAL), 0, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Nayber! en fuente tamaño 16 semibold
        spannableString.setSpan(new RelativeSizeSpan(1.63f), 13, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 13, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textViewBienvenidaUsuario.setText(spannableString);


        //Inicializando Botones del Footer
        btnFooterHome = findViewById(R.id.btnFooterHome);
        btnFooterJardin = findViewById(R.id.btnFooterJardin);
        btnFooterRecursos = findViewById(R.id.btnFooterRecursos);
        btnFooterActividades = findViewById(R.id.btnFooterActividades);

        btnFooterHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnFooterJardin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MiJardinActivity.class);
                startActivity(intent);
            }
        });

        btnFooterRecursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UsoDeRecursosActivity.class);
                startActivity(intent);
            }
        });

        btnFooterActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Opción en mantenimiento, intenta más tarde.", Toast.LENGTH_SHORT).show();
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

    //METODO PARA CARGAR LOS DAILY TIPS

    private void loadDailyTips() {
        SharedPreferences preferences = getSharedPreferences("DailyTips",MODE_PRIVATE);
        String dailyTips = preferences.getString("currentTip","Consejo no disponible");
        textViewTip.setText(dailyTips);
    }
}