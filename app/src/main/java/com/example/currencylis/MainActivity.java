package com.example.currencylis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab_add;
    ListView lv_country;
    DBManager dbManager;
    List<String> simpleAdapter;
   private String m_Text = "";

   final String [] fromDatabase = new String[]{
        DBHelper._ID,
        DBHelper._NAME,
        DBHelper._CURRENCY

   };

   final int [] to =new int[]{
           R.id.text_view_id,
           R.id.textView_country,
           R.id.textView_currency
   };

   SimpleCursorAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTitle("Country/Currency App");




        DBmain dBmain = new DBmain(this);

        dbManager =new DBManager(this);



        fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        lv_country = (ListView)findViewById(R.id.lv_country);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dBmain.show();


                Snackbar.make(v,"FAB Clicked",Snackbar.LENGTH_LONG).show();
                simpleAdapter = new ArrayList<>();
                simpleAdapter.add("India");
                simpleAdapter.add("Australia");
                simpleAdapter.add("China");
                simpleAdapter.add("Japan");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, simpleAdapter);

                lv_country.setAdapter(adapter);

                registerForContextMenu(lv_country);


            }
        });

        FetchDatabase();

    }

    private void FetchDatabase() {

        dbManager.Open();

        Cursor fetch = dbManager.fetch();

        dbManager.Close();

        adapter = new SimpleCursorAdapter(getApplicationContext(),R.layout.layout_view_countries,fetch,fromDatabase,to);

        ListView listView = (ListView) findViewById(R.id.lv_country);

        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();






    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.delete:
                Toast.makeText(getApplicationContext(),"Menu Delete Clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit:
                Toast.makeText(getApplicationContext(),"Menu Edit Clicked",Toast.LENGTH_SHORT).show();
                break;



        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.context,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    //Menu Creation Code

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);

        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.options_addmenu){
            Toast.makeText(this,"Items options menu add Clicked",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}