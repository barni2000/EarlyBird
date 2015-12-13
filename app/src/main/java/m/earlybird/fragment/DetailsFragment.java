package m.earlybird.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import m.earlybird.R;
import m.earlybird.model.TimeModel;

public class DetailsFragment extends Fragment{

    public static final String TAG = "DetailsFragment";
    public static final String KEY_ALARM_TIME = "alarmTime";
    public static final String KEY_ALARM_SNOOZE = "alarmSnooze";
    public static final String KEY_ALARM_REPEAT = "alarmRepeat";

    @Bind(R.id.alarm_detail)
    TextView alarmDescription;

    private String alarmTime;
    private Boolean alarmSnooze;
    private ArrayList<Boolean> alarmRepeat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            if(getArguments() != null){
                 alarmTime = getArguments().getString(KEY_ALARM_TIME);
                 alarmSnooze = getArguments().getBoolean(KEY_ALARM_SNOOZE);
                 alarmRepeat =(ArrayList<Boolean>) getArguments().getSerializable(KEY_ALARM_REPEAT);

            }
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.details_view, container, false);

        ButterKnife.bind(this,root);

        alarmDescription.setText(
                "AlarmTime: "+alarmTime+"\nSnooze: "+alarmSnooze+"\nRepeat: "+alarmRepeat
        );
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
