package kh.edu.app.myproject.lc_luncher.Activity;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import kh.edu.app.myproject.lc_luncher.R;


public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                startHomeActivity();
                finish();
            }
        },500);
    }
    private  void  startHomeActivity(){
        Intent HomeIntent = new Intent(this,MainActivity.class);
        startActivity(HomeIntent);
    }
}
