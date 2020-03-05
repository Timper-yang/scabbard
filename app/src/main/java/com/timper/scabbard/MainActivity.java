package com.timper.scabbard;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.timper.scabbard.annotations.Time;

/**
 * User: tangpeng.yang
 * Date: 2019-11-19
 * Description:
 * FIXME
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hollow();
    }

    @Time
    void hollow(){

    }

}
