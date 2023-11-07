package com.example.androidassignments
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.view.LayoutInflater

class ChatAdapter(context: Context, private val messages: List<String>) : ArrayAdapter<String>(context, 0, messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val result: View

        if (position % 2 == 0) {
            result = inflater.inflate(R.layout.chat_row_incoming, null)
        } else {
            result = inflater.inflate(R.layout.chat_row_outgoing, null)
        }

        val message = result.findViewById<TextView>(R.id.message_text)

        if (position < messages.size) {
            message.text = getItem(position)
        } else {
            message.text = ""
        }

        return result
    }
}
