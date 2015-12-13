package m.earlybird;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import m.earlybird.adapter.AlarmRecyclerViewAdapter;
import m.earlybird.fragment.AlarmCreateFragment;
import m.earlybird.fragment.AlarmFragment;
import m.earlybird.fragment.DetailsFragment;
import m.earlybird.model.TimeModel;

public class AlarmActivity
        extends AppCompatActivity
        implements AlarmFragment.OnListFragmentInteractionListener
{

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        ButterKnife.bind(this);

        if(findViewById(R.id.detail_container) != null){
            mTwoPane = true;
            AlarmFragment alarmFragment = (AlarmFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.alarm);
        }
    }

    @OnClick(R.id.button_add_alarm)
    void addAlarm(){
        AlarmFragment alarmFragment = (AlarmFragment) getSupportFragmentManager()
                .findFragmentById(R.id.alarm);
        alarmFragment.showAlarmCreate();
    }

    @Override
    public void onListFragmentInteraction(TimeModel item) {
        Bundle arguments = new Bundle();
        arguments.putString(DetailsFragment.KEY_ALARM_TIME, item.toString());
        arguments.putBoolean(DetailsFragment.KEY_ALARM_SNOOZE, item.getSnooze());
        arguments.putSerializable(DetailsFragment.KEY_ALARM_REPEAT, item.getRepeat());

        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.


            DetailsFragment fragment = new DetailsFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, DetailsActivity.class);
            detailIntent.putExtra(DetailsActivity.KEY_DETAILS_BUNDLE,arguments);
            startActivity(detailIntent);
        }
    }

}
