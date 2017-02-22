package tech.destinum.carmemo.fragments;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.destinum.carmemo.R;
import tech.destinum.carmemo.adapters.ListAdapter;

public class Selection extends Fragment {

    private CardView mCardView;
    private ListAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root_view = inflater.inflate(R.layout.selection, container, false);
        mRecyclerView = (RecyclerView) root_view.findViewById(R.id.recycler_view);

        mAdapter = new ListAdapter(mContext, mDBHelper.getAllCategories());

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        return root_view;
    }
}
