package com.android.martino.customviewset.transitioncompat.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.martino.customviewset.R;
import com.android.martino.customviewset.transitioncompat.SharedElementTransitionManager;
import com.android.martino.customviewset.transitioncompat.activity.ToActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FromFragment extends android.support.v4.app.Fragment {

    @Bind(R.id.image_from_fragment)
    ImageView mFromImageView;

    public static FromFragment newInstance() {
        FromFragment fragment = new FromFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FromFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_from, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mFromImageView.getVisibility() == View.INVISIBLE) {
            mFromImageView.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.image_from_fragment)
    public void onClickFromImage() {

        int[] screenLocation = new int[2];
        mFromImageView.getLocationOnScreen(screenLocation);

        SharedElementTransitionManager.with(500);
        SharedElementTransitionManager sharedElementTransitionManager = SharedElementTransitionManager.get();
        sharedElementTransitionManager.setFromView(mFromImageView);

        ToActivity.startActivityWithSettingCompat(getActivity());
        mFromImageView.setVisibility(View.INVISIBLE);
    }
}
