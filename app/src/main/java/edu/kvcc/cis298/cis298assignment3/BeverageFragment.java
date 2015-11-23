package edu.kvcc.cis298.cis298assignment3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Fragment class to display each beverage.
 * Created by Waffy on 11/22/2015.
 */
public class BeverageFragment extends Fragment{

    //region Variables

    private Beverage mBeverage;
    private TextView mIdField;
    private EditText mNameField;
    private EditText mPackField;
    private EditText mPriceField;
    private CheckBox mActiveCheckBox;

    //endregion



    //region Static information to summon Fragment.

    private static final String ARG_BEVERAGE_ID = "beverage_id";

    public static BeverageFragment newInstance(String beverageId) {
        Bundle args = new Bundle();
        args.putString(ARG_BEVERAGE_ID, beverageId);

        BeverageFragment fragment = new BeverageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //endregion



    //region Override Methods


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String beverageId = (String) getActivity().getIntent().getStringExtra(BeverageActivity.EXTRA_BEVERAGE_ID);
        mBeverage = BeverageList.get(getActivity()).getBeverage(beverageId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_beverage, container, false);

        // Set all the beverage variables.
        mIdField = (TextView)v.findViewById(R.id.beverage_id);
        mIdField.setText(mBeverage.getID());


        mNameField = (EditText)v.findViewById(R.id.beverage_name);
        mNameField.setText(mBeverage.getName());
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Intentionally left blank.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBeverage.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Intentionally left blank.
            }
        });


        mPackField = (EditText)v.findViewById(R.id.beverage_pack);
        mPackField.setText(mBeverage.getPack());
        mPackField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Intentionally left blank.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBeverage.setPack(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Intentionally left blank.
            }
        });


        mPriceField = (EditText)v.findViewById(R.id.beverage_price);
        mPriceField.setText(Double.toString(mBeverage.getPrice()));
        mPackField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Intentionally left blank.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBeverage.setPrice(Double.parseDouble(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Intentionally left blank.
            }
        });


        mActiveCheckBox = (CheckBox)v.findViewById(R.id.beverage_active);
        mActiveCheckBox.setChecked(mBeverage.isActive());
        mActiveCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mBeverage.setActive(isChecked);
            }
        });


        return v;
    }


    //endregion


}
