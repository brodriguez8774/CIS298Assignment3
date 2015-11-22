package edu.kvcc.cis298.cis298assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Fragment to display the Beverage List.
 * Created by Waffy on 11/22/2015.
 */
public class BeverageListFragment extends Fragment {

    //region Variables

    private RecyclerView mBeverageRecyclerView;
    private BeverageAdapter mAdapter;

    //endregion


    //region Override Methods

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflates view from xml into java-readable code.
        View view = inflater.inflate(R.layout.fragment_beverage_list, container, false);

        // Get a reference to the RecyclerView.
        mBeverageRecyclerView = (RecyclerView) view.findViewById(R.id.beverage_recycler_view);
        // LayoutManager so RecyclerView doesn't crash.
        mBeverageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    // Checks to see if data has changed on every resume. That way user never sees outdated info.
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    //endregion



    // Class within a class? Why?
    private class BeverageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Variables
        private Beverage mBeverage;
        private TextView mIdTextView;
        private TextView mNameTextView;
        private TextView mPriceTextView;

        // Constructor for BeverageHolder.
        public BeverageHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            // Assign ID to variables.
            mIdTextView = (TextView) itemView.findViewById(R.id.list_item_beverage_id_text_view);
            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_beverage_name_text_view);
            mPriceTextView = (TextView) itemView.findViewById(R.id.list_item_beverage_price_text_view);

        }

        // Binds individual BeverageHolder views (created above) to layout.
        public void bindBeverage(Beverage beverage) {
            mBeverage = beverage;
            mIdTextView.setText(mBeverage.getID().toString());
            mNameTextView.setText(mBeverage.getName());
            mPriceTextView.setText(Double.toString(mBeverage.getPrice()));
        }

        @Override
        public void onClick(View v) {
            // Creates intent using specified method in BeverageActivity.
            Intent intent = BeverageActivity.newIntent(getActivity(), mBeverage.getID());
            startActivity(intent);
        }
    }

    //Another class within a class.
    // Adapts information so that RecyclerView can display things.
    private class BeverageAdapter extends RecyclerView.Adapter<BeverageHolder> {

        // List to hold beverages.
        private List<Beverage> mBeverages;

        // Constructor.
        public BeverageAdapter(List<Beverage> beverages) {
            mBeverages = beverages;
        }

        // Override methods for BeverageAdapter.

        @Override
        public BeverageHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            // Creates a new View to put into viewHolder.
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            // Inflates view of single list item to xml layout?
            View view = layoutInflater.inflate(R.layout.list_item_beverage, viewGroup, false);
            return  new BeverageHolder(view);
        }

        @Override
        public void onBindViewHolder(BeverageHolder beverageHolder, int i) {
            // Finds the position on the screen to bind to.
            Beverage beverage = mBeverages.get(i);
            // Sends info to BeverageHolder Class.
            beverageHolder.bindBeverage(beverage);
        }

        @Override
        public int getItemCount() {
            return mBeverages.size();
        }
    }

    // Does the work of getting data from List and setting it up with adapter. Basically updates things.
    private void updateUI()
    {
        BeverageList beverageList = BeverageList.get(getActivity());
        List<Beverage> beverages = beverageList.getBeverages();

        if (mAdapter == null) {
            mAdapter = new BeverageAdapter(beverages);
            mBeverageRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }


}
