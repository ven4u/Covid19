package com.android.covid19.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.android.covid19.database.dao.CountriesDao;
import com.android.covid19.database.tables.Countries;

@Database(entities = {Countries.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
        public abstract CountriesDao countriesDao();
}
