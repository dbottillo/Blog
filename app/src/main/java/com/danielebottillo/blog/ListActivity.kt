package com.danielebottillo.blog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ListActivity)
            adapter = ListAdapter()
        }
    }
}

class ListAdapter : RecyclerView.Adapter<RowViewHolder>() {
    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        holder.title.text = "Row $position"
        holder.icon.setImageResource(if (position % 2 == 0) R.drawable.ic_arrow_drop_down_circle_black_24dp else R.drawable.ic_autorenew_black_24dp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RowViewHolder(inflater.inflate(R.layout.list_row, parent, false))
    }

    override fun getItemCount(): Int = 100

}

class RowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView by lazy { view.findViewById(R.id.list_title) as TextView }
    val icon: ImageView by lazy { view.findViewById(R.id.list_icon) as ImageView }
}