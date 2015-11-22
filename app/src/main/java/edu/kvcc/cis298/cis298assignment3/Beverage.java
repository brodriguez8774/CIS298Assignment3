package edu.kvcc.cis298.cis298assignment3;

import java.util.UUID;

/**
 * Class for each individual instance of a Beverage.
 * Created by Waffy on 11/22/2015.
 */
public class Beverage {

    //region Variables

    private UUID mID;
    private String mName;
    private String mPack;
    private double mPrice;      // Needs changed to Decimal.
    private boolean mActive;

    //endregion



    //region Constructor
    public Beverage(UUID id, String name, String pack, Double price, Boolean active) {
        mID = id;
        mName = name;
        mPack = pack;
        mPrice = price;
        mActive = active;
    }

    //endregion



    //region Properties

    public UUID getID() {
        return mID;
    }

    public void setID(UUID ID) {
        mID = ID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPack() {
        return mPack;
    }

    public void setPack(String pack) {
        mPack = pack;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public boolean isActive() {
        return mActive;
    }

    public void setActive(boolean active) {
        mActive = active;
    }

    //endregion

}
