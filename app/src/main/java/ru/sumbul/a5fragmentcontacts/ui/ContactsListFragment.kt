package ru.sumbul.a5fragmentcontacts.ui

import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.sumbul.a5fragmentcontacts.R
import ru.sumbul.a5fragmentcontacts.adapter.Adapter
import ru.sumbul.a5fragmentcontacts.adapter.OnInteractionListener
import ru.sumbul.a5fragmentcontacts.data.Datasource
import ru.sumbul.a5fragmentcontacts.databinding.FragmentContactsListBinding
import ru.sumbul.a5fragmentcontacts.model.Contact
import ru.sumbul.a5fragmentcontacts.ui.ContactInfoFragment.Companion.textArg

class ContactsListFragment : Fragment() {
    val dataset: MutableList<Contact> = Datasource().loadContacts() as MutableList<Contact>

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

            override fun onRemove(contact: Contact) {
                dataset.removeAt(contact.id)

            }
        })
        val manager = LinearLayoutManager(requireContext())

        dataset.toMutableList()
        val recyclerView = binding.list

        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
        adapter.submitList(dataset)

        val searchInput = binding.textInputEdit
        searchInput.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val question = searchInput.text.toString()

                adapter.submitList(filterContacts(question))
            }
            return@setOnEditorActionListener false
        }

        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        recyclerView.addItemDecoration(dividerItemDecoration)

        return binding.root
    }

    private fun filterContacts(question: String): List<Contact> {
        return dataset.filter { it.name.contains(question, ignoreCase = true) }
    }
}

