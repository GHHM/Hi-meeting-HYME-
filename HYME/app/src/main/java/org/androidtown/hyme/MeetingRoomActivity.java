package org.androidtown.hyme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Master on 2017-12-12.
 */

public class MeetingRoomActivity extends AppCompatActivity {

    ListView lv_meeting_list;
    RoomList roomList;
    Button bt_go_create;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_room);
        initView();
    }


    public void initView(){

        lv_meeting_list = (ListView) findViewById(R.id.lv_meeting_list);
        roomList = new RoomList(this);
        bt_go_create = (Button) findViewById(R.id.bt_go_create);

        lv_meeting_list.setAdapter(roomList);

        roomList.addItem("졸업 작품 회의방", "홍길동, 박동민, 김혜미, 김단우, 김전일", "05:30 PM");

        lv_meeting_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                Intent intent = new Intent(MeetingRoomActivity.this, SpeechActivity.class);
                startActivity(intent);
            }

        });

        bt_go_create.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MeetingRoomActivity.this, CreateRoomActivity.class);
                startActivity(intent);
            }
        });
    }
}
