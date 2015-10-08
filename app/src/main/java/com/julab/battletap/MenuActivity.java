package com.julab.battletap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity
{
    // test commit github
    private ExpandListAdapter expandListAdapter;
    private ArrayList<ExpandListGroup> expandListItems;
    private ExpandableListView expandList;

    private final int SOLO_GROUP = 0;
        private final int FIGHT_THE_NUMBERS_CHILD = 0;

    private final int MULITPLAYER_GROUP = 1;
        private final int FIGHT_THE_TIMES_CHILD = 0;
        private final int FIGHT_THE_INCREMENT_CHILD = 1;
        private final int FIGHT_THE_BOTH_CHILD = 2;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /*
        Change la couleur de la barre de status

        Window window = getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(Color.parseColor("#000000"));
        */

        // Create all components for ExpandableListView
        expandList = (ExpandableListView) findViewById(R.id.expandableListSolo);
        expandListItems = SetStandardGroups();
        expandListAdapter = new ExpandListAdapter(MenuActivity.this, expandListItems);
        expandList.setAdapter(expandListAdapter);
        // set item listener
        expandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            {
                if(groupPosition == MULITPLAYER_GROUP && childPosition == FIGHT_THE_TIMES_CHILD)
                {
                    Toast.makeText(getApplicationContext(), "multi", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MenuActivity.this, BluetoothBattle.class);
                    startActivity(intent);
                }
                return true;
            }
        });

    }

    // Manage string menu of ExpandableListView
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
