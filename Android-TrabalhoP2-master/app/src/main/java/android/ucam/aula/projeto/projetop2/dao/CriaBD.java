package android.ucam.aula.projeto.projetop2.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hugo on 09/12/2016.
 */

public class CriaBD extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "TarefasBD";
    private static final int VERSAO = 1;

    public static final String TABELA_ALUNO = "aluno";
    public static final String ID_ALUNO = "_id";
    public static final String NOME_ALUNO = "nome";
    public static final String MATRICULA_ALUNO = "matricula";
    public static final String EMAIL_ALUNO = "email";
    public static final String CONTA_ALUNO = "conta";

    public static final String TABELA_MATERIA = "materia";
    public static final String ID_MATERIA = "_id";
    public static final String NOME_MATERIA = "materia";

    public static final String TABELA_AULA = "aula";
    public static final String ID_AULA = "_id";
    public static final String NOME_AULA = "aula";
    public static final String EXEC_AULA = "execOk";
    public static final String LAB_AULA = "labOk";
    public static final String ID_MATERIA_AULA = "id_materia";

    public static final String TABELA_USUARIO = "usuario";
    public static final String ID_USER = "_id";
    public static final String LOGIN = "login";
    public static final String SENHA = "senha";


    public CriaBD(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlAluno = "CREATE TABLE "+TABELA_ALUNO+" ("
                + ID_ALUNO + " integer primary key autoincrement, "
                + NOME_ALUNO + " text,"
                + MATRICULA_ALUNO + " text,"
                + EMAIL_ALUNO + " text,"
                + CONTA_ALUNO + " text"
                +")";

        db.execSQL(sqlAluno);

        String sqlMateria = "CREATE TABLE "+TABELA_MATERIA+" ("
                + ID_MATERIA + " integer primary key autoincrement, "
                + NOME_MATERIA + " text"
                +")";

        db.execSQL(sqlMateria);

        String sqlAula = "CREATE TABLE "+TABELA_AULA+" ("
                + ID_AULA + " integer primary key autoincrement, "
                + NOME_AULA + " text,"
                + EXEC_AULA + " boolean,"
                + LAB_AULA + " boolean,"
                + ID_MATERIA_AULA + " int"
                +")";

        db.execSQL(sqlAula);

        String sqlUsuario = "CREATE TABLE "+TABELA_USUARIO+" ("
                + ID_USER + " integer primary key autoincrement, "
                + LOGIN + " text,"
                + SENHA + " text"
                +")";

        db.execSQL(sqlUsuario);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_ALUNO
                + "; DROP TABLE IF EXISTS " + TABELA_USUARIO + "; DROP TABLE IF EXISTS " + TABELA_MATERIA + "; DROP TABLE IF EXISTS " + TABELA_AULA + ";");
        onCreate(db);
    }
}