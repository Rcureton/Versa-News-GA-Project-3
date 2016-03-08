package com.adi.ho.jackie.versa_news.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adi.ho.jackie.versa_news.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechFragment extends Fragment {

    private RecyclerView mTechRecyclerView;


    public TechFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tech, container, false);
        mTechRecyclerView = (RecyclerView)view.findViewById(R.id.tech_recycler_list);

        return view;
    }

}
