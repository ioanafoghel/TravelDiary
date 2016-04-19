package com.example.ioana.traveldiaryapp.storage;

import com.example.ioana.traveldiaryapp.model.Trip;

import java.util.ArrayList;

/**
 * Created by Ioana on 15/04/2016.
 */
public class Storage {
    private ArrayList<Trip> trips = new ArrayList<>();
    private static Storage uniqueInstance;

    private Storage() {
    }

    public static Storage getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Storage();
        }
        return uniqueInstance;
    }

    public ArrayList<Trip> getTrips() {
        return new ArrayList<Trip>(trips);
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public void removeTrip(Trip trip) {
        trips.remove(trip);
    }


    /*public void createSomeObjects() {
       String[] productNames = {"Kinder Bueno", "Kinder Chocolate 4", "Kinder Chocolate 8", "Kinder Country", "Kinder Delice", "Kinder Pinguin", "Kinder Schoko-Bons", "Kinder Surprise"};
        String[] productDescriptions = {"23 g", "100 g", "200 g", "30 g", "50 g", "100 g", "230 g", "12 g"};
        double[] productPrice = {15, 30, 40, 20, 23.9, 50, 50, 35};
        double[] offers = {12, 25, 38, 18, 20.9, 30, 30, 32};
        int[] imgs = {R.drawable.kinder_bueno, R.drawable.kinder_chocolate4, R.drawable.kinder_chocolate8, R.drawable.kinder_country, R.drawable.kinder_delice, R.drawable.kinder_pinguin, R.drawable.kinder_schoko_bons, R.drawable.kinder_surprise};
        String[] shopNames = {"Aldi", "Bilka", "FØtex", "Lidl", "Rema 1000", "Netto"};
        String[] shopsAddresses = {"AAaa", "BBb", "Ccc", "Ddd", "Eee", "ffff"};
        int[] shopsImgs = {R.drawable.aldi_icon, R.drawable.bilka_icon, R.drawable.fotex_icon, R.drawable.lidl_icon, R.drawable.rema_icon, R.drawable.netto_icon};
        String[] productListNames = {"Saturday snack", "Halloween  candy", "Kids rewards"};
      /*  ArrayList<Product> products = new ArrayList<Product>();
       for (int i = 0; i < productNames.length; i++) {
            Product product = uniqueInstance.createProduct(imgs[i], " " + productNames[i], " " + productDescriptions[i], productPrice[i], offers[i]);
            products.add(product);
        }
        ArrayList<Shop> shops = new ArrayList<Shop>();
        for (int i = 0; i < shopNames.length; i++) {
            Shop shop = uniqueInstance.createShop(shopsImgs[i], " " + shopNames[i], " " + shopsAddresses[i]);
            for (int j=0; j<productNames.length; j++){
                Product product = uniqueInstance.createProduct(imgs[j], " " + productNames[j], " " + productDescriptions[j], productPrice[j], offers[j]);
                shop.addProductInShop(product);
            }
            shops.add(shop);
        }
        ArrayList<ProductList> productLists = new ArrayList<ProductList>();
        for (int i = 0; i < productListNames.length; i++) {
            ProductList productList = uniqueInstance.createProductList(productListNames[i]);
            for (int j=0; j<productNames.length; j++){
                Product product = uniqueInstance.createProduct(imgs[j], " " + productNames[j], " " + productDescriptions[j], productPrice[j], offers[j]);
                productList.addProductInList(product);
            }
            productLists.add(productList);
        }

        Shop s1 = Service.createShop(R.drawable.bilka_icon, "Bilka", "BBbb");
        Product p1=Service.createProduct(R.drawable.kinder_bueno,"Kinder Bueno","23g",12.3,10.0);
        s1.addProductInShop(p1);
        ProductList l1=Service.createProductList("Christmas Shopping");
        l1.addProductInList(p1);
    }*/

     /*   Shop s1 = createShop(R.drawable.bilka_icon, "Bilka", "BBbb");
        Product p1 = createProduct(R.drawable.kinder_bueno, "Kinder Bueno", "23 g", 12.3, 10.0, 1);
        s1.addProductInShop(p1);
        ProductList l1 = createProductList("Christmas Shopping");*/


}
