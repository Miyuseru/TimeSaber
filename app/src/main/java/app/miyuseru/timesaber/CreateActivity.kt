package app.miyuseru.timesaber

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RatingBar
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

    // var selectedItems: Array<Int> = arrayOf(0, 0, 0, 0, 0, 0, 0, 0)


//    private val mOnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//        override fun onNothingSelected(parent: AdapterView<*>?) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//
////        override fun onItemSelected(parent: AdapterView<*>, view: View, postion: Int, id: Long) {
////            Log.d("position", postion.toString())
////
////            when (parent.id) {
////                app.miyuseru.timesaber.R.id.levelSpinner -> {
////
////                    selectedItems[0] = postion
////                    selectedItems[1] = postion
////
////                    selectedItems[2] = postion
////                    selectedItems[3] = postion
////                    selectedItems[4] = postion
////
////                }
////
////
////            }
////
////        }
//
//
//    }


    private val mOnDateClickListener = View.OnClickListener {
        val datePickerDialog = DatePickerDialog(
            this@CreateActivity,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

                mYear = year
                mMonth = monthOfYear
                mDay = dayOfMonth

                val dateString = "$mYear/" + String.format(
                    "%02d",
                    mMonth + 1
                ) + "/" + String.format("%02d", mDay)
                deadlineButton.text = dateString
            }, mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        //  val ratingBar = ratingBar ?: ratingBar

        ratingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { p0, p1, p2 ->
                Log.d(
                    "rating_bar",
                    ratingBar.rating.toString()
                )


            }


        // setSpinnerAdapter()
        deadlineButton.setOnClickListener(mOnDateClickListener)

        val calendar = Calendar.getInstance()
        mYear = calendar.get(Calendar.YEAR)
        mMonth = calendar.get(Calendar.MONTH)
        mDay = calendar.get(Calendar.DAY_OF_MONTH)

        createButton.setOnClickListener() {
            startActivity(Intent(this, MainActivity::class.java))
            create(
                titleText.text.toString(),
                contentText.text.toString(),
                deadlineButton.text.toString()
                // levelSpinner.toString()

            )
        }
    }


    private fun create(title: String, content: String, deadline: String) {
//
//        var spinner = findViewById(R.id.spinner) as Spinner
//        var level = spinner.selectedItemPosition

        realm.executeTransaction {
            val task = it.createObject(Task::class.java, UUID.randomUUID().toString())
            task.title = title
            task.content = content
            task.deadline = deadline
            //  task.level = level

        }
        Log.d("title", title)
    }

//    private fun setSpinnerAdapter() {
//        val spinnerAdapter = ArrayAdapter<String>(
//            this,
//            android.R.layout.simple_spinner_item,
//            resources.getStringArray(app.miyuseru.timesaber.R.array.list)
//        )
//
//
//        val spinner = arrayOf(
//            levelSpinner
//
//        )
//
//        for (s in spinner) {
//            s.adapter = spinnerAdapter
//            s.onItemSelectedListener = mOnItemSelectedListener
//        }
//
//    }
}




