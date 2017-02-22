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

import java.util.ArrayList;

import tech.destinum.carmemo.R;
import tech.destinum.carmemo.adapters.ListAdapter;
import tech.destinum.carmemo.pojo.Category;

public class Selection extends Fragment {

    private CardView mCardView;
    private ListAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private ArrayList<Category> mCategoryArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root_view = inflater.inflate(R.layout.selection, container, false);
        mRecyclerView = (RecyclerView) root_view.findViewById(R.id.recycler_view);

        int[] covers = new int[]{
                R.drawable.moto,
                R.drawable.private_transport,
                R.drawable.publico};

        mCategoryArrayList = new ArrayList<>();
        mCategoryArrayList.add(new Category("Servicio Particular", "Si usted utiliza un vehículo de placas amarillas y de uso privado", covers[1]));
        mCategoryArrayList.add(new Category("Servicio Público", "Si usted utiliza un vehículo de placas blancas y de uso público", covers[2]));
        mCategoryArrayList.add(new Category("Motos", "Si usted utiliza una moto de 2 o 4 Tiempos", covers[0]));
        mAdapter = new ListAdapter(mContext, mCategoryArrayList);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        return root_view;
    }
}
