package com.devandcoffee.iamalive

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_contacts.*

class ContactsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)


        val users:ArrayList<User> = createUsers()

        contacts_list.layoutManager = LinearLayoutManager(this)
        contacts_list.adapter = RecyclerViewAdapter(users, this)

    }

    private fun createUsers(): ArrayList<User> {
        val list = ArrayList<User>()
        list.add(User("test1@gmail.com", "Juan", "Perez"))
        list.add(User("test2@gmail.com", "Jose", "Lopez"))
        list.add(User("test3@gmail.com", "Laura", "Cisnero"))
        list.add(User("test4@gmail.com", "Dani", "Pavon"))
        return list
    }
}
