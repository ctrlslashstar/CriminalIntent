package com.example.criminalintent.repository;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.criminalintent.database.CrimeDBSchema;
import com.example.criminalintent.model.Crime;

import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        UUID uuid = UUID.fromString(getString(getColumnIndex(CrimeDBSchema.CrimeTable.Cols.UUID)));
        String title = getString(getColumnIndex(CrimeDBSchema.CrimeTable.Cols.TITLE));
        Date date = new Date(getLong(getColumnIndex(CrimeDBSchema.CrimeTable.Cols.DATE)));
        boolean solved = getInt(getColumnIndex(CrimeDBSchema.CrimeTable.Cols.SOLVED)) == 0 ? false : true;

        return new Crime(uuid, title, date, solved);
    }
}
