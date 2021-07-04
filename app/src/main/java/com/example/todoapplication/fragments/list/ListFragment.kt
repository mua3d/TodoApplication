package com.example.todoapplication.fragments.list

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.R
import com.example.todoapplication.data.Converter
import com.example.todoapplication.data.models.TodoData
import com.example.todoapplication.data.viewModel.TodoViewModel
import com.example.todoapplication.fragments.SharedViewModel
import com.example.todoapplication.retrofit.TodoNetwork
import com.example.todoapplication.data.models.TodoDataItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://60db261d801dcb0017290ed9.mockapi.io/"
class ListFragment : Fragment() {

    //initializing to do view model
    private val mTodoViewModel: TodoViewModel by viewModels()
    private val mShardViewModel: SharedViewModel by viewModels()
    val serviceSetterGetter = MutableLiveData<TodoDataItem>()


    private val adapter: ListAdapter by lazy { ListAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        mTodoViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            adapter.setData(data)
        })


        val floatAB = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatAB.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        //set menu
        setHasOptionsMenu(true)
        return view


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ic_download -> getDataFromServer()
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }


    private fun getDataFromServer() {

        val retrofitBuild = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory
                    .create()
            )
            .baseUrl(BASE_URL)
            .build()
            .create(TodoNetwork::class.java)

        val retrofitData = retrofitBuild.getData()

        retrofitData.enqueue(object : Callback<List<TodoDataItem>?> {
            override fun onResponse(
                call: Call<List<TodoDataItem>?>,
                response: Response<List<TodoDataItem>?>
            ) {
                val responseBody = response.body()!!
                val covert = Converter()



                for (myData in responseBody) {
                    val mTitle = myData.Title
                    val mPriority = myData.Priority
                    val mDescription = myData.Description


                    val convertPriority = covert.fromPriority(mPriority)
                    val newData = TodoData(
                        0,
                        mTitle,
                        mShardViewModel.parsePriority(convertPriority),
                        mDescription
                    )
                    mTodoViewModel.insertData(newData)
                    Toast.makeText(
                        requireContext(),
                        "downloading tasks, please wait",
                        Toast.LENGTH_LONG
                    ).show()


                }
            }

            override fun onFailure(call: Call<List<TodoDataItem>?>, t: Throwable) {
                Log.d("ListFragment", "onFailure: " + t.message)
            }
        })


    }
}