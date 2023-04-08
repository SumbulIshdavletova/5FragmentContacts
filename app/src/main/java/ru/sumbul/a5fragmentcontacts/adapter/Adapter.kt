package ru.sumbul.a5fragmentcontacts.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sumbul.a5fragmentcontacts.databinding.ContactCardBinding
import ru.sumbul.a5fragmentcontacts.model.Contact
import ru.sumbul.a5fragmentcontacts.util.loadCircleCrop


interface OnInteractionListener {
    fun onClick(contact: Contact) {}
}


class Adapter(
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<Contact, ContactViewHolder>(ContactDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ContactCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact)
    }
}

class ContactViewHolder(
    private val binding: ContactCardBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(contact: Contact) {
        binding.apply {
            name.text = contact.name
            id.text = contact.id.toString()
            val url = "https://picsum.photos/200?random="

            imageView.loadCircleCrop(url+contact.id)

            itemView.setOnClickListener {
                onInteractionListener.onClick(contact)
            }

        }
    }
}

class ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }
}
