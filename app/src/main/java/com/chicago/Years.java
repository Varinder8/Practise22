package com.chicago;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name="Years")
public class Years extends Model {
    @Column(name = "Year")
    public int Year;

    public static boolean Save(Years years){
        years.save();
        return true;
    }

    public static Boolean Save(){

        Years years = new Years();
        years.Year=2;
        years.save();

        years = new Years();
        years.Year=3;
        years.save();

        years = new Years();
        years.Year=4;
        years.save();

        years = new Years();
        years.Year=5;
        years.save();

        years = new Years();
        years.Year=6;
        years.save();

        years = new Years();
        years.Year=7;
        years.save();

        return  true;
    }

    public static Boolean Delete(){
        new Delete().from(Years.class).where("1=1").executeSingle();
        return true;
    }

    public static List<Years> Retrieve()
    {
    List<Years> year =new Select().distinct().from(Years.class).execute();

    return new Select().from(Years.class).orderBy("Year").execute();
    }
}

