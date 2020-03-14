package app.miyuseru.timesaber

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.calendar_cell.view.*
import java.text.SimpleDateFormat
import java.util.*

class CalendarAdapter(var context: Context) : BaseAdapter() {

    private var dateArray: List<Date> = ArrayList()
    var mDateManager: DateManager = DateManager()

    class ViewHolder(view: View) {
        var dateText: TextView = view.dateText

    }

    init {
        dateArray = mDateManager.getDays()!!
    }

    override fun getCount(): Int {
        return dateArray.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        val (viewHolder, convertView) = if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            val convertView = inflater.inflate(R.layout.calendar_cell, null)
            val viewHolder = ViewHolder(convertView)

            convertView.tag = viewHolder
            Pair(viewHolder, convertView)
        } else {
            Pair(convertView.tag, convertView)
        }

        val dp = context.resources.displayMetrics.density
        val params = AbsListView.LayoutParams(
            parent.width / 7 - dp.toInt(),
            (parent.height - dp.toInt() * mDateManager.getWeeks()) / mDateManager.getWeeks()
        )
        convertView.layoutParams = params

        val dateFormat = SimpleDateFormat("d", Locale.JAPAN)
        (viewHolder as ViewHolder).dateText.text = dateFormat.format(dateArray[position])

        if (mDateManager.isCurrentMonth(dateArray[position])) {
            convertView.setBackgroundColor(Color.WHITE)
        } else {
            convertView.setBackgroundColor(Color.LTGRAY)
        }

        val colorId: Int = when (mDateManager.getDayOfWeek(dateArray[position])) {
            1 -> Color.RED
            7 -> Color.BLUE
            else -> Color.BLACK
        }
        (viewHolder as ViewHolder).dateText.setTextColor(colorId)

        // ここでGridView毎のレイアウトを作成しているので，ここで画像の挿入を行う．
        // TODO : タスクがあるのかどうか判定して、あるのであれば画像の挿入を行う．
        // 範囲内にあれば、表示できるように

        val imageView = ImageView(context)
        // 画像のサイズ周りの記述
        // https://akira-watson.com/android/button-hardcoding.html

        imageView.layoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

        Log.d("position", position.toString())

        imageView.setImageResource(R.drawable.bar1)

        //imageView.setImageResource(R.drawable.)
        (convertView as LinearLayout).addView(imageView)

        return convertView
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    fun getDayOfWeek(position: Int): Any? {
        return dateArray[position]
    }

    fun getTitle(): String? {
        val format = SimpleDateFormat("yyyy.MM", Locale.US)
        return format.format(mDateManager.mCalendar.time)
    }

    fun nextMonth() {
        mDateManager.nextMonth()
        dateArray = mDateManager.getDays()!!
        notifyDataSetChanged()

    }

    fun prevMonth() {
        mDateManager.prevMonth()
        dateArray = mDateManager.getDays()!!
        notifyDataSetChanged()
    }


}