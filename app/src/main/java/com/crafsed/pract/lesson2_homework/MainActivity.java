package com.crafsed.pract.lesson2_homework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements UIFragment.OnFragmentDataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        UIFragment fragment = UIFragment.createFragment(1,Curr.RUB);
        UIFragment fragment1 = UIFragment.createFragment(2,Curr.USD);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.firstFrameLayout, fragment,"1");
        transaction.add(R.id.secondFrameLayout, fragment1,"2");
        transaction.commit();
    }

    @Override
    public void onFragmentDataListener(int id, Curr c, String tag) {
        if (tag.equals(UIFragment.TAG)) {
            BottomMenuDialog bottomMenuDialog = new BottomMenuDialog(c, id);
            bottomMenuDialog.show(getSupportFragmentManager(), "bottomSheet");
        }
        if (tag.equals(BottomMenuDialog.TAG)){
            UIFragment fragment = (UIFragment) getSupportFragmentManager().findFragmentByTag(Integer.toString(id));
            fragment.changeCurr(c);
            kostil();
        }
    }

    void kostil(){
        UIFragment fragment = (UIFragment) getSupportFragmentManager().findFragmentByTag(Integer.toString(1));
        String s = String.valueOf(fragment.getEditView().getText());
        fragment.getEditView().setText("");
        fragment.getEditView().setText(s);
        fragment.getEditView().setSelection(s.length());
    }

    double usd = 73.52;
    double eur = 81.45;
    double jpy = 0.68;
    double rub = 1;

    String format(double a, Curr c){
        double b = 0;
        switch (c){
            case USD:
                b = a/usd;
                break;
            case RUB:
                b = a/rub;
                break;
            case JPY:
                b = a/jpy;
                break;
            case EUR:
                b = a/eur;
                break;
        }
        return new DecimalFormat("#0.0000").format(b);
    }
    @Override
    public void editTextChangeListener(int id, Curr c, String newText) {
        UIFragment fragment;
        if(id == 1){
            double x = 0;
            fragment = (UIFragment) getSupportFragmentManager().findFragmentByTag(Integer.toString(2));
            if (!newText.isEmpty()) {
                switch (c) {
                    case EUR:
                        x = eur;
                        break;
                    case JPY:
                        x = jpy;
                        break;
                    case RUB:
                        x = rub;
                        break;
                    case USD:
                        x = usd;
                }
                String s = format(x * Double.parseDouble(newText), fragment.getCurr());
                fragment.changeText(s);
            } else {
                fragment.changeText("0.000");
            }
        }
        if(id == 2){
            fragment = (UIFragment) getSupportFragmentManager().findFragmentByTag(Integer.toString(1));
            fragment.changeText(newText);
        }

    }
}
