package com.example.eliasreis.todolist.helper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.eliasreis.todolist.Tarefa;

import java.util.ArrayList;

/**
 * Created by eliasreis on 02/12/2017.
 */

public class SQL_Helper {
    private static SQL_Helper database1;
    SQLiteDatabase db;
    String dbName = "app1";
    String tableName = "tarefas ";
    String tableparametets = "(id INTEGER PRIMARY KEY AUTOINCREMENT, tarefa VARCHAR, feito INTEGER(1))";
    String TAG = "aula04sql";

    public static SQL_Helper getInstance() {
        if (database1 == null) {
            database1 = new SQL_Helper();

        }
        return database1;
    }

    public void criar(Activity sqlActivity) {
        db = sqlActivity.openOrCreateDatabase(dbName, sqlActivity.MODE_PRIVATE, null);
        String tableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + tableparametets;
        Log.d(TAG, "Query de criação: " + tableQuery);
        db.execSQL(tableQuery);
    }

    public void insert(Tarefa tarefas){
        ContentValues values = new ContentValues();

        values.put("tarefa", tarefas.tarefa);
        values.put("feito", tarefas.feito);

        try{
            db.insert(tableName, null, values);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(int codigo){
        try{
            String tableQueryUpdate = "UPDATE "+ tableName+ "SET feito = 1 WHERE id ="+codigo;
            db.execSQL(tableQueryUpdate);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Tarefa> carregarValores(){
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        String query;
        query = "SELECT * FROM " + tableName + "where feito = 0 ORDER BY id DESC;";
        Cursor myCursor = db.rawQuery(query, null);

        if (myCursor.moveToFirst()) {

            do {
                int id = myCursor.getInt(0);
                String tarefa = myCursor.getString(1);
                int feito = myCursor.getInt(2);

                Tarefa task = new Tarefa(id, tarefa, feito);
                tarefas.add(task);

                Log.d(TAG, String.valueOf(id));
                Log.d(TAG, tarefa);
                Log.d(TAG, String.valueOf(feito));
                Log.d(TAG, "------------------------");

            } while (myCursor.moveToNext());
        }
        return tarefas;
    }
}
