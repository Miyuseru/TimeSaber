package app.miyuseru.timesaber

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm

class TaskActivity : AppCompatActivity() {

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

    }
}
