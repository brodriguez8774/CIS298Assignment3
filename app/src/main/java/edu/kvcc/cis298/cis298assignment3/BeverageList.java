package edu.kvcc.cis298.cis298assignment3;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Singleton class which holds list of beverages.
 * Created by Waffy on 11/22/2015.
 */
public class BeverageList {

    //region Singleton Setup

    private static BeverageList sBeverageList;

    public static BeverageList get(Context context) {
        if (sBeverageList == null) {
            sBeverageList = new BeverageList(context);
        }
        return sBeverageList;
    }

    //endregion



    //region Variables

    private List<Beverage> mBeverages;
    private Context mContext;

    //endregion



    //region Constructor

    private BeverageList(Context context) {
        // Create list to hold data.
        mBeverages = new ArrayList<>();
        mContext = context;
        loadBeverageList();
    }

    //endregion



    //region Properties

    public List<Beverage> getBeverages() {
        return mBeverages;
    }

    //endregion



    //region Methods

    // Find beverage with Id.
    public Beverage getBeverage(String id) {
        for (Beverage beverage : mBeverages) {
            if (beverage.getID().equals(id)) {
                return beverage;
            }
        }
        return null;
    }

    // Load in data from CSV.
    public void loadBeverageList() {
        // Create scanner which is used to read from file.
        Scanner scanner = null;

        // Attempt to read in from CSV.
        try {
            // Set path for scanner?
            scanner = new Scanner(mContext.getResources().openRawResource(R.raw.beverage_list));

            // While scanner recognizes that there are still lines to read.
            while (scanner.hasNextLine()) {
                // Assign next line to string.
                String line = scanner.nextLine();
                // Separate string by commas.
                String parts[] = line.split(",");

                // Assign each split part to a meaningful variable.
                String idString = parts[0].toString().trim();
                String nameString = parts[1].toString().trim();
                String packString = parts[2].toString().trim();
                String priceString = parts[3].toString().trim();
                String activeString = parts[4].toString().trim();

                // Parse the price into double.
                Double priceDouble = Double.parseDouble(priceString);

                // Parse active into bool.
                boolean isActive;
                if (activeString.toLowerCase().equals("true")) {
                    isActive = true;
                } else {
                    isActive = false;
                }

                mBeverages.add(new Beverage(idString, nameString, packString, priceDouble, isActive));
            }
        } catch (Exception error) {
            // If error, log to console.
            Log.e("Read CSV Error", error.toString());
        } finally {
            // Regardless of successful or not, close scanner to prevent memory leak.
            scanner.close();
        }
    }

    //endregion

}
