package com.gimme.gimmeproject;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;


public class Permission {

    //To implement

    public class FragmentPermission<T extends Fragment>{
        T fragment;

        public FragmentPermission(T fragment){
            this.fragment = fragment;
        }

        public boolean isAllowed(Fragment fragment, Manifest.permission permission){
            return checkPermission(fragment.getActivity(),String.valueOf(permission));
        }


    }

    public class ActivitiPermission<T extends Activity>{
        T activity;

        public ActivitiPermission(T activity){
            this.activity = activity;
        }

        public boolean isAllowed(Activity activity, Manifest.permission permission){
            return checkPermission(activity,String.valueOf(permission));
        }

    }

    private boolean checkPermission(Activity activity, String permission){

        if(ContextCompat.checkSelfPermission(activity, permission)
                == PackageManager.PERMISSION_GRANTED){
            return true;
        }

        return false;
    }


}
