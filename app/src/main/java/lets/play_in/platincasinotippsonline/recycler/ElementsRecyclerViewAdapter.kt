package lets.play_in.platincasinotippsonline.recycler

import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView

class ElementsRecyclerViewAdapter(private val elements: List<Int>, private val callback: (Int) -> Unit): RecyclerView.Adapter<ElementsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementsViewHolder {
        val element = ImageView(parent.context)
        val destiny = parent.resources.displayMetrics.density
        element.layoutParams = ConstraintLayout.LayoutParams((destiny * 166.67).toInt(), (destiny * 260.67).toInt())
        element.setPadding((8 * destiny).toInt())
        return ElementsViewHolder(element)
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    override fun onBindViewHolder(holder: ElementsViewHolder, position: Int) {
        (holder.itemView as ImageView).setImageResource(elements[position])
        holder.itemView.setOnClickListener {
            callback(position)
        }
    }

}
