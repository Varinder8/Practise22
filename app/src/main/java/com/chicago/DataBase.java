package com.chicago;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Varinder on 8/7/2014.
 */
public class DataBase extends SQLiteOpenHelper {

    static String dbName="ChicagoKisssan.db";
    static int dbVersion=1;

    public DataBase(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        CreateAllTables(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int previousVersion, int newVersion) {
        DropTables(db);
    }

    private void DropTables(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS RecurringDeposit");
        db.execSQL("DROP TABLE IF EXISTS Columns");
        db.execSQL("DROP TABLE IF EXISTS Years");
        db.close();
    }

    public boolean Delete(){
        SQLiteDatabase db = getWritableDatabase();
        DropTables(db);
        db.close();
        return true;
    }

    void CreateAllTables(SQLiteDatabase sqLiteDatabase) {
        CreateTable(sqLiteDatabase,"RecurringDeposit");
    }

    void CreateAllTables() {
        SQLiteDatabase db =getWritableDatabase();
        CreateTable(db,"RecurringDeposit");
        db.close();
    }

    void CreateTable(SQLiteDatabase db ,String tableName){
        String createTable="CREATE TABLE "+tableName+" (Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,Monthly " +
                "INTEGER , Quarterly INTEGER ,HalfYearly INTEGER ,Yearly INTEGER ," +
                "FundValue INTEGER ,Maturity INTEGER , Years INTEGER)";
        Log.d("Qry",createTable);
        db.execSQL(createTable);
    }

    public List<String> GetColumns(){
        List<String> columnsList = new ArrayList<String>();
        columnsList.add("Monthly");
        columnsList.add("Quarterly");
        columnsList.add("HalfYearly");
        columnsList.add("Yearly");
        return columnsList;

        /*List<String> columns = new ArrayList<String>();
        SQLiteDatabase db =getReadableDatabase();
        String qry ="select ColumnName from Columns";
        Cursor cursor= db.rawQuery(qry,null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        db.close();
        return columns;*/
    }

    public List<String> GetDuration(){
        List<String> durationList = new ArrayList<String>();
        durationList.add("2");
        durationList.add("3");
        durationList.add("4");
        durationList.add("5");
        durationList.add("6");
        durationList.add("7");

        return durationList;

        /*List<String> columns = new ArrayList<String>();
        SQLiteDatabase db =getReadableDatabase();
        String qry ="select ColumnName from Columns";
        Cursor cursor= db.rawQuery(qry,null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        db.close();
        return columns;*/
    }

    public SparseArray<Group> RetrievingRD(String columnName ,String years,String Amount){
        SparseArray<Group> groupList =new SparseArray<Group>();
        String qry ="select * from RecurringDeposit where ("+columnName+" >"+Amount+" or "+columnName+" " +
                "="+Amount+" or "+columnName+" <"+Amount+" ) " +
                "and Years ="+years+" order by "+columnName+" limit 4";
        SQLiteDatabase db = getReadableDatabase();
         Cursor cursor= db.rawQuery(qry,null);
        if(cursor!=null){
            int count=0;
            while (cursor.moveToNext()){
                Group group = new Group("Duration "+years+" years");
                RecurringDeposit recurringDeposit = new RecurringDeposit();
                recurringDeposit.Monthly =Integer.parseInt(cursor.getString(1));
                recurringDeposit.Quarterly=Integer.parseInt(cursor.getString(2));
                recurringDeposit.HalfYearly=Integer.parseInt(cursor.getString(3));
                recurringDeposit.Yearly=Integer.parseInt(cursor.getString(4));
                recurringDeposit.FundValue=Integer.parseInt(cursor.getString(5));
                recurringDeposit.Maturity=Integer.parseInt(cursor.getString(6));
                recurringDeposit.Years =Integer.parseInt(cursor.getString(7));
                group.childList.add(recurringDeposit);
                groupList.append(count,group);
                count++;
            }


        }
        db.close();

        return  groupList;
    }

    public SparseArray<Group> RetrievingRD(String columnName ,String Amount){
        SparseArray<Group> groupList =new SparseArray<Group>();
        groupList= RetrievingRD(columnName,Amount,"2",groupList,">");
        groupList= RetrievingRD(columnName,Amount,"2",groupList,"=");
        groupList= RetrievingRD(columnName,Amount,"2",groupList,"<");

        groupList= RetrievingRD(columnName,Amount,"3",groupList,">");
        groupList= RetrievingRD(columnName,Amount,"3",groupList,"=");
        groupList= RetrievingRD(columnName,Amount,"3",groupList,"<");

        groupList= RetrievingRD(columnName,Amount,"4",groupList,">");
        groupList= RetrievingRD(columnName,Amount,"4",groupList,"=");
        groupList= RetrievingRD(columnName,Amount,"4",groupList,"<");

        groupList= RetrievingRD(columnName,Amount,"5",groupList,">");
        groupList= RetrievingRD(columnName,Amount,"5",groupList,"=");
        groupList= RetrievingRD(columnName,Amount,"5",groupList,"<");

        groupList= RetrievingRD(columnName,Amount,"6",groupList,">");
        groupList= RetrievingRD(columnName,Amount,"6",groupList,"=");
        groupList= RetrievingRD(columnName,Amount,"6",groupList,"<");

        groupList= RetrievingRD(columnName,Amount,"7",groupList,">");
        groupList= RetrievingRD(columnName,Amount,"7",groupList,"=");
        groupList= RetrievingRD(columnName,Amount,"7",groupList,"<");
        return  groupList;
    }

    SparseArray<Group> RetrievingRD(String columnName ,String Amount,String years,SparseArray<Group> groupList,String sign ){
        String qry ="select * from RecurringDeposit where ("+columnName+" "+sign+" "+Amount+" ) " +
                "and Years ="+years+" order by "+columnName+" limit 1";

        Log.e("Qry *****----******* ",qry);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor= db.rawQuery(qry,null);
        if(cursor!=null & cursor.getCount()>0){
            groupList= MakingSparseArray(years,cursor,groupList);
        }
        cursor.close();
        db.close();
        return  groupList;
    }

    SparseArray<Group> MakingSparseArray(String years,Cursor cursor,SparseArray<Group> groupList){
        Group group = new Group(years+" years");
        while (cursor.moveToNext()){
            RecurringDeposit recurringDeposit = new RecurringDeposit();
            recurringDeposit.Monthly =Integer.parseInt(cursor.getString(1));
            recurringDeposit.Quarterly=Integer.parseInt(cursor.getString(2));
            recurringDeposit.HalfYearly=Integer.parseInt(cursor.getString(3));
            recurringDeposit.Yearly=Integer.parseInt(cursor.getString(4));
            recurringDeposit.FundValue=Integer.parseInt(cursor.getString(5));
            recurringDeposit.Maturity=Integer.parseInt(cursor.getString(6));
            recurringDeposit.Years =Integer.parseInt(cursor.getString(7));
            group.childList.add(recurringDeposit);
        }
        groupList.append(groupList.size(),group);
        return groupList;
    }

   void InsertAllDeposits(){

        // region Inserting RD2
        InsertDeposits("RecurringDeposit",new RecurringDeposit(500,1500,3000,6000,12000,14640,2));
        InsertDeposits("RecurringDeposit",new RecurringDeposit(1000,3000,6000,12000,24000,29280,2));
        InsertDeposits("RecurringDeposit",new RecurringDeposit(2000,6000,12000,24000,48000,58560,2));
        InsertDeposits("RecurringDeposit",new RecurringDeposit(3000,9000,18000,36000,72000,87840,2));
        InsertDeposits("RecurringDeposit",new RecurringDeposit(5000,15000,30000,60000,120000,146400,2));
        InsertDeposits("RecurringDeposit",new RecurringDeposit(10000,30000,60000,120000,240000,292800,2));
        // endregion

        // region Inserting RD3
        InsertDeposits("RecurringDeposit",new RecurringDeposit(500,1500,3000,6000,12000,14640,3));
        InsertDeposits("RecurringDeposit",new RecurringDeposit(1000,3000,6000,12000,24000,29280,3));
        InsertDeposits("RecurringDeposit",new RecurringDeposit(2000,6000,12000,24000,48000,58560,3));
        InsertDeposits("RecurringDeposit",new RecurringDeposit(3000,9000,18000,36000,72000,87840,3));
        InsertDeposits("RecurringDeposit",new RecurringDeposit(5000,15000,30000,60000,120000,146400,3));
        InsertDeposits("RecurringDeposit",new RecurringDeposit(10000,30000,60000,120000,240000,292800,3));
        // endregion

        // region Inserting RD4
        InsertDeposits("RecurringDeposit",new RecurringDeposit(500,1500,3000,6000,12000,14640,4));
        InsertDeposits("RecurringDeposit",new RecurringDeposit(1000,3000,6000,12000,24000,29280,4));
        InsertDeposits("RecurringDeposit",new RecurringDeposit(2000,6000,12000,24000,48000,58560,4));
        InsertDeposits("RecurringDeposit",new RecurringDeposit(3000,9000,18000,36000,72000,87840,4));
        InsertDeposits("RecurringDeposit",new RecurringDeposit(5000,15000,30000,60000,120000,146400,4));
        InsertDeposits("RecurringDeposit",new RecurringDeposit(10000,30000,60000,120000,240000,292800,4));
        // endregion

    }

    boolean InsertDeposits(String table, RecurringDeposit REC2){
        SQLiteDatabase db =getWritableDatabase();
        String qry ="Insert into "+table+" (Monthly,Quarterly,HalfYearly,Yearly,FundValue,Maturity,Years)"+
        "values ("+REC2.Monthly+" ,"+REC2.Quarterly+","+REC2.HalfYearly+","+REC2.Yearly+"" +
        ","+REC2.FundValue+","+REC2.Maturity+","+REC2.Years+")";
        Log.d("Insert Qry" ,qry);
        db.execSQL(qry);
        db.close();
        return  true;
    }

    // region Commented
/*
    RecurringDeposit2Years Retrieve(){
        SQLiteDatabase db =getReadableDatabase();
        Cursor cursor= db.rawQuery("Select * from RecurringDeposit where Id = 1",null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            RecurringDeposit2Years RD2 = new RecurringDeposit2Years(Integer.parseInt(cursor.getString(0))
                    ,Integer.parseInt(cursor.getString(1))
             ,Integer.parseInt( cursor.getString(2)),Integer.parseInt(cursor.getString(3))
                    ,Integer.parseInt(cursor.getString(4)),Integer.parseInt(cursor.getString(5)),2);
            return RD2;
        }
        return null ;
    }*/
    // endregion
}