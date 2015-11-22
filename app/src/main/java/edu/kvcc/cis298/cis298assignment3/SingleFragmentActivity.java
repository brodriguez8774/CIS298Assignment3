package edu.kvcc.cis298.cis298assignment3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Abstract class which all fragments inherit from.
 * Created by Waffy on 11/22/2015.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inflates view using xml.
        setContentView(R.layout.activity_fragment);

        //Initialize fragment manager. Is needed for all fragments.
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        // If  null, create new Fragment.
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    // Abstract method to create fragment.
    protected abstract Fragment createFragment();
}
