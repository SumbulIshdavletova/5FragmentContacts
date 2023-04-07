package ru.sumbul.a5fragmentcontacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.sumbul.a5fragmentcontacts.R
import ru.sumbul.a5fragmentcontacts.adapter.Adapter
import ru.sumbul.a5fragmentcontacts.adapter.OnInteractionListener
import ru.sumbul.a5fragmentcontacts.data.Datasource
import ru.sumbul.a5fragmentcontacts.databinding.FragmentContactsListBinding
import ru.sumbul.a5fragmentcontacts.model.Contact
import ru.sumbul.a5fragmentcontacts.ui.ContactInfoFragment.Companion.textArg

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

        val adapter = Adapter(object : OnInteractionListener {
            override fun onClick(contact: Contact) {
                findNavController().navigate(
                    R.id.action_contactsListFragment_to_contactInfoFragment,
                    Bundle().apply {
                        textArg = contact.id.toString()
                    })
            }
        })
        val manager = LinearLayoutManager(requireContext())


        val dataset = Datasource().loadContacts()
        dataset.toMutableList()
        val recyclerView = binding.list

        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
       adapter.submitList(dataset)
        return binding.root
    }
}
