package com.example.eliasreis.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eliasreis.todolist.helper.SQL_Helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Button btnAdicionar;
    private ListView listView;
    private EditText textoTarefa;
    public SQLiteDatabase bd;
    private ArrayAdapter<String> itenAdapter;
    private ArrayList<String> itens;
    private ArrayAdapter<Integer> itenCodAdapter;
    private ArrayList<Integer> itensCod;
    private TextView txtData;
    MeuAdapter meuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQL_Helper.getInstance().criar(this);
        try {
            RecyclerView myRecycler = (RecyclerView) findViewById(R.id.myRecycler);
            btnAdicionar = (Button) findViewById(R.id.buttonAdicionar);
            meuAdapter = new MeuAdapter(this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            myRecycler.setAdapter(meuAdapter);
            myRecycler.setLayoutManager(layoutManager);


            textoTarefa = (EditText) findViewById(R.id.editTextTarefa);
            RefreshList();
            meuAdapter.setTarefas();
            btnAdicionar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String texto = textoTarefa.getText().toString();
                    if (texto.equals("")) {
                        Toast.makeText(MainActivity.this, "Por favor, digite uma tarefa",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        AdicionaTarefa(texto);
                    }
                }
            });

            Date data = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormat = format.format(data);
            txtData = (TextView) findViewById(R.id.textViewData);
            txtData.setText(dataFormat);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void AdicionaTarefa (String tarefa){
        try {
            Tarefa adiciona = new Tarefa(0, tarefa, 0);
            SQL_Helper.getInstance().insert(adiciona);
            RefreshList();
            textoTarefa.setText("");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void RefreshList(){
        try {
            meuAdapter.setTarefas();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void UpdateTask(int idUpdate){
        try {
//            String teste;
//            bd.execSQL("UPDATE tarefas SET feito = 1 WHERE id ="+ idUpdate);
//            RefreshList();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}