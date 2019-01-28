package com.example.android.musicplayer.controller;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.musicplayer.R;
import com.example.android.musicplayer.model.Song;
import com.example.android.musicplayer.repository.SongRepository;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MusicListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MusicListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mAllSongsRecView;
    private RecyclerView mArtistRecView;
    private RecyclerView mPlayListRecView;
    private TextView mAllSongsTxt;
    private TextView mArtistTxt;
    private TextView mPlayListTxt;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Song> mSongLists;

    private OnFragmentInteractionListener mListener;

    public MusicListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MusicListFragment.
     */
    public static MusicListFragment newInstance() {
        MusicListFragment fragment = new MusicListFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        SongRepository songRepository = new SongRepository(getActivity());
        mSongLists = songRepository.getSongList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music_list, container, false);
        mAllSongsRecView = view.findViewById(R.id.allsongs_recView);
        mArtistRecView = view.findViewById(R.id.artist_recView);
        mPlayListRecView = view.findViewById(R.id.playList_recView);
        //----
        mAllSongsTxt = view.findViewById(R.id.allsongs_txt);
        mArtistTxt = view.findViewById(R.id.artist_txt);
        mPlayListTxt = view.findViewById(R.id.playList_txt);

        mAllSongsRecView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        mAllSongsRecView.setAdapter(new AllSongsAdapter(mSongLists));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
    private class AllSongsHolder extends RecyclerView.ViewHolder{
        private CardView mCardView;
        private ImageView mImageView;

        private Song mSong;
        public AllSongsHolder(@NonNull View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.card_view);
            mImageView = itemView.findViewById(R.id.thumbnail);

        }
        public void bind(Song song){
            mSong = song;
            mCardView.setCardBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
            mImageView.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }
    private class AllSongsAdapter extends RecyclerView.Adapter<AllSongsHolder>{
        private List<Song> mSongList;

        public AllSongsAdapter(List<Song> songList) {
            mSongList = songList;
        }

        @NonNull
        @Override
        public AllSongsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater
                    .from(getActivity()).inflate(R.layout.music_list_item,viewGroup, false);
            return new AllSongsHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AllSongsHolder allSongsHolder, int i) {
            Song song = mSongList.get(i);
            allSongsHolder.bind(song);
        }

        @Override
        public int getItemCount() {
            return mSongList.size();
        }
    }
}
