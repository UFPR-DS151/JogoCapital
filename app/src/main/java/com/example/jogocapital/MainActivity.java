package com.example.jogocapital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private String[] estados = {
        "acre", "amapá", "bahia", "distrito federal", "goiás",
        "mato grosso", "minas gerais", "paraíba", "pernambuco", "rio de janeiro",
        "rio grande do sul", "roraima", "são paulo", "tocantins", "rio grande do norte"
    };
    private String[] capitais = {
        "rio branco", "macapá", "salvador", "brasília", "goiânia",
        "cuiabá", "belo horizonte", "joão pessoa", "recife", "rio de janeiro",
        "porto alegre", "boa vista", "são paulo", "palmas", "natal"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}