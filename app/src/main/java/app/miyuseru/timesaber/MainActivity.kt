package app.miyuseru.timesaber

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmResults
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

            val date = "$year/${month + 1}/$dayOfMonth"

            Log.d("date", date)

            //アイテムを見つける item→dete=deadline

            // 該当アイテムの取得
            val task = realm
                .where(Task::class.java)
                .equalTo("deadline", date)
                .findFirst()

            task?.let {
                Log.d("task", it.deadline)
            }

//            val item = date.equals(Task::deadline)

            val preview = Intent(applicationContext, TaskActivity::class.java)
//
//            //itemの
//            preview.putExtra("Title", date)
//            preview.putExtra("content", date)
            startActivity(preview)
//            Log.d("click", "click")
        }
    }

    override fun onStart() {
        super.onStart()
        // 全タスクの確認
        val results: RealmResults<Task> = realm.where(Task::class.java).findAll().sort("deadline")

        for (result in results) {
            Log.d("task.deadline", result.deadline)
        }

    }
}

