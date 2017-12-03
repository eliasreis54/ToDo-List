package com.example.eliasreis.todolist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eliasreis.todolist.helper.SQL_Helper;

import java.util.ArrayList;

/**
 * Created by eliasreis on 02/12/2017.
 */

public class MeuAdapter extends RecyclerView.Adapter<MeuAdapter.MeuViewHolder> {
    Context ctx;
    ArrayList<Tarefa> tasks;

    public MeuAdapter(Context ctx) {
        this.ctx = ctx;
        this.tasks = SQL_Helper.getInstance().carregarValores();
    }


    public class MeuViewHolder extends RecyclerView.ViewHolder {

        TextView txtnome;
        RelativeLayout LinearLayout;

        public MeuViewHolder(View itemView) {
            super(itemView);
            txtnome = (TextView) itemView.findViewById(R.id.textviewAdapter);
            LinearLayout = (RelativeLayout) itemView.findViewById(R.id.linear);
        }
    }

    @Override
    public MeuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.lista_adapter, parent, false);

        return new MeuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MeuViewHolder holder, int position) {
        //passa os valores para os seus devidos lugares
        MeuViewHolder meuholder = ((MeuViewHolder) holder);

        //meuholder.LinearLayout.setBackgroundColor("#FFFFFF");
        final Tarefa taksHolder = tasks.get(position);
        meuholder.txtnome.setText(taksHolder.tarefa);

        meuholder.LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQL_Helper.getInstance().update(taksHolder.id);
                setTarefas();
                Toast.makeText(ctx, "Tarefa concluída com sucesso.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTarefas() {
        this.tasks.clear();
        this.tasks = SQL_Helper.getInstance().carregarValores();
        notifyDataSetChanged();
    }
}
