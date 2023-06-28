package org.android.go.sopt.gallery

import ContentUriRequestBody
import GalleryViewModel
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import org.android.go.sopt.Pager
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding)
    private val viewmodel by viewModels<GalleryViewModel>()

    private val launcher =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { imageUriList ->
            viewmodel.setRequestBody(ContentUriRequestBody(requireContext(), imageUriList!!))

            with(binding) {
                ivGalleryFirst.load(imageUriList)
            }
        }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {

        } else {

        }
    }


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
//        val galleryAdapter = GalleryAdapter(requireContext())
//        binding.pagerGallery.adapter = galleryAdapter
//        galleryAdapter.submitList(itemlist)

        requestPermissionLauncher.launch("android.permission.ACCESS_COARSE_LOCATION")

        binding.btnGalleryPickImage.setOnClickListener {
            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
        viewmodel.image.observe(viewLifecycleOwner) {
            viewmodel.uploadProfileImage()
        }


    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}