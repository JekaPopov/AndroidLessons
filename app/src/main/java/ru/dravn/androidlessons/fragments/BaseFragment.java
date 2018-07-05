package ru.dravn.androidlessons.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import ru.dravn.androidlessons.R;


public class BaseFragment extends Fragment {




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    protected void showMessage(String mMessage) {
        Toast.makeText(getActivity(), mMessage, Toast.LENGTH_LONG).show();
    }

}
