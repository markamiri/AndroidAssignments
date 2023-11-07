package com.example.androidassignments

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class ChatWindow : AppCompatActivity() {
    private var chatListView: ListView? = null
    private var messageEditText: EditText? = null
    private var sendButton: Button? = null
    private val chatMessages = ArrayList<String>()
    private var dbHelper: ChatDatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_window)
        chatListView = findViewById(R.id.chatListView)
        messageEditText = findViewById(R.id.messageEditText)
        sendButton = findViewById(R.id.sendButton)
        dbHelper = ChatDatabaseHelper(this)

        val database = dbHelper?.writableDatabase

        val query = "SELECT * FROM " + ChatDatabaseHelper.TABLE_NAME
        val cursor = database?.rawQuery(query, null)

        while (cursor?.moveToNext() == true) {
            val columnIndex = cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)
            if (columnIndex >= 0) {
                val message = cursor.getString(columnIndex)
                Log.i("ChatDatabaseHelper", "SQL MESSAGE: $message")
                chatMessages.add(message)
            } else {
                Log.w("ChatDatabaseHelper", "Column not found: ${ChatDatabaseHelper.KEY_MESSAGE}")
            }
        }


        Log.i("ChatDatabaseHelper", "Cursor's column count = " + cursor?.columnCount)
        cursor?.columnNames?.forEachIndexed { i, columnName ->
            Log.i("ChatDatabaseHelper", "Column $i name: $columnName")
        }
        cursor?.close() // Close the Cursor when done with it

        // Continue with your code to display the chat messages in the ListView

        val chatAdapter = ChatAdapter(this, chatMessages)
        chatListView?.adapter = chatAdapter

        sendButton?.setOnClickListener {
            val message = messageEditText?.text.toString().trim()
            if (message.isNotEmpty()) {
                chatMessages.add(message)
                chatAdapter.notifyDataSetChanged()
                messageEditText?.text?.clear()

                val dbHelper = ChatDatabaseHelper(this)
                val db = dbHelper.writableDatabase

                val values = ContentValues()
                values.put(ChatDatabaseHelper.KEY_MESSAGE, message)

                db.insert(ChatDatabaseHelper.TABLE_NAME, null, values)
                db.close() // Close the database connection
            }
        }
    }

    // Implement onDestroy to close the database when the activity is destroyed
    override fun onDestroy() {
        dbHelper?.close()
        super.onDestroy()
    }

    // Continue with your ChatAdapter and other code here
}

