package com.devandcoffee.iamalive

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import kotlinx.android.synthetic.main.activity_contacts.*
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class ContactsActivity : AppCompatActivity() {

    val users: ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        contacts_list.layoutManager = LinearLayoutManager(this)
        contacts_list.adapter = RecyclerViewAdapter(users, this)


        val apolloClient = ApolloClient.builder()
                .serverUrl("https://graphql-server-iamalive.herokuapp.com/graphql")
                .okHttpClient(OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .build())
                .build()


        apolloClient.query(GetUsersQuery
                .builder()
                .build())
                .enqueue(object : ApolloCall.Callback<GetUsersQuery.Data>() {

                    override fun onFailure(e: ApolloException) {

                    }

                    override fun onResponse(response: Response<GetUsersQuery.Data>) {
                        runOnUiThread {
                            for (usersGraphql: GetUsersQuery.User in response.data()!!.users()) {
                                Log.i("### test ###", ":${usersGraphql.email}")
                                users.add(User(usersGraphql.email, usersGraphql.firstName, usersGraphql.lastName))
                                contacts_list.adapter.notifyDataSetChanged()
                            }
                        }
                    }

                })
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
