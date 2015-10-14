package com.julab.battletap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ExpandListAdapter extends BaseExpandableListAdapter
{
    private Context context;
    private ArrayList<ExpandListGroup> groups;

    public ExpandListAdapter(Context context, ArrayList<ExpandListGroup> groups)
    {
        this.context = context;
        this.groups = groups;
    }

    public void addItem(ExpandListChild item, ExpandListGroup group)
    {
        if (!groups.contains(group))
        {
            groups.add(group);
        }

        int index = groups.indexOf(group);
        ArrayList<ExpandListChild> child = groups.get(index).getItems();
        child.add(item);
        groups.get(index).setItems(child);
    }

    @Override
    public int getGroupCount()
    {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        ArrayList<ExpandListChild> chList = groups.get(groupPosition).getItems();
        return chList.size();
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        ArrayList<ExpandListChild> childList = groups.get(groupPosition).getItems();
        return childList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        ExpandListChild child = (ExpandListChild) getChild(groupPosition, childPosition);
        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.expandlist_child_textview, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.tvChildId);
        textView.clearComposingText();
        textView.setText(child.getName());
        textView.setTag(child.getTag());

        // TODO Auto-generated method stub
        return convertView;
    }

    public View getGroupView(int groupPosition, boolean isLastChild, View view, ViewGroup parent)
    {

        ExpandListGroup group = (ExpandListGroup) getGroup(groupPosition);

        if (view == null)
        {
            LayoutInflater inf;
            inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.expandlist_group_textview, null);
        }

        TextView textView = (TextView) view.findViewById(R.id.tvGroupId);
        textView.clearComposingText();
        textView.setText(group.getName());

        // TODO Auto-generated method stub
        return view;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }
}
