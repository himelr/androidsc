package com.example.presidentkotlin

import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import com.example.presidentkotlin.R.id.item2
import com.example.presidentkotlin.R.id.list_item
import kotlinx.android.synthetic.main.president_item.view.*
import org.w3c.dom.Text
import java.util.HashSet

/**
 * Created by HimelR on 08-Feb-18.
 */
class CustomAdapter constructor(private val context: Context, private val item_layout: Int, private val presidents: List<President>): ListAdapter  {
    private val observerChangeSet: MutableSet<DataSetObserver> = hashSetOf()

    override fun areAllItemsEnabled(): Boolean {
        return true
    }

    override fun isEnabled(position: Int): Boolean {
        return true
    }

    override fun registerDataSetObserver(observer: DataSetObserver) {
        observerChangeSet.add(observer)

    }

    override fun unregisterDataSetObserver(observer: DataSetObserver) {
        observerChangeSet.remove(observer)

    }

    override fun getCount(): Int {
        return presidents.size
    }

    override fun getItem(position: Int): Any {
        return presidents[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun isEmpty(): Boolean {
        return presidents.isEmpty()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(item_layout, null)
        }

        val p = presidents[position]
        convertView!!.findViewById<TextView>(item2).text = p.name



        return convertView!!
    }
}