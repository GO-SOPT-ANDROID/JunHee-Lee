package org.android.go.sopt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import org.android.go.sopt.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗 binding이 null 이다!" }


    private val itemlist = listOf(
        Music(
            "music1",
            "artist1"
        ),
        Music(
            "music2",
            "artist2"
        ),
        Music(
            "music3",
            "artist3"
        ),
        Music(
            "music4",
            "artist4"
        ),
        Music(
            "music5",
            "artist5"
        ),
        Music(
            "music6",
            "artist6"
        ),
        Music(
            "music7",
            "artist7"
        ),
        Music(
            "music8",
            "artist8"
        )


    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val musicadapter = MyAdapter(requireContext())
        val headerAdapter = HeaderAdapter(requireContext())
        val adapter = ConcatAdapter(headerAdapter, musicadapter)
        binding.rvHome.adapter = adapter
        binding.rvHome.layoutManager = LinearLayoutManager(context)
        musicadapter.submitList(itemlist)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}