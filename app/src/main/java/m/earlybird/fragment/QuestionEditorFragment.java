package m.earlybird.fragment;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import m.earlybird.adapter.QuestionEditorRecyclerViewAdapter;
import m.earlybird.R;
import m.earlybird.model.QuestionModel;

public class QuestionEditorFragment extends PreferenceFragment implements QuestionCreateFragment.IQuestionCreateFragment{
    private QuestionEditorRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QuestionEditorFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.questioneditor_view, container, false);

        ButterKnife.bind(this, view);

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.question_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new QuestionEditorRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                adapter.removeItem((QuestionEditorRecyclerViewAdapter.ViewHolder)viewHolder);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return view;
    }

    @OnClick(R.id.button_add_question)
    void addItem(){
        QuestionCreateFragment createFragment = new QuestionCreateFragment();
        createFragment.setTargetFragment(this,0);
        FragmentManager fm = getFragmentManager();
        createFragment.show(fm, AlarmCreateFragment.TAG);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onQuestionCreated(QuestionModel model) {
        adapter.addItem(model);
    }
}
