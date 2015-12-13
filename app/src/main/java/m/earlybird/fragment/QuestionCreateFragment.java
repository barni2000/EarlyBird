package m.earlybird.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import m.earlybird.R;
import m.earlybird.model.QuestionModel;

public class QuestionCreateFragment extends DialogFragment {
    public static final String TAG = "QuestionCreateFragment";

    @Bind(R.id.text_question)
    EditText textQuestion;

    @Bind(R.id.text_anwser)
    EditText textAnwser;

    // Listener
    private IQuestionCreateFragment listener;

    public interface IQuestionCreateFragment {
        void onQuestionCreated(QuestionModel model);
    }

    public QuestionCreateFragment(){}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (getTargetFragment() != null) {
            try {
                listener = (IQuestionCreateFragment) getTargetFragment();
            } catch (ClassCastException ce) {
                Log.e(TAG,
                        "Target Fragment does not implement fragment interface!");
            } catch (Exception e) {
                Log.e(TAG, "Unhandled exception!");
                e.printStackTrace();
            }
        } else {
            try {
                listener = (IQuestionCreateFragment) activity;
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
        View root = inflater.inflate(R.layout.questioncreate_view, null);
        ButterKnife.bind(this, root);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(root)
                // Add action buttons
                .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onQuestionCreated(
                                new QuestionModel(
                                        textQuestion.getText().toString(),
                                        textAnwser.getText().toString()
                                )
                        );
                    }
                })
                .setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Unused
                    }
                });
        return builder.create();
    }

}
