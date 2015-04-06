package com.r2apps.sfa.util;

/**
 * Created by ravindra.kambale on 2/25/2015.
 */
public interface AppConstants {
    int DASHBOARD = 0;
    int DISTRIBUTOR = 1;
    int PRODUCTS = 2;
    int RETAILERS = 3;
    int PREFERENCES = 4;
    int ORDERS = 5;
    int ADD_RETAILER = 6;
    int STORES = 7;

    int DISTANCE_IN_METERS = 5000;
    String LOCATION = "12.8897331,77.5780595";
    String API_KEY = "AIzaSyAIpkRMJlyDbUuklzrcMX9jq_PWW91lBBk";
    String PLACES_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
            + LOCATION
            + "&radius=" + DISTANCE_IN_METERS
            + "&types=grocery_or_supermarket" +
            "&key=" + API_KEY;
}
