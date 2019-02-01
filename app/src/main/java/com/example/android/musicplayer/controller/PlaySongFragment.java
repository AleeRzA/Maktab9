package com.example.android.musicplayer.controller;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.musicplayer.R;
import com.example.android.musicplayer.repository.SongRepository;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlaySongFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlaySongFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaySongFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_IMG = "param1";
    private static final String ARG_SONGID = "param2";

    private ImageView mImageView;
    private TextView mStartTime;
    private TextView mEndTime;
    private SeekBar mSeekBar;
    private String mImagePath;
    private Long mSongId;
    private MediaPlayer mMediaPlayer;

    private ImageButton mPlay;
    private ImageButton mPause;
    private ImageButton mStop;
    private ImageButton mBackward;
    private ImageButton mForward;
    private ImageButton mRefresh;
    private ImageButton mShuffle;


    private OnFragmentInteractionListener mListener;
    private Bitmap mBitmap;

    public PlaySongFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PlaySongFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlaySongFragment newInstance(String imgPath, Long songId) {
        PlaySongFragment fragment = new PlaySongFragment();
        Bundle args = new Bundle();
        args.putString(ARG_IMG, imgPath);
        args.putLong(ARG_SONGID, songId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImagePath = getArguments().getString(ARG_IMG);
            mSongId = getArguments().getLong(ARG_SONGID);
        }
        mBitmap = SongRepository.getInstance(getActivity()).getAlbumImage(mImagePath);

        Uri contentUri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, Long.valueOf(mImagePath));

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mMediaPlayer.setDataSource(getActivity(), contentUri);
        }catch (Exception e){
            e.getMessage();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play_song, container, false);
        mImageView = view.findViewById(R.id.imageView_clipArt);
        mImageView.setImageBitmap(mBitmap);

        mPlay = view.findViewById(R.id.playBtn_music);
        mPause = view.findViewById(R.id.pauseBtn_music);
        mPause.setVisibility(View.GONE);
        mBackward = view.findViewById(R.id.backwardBtn_music);
        mForward = view.findViewById(R.id.forwardBtn_music);
        mStop = view.findViewById(R.id.stopBtn_music);
        mRefresh = view.findViewById(R.id.reaptBtn_music);
        mShuffle = view.findViewById(R.id.shuffleBtn_music);

        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.start();
                Toast.makeText(getActivity(), "Play Button", Toast.LENGTH_SHORT).show();
                mPlay.setVisibility(View.GONE);
                mPause.setVisibility(View.VISIBLE);
            }
        });
        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.pause();

            }
        });
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.stop();
            }
        });
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
