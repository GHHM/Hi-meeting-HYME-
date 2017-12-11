package org.androidtown.hyme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Master on 2017-12-06.
 */

public class SpeechActivity extends AppCompatActivity {

    Button bt_start_voice_recog;
    Button bt_go_participant;
    Button bt_show_log;
    TextView tv_voice_recog_result;
    TextView tv_speak_type;
    RadioGroup rg_speech_type;

    // set speech type
    int typeStatus = 0;

    // Save string
    String saveString = "";


    // voice recognition
    public static final String TAG = "SpeechActivity";
    private static final int RECORD_REQUEST_CODE = 101;
    private SpeechAPI speechAPI;
    private VoiceRecorder mVoiceRecorder;
    private final VoiceRecorder.Callback mVoiceCallback = new VoiceRecorder.Callback() {
        @Override
        public void onVoiceStart() {
            if (speechAPI != null) {
                speechAPI.startRecognizing(mVoiceRecorder.getSampleRate());
            }
        }

        @Override
        public void onVoice(byte[] data, int size) {
            if (speechAPI != null) {
                speechAPI.recognize(data, size);
            }
        }

        @Override
        public void onVoiceEnd() {
            if (speechAPI != null) {
                speechAPI.finishRecognizing();
            }
        }
    };
    private final SpeechAPI.Listener mSpeechServiceListener =
            new SpeechAPI.Listener() {
                @Override
                public void onSpeechRecognized(final String text, final boolean isFinal) {
                    if (isFinal) {
                        mVoiceRecorder.dismiss();
                    }
                    if (tv_voice_recog_result != null && !TextUtils.isEmpty(text)) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (isFinal) {
                                    Log.i("aaa", "is got here");
                                    Log.i("aaa", text);
                                    saveString += text;
                                    tv_voice_recog_result.setText(saveString);
                                } else {
                                    Log.i("aaa", "or got here");
                                    Log.i("aaa", text);
                                    tv_voice_recog_result.setText(saveString + text);
                                }
                            }
                        });
                    }
                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);
        initView();
    }

    public void initView() {
        tv_voice_recog_result = (TextView) findViewById(R.id.tv_voice_recog_result);
        tv_speak_type = (TextView) findViewById(R.id.tv_speak_type);

        speechAPI = new SpeechAPI(SpeechActivity.this);

        bt_start_voice_recog = (Button) findViewById(R.id.bt_start_voice_recog);
        bt_start_voice_recog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (bt_start_voice_recog.getText().toString() == getResources().getString(R.string.activity_speech_want)) {
                    Log.i("aaa", "started");

                    if (typeStatus == 0) {
                        Toast.makeText(getApplicationContext(), "발언 종류 선택 후 눌러주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        if (isGrantedPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                            Log.i("aaa", "where are you");
                            bt_start_voice_recog.setText(getResources().getString(R.string.activity_speech_ongoing));
                            startVoiceRecorder();
                            speechAPI.addListener(mSpeechServiceListener);

                            switch (typeStatus) {
                                case 1:
                                    tv_speak_type.setText(getResources().getString(R.string.dialog_speech_opinion));
                                    tv_speak_type.setBackgroundColor(Color.parseColor("#FFD700"));
                                    break;
                                case 2:
                                    tv_speak_type.setText(getResources().getString(R.string.dialog_speech_additional));
                                    tv_speak_type.setBackgroundColor(Color.parseColor("#63b2f7"));
                                    break;
                                case 3:
                                    tv_speak_type.setText(getResources().getString(R.string.dialog_speech_ask));
                                    tv_speak_type.setBackgroundColor(Color.parseColor("#32CD32"));
                                    break;
                                case 4:
                                    tv_speak_type.setText(getResources().getString(R.string.dialog_speech_answer));
                                    tv_speak_type.setBackgroundColor(Color.parseColor("#FF0000"));
                                    break;
                            }
                        } else {
                            Log.i("aaa", "are you here");
                            Toast.makeText(getApplicationContext(), "오디오 접근 허용 승인 후 사용해 주시기 바랍니다.", Toast.LENGTH_LONG).show();
                            makeRequest(Manifest.permission.RECORD_AUDIO);
                        }
                    }

                } else if (bt_start_voice_recog.getText().toString() == getResources().getString(R.string.activity_speech_ongoing)) {
                    bt_start_voice_recog.setText(getResources().getString(R.string.activity_speech_want));
                    typeStatus = 0;
                    tv_speak_type.setText(getResources().getString(R.string.activity_speech_ready));
                    tv_speak_type.setBackgroundColor(getResources().getColor(R.color.littleDark));

                    /* send data */
                    //sendData(tv_voice_recog_result.getText().toString());

                    rg_speech_type.clearCheck();
                }

            }
        });

        rg_speech_type = (RadioGroup) findViewById(R.id.rg_speech_type);
        rg_speech_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.rb_speech_type_1:
                        typeStatus = 1;
                        break;
                    case R.id.rb_speech_type_2:
                        typeStatus = 2;
                        break;
                    case R.id.rb_speech_type_3:
                        typeStatus = 3;
                        break;
                    case R.id.rb_speech_type_4:
                        typeStatus = 4;
                        break;
                    default:
                        typeStatus = 0;
                        break;
                }
            }
        });

        bt_go_participant = (Button) findViewById(R.id.bt_show_status);
        bt_go_participant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SpeechActivity.this, ParticipantActivity.class);
                startActivity(intent);
            }
        });

        bt_show_log = (Button) findViewById(R.id.bt_show_log);
        bt_show_log.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SpeechActivity.this, LogActivity.class);
                startActivity(intent);
            }
        });

    }



    /* For voice recognition! */

    @Override
    protected void onStop() {
        stopVoiceRecorder();

        // Stop Cloud Speech API
        if ((mSpeechServiceListener != null) && (speechAPI != null)) {
            speechAPI.removeListener(mSpeechServiceListener);
            speechAPI.destroy();
            speechAPI = null;
        }

        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isGrantedPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
        } else {
            Toast.makeText(getApplicationContext(), "오디오 접근 허용 승인 후 사용해 주시기 바랍니다.", Toast.LENGTH_LONG).show();
            makeRequest(Manifest.permission.RECORD_AUDIO);
        }
    }

    private int isGrantedPermission(String permission) {
        return ContextCompat.checkSelfPermission(this, permission);
    }

    private void makeRequest(String permission) {
        ActivityCompat.requestPermissions(this, new String[]{permission}, RECORD_REQUEST_CODE);
    }

    private void startVoiceRecorder() {
        if (mVoiceRecorder != null) {
            Log.i("aaa", "it means mVoiceRecorder is null");
            mVoiceRecorder.stop();
        }
        Log.i("aaa", "Recorder call");
        mVoiceRecorder = new VoiceRecorder(mVoiceCallback);
        mVoiceRecorder.start();
    }

    private void stopVoiceRecorder() {
        if (mVoiceRecorder != null) {
            mVoiceRecorder.stop();
            mVoiceRecorder = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == RECORD_REQUEST_CODE) {
            if (grantResults.length == 0 && grantResults[0] == PackageManager.PERMISSION_DENIED
                    && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                finish();
            } else {
                startVoiceRecorder();
            }
        }
    }
}