package m.earlybird;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import m.earlybird.fragment.DetailsFragment;

public class DetailsActivity extends AppCompatActivity {

    public static final String KEY_DETAILS_BUNDLE = "bundleDetails";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if(savedInstanceState == null){
            Bundle arguments = getIntent().getBundleExtra(KEY_DETAILS_BUNDLE);
            DetailsFragment fragment = new DetailsFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.detail_container,fragment)
                    .commit();

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, AlarmActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
