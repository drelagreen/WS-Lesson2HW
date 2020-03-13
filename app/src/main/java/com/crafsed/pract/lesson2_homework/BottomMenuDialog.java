package com.crafsed.pract.lesson2_homework;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomMenuDialog extends BottomSheetDialogFragment {
    static final String TAG = "bottom_sheet" ;
    private Curr mCurr;
    private int id;
    BottomMenuDialog(Curr c, int id){
        mCurr = c;
        this.id = id;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_bottom_menu, container, false);
        RadioButton rub = v.findViewById(R.id.rubButton);
        RadioButton usd = v.findViewById(R.id.usdButton);
        RadioButton jpy = v.findViewById(R.id.jpyButton);
        RadioButton eur = v.findViewById(R.id.eurButton);

        RadioGroup rg = v.findViewById(R.id.radio_group);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case (R.id.rubButton):
                        mCurr = Curr.RUB;
                        break;
                    case (R.id.jpyButton):
                        mCurr = Curr.JPY;
                        break;
                    case (R.id.usdButton):
                        mCurr = Curr.USD;
                        break;
                    case (R.id.eurButton):
                        mCurr = Curr.EUR;
                        break;
                }
                mListener.onFragmentDataListener(id,mCurr, TAG);
            }
        });
        switch (mCurr.toString()) {
            case "RUB":
                rub.toggle();
                break;
            case "JPY":
                jpy.toggle();
                break;
            case "USD":
                usd.toggle();
                break;
            case "EUR":
                eur.toggle();
                break;
        }
        return v;
    }
    UIFragment.OnFragmentDataListener mListener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UIFragment.OnFragmentDataListener) {
            mListener = (UIFragment.OnFragmentDataListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragment1DataListener");
        }
    }
}
