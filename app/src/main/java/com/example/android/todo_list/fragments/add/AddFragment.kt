package com.example.android.todo_list.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.todo_list.R
import com.example.android.todo_list.data.models.Priority
import com.example.android.todo_list.data.models.ToDodata
import com.example.android.todo_list.data.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_add.*


class AddFragment : Fragment(){
   private val mToDoViewModel: ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add){
            insertDataToDb()}
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
       val mTitle = title_et.text.toString()
        val mPriority = priorities_spinner.selectedItem.toString()
        val mDescription = description_et.text.toString()
        val validation = verifyDataFromUser(mTitle,mDescription)
        if(validation){
            //insert data to database
            val newData = ToDodata(
                0,
                mTitle,
               parsePriority(mPriority),
                mDescription
            )
            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(),"Successfully added!",Toast.LENGTH_SHORT).show()
            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(),"Please fill out all fields.",Toast.LENGTH_SHORT).show()
        }
    }
   private  fun verifyDataFromUser(title:String ,description:String):Boolean{
       return if(TextUtils.isEmpty(title) || TextUtils.isEmpty(description)){
           false
       }else!(title.isEmpty()||description.isEmpty())
   }
    private fun parsePriority(priority:String):Priority
    {
        return when(priority){
            "High Priority"->{Priority.HIGH}
            "Medium Priority"->{Priority.MEDIUM}
            "Low Priority"->{Priority.LOW}
            else-> Priority.LOW
        }
    }
}