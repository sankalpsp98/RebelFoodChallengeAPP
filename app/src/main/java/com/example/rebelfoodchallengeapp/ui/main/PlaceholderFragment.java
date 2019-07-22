package com.example.rebelfoodchallengeapp.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rebelfoodchallengeapp.MapsActivity;
import com.example.rebelfoodchallengeapp.R;
import com.example.rebelfoodchallengeapp.UserAdapter;
import com.example.rebelfoodchallengeapp.UserDetail;
import com.example.rebelfoodchallengeapp.dataWire;
import com.example.rebelfoodchallengeapp.users;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment  {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private RecyclerView recyclerView ;
    private  RecyclerView.Adapter adapter;
    private RecyclerView.Adapter adapter2;
    List<users> usersList ;
    private users usersData;
    List<users> usersList2 ;
    private users usersData2;
    dataWire dataWire = new dataWire();
    SharedPreferences sharedPreferences;



    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);

        sharedPreferences = getContext().getSharedPreferences("favuser",getContext().MODE_PRIVATE);

        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Refresh your fragment here

            dataWire.setCurrentScreen(String.valueOf(getArguments().getInt(ARG_SECTION_NUMBER)));


            if (getArguments().getInt(ARG_SECTION_NUMBER)==2) {

                Toast.makeText(getContext(), ">>"+dataWire.getUsersList().size()
                        , Toast.LENGTH_SHORT).show();
                usersList2.clear();
               // usersList2.addAll(dataWire.getUsersList());
                pageViewModel.getUsersLiveData().observe(this, new Observer<List<users>>() {
                    @Override
                    public void onChanged(List<users> users) {

                        Toast.makeText(getContext(), "watching "+usersList2.size(), Toast.LENGTH_SHORT).show();

                        for (int i =0;i<usersList2.size();i++)
                        {
                            usersList2.get(i).setFavStatus(1);
                            adapter2.notifyItemInserted(i);


                    }
                        recyclerView.setAdapter(adapter2);


                    }
                });

                Map<String, String> mapUserFav = (Map<String, String>) sharedPreferences.getAll();
                Iterator it = mapUserFav.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();

                    System.out.println(pair.getKey() + " = " + pair.getValue()+" size "+usersList2.size());
                    usersList2.add(dataWire.getUsersList().get(Integer.parseInt(pair.getKey().toString())));


                    // avoids a ConcurrentModificationException
                }

                pageViewModel.setUsersLiveData(usersList2);


            }
            else if (dataWire.getCurrentScreen().equals("1"))
            {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                },200);
            }

        }
    }
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = root.findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        usersList = new ArrayList<>();
        usersList2 = new ArrayList<>();
        adapter2 =  new UserAdapter(usersList2, getContext(), new UserAdapter.myItemRemovedListener() {
            @Override
            public void onItemRemved(int posistion) {
                adapter2.notifyItemChanged(posistion);

                Toast.makeText(getContext(), "item  has been deleted"+usersList2.size(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCardClicked(int position, String lat, String lng) {
                Toast.makeText(getContext(), position+" "+lat+" "+lng, Toast.LENGTH_SHORT).show();
                Intent maps =new Intent(getActivity(),MapsActivity.class);
                dataWire.setLat(Double.valueOf(lat));
                dataWire.setLog(Double.valueOf(lng));
                String add =  dataWire.getUsersList().get(position).getStreet()+","+dataWire.getUsersList().get(position).getSuite()+","+dataWire.getUsersList().get(position).getCity()+","+dataWire.getUsersList().get(position).getZipcode();
                dataWire.setAddress(add);
                startActivity(maps);

            }
        });
        adapter = new UserAdapter(usersList, getContext(), new UserAdapter.myItemRemovedListener() {
            @Override
            public void onItemRemved(int posistion) {
                Toast.makeText(getContext(), "item  has been deleted", Toast.LENGTH_SHORT).show();
                adapter.notifyItemChanged(posistion);
            }

            @Override
            public void onCardClicked(int position, String lat, String lng) {

                Toast.makeText(getContext(), position+" "+lat+" "+lng, Toast.LENGTH_SHORT).show();
                Intent maps =new Intent(getActivity(),MapsActivity.class);
                dataWire.setLat(Double.valueOf(lat));
                dataWire.setLog(Double.valueOf(lng));
               String add =  usersList.get(position).getStreet()+","+usersList.get(position).getSuite()+","+usersList.get(position).getCity()+","+usersList.get(position).getZipcode();
               dataWire.setAddress(add);
                startActivity(maps);

            }
        });


        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                Toast.makeText(getContext(), "page watcher", Toast.LENGTH_SHORT).show();
                if (s.equals("1"))
                {
                     /**
                      *testing recycleview ofline first
                    for (int i=0;i<10;i++)
                    {
                        usersData =new users(i+"","","","","","","","","",0);
                        usersList.add(usersData);
                    }*/
                     usersList.addAll(dataWire.getUsersList());

                    dataWire.setUsersList(usersList);
                    Map<String, String> mapUserFav = (Map<String, String>) sharedPreferences.getAll();
                    Iterator it = mapUserFav.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();

                        System.out.println(">>>>>> "+pair.getKey() + " = " + pair.getValue()+" size "+usersList.size());
                        usersList.get(Integer.parseInt(pair.getKey().toString())).setFavStatus(1);
                        adapter.notifyItemChanged(Integer.parseInt(pair.getKey().toString()));


                        // avoids a ConcurrentModificationException
                    }
                    Toast.makeText(getContext(), "userlist"+usersList.size()+" "+dataWire.getUsersList().size(), Toast.LENGTH_SHORT).show();
                    recyclerView.setAdapter(adapter);
                }
            }
        });


        return root;
    }


}