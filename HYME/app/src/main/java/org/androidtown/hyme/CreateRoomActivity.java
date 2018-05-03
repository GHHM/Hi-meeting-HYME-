package org.androidtown.hyme;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.androidtown.hyme.database.DbOpenHelper;

import java.util.Calendar;

public class CreateRoomActivity extends AppCompatActivity {

    ParticipantCreateList pt_list;
    TextView tv_create_participant_number;
    EditText ed_create_name;
    EditText ed_create_participant;
    Button bt_create_participant_add;
    Button bt_create_participant_search;
    ListView lv_create_participant_list;
    CheckBox cb_create_current_time;
    CheckBox cb_create_alarm;
    Button bt_create_confirm;
    Button bt_create_cancel;

    String room_name;
    String participants="";
    String set_time="";
    String set_date="";

    // time and date
    TextView tv_create_room_ampm;
    TextView tv_create_room_hour;
    TextView tv_create_room_minute;
    TextView tv_create_room_year;
    TextView tv_create_room_month;
    TextView tv_create_room_day;

    DialogFragment mFragment = null;

    DbOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        initView();
    }

    public void initView() {
        ed_create_name = (EditText) findViewById(R.id.ed_create_name);
        tv_create_participant_number = (TextView) findViewById(R.id.tv_create_participant_number);
        ed_create_participant = (EditText) findViewById(R.id.ed_create_participant);
        bt_create_participant_search = (Button) findViewById(R.id.bt_create_participant_search);
        bt_create_participant_add = (Button) findViewById(R.id.bt_create_participant_add);
        lv_create_participant_list = (ListView) findViewById(R.id.lv_create_participant_list);
        cb_create_current_time = (CheckBox) findViewById(R.id.cb_create_current_time);
        cb_create_alarm = (CheckBox) findViewById(R.id.cb_create_alarm);
        bt_create_confirm = (Button) findViewById(R.id.bt_create_confirm);
        bt_create_cancel = (Button) findViewById(R.id.bt_create_cancel);

        tv_create_room_ampm = (TextView) findViewById(R.id.tv_create_room_ampm);
        tv_create_room_hour = (TextView) findViewById(R.id.tv_create_room_hour);
        tv_create_room_minute = (TextView) findViewById(R.id.tv_create_room_minute);
        tv_create_room_year = (TextView) findViewById(R.id.tv_create_room_year);
        tv_create_room_month = (TextView) findViewById(R.id.tv_create_room_month);
        tv_create_room_day = (TextView) findViewById(R.id.tv_create_room_day);

        pt_list = new ParticipantCreateList(this);
        mDBOpenHelper = new DbOpenHelper(this);


        bt_create_participant_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show participant list popup
            }
        });

        bt_create_participant_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getPart = ed_create_participant.getText().toString();
                if(getPart.isEmpty()){
                    Toast.makeText(getApplicationContext(), "참여자를 입력해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    // add participant in list
                    pt_list.addItem(getPart);
                    lv_create_participant_list.setAdapter(pt_list);
                    tv_create_participant_number.setText(pt_list.getCount() + "명");

                    if(participants.length() > 0) {
                        participants += ", " + getPart;
                    }
                    else
                        participants = getPart;

                    // initialize
                    ed_create_participant.setText("");
                }
            }
        });

        bt_create_confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(ed_create_name.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "회의방 이름을 설정해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                }
                else if(pt_list.isEmpty()){
                    Toast.makeText(getApplicationContext(), "참여자를 추가해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                }
                else if(tv_create_room_hour.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "시간을 설정해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                }
                else if(tv_create_room_year.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "날짜를 설정해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    room_name = ed_create_name.getText().toString();
                    saveData();
                    finish();
                }
            }
        });

        bt_create_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        // pick time
        tv_create_room_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDateDialog(0);
            }
        });
        tv_create_room_minute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDateDialog(0);
            }
        });

        // pick date
        tv_create_room_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDateDialog(1);
            }
        });
        tv_create_room_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDateDialog(1);
            }
        });
        tv_create_room_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDateDialog(1);
            }
        });

    }

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        // onTimeSet method
        public void onTimeSet(TimePicker view, int hour, int minute) {
            String getAmpm;

            if(hour >= 12){
                if(hour>12){
                    hour -= 12;
                }
                tv_create_room_ampm.setText("오후");
                getAmpm = "PM";
            }

            else{
                tv_create_room_ampm.setText("오전");
                getAmpm = "AM";
            }

            tv_create_room_hour.setText(hour + "");
            tv_create_room_minute.setText(minute + "");
            set_time = tv_create_room_hour.getText().toString();
            set_time += ":" + tv_create_room_minute.getText().toString();
            set_time += " " + getAmpm;
        }
    };

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        // onDateSet method
        public void onDateSet(DatePicker view, int year, int month, int day) {
            tv_create_room_year.setText(year + "");
            tv_create_room_month.setText(month+1 + "");
            tv_create_room_day.setText(day + "");
            set_date = tv_create_room_year.getText().toString();
            set_date += "/" + tv_create_room_month.getText().toString();
            set_date += "/" + tv_create_room_day.getText().toString();
        }
    };

    private void showTimeDateDialog(int id){
        Dialog dia = null;
        Calendar c = Calendar.getInstance();

        if(id == 0){
            dia = new TimePickerDialog(this, mTimeSetListener, c.get(Calendar.HOUR_OF_DAY),
                    c.get(Calendar.MINUTE), false);
        }else if(id == 1){
            dia = new DatePickerDialog(this,
                    mDateSetListener,c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
        }
        dia.show();
    }

    // save meeting room in database
    private void saveData(){
        mDBOpenHelper.insertColumn_Con(room_name,participants,set_time,set_date);
    }


}
