package com.android.martino.customviewset.transitioncompat.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.martino.customviewset.R;
import com.android.martino.customviewset.transitioncompat.SharedElementTransitionManager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class ToFragment extends android.support.v4.app.Fragment {

    @Bind(R.id.root_fragment_to)
    RelativeLayout mBackgroundView;
    @Bind(R.id.image_to_fragment)
    ImageView mToFragmentImageView;

    public static ToFragment newInstance() {
        ToFragment fragment = new ToFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public ToFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to, container, false);
        ButterKnife.bind(this, view);

        SharedElementTransitionManager sharedElementTransitionManager = SharedElementTransitionManager.get();
        sharedElementTransitionManager.applyTransitionToView(mToFragmentImageView, mBackgroundView, savedInstanceState);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
