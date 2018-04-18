package org.androidtown.hyme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bt_kakaoLogin;
    Button bt_hymeLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    public void initView(){

        bt_kakaoLogin = (Button) findViewById(R.id.bt_kakaoLogin);
        bt_hymeLogin = (Button) findViewById(R.id.bt_hymeLogin);

        bt_kakaoLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(),"Error : 서버를 불러오는데 실패했습니다.", Toast.LENGTH_LONG).show();
            }
        });

        bt_hymeLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
