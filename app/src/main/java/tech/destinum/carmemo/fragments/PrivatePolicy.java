package tech.destinum.carmemo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.destinum.carmemo.R;
import tech.destinum.carmemo.activities.BaseActivity;

public class PrivatePolicy extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.privatepolicy, container, false);
    }
}
