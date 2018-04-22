package org.androidtown.hyme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

public class CreateRoomActivity extends AppCompatActivity {

    EditText ed_create_name;
    Button bt_create_participant;
    ListView lv_create_participant_list;
    CheckBox cb_create_current_time;
    CheckBox cb_create_alarm;
    Button bt_create_confirm;
    Button bt_create_cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        initView();
    }

    public void initView(){
        ed_create_name = (EditText) findViewById(R.id.ed_create_name);
        bt_create_participant = (Button) findViewById(R.id.bt_create_participant);
        lv_create_participant_list = (ListView) findViewById(R.id.lv_create_participant_list);
        cb_create_current_time = (CheckBox) findViewById(R.id.cb_create_current_time);
        cb_create_alarm = (CheckBox) findViewById(R.id.cb_create_alarm);
        bt_create_confirm = (Button) findViewById(R.id.bt_create_confirm);
        bt_create_cancel = (Button) findViewById(R.id.bt_create_cancel);

        bt_create_confirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        bt_create_cancel.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                finish();
            }
        });

    }



}
