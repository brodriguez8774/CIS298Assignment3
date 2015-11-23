package edu.kvcc.cis298.cis298assignment3;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import java.util.List;

/**
 * Activity class for each individual beverage.
 */
public class BeverageActivity extends FragmentActivity {

    //region Variables

    private ViewPager mViewPager;
    private List<Beverage> mBeverages;

    //endregion



    //region Intent to summon class.

    public static final String EXTRA_BEVERAGE_ID = "edu.kvcc.cis298.cis298assignment3.beverage_id";

    public static Intent newIntent(Context packageContext, String beverageId) {

        Intent intent = new Intent(packageContext, BeverageActivity.class);

        intent.putExtra(EXTRA_BEVERAGE_ID, beverageId);
        return intent;
    }

    //endregion



    //region Override Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Sets content to use beverage pager.
        setContentView(R.layout.activity_beverage_view_pager);

        String beverageId = (String) getIntent().getStringExtra(EXTRA_BEVERAGE_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_beverage_view_pager);

        // Get list of beverages.
        mBeverages = BeverageList.get(this).getBeverages();
        // Create new fragment and adapter.
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                // Fetch the specific beverage using the ID.
                Beverage beverage = mBeverages.get(i);
                return BeverageFragment.newInstance(beverage.getID());
            }

            @Override
            public int getCount() {
                return mBeverages.size();
            }
        });

        // Go through list and find matching ID. Set that one to current pager index.
        for (int i = 0; i < mBeverages.size(); i++) {
            if (mBeverages.get(i).getID().equals(beverageId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    //endregion


}
