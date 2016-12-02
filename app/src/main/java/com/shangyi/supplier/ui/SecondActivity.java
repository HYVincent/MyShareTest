package com.shangyi.supplier.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.shangyi.supplier.R;

/**
 * description :
 * project name：MyTest
 * author : Vincent
 * creation time: 2016/12/2  15:53
 *
 * @version 1.0
 */

public class SecondActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_second);
        findViewById(R.id.btn_clone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
