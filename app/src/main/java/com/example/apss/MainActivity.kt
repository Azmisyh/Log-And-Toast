package com.example.apss

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var tvSelectedDateTime: TextView
    private val calendar = Calendar.getInstance()
    private val TAG = "MainActivity" // Tag untuk log

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.v(TAG, "Aplikasi mulai dijalankan")
        Log.d(TAG, "onCreate: Activity sedang dibuat")

        Toast.makeText(applicationContext, "Halo Azmi", Toast.LENGTH_SHORT).show()

        tvSelectedDateTime = findViewById(R.id.tvSelectedDateTime)

        // Date Picker
        findViewById<Button>(R.id.btnDatePicker).setOnClickListener {
            Log.d(TAG, "Tombol Date Picker diklik")
            showDatePicker()
        }

        // Time Picker
        findViewById<Button>(R.id.btnTimePicker).setOnClickListener {
            Log.d(TAG, "Tombol Time Picker diklik")
            showTimePicker()
        }

        // Alert Dialog
        findViewById<Button>(R.id.btnShowAlert).setOnClickListener {
            Log.d(TAG, "Tombol Alert Dialog diklik")
            showAlertDialog()
        }
    }

    private fun showDatePicker() {
        Log.d(TAG, "Menampilkan Date Picker Dialog")
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                Log.i(TAG, "Tanggal dipilih: $dayOfMonth-${month+1}-$year")
                updateSelectedDateTime()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun showTimePicker() {
        Log.d(TAG, "Menampilkan Time Picker Dialog")
        val timePicker = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                Log.i(TAG, "Waktu dipilih: $hourOfDay:$minute")
                updateSelectedDateTime()
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePicker.show()
    }

    private fun updateSelectedDateTime() {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
        val formattedDateTime = dateFormat.format(calendar.time)
        tvSelectedDateTime.text = "Tanggal dan waktu yang dipilih: $formattedDateTime"
        Log.d(TAG, "Tanggal dan waktu diperbarui: $formattedDateTime")
    }

    private fun showAlertDialog() {
        Log.d(TAG, "Menampilkan Alert Dialog")
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Konfirmasi")
            .setMessage("Apakah Anda yakin dengan pilihan tanggal dan waktu?")
            .setPositiveButton("Ya") { _: DialogInterface, _: Int ->
                Toast.makeText(applicationContext, "Pilihan dikonfirmasi", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Pengguna memilih Ya pada Alert Dialog")
            }
            .setNegativeButton("Tidak") { _: DialogInterface, _: Int ->
                Toast.makeText(applicationContext, "Pilihan dibatalkan", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Pengguna memilih Tidak pada Alert Dialog")
            }
            .setNeutralButton("Nanti") { _: DialogInterface, _: Int ->
                Toast.makeText(applicationContext, "Anda memilih nanti", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Pengguna memilih Nanti pada Alert Dialog")
            }
            .create()

        alertDialog.show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Activity mulai terlihat")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Activity sedang berjalan")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Activity sedang dijeda")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Activity berhenti terlihat")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Activity dihancurkan")
    }
}
