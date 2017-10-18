package com.base.testapp.base.app.fragmenthelper;

import android.support.v4.app.Fragment;

public interface TransactionManager {

    void doAddFragment(Fragment fragment);

    void doAddFragment(Fragment fragment, boolean isBackStack);

    void doReplaceFragment(Fragment fragment);

    void doReplaceFragment(Fragment fragment, boolean isBackStack);

    boolean doGoBack();

    Fragment getFragmentActive();
}
