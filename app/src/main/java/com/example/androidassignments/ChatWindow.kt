package com.example.androidassignments
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter // Import ArrayAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView


class ChatWindow : AppCompatActivity() {

    private lateinit var chatListView: ListView
    private lateinit var messageEditText: EditText
    private lateinit var sendButton: Button
    private val chatMessages = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_window)

        // Initialize class variables with findViewById
        chatListView = findViewById(R.id.chatListView)
        messageEditText = findViewById(R.id.messageEditText)
        sendButton = findViewById(R.id.sendButton)

        // Create and set an adapter for the chatListView using chatMessages ArrayList
        val chatAdapter = ChatAdapter(this, chatMessages)

        // Correctly cast the adapter to ArrayAdapter before setting it
        chatListView.adapter = chatAdapter

        // Set an onClickListener for the sendButton
        sendButton.setOnClickListener {
            // Get the text from the EditText field
            val message = messageEditText.text.toString().trim()

            if (message.isNotEmpty()) {
                // Add the message to the chatMessages ArrayList
                chatMessages.add(message)

                // Clear the EditText field after sending
                messageEditText.text.clear()

                // Notify the adapter that the data set has changed
                chatAdapter.notifyDataSetChanged()
            }
        }
    }

    inner class ChatAdapter(context: Context, private val messages: ArrayList<String>) :
        ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, messages) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater = LayoutInflater.from(context)
            val result: View

            if (position % 2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null)
            } else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null)
            }

            val message = result.findViewById<TextView>(R.id.message_text)

            // Ensure that the context and TextView type are correct
            if (position < messages.size) {
                message.text = getItem(position) // Assuming messages is your ArrayList<String>
            } else {
                message.text = "" // Set a default text or handle this case as needed
            }

            return result
        }

        override fun getCount(): Int {
            // Return the number of rows (number of strings in the ArrayList)
            return messages.size
        }

        override fun getItem(position: Int): String? {
            // Return the item to show in the list at the specified position
            return messages.getOrNull(position)
        }

        // Rest of your ChatWindow.kt code
    }
}
