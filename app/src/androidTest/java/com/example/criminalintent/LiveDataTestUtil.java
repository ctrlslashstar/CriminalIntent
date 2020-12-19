package com.example.criminalintent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.CountDownLatch;

public class LiveDataTestUtil {

    public static<T> T getValue(LiveData<T> liveData) throws InterruptedException {
        final Object[] result = new Object[1];
        CountDownLatch latch = new CountDownLatch(1);

        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(T t) {
                result[0] = t;
                latch.countDown();
            }
        };
        liveData.observeForever(observer);
        latch.await();

        return (T) result[0];
    }
}
