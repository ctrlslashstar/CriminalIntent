package com.example.criminalintent;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.criminalintent.data.room.CrimeRoomDataBase;
import com.example.criminalintent.data.room.entities.Crime;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
public class RoomDataBaseTest {

    private CrimeRoomDataBase mDataBase;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        mDataBase = Room
                .inMemoryDatabaseBuilder(
                        ApplicationProvider.getApplicationContext(),
                        CrimeRoomDataBase.class)
                .build();
    }

    @After
    public void closeDb() throws Exception {
        mDataBase.close();
    }

    @Test
    public void insertAndGetCrime() throws InterruptedException {
        Crime testCrime = new Crime(
                UUID.randomUUID(),
                "testTitle",
                new Date(),
                true,
                "testSuspect");

        mDataBase.getCrimeDAO().insertCrimes(testCrime);

        LiveData<Crime> crimeLiveData = mDataBase.getCrimeDAO().getCrime(testCrime.getId());
        Crime crime = LiveDataTestUtil.getValue(crimeLiveData);
        Assert.assertNotNull(crime);
        Assert.assertEquals(testCrime, crime);

        /*LiveData<Crime> crimeLiveData = mDataBase.getCrimeDAO().getCrime(testCrime.getId());
        CountDownLatch latch = new CountDownLatch(1);
        final Crime[] resultCrime = new Crime[1];
        crimeLiveData.observeForever(new Observer<Crime>() {
            @Override
            public void onChanged(Crime crime) {
                resultCrime[0] = crime;
                latch.countDown();
            }
        });
        //stop until we get the crime from background task
        latch.await();

        Assert.assertNotNull(resultCrime[0]);
        Assert.assertEquals(testCrime, resultCrime[0]);*/
    }
}
