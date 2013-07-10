/*
 * Copyright 2013 Chris Banes
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

package uk.co.senab.actionbarpulltorefresh.sample;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshListAttacher;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshListAttacher.ListOptions;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshListAttacher.MODE;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * This sample shows how to use ActionBar-PullToRefresh with a
 * {@link android.widget.ListView ListView}, and manually creating (and attaching) a
 * {@link PullToRefreshAttacher} to the view.
 */
public class ListViewHeaderFooterActivity extends ListActivity
        implements PullToRefreshListAttacher.OnHeaderRefreshListener,
        PullToRefreshListAttacher.OnFooterRefreshListener{

    private static String[] ITEMS = {
    		"Abbaye de Belloc",
    		"Abbaye du Mont des Cats",
    		"Abertam",
            "Abondance",
            "Ackawi",
            "Acorn",
            "Adelost",
            "Affidelice au Chablis",
            "Afuega'l Pitu",
            "Airag",
            "Airedale",
            "Aisy Cendre",
            "Allgauer Emmentaler",
            "Abbaye de Belloc",
            "Abbaye du Mont des Cats",
            "Abertam",
            "Abondance",
            "Ackawi",
            "Acorn",
            "Adelost",
            "Affidelice au Chablis", 
            "Afuega'l Pitu",
            "Airag",
            "Airedale",
            "Aisy Cendre",
            "Allgauer Emmentaler"};

    private PullToRefreshListAttacher mPullToRefreshAttacher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Get ListView and give it an adapter to display the sample items
         */
        ListView listView = getListView();
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                ITEMS);


        /**
         * Here we create a PullToRefreshAttacher manually without an Options instance.
         * PullToRefreshAttacher will manually create one using default values.
         */
        
        ListOptions options = new ListOptions();
       // options.headerLayout = R.layout.default_footer;
        //options.headerTheme = 1;
        //options.footerTheme = 1;
        mPullToRefreshAttacher = new PullToRefreshListAttacher(this, options);

        // Set the Refreshable View to be the ListView and the refresh listener to be this.
        mPullToRefreshAttacher.setRefreshableView(listView, MODE.Both);
        //mPullToRefreshAttacher.setHeaderRefreshing(true);
        mPullToRefreshAttacher.setHeaderRefreshLister(this);
        mPullToRefreshAttacher.setFooterRefreshLister(this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onHeaderRefreshStarted(View view) {
        /**
         * Simulate Refresh with 4 seconds sleep
         */
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);

                // Notify PullToRefreshAttacher that the refresh has finished
                mPullToRefreshAttacher.setHeaderRefreshComplete();
            }
        }.execute();
    }
    

    @Override
    public void onFooterRefreshStarted(View view) {
        /**
         * Simulate Refresh with 4 seconds sleep
         */
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);

                // Notify PullToRefreshAttacher that the refresh has finished
                mPullToRefreshAttacher.setFooterRefreshComplete();
            }
        }.execute();
    }
}
