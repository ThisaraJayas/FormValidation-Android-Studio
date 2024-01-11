package com.example.tutorial5

import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.tutorial5.R.id.tvYear
import com.example.tutorial5.models.FormData
import com.example.tutorial5.models.validations.ValidationResult

class MainActivity : AppCompatActivity() {
    lateinit var editStudentId:EditText
    lateinit var spnYear:Spinner
    lateinit var spnSemester:Spinner
    lateinit var cbAgree:CheckBox
    lateinit var tvYear:TextView
    lateinit var tvSemester:TextView
    private var count = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editStudentId = findViewById(R.id.editStudentId)
        tvYear = findViewById(R.id.tvYear)
        spnYear = findViewById(R.id.spnYear)
        tvSemester = findViewById(R.id.tvSemester)
        spnSemester = findViewById(R.id.spnSemester)
        cbAgree=findViewById(R.id.cbAgree)
    }
    //alert box can use in other code also
    fun displayAlert(title: String,message: String){
        val  builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok") { dialog, which ->
            // Do something when the "OK" button is clicked
        }
        val dialog = builder.create()
        dialog.show()
    }
    fun submit(v:View){
        val myForm = FormData(
            editStudentId.text.toString(),
            spnYear.selectedItem.toString(),
            spnSemester.selectedItem.toString(),
            cbAgree.isChecked
        )
        val studentIdValidation = myForm.validateStudentID()
        val spnYearValidation = myForm.validateYear()
        val spnSemesterValidation = myForm.validateSemester()
        val cbAgreeValidation = myForm.validateAgreement()
        when(studentIdValidation){
            is ValidationResult.Valid ->{
                count ++
            }
            is ValidationResult.Invalid ->{
                editStudentId.error = studentIdValidation.errorMessage
            }
            is ValidationResult.Empty ->{
                editStudentId.error = studentIdValidation.errorMessage
            }
        }
        when(spnYearValidation){
            is ValidationResult.Valid ->{
                count ++
            }
            is ValidationResult.Invalid ->{
                val tv:TextView = spnYear.selectedView as TextView
                tv.error =""
                tv.text = spnYearValidation.errorMessage
            }
            is ValidationResult.Empty ->{
                val tv:TextView = spnYear.selectedView as TextView
                tv.error =""
                tv.text = spnYearValidation.errorMessage
            }
        }
        when(spnSemesterValidation){
            is ValidationResult.Valid ->{
                count ++
            }
            is ValidationResult.Invalid ->{
                val tv:TextView = spnSemester.selectedView as TextView
                tv.error =""
                tv.text = spnSemesterValidation.errorMessage
            }
            is ValidationResult.Empty ->{
                val tv:TextView = spnSemester.selectedView as TextView
                tv.error =""
                tv.text = spnSemesterValidation.errorMessage
            }
        }
        when(cbAgreeValidation){
            is ValidationResult.Valid ->{
                count ++
            }
            is ValidationResult.Invalid ->{
                displayAlert("Error", cbAgreeValidation.errorMessage)
            }
            is ValidationResult.Empty ->{
            }
        }
        if(count==4){
            displayAlert("Success","You have successfully registered")
        }
    }
}