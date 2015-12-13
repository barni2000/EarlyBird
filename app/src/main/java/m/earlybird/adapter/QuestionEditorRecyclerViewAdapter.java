package m.earlybird.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import m.earlybird.R;
import m.earlybird.model.QuestionModel;

import java.util.List;

public class QuestionEditorRecyclerViewAdapter extends RecyclerView.Adapter<QuestionEditorRecyclerViewAdapter.ViewHolder> {

    private final List<QuestionModel> mValues;

    public QuestionEditorRecyclerViewAdapter() {
        mValues = QuestionModel.listAll(QuestionModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mQuestionItem.setText(holder.mItem.getQuestion());
        holder.mAnswerItem.setText(holder.mItem.getAnswer());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mQuestionItem;
        public final TextView mAnswerItem;
        public QuestionModel mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mQuestionItem = (TextView) view.findViewById(R.id.question_item);
            mAnswerItem = (TextView) view.findViewById(R.id.anwser_item);
        }
    }

    public void addItem(QuestionModel model){
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
