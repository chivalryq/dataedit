package com.example.dataedit

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.core.content.contentValuesOf
import com.example.dataedit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val uri = Uri.parse("content://com.example.datashow.provider/staff")
    private val STAFF_TABLE = "staff"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addData.setOnClickListener {
            val staffId = binding.inputId.text.toString()
            if (staffId.isEmpty()) {
                return@setOnClickListener
            }

        }
        binding.deleteData.setOnClickListener {
            val staffId = binding.inputId.text.toString()
            if (staffId.isEmpty()) {
                return@setOnClickListener
            }
            contentResolver.delete(uri, "id = ?", arrayOf(staffId))
        }
        Log.d("binding","biding listener")
        binding.updateData.setOnClickListener {
            Log.d("update-btn","$$$")
            val staffId = binding.inputId.text.toString()
            if (staffId.isEmpty()) {
                return@setOnClickListener
            }
            Log.d("update-btn",staffId)
            staffId.let {
                val uri = Uri.parse("content://com.example.datashow.provider/$STAFF_TABLE/$it")
                val values = ContentValues().apply {
                    if (binding.inputName.text.isEmpty()) {
                        put("name", binding.inputName.text.toString())
                    }
                    if (binding.inputGender.text.isEmpty()) {
                        put("gender", binding.inputGender.text.toString())
                    }
                    if (binding.inputDepartment.text.isEmpty()) {
                        put("department", binding.inputDepartment.text.toString())
                    }
                    if (binding.inputSalary.text.isEmpty()) {
                        put("salary", binding.inputSalary.text.toString())
                    }
                }
                Log.d("update-btn", values.toString())
                contentResolver.update(uri, values, "id = ?", arrayOf(staffId))
            }
        }
    }
}

