package app.miyuseru.timesaber

import android.os.Bundle
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


        val titletext = intent.getStringExtra("Title")
        val contenttext = intent.getStringExtra("content")
        val deadline = intent.getStringExtra("deadline")
        val level = intent.getStringExtra("level")


        titleText.setText(titletext)
        contentText.setText(contenttext)
        deadlineButton.setText(deadline)
        levelSpinner.setText(level)


    }
}
