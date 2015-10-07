package com.julab.battletap;

import java.util.ArrayList;

/**
 * Created by julien on 05-10-15.
 */
public class ExpandListGroup
{
    private String name;
    private ArrayList<ExpandListChild> items;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<ExpandListChild> getItems()
    {
        return items;
    }

    public void setItems(ArrayList<ExpandListChild> items)
    {
        this.items = items;
    }
}
