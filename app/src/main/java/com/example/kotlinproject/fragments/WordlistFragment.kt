/*
Lauri Riikonen
1909911
 */
package com.example.kotlinproject.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinproject.R
import com.example.kotlinproject.adapter.WordlistAdapter
import com.example.kotlinproject.databinding.FragmentWordlistBinding
import com.example.kotlinproject.databinding.ListViewItemBinding
import com.example.kotlinproject.viewmodels.WordlistViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [WordlistFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WordlistFragment : Fragment() {


    /**
     * Lazily initialize [WordlistViewModel].
     */
    private val viewModel: WordlistViewModel by lazy {
        ViewModelProvider(this).get(WordlistViewModel::class.java)
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the WordlistFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentWordlistBinding.inflate(inflater)

        (activity as AppCompatActivity).supportActionBar?.title = "Wordlist"


        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the WordListViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the jsonList RecyclerView
        binding.jsonList.adapter = WordlistAdapter()

        setHasOptionsMenu(true)
        return binding.root
    }

    //Inflate the options menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.info_menu, menu)
    }


    //When statement for menu choices
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.info -> {          //Toast.makeText(context, "Hello testing", Toast.LENGTH_SHORT).show()
                val builder = AlertDialog.Builder(requireContext())
                builder.setPositiveButton("Okay") { _, _ -> }
                builder.setTitle("Info")
                builder.setMessage("List of words used in the game")
                builder.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }



}