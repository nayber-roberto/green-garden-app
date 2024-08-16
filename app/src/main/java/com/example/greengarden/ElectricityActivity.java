package com.example.greengarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ElectricityActivity extends AppCompatActivity {

    private EditText editTextKilowatt, editTextMonth, editTextPrice;
    private Button buttonSave;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);

        editTextKilowatt = findViewById(R.id.editTextKilowatt);
        editTextMonth = findViewById(R.id.editTextMonth);
        editTextPrice = findViewById(R.id.editTextPrice);
        buttonSave = findViewById(R.id.buttonSave);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElectricityActivity.this, UsoDeRecursosActivity.class);
                startActivity(intent);
            }
        });

        buttonSave.setOnClickListener(v -> saveElectricityData());
    }
    private void saveElectricityData() {
        String kilowatt = editTextKilowatt.getText().toString();
        String month = editTextMonth.getText().toString();
        String price = editTextPrice.getText().toString();

        if(kilowatt.isEmpty() || month.isEmpty() || price.isEmpty()) {
            Toast.makeText(this, "Por favor complete los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        //Guardar los datos en SharePreference
        SharedPreferences preferences = getSharedPreferences("ElectricityData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        int index = preferences.getInt("index",0);

        editor.putString("kilowatt" + index, kilowatt);
        editor.putString("month" + index, month);
        editor.putString("price" + index, price);

        editor.putInt("index", index+1);
        editor.apply();

        Toast.makeText(this,"Datos guardados",Toast.LENGTH_SHORT).show();
        finish();
    }
}