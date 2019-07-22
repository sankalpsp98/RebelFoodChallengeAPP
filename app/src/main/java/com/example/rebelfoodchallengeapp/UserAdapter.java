package com.example.rebelfoodchallengeapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rebelfoodchallengeapp.ui.main.PageViewModel;
import com.example.rebelfoodchallengeapp.ui.main.PlaceholderFragment;

import java.util.List;
import java.util.prefs.Preferences;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<users> usersList;
    Context context;
    dataWire dataWire = new dataWire();

    private PageViewModel pageViewModel;
    myItemRemovedListener myItemRemovedListener;

    public UserAdapter(List<users> usersList, Context context,myItemRemovedListener myItemRemovedListener) {
        this.usersList = usersList;
        this.context = context;
        this.myItemRemovedListener =myItemRemovedListener;



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_detail,parent,false);


        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("favuser",context.MODE_PRIVATE);

        final users usersStr = usersList.get(position);
        holder.username.setText(usersStr.getUsername());
        holder.imageViewFav.setImageResource(usersStr.getFavStatus());
        holder.name.setText(usersStr.getName());
        holder.email.setText(usersStr.getEmail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, position+" "+usersStr.getLat()+" "+usersStr.getLng(), Toast.LENGTH_SHORT).show();
                Log.e("card >>>> ",usersStr.getLat()+" "+usersStr.getLng());
                myItemRemovedListener.onCardClicked(Integer.parseInt(usersStr.getId())-1,usersStr.getLat(),usersStr.getLng());

            }
        });
        holder.imageViewFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!sharedPreferences.contains(position+"")) {
                    if (dataWire.getCurrentScreen().equals("1")) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(position + "", usersStr.getName());
                        editor.commit();
                        usersStr.setFavStatus(1);
                        holder.imageViewFav.setImageResource(usersStr.getFavStatus());

                        Toast.makeText(context, "" + usersStr.getName(), Toast.LENGTH_SHORT).show();

                    }
                    if (dataWire.getCurrentScreen().equals("2"))
                    {
                        int a = Integer.parseInt(usersStr.getId())-1;
                        sharedPreferences.edit().remove(String.valueOf(a)).commit();
                        usersStr.setFavStatus(0);
                        myItemRemovedListener.onItemRemved(position);
                        Toast.makeText(context, "item have been removed from fav TAB", Toast.LENGTH_LONG).show();
                    }
                }else
                {

                    if (dataWire.getCurrentScreen().equals("1")) {
                        sharedPreferences.edit().remove(String.valueOf(position)).commit();
                        usersStr.setFavStatus(0);
                        myItemRemovedListener.onItemRemved(position);

                    }
                    if (dataWire.getCurrentScreen().equals("2")) {
                        sharedPreferences.edit().remove(String.valueOf(position)).commit();
                        usersStr.setFavStatus(0);
                        myItemRemovedListener.onItemRemved(position);

                    }

                }
            }
        });



    }

    public  interface myItemRemovedListener
    {
        void onItemRemved(int posistion);
        void onCardClicked(int position, String lat ,String lng);

    }


    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView name;
        TextView email;
        ImageView imageViewFav;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username =  itemView.findViewById(R.id.textView);
            name = itemView.findViewById(R.id.textView2);
            email = itemView.findViewById(R.id.textView3);
            cardView = itemView.findViewById(R.id.cv);
            imageViewFav = itemView.findViewById(R.id.imageView);

        }
    }
}
