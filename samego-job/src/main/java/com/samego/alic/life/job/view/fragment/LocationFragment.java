package com.samego.alic.life.job.view.fragment;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.GoogleApiClient;
import com.samego.alic.life.helper.R;


public class LocationFragment extends Fragment {
    private GoogleApiClient mGoogleApiClient;
    private boolean mLocationPermissionGranted;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.main_content_location, container, false);
    }
}
