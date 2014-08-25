package com.chicago;

import android.app.Activity;
import android.test.suitebuilder.annotation.Suppress;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.util.Log;

import java.util.zip.Inflater;

/**
 * Created by Varinder on 8/24/2014.
 */
public class ExpandableAdapter extends BaseExpandableListAdapter {
    private  final SparseArray<Group> groupSparseArray;
    Activity activity;
    LayoutInflater layoutInflater;

    public ExpandableAdapter(Activity activity, SparseArray<Group> groupSparseArray) {
        this.groupSparseArray = groupSparseArray;
        this.activity =activity;
        layoutInflater=activity.getLayoutInflater();
    }

    @Override
    public int getGroupCount() {
        return groupSparseArray.size();
    }

    @Override
    public int getChildrenCount(int position) {
        return groupSparseArray.get(position).childList.size();
    }

    @Override
    public Object getGroup(int position) {
        return groupSparseArray.get(position);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupSparseArray.get(groupPosition).childList.get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i2) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean IsExpanded, View convertView, ViewGroup parent) {
        try
        {
        if(convertView ==null){
            convertView=layoutInflater.inflate(R.layout.listrow_group,null);
        }
        Group group =(Group) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(group.groupName);
        ((CheckedTextView) convertView).setChecked(IsExpanded);
        }
        catch (Exception ex) {
            Log.d("Exception at getGroupView --------*****^&$##$----",ex.getMessage());
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        try {
            final RecurringDeposit childObject = (RecurringDeposit) getChild(groupPosition, childPosition);
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.listrow_details, null);
            }
            TextView textViewMonthly = (TextView) convertView.findViewById(R.id.txtViewMonthly);
            textViewMonthly.setText("Monthly - " + childObject.Monthly);

            TextView textViewQuarterly = (TextView) convertView.findViewById(R.id.txtQuarterly);
            textViewQuarterly.setText("Quarterly - " + childObject.Quarterly);

            ImageView imgSeeMore = (ImageView) convertView.findViewById(R.id.ImgseeMore);
            imgSeeMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(activity, "Hi iam a SubItem", Toast.LENGTH_LONG).show();
                }
            });
        }
        catch (Exception ex) {
            Log.d("Exception at getChildView --------*****^&$##$----",ex.getMessage());
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }
}
