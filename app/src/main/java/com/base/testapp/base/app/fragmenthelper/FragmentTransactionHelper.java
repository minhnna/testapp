package com.base.testapp.base.app.fragmenthelper;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class FragmentTransactionHelper implements TransactionManager {

    private int idPlaceHolder;
    private AppCompatActivity activity;
    private FragmentManager fragmentManager;

    public FragmentTransactionHelper(AppCompatActivity activity, @IdRes int idPlaceHolder) {

        this.activity = activity;
        this.idPlaceHolder = idPlaceHolder;
        fragmentManager = activity.getSupportFragmentManager();
    }

    /**
     *
     * @param fragment to do add fragment
     * @param addToBackStack using for add to back stack
     */
    private void addFragment(Fragment fragment, boolean addToBackStack){
        if(isReadyTransaction()){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if(addToBackStack){
                NavigationState navigationState = new NavigationState(idPlaceHolder);
                fragmentTransaction.add(idPlaceHolder, fragment).addToBackStack(navigationState.getBackStackName()).commit();
            }else {
                fragmentTransaction.add(idPlaceHolder, fragment).commit();
            }
        }
    }

    /**
     *
     * @param fragment
     * @param addToBackStack
     */
    private void replaceFragment(Fragment fragment, boolean addToBackStack){
        if(isReadyTransaction()){
            FragmentTransaction fragmentTRansaction = fragmentManager.beginTransaction();
            if(addToBackStack){
                fragmentTRansaction.replace(idPlaceHolder, fragment).addToBackStack("3").commit();
            }else{
                fragmentTRansaction.replace(idPlaceHolder, fragment).commit();
            }
        }
    }

    public boolean goBack(){
        if(isReadyTransaction() && getBackStackEntryCount() > 0){
            fragmentManager.popBackStack();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            Fragment currentFragment = fragmentManager.findFragmentById(idPlaceHolder);
            if(currentFragment != null){
                transaction.remove(currentFragment);
                transaction.commit();
            }
            return true;
        }
        return false;
    }

    /**
     * @return count entry
     */
    public int getBackStackEntryCount() {
        return fragmentManager.getBackStackEntryCount();
    }

    /**
     * Terminate resource that keep by this class. Must call in
     * {@link Activity#onDestroy()}
     */
    public void terminate() {
        fragmentManager = null;
        activity = null;
    }

    @Override
    public void doAddFragment(Fragment fragment) {
        addFragment(fragment, true);
    }

    @Override
    public void doAddFragment(Fragment fragment, boolean isBackStack) {
        addFragment(fragment, isBackStack);
    }

    @Override
    public void doReplaceFragment(Fragment fragment) {
        replaceFragment(fragment, true);
    }

    @Override
    public void doReplaceFragment(Fragment fragment, boolean isBackStack) {
        replaceFragment(fragment, isBackStack);
    }

    @Override
    public boolean doGoBack() {
        return goBack();
    }

    @Override
    public Fragment getFragmentActive() {
        return fragmentManager.findFragmentById(idPlaceHolder);
    }

    private boolean isReadyTransaction(){
        return activity != null && !activity.isFinishing() && activity.getSupportFragmentManager() != null;
    }
}
