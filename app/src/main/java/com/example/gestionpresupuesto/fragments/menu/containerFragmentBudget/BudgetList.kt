package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.text.TextPaint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.adapters.BudgetAdapter
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.viewmodels.BudgetListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.FileOutputStream
import java.util.*


class BudgetList : Fragment()  {

    companion object {
        fun newInstance() = BudgetList()
    }

    lateinit var v : View
    lateinit var recBudgets : RecyclerView
    private lateinit var viewModel: BudgetListViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var budgetAdapter : BudgetAdapter
    private lateinit var searchView : SearchView
    private lateinit var temporalBudgetList : MutableList<Budget>
    private lateinit var buttonAdd : FloatingActionButton

    private var activityResultLauncher: ActivityResultLauncher<Array<String>>
    init{
        this.activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()) { result ->
            var allAreGranted = true
            for(b in result.values) {
                allAreGranted = allAreGranted && b
            }

            if(allAreGranted) {
                //capturePhoto()
            }
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_main_budget_list, container, false)
        recBudgets = v.findViewById(R.id.rec_budgets)
        searchView = v.findViewById(R.id.searchViewBudget)
        buttonAdd = v.findViewById(R.id.floating_action_button_user)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BudgetListViewModel::class.java)

    }

    override fun onStart() {

        super.onStart()
        createPDF()

        viewModel.getAllBudgets()

        viewModel.budgetList.observe(viewLifecycleOwner, Observer { result ->

            searchView.setQuery("", false);
            var budgetList = result
            recBudgets.setHasFixedSize(true)
            linearLayoutManager = LinearLayoutManager(context)
            recBudgets.layoutManager = linearLayoutManager
            budgetAdapter = BudgetAdapter(budgetList, requireContext())

            recBudgets.adapter = budgetAdapter

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(query : String?): Boolean {
                    search(budgetList,query)
                    return true
                }

            })
        })

        buttonAdd.setOnClickListener {
            val action = BudgetListDirections.actionMainBudgetListToBudgetForm()
            v.findNavController().navigate(action)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun search(budgetList : MutableList<Budget>, query: String?) {

        val temporalBudgetList = mutableListOf<Budget>()
        val queryLowerCase = query!!.lowercase(Locale.getDefault())

        budgetList.forEach {
            if (it.clientDomicile.lowercase().contains(queryLowerCase)) {
                temporalBudgetList.add(it)
            }
                 var auxiliarAdapter = BudgetAdapter(temporalBudgetList, requireContext())
                     recBudgets.setAdapter(auxiliarAdapter)

                 }
        }

    fun onItemClick ( position : Int )  {
        Snackbar.make(v,position.toString(), Snackbar.LENGTH_SHORT).show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BudgetListViewModel::class.java)
        // TODO: Use the ViewModel
    }


    private fun createFile(pickerInitialUri: Uri) {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/pdf"
            putExtra(Intent.EXTRA_TITLE, "invoice.pdf")
        }
    }

    fun createPDF() {

                var tituloText = "Presupuesto"
                var descripcionText =  "Presupuesto";

                var pageHeight = 1120
                var pageWidth = 792


                var pdfDocument = PdfDocument()
                var paint = Paint()
                var titulo = TextPaint()
                var descripcion = TextPaint()

                var paginaInfo = PdfDocument.PageInfo.Builder(816, 1054, 1).create()
                var pagina1 = pdfDocument.startPage(paginaInfo)

                var canvas = pagina1.canvas

                var bitmap = BitmapFactory.decodeResource(resources, R.drawable.cctvlogo)
                var bitmapEscala = Bitmap.createScaledBitmap(bitmap, 80,80, false)
                canvas.drawBitmap(bitmapEscala, 368f, 20f, paint)

                titulo.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))
                titulo.textSize = 20f
                canvas.drawText(tituloText, 10f, 150f, titulo)

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
                    "test_presupuesto.pdf")

                try {
                    pdfDocument.writeTo(FileOutputStream(file))
                    Toast.makeText(requireContext(), "Se creo el PDF correctamente", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                pdfDocument.close()

            }
    }










