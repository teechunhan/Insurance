package com.example.insurance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    val selectedItem = spinnerAge.getItemAtPosition(position)
        /*val selectedItem = spinnerAge.selectedItemPosition*/
        Toast.makeText(this,"Selected Item =" + selectedItem,Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Handling item selected listener for the spinner
        spinnerAge.onItemSelectedListener = this

        buttonCalculate.setOnClickListener(){
            calculatePremium();
        }

        /*val buttonThird: Button
        buttonThird.setOnClickListener(this)*/
    }

    private fun calculatePremium(){
        //get the age group
        val age: Int =spinnerAge.selectedItemPosition

        var premium = when(age){
            0 -> 60//less than 17
            1 -> 70//from 17 to 25
            2 -> 90//from 26 to 30
            3 -> 120//from 31 to 40
            4 -> 150//from 41 to 55
            else ->150//more than 55

        }
        var extraMale=0
        //get the gender selection. ID of radio button
        val gender=radioGroupGender.checkedRadioButtonId
        if(gender == R.id.radioButtonMale){
            //calculate premium for male
            extraMale = when(age){
                0 -> 0//less than 17
                1 -> 50//from 17 to 25
                2 -> 100//from 26 to 30
                3 -> 150//from 31 to 40
                else ->200//more than 41
            }

        }else{
            //calculate premium for female

        }

        //Determine smoker or non-smoker
        val smoker: Boolean = checkBoxSmoker.isChecked
        if(smoker){
            //calculate the premium for smoker
            var extraSmoker = when(age){
                0 -> 0//less than 17
                1 -> 100//from 17 to 25
                2 -> 150//from 26 to 30
                3 -> 200//from 31 to 40
                4 -> 250//from 41 to 55
                else -> 300//more than 55
            }
            val symbol=Currency.getInstance(Locale.getDefault()).symbol
            textViewPremium.text = String.format("%s %d",symbol,premium)

            //val premium:Double = textViewPremium.text.toString().toDouble()
            if(gender == R.id.radioButtonMale) {
                textViewPremium.text = String.format(
                    "Premium =%s %d \nExtra payment for male=%s %d \n Extra Smoker payment=%s %d",
                    symbol,premium,
                    symbol,extraMale,
                    symbol,extraSmoker
                )
            }
            else{
                textViewPremium.text = String.format(
                    "Premium =%s %d \n Extra Smoker payment=%s %d",
                    symbol,premium,
                    symbol,extraSmoker
                )
            }
            /*var totalpayment = premium + gender + extraSmoker
            textViewPremium.text=String.format("%.2f",totalpayment)*/
        }
        else{
            textViewPremium.text="Cannot be calculate, because you have a healthy life"
        }
    }

    fun reset(view: View){
        spinnerAge.setSelection(0)
        if(checkBoxSmoker.isChecked){
            checkBoxSmoker.isChecked = false
        }
        if(radioButtonMale.isChecked || radioButtonMale.isChecked ) {
            radioButtonMale.isChecked = false
            radioButtonFemale.isChecked = false
        }
        textViewPremium.setText("")
    }
}
