package com.example.cadastroalunossqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class EstudanteBD extends SQLiteOpenHelper {
    private static final String FAQ = "sql";
    private static final String NOME_BANCO = "estudantes.sqlite";
    private static final int VERSAO = 1;


    public EstudanteBD(Context context) {
        super(context, NOME_BANCO, null, VERSAO);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(FAQ, "criando a tabela estudante");
        db.execSQL("create table if not exists estudante(_id integer primary key autoincrement, nome text, curso text);");
        Log.d(FAQ, "tabela criada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int delete(Estudante e) {
        SQLiteDatabase db = getWritableDatabase();
        try{
            String _id = String.valueOf(e.getId());
            String[] whereArgs = new String[]{_id};
            int count = db.delete("estudante", "_id=?", whereArgs);
            return count;

        }catch(Exception ex){
            System.out.println(ex);

        }finally{
            db.close();
        }
        return 0;
    }

    @SuppressLint("Range")
    public List<Estudante> toList(Cursor c){
        List<Estudante> estudantes = new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                Estudante e = new Estudante();
                e.setId(c.getInt(c.getColumnIndex("_id")));
                e.setNome(c.getString(c.getColumnIndex("nome")));
                e.setCurso(c.getString(c.getColumnIndex("curso")));
                estudantes.add(e);
            }while (c.moveToNext());
        }
        return estudantes;
    }

    public List<Estudante> findAll() {
        SQLiteDatabase db = getReadableDatabase();
        try{
            Cursor c = db.query("estudante", null, null, null, null, null, null);
            return toList(c);
        }finally{
            db.close();
        }

    }

    public long save(Estudante e) {
        long id = e.getId();

        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("nome", e.getNome());
            values.put("curso", e.getCurso());
            if (id != 0) {   //update
                String _id = String.valueOf(e.getId());
                String[] whereArgs = new String[]{_id};
                int count = db.update("estudante", values, "_id=?", whereArgs);
                return count;
            } else {   //insert
                id = db.insert("estudante", "", values);
                return id;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            db.close();
        }
        return 0;

    }
}





