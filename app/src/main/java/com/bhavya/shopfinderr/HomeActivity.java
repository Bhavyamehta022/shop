package com.bhavya.shopfinderr;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bhavya.shopfinderr.Model.Products;
import com.bhavya.shopfinderr.ViewHolder.ProductViewHolder;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter_LifecycleAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Home_Fragment.OnFragmentInteractionListener,Shops_Fragment.OnFragmentInteractionListener,Products_Fragment.OnFragmentInteractionListener,Me_Fragment.OnFragmentInteractionListener  {


    private DatabaseReference ProductsReference;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ProductsReference=FirebaseDatabase.getInstance().getReference().child("Products");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("ShopFinder");
        setSupportActionBar(toolbar);


        TabLayout tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Shops"));
        tabLayout.addTab(tabLayout.newTab().setText("Products"));
        tabLayout.addTab(tabLayout.newTab().setText("Me"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        Button logOut = findViewById(R.id.nav_logout);
//        logOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signOut();
//            }
//        });

        recyclerView=(RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true);
        recyclerView.setLayoutManager(layoutManager);
    }
    @Override
    protected void onStart(){

        super.onStart();
        FirebaseRecyclerOptions<Products> options=
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(ProductsReference,Products.class)
                        .build();

        FirebaseRecyclerAdapter<Products,ProductViewHolder> adapter=
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Products model) {

                        holder.productName.setText(model.getName());
                        holder.productDescription.setText(model.getDescription());
                        holder.productPrice.setText("Price = Rs" +String.valueOf( model.getPrice())+ "/-");
                        Picasso.get().load(model.getImage()).into(holder.imageView);

                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_home_,viewGroup,false);
                        ProductViewHolder holder=new ProductViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        }

        else if (id == R.id.nav_range) {

        } else if (id == R.id.nav_shop) {

        } else if (id == R.id.nav_near) {

        }


        else if (id == R.id.nav_language) {

        }


        else if (id == R.id.nav_help) {

        }
        else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void signOut()
    {
        new AlertDialog.Builder(this)
                .setTitle(R.string.sign_out)
                .setMessage("You want to sign out?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(R.string.positive_say, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        out();
                        Toast.makeText(HomeActivity.this, "you are now signed out", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.negative_say, null)
                .show();
    }

    public void out()
    {
        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //user is now signed out
                //TODO what to do when signed out
                Intent i = new Intent(HomeActivity.this, Splash.class);
                startActivity(i);
            }
        });
    }
    public void onCreated(ViewPager viewPager)
    {
        //SectionsPageAdapter adapter=
    }

    @Override
    public void onFragmentInteraction(Uri uri) {


    }
}
