package org.androidtown.hyme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

/**
 * Created by Master on 2017-12-11.
 */

public class LogActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    LogList logList;
    Button bt_share;
    CheckBox cb_type_opinion;
    CheckBox cb_type_additional;
    CheckBox cb_type_ask;
    CheckBox cb_type_answer;
    ListView lv_participant_log;
    Button bt_go_previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        initView();
    }

    public void initView() {
        Bundle getBundle = new Bundle();
        getBundle = getIntent().getExtras();
        String data = getBundle.getString("data");
        int type = getBundle.getInt("type");
        String getType="";



        bt_share = (Button) findViewById(R.id.bt_share);
        bt_go_previous = (Button) findViewById(R.id.bt_go_previous);
        lv_participant_log = (ListView) findViewById(R.id.lv_participant_log);

        cb_type_opinion = (CheckBox) findViewById(R.id.cb_type_opinion);
        cb_type_additional = (CheckBox) findViewById(R.id.cb_type_additional);
        cb_type_ask = (CheckBox) findViewById(R.id.cb_type_ask);
        cb_type_answer = (CheckBox) findViewById(R.id.cb_type_answer);

        logList = new LogList(this);

        /* Get Data */
        //getData();


        switch(type){
            case 1:
                getType="의견";
                break;
            case 2:
                getType="추가";
                break;
            case 3:
                getType="질문";
                break;
            case 4:
                getType="답변";
                break;
        }





        /* Temporal setting */
        logList.addItem(1, "홍길동", getType, data);
        logList.addItem(2, "김혜미", "추가", "그와 관련된 보도 자료가 있습니다. 다음을 참고하시면...");
        logList.addItem(3, "김단우", "질문", "외국에서는 우리나라의 효도법과 비슷한 제도가...");
        logList.addItem(4, "박동민", "의견", "홍길동씨와 다르게, 저는 부양의무에 대해서 이렇게 생각하는데...");

        cb_type_opinion.setOnCheckedChangeListener(this);
        cb_type_additional.setOnCheckedChangeListener(this);
        cb_type_ask.setOnCheckedChangeListener(this);
        cb_type_answer.setOnCheckedChangeListener(this);

        lv_participant_log.setAdapter(logList);

        bt_share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LogActivity.this, DownloadActivity.class);
                startActivity(intent);
            }
        });

        bt_go_previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (cb_type_opinion.isChecked()) {
            checkView(1, true);
        } else {
            checkView(1, false);
        }

        if (cb_type_additional.isChecked()) {
            checkView(2, true);
        } else {
            checkView(2, false);
        }

        if (cb_type_ask.isChecked()) {
            checkView(3, true);
        } else {
            checkView(3, false);
        }

        if (cb_type_answer.isChecked()) {
            checkView(4, true);
        } else {
            checkView(4, false);
        }

    }

    private void checkView(int check, boolean isVisible) {

        for (int i = 0; i < lv_participant_log.getCount(); i++) {
            Log.i("aaa", String.valueOf(lv_participant_log.getCount()));

            if (isVisible) {
                switch (check) {
                    case 1:
                        if(logList.printType(i).equals(cb_type_opinion.getText().toString())) {
                            Log.i("aaa", "right");
                            lv_participant_log.getChildAt(i).setVisibility(View.VISIBLE);
                        }
                        break;
                    case 2:
                        if(logList.printType(i).equals("추가")) {
                            lv_participant_log.getChildAt(i).setVisibility(View.VISIBLE);
                        }
                        break;
                    case 3:
                        if(logList.printType(i).equals(cb_type_ask.getText().toString())) {
                            lv_participant_log.getChildAt(i).setVisibility(View.VISIBLE);
                        }
                        break;
                    case 4:
                        if(logList.printType(i).equals(cb_type_answer.getText().toString())) {
                            lv_participant_log.getChildAt(i).setVisibility(View.VISIBLE);
                        }
                        break;
                }
            }

            else{
                switch (check) {
                    case 1:
                        if(logList.printType(i).equals(cb_type_opinion.getText().toString())) {
                            Log.i("aaa", "wrong");
                            lv_participant_log.getChildAt(i).setVisibility(View.GONE);
                        }
                        break;
                    case 2:
                        if(logList.printType(i).equals("추가")) {
                            lv_participant_log.getChildAt(i).setVisibility(View.GONE);
                        }
                        break;
                    case 3:
                        if(logList.printType(i).equals(cb_type_ask.getText().toString())) {
                            lv_participant_log.getChildAt(i).setVisibility(View.GONE);
                        }
                        break;
                    case 4:
                        if(logList.printType(i).equals(cb_type_answer.getText().toString())) {
                            lv_participant_log.getChildAt(i).setVisibility(View.GONE);
                        }
                        break;
                }
            }
        }

    }


}
