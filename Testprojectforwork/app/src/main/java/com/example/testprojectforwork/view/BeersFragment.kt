package com.example.testprojectforwork.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testprojectforwork.BeerAdapter
import com.example.testprojectforwork.BeerAdapterListener
import com.example.testprojectforwork.R
import com.example.testprojectforwork.navigation.ScreenNavigator
import com.example.testprojectforwork.api.BeerResponse
import com.example.testprojectforwork.viewmodel.BeersViewModel
import kotlinx.android.synthetic.main.fragment_beers.*

class BeersFragment : Fragment(),
    BeerAdapterListener {

    private lateinit var viewModel: BeersViewModel

    private val userAdapter by lazy {
        BeerAdapter(
            this
        )
    }
    private fun getBeers() {
        viewModel = ViewModelProviders.of(this).get(BeersViewModel::class.java)
        viewModel.beers.observe(viewLifecycleOwner, Observer {
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_beers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBeers()
        toolbar.setNavigationIcon(R.drawable.ic_exit)
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_contentFragment_to_signInFragment)
        }
        prepareViewModel()
        rvList.apply {
            layoutManager = activity?.let { LinearLayoutManager(it, RecyclerView.VERTICAL, false) }
            adapter = this@BeersFragment.userAdapter
        }
    }

    private fun prepareViewModel() {
        viewModel = ViewModelProviders.of(this)[BeersViewModel::class.java]

        viewModel.beers.observe(viewLifecycleOwner, Observer {
            it?.let {
                rvList.post {
                    userAdapter.refreshData(it)
                    userAdapter.notifyDataSetChanged()
                }
            }
        })
    }

    override fun onBeerSelected(beerResponse: BeerResponse?, view: View?) {
        beerResponse?.id?.let { ScreenNavigator()
            .contentOpenDetail(findNavController(), it) }
    }
}