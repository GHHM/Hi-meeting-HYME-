package org.androidtown.hyme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Master on 2017-12-06.
 */

public class RequestSpeechActivity extends AppCompatActivity {

    Button bt_speech_opinion;
    Button bt_speech_ask;
    Button bt_speech_additional;
    Button bt_speech_answer;

    TextView tv_request_type;

    Button bt_go_back;
    Button bt_ok_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_speech);
        initView();
    }

    public void initView(){
        bt_speech_opinion = (Button) findViewById(R.id.bt_speech_opinion);
        bt_speech_ask = (Button) findViewById(R.id.bt_speech_ask);
        bt_speech_additional = (Button) findViewById(R.id.bt_speech_additional);
        bt_speech_answer = (Button) findViewById(R.id.bt_speech_answer);
        tv_request_type = (TextView) findViewById(R.id.tv_request_type);
        tv_request_type.setText("");
        bt_go_back = (Button) findViewById(R.id.bt_go_back);
        bt_ok_request = (Button) findViewById(R.id.bt_ok_request);

        bt_go_back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Intent intent = new Intent(RequestSpeechActivity.this, SpeechActivity.class);
                //startActivity(intent);
            }
        });

        bt_ok_request.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Toast.makeText(getApplicationContext(), "요청을 보냈습니다.", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Error : 요청을 보내는데 실패했습니다", Toast.LENGTH_LONG).show();
            }
        });

        bt_speech_opinion.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv_request_type.setText("'의견'을 선택하셨습니다.");
            }
        });

        bt_speech_ask.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv_request_type.setText("'질문'을 선택하셨습니다.");
            }
        });

        bt_speech_additional.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv_request_type.setText("'추가내용'을 선택하셨습니다.");
            }
        });

        bt_speech_answer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv_request_type.setText("'답변'을 선택하셨습니다.");
            }
        });

    }
}
