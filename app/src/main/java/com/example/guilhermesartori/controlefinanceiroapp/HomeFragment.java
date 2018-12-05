package com.example.guilhermesartori.controlefinanceiroapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guilhermesartori.controlefinanceiroapp.model.Nota;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements RecyclerViewNotasAdapter.NotasAdapterListener {
    private static final String TAG = "READING";
    private TextView title;
    private TextView content;
    private RecyclerView recyclerView;
    RecyclerViewNotasAdapter adapter;
    private DatabaseReference mDatabase;
    EditText titleEdit;
    EditText contentEdit;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        Button button = inflate.findViewById(R.id.teste);
        title = inflate.findViewById(R.id.item_nota_title);
        content = inflate.findViewById(R.id.item_nota_content);
        recyclerView = inflate.findViewById(R.id.recycler_notas);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerViewNotasAdapter(getNotas(), this);
        recyclerView.setAdapter(adapter);

        titleEdit = inflate.findViewById(R.id.edit_nota_title);
        contentEdit = inflate.findViewById(R.id.edit_nota_content);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nota nota = new Nota();

                nota.setTitle(titleEdit.getText().toString());
                nota.setContent(contentEdit.getText().toString());

                writeToDatabase(nota);
            }
        });
        return inflate;
    }

    private List<Nota> getNotas() {
        List<Nota> notas = new ArrayList<>();
        notas.add(new Nota("teste", "teste"));
        notas.add(new Nota("teste", "teste"));
        return notas;
    }


    private void writeToDatabase(Nota nota){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(mAuth.getUid());

        myRef.setValue(nota);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Nota nota = dataSnapshot.getValue(Nota.class);
                adapter.addNewNota(nota);
//                title.setText(nota.getTitle());
//                content.setText(nota.getContent());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }


    @Override
    public void addToFavs(Nota nota) {

    }
}
