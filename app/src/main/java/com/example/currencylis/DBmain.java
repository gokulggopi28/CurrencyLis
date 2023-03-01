package com.example.currencylis;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class DBmain extends Dialog implements View.OnClickListener {

    EditText edt1, edt2;
    Button btn;

    public Activity c;
    DBManager dbManager = new DBManager(getContext());
    String s1, s2;






    public  DBmain(@NonNull Activity content) {
        super(content);
        this.c =content;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dbmain);
        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        s1 = String.valueOf(edt1.getText());
        s2 = String.valueOf(edt2.getText());
        dbManager.Open();
        dbManager.Insert(s1,s2);
        dbManager.Close();

        dismiss();

        }

    }
