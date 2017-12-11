package org.androidtown.hyme;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

/**
 * Created by Master on 2017-12-12.
 */

public class DownloadActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    CheckBox cb_type_opinion;
    CheckBox cb_type_additional;
    CheckBox cb_type_ask;
    CheckBox cb_type_answer;
    CheckBox cb_format_word;
    CheckBox cb_format_pdf;

    Button bt_download_confirm;
    Button bt_go_previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initView();
    }

    public void initView(){
        bt_download_confirm = (Button) findViewById(R.id.bt_download_confirm);
        bt_go_previous = (Button) findViewById(R.id.bt_go_previous);

        cb_type_opinion = (CheckBox) findViewById(R.id.cb_type_opinion);
        cb_type_additional = (CheckBox) findViewById(R.id.cb_type_additional);
        cb_type_ask = (CheckBox) findViewById(R.id.cb_type_ask);
        cb_type_answer = (CheckBox) findViewById(R.id.cb_type_answer);
        cb_format_word = (CheckBox) findViewById(R.id.cb_format_word);
        cb_format_pdf = (CheckBox) findViewById(R.id.cb_format_pdf);

        bt_download_confirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "현재 준비중입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        bt_go_previous.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

        cb_type_opinion.setOnCheckedChangeListener(this);
        cb_type_additional.setOnCheckedChangeListener(this);
        cb_type_ask.setOnCheckedChangeListener(this);
        cb_type_answer.setOnCheckedChangeListener(this);
        cb_format_word.setOnCheckedChangeListener(this);
        cb_format_pdf.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){

        if(cb_type_opinion.isChecked()){
            cb_type_opinion.setBackgroundColor(Color.parseColor("#FFD700"));
        }
        else{
            cb_type_opinion.setBackgroundColor(Color.parseColor("#BDBDBD"));
        }

        if(cb_type_additional.isChecked()){
            cb_type_additional.setBackgroundColor(Color.parseColor("#63b2f7"));
        }
        else{
            cb_type_additional.setBackgroundColor(Color.parseColor("#BDBDBD"));
        }

        if(cb_type_ask.isChecked()){
            cb_type_ask.setBackgroundColor(Color.parseColor("#32CD32"));
        }
        else{
            cb_type_ask.setBackgroundColor(Color.parseColor("#BDBDBD"));
        }

        if(cb_type_answer.isChecked()){
            cb_type_answer.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        else{
            cb_type_answer.setBackgroundColor(Color.parseColor("#BDBDBD"));
        }


        if(cb_format_word.isChecked()){
            cb_format_word.setBackgroundColor(Color.parseColor("#63b2f7"));
        }
        else{
            cb_format_word.setBackgroundColor(Color.parseColor("#BDBDBD"));
        }

        if(cb_format_pdf.isChecked()){
            cb_format_pdf.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        else{
            cb_format_pdf.setBackgroundColor(Color.parseColor("#BDBDBD"));
        }
    }
}
