package com.chicago;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;

/**
 * Created by Varinder on 8/7/2014.
 */

@Table(name = "RecurringDeposit")
public class RecurringDeposit extends Model {
    //region Variables Declaration
    int _id;

    @Column(name = "Monthly")
    int Monthly;
    @Column(name = "Quarterly")
    int Quarterly;
    @Column (name = "HalfYearly")
    int HalfYearly;
    @Column (name = "Yearly")
    int Yearly;
    @Column (name = "FundValue")
    int FundValue;
    @Column (name = "Maturity")
    int Maturity;
    @Column (name = "Years")
    int Years;

    // endregion

    // region Constructors
    public RecurringDeposit(){}

    public RecurringDeposit(int monthly, int quarterly, int halfyearly, int yearly, int fundValue, int maturity, int years){
        this.Monthly=monthly;
        this.Quarterly=quarterly;
        this.HalfYearly=halfyearly;
        this.Yearly=yearly;
        this.FundValue=fundValue;
        this.Maturity=maturity;
        this.Years = years;
    }

    public static Boolean Save() {
        RecurringDeposit recurringDeposit = new RecurringDeposit(500,1500,3000,6000,12000,14640,2);
        recurringDeposit.save();
        recurringDeposit = new RecurringDeposit(1000,3000,6000,12000,24000,29280,2);
        recurringDeposit.save();
        recurringDeposit = new RecurringDeposit(2000,6000,12000,24000,48000,58560,2);
        recurringDeposit.save();
        recurringDeposit = new RecurringDeposit(3000,9000,18000,36000,72000,87840,2);
        recurringDeposit.save();
        recurringDeposit = new RecurringDeposit(5000,15000,30000,60000,120000,146400,2);
        recurringDeposit.save();
        recurringDeposit = new RecurringDeposit(10000,30000,60000,120000,240000,292800,2);
        recurringDeposit.save();
        // endregion

        // region Inserting RD3
        recurringDeposit = new RecurringDeposit(500,1500,3000,6000,12000,14640,3);
        recurringDeposit.save();
        recurringDeposit = new RecurringDeposit(1000,3000,6000,12000,24000,29280,3);
        recurringDeposit.save();
        recurringDeposit = new RecurringDeposit(2000,6000,12000,24000,48000,58560,3);
        recurringDeposit.save();
        recurringDeposit = new RecurringDeposit(3000,9000,18000,36000,72000,87840,3);
        recurringDeposit.save();
        recurringDeposit = new RecurringDeposit(5000,15000,30000,60000,120000,146400,3);
        recurringDeposit.save();
        recurringDeposit = new RecurringDeposit(10000,30000,60000,120000,240000,292800,3);
        recurringDeposit.save();
        // endregion

        // region Inserting RD4
        recurringDeposit = new RecurringDeposit(500,1500,3000,6000,12000,14640,4);
        recurringDeposit.save();
        recurringDeposit = new RecurringDeposit(1000,3000,6000,12000,24000,29280,4);
        recurringDeposit.save();
        recurringDeposit = new RecurringDeposit(2000,6000,12000,24000,48000,58560,4);
        recurringDeposit.save();
        recurringDeposit = new RecurringDeposit(3000,9000,18000,36000,72000,87840,4);
        recurringDeposit.save();
        recurringDeposit = new RecurringDeposit(5000,15000,30000,60000,120000,146400,4);
        recurringDeposit.save();
        recurringDeposit = new RecurringDeposit(10000,30000,60000,120000,240000,292800,4);
        return  true;
    }

    public static Boolean Delete (){
        new Delete().from(RecurringDeposit.class).where("1=1").executeSingle();
        return  true;
    }





    /*public RecurringDeposit(int monthly, int quarterly, int halfyearly, int yearly, int fundValue, int maturity, int years){
        this._monthly=monthly;
        this._quarterly=quarterly;
        this._halfYearly=halfyearly;
        this._yearly=yearly;
        this._fundValue=fundValue;
        this._maturity=maturity;
        this._years = years;
    }
    // endregion

    // region Get Properties
    public int Id(){return this._id;}
    public int Monthly(){return this._monthly;}
    public int Quarterly () {return  this._quarterly;}
    public int HalfYearly(){return this._halfYearly;}
    public int Yearly(){return this._yearly;}
    public int FundValue() {return this._fundValue;}
    public int Maturity(){return this._maturity;}
    public int Years(){return  this._years;}
    // endregion

    // region Set Properties
    public int Id(int id){return this._id=id;}
    public int Monthly(int monthly){return this._monthly=monthly;}
    public int Quarterly (int quaterly) {return  this._quarterly=quaterly;}
    public int HalfYearly(int halfYearly){return this._halfYearly=halfYearly;}
    public int Yearly(int yearly){return this._yearly=yearly;}
    public int FundValue(int fundValue) {return this._fundValue=fundValue;}
    public int Maturity(int maturity){return this._maturity=maturity;}
    public int Years(int years){return  this._years=years;}
    // endregion*/
}
