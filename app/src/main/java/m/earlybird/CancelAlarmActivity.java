package m.earlybird;

import android.app.AlarmManager;
import android.app.Service;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import m.earlybird.model.QuestionModel;

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

        questions = QuestionModel.listAll(QuestionModel.class);


        size = questions.size();
        if(size > 0) {

            Random random = new Random(System.currentTimeMillis());
            index = random.nextInt(questions.size());
            textQuestion.setText(questions.get(index).getQuestion());
        }

        try {
            Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            r = RingtoneManager.getRingtone(getApplicationContext(), alarm);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @OnClick(R.id.button_dismiss)
    void onDismiss(){
        if(size == 0){
            r.stop();
            finish();
        }else if(editAnswer.getText().toString().equals(questions.get(index).getAnswer()) ){
            r.stop();
            finish();
        }
    }

    @OnClick(R.id.button_snooze)
    void onSnooze(){
        //TODO: Snooze
        r.stop();
    }


}
