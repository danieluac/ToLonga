package dev.danieluac.toknowing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Path;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import dev.danieluac.toknowing.Model.QuestionTable;

public class Home extends Activity implements View.OnClickListener {
    private Button BTN_OPCAO1, BTN_OPCAO2, BTN_OPCAO3;
    private Vibrator VIBRADOR;
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private TextView TXT_PERGUNTA, TXT_PONTOS, TXT_COUNTERDOWN;
    private int Contador;
    private int TotalContar;
    private List<QuestionTable> QuestionTableList;
    private QuestionTable CurrentQuestion;
    private QuestionTable Question;
    private int Pontos;
    private int Resposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.VIBRADOR = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        Vibrar(50);

        this.TXT_PERGUNTA = findViewById(R.id.txt_pergunta);
        this.TXT_PONTOS = findViewById(R.id.txt_pontos);
        this.TXT_COUNTERDOWN = findViewById(R.id.txt_counter_time);

        this.BTN_OPCAO1 = this.findViewById(R.id.btnR_opcao1);
        this.BTN_OPCAO1.setOnClickListener(this);

        this.BTN_OPCAO2 = this.findViewById(R.id.btnR_opcao2);
        this.BTN_OPCAO2.setOnClickListener(this);

        this.BTN_OPCAO3 = this.findViewById(R.id.btnR_opcao3);
        this.BTN_OPCAO3.setOnClickListener(this);

        this.Question = new QuestionTable(this);
        this.QuestionTableList = this.Question.getAllData();
        this.TotalContar = this.QuestionTableList.size();
        Collections.shuffle(this.QuestionTableList);
        this.InciaJogo();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnR_opcao1:
                countDownTimer.cancel();
                INICIA_CONTADOR_DE_RESPOSTA(this.Resposta, 1);
                break;
            case R.id.btnR_opcao2:
                countDownTimer.cancel();
                INICIA_CONTADOR_DE_RESPOSTA(this.Resposta, 2);
                break;
            case R.id.btnR_opcao3:
                countDownTimer.cancel();
                INICIA_CONTADOR_DE_RESPOSTA(this.Resposta, 3);
                break;
        }
    }
    private void Vibrar (long Timer)
    {
        this.VIBRADOR.vibrate(Timer);
    }
    private void INICIA_CONTADOR_DE_RESPOSTA(final int Res, final int Opc) {
        countDownTimer = new CountDownTimer(1000, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                RESPOSTA_CERTA_OU_ERRADA_TEMPO(Res, Opc);
            }

            @Override
            public void onFinish() {

                CONTINUA_O_JOGO_OU_TERMINA(Res, Opc);
            }
        }.start();
    }
    private void RESPOSTA_CERTA_OU_ERRADA_TEMPO(int Res, int Op) {
        this.BTN_OPCAO1.setEnabled(false);
        this.BTN_OPCAO2.setEnabled(false);
        this.BTN_OPCAO3.setEnabled(false);
        this.BTN_OPCAO1.setVisibility(View.INVISIBLE);
        this.BTN_OPCAO2.setVisibility(View.INVISIBLE);
        this.BTN_OPCAO3.setVisibility(View.INVISIBLE);
        if (Res == 1 && Op == 1) {
            this.BTN_OPCAO1.setVisibility(View.VISIBLE);
            Vibrar (200);
            this.BTN_OPCAO1.setBackgroundResource(R.color.clr_green_ansr);
        } else if (Res != 1 && Op == 1) {
            this.BTN_OPCAO1.setVisibility(View.VISIBLE);
            Vibrar (1000);
            this.BTN_OPCAO1.setBackgroundResource(R.color.clr_red_ansr);
        }
        if (Res == 2 && Op == 2) {
            this.BTN_OPCAO2.setVisibility(View.VISIBLE);
            Vibrar (200);
            this.BTN_OPCAO2.setBackgroundResource(R.color.clr_green_ansr);
        } else if (Res != 2 && Op == 2) {
            this.BTN_OPCAO2.setVisibility(View.VISIBLE);
            Vibrar (1000);
            this.BTN_OPCAO2.setBackgroundResource(R.color.clr_red_ansr);
        }
        if (Res == 3 && Op == 3) {
            this.BTN_OPCAO3.setVisibility(View.VISIBLE);
            Vibrar (200);
            this.BTN_OPCAO3.setBackgroundResource(R.color.clr_green_ansr);
        } else if (Res != 3 && Op == 3) {
            this.BTN_OPCAO3.setVisibility(View.VISIBLE);
            Vibrar (1000);
            this.BTN_OPCAO3.setBackgroundResource(R.color.clr_red_ansr);
        }
    }

    private void CONTINUA_O_JOGO_OU_TERMINA(int Res, int Op) {

        if (Res == 1 && Op == 1 || Res == 2 && Op == 2 || Res == 3 && Op == 3) {
            this.Pontos++;
            this.TXT_PONTOS.setText("PONTOS: " + this.Pontos);
            this.InciaJogo();
        } else
            FimJogo();
    }

    private void InciaJogo() {
        this.BTN_OPCAO1.setBackgroundResource(R.color.colorPrimaryDark);
        this.BTN_OPCAO2.setBackgroundResource(R.color.colorPrimaryDark);
        this.BTN_OPCAO3.setBackgroundResource(R.color.colorPrimaryDark);
        this.BTN_OPCAO1.setEnabled(true);
        this.BTN_OPCAO2.setEnabled(true);
        this.BTN_OPCAO3.setEnabled(true);
        this.BTN_OPCAO1.setVisibility(View.VISIBLE);
        this.BTN_OPCAO2.setVisibility(View.VISIBLE);
        this.BTN_OPCAO3.setVisibility(View.VISIBLE);

        if (this.Contador < this.TotalContar) {
            this.CurrentQuestion = this.QuestionTableList.get(this.Contador);

            this.TXT_PERGUNTA.setText(this.CurrentQuestion.getPergunta());
            this.BTN_OPCAO1.setText(this.CurrentQuestion.getOpcao1());
            this.BTN_OPCAO2.setText(this.CurrentQuestion.getOpcao2());
            this.BTN_OPCAO3.setText(this.CurrentQuestion.getOpcao3());
            this.Resposta = Integer.parseInt(this.CurrentQuestion.getResposta());
            this.Contador++;
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        } else {
            Vibrar(2000);
            Toast.makeText(Home.this, "Nivel Concluído... PARABÉNS", Toast.LENGTH_LONG).show();
            FimJogo();
        }
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                Vibrar(1500);
                FimJogo();
                updateCountDownText();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormated = String.format(Locale.getDefault(), " %02d:%02d ", minutes, seconds);

        this.TXT_COUNTERDOWN.setText(timeFormated);
        if (timeLeftInMillis < 10000) {
            int color = this.TXT_COUNTERDOWN.getResources().getColor(R.color.clr_red_ansr);
            this.TXT_COUNTERDOWN.setTextColor(color);
            Vibrar(50);
        } else {
            this.TXT_COUNTERDOWN.setTextColor(Color.WHITE);
        }
    }

    private void FimJogo() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null)
            countDownTimer.cancel();
    }
}
