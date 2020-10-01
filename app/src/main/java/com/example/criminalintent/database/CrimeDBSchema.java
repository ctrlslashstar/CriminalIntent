package com.example.criminalintent.database;

public class CrimeDBSchema {
    public static final String NAME = "crime.db";
    public static final Integer VERSION = 1;

    public static final class CrimeTable {
        public static final String NAME = "crimeTable";

        public static final class Cols {
            //this column in only for database
            public static final String ID = "id";
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
        }
    }
}
