package org.androidtown.hyme;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class CreateRoomActivity extends AppCompatActivity {

    EditText ed_create_name;
    Button bt_create_participant;
    ListView lv_create_participant_list;
    CheckBox cb_create_current_time;
    CheckBox cb_create_alarm;
    Button bt_create_confirm;
    Button bt_create_cancel;

    // time and date
    LinearLayout ll_create_room_time;
    TextView tv_create_room_ampm;
    EditText ed_create_room_hour;
    EditText ed_create_room_minute;
    LinearLayout ll_create_room_date;
    EditText ed_create_room_year;
    EditText ed_create_room_month;
    EditText ed_create_room_day;

    DialogFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        initView();
    }

    public void initView() {
        ed_create_name = (EditText) findViewById(R.id.ed_create_name);
        bt_create_participant = (Button) findViewById(R.id.bt_create_participant);
        lv_create_participant_list = (ListView) findViewById(R.id.lv_create_participant_list);
        cb_create_current_time = (CheckBox) findViewById(R.id.cb_create_current_time);
        cb_create_alarm = (CheckBox) findViewById(R.id.cb_create_alarm);
        bt_create_confirm = (Button) findViewById(R.id.bt_create_confirm);
        bt_create_cancel = (Button) findViewById(R.id.bt_create_cancel);

        ll_create_room_time = (LinearLayout) findViewById(R.id.ll_create_room_time);
        tv_create_room_ampm = (TextView) findViewById(R.id.tv_create_room_ampm);
        ed_create_room_hour = (EditText) findViewById(R.id.ed_create_room_hour);
        ed_create_room_minute = (EditText) findViewById(R.id.ed_create_room_minute);
        ll_create_room_date = (LinearLayout) findViewById(R.id.ll_create_room_date);
        ed_create_room_year = (EditText) findViewById(R.id.ed_create_room_year);
        ed_create_room_month = (EditText) findViewById(R.id.ed_create_room_month);
        ed_create_room_day = (EditText) findViewById(R.id.ed_create_room_day);

        bt_create_confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        bt_create_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        // pick time
        ed_create_room_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDateDialog(0);
            }
        });
        ed_create_room_minute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDateDialog(0);
            }
        });
        ll_create_room_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDateDialog(0);
            }
        });

        // pick date
        ed_create_room_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDateDialog(1);
            }
        });
        ed_create_room_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDateDialog(1);
            }
        });
        ed_create_room_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDateDialog(1);
            }
        });
        ll_create_room_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDateDialog(1);
            }
        });

    }

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        // onTimeSet method
        public void onTimeSet(TimePicker view, int hour, int minute) {
            if(hour >= 12){
                if(hour>12){
                    hour -= 12;
                }
                tv_create_room_ampm.setText("오후");
            }

            else{
                tv_create_room_ampm.setText("오전");
            }

            ed_create_room_hour.setText(hour + "");
            ed_create_room_minute.setText(minute + "");
        }
    };

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        // onDateSet method
        public void onDateSet(DatePicker view, int year, int month, int day) {
            ed_create_room_year.setText(year + "");
            ed_create_room_month.setText(month+1 + "");
            ed_create_room_day.setText(day + "");
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



}
