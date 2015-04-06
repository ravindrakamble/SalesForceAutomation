package com.r2apps.sfa.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.r2apps.sfa.R;
import com.r2apps.sfa.adapters.ProductListAdapter;
import com.r2apps.sfa.adapters.ProductsAdapter;
import com.r2apps.sfa.adapters.ProductsGridAdapter;
import com.r2apps.sfa.dao.Product;
import com.r2apps.sfa.util.DataLoader;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView lstProducts;
    private ProductsAdapter productListAdapter;

    private GridView gridProducts;
    private ProductsGridAdapter productsGridAdapter;

    private ImageButton btnGrid, btnList;
    List<Product> purchasedProducts;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductsFragment newInstance(String param1, String param2) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_products, container, false);

        lstProducts = (ListView)view.findViewById(R.id.list_for_products);
        btnGrid = (ImageButton)view.findViewById(R.id.btn_grid);
        btnList = (ImageButton)view.findViewById(R.id.btn_list);
        gridProducts = (GridView)view.findViewById(R.id.grid_for_products);

        btnGrid.setOnClickListener(this);
        btnList.setOnClickListener(this);
        loadList();
        return view;
    }

    private void loadList(){
        gridProducts.setVisibility(View.GONE);
        lstProducts.setVisibility(View.VISIBLE);
        productsGridAdapter = null;
        productListAdapter = null;
        if(purchasedProducts == null)
        purchasedProducts = DataLoader.getListOfProducts();

        productListAdapter = new ProductsAdapter(getActivity(), R.layout.row_product_list, purchasedProducts);
        lstProducts.setAdapter(productListAdapter);
    }

    private void loadGrid(){
        lstProducts.setVisibility(View.GONE);
        gridProducts.setVisibility(View.VISIBLE);
        productsGridAdapter = null;
        productListAdapter = null;
        if(purchasedProducts == null)
            purchasedProducts = DataLoader.getListOfProducts();

        productsGridAdapter = new ProductsGridAdapter(getActivity(), R.layout.row_product_grid, purchasedProducts);
        gridProducts.setAdapter(productsGridAdapter);
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        Log.i("Button Click","ID : "+ v.getId());
        switch (v.getId()){
            case R.id.btn_grid:
                loadGrid();
                break;

            case R.id.btn_list:
                loadList();
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
