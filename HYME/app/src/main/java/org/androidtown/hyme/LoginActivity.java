package org.androidtown.hyme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * Created by HAMHAM on 2017-11-26.
 */

public class LoginActivity extends AppCompatActivity {

    EditText ed_user_id;
    EditText ed_password;
    Button bt_register;
    Button bt_go_login;

    // For popup view
    private View get_layout;
    private PopupWindow popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        getRegister();
    }


    public void initView(){

        ed_user_id = (EditText) findViewById(R.id.ed_user_id);
        ed_password = (EditText) findViewById(R.id.ed_password);
        bt_register = (Button) findViewById(R.id.bt_register);
        bt_go_login = (Button) findViewById(R.id.bt_go_login);

        ed_user_id.setFocusable(true);
        ed_user_id.setFocusableInTouchMode(true);


        bt_register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                showPopup(LoginActivity.this, 1);
            }
        });

        bt_go_login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                //checkId();

                /* For testing!*/
                justForTest();

            }
        });

    }

    // For testing!!
    public void justForTest(){

        if(ed_user_id.getText().toString().equals("user001")){

            if(ed_password.getText().toString().equals("0000")){
                Intent intent = new Intent(LoginActivity.this, MeetingRoomActivity.class);
                startActivity(intent);
            }

            else{
                Toast.makeText(getApplicationContext(), "비밀번호가 틀립니다. 다시 입력해주세요", Toast.LENGTH_LONG).show();
            }
        }

        else{
            Toast.makeText(getApplicationContext(), "없는 ID입니다. 다시 입력해주세요", Toast.LENGTH_LONG).show();
        }

    }

    // For pupup view
    public void getRegister(){

        EditText ed_reg_id;
        Button bt_reg_id_confirm;
        EditText ed_reg_pw;
        final EditText ed_reg_pw_confirm;
        EditText ed_reg_name;
        EditText ed_reg_phone;
        Button bt_register;
        Button bt_reg_cancel;

        String createId;
        final String createPw;
        final String createName;
        final String createPhone;

        // Check the state
        final Boolean dupId = false;

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        get_layout = inflater.inflate(R.layout.popup_register, (ViewGroup) findViewById(R.id.popup_reg));
        popup = new PopupWindow(this);
        popup.setContentView(get_layout);
        popup.setWidth(870);
        popup.setHeight(1520);

        // initialize view
        ed_reg_id = (EditText) get_layout.findViewById(R.id.ed_register_id);
        bt_reg_id_confirm = (Button) get_layout.findViewById(R.id.bt_register_id_confirm);
        ed_reg_pw = (EditText) get_layout.findViewById(R.id.ed_register_pw);
        ed_reg_pw_confirm = (EditText) get_layout.findViewById(R.id.ed_register_pw_confirm);
        ed_reg_name = (EditText) get_layout.findViewById(R.id.ed_register_name);
        ed_reg_phone = (EditText) get_layout.findViewById(R.id.ed_register_phone_num);
        bt_register = (Button) get_layout.findViewById(R.id.bt_register);
        bt_reg_cancel = (Button) get_layout.findViewById(R.id.bt_register_cancel);

        createId = ed_reg_id.getText().toString();
        createPw = ed_reg_pw.getText().toString();
        createName = ed_reg_name.getText().toString();
        createPhone = ed_reg_phone.getText().toString();

        bt_reg_id_confirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                // check the same ID
                //Boolean isSame = checkId();

                /*
                if(!isSame){
                    dupId = true;
                }
                */

            }
        });

        bt_register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Boolean confirmed = false;

                // check Id
                if(dupId){
                    // check password
                    if((!createPw.isEmpty()) && (createPw.equals(ed_reg_pw_confirm.getText().toString()))){
                        // check name & phone number
                        if(!(createName.isEmpty() || createPhone.isEmpty())){
                            confirmed = true;
                        }
                    }
                }

                if(confirmed){

                    //saveData();
                    Toast.makeText(getApplicationContext(), "가입이 완료되었습니다.", Toast.LENGTH_LONG).show();
                    popup.dismiss();
                }

                else{
                    Toast.makeText(getApplicationContext(), "Error : 정보를 정확히 기입해주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });

        bt_reg_cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                popup.dismiss();
            }
        });

    }

    public void showPopup(final Activity context, int mode) {
        // location of popup
        int OFFSET_X = 0;
        int OFFSET_Y = 0;

        if(mode==1) {
            if (popup != null) {
                popup.setFocusable(true);
                popup.showAtLocation(get_layout, Gravity.CENTER, OFFSET_X, OFFSET_Y);
            }
        }
    }


}
