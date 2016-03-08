package com.adi.ho.jackie.versa_news.GSONClasses;

import java.util.ArrayList;

/**
 * Created by Rob on 3/8/16.
 */
public class ViceDataClass {

    private ArrayList<ViceItemsClass> items;

    public void setItems(ArrayList<ViceItemsClass> items){
        this.items = items;
    }

    public ArrayList<ViceItemsClass> getItems(){
        return items;
    }

    @Override
    public String toString() {
        return items.size()+" item(s) in the search result";
    }
    
}
