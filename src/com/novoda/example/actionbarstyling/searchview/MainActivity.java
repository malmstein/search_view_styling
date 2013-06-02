/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.novoda.example.actionbarstyling.searchview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import java.lang.reflect.Field;

/**
 * This example illustrates a common usage of the DrawerLayout widget
 * in the Android support library.
 * <p/>
 * <p>When a navigation (left) drawer is present, the host activity should detect presses of
 * the action bar's Up affordance as a signal to open and close the navigation drawer. The
 * ActionBarDrawerToggle facilitates this behavior.
 * Items within the drawer should fall into one of two categories:</p>
 * <p/>
 * <ul>
 * <li><strong>View switches</strong>. A view switch follows the same basic policies as
 * list or tab navigation in that a view switch does not create navigation history.
 * This pattern should only be used at the root activity of a task, leaving some form
 * of Up navigation active for activities further down the navigation hierarchy.</li>
 * <li><strong>Selective Up</strong>. The drawer allows the user to choose an alternate
 * parent for Up navigation. This allows a user to jump across an app's navigation
 * hierarchy at will. The application should treat this as it treats Up navigation from
 * a different task, replacing the current task stack using TaskStackBuilder or similar.
 * This is the only form of navigation drawer that should be used outside of the root
 * activity of a task.</li>
 * </ul>
 * <p/>
 * <p>Right side drawers should be used for actions, not navigation. This follows the pattern
 * established by the Action Bar that navigation should be to the left and actions to the right.
 * An action should be an operation performed on the current contents of the window,
 * for example enabling or disabling a data overlay on top of the current content.</p>
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        setSearchTextColour(searchView);
        setCloseSearchIcon(searchView);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
        case R.id.action_search:

            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void setSearchTextColour(SearchView searchView) {
        int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText searchPlate = (EditText) searchView.findViewById(searchPlateId);
        searchPlate.setTextColor(getResources().getColor(R.color.novoda_blue));
//        searchPlate.setBackgroundResource(R.drawable.edit_text_holo_light);
        searchPlate.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
    }

//    private void setSearchTextColourWithActionBarSherlock(SearchView searchView) {
//        AutoCompleteTextView searchPlate = (AutoCompleteTextView) searchView.findViewById(R.id.abs__search_src_text);
//        searchPlate.setTextColor(getResources().getColor(R.color.novoda_blue));
//        searchPlate.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
//    }

    private void setCloseSearchIcon(SearchView searchView) {
        try {
            Field searchField = SearchView.class.getDeclaredField("mCloseButton");
            searchField.setAccessible(true);
            ImageView closeBtn = (ImageView) searchField.get(searchView);
            closeBtn.setImageResource(R.drawable.action_cancel);

        } catch (NoSuchFieldException e) {
            Log.e("SearchView", e.getMessage(), e);
        } catch (IllegalAccessException e) {
            Log.e("SearchView", e.getMessage(), e);
        }
    }

//    private void setSearchIconsWithActionBarSherlock(SearchView searchView) {
//        try {
//            Field searchField = SearchView.class.getDeclaredField("mCloseButton");
//            searchField.setAccessible(true);
//            ImageView closeBtn = (ImageView) searchField.get(searchView);
//            closeBtn.setImageResource(R.drawable.ic_menu_cancel);
//
//            searchField = SearchView.class.getDeclaredField("mVoiceButton");
//            searchField.setAccessible(true);
//            ImageView voiceBtn = (ImageView) searchField.get(searchView);
//            voiceBtn.setImageResource(R.drawable.ic_menu_voice_input);
//
//            ImageView searchButton = (ImageView) searchView.findViewById(R.id.abs__search_button);
//            searchButton.setImageResource(R.drawable.ic_menu_search);
//
//        } catch (NoSuchFieldException e) {
//            Log.e("SearchView", e.getMessage(), e);
//        } catch (IllegalAccessException e) {
//            Log.e("SearchView", e.getMessage(), e);
//        }
//    }

}