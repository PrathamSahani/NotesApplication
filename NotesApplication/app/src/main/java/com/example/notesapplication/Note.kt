package com.example.notesapplication

import android.icu.text.CaseMap.Title
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp


@Entity(tableName = "notesTable")
class Note (
    @ColumnInfo(name = "title")val noteTitle:String,
    @ColumnInfo(name = "description")val notDescription:String,
    @ColumnInfo(name = "timestamp")val timestamp: String)
{
    @PrimaryKey(autoGenerate =  true)
    var id = 0



}