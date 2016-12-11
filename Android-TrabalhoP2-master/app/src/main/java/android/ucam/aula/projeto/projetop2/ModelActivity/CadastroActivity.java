package android.ucam.aula.projeto.projetop2.modelactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.ucam.aula.projeto.projetop1.R;
import android.ucam.aula.projeto.projetop2.dao.DAO;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CadastroActivity extends AppCompatActivity {

    private EditText nome;
    private EditText matricula;
    private EditText email;
    private EditText contaGit;
    private Spinner listaMaterias;
    private TextView envio;

    private Button cadastrar;
    private Button limpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = (EditText) findViewById(R.id.editTextCadNome);
        matricula = (EditText) findViewById(R.id.editTextCadMatricula);
        email = (EditText) findViewById(R.id.editTextCadEmail);
        contaGit = (EditText) findViewById(R.id.editTextCadConta);
        envio = (TextView) findViewById(R.id.textViewEnvioDetalhe);

        listaMaterias = (Spinner) findViewById(R.id.spinnerCursos);

        cadastrar = (Button) findViewById(R.id.buttonCadCadastrar);
        limpar = (Button) findViewById(R.id.buttonCadLimpar);

        carregaMateriasBd();

        cadastrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                DAO insert = new DAO(getBaseContext());
                String nomeString = nome.getText().toString();
                String matriculaString = matricula.getText().toString();
                String emailString = email.getText().toString();
                String contaGitString = contaGit.getText().toString();

                if(nomeString.isEmpty() || matriculaString.isEmpty() || emailString.isEmpty() || contaGitString.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ops! Algum campo ficou vazio", Toast.LENGTH_LONG).show();
                }else{
                    String result = insert.insereAluno(nomeString,matriculaString,emailString,contaGitString);
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                    envio.setText("Enviar dados...");

                    envio.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {

                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("plain/text");
                            intent.putExtra(Intent.EXTRA_EMAIL,
                                    new String[]{email.getText().toString()});
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Dados do meu cadastro");
                            intent.putExtra(Intent.EXTRA_TEXT, "Olá " + nome.getText().toString() + ", tudo bem? Seu cadastro foi efetuado.\n\n Seguem os dados: \n" +
                                    "Nome: " + nome.getText().toString() + "\n" +
                                    "Matrícula: " + matricula.getText().toString() + "\n" +
                                    "E-mail: " + email.getText().toString() + "\n" +
                                    "Conta no GitHub: " + contaGit.getText().toString());
                            startActivity(Intent.createChooser(intent, "Escolha a forma de envio"));
                        }
                    });
                }
            }
        });

        limpar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                nome.setText("");
                matricula.setText("");
                email.setText("");
                contaGit.setText("");
            }
        });
    }

    private void carregaMateriasBd() {
        DAO db = new DAO(getApplicationContext());
        List<String> lables = db.listaMaterias();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        listaMaterias.setAdapter(dataAdapter);
    }
}

