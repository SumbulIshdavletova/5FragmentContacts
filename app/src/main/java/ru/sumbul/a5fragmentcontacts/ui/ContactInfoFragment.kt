package ru.sumbul.a5fragmentcontacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.sumbul.a5fragmentcontacts.data.Datasource
import ru.sumbul.a5fragmentcontacts.databinding.FragmentContactInfoBinding
import ru.sumbul.a5fragmentcontacts.model.Contact
import ru.sumbul.a5fragmentcontacts.util.StringArg

class ContactInfoFragment : Fragment() {

    companion object {
        var Bundle.textArg: String? by StringArg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentContactInfoBinding.inflate(
            inflater,
            container,
            false
        )

        val dataset = Datasource().loadContacts()
        dataset.toMutableList()

        val id = arguments?.textArg?.toInt()

        if (id != null) {
            val num = dataset[id].number
            val namee = dataset[id].name
            binding.name.text = namee
            binding.number.text = num.toString()
        }


        return binding.root
    }

}
