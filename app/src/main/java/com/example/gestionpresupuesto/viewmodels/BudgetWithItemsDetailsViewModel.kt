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

    fun createPDF(context : Context) {

        var tituloText = "Presupuesto"
        var descripcionText =  "Presupuesto";
        var date = LocalDateTime.now().toString()

        var pageHeight = 1120
        var pageWidth = 792


        var pdfDocument = PdfDocument()
        var paint = Paint()
        var titulo = TextPaint()
        var descripcion = TextPaint()
        var fecha = TextPaint()

        var paginaInfo = PdfDocument.PageInfo.Builder(816, 1054, 1).create()
        var pagina1 = pdfDocument.startPage(paginaInfo)

        var canvas = pagina1.canvas

        var bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.cctvlogo)
        var bitmapEscala = Bitmap.createScaledBitmap(bitmap, 300,300, false)
        canvas.drawBitmap(bitmapEscala, 368f, 20f, paint)

        titulo.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))
        titulo.textSize = 20f
        canvas.drawText(tituloText, 10f, 150f, titulo)
        canvas.drawText(date, 10f, 150f, fecha)

        descripcion.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
        descripcion.textSize = 14f

        var arrDescripcion = descripcionText.split("\n")

        var y = 200f
        for (item in arrDescripcion) {
            canvas.drawText(item, 10f, y, descripcion)
            y += 15
        }

        pdfDocument.finishPage(pagina1)

        pdfDocument.toString()

        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            "presupuesto.pdf")

        try {
            pdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(context, "Se creo el PDF correctamente", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()


        pdfDocument.close()

    }
}
}