package dev.danieluac.toknowing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GoHome(View view) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void GoConfig(View view) {
        Intent intent = new Intent(this, CadastraPerguntas.class);
        startActivity(intent);
    }
}
