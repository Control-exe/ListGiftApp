package com.example.presentlist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.content.SharedPreferences;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private EditText recipientNameEditText;
    private NumberPicker valuePicker;
    private DatePicker deliveryDatePicker;
    private SeekBar progressSeekBar;
    private CheckBox boughtCheckBox;
    private Switch reminderSwitch;
    private RadioGroup occasionRadioGroup;
    private Button submitButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializando os componentes da interface
        recipientNameEditText = findViewById(R.id.recipientNameEditText);
        valuePicker = findViewById(R.id.valuePicker);
        deliveryDatePicker = findViewById(R.id.deliveryDatePicker);
        progressSeekBar = findViewById(R.id.progressSeekBar);
        boughtCheckBox = findViewById(R.id.boughtCheckBox);
        reminderSwitch = findViewById(R.id.reminderSwitch);
        occasionRadioGroup = findViewById(R.id.occasionRadioGroup);
        submitButton = findViewById(R.id.submitButton);

        // Inicializando o SharedPreferences
        sharedPreferences = getSharedPreferences("PresentPrefs", MODE_PRIVATE);

        // Ação do botão "Salvar presente"
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePresentData();
            }
        });
    }

    private void savePresentData() {
        // Obtendo valores da interface
        String recipientName = recipientNameEditText.getText().toString();
        int value = valuePicker.getValue();
        int day = deliveryDatePicker.getDayOfMonth();
        int month = deliveryDatePicker.getMonth();
        int year = deliveryDatePicker.getYear();
        int progress = progressSeekBar.getProgress();
        boolean isBought = boughtCheckBox.isChecked();
        boolean reminderEnabled = reminderSwitch.isChecked();

        // Identificando a ocasião
        int selectedOccasionId = occasionRadioGroup.getCheckedRadioButtonId();
        String occasion = "Outro"; // Valor padrão
        if (selectedOccasionId == R.id.birthdayRadioButton) {
            occasion = "Aniversário";
        } else if (selectedOccasionId == R.id.christmasRadioButton) {
            occasion = "Natal";
        }

        // Salvando os dados no SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("recipientName", recipientName);
        editor.putInt("value", value);
        editor.putInt("day", day);
        editor.putInt("month", month);
        editor.putInt("year", year);
        editor.putInt("progress", progress);
        editor.putBoolean("isBought", isBought);
        editor.putBoolean("reminderEnabled", reminderEnabled);
        editor.putString("occasion", occasion);
        editor.apply();

        // Exibindo um Toast de confirmação
        Toast.makeText(MainActivity.this, "Dados do presente salvos!", Toast.LENGTH_SHORT).show();
    }
}