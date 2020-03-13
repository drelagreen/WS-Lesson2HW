package com.crafsed.pract.lesson2_homework;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class UIFragment extends Fragment {
    private TextView mTextView;
    private EditText mEditView;
    private Curr curr;
    private int id = 0;
    static final String TAG = "ui_fragment";

    public interface OnFragmentDataListener{
        void onFragmentDataListener(int id, Curr c, String tag);
        void editTextChangeListener(int id, Curr c, String newText);
    }


    private OnFragmentDataListener mFragmentDataListener;
    private OnFragmentDataListener mEditTextChangeListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.layout_first_window, container, false);
        mEditView = v.findViewById(R.id.editText);
        mTextView = v.findViewById(R.id.textView);
        mTextView.setText(curr.toString());
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentDataListener.onFragmentDataListener(id, curr, TAG);
            }
        });
        if (id == 1)
        mEditView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mEditView.isFocused()){
                    mEditTextChangeListener.editTextChangeListener(id, curr, s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        if (id == 2){
            mEditView.setEnabled(false);
        }
        return v;
    }
    void changeCurr(Curr c){
        this.mTextView.setText(c.toString());
        this.curr = c;
    }
    void changeText(String newText){
        mEditView.setText(newText);
    }

    public static UIFragment createFragment(int id, Curr c){
        UIFragment uiFragment = new UIFragment();
        uiFragment.curr = c;
        uiFragment.id = id;
        return uiFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentDataListener) {
            mFragmentDataListener = (OnFragmentDataListener) context;
            mEditTextChangeListener = (OnFragmentDataListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragment1DataListener");
        }
    }

    public Curr getCurr() {
        return curr;
    }

    public EditText getEditView() {
        return mEditView;
    }
}
