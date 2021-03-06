package com.example.cursorloader;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import java.util.List;

/**
 * Created by HimelR on 31-Jan-18.
 */

@Dao
public interface PresidentDao {
    @Query("SELECT * FROM president")
    LiveData<List<President>> getAll();

    @Query("SELECT * FROM president WHERE id IN (:userIds)")
    List<President> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM president WHERE name LIKE :name LIMIT 1")
    LiveData<President> findByName(String name);

    @Query("SELECT * FROM president WHERE id = :id")
    President findByid(int id);

    @Insert
    void insertAll(President... presidents);

    @Delete
    void delete(President president);

    @Update
    void update(President president);

    @Query("DELETE FROM president WHERE id = :id")
    void deleteID(int id);
}
