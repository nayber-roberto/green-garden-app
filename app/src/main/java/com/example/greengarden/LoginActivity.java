package com.example.greengarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button btnIngresar, btnRegistrarse;

    private UserManager userManager;

    private View view_container_login;

    private String[] dailyTips = {
            "Ahorra energía apagando las luces que no necesitas.",
            "Desconecta los electrodomésticos que no estás usando.",
            "Utiliza bombillas LED para reducir el consumo de energía.",
            "Apaga tu computadora cuando no la estés usando.",
            "Aprovecha la luz natural durante el día.",
            "Mantén el refrigerador bien cerrado para ahorrar energía.",
            "Usa el aire acondicionado con moderación.",
            "Lava tu ropa con agua fría para ahorrar energía.",
            "Revisa tus aparatos eléctricos para evitar fugas de energía.",
            "Plancha tu ropa en una sola sesión para ahorrar energía."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIngresar = findViewById(R.id.button_ingresar);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        userManager = new UserManager(this);

        SharedPreferences preferences = getSharedPreferences("DailyTips", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        boolean isInitialized = preferences.getBoolean("isInitialized", false);
        if (!isInitialized) {
            for (int i = 0; i < dailyTips.length; i++) {
                editor.putString("tip_" + i, dailyTips[i]);
            }
            editor.putBoolean("isInitialized", true);
            editor.apply();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", new Locale("es", "CO"));
        String currentDate = sdf.format(new Date());

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                if(userManager.loginUser(email,password)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                    String saveDate = preferences.getString("saveDate", "");
                    if (!currentDate.equals(saveDate)) {
                        Random random = new Random();
                        int randomIndex = random.nextInt(dailyTips.length);
                        String newTip = dailyTips[randomIndex];
                        editor.putString("currentTip", newTip);
                        editor.putString("saveDate", currentDate);
                        editor.apply();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Email o Password incorrecto", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

}