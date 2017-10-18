package com.base.testapp.base.app.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.widget.Toast;

import com.base.testapp.base.app.Constant;

import java.util.List;

/**
 * Created by admin on 1/6/2017.
 */

public class ShareUtils {

    public static void initShareFacebook(Context mContext, String urlToshare){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, urlToshare);
        boolean facebookAppFound = false;
        List<ResolveInfo> matches = mContext.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith(Constant.FACEBOOK_PACKAGE_NAME)) {
                intent.setPackage(info.activityInfo.packageName);
                facebookAppFound = true;
                break;
            }
        }
        if (!facebookAppFound) {
            try {
                Intent i= new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Constant.FACEBOOK_PACKAGE_NAME));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            } catch (android.content.ActivityNotFoundException anfe) {
                Intent i= new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + Constant.FACEBOOK_PACKAGE_NAME));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
            Toast.makeText(mContext, "Facebook app does not found", Toast.LENGTH_SHORT).show();
        }else{
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    }

    public static void initShareZaloApp(Context mContext, String urlToShare){
        Intent zaloIntent = new Intent(Intent.ACTION_SEND);
        zaloIntent.setType("text/plain");
        zaloIntent.putExtra(Intent.EXTRA_TEXT, urlToShare);
        PackageManager packManager = mContext.getPackageManager();
        List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(zaloIntent, PackageManager.MATCH_DEFAULT_ONLY);
        boolean resolved = false;
        for (ResolveInfo resolveInfo : resolvedInfoList) {
            if (resolveInfo.activityInfo.packageName.startsWith(Constant.ZALO_PACKAGE_NAME)) {
                zaloIntent.setClassName(
                        resolveInfo.activityInfo.packageName,
                        resolveInfo.activityInfo.name);
                resolved = true;
                break;
            }
        }
        if (resolved) {
            zaloIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(zaloIntent);
        } else {
            try {
                Intent i= new Intent(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Constant.ZALO_PACKAGE_NAME)));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            } catch (android.content.ActivityNotFoundException anfe) {
                Intent i= new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + Constant.ZALO_PACKAGE_NAME));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
            Toast.makeText(mContext, "Zalo app isn't found", Toast.LENGTH_LONG).show();
        }

    }

}
