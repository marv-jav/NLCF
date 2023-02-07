package com.tmmarv.nlcf.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmmarv.nlcf.BooksActivity.OneHundredActivity;
import com.tmmarv.nlcf.BooksActivity.SpiritualActivity;
import com.tmmarv.nlcf.BooksActivity.ThreeHundredActivity;
import com.tmmarv.nlcf.BooksActivity.TwoHundredActivity;
import com.tmmarv.nlcf.R;
public class BooksFragment extends Fragment {

    private CardView spiritual;
    private CardView one;
    private CardView two;
    private CardView three;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books, container, false);

        spiritual = view.findViewById(R.id.spiritual_books);
        one = view.findViewById(R.id.one_hundred);
        two = view.findViewById(R.id.two_hundred);
        three = view.findViewById(R.id.three_hundred);

        spiritual.setOnClickListener(v -> startActivity(new Intent(getActivity(), SpiritualActivity.class)));
        one.setOnClickListener(v -> startActivity(new Intent(getActivity(), OneHundredActivity.class)));
        two.setOnClickListener(v -> startActivity(new Intent(getActivity(), TwoHundredActivity.class)));
        three.setOnClickListener(v -> startActivity(new Intent(getActivity(), ThreeHundredActivity.class)));

        return view;
    }
}