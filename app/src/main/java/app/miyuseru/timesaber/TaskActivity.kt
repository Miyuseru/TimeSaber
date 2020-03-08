package app.miyuseru.timesaber

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort

class TaskActivity : AppCompatActivity() {

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)




        fun readAll(): RealmResults<Task> {
            return realm.where(Task::class.java).findAll().sort("deadline", Sort.ASCENDING)
        }
//        val titleText = intent.getStringExtra("Title")
//        val contentText = intent.getStringExtra("content")

//        titleText.text = titleText
//        contentText.text = contentText


    }


}
