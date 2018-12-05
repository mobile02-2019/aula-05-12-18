package com.example.guilhermesartori.controlefinanceiroapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guilhermesartori.controlefinanceiroapp.model.Nota;

import java.util.List;

public class RecyclerViewNotasAdapter extends RecyclerView.Adapter<RecyclerViewNotasAdapter.ViewHolder> {
    private List<Nota> notas;
    private NotasAdapterListener listener;

    public RecyclerViewNotasAdapter(List<Nota> notas, NotasAdapterListener listener) {
        this.notas = notas;
        this.listener = listener;
    }

    public interface NotasAdapterListener{
        void addToFavs(Nota nota);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nota_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(notas.get(i));
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_nota_title);
            content = itemView.findViewById(R.id.item_nota_content);
        }

        public void bind(Nota nota){
            title.setText(nota.getTitle());
            content.setText(nota.getContent());
        }
    }
}
