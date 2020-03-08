package app.miyuseru.timesaber

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter


class TaskAdapter(

    private val context: Context,
    private var taskList: OrderedRealmCollection<Task>?,
    private var listener: AdapterView.OnItemClickListener,
    private val autoUpdate: Boolean

) :


    RealmRecyclerViewAdapter<Task, TaskAdapter.TaskViewHolder>(taskList, autoUpdate) {
//    override fun getItemCount(): Int = taskList?.size ?: 0


//    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
//        val task: Task = taskList?.get(position) ?: return
//
////        holder.container.setOnClickListener {
////            listener.onItemClick(task)
////        }
//
//        holder.TitleTextView.text = task.Todo
//    }


    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentTextView: LinearLayout = view.content
        val TitleTextView: TextView = view.Todo
    }

}

