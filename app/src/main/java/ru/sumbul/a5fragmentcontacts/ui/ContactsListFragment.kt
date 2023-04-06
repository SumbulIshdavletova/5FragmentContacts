package ru.sumbul.recycleviewaston6.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.sumbul.recycleviewaston6.databinding.FragmentContactsListBinding

class ContactsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentContactsListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }
}
