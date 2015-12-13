package m.earlybird.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import m.earlybird.R;
import m.earlybird.model.TimeModel;

public class AlarmCreateFragment extends DialogFragment {
    public static final String TAG = "AlarmCreateFragment";

    @Bind(R.id.time_picker)
    TimePicker timePicker;

    @Bind( {
            R.id.day_monday,
            R.id.day_tuesday,
            R.id.day_wednesday,
            R.id.day_thursday,
            R.id.day_friday,
            R.id.day_saturday,
            R.id.day_sunday
    })
    List<ToggleButton> buttonDayList;

    @Bind(R.id.checkbox_snooze)
    CheckBox snooze;

    @Bind(R.id.text_utc)
    TextView textUtc;

    private int diffUtc = 0;

    // Listener
    private IAlarmCreateFragment listener;

    public interface IAlarmCreateFragment {
        void onAlarmCreated(TimeModel alarm);
    }

    public AlarmCreateFragment(){}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (getTargetFragment() != null) {
            try {
                listener = (IAlarmCreateFragment) getTargetFragment();
            } catch (ClassCastException ce) {
                Log.e(TAG,
                        "Target Fragment does not implement fragment interface!");
            } catch (Exception e) {
                Log.e(TAG, "Unhandled exception!");
                e.printStackTrace();
            }
        } else {
            try {
                listener = (IAlarmCreateFragment) activity;
            } catch (ClassCastException ce) {
                Log.e(TAG,
                        "Parent Activity does not implement fragment interface!");
            } catch (Exception e) {
                Log.e(TAG, "Unhandled exception!");
                e.printStackTrace();
            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View root = inflater.inflate(R.layout.alarmcreate_view, null);
        ButterKnife.bind(this,root);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(root)
                // Add action buttons
                .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ArrayList<Boolean> repeat = new ArrayList<Boolean>();
                        for(ToggleButton tb : buttonDayList){
                            repeat.add(tb.isChecked());
                        }
                        TimeModel alarm = new TimeModel(
                                timePicker.getCurrentHour(),
                                timePicker.getCurrentMinute(),
                                snooze.isChecked(),
                                repeat
                        );
                        listener.onAlarmCreated(alarm);
                    }
                })
                .setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Unused
                    }
                });
        return builder.create();
    }

    @OnClick(R.id.button_utc_minus)
    void utcDec(){
        if(diffUtc == -12){
            diffUtc = 12;
        }else{
            diffUtc --;
        }
        textUtc.setText("UTC "+diffUtc);
    }

    @OnClick(R.id.button_utc_plus)
    void utcInc(){
        if(diffUtc == 12){
            diffUtc = -12;
        }else{
            diffUtc ++;
        }
        textUtc.setText("UTC "+diffUtc);
    }

}
