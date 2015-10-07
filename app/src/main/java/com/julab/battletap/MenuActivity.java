package com.julab.battletap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity
{
    private ExpandableListView listSolo, listMultiplayer;
    private ExpandableListAdapter adapterSolo, adapterMulti;
    private List<String> soloList, multiList;

    /*@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Initialisation List
        soloList = new ArrayList<String>();
        soloList.add("Fight the numbers");
        String[] catSolo = soloList.toArray(new String[soloList.size()]);
        multiList = new ArrayList<String>();
        multiList.add("Fight the time");
        multiList.add("Fight the increment");
        multiList.add("Fight the time & increment");
        String[] catMulti = multiList.toArray(new String[multiList.size()]);

        listSolo = (ExpandableListView) findViewById(R.id.expandableListSolo);
        //adapterSolo.
        listSolo.setAdapter(adapterSolo);


    }*/

    private ExpandListAdapter ExpAdapter;
    private ArrayList<ExpandListGroup> ExpListItems;
    private ExpandableListView ExpandList;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ExpandList = (ExpandableListView) findViewById(R.id.expandableListSolo);
        ExpListItems = SetStandardGroups();
        ExpAdapter = new ExpandListAdapter(MenuActivity.this, ExpListItems);
        ExpandList.setAdapter(ExpAdapter);
    }

    public ArrayList<ExpandListGroup> SetStandardGroups()
    {

        ArrayList<ExpandListGroup> listGroup = new ArrayList<ExpandListGroup>();
        ArrayList<ExpandListChild> listChild = new ArrayList<ExpandListChild>();

        ExpandListGroup group1 = new ExpandListGroup();
        group1.setName("Solo");

        ExpandListChild child1Group1 = new ExpandListChild();
        child1Group1.setName("• Fight the numbers");
        child1Group1.setTag(null);

        listChild.add(child1Group1);
        group1.setItems(listChild);

        listChild = new ArrayList<ExpandListChild>();


        ExpandListGroup group2 = new ExpandListGroup();
        group2.setName("Multiplayer");

        ExpandListChild child1Group2 = new ExpandListChild();
        child1Group2.setName("• Fight the times");
        child1Group2.setTag(null);

        listChild.add(child1Group2);

        ExpandListChild child2Group2 = new ExpandListChild();
        child2Group2.setName("• Fight the increment");
        child2Group2.setTag(null);

        listChild.add(child2Group2);

        ExpandListChild child3Group2 = new ExpandListChild();
        child3Group2.setName("• Fight both");
        child3Group2.setTag(null);

        listChild.add(child3Group2);

        group2.setItems(listChild);

        listGroup.add(group1);
        listGroup.add(group2);


        return listGroup;

    }

}
