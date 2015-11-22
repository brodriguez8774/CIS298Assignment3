package edu.kvcc.cis298.cis298assignment3;

import android.support.v4.app.Fragment;

/**
 * Activity class for Beverage List.
 * Created by Waffy on 11/22/2015.
 */
public class BeverageListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new BeverageListFragment();
    }
}
