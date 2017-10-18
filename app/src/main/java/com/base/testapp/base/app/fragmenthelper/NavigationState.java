package com.base.testapp.base.app.fragmenthelper;

import android.os.Parcel;
import android.os.Parcelable;

public class NavigationState implements Parcelable {

    private final int placeHolder;
    private final String backStackName;

    public NavigationState(Parcel parcel){
        placeHolder = parcel.readInt();
        backStackName = parcel.readString();
    }

    public NavigationState(int type, String backStackName){
        this.placeHolder = type;
        this.backStackName = backStackName;
    }

    public NavigationState(int type){
        this(type, Integer.toString((int) (Integer.MAX_VALUE * Math.random())));
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(placeHolder);
        dest.writeString(backStackName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NavigationState> CREATOR = new Creator<NavigationState>() {
        @Override
        public NavigationState createFromParcel(Parcel in) {
            return new NavigationState(in);
        }

        @Override
        public NavigationState[] newArray(int size) {
            return new NavigationState[size];
        }
    };

    public int getPlaceHolder() {
        return placeHolder;
    }

    public String getBackStackName() {
        return backStackName;
    }
}
