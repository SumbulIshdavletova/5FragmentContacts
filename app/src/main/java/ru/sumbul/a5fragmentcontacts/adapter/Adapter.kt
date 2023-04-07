package ru.sumbul.a5fragmentcontacts.adapter

import android.R
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import ru.sumbul.a5fragmentcontacts.databinding.ContactCardBinding
import ru.sumbul.a5fragmentcontacts.model.Contact
import ru.sumbul.a5fragmentcontacts.util.loadCircleCrop


interface OnInteractionListener {
    fun onClick(contact: Contact) {}
}

//
//class MyAdapter(
//    private val contactsList: List<Contact>
//) : RecyclerView.Adapter<ViewHolder>() {
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = ContactCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//
//        viewHolder.textId.text = contactsList[position].toString()
//        viewHolder.textName.text = contactsList[position].toString()
//        val question: String = "https://picsum.photos/seed/picsum/200"
//
//        Glide.with(viewHolder.imageView)
//            .load(question)
//            //   .placeholder(R.drawable.ic_baseline_rotate_right_24)
//            //   .error(R.drawable.ic_baseline_error_24)
//            .into(viewHolder.imageView)
//        //  viewHolder.imageView.setImageResource(flags[position])
//    }
//
//    override fun getItemCount() = contactsList.size
//
//}

//class ViewHolder(binding: ContactCardBinding) : RecyclerView.ViewHolder(binding.root) {
//    val textName: TextView
//    val textId: TextView
//    val imageView: ImageView
//
//    init {
//        textName = binding.name
//        textId = binding.id
//        imageView = binding.image
//    }
//}

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
            image.loadCircleCrop("https://picsum.photos/seed/picsum/100")
          //  val question = "https://picsum.photos/seed/picsum/100"

//            Glide.with(binding.image)
//                .load(question)
//                .circleCrop()
//             //   .placeholder(R.drawable.ic_baseline_rotate_right_24)
//             //   .error(R.drawable.ic_baseline_error_24)
//                .into(binding.image)

            itemView.setOnClickListener{
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
