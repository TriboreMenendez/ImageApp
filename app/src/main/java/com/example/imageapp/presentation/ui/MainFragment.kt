package com.example.imageapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.R
import com.example.imageapp.databinding.FragmentMainBinding
import com.example.imageapp.domain.model.ImageDomainModel
import com.example.imageapp.presentation.adapter.AdapterRecycler
import com.example.imageapp.presentation.viewmodel.ImageViewModel
import com.example.imageapp.utils.Const
import com.example.imageapp.utils.StatusLoading
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: ImageViewModel by viewModels()
    private val adapter = AdapterRecycler { item -> onClickRecyclerItem(item) }
    private lateinit var binding: FragmentMainBinding
    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        recycler = binding.recyclerImage
        recycler.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.statusLoad.observe(viewLifecycleOwner) {
            when (it) {
                StatusLoading.DONE -> eventSuccessLoadingData()
                StatusLoading.ERROR -> eventErrorLoadingData()
                StatusLoading.LOADING -> {}
                else -> throw Exception("Invalid status loading.")
            }
        }

        binding.swipeRefreshData.setOnRefreshListener { viewModel.getDataRemoteSource() }
    }

    private fun onClickRecyclerItem(item: ImageDomainModel) {
        val bundle = Bundle()
        bundle.putString(Const.IMAGE_URL, item.imageUrl)
        findNavController().navigate(R.id.action_mainFragment_to_fullscreenFragment, bundle)
    }

    private fun eventSuccessLoadingData() {
        adapter.submitList(viewModel.imageList.value)
        binding.swipeRefreshData.isRefreshing = false
    }

    private fun eventErrorLoadingData() {
        Toast.makeText(context, Const.MESSAGE_ERROR_CONNECTION, Toast.LENGTH_LONG).show()
        binding.swipeRefreshData.isRefreshing = false
    }

}