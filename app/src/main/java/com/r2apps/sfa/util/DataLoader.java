package com.r2apps.sfa.util;

import com.r2apps.sfa.dao.Distributor;
import com.r2apps.sfa.dao.Product;
import com.r2apps.sfa.dao.Retailer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravindra.kambale on 3/2/2015.
 */
public class DataLoader {
    public static List<Product> getListOfProducts(){
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.name = "Licel";
        product.size = "20";
        product.unit = "ml";
        product.price = "35";
        product.promoPrice = "31";
        productList.add(product);

        product = new Product();
        product.name = "Licel";
        product.size = "50";
        product.unit = "ml";
        product.price = "70";
        product.promoPrice = "63";
        productList.add(product);

        product = new Product();
        product.name = "Nok 99 Liquid";
        product.size = "50";
        product.unit = "ml";
        product.price = "35";
        product.promoPrice = "32";
        productList.add(product);

        product = new Product();
        product.name = "Nok 99 Liquid";
        product.size = "1";
        product.unit = "Ltr";
        product.price = "240";
        product.promoPrice = "215";
        productList.add(product);

        product = new Product();
        product.name = "Nok Bait";
        product.size = "25";
        product.unit = "gms";
        product.price = "20";
        product.promoPrice = "18";
        productList.add(product);

        product = new Product();
        product.name = "Woodshield";
        product.size = "100";
        product.unit = "ml";
        product.price = "60";
        product.promoPrice = "54";
        productList.add(product);

        product = new Product();
        product.name = "Airoma";
        product.size = "320";
        product.unit = "ml";
        product.price = "120";
        product.promoPrice = "105";
        productList.add(product);

        return productList;
    }

    public static List<Retailer> getRetailers(){
        List<Retailer> retilerList = new ArrayList<>();
        Retailer retailer = new Retailer();

        for(int i = 0; i < 20; i++){
            retailer = new Retailer();
            retailer.setName("Retailer " + i);
            retailer.setAddress("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor");
            retilerList.add(retailer);
        }

        return retilerList;
    }

    public static List<Distributor> getDistributors(){
        List<Distributor> distList = new ArrayList<>();
        Distributor dist = null;

        for(int i = 0; i < 20; i++){
            dist = new Distributor();
            dist.setName("Distributor " + i);
            dist.setAddress("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor");
            distList.add(dist);
        }

        return distList;
    }
}
