package m.earlybird;


import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import m.earlybird.model.QuestionModel;
import m.earlybird.receiver.AlarmReceiver;

public class CancelAlarmActivity extends AppCompatActivity {

    @Bind(R.id.text_dialog_question)
    TextView textQuestion;
    @Bind(R.id.edit_dialog_anwser)
    EditText editAnswer;

    private List<QuestionModel> questions;
    private Integer index;
    private Ringtone r;
    int size = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_alarm);

        ButterKnife.bind(this);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        boolean enable_question = sp.getBoolean("alarm_enable_questions", true);

        if(enable_question){
            questions = QuestionModel.listAll(QuestionModel.class);
            textQuestion.setVisibility(View.VISIBLE);
            editAnswer.setVisibility(View.VISIBLE);


            size = questions.size();
            if(size > 0) {

                Random random = new Random(System.currentTimeMillis());
                index = random.nextInt(questions.size());
                textQuestion.setText(questions.get(index).getQuestion());
            }


        }else{
            textQuestion.setVisibility(View.GONE);
            editAnswer.setVisibility(View.GONE);
        }

        try {
            Uri alert =  RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            MediaPlayer mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(this, alert);
            final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mMediaPlayer.setLooping(true);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            }
        } catch(Exception e) {
        }



    }

    @OnClick(R.id.button_dismiss)
    void onDismiss(){
        if(size == 0){
            r.stop();
            finish();
            AlarmReceiver.cancelAlarm(this);
        }else if(editAnswer.getText().toString().equals(questions.get(index).getAnswer()) ){
            r.stop();
            finish();
            AlarmReceiver.cancelAlarm(this);
        }
    }

    @OnClick(R.id.button_snooze)
    void onSnooze(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        int interval = Integer.valueOf(sp.getString("alarm_snooze","5"));
        AlarmReceiver.snooze(interval);
        r.stop();
        finish();
    }


}
