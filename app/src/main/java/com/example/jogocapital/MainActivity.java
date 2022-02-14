package com.example.jogocapital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
    }

    public MainActivity() {
        // Valores iniciais para os atributos do controller
        this.estadoAtual = "";
        this.capitalAtual = "";
        this.pontos = 0;
        this.rodada = 0;
    }

    public void randomState() {
        // Chamar essa função quando o programa for iniciado (ou caso queira criar um botão "começar jogo", colocar após essa ação)
        // e quando o usuário clicar em próxima pergunta
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

        // Faltou fazer TextView que mostra o estado para o usuário receber o estado gerado
    }

    public void Answer() {
        // Pegar valor da PlainText e inserir na váriavel "capitalEscolhida" (usando valor estático no momento)
        String capitalEscolhida = "Salvador";
        capitalEscolhida = capitalEscolhida.toLowerCase(Locale.ROOT);
        if (capitalEscolhida == this.capitalAtual) {
            this.pontos++;
        } else {
            // Informar o usuário que ele errou e a capital correta
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