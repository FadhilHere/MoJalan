package com.example.mojalan

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mojalan.R

class ChatActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ChatAdapter
    private lateinit var editTextMessage: EditText
    private lateinit var buttonSend: ImageButton
    private val messages = mutableListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        recyclerView = findViewById(R.id.recycler_view)
        editTextMessage = findViewById(R.id.edit_text_message)
        buttonSend = findViewById(R.id.button_send)

        adapter = ChatAdapter(messages)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Contoh data pesan
        messages.add(Message("Halo kak, saya Azzura", "08:00 AM", true))
        messages.add(Message("Nanti kita akan bertemu dimana kaka?", "08:01 AM", true))
        messages.add(Message("Halo kak, salam kenal panggil aku Galih ya kak :D", "08:02 AM", false))
        messages.add(Message("Nanti kakak bisa langsung ke pasar sekitar lokasi ya kak.", "08:03 AM", false))

        adapter.notifyDataSetChanged()

        buttonSend.setOnClickListener {
            val text = editTextMessage.text.toString().trim()
            if (text.isNotEmpty()) {
                sendMessage(text)
            }
        }
    }

    private fun sendMessage(text: String) {
        val timestamp = "08:00 AM" // Ubah dengan waktu saat ini
        val message = Message(text, timestamp, true)
        messages.add(message)
        adapter.notifyItemInserted(messages.size - 1)
        editTextMessage.text.clear()
        recyclerView.scrollToPosition(messages.size - 1)
    }
}
