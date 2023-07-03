package org.android.go.sopt.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import org.android.go.sopt.HomeServicePool
import org.android.go.sopt.SoptServicePool
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.sign.LoginViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗 binding이 null 이다!" }

    private val viewModel: HomeViewModel by viewModels()


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

        viewModel.followList.observe(viewLifecycleOwner){followList ->
            homeadapter.submitList(followList)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}
