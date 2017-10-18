package com.base.testapp.base.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.base.testapp.base.app.fragmenthelper.FragmentHost;
import com.base.testapp.base.app.fragmenthelper.TransactionManager;

public abstract class BaseFragment extends Fragment {

    public Activity activity;
    public TransactionManager transactionManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            activity = (Activity) context;
        }
        if (context instanceof FragmentHost) {
            FragmentHost fragmentHost = (FragmentHost) context;
            transactionManager = fragmentHost.getTransactionManager();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        if (!hasActionbar()) {
            initView(view);
            return view;
        } else {
            //handle add action bar
            return null;
        }
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void initView(View view);

    protected boolean hasActionbar() {
        return false;
    }

    protected void initData() {}
}
