package com.r2apps.sfa;




import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.r2apps.sfa.adapters.NavDrawerListAdapter;
import com.r2apps.sfa.dao.NavDrawerItem;
import com.r2apps.sfa.fragments.AddRetailer;
import com.r2apps.sfa.fragments.Dashboard;
import com.r2apps.sfa.fragments.Distributors;
import com.r2apps.sfa.fragments.Orders;
import com.r2apps.sfa.fragments.Preferences;
import com.r2apps.sfa.fragments.ProductsFragment;
import com.r2apps.sfa.fragments.Retailers;
import com.r2apps.sfa.fragments.Stores;
import com.r2apps.sfa.util.AppConstants;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements Distributors.OnFragmentInteractionListener, Retailers.OnFragmentInteractionListener,
        Orders.OnFragmentInteractionListener, AddRetailer.OnFragmentInteractionListener, Preferences.OnFragmentInteractionListener,
        Stores.OnFragmentInteractionListener, ProductsFragment.OnFragmentInteractionListener{

    // slide menu items
    private Toolbar toolbar;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private FragmentManager fragmentManager;

    private String mTitle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Navigation Drawer");
            toolbar.setTitleTextColor(Color.WHITE);
        }
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Booking Calendar
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));

        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
       /// getActionBar().setIcon(R.drawable.abs__ic_menu_moreoverflow_normal_holo_dark);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar,
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        displayView(AppConstants.DASHBOARD);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Log.i("onItemClick", "Position:" + position);
            displayView(position);
            //mDrawerList.setItemChecked(position, true);
            //mDrawerList.setSelection(position);
            //adapter.setSelectedPosition(position);
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case AppConstants.DASHBOARD:
                setTitle("DashBoard");

                fragment = new Dashboard();

                break;

            case AppConstants.DISTRIBUTOR:
                setTitle("Distributors");
                fragment = new Distributors();
                break;

            case AppConstants.PRODUCTS:
                setTitle("Products");
                fragment = new ProductsFragment();
                break;

            case AppConstants.RETAILERS:
                setTitle("Retailers");
                fragment = new Retailers();
                break;

            case AppConstants.PREFERENCES:
                setTitle("Start A Day");
                fragment = new Preferences();
                break;

            case AppConstants.ORDERS:
                setTitle("Orders");
                fragment = new Orders();
                break;

            case AppConstants.ADD_RETAILER:
                setTitle("Add Retailer");
                fragment = new AddRetailer();
                break;

            case AppConstants.STORES:
                setTitle("Stores Around You");
                fragment = new Stores();
                break;
        }
        if(fragment != null){
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title.toString();
        toolbar.setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
