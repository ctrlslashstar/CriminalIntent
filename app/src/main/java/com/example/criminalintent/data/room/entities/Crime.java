package com.example.criminalintent.data.room.entities;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity(tableName = "crime")
public class Crime {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private UUID mId;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "date")
    private Date mDate;

    @ColumnInfo(name = "solved")
    private boolean mSolved;

    @ColumnInfo(name = "suspect")
    private String mSuspect;

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }

    @Ignore
    public Crime(UUID id, String title, Date date, boolean solved, String suspect) {
        mId = id;
        mTitle = title;
        mDate = date;
        mSolved = solved;
        mSuspect = suspect;
    }

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    /**
     * This is calculator field.
     * @return
     */
    public String getPhotoFileName() {
        return "IMG_" + getId() + ".jpg";
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crime crime = (Crime) o;
        return mSolved == crime.mSolved &&
                mId.equals(crime.mId) &&
                Objects.equals(mTitle, crime.mTitle) &&
                Objects.equals(mDate, crime.mDate) &&
                Objects.equals(mSuspect, crime.mSuspect);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(mId, mTitle, mDate, mSolved, mSuspect);
    }
}
