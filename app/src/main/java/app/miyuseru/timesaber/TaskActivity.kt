package app.miyuseru.timesaber

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_create.contentText
import kotlinx.android.synthetic.main.activity_create.deadlineButton
import kotlinx.android.synthetic.main.activity_create.titleText
import kotlinx.android.synthetic.main.activity_task.*
import java.util.*

class TaskActivity : AppCompatActivity() {

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        floatingActionButton.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))

            onUpdate(
                titleText.text.toString(),
                contentText.text.toString(),
                deadlineButton.text.toString()
                // levelSpinner.toString()

            )
        }


        val titletext = intent.getStringExtra("Title")
        val contenttext = intent.getStringExtra("content")
        val deadline = intent.getStringExtra("deadline")
        val level = intent.getStringExtra("level")


        titleText.setText(titletext)
        contentText.setText(contenttext)
        deadlineButton.setText(deadline)
        //levelView.setText(level)


    }

     fun onUpdate(title: String, content: String, deadline: String) {


        realm.executeTransaction {
            val task = it.createObject(Task::class.java, UUID.randomUUID().toString())
            task.title = title
            task.content = content
            task.deadline = deadline
            //  task.level = level

        }
        Log.d("title", title)
    }


}


