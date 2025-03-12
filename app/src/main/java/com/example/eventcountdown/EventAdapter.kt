import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventcountdown.EventModel
import com.example.eventcountdown.R

class EventAdapter(private val eventList: List<EventModel>, private val isImportant: Boolean) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event_horizontal, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]
        holder.title.text = event.eventTitle
        holder.date.text = event.eventDate
        holder.time.text = event.eventTime

        //  Apply the half-screen width rule ONLY for Important Events
//        if (isImportant) {
//            val screenWidth = Resources.getSystem().displayMetrics.widthPixels
//            holder.itemView.layoutParams.width = (screenWidth * 0.5).toInt()
//        }
    }

    override fun getItemCount(): Int = eventList.size

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.eventTitle)
        val date: TextView = itemView.findViewById(R.id.eventDate)
        val time: TextView = itemView.findViewById(R.id.eventTime)
    }
}
