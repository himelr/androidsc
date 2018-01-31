package com.example.cursorloader;

import android.arch.persistence.room.*;

import java.util.List;

/**
 * Created by HimelR on 31-Jan-18.
 */

@Dao
public interface PresidentDao {
    @Query("SELECT * FROM president")
    List<President> getAll();

    @Query("SELECT * FROM president WHERE id IN (:userIds)")
    List<President> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM president WHERE name LIKE :name LIMIT 1")
    President findByName(String name);

    @Query("SELECT * FROM president WHERE id = :id")
    President findByid(int id);

    @Insert
    void insertAll(President... presidents);

    @Delete
    void delete(President president);
}
