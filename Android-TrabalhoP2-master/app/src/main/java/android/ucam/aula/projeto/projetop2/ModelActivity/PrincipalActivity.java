package android.ucam.aula.projeto.projetop2.modelactivity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.ucam.aula.projeto.projetop1.R;
import android.ucam.aula.projeto.projetop2.dao.CriaBD;
import android.ucam.aula.projeto.projetop2.dao.DAO;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PrincipalActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private Button acessar;
    private Button limpar;
    private TextView resultado;
    private String loginBd;
    private String senhaBd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        adicionaMateria();
        adicionaAula();

        DAO primeiroUsuario = new DAO(getBaseContext());
        primeiroUsuario.insereUsuario("Admin", "123$abc");

        Cursor cursor = primeiroUsuario.consultaUsuario(1);

        loginBd = cursor.getString(cursor.getColumnIndexOrThrow(CriaBD.LOGIN));
        senhaBd = cursor.getString(cursor.getColumnIndexOrThrow(CriaBD.SENHA));

        email = (EditText) findViewById(R.id.editTextEmail);
        senha = (EditText) findViewById(R.id.editTextSenha);
        acessar = (Button) findViewById(R.id.buttonAcessar);
        limpar = (Button) findViewById(R.id.buttonLimpar);
        resultado = (TextView) findViewById(R.id.textViewResultado);

        acessar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(email.getText().toString().equalsIgnoreCase(loginBd) && senha.getText().toString().equalsIgnoreCase(senhaBd))
                {
                    Intent trocadorTela = new Intent(PrincipalActivity.this, TarefaActivity.class);
                    startActivity(trocadorTela);
                }else{
                    resultado.setText("Usuário ou senha inválidos.");
                }
            }
        });

        limpar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                email.setText("");
                senha.setText("");
                resultado.setText("");
            }
        });
    }

    private void adicionaMateria(){
        DAO materia = new DAO(getBaseContext());
        materia.insereMateria("Processo e qualidade de software");
        materia.insereMateria("Sistemas Operacionais II");
        materia.insereMateria("Desenvolvimento para dispositivos móveis");
    }

    private void adicionaAula(){
        DAO aula = new DAO(getBaseContext());
        aula.insereAula("Aula 1 - PQS",false,false,1);
        aula.insereAula("Aula 2 - PQS",false,false,1);
        aula.insereAula("Aula 3 - PQS",false,false,1);

        aula.insereAula("Aula 1 - SO",false,false,2);
        aula.insereAula("Aula 2 - SO",false,false,2);
        aula.insereAula("Aula 3 - SO",false,false,2);

        aula.insereAula("Aula 1 - DDM",false,false,3);
        aula.insereAula("Aula 2 - DDM",false,false,3);
        aula.insereAula("Aula 3 - DDM",false,false,3);
    }
}
