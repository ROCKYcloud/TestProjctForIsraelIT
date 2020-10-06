package com.example.testprojectforwork.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.testprojectforwork.R
import com.example.testprojectforwork.navigation.ScreenNavigator
import com.example.testprojectforwork.viewmodel.DeleteViewModel
import kotlinx.android.synthetic.main.fragment_beer.*

class BeerFragment : Fragment() {

    private lateinit var viewModel: DeleteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_beer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_contentFragment)
        }
        viewModel = ViewModelProviders.of(this).get(DeleteViewModel::class.java)
        getBeer()
    }

    private fun getBeer() {
        var id = ScreenNavigator().getBeerParentsId(this)
        id?.let { viewModel.getBeer(it) }
        viewModel.beer.observe(viewLifecycleOwner, Observer {
            it.map {
                tvName.text = it.name
                tvDescription.text = it.description
                tvVolume.text = it.volume?.value.toString()
                tvTagLine.text = it.tagline
                tvFirstBrewed.text = it.firstBrewed
                tvABV.text = it.abv.toString()
                Glide.with(this).load(it.imageURL).into(imageView)
            }
        })
    }
}