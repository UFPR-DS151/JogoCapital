package com.example.jogocapital;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.Normalizer;
import java.util.ArrayList;
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
    }

    public void Begin(View view) {
        // Limpando variáveis do jogo
        this.estadoAtual = "";
        this.capitalAtual = "";
        this.pontos = 0;
        this.rodada = 0;
        this.indexBlacklist = new ArrayList<Integer>();

        // Pegando um estado aleatório
        TextView stateInput = findViewById(R.id.actualState);
        Random r = new Random();
        int index = r.nextInt(15);
        this.estadoAtual = estados[index];
        this.capitalAtual = capitais[index];
        this.indexBlacklist.add(index);
        stateInput.setText(estadoAtual);

        // Limpando o que deve sumir da tela
        findViewById(R.id.beginButton).setVisibility(View.GONE);
        findViewById(R.id.initialMessage).setVisibility(View.GONE);
        findViewById(R.id.msgFinal).setVisibility(View.GONE);
        findViewById(R.id.scoreFinal).setVisibility(View.GONE);
        findViewById(R.id.restartButton).setVisibility(View.GONE);

        // Mostrando o que deve aparecer
        findViewById(R.id.verifyButton).setVisibility(View.VISIBLE); // Botão de verificação
        findViewById(R.id.actualState).setVisibility(View.VISIBLE); // Estado

        // input de capital
        EditText input = (EditText) findViewById(R.id.inputCapital);
        input.setVisibility(View.VISIBLE);
        input.getText().clear();

        // Pontuação
        TextView score = findViewById(R.id.score);
        score.setVisibility(View.VISIBLE);
        score.setText("Pontuação: 0 pontos");

        // Resposta do jogo
        TextView resposta = findViewById(R.id.resposta);
        resposta.setVisibility(View.VISIBLE);
        resposta.setText("");
    }

    public void randomState(View view) {
        // apagando a capital informada pelo usuário
        EditText input = (EditText) findViewById(R.id.inputCapital);
        input.getText().clear();

        // Trocando o botão que é mostrado
        findViewById(R.id.nextQuestionButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.verifyButton).setVisibility(View.VISIBLE);

        // Limpando resposta
        TextView resposta = findViewById(R.id.resposta);
        resposta.setText("");

        // Encontrando estado para ser mostrado
        TextView stateInput = findViewById(R.id.actualState);
        Random r = new Random();
        do { // loop para repetir caso o programa gere um index já chamado anteriormente
            int index = r.nextInt(15);
            if (!this.indexBlacklist.contains(index)) {
                this.estadoAtual = estados[index];
                this.capitalAtual = capitais[index];
                this.indexBlacklist.add(index);
                break;
            } else {
                continue;
            }
        } while(true);
        stateInput.setText(estadoAtual);
    }

    public void Answer(View view) {
        // Escondendo o teclado após apertar para responder
        View viewK = this.getCurrentFocus();
        if (viewK != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(viewK.getWindowToken(), 0);
        }

        // Pegando o texto informado pelo usuário
        TextView outPoints = findViewById(R.id.score);
        TextView outAnswer = findViewById(R.id.resposta);

        EditText inputCapital = findViewById(R.id.inputCapital);
        String capitalEscolhida = inputCapital.getText().toString();

        // Retirando caracteres especiais
        capitalEscolhida = Normalizer.normalize(capitalEscolhida, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        String capitalComparacao = Normalizer.normalize(this.capitalAtual, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        // Comparando a capital correta com a informada pelo usuário
        if (capitalEscolhida.equalsIgnoreCase(capitalComparacao)) {
            this.pontos += 10;
            outAnswer.setText("Você acertou!");
            outPoints.setText("Pontuação: " + (this.pontos) + " pontos");
        } else {
            outAnswer.setText("Você errou! A capital correta é " + this.capitalAtual);
        }

        // Invertendo os botões mostrados
        findViewById(R.id.verifyButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.nextQuestionButton).setVisibility(View.VISIBLE);

        if (this.rodada < 4) { // Verificando se é a última rodada do jogo
            this.rodada++;
        } else {
            // Sumindo com itens desnecessários da tela
            findViewById(R.id.inputCapital).setVisibility(View.GONE);
            findViewById(R.id.nextQuestionButton).setVisibility(View.GONE);
            findViewById(R.id.verifyButton).setVisibility(View.GONE);
            findViewById(R.id.resposta).setVisibility(View.GONE);
            findViewById(R.id.score).setVisibility(View.GONE);
            findViewById(R.id.actualState).setVisibility(View.GONE);

            // Mostrando os itens do final do jogo
            TextView msg1 = findViewById(R.id.msgFinal);
            TextView msg2 = findViewById(R.id.scoreFinal);
            Button btn = findViewById(R.id.restartButton);

            msg1.setVisibility(View.VISIBLE);
            msg2.setVisibility(View.VISIBLE);
            btn.setVisibility(View.VISIBLE);

            msg2.setText("Você marcou "+ this.pontos +" pontos.");

        }
    }

}