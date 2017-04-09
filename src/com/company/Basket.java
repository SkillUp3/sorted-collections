package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by idejesus on 09/04/2017.
 */
public class Basket {
    private final String name;
    private final Map<StockItem, Integer> list;

    public  Basket(String name){
        this.name = name;
        this.list = new TreeMap<>();
    }

    public int addToBasket(StockItem item, int quantity){
        if((item!= null) && quantity >0){
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }

    @Override
    public String toString() {
        String s = "\nShopping Basket " + name + " contains " + list.size() + " items" + "\n";
        double totalCost = 0.0;

        for(Map.Entry<StockItem, Integer> item : list.entrySet()){
            s = s + item.getKey() + ". " + item.getValue() + " purchased\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost " + totalCost;
    }
}
