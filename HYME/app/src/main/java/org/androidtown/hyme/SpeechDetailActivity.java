package org.androidtown.hyme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SpeechDetailActivity extends AppCompatActivity {

    TextView tv_detail_speecher;
    TextView tv_detail_type;
    TextView tv_detail_content;

    String speaker="";
    String type="";
    String content="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_detail);

        Bundle getBundle = new Bundle();
        getBundle = getIntent().getExtras();

        speaker = getBundle.getString("speaker");
        type = getBundle.getString("type");
        content = getBundle.getString("content");

        initView();
    }

    public void initView(){
        tv_detail_speecher = (TextView) findViewById(R.id.tv_detail_speecher);
        tv_detail_type = (TextView) findViewById(R.id.tv_detail_type);
        tv_detail_content = (TextView) findViewById(R.id.tv_detail_content);

        tv_detail_speecher.setText(speaker);
        tv_detail_type.setText(type);
        tv_detail_content.setText(content);
    }

}
