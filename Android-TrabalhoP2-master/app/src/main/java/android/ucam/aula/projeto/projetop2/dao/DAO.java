package android.ucam.aula.projeto.projetop2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hugo on 09/12/2016.
 */

public class DAO {

    private SQLiteDatabase dbExecute;
    private CriaBD banco;
    String tmp1;

    public DAO(Context context){
        banco = new CriaBD(context);
    }

    //
    // Insere usuário admin no banco de dados
    //

    public void insereUsuario(String login, String senha){
        ContentValues valores;

        dbExecute = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBD.LOGIN, login);
        valores.put(CriaBD.SENHA, senha);

        dbExecute.insert(CriaBD.TABELA_USUARIO, null, valores);
        dbExecute.close();

    }

    //
    // Consulta usuário "admin" para login no sistema
    //

    public Cursor consultaUsuario(int id){
        Cursor cursor;
        String[] campos =  {banco.ID_USER,banco.LOGIN,banco.SENHA};
        String where = CriaBD.ID_USER + "=" + id;
        dbExecute = banco.getReadableDatabase();
        cursor = dbExecute.query(banco.TABELA_USUARIO, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        dbExecute.close();
        return cursor;
    }

    //
    // Insere aluno
    //

    public String insereAluno(String nome, String matricula, String email, String conta){
        ContentValues valores;
        long resultado;


        dbExecute = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBD.NOME_ALUNO, nome);
        valores.put(CriaBD.MATRICULA_ALUNO, matricula);
        valores.put(CriaBD.EMAIL_ALUNO, email);
        valores.put(CriaBD.CONTA_ALUNO, conta);

        resultado = dbExecute.insert(CriaBD.TABELA_ALUNO, null, valores);
        dbExecute.close();

        if (resultado ==-1)
            return "Não foi possível inserir o aluno";
        else
            return "Aluno "+ nome +" cadastrado com sucesso";

    }

    //
    // Insere aulas no sistema
    //

    public void insereAula(String nome, boolean exec, boolean lab, int idMateria){
        ContentValues valores;

        dbExecute = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBD.NOME_AULA, nome);
        valores.put(CriaBD.EXEC_AULA, exec);
        valores.put(CriaBD.LAB_AULA, lab);
        valores.put(CriaBD.ID_MATERIA_AULA, idMateria);

        dbExecute.insert(CriaBD.TABELA_AULA, null, valores);
        dbExecute.close();

    }

    //
    // Consulta aulas no sistema
    //

    public List<String> consultaAulas(int pos, String aula, int id){
        id++;
        pos++;
        List<String> labels = new ArrayList<String>();
        Cursor cursor;

        String[] campos =  {banco.ID_AULA,banco.NOME_AULA,banco.EXEC_AULA,banco.LAB_AULA,banco.ID_MATERIA_AULA};
        String where = CriaBD.ID_MATERIA_AULA + "=" + id;
        dbExecute = banco.getReadableDatabase();
        cursor = dbExecute.query(banco.TABELA_AULA, campos, where, null, null, null, null, "3");

        if (cursor.moveToNext()) {
            do {
                if(aula.equals(cursor.getString(1)) || pos == cursor.getInt(0)) {
                    labels.add(cursor.getString(0));
                    labels.add(cursor.getString(1));
                    labels.add(cursor.getString(2));
                    labels.add(cursor.getString(3));
                    labels.add(cursor.getString(4));
                }
            } while (cursor.moveToNext());

        }

        cursor.close();
        dbExecute.close();

        return labels;
    }

    ///
    /// Listar aulas
    ///

    public List<String> listaAulas(int idMateria){
        idMateria++;
        List<String> labels = new ArrayList<String>();
        Cursor cursor;

        String[] campos =  {banco.ID_AULA,banco.NOME_AULA};
        String where = CriaBD.ID_MATERIA_AULA + "=" + idMateria;
        dbExecute = banco.getReadableDatabase();
        cursor = dbExecute.query(banco.TABELA_AULA, campos, where, null, null, null, null, "3");

        if (cursor.moveToNext()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();
        dbExecute.close();

        return labels;
    }

    ///
    /// Altera a checkbox de exercicio
    ///

    public void updateCheckBoxExec(String id, int check){
        ContentValues valores;

        dbExecute = banco.getWritableDatabase();
        valores = new ContentValues();
        String where = CriaBD.ID_AULA + "=" + id;
        valores.put(CriaBD.EXEC_AULA, check);

        dbExecute.update(CriaBD.TABELA_AULA, valores, where, null);
        dbExecute.close();
    }

    ///
    /// Altera a checkbox de laboratorio
    ///

    public void updateCheckBoxLab(String id, int check){
        ContentValues valores;

        dbExecute = banco.getWritableDatabase();
        valores = new ContentValues();
        String where = CriaBD.ID_AULA + "=" + id;
        valores.put(CriaBD.LAB_AULA, check);

        dbExecute.update(CriaBD.TABELA_AULA, valores, where, null);
        dbExecute.close();
    }

    ///
    /// Insere materias no sistema
    ///

    public void insereMateria(String nome){
        ContentValues valores;

        dbExecute = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBD.NOME_MATERIA, nome);

        dbExecute.insert(CriaBD.TABELA_MATERIA, null, valores);
        dbExecute.close();
    }

    //
    // Consulta materias no sistema
    //

    public Cursor consultaMaterias(int id){
        Cursor cursor;
        String[] campos =  {banco.ID_MATERIA,banco.NOME_MATERIA};
        String where = CriaBD.ID_MATERIA + "=" + id;
        dbExecute = banco.getReadableDatabase();
        cursor = dbExecute.query(banco.TABELA_MATERIA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        dbExecute.close();
        return cursor;
    }

    ///
    /// Listar matérias
    ///

    public List<String> listaMaterias(){
        List<String> labels = new ArrayList<String>();
        Cursor cursor;

        String[] campos =  {banco.ID_MATERIA,banco.NOME_MATERIA};
        dbExecute = banco.getReadableDatabase();
        cursor = dbExecute.query(banco.TABELA_MATERIA, campos, null, null, null, null, null, "3");

        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();
        dbExecute.close();

        return labels;
    }

    ///
    /// Consulta desempenho
    ///

    public List<Integer> desempenho() {
        List<Integer> desemp = new ArrayList<Integer>();

        List<Integer> aux1 = new ArrayList<Integer>();
        List<Integer> aux2 = new ArrayList<Integer>();
        List<Integer> aux3 = new ArrayList<Integer>();

        Cursor cursorAulas;

        String[] camposAula = {banco.ID_AULA};
        dbExecute = banco.getReadableDatabase();

        String where = CriaBD.EXEC_AULA + "= 1";
        cursorAulas = dbExecute.query(banco.TABELA_AULA, camposAula, where, null, null, null, null);

        if (cursorAulas.moveToNext()) {
            do {
                aux1.add(cursorAulas.getInt(0));
            } while (cursorAulas.moveToNext());
        }

        where = CriaBD.LAB_AULA + "= 1";
        cursorAulas = dbExecute.query(banco.TABELA_AULA, camposAula, where, null, null, null, null);

        if (cursorAulas.moveToNext()) {
            do {
                aux2.add(cursorAulas.getInt(0));
            } while (cursorAulas.moveToNext());
        }

        cursorAulas = dbExecute.query(banco.TABELA_AULA, camposAula, null, null, null, null, null, "6");

        if (cursorAulas.moveToNext()) {
            do {
                aux3.add(cursorAulas.getInt(0));
            } while (cursorAulas.moveToNext());
        }

        desemp.add(aux3.size());
        desemp.add(aux1.size());
        desemp.add(aux2.size());

        cursorAulas.close();
        dbExecute.close();

        return desemp;
    }

}