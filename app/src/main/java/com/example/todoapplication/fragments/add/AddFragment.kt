package com.example.todoapplication.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapplication.R
import com.example.todoapplication.data.models.Priority
import com.example.todoapplication.data.models.TodoData
import com.example.todoapplication.data.viewModel.TodoViewModel
import com.example.todoapplication.fragments.SharedViewModel


class AddFragment : Fragment() {

    private val mTodoViewModel : TodoViewModel by viewModels()
    private val mShardViewModel: SharedViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)



        //set menu
        setHasOptionsMenu(true)
        
        //apply
        view.findViewById<Spinner>(R.id.priorities_spinner).onItemSelectedListener = mShardViewModel.listener

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add)
            insertDataToDB()
        findNavController().navigate(R.id.action_addFragment_to_listFragment)

        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDB() {

        val mTitle = view?.findViewById<EditText>(R.id.title_et)?.text.toString()
        val mPriority = view?.findViewById<Spinner>(R.id.priorities_spinner)?.selectedItem.toString()
        val mDescription = view?.findViewById<EditText>(R.id.description_et)?.text.toString()

        val validation = mShardViewModel.verifyDataFromUser(mTitle,mDescription)
        if(validation){
            //insert data to database
            val newData = TodoData(0 ,mTitle,mShardViewModel.parsePriority(mPriority),mDescription)
            mTodoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Task Added!", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show()

        }
    }


}