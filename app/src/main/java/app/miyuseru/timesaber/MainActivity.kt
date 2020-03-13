package app.miyuseru.timesaber

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    private val mCalendarAdapter = CalendarAdapter(this)

    private var dateArray: List<Date> = ArrayList()
    var mDateManager: DateManager = DateManager()

    private lateinit var behavior: LockableBottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        behavior = BottomSheetBehavior.from(bottom_sheet) as LockableBottomSheetBehavior<*>
        behavior.locked = true

        button.setOnClickListener {
            startActivity(Intent(applicationContext, CreateActivity::class.java))
        }

        prevButton.setOnClickListener {
            mCalendarAdapter.run { prevMonth() }
            titleText.text = mCalendarAdapter.getTitle()
        }

        nextButton.setOnClickListener {
            mCalendarAdapter.nextMonth()
            titleText.text = mCalendarAdapter.getTitle()
        }

        calendarGridView.adapter = mCalendarAdapter
        titleText.text = mCalendarAdapter.getTitle()


        calendarGridView.onItemClickListener = this

        taskTitle.setOnClickListener {
            // do something
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

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d("position", position.toString())
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN)
        Log.d("data", dateFormat.format(mCalendarAdapter.getDayOfWeek(position)))
        val itemDeadline = dateFormat.format(mCalendarAdapter.getDayOfWeek(position))

        // ボタンクリックによって、Expand・Collapseを制御する
        when (behavior.state) {
            BottomSheetBehavior.STATE_COLLAPSED -> {
                behavior.state = BottomSheetBehavior.STATE_HIDDEN
            }
            BottomSheetBehavior.STATE_HIDDEN -> {
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        val task = realm.where(Task::class.java).equalTo("deadline", itemDeadline).findFirst()

        val title = task?.title

        if (task != null) {
            taskTitle.text = title.toString()
        } else {
            taskTitle.text = ""
        }

    }


//    val taskTitleView: TextView = findViewById(R.id.taskTitle)

//    private fun taskTitleView.setOnItemClickListener() {
//
//        val preview = Intent(TaskActivity::class.java)
//
//        if (task != null) {
//            preview.putExtra("Title", task.Title)
//        }
//        if (task != null) {
//            preview.putExtra("content", task.content)
//        }
//        if (task != null) {
//            preview.putExtra("deadline", task.deadline)
//        }
//        if (task != null) {
//            preview.putExtra("level", task.level)
//        }
//
//        //startActivity(preview)
//        Log.d("click", "click")
//    }


}


//calendarGridView.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
//            var mDateManager: DateManager = DateManager()
//            val date = mDateManager.getDays()!!
//
//            //
//                view, year, month, dayOfMonth ->
//
//            val date =
//                "$year/" + String.format(
//                    "%02d",
//                    month + 1
//                ) + "/" + String.format("%02d", dayOfMonth)
//            "$year/${month + 1}/$dayOfMonth"
//
//
//
//            //DateManagerから日付を取得
//            //押した日付とdeadlineが一致するタスクを取得してタスクアクティビティへ表示
//
//
//            //  Log.d("date", date)
//
//            //アイテムを見つける item→dete=deadline
//
//            // 該当アイテムの取得
//            val task = realm
//                .where(Task::class.java)
//                 .equalTo("deadline", date)
//                .findFirst()
//
//            task?.let {
//                Log.d("task", it.deadline)
//            }
//            Log.d("task content", task.toString())
//
//            val item = date.equals(Task::deadline)
//
//            //  val preview = Intent(applicationContext, TaskActivity::class.java)
//
//            //itemの
//            if (task != null) {
//                preview.putExtra("Title", task.Title)
//            }
//            if (task != null) {
//                preview.putExtra("content", task.content)
//            }
//            if (task != null) {
//                preview.putExtra("deadline", task.deadline)
//            }
//            if (task != null) {
//                preview.putExtra("level", task.level)
//            }
//
//            //startActivity(preview)
//            Log.d("click", "click")
//        }


//やること
//デザイン
//タスクの保存
//タスクの割り振り
