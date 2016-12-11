package android.ucam.aula.projeto.projetop2.modelactivity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.ucam.aula.projeto.projetop1.R;
import android.ucam.aula.projeto.projetop2.dao.CriaBD;
import android.ucam.aula.projeto.projetop2.dao.DAO;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TarefaActivity extends AppCompatActivity {

    private Spinner listaMaterias;
    private Spinner listaAulas;
    private TextView aula;
    private CheckBox exec;
    private CheckBox lab;
    private ProgressBar progressoBar;
    private Button progresso;
    private Button cadastro;
    private Button sair;

    private int materiaPosition;
    private boolean execCheck;
    private boolean labCheck;

    private List<String> listaMateria = new ArrayList<>();
    private List<String> listaAula = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        listaMaterias = (Spinner) findViewById(R.id.spinnerCurso);
        listaAulas = (Spinner) findViewById(R.id.spinnerAula);
        aula = (TextView) findViewById(R.id.textViewAula);
        exec = (CheckBox) findViewById(R.id.checkBoxExec);
        lab = (CheckBox) findViewById(R.id.checkBoxLab);
        progressoBar = (ProgressBar) findViewById(R.id.progressBarTarefas);
        progresso = (Button) findViewById(R.id.buttonProgresso);
        cadastro = (Button) findViewById(R.id.buttonCadastro);
        sair = (Button) findViewById(R.id.buttonSair);

        carregaMateriasBd();

        listaMaterias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                materiaPosition = position;
                carregaAulasBd(position);
                exec.setChecked(false);
                lab.setChecked(false);

                exec.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        execCheck = exec.isChecked();
                        if(!execCheck){
                            DAO db = new DAO(getApplicationContext());
                            db.updateCheckBoxExec(listaAula.get(0),0);
                            preencheBarra();
                        }else{
                            DAO db = new DAO(getApplicationContext());
                            db.updateCheckBoxExec(listaAula.get(0),1);
                            preencheBarra();
                        }

                    }
                });

                lab.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        labCheck = lab.isChecked();
                        if(!labCheck){
                            DAO db = new DAO(getApplicationContext());
                            db.updateCheckBoxLab(listaAula.get(0),0);
                            preencheBarra();
                        }else{
                            DAO db = new DAO(getApplicationContext());
                            db.updateCheckBoxLab(listaAula.get(0),1);
                            preencheBarra();
                        }
                    }
                });

             listaAulas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                 @Override
                 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                     String aulaSelecionada = (String) parent.getItemAtPosition(position);
                     exec.setChecked(false);
                     lab.setChecked(false);

                     aula.setText(aulaSelecionada.toString());

                     DAO db = new DAO(getApplicationContext());

                     listaAula = db.consultaAulas(position, aulaSelecionada, materiaPosition);

                     if(listaAula.get(2).equals("0") && listaAula.get(3).equals("0")){
                         exec.setChecked(false);
                         lab.setChecked(false);
                         progressoBar.setProgress(0);
                     }else if(listaAula.get(2).equals("1") && listaAula.get(3).equals("0")){
                         exec.setChecked(true);
                         lab.setChecked(false);
                         progressoBar.setProgress(50);
                     }else if(listaAula.get(3).equals("1") && listaAula.get(2).equals("0")){
                         lab.setChecked(true);
                         exec.setChecked(false);
                         progressoBar.setProgress(50);
                     }else if(listaAula.get(2).equals("1") && listaAula.get(3).equals("1")){
                         exec.setChecked(true);
                         lab.setChecked(true);
                         progressoBar.setProgress(100);
                     }

                     exec.setOnClickListener(new View.OnClickListener() {
                         public void onClick(View v) {
                             execCheck = exec.isChecked();
                             if(!execCheck){
                                 DAO db = new DAO(getApplicationContext());
                                 db.updateCheckBoxExec(listaAula.get(0),0);
                                 preencheBarra();
                             }else{
                                 DAO db = new DAO(getApplicationContext());
                                 db.updateCheckBoxExec(listaAula.get(0),1);
                                 preencheBarra();
                             }

                         }
                     });

                     lab.setOnClickListener(new View.OnClickListener() {
                         public void onClick(View v) {
                             labCheck = lab.isChecked();
                             if(!labCheck){
                                 DAO db = new DAO(getApplicationContext());
                                 db.updateCheckBoxLab(listaAula.get(0),0);
                                 preencheBarra();
                             }else{
                                 DAO db = new DAO(getApplicationContext());
                                 db.updateCheckBoxLab(listaAula.get(0),1);
                                 preencheBarra();
                             }
                         }
                     });
                 }

                 @Override
                 public void onNothingSelected(AdapterView<?> parent) {

                 }
             });
         }

         public void onNothingSelected(AdapterView<?> parent) {}
        });

        exec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                execCheck = exec.isChecked();
                if(!execCheck){
                    DAO db = new DAO(getApplicationContext());
                    db.updateCheckBoxExec(listaAula.get(0),0);
                    preencheBarra();
                }else{
                    DAO db = new DAO(getApplicationContext());
                    db.updateCheckBoxExec(listaAula.get(0),1);
                    preencheBarra();
                }

            }
        });

        lab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                labCheck = lab.isChecked();
                if(!labCheck){
                    DAO db = new DAO(getApplicationContext());
                    db.updateCheckBoxLab(listaAula.get(0),0);
                    preencheBarra();
                }else{
                    DAO db = new DAO(getApplicationContext());
                    db.updateCheckBoxLab(listaAula.get(0),1);
                    preencheBarra();
                }
            }
        });

        progresso.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent trocadorTela = new Intent(TarefaActivity.this, ProgressoActivity.class);
                startActivity(trocadorTela);
            }
        });

        cadastro.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent trocadorTela = new Intent(TarefaActivity.this, CadastroActivity.class);
                startActivity(trocadorTela);
            }
        });

        sair.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent trocadorTela = new Intent(TarefaActivity.this, PrincipalActivity.class);
                startActivity(trocadorTela);
            }
        });

    }
        private void preencheBarra(){
            if(exec.isChecked() && lab.isChecked()){
                progressoBar.setProgress(100);
            }else if(exec.isChecked()){
                progressoBar.setProgress(50);
            }else if(lab.isChecked()){
                progressoBar.setProgress(50);
            }else{
                progressoBar.setProgress(0);
            }
        }



    private List<String> carregaMateriasBd() {
        DAO db = new DAO(getApplicationContext());
        listaMateria = db.listaMaterias();
        List<String> lables = db.listaMaterias();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        listaMaterias.setAdapter(dataAdapter);

        return lables;
    }

    private List<String> carregaAulasBd(int pos) {
        DAO db = new DAO(getApplicationContext());
        listaAula = db.listaAulas(pos);
        List<String> lables = db.listaAulas(pos);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        listaAulas.setAdapter(dataAdapter);

        return lables;
    }

}