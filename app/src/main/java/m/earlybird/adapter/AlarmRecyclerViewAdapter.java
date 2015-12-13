package m.earlybird.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import m.earlybird.R;
import m.earlybird.fragment.AlarmFragment.OnListFragmentInteractionListener;
import m.earlybird.model.TimeModel;

public class AlarmRecyclerViewAdapter extends RecyclerView.Adapter<AlarmRecyclerViewAdapter.ViewHolder> {

    private final OnListFragmentInteractionListener mListener;
    private List<TimeModel> mValues;

    public AlarmRecyclerViewAdapter(OnListFragmentInteractionListener listener) {
        mListener = listener;
        mValues = TimeModel.listAll(TimeModel.class);

        for(TimeModel t : mValues){
            Log.d("TimeModel:",t.toString());
        }

        Log.d("Size:"," "+mValues.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alarmrow_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d("Index:"," "+mValues.get(position).toString());
        holder.mItem = mValues.get(position);
        holder.mAlarmText.setText(holder.mItem.toString());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public TimeModel mItem;
        public TextView mAlarmText;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mAlarmText =(TextView) view.findViewById(R.id.text_alarm_time);
        }
    }

    public void addItem(TimeModel model){
        model.save();
        mValues.add(model);
        notifyDataSetChanged();
    };

    public void removeItem(ViewHolder viewHolder){
        mValues.remove(viewHolder.mItem);
        viewHolder.mItem.delete();
        notifyDataSetChanged();
    }
}
