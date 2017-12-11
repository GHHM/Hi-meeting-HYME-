package org.androidtown.hyme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

/**
 * Created by Master on 2017-12-11.
 */

public class LogActivity extends AppCompatActivity {

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

    public void initView(){
        bt_share = (Button) findViewById(R.id.bt_share);
        bt_go_previous = (Button) findViewById(R.id.bt_go_previous);
        lv_participant_log = (ListView) findViewById(R.id.lv_participant_log);

        logList = new LogList(this);

        /* Get Data */
        //getData();

        lv_participant_log.setAdapter(logList);

        /* Temporal setting */
        logList.addItem(1, "홍길동", "의견", "기존의 효도법과 부양 의무에 대해 제 생각은...");
        logList.addItem(2, "김혜미", "추가", "그와 관련된 보도 자료가 있습니다. 다음을 참고하시면...");
        logList.addItem(3, "김단우", "질문", "외국에서는 우리나라의 효도법과 비슷한 제도가...");
        logList.addItem(4, "박동민", "의견", "홍길동씨와 다르게, 저는 부양의무에 대해서 이렇게 생각하는데...");

        bt_share.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LogActivity.this, DownloadActivity.class);
                startActivity(intent);
            }
        });

        bt_go_previous.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

    }


}
