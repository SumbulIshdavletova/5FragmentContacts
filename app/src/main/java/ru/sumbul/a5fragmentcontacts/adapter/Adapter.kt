package ru.sumbul.a5fragmentcontacts.adapter


import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sumbul.a5fragmentcontacts.databinding.ContactCardBinding
import ru.sumbul.a5fragmentcontacts.model.Contact
import ru.sumbul.a5fragmentcontacts.util.loadCircleCrop


class Adapter(
    private val onClickListener: OnClickListener,
    private val onLongClick: OnLongClick,
) : RecyclerView.Adapter<Adapter.ContactViewHolder>() {

    class OnClickListener(val clickListener: (contact: Contact) -> Unit) {
        fun onClick(contact: Contact) = clickListener(contact)

    }

    class OnLongClick(val clickListener: (contact: Contact) -> Unit) {
        fun onRemove(contact: Contact) = clickListener(contact)
    }


    private var oldContactsList = emptyList<Contact>()

    class ContactViewHolder(
        val binding: ContactCardBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ContactCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {

        holder.binding.id.text = oldContactsList[position].id.toString()
        holder.binding.name.text = oldContactsList[position].name
        val url = "https://picsum.photos/200?random="
        holder.binding.imageView.loadCircleCrop(url + oldContactsList[position].id)
        holder.itemView.setOnClickListener { onClickListener.onClick(oldContactsList[position]) }
        holder.itemView.setOnLongClickListener {
            onLongClick.onRemove(oldContactsList[position])
            val g = oldContactsList.size
            (oldContactsList as MutableList<Contact>).removeAt(position)
            val newList = oldContactsList
            newList.size
            setData(newList)
            notifyDataSetChanged()
            return@setOnLongClickListener true

        }
    }

    override fun getItemCount(): Int {
        return oldContactsList.size
    }

    fun setData(newContactList: List<Contact>) {
        val diffUtil = ContactDiffCallback(oldContactsList, newContactList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldContactsList = newContactList
        diffResults.dispatchUpdatesTo(this)
    }


}
//
//class ContactViewHolder(
//    private val binding: ContactCardBinding,
//    private val onInteractionListener: OnInteractionListener
//) : RecyclerView.ViewHolder(binding.root) {
//
//    fun bind(contact: Contact) {
//        binding.apply {
//            name.text = contact.name
//            id.text = contact.id.toString()
//            val url = "https://picsum.photos/200?random="
//
//            imageView.loadCircleCrop(url + contact.id)
//
//            itemView.setOnClickListener {
//                onInteractionListener.onClick(contact)
//            }
//
//            itemView.setOnLongClickListener {
//                onInteractionListener.onRemove(contact)
//                bindingAdapter?.notifyItemRemoved(layoutPosition)
//                //   bindingAdapter?.notifyDataSetChanged()
//                return@setOnLongClickListener true
//            }
//
//        }
//    }
//}

class ContactDiffCallback(
    private val oldList: List<Contact>,
    private val newList: List<Contact>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                false
            }
            oldList[oldItemPosition].number != newList[newItemPosition].number -> {
                false
            }
            else -> true
        }
    }
}

