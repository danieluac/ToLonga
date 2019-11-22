package dev.danieluac.toknowing;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import dev.danieluac.toknowing.Model.QuestionTable;

public class CadastraPerguntas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_perguntas);
    }

    public void CadPerguntas(View view) {
        EditText pergunta = this.findViewById(R.id.Edt_Pergunta);
        EditText opcao1 = this.findViewById(R.id.Edt_Opcao1);
        EditText opcao2 = this.findViewById(R.id.Edt_Opcao2);
        EditText opcao3 = this.findViewById(R.id.Edt_Opcao3);
        EditText resposta = this.findViewById(R.id.Edt_Resposta);

        QuestionTable questionTable = new QuestionTable(this);

        questionTable.setPergunta(pergunta.getText().toString());
        questionTable.setOpcao1(opcao1.getText().toString());
        questionTable.setOpcao2(opcao2.getText().toString());
        questionTable.setOpcao3(opcao3.getText().toString());
        questionTable.setResposta(resposta.getText().toString());
        if (questionTable.InsertData() == true) {
            pergunta.setText(null);
            opcao1.setText(null);
            opcao2.setText(null);
            opcao3.setText(null);
            resposta.setText(null);
            Toast.makeText(this, "Cadastrado com sucesso...", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Não adastrado...", Toast.LENGTH_SHORT).show();
    }

    public void ShowPerguntas(View view) {
        QuestionTable questionTable = new QuestionTable(this);

        List<QuestionTable> questionTableList = questionTable.getAllData();

        QuestionTable question;
        int contado = (questionTableList.size());
        int contador = 0;

        if (contado == 0)
            DisplaySmsShow("Tabela Vazia: ", " Tabela está vazia...");
        else {
            StringBuffer buffer = new StringBuffer();

            while (contado > contador) {
                question = questionTableList.get(contador);
                buffer.append("ID: " + question.getId() + "\n");
                buffer.append("PERGUNTA: " + question.getPergunta() + "\n");
                buffer.append(" ...................... \n");
                buffer.append("OPÇÃO 1: " + question.getOpcao1() + "\n.....\n");
                buffer.append("OPÇÃO 2: " + question.getOpcao2() + "\n.....\n");
                buffer.append("OPÇÃO 3: " + question.getOpcao3() + "\n.....\n");
                buffer.append("RESPOSTA: " + question.getResposta() + "\n ............................................................ \n");

                contador++;
            }
            DisplaySmsShow("Resultado da tabela: ", buffer.toString());
        }
    }

    public void DisplaySmsShow(String Title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();
    }
}
