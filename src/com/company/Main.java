package com.company;

import java.util.Map;

public class Main {
        /*

    Modify the program so that adding items to the shopping basket doesn't
    actually reduce the stock count but, instead, reserves the requested
    number of items.

    You will need to add a "reserved" field to the StockItem class to store the
    number of items reserved.

    Items can continue to be added to the basket, but it should not be possible to
    reserve more than the available stock of any item. An item's available stock
    is the stock count less the reserved amount.

    The stock count for each item is reduced when a basket is checked out, at which
    point all reserved items in the basket have their actual stock count reduced.

    Once checkout is complete, the contents of the basket are cleared.

    It should also be possible to "unreserve" items that were added to the basket
    by mistake.

    The program should prevent any attempt to unreserve more items than were
    reserved for a basket.

    Add code to Main that will unreserve items after they have been added to the basket,
    as well as unreserving items that have not been added to make sure that the code
    can cope with invalid requests like that.

    After checking out the baskets, display the full stock list and the contents of each
    basket that you created.

     */


    private static StockList stockList = new StockList();

    public static void main(String[] args) {

        // write your code here
        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);


        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.addStock(temp);
        temp = new StockItem("cup", 0.45, 7);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);
//
//        System.out.println(stockList);
//
//        for (String s : stockList.items().keySet()) {
//            System.out.println(s);

        Basket basket = new Basket("Ian");
        reserveItem(basket, "car", 1);
//            System.out.println(basket);

        reserveItem(basket, "car", 1);
//            System.out.println(basket);

        if (reserveItem(basket, "car", 1) != 1) {
            System.out.println("There are no more cars in stock");
        }

        reserveItem(basket, "spanner", 5);
//            System.out.println(basket);


        reserveItem(basket, "juice", 4);
        reserveItem(basket, "cup", 12);
        reserveItem(basket, "bread", 1);

//            System.out.println(basket);

        Basket basketRap = new Basket("rap");
        reserveItem(basketRap, "cup", 100);
        reserveItem(basketRap, "juice", 5);
        removeItem(basketRap, "cup", 1);
//            System.out.println(basketRap);

        checkout(basketRap);
//            System.out.println(basketRap);

//        temp = new StockItem("pen", 1.12);
//        stockList.items().put(temp.getName(), temp);
//            stockList.items().get("car").adjustStock(2000);
//            stockList.items().get("car").adjustStock(-1000);
//            System.out.println(stockList);

    }


    private static int reserveItem(Basket basket, String item, int quantity) {
        //retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if (stockList.reserveStock(item, quantity) != 0) {
//            basket.addToBasket(stockItem, quantity);
            return basket.addToBasket(stockItem, quantity);
        }
        return 0;
    }

    private static int removeItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);

        if (stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }

        if (basket.removeFromBasket(stockItem, quantity) == quantity) {
            return stockList.unreserveStock(item, quantity);
        }
        return 0;
    }

    private static void checkout(Basket basket) {
        for (Map.Entry<StockItem, Integer> item : basket.items().entrySet()) {
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }
}
