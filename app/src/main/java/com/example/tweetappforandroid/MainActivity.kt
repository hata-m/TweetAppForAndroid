package com.example.tweetappforandroid

import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.InputStream


class MainActivity : AppCompatActivity() {
    val startResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val intent= result.data

            val title:String = intent?.getStringExtra("title")!!
            val context:String = intent?.getStringExtra("context")!!

            Log.i ("MainActivity" , "from sub ${title}")
            Log.i ("MainActivity" , "from sub ${context}")
            val tweet = Tweet("",title, context)
            tweet.title = title
            tweet.context = context
            dataSet.add(tweet)
            adapter.notifyDataSetChanged()
            Log.i ("MainActivity" , "from sub ${dataSet.size}")
        }
    }

    val dataSet = mutableListOf<Tweet>()
    val adapter = CustomerAdapter(dataSet)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvList = findViewById<RecyclerView>(R.id.rvList)
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(applicationContext)

        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager(this).getOrientation())
//        getDrawable(R.drawable.divider)?.let { dividerItemDecoration.setDrawable(it) }
        rvList.addItemDecoration(dividerItemDecoration)
        findViewById<FloatingActionButton>(R.id.addButton).setOnClickListener{

            val intent = Intent(applicationContext, AddTweetActivity::class.java)
//            startActivity(intent)
            startResult.launch(intent)
//            dataSet.add("Add")
            adapter.notifyDataSetChanged()
        }
    }

    class Tweet constructor(_fullName: String, _title: String, _context:String){
        var name: String = ""
        var title: String = ""
        var context: String = ""
        init {
            name = _fullName
            title = _title
            context = _context
        }
    }
    class CustomerAdapter(private  val dataSet: List<Tweet>): RecyclerView.Adapter<CustomerAdapter.ViewHolder>(){
        class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            val namelabel: TextView
            val contentlabel: TextView
            val titlelabel: TextView
            val imageView: ImageView
            init {
                namelabel = view.findViewById(R.id.namelabel)
                contentlabel = view.findViewById(R.id.contextlabel)
                titlelabel = view.findViewById(R.id.titlelabel)
                imageView = view.findViewById(R.id.imageView)
            }
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.listcell, viewGroup, false)

            return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.namelabel.text = "anonymous"
            viewHolder.titlelabel.text = dataSet[position].title
            viewHolder.contentlabel.text = dataSet[position].context
            viewHolder.imageView.setImageResource(android.R.drawable.ic_menu_gallery)
        }



        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount():Int {
            return dataSet.size
        }
    }
}