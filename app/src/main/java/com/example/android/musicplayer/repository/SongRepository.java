package com.example.android.musicplayer.repository;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.android.musicplayer.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SongRepository {
    public static final String songRepoTag = "model_tag";
    private static SongRepository instance;
    private static List<Song> mSongList = new ArrayList<>();
    private static List<Song> mAlbumList = new ArrayList<>();
    private static List<Song> mArtist = new ArrayList<>();

    private SongRepository(Context context){
        laodSongs(context);
    }

    public static SongRepository getInstance(Context context){
        if(instance == null)
            instance = new SongRepository(context);
        return instance;
    }
    public List<Song> getSongList() {
        return mSongList;
    }

    public static List<Song> getmAlbumList() {
        return mAlbumList;
    }

    public static List<Song> getmArtist() {
        return mArtist;
    }

    private void laodSongs(Context context) {
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        Cursor cursor = querySong(context, selection);
        SongCursorWrapper cursorWrapper = new SongCursorWrapper(cursor);
        try{
            if(cursorWrapper.getCount() == 0 )
                return;
            cursorWrapper.moveToFirst();
            do {
                mSongList.add(cursorWrapper.getSong());
            }while (cursorWrapper.moveToNext());

        }finally {
            cursorWrapper.close();
        }
    }

    private Cursor querySong(Context context, String selection) {
        ContentResolver mContentResolver = context.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        return mContentResolver.query(uri,
                                        null,
                                        null,
                                        null,
                                        null);
    }

    private void loadArtist(Context context){
        ContentResolver mContentResolver = context.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";

        Cursor cursor = querySong(context, selection);
        SongCursorWrapper cursorWrapper = new SongCursorWrapper(cursor);
        try{
            if(cursorWrapper.getCount() == 0 )
                return;
            cursorWrapper.moveToFirst();
            do {
                mSongList.add(cursorWrapper.getSong());
            }while (cursorWrapper.moveToNext());

        }finally {
            cursorWrapper.close();
        }
    }


    public Bitmap getAlbumImage(String path) {
        android.media.MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(path);
        byte[] data = mmr.getEmbeddedPicture();
        if (data != null) return BitmapFactory.decodeByteArray(data, 0, data.length);
        return null;
    }
}
