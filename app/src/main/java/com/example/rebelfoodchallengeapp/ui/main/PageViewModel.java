package com.example.rebelfoodchallengeapp.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.rebelfoodchallengeapp.users;

import java.util.List;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "" + input;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }


   private MutableLiveData<List<users>> liveFavData = new MutableLiveData<>();
    private LiveData<List<users>> usersLiveData = Transformations.map(liveFavData, new Function<List<users>, List<users>>() {
        @Override
        public List<users> apply(List<users> input) {
            return input;
        }
    });

    public LiveData<List<users>> getUsersLiveData()
    {
        return usersLiveData;
    }
    public  void setUsersLiveData(List<users> users)
    {
        liveFavData.setValue(users);
    }

}