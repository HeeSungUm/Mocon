package com.appplepie.mocon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTodoActivity extends AppCompatActivity {
    EditText placeEt, descEt;
    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        placeEt = findViewById(R.id.addPlanPlaceDropDown);
        descEt = findViewById(R.id.addPlanDescInput);
        submitBtn = findViewById(R.id.addPlanSubmitButton);
        submitBtn.setOnClickListener(view -> {
            if (!descEt.getText().toString().equals("")){
                Intent intent = getIntent();
                intent.putExtra("desc", descEt.getText().toString());
                intent.putExtra("place", placeEt.getText().toString());
            }
            else{
                descEt.setError("메모를 입력해주세요");
            }
        });
    }
}