package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvResult: TextView

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result ->
        if(result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null){
            val selectedValue =
                result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0 )
            tvResult.text = "Hasil : $selectedValue"
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveWithData: Button = findViewById(R.id.btn_move_with_data)
        btnMoveWithData.setOnClickListener(this)

        val btnMoveWithParcel: Button = findViewById(R.id.btn_move_with_parcel)
        btnMoveWithParcel.setOnClickListener(this)

        val btnDialANumber: Button = findViewById(R.id.btn_dial_number)
        btnDialANumber.setOnClickListener(this)

        val btnMoveForResult: Button = findViewById(R.id.btn_choose_number)
        btnMoveForResult.setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result)
    }




    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_move_with_data -> {
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Erlya")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 19)
                startActivity(moveWithDataIntent)
            }
            R.id.btn_move_with_parcel -> {
                val person = Person(
                    "Erlya",
                    19,
                    "erlya@sweet.heart",
                    "Surabaya"
                )

                val moveWithDataParcel = Intent(this@MainActivity, MoveWithParcelActivity::class.java)
                moveWithDataParcel.putExtra(MoveWithParcelActivity.EXTRA_PERSON, person)
                startActivity(moveWithDataParcel)

            }

            R.id.btn_dial_number -> {
                val phoneNumber = "087851224563"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_choose_number -> {
                val chooseNumberIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                resultLauncher.launch(chooseNumberIntent)
            }
        }
    }
}