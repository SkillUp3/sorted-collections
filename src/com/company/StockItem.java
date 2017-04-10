package com.company;

/**
 * Created by idejesus on 09/04/2017.
 */
public class StockItem implements  Comparable<StockItem> {
    private final String name;
    private double price;
    private int quantityStock;
    private int reserved = 0;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock = 0;
    }

//    public int reserved(){
//        return reserved;
//    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


    public int getQuantityStock() {
        return quantityStock;
    }

    public void setPrice(double price){
        if(price >0.0){
            this.price = price;
        }
    }

    public void adjustStock(int quantity){
        int newQuantity = this.quantityStock + quantity;
        if(newQuantity>=0){
            this.quantityStock = newQuantity;
        }
    }

    public  int availableQuantity(){
        return quantityStock - reserved;
    }

    public int reserveStock(int quantity){
        if(quantity <= availableQuantity()){
            reserved += quantity;
            return quantity;
        }
        return  0;
    }

    public int unreserveStock(int quantity){
        if(quantity <= reserved){
            reserved -= quantity;
            return quantity;
        }
        return 0;
    }

    public int finaliseStock(int quantity){
        if (quantity <= reserved){
            quantityStock -= quantity;
            reserved -= quantity;
            return quantity;
        }
        return 0;
    }



    @Override
    public int hashCode() {
        return this.name.hashCode() + 31;
    }


    //StockItem a = new StockItem();
    //StockItem b = new StockItem();
    //a.equals(b)

    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering StockItem.equals");

        if(obj == this){
            return true;
        }

        if((obj == null) || obj.getClass() != this.getClass()){
            return false;
        }

        String objName = ((StockItem) obj).getName();
        return  this.name.equals(objName);
    }

    @Override
    public int compareTo(StockItem o) {
//        System.out.println("Entering StockItem.compareTo");
        if(o == this){
            return 0;
        }

        if((o != null) ){
            return this.name.compareTo(o.getName());
        }

        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name + " : price " + this.price + ". Reserved: " + this.reserved;
    }
}
