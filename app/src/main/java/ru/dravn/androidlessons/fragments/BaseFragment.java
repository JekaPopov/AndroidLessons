package ru.dravn.androidlessons.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import com.squareup.leakcanary.RefWatcher;

import java.util.HashMap;

import ru.dravn.androidlessons.App;
import ru.dravn.androidlessons.MainActivity;


public class BaseFragment extends Fragment {




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    protected void showFragment(Class fragmentClass, HashMap<String, String> message)
    {
        ((MainActivity) getActivity()).showFragment(fragmentClass, message);
    }

    protected void showMessage(String mMessage) {
        Toast.makeText(getActivity(), mMessage, Toast.LENGTH_LONG).show();
    }

    protected void showMessage(int mMessage) {
        Toast.makeText(getActivity(), mMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = App.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

}
