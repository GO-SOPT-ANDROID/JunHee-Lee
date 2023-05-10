package org.android.go.sopt.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.android.go.sopt.Pager
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding)


    private val itemlist = listOf(
        Pager(
            R.drawable.pic1
        ),
        Pager(
            R.drawable.pic2
        ),
        Pager(
            R.drawable.pic3
        ),
        Pager(
            R.drawable.pic4
        )


    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val galleryAdapter = GalleryAdapter(requireContext())
        binding.pagerGallery.adapter = galleryAdapter
        galleryAdapter.submitList(itemlist)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}