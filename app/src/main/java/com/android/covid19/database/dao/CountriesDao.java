package com.android.covid19.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.android.covid19.database.tables.Countries;

import java.util.List;

@Dao
public interface  CountriesDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Countries countries);

    @Query("DELETE FROM countries_table")
        void deleteAll();

    @Query("SELECT * from countries_table ORDER BY slug ASC")
    List<Countries> getAlphabetizedCountries();

    @Delete
    void delete(Countries countries);

    @Update
    void update(Countries countries);

}
