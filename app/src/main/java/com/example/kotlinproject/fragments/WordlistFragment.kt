package com.example.kotlinproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
     * Lazily initialize our [WordlistViewModel].
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


        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the jsonList RecyclerView
        binding.jsonList.adapter = WordlistAdapter()

        setHasOptionsMenu(true)
        return binding.root
    }




}