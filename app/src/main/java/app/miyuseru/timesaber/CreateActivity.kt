package app.miyuseru.timesaber

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_create.*
import java.util.*

class CreateActivity : AppCompatActivity() {


    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        class DatePickerDialogFragment : DialogFragment() {
            override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
                return DatePickerDialog(this@CreateActivity, null, 2018, 3, 29)
            }
        }


        createButton.setOnClickListener() {
            startActivity(Intent(this, MainActivity::class.java))
            create(
                titleText.text.toString(),
                contentText.text.toString(),
                deadlineText.text.toString()
            )

        }

    }

    fun create(todo: String, content: String, deadline: String) {

        realm.executeTransaction {
            val task = it.createObject(Task::class.java, UUID.randomUUID().toString())
            task.Todo = todo
            task.content = content
            task.deadline = deadline

        }
    }
}
