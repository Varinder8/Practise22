package com.chicago;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Varinder on 8/24/2014.
 */
public class Group {
    public List<RecurringDeposit> childList =new ArrayList<RecurringDeposit>();
    String groupName;
    public Group(String groupName){
        this.groupName=groupName;
    }
    public Group(){}
}
