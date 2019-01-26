package com.example.android.musicplayer.controller;

import android.net.Uri;
import android.support.v4.app.Fragment;

public class MusicListActivity
        extends SingleFragmentActivity
        implements MusicListFragment.OnFragmentInteractionListener {


    @Override
    public Fragment getFragment() {
        return MusicListFragment.newInstance();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
