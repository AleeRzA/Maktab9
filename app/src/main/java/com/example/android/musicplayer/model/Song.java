package com.example.android.musicplayer.model;

public class Song {
    private Long mID;
    private String mTitle;
    private int mDuration;
    private String mArtistName;
    private String mAlbumName;
    private int mTrackNum;
    private int mImgPath;

    public Song(Long ID, String title) {
        mID = ID;
        mTitle = title;
    }

    public Long getID() {
        return mID;
    }


    public String getTitle() {
        return mTitle;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public void setArtistName(String artistName) {
        mArtistName = artistName;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public void setAlbumName(String albumName) {
        mAlbumName = albumName;
    }

    public int getTrackNum() {
        return mTrackNum;
    }

    public void setTrackNum(int trackNum) {
        mTrackNum = trackNum;
    }
    public int getImgPath() {
        return mImgPath;
    }

    public void setImgPath(int imgPath) {
        mImgPath = imgPath;
    }
}
