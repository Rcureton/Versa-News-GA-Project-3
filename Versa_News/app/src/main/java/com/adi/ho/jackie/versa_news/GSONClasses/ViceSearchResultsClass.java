package com.adi.ho.jackie.versa_news.GSONClasses;

import java.util.ArrayList;

/**
 * Created by Rob on 3/7/16.
 */
public class ViceSearchResultsClass {

    private ArrayList<ViceDataClass> items;

    public void setItems(ArrayList<ViceDataClass> items){
        this.items = items;
    }

    public ArrayList<ViceDataClass> getItems(){
        return items;
    }

}
