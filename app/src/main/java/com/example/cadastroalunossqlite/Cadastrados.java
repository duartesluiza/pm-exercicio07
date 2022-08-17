package com.example.cadastroalunossqlite;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Cadastrados extends AppCompatActivity {
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrados);
        lv = (ListView) findViewById(R.id.lv);

        List<Estudante> lista = (List<Estudante>) getIntent().getSerializableExtra("objList");
        String[] array = new String[lista.size()];

        for(int i=0;i< lista.size();i++) {
            array[i] = (String) lista.get(i).getNome() + "" + lista.get(i).getCurso();
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array);
        lv.setAdapter(adapter);



    }
}