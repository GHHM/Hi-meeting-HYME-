package org.androidtown.hyme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import org.androidtown.hyme.database.DbOpenHelper;

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
    EditText ed_reg_id;
    Button bt_reg_id_confirm;
    EditText ed_reg_pw;
    EditText ed_reg_pw_confirm;
    EditText ed_reg_name;
    EditText ed_reg_phone;
    Button bt_create_register;
    Button bt_reg_cancel;

    // for creating account
    String createId;
    String createPw;
    String createName;
    String createPhone;

    // Check the state
    Boolean dupId = false;
    Boolean confirmed = false;

    //Database
    private DbOpenHelper mDBOpenHelper;
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        initDatabase();
        initView();
        getRegister();

    }

    public void initDatabase(){
        mDBOpenHelper = new DbOpenHelper(this);
        mDBOpenHelper.openDB();
        mDBOpenHelper.insertColumn_User("user001", "dongmin", "0000", "01000000000");
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

               checkInfo();

            }
        });

    }

    public void checkInfo(){

        int i=0;

        while(i<mDBOpenHelper.countUser()){
            mCursor=mDBOpenHelper.getColumn_User(i);

            if(mCursor.moveToFirst() && mCursor.getCount() >=1 ){
                if(ed_user_id.getText().toString().equals(mCursor.getString(1))){

                    confirmed=true;

                    // check password
                    if(ed_password.getText().toString().equals(mCursor.getString(3))){
                        Intent intent = new Intent(LoginActivity.this, MeetingRoomActivity.class);
                        Toast.makeText(getApplicationContext(), mCursor.getString(2) + "님, 환영합니다.", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }

                    else{
                        Toast.makeText(getApplicationContext(), "비밀번호가 틀립니다. 다시 입력해주세요", Toast.LENGTH_LONG).show();
                        break;
                    }

                }
            }

            i++;

        }

        if(!confirmed){
            Toast.makeText(getApplicationContext(), "없는 ID입니다. 다시 입력해주세요", Toast.LENGTH_LONG).show();
        }

    }

    // For pupup view
    public void getRegister(){

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
        bt_create_register = (Button) get_layout.findViewById(R.id.bt_register);
        bt_reg_cancel = (Button) get_layout.findViewById(R.id.bt_register_cancel);

        createId = ed_reg_id.getText().toString();
        createPw = ed_reg_pw.getText().toString();
        createName = ed_reg_name.getText().toString();
        createPhone = ed_reg_phone.getText().toString();

        bt_reg_id_confirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                // check the same ID
                Boolean isSame = checkId();

                if(isSame){
                    dupId = true;
                    Toast.makeText(getApplicationContext(), "ID가 존재합니다.", Toast.LENGTH_LONG).show();
                }

                else{
                    Toast.makeText(getApplicationContext(), "사용가능한 ID입니다.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        bt_create_register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Boolean confirmed = false;

                createPw = ed_reg_pw.getText().toString();
                createName = ed_reg_name.getText().toString();
                createPhone = ed_reg_phone.getText().toString();

                // check Id
                if(!dupId){
                    Toast.makeText(getApplicationContext(), "is it okay?", Toast.LENGTH_SHORT).show();
                    // check password
                    if((!createPw.isEmpty()) && (createPw.equals(ed_reg_pw_confirm.getText().toString()))){
                        // check name & phone number
                        Toast.makeText(getApplicationContext(), "is it working?", Toast.LENGTH_SHORT).show();
                        if(!(createName.isEmpty() || createPhone.isEmpty())){
                            Toast.makeText(getApplicationContext(), "please..", Toast.LENGTH_SHORT).show();
                            confirmed = true;
                        }
                    }
                }

                if(confirmed){

                    saveData();
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

    private void saveData(){
        Toast.makeText(getApplicationContext(), createId + " " + createName + " " + createPhone, Toast.LENGTH_SHORT).show();
        mDBOpenHelper.insertColumn_User(createId,createName,createPw,createPhone);

    }

    private Boolean checkId(){
        createId = ed_reg_id.getText().toString();

        for (int i=0; i <= mDBOpenHelper.countUser();i++){
            mCursor=mDBOpenHelper.getColumn_User(i);

            // cursor is initialized as -1
            if(mCursor.moveToFirst() && mCursor.getCount() >= 1) {

                // starts with 1 : user
                if (createId.equals(mCursor.getString(1))) {
                    return true;
                }
            }
        }


        return false;

    }


}
