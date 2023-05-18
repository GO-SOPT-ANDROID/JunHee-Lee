package org.android.go.sopt.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import org.android.go.sopt.HomeServicePool
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.model.ResponseHome
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗 binding이 null 이다!" }
    private val homeService = HomeServicePool.homeService


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeadapter = MyAdapter(requireContext())
        val headerAdapter = HeaderAdapter(requireContext())
        val adapter = ConcatAdapter(headerAdapter, homeadapter)
        binding.rvHome.adapter = adapter
        binding.rvHome.layoutManager = LinearLayoutManager(context)



        homeService.listuser().enqueue(object : retrofit2.Callback<ResponseHome> {
            override fun onResponse(
                call: Call<ResponseHome>,
                response: Response<ResponseHome>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        homeadapter.submitList(it)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseHome>, t: Throwable) {

            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}
