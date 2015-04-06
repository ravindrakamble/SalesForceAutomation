package com.r2apps.sfa.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.androidplot.pie.PieChart;
import com.androidplot.pie.PieRenderer;
import com.androidplot.pie.Segment;
import com.androidplot.pie.SegmentFormatter;
import com.r2apps.sfa.R;
import com.r2apps.sfa.UiUpdator;
import com.r2apps.sfa.adapters.ProductListAdapter;
import com.r2apps.sfa.dao.Product;
import com.r2apps.sfa.http.RestResponse;
import com.r2apps.sfa.util.DataLoader;

import java.util.Collections;
import java.util.List;

/**
 * Created by ravindra.kambale on 2/24/2015.
 */
public class Dashboard extends Fragment implements UiUpdator {

    private View rootView = null;
    private PieChart pieDaily;
    private PieChart pieMonthly;

    private Segment s1;
    private Segment s2;
    private ListView lstProducts;
    private ProductListAdapter productListAdapter;

    private ListView lstLeastProducts;
    private ProductListAdapter leastProductListAdapter;
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dashboard, container, false);
        pieDaily = (PieChart)rootView.findViewById(R.id.mySimplePieChart);
        pieMonthly = (PieChart)rootView.findViewById(R.id.piechart_monthly);
        lstProducts = (ListView)rootView.findViewById(R.id.list_products);
        lstLeastProducts = (ListView)rootView.findViewById(R.id.list_products_least);
        new Handler().post(
                new Runnable() {
                    public void run() {
                        if(getActivity() != null){
                            getProducts();
                            drawPieChartDaily();
                            drawPieChartMonthly();
                        }
                    }
                });

        setRetainInstance(true);
        return rootView;
    }
    @Override
    public void updateUI(int requestCode, RestResponse.StatusCode responseCode, Object data) {

    }

    private void getProducts() {
        List<Product> purchasedProducts = DataLoader.getListOfProducts();

        productListAdapter = new ProductListAdapter(getActivity(), R.layout.only_product_row, purchasedProducts);
        lstProducts.setAdapter(productListAdapter);

        Collections.reverse(purchasedProducts);
        leastProductListAdapter = new ProductListAdapter(getActivity(), R.layout.only_product_row, purchasedProducts);
        lstLeastProducts.setAdapter(leastProductListAdapter);
    }


    public void drawPieChartDaily(){
        s1 = new Segment("70 %", 20);
        s2 = new Segment("30 %", 5);

        EmbossMaskFilter emf = new EmbossMaskFilter(
                new float[]{1, 1, 1}, 0.4f, 10, 8.2f);

        SegmentFormatter sf1 = new SegmentFormatter();
        sf1.configure(getActivity().getApplicationContext(), R.xml.pie_segment_formatter4);

        sf1.getFillPaint().setMaskFilter(emf);

        SegmentFormatter sf2 = new SegmentFormatter();
        sf2.configure(getActivity().getApplicationContext(), R.xml.pie_segment_formatter2);

        sf2.getFillPaint().setMaskFilter(emf);



        pieDaily.addSeries(s1, sf1);
        pieDaily.addSeries(s2, sf2);

        pieDaily.getBorderPaint().setColor(Color.TRANSPARENT);
        pieDaily.getBackgroundPaint().setColor(Color.WHITE);
        pieDaily.getRenderer(PieRenderer.class).setDonutSize(0.3f, PieRenderer.DonutMode.PERCENT);
        pieDaily.redraw();
    }

    public void drawPieChartMonthly(){
        s1 = new Segment("60 %", 20);
        s2 = new Segment("40 %", 5);

        EmbossMaskFilter emf = new EmbossMaskFilter(
                new float[]{1, 1, 1}, 0.4f, 10, 8.2f);

        SegmentFormatter sf1 = new SegmentFormatter();
        sf1.configure(getActivity().getApplicationContext(), R.xml.pie_segment_formatter4);

        sf1.getFillPaint().setMaskFilter(emf);

        SegmentFormatter sf2 = new SegmentFormatter();
        sf2.configure(getActivity().getApplicationContext(), R.xml.pie_segment_formatter2);

        sf2.getFillPaint().setMaskFilter(emf);



        pieMonthly.addSeries(s1, sf1);
        pieMonthly.addSeries(s2, sf2);

        pieMonthly.getBorderPaint().setColor(Color.TRANSPARENT);
        pieMonthly.getBackgroundPaint().setColor(Color.WHITE);
        pieMonthly.getRenderer(PieRenderer.class).setDonutSize(0.3f, PieRenderer.DonutMode.PERCENT);
        pieMonthly.redraw();
    }
}
