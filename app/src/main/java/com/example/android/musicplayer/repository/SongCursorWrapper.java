package com.example.android.musicplayer.repository;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.provider.MediaStore;

import com.example.android.musicplayer.model.Song;

public class SongCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public SongCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Song getSong(){
        String artistName = getString(getColumnIndex(MediaStore.Audio.Media.ARTIST));
        String albumName = getString(getColumnIndex(MediaStore.Audio.Media.ALBUM));
        String songTitle = getString(getColumnIndex(MediaStore.Audio.Media.TITLE));
        int songDuration = getColumnIndex(MediaStore.Audio.Media.DURATION);
        Long idColumn =
                (getLong(getColumnIndex(MediaStore.Audio.Media._ID)));
        String filePathIndex = getString(getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
        int trackNumber = getColumnIndex(MediaStore.Audio.Media.TRACK);

        Song song = new Song(idColumn, songTitle);
        song.setArtistName(artistName);
        song.setAlbumName(albumName);
        song.setDuration(songDuration);
        song.setTrackNum(trackNumber);
        song.setImgPath(filePathIndex);

        return song;
    }
}
