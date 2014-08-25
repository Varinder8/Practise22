package com.chicago;

import android.provider.ContactsContract;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by Varinder on 8/22/2014.
 */
@Table (name="Columns")
public class Columns extends Model {
    @Column (name = "ColumnName")
    public String ColumnName ;

    @Column (name = "ColumnNameToDisplay")
    public String ColumnNameToDisplay;

    public static Boolean Save(){
        Columns columns =new Columns();
        columns.ColumnName = "Monthly";
        columns.ColumnNameToDisplay="Monthly";
        columns.save();

        columns =new Columns();
        columns.ColumnName = "Quarterly";
        columns.ColumnNameToDisplay="Quarterly";
        columns.save();

        columns =new Columns();
        columns.ColumnName = "HalfYearly";
        columns.ColumnNameToDisplay="Half Yearly";
        columns.save();

        columns =new Columns();
        columns.ColumnName = "Yearly";
        columns.ColumnNameToDisplay="Yearly";
        columns.save();

        return  true;
    }

    public static Boolean Delete(){
        new Delete().from(Columns.class).where("1=1").executeSingle();
        /*Columns columns =new Columns();
        columns.delete();*/
        return  true;
    }

    public static List<Columns> GetColumns(){

       return new Select().from(Columns.class).orderBy("ColumnNameToDisplay").execute();

    }
}
