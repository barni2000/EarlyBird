package m.earlybird.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import m.earlybird.CancelAlarmActivity;
import m.earlybird.receiver.AlarmReceiver;

public class AlarmIntentService extends IntentService {


    public AlarmIntentService() {
        super("AlarmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("WakeUP", "Alarm alarm");

        Intent intentStartActivity = new Intent(getApplicationContext(), CancelAlarmActivity.class);
        intentStartActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentStartActivity);

        AlarmReceiver.completeWakefulIntent(intent);

    }

}
