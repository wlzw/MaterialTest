package com.suhe.materialtest;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Fruit> fruitList = new ArrayList<Fruit>();
    private FruitAdapter fruitAdapter;
    private DrawerLayout drawerLayout;
    private Fruit[] fruits = {new Fruit("Apple", R.drawable.apple),
            new Fruit("Orange", R.drawable.orange),
            new Fruit("Pear", R.drawable.pear),
            new Fruit("Pineapple", R.drawable.pineapple),
            new Fruit("Banana", R.drawable.banana),
            new Fruit("Watermelon", R.drawable.watermelon),
            new Fruit("Strawberry", R.drawable.strawberry),
            new Fruit("Grape", R.drawable.grape),
            new Fruit("Cherry", R.drawable.cherry),
            new Fruit("Mango", R.drawable.mango)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        SwipeRefreshLayout.

        final ActionBar actionBar = getSupportActionBar();
//        ActionBar左上角的导航图标，竟然是这样设置的！
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                actionBar.setSubtitle(item.getTitle());
                drawerLayout.closeDrawers();
                return true;
            }
        });

        FloatingActionButton floatBtn = (FloatingActionButton) findViewById(R.id.floatBtn);
        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Data deleted", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Data recovered", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

//        水果列表相关操作
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        initFruits();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        fruitAdapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(fruitAdapter);
    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItem_backup:
                Toast.makeText(this, "item: backup", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuItem_delete:
                Toast.makeText(this, "item: Delete", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuItem_settings:
                Toast.makeText(this, "item: Settings", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
//                ActionBar左上角home键也包括在这个方法内，识别id来自 android.R.id
//                划出方法需要指定方向
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }
}
