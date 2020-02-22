package com.example.cores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.logging.SocketHandler;

public class MainActivity extends Activity {

    private RadioGroup rGroup;
    private RadioButton rbSelected;
    private Button btnSalvar;
    private ConstraintLayout layout;
    private static final String ARQUIVO_PREFERENCIA = "ArqPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rGroup = (RadioGroup) findViewById(R.id.rGroup);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        layout = (ConstraintLayout) findViewById(R.id.layout);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idRadioButtonEscolhido = rGroup.getCheckedRadioButtonId();

                if(idRadioButtonEscolhido > 0){
                    rbSelected = (RadioButton) findViewById(idRadioButtonEscolhido);

                    SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String corEscolhida = rbSelected.getText().toString();
                    editor.putString("corEscolhida", corEscolhida);
                    editor.commit();

                    setBackgroud(corEscolhida);
                }
            }
        });

        //Recuperar o Valor
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if(sharedPreferences.contains("corEscolhida")){
            String corRecuperada = sharedPreferences.getString("corEscolhida", "Laranja");
            setBackgroud(corRecuperada);
        }

    }

    public void setBackgroud(String cor){
        if(cor.equals("Azul")){
            layout.setBackgroundColor(Color.parseColor("#495b7c"));
        }else if(cor.equals("Laranja")){
            layout.setBackgroundColor(Color.parseColor("#ffce26"));
        }else if(cor.equals("Verde")){
            layout.setBackgroundColor(Color.parseColor("#009670"));
        }
    }

}
