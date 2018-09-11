package org.avm.lesson7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.avm.lesson7.view.CustomLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomLayout layout = findViewById(R.id.custom_layout);
        layout.setOnClickListener((v) -> setContentView(R.layout.activity_main));
    }
}
