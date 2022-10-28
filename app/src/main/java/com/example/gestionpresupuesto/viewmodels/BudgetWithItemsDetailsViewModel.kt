package com.example.gestionpresupuesto.viewmodels

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.text.TextPaint
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.repository.BudgetRepository
import com.google.firebase.Timestamp.now
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.time.Instant.now
import java.time.LocalDate.now
import java.time.LocalDateTime
import java.time.LocalTime.now
import java.util.*

class BudgetWithItemsDetailsViewModel : ViewModel() {

    var budgetRepository = BudgetRepository()

    fun deleteBudget(budgetToDelete: Budget) {
        viewModelScope.launch(Dispatchers.Main) {

            try {

                budgetRepository.deleteBudget(budgetToDelete)

            } catch (e: Exception) {

                Log.d("BudgetCreatorViewModel", e.message.toString())

            }
        }

    }


    fun createPDF(context : Context, budgetDetails : Budget) {

    }
}