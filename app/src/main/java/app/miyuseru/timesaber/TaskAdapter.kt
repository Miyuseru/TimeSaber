package app.miyuseru.timesaber

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm


private val realm: Realm by lazy {
    Realm.getDefaultInstance()
}
//
//class TaskAdapter(
//
////    private val context: Context,
////    private var taskList: OrderedRealmCollection<Task>?,
////    private var listener: AdapterView.OnItemClickListener,
////    private val autoUpdate: Boolean
//
//) :


class TaskAdaptar : AppCompatActivity() {

    private var task: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        Log.d("onCreate:", "OK")

        task = realm
            .where(Task::class.java)
            .equalTo("Title", intent.getStringExtra("Title"))
            .findFirst()

        showData()
    }


    private fun showData() {
        task?.let {
//            titleTextView.text = it.Todo
//            contentTextView.text = it.content
        }
    }

}

