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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.entities.Item
import com.example.gestionpresupuesto.repository.BudgetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDateTime
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

        var pdfDocument = PdfDocument()

        var itemList = budgetDetails.productsItems

        var pageHeight = 1120
        var pageWidth = 792

        var paint = Paint()
        var titulo = TextPaint()
        var fecha = TextPaint()
        var total = TextPaint()
        var items = TextPaint()

        var paginaInfo = PdfDocument.PageInfo.Builder(816, 1054, 1).create()
        var pagina1 = pdfDocument.startPage(paginaInfo)


        var canvas = pagina1.canvas

        var bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.cctvlogo)
        var bitmapEscala = Bitmap.createScaledBitmap(bitmap, 300,300, false)
        canvas.drawBitmap(bitmapEscala, 368f, 20f, paint)

        var alto : Float = 360f
        var derecha : Float = 100f

        var tituloText = "Presupuesto"
        var date = budgetDetails.budgetDate.toDate().toString()
        var totalGross = "TOTAL: " + budgetDetails.totalGross
        var PdfName = budgetDetails.budgetNumber.toString() + ".pdf"

        titulo.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))
        titulo.textSize = 20f

        canvas.drawText(date, 480f, 335f, fecha)

        canvas.drawText(tituloText, derecha, alto, titulo)

        //derecha = 380f
        alto  = 420f


        for (item in itemList) {

            var descripcionText =  "Producto : " +  item.name + " Cantidad : " + item.quantity

            canvas.drawText(descripcionText, derecha, alto, items)

            alto += 35f

        }

        alto += 35f
        canvas.drawText(totalGross.toString(), derecha, alto , total)


        pdfDocument.finishPage(pagina1)



        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            PdfName)

        try {
            pdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(context, "Se creo el PDF correctamente", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()


            pdfDocument.close()

        }
    }
}