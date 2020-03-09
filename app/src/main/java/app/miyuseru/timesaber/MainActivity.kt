package app.miyuseru.timesaber

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener {
            startActivity(Intent(applicationContext, CreateActivity::class.java))
        }


        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = "$year/$month/$dayOfMonth"

            //アイテムを見つける item→dete=deadline
            val item = date.equals(Task::deadline)

            val preview = Intent(applicationContext, TaskActivity::class.java)

            //itemの
            preview.putExtra("Title", date)
            preview.putExtra("content", date)
            startActivity(preview)
//            Log.d("click", "click")

        }


    }

}

