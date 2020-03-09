package app.miyuseru.timesaber

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_create.*
import java.util.*

class CreateActivity : AppCompatActivity() {


    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    private var mYear: Int = 0
    private var mMonth: Int = 0
    private var mDay: Int = 0

//    val nowdate = SimpleDateFormat("yyyy-MM-dd").format(Date())

    private val mOnDateClickListener = View.OnClickListener {
        val datePickerDialog = DatePickerDialog(
            this@CreateActivity,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

                val dateString = mYear.toString() + "/" + String.format(
                    "%02d",
                    mMonth + 1
                ) + "/" + String.format("%02d", mDay)
                dateButton.setText(dateString)
            }, mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)


        dateButton.setOnClickListener(mOnDateClickListener)

        val calendar = Calendar.getInstance()
        mYear = calendar.get(Calendar.YEAR)
        mMonth = calendar.get(Calendar.MONTH)
        mDay = calendar.get(Calendar.DAY_OF_MONTH)





        createButton.setOnClickListener() {
            startActivity(Intent(this, MainActivity::class.java))
            create(
                titleText.text.toString(),
                contentText.text.toString(),
                dateButton.text.toString()

                //levelSpinner.text
            )


        }

    }

    fun create(title: String, content: String, deadline: String) {

        realm.executeTransaction {
            val task = it.createObject(Task::class.java, UUID.randomUUID().toString())
            task.Title = title
            task.content = content
            task.deadline = deadline
            // task.level = level

        }
        Log.d("todo", content)
    }
}



