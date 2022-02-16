package com.example.jogocapital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Estados e capitais ordenados através do índice
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

    private String estadoAtual; // Estado que sistema gerou para a pergunta atual
    private String capitalAtual; // Capital que é a resposta para o estado mostrado
    private int pontos; // Pontos que o usuário marcou
    private int rodada; // Rodada atual do jogo (máximo de 5)
    private ArrayList<Integer> indexBlacklist; // array com os index correspondentes aos estados já mostrados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView stateInput = findViewById(R.id.textViewRandomState);
        Random r = new Random();
        int index = r.nextInt(15) + 1;
        this.estadoAtual = estados[index];
        this.capitalAtual = capitais[index];
        stateInput.setText(estadoAtual);
    }

    public MainActivity() {
        // Valores iniciais para os atributos do controller
        this.estadoAtual = "";
        this.capitalAtual = "";
        this.pontos = 0;
        this.rodada = 0;
    }

    public void randomState(View view) {
        TextView stateInput = findViewById(R.id.textViewRandomState);
        Random r = new Random();
        int index = r.nextInt(15) + 1;
        this.estadoAtual = estados[index];
        this.capitalAtual = capitais[index];
        stateInput.setText(estadoAtual);

    }

    public void Answer(View view) {
        TextView outPoints = findViewById(R.id.textViewOutputPoints);
        TextView outAnswer = findViewById(R.id.textViewOutputCorrect);

        EditText inputCapital = findViewById(R.id.editTextInputCapital);
        String capitalEscolhida = inputCapital.getText().toString();

        if (capitalEscolhida == this.capitalAtual) {
            this.pontos++;
            outAnswer.setText("Você acertou!");
            outPoints.setText("Pontuação: " + (this.pontos*10));
        } else {
            outAnswer.setText("Você errou! A capital correta é " + this.capitalAtual);
            outPoints.setText("Pontuação: " + (this.pontos*10));
        }

        if (this.rodada < 4) { // Verificando se é a última rodada do jogo
            this.rodada++;
        } else {
            // Mostrar quantos pontos o usuário fez através do atributo "this.pontos"
            // Desabilitar os dois botões
            // (Opcional) Opção para o usuário reiniciar o jogo
        }
    }

}