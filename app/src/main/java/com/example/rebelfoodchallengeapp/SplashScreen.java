package com.example.rebelfoodchallengeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkStatus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

import java.util.concurrent.TimeUnit;

public class SplashScreen extends AppCompatActivity {

    private OneTimeWorkRequest oneTimeWorkRequest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataWire.setUrl("https://jsonplaceholder.typicode.com/users");
        setContentView(R.layout.activity_splash_screen);
        LottieAnimationView lottieAnimationView=findViewById(R.id.animation_view);
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();

        oneTimeWorkRequest1 = new   OneTimeWorkRequest.Builder(workManager.class).setInitialDelay(1, TimeUnit.SECONDS).addTag("newsWorker").build();
        WorkManager.getInstance().beginWith(oneTimeWorkRequest1).enqueue();


        WorkManager.getInstance().getStatusById(oneTimeWorkRequest1.getId()).observe(this, new Observer<WorkStatus>() {
            @Override
            public void onChanged(@Nullable WorkStatus listLiveData) {
                if (listLiveData != null && listLiveData.getState().isFinished()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                             startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    },1000);

                }

            }
        });

    }
}
