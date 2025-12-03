package com.example.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Button buttonInsere;
    EditText editText;
    ListView listView;
    ArrayList<Nota> notas = new ArrayList<Nota>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonInsere=findViewById(R.id.buttonInsere);
        editText=findViewById(R.id.edText);
        listView=findViewById(R.id.listView);

        db=openOrCreateDatabase("notas",MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS notas (id INTEGER PRIMARY KEY AUTOINCREMENT , txt TEXT)");

        buttonInsere.setOnClickListener(v->{
            insereNota(editText.getText().toString());
        });

        // Listener de clique longo
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Nota n = (Nota)adapterView.getItemAtPosition(i);
                db.delete("notas","id=?",new String [] {Integer.toString(n.id)});
                carregaNota();
                return false;
            }
        });
        carregaNota();
    }

    // Insert
    public String insereNota(String txt){
        ContentValues cv = new ContentValues();
        cv.put("txt",txt);
        db.insert("notas",null, cv);
        carregaNota();
        return "Inserida";
    }

    // Select
    public void carregaNota(){
        // Cursor é utilizado para se mover no select
        Cursor cursor=db.rawQuery("SELECT * FROM notas ",null);
        cursor.moveToFirst();
        notas.clear();

        // Enquanto o cursor não chegar depois do ultimo, vai inserindo a nota na lista
        while(!cursor.isAfterLast()){
            int columnid =cursor.getColumnIndex("id");
            int columnTxt =cursor.getColumnIndex("txt");
            int id=cursor.getInt(columnid);
            String txt=cursor.getString(columnTxt);
            notas.add (new Nota(id,txt));
            cursor.moveToNext();
        }

        // Coloca no adapter
        AdapterNota adapter =new AdapterNota(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                notas);
        listView.setAdapter(adapter);
    }
}