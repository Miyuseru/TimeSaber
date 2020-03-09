package app.miyuseru.timesaber

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_create.*

class TaskActivity : AppCompatActivity() {

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val task = realm
            .where(Task::class.java)
            .equalTo("deadline", String())
            .findFirst()

        task?.let {
            Log.d("task", it.deadline)

            val titletext = intent.getStringExtra("Title")
            val contenttext = intent.getStringExtra("content")

            titleText.setText(titletext)
            contentText.setText(contenttext)
        }


    }
}
