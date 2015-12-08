package com.example.hoang.bookmovietickets.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.hoang.bookmovietickets.Helper.DBHelper;
import com.example.hoang.bookmovietickets.Helper.SimpleItemTouchHelperCallback;
import com.example.hoang.bookmovietickets.Model.MovieModel;
import com.example.hoang.bookmovietickets.Adapter.MyPagerAdapter;
import com.example.hoang.bookmovietickets.Model.SessionModel;
import com.example.hoang.bookmovietickets.R;
import com.example.hoang.bookmovietickets.Adapter.RVAdapter;
import com.viewpagerindicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private ItemTouchHelper mItemTouchHelper;
    private RVAdapter adapter;
    private List<MovieModel> list;
    private DBHelper dbHelper;
    private List<MovieModel> listDangChieu;
    private List<MovieModel> listSapChieu;
    private MenuItem user;
    private Menu menu;

    private ViewPager viewPager;
    private MyPagerAdapter myPagerAdapter;
    private PageIndicator indicator;
    private NavigationView navigationView;

    private boolean isLogin = false;
    public static ArrayList<String> Uprof = new ArrayList<String>();
    public static final String ISLOGIN = "islogin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {

            isLogin = savedInstanceState.getBoolean(ISLOGIN);
            if (isLogin) {
                Toast.makeText(this, "hehe", Toast.LENGTH_SHORT).show();
                user.setTitle("User Profile");
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        menu = navigationView.getMenu();
        getControls();

        if(Uprof.isEmpty()) {
            for (int i = 0; i < 4; i++) {
                Uprof.add("");
            }
        }

    }

    public void getControls(){
        dbHelper = new DBHelper(this);
        MovieModel model1 = new MovieModel("1800 Year Old Bride", "This is a greate movie , You'll be regret so much if you don't see it This is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see it",
                "@drawable/codau8tuoi", false );
        MovieModel model2 = new MovieModel("Guardian Of The Galaxy", "This is a greate movie , You'll be regret so much if you don't see it This is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see it",
                "@drawable/guardian", false );
        MovieModel model3 = new MovieModel("Spider Man 10", "This is a greate movie , You'll be regret so much if you don't see it",
                "@drawable/spider", false );
        MovieModel model4 = new MovieModel("Tom And Jerry The Movie", "This is a greate movie , You'll be regret so much if you don't see it",
                "@drawable/tom_jerry", false );
        MovieModel model5 = new MovieModel("Ghost Rider", "This is a greate movie , You'll be regret so much if you don't see it",
                "@drawable/ghost_rider", false );
        MovieModel model6 = new MovieModel("Despicable Me 5", "This is a greate movie , You'll be regret so much if you don't see it",
                "@drawable/despicableme", false );
        MovieModel model7 = new MovieModel("AntMan", "This is a greate movie , You'll be regret so much if you don't see it",
                "@drawable/antman", false );
        recyclerView = (RecyclerView) findViewById(R.id.phimsapchieu);
        listDangChieu = new ArrayList<MovieModel>();
        listSapChieu = new ArrayList<MovieModel>();
        if (listDangChieu.size() == 0){
            dbHelper.createMovie(model1);
            dbHelper.createMovie(model2);
            dbHelper.createMovie(model3);
            dbHelper.createMovie(model4);
            dbHelper.createMovie(model5);
            dbHelper.createMovie(model6);
            dbHelper.createMovie(model7);
            listDangChieu.add(dbHelper.getMovie(1));
            listDangChieu.add(dbHelper.getMovie(2));
            listDangChieu.add(dbHelper.getMovie(3));
        }

        if(listSapChieu.size() == 0){
            listSapChieu.add(dbHelper.getMovie(4));
            listSapChieu.add(dbHelper.getMovie(5));
            listSapChieu.add(dbHelper.getMovie(6));
            listSapChieu.add(dbHelper.getMovie(7));
        }

        adapter = new RVAdapter(this, listSapChieu, 0);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        myPagerAdapter = new MyPagerAdapter(this, listDangChieu);
        viewPager.setAdapter(myPagerAdapter);
        indicator = (PageIndicator) findViewById(R.id.pagerindicator);
        indicator.setViewPager(viewPager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 2;
                }
                return 1;
            }
        });

        recyclerView.setLayoutManager(gridLayoutManager);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    adapter.getFilter().filter("");
                } else {
                    adapter.getFilter().filter(newText.toString());
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateMenu();
    }

    public void updateMenu(){
        SessionModel sessionModel = dbHelper.getSession();
        user = menu.findItem(R.id.user);
        if (sessionModel.getIslogin() == 1){
            user.setTitle("User Profile");
            menu.add(0, 5, Menu.NONE, "Sign Out").setIcon(R.drawable.ic_log_out);
        }else {
            user.setTitle("Account");
            if (menu.findItem(5) != null){
                menu.removeItem(5);
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        SessionModel sessionModel = dbHelper.getSession();
        if (id == R.id.user) {
            user = item;
            if (sessionModel.getIslogin() == 0) {
                Intent intent = new Intent(MainActivity.this, LoginForm.class);
                startActivityForResult(intent, 1);
            }else {
                Intent intent = new Intent(MainActivity.this, UserProfile.class);
                startActivity(intent);
            }
        } else if (id == R.id.my_list) {
            Intent intent = new Intent(MainActivity.this, MyListTicketsActivity.class);
            startActivity(intent);
        } else if (id == R.id.history_list){
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        }

        if (id == 5){
            Toast.makeText(getApplicationContext(),"You've Sign out",Toast.LENGTH_SHORT).show();
            dbHelper.deleteSession();
            updateMenu();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if (resultCode == RESULT_OK) {
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
