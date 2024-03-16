package com.example.weatherapp.Utilitites

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun formatDate(timestamp: Int): String {
    val sdf = SimpleDateFormat("EEEE, MMMM d, Y")
    val date = Date(timestamp.toLong() * 1000)
    return sdf.format(date)
}
@SuppressLint("SimpleDateFormat")
fun formatDateOnlyDayName(timestamp: Int): String {
    val sdf = SimpleDateFormat("EEEE")
    val date = Date(timestamp.toLong() * 1000)
    return sdf.format(date)
}


@SuppressLint("SimpleDateFormat")
fun formatDateTime(timestamp: Int): String {
    val sdf = SimpleDateFormat("hh:mm:aa")
    val date = Date(timestamp.toLong() * 1000)
    return sdf.format(date)
}
@SuppressLint("SimpleDateFormat")
fun formatDate2(timestamp: Int): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    val date = Date(timestamp.toLong() * 1000)
    return sdf.format(date)
}


fun formatDecimals(item: Double): String {
    return "%.0f".format(item)
}
