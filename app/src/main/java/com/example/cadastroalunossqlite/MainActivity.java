package com.example.cadastroalunossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.List;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText eNome;
    private EditText eCurso;
    private Button btnSalvar;
    private Button btnBuscar;
    private EstudanteBD db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eNome = (EditText) findViewById(R.id.eNome);
        eCurso = (EditText) findViewById(R.id.eCurso);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnSalvar.setOnClickListener(this);
        btnBuscar.setOnClickListener(this);
        db = new EstudanteBD(this);


    }

    @Override
    public void onClick(View v) {

        //código para salvar
        if (v.getId() == R.id.btnSalvar) {

            Estudante e = new Estudante();
            e.setNome(eNome.getText().toString().trim());
            e.setCurso(eCurso.getText().toString().trim());
            db.save(e);

        } else if (v.getId() == R.id.btnBuscar) {  //código para buscar

            List<Estudante> l = db.findAll();

            for(int i=0;i<l.size();i++) {
                System.out.println(l.get(i).getId() + " - " + l.get(i).getNome() + " - " + l.get(i).getCurso());
            }

            Intent i = new Intent(getApplicationContext(), Cadastrados.class);
            i.putExtra("objList", (Serializable) l);
            startActivity(i);

        }


    }
}