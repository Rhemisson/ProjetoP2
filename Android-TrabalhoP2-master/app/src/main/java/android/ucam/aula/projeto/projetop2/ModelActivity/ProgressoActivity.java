package android.ucam.aula.projeto.projetop2.modelactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.ucam.aula.projeto.projetop1.R;
import android.ucam.aula.projeto.projetop2.dao.CriaBD;
import android.ucam.aula.projeto.projetop2.dao.DAO;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class ProgressoActivity extends AppCompatActivity {

    private ImageButton voltar;
    private ProgressBar progAulas;
    private ProgressBar progExecs;
    private ProgressBar progLabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progresso);

        DAO db = new DAO(getApplicationContext());

        voltar = (ImageButton) findViewById(R.id.ImagemButtonVoltar);
        progAulas = (ProgressBar) findViewById(R.id.ProgressBarProgAulas);
        progExecs = (ProgressBar) findViewById(R.id.ProgressBarProgExecs);
        progLabs = (ProgressBar) findViewById(R.id.ProgressBarProgLabs);

        progAulas.setProgress(db.desempenho().get(0));
        progExecs.setProgress(db.desempenho().get(1));
        progLabs.setProgress(db.desempenho().get(2));

        voltar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent trocadorTela = new Intent(ProgressoActivity.this, TarefaActivity.class);
                startActivity(trocadorTela);
            }
        });
    }
}
