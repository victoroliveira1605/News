package br.com.mesa.newsapp.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.com.mesa.newsapp.data.model.News
import br.com.mesa.newsapp.databinding.ItemNewsPresentationBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>(), Filterable {
    lateinit var context: Context
    private val newsList: MutableList<News> = mutableListOf()
    private var listFiltered: MutableList<News> = newsList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding =
            ItemNewsPresentationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listFiltered.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            Picasso.get().load(listFiltered[position].imageUrl)
                .resize(1020, 600)
                .centerCrop()
                .into(binding.itemNewsImage)
//            binding.itemNewsDate.text =
//                SimpleDateFormat("dd/MM/yyyy HH:mm")
//                    .format(
//                        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'")
//                            .parse(listFiltered[position].publishedAt)
//                    )

            binding.itemNewsTitleDescriptions.text = listFiltered[position].title
            binding.itemNewsDescriptions.text = listFiltered[position].description
//            binding.itemNewsAuthor.text = listFiltered[position].author
        }
    }

    class ViewHolder(val binding: ItemNewsPresentationBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun addNews(listNews: List<News>) {
        listFiltered.clear()
        listFiltered.addAll(listNews)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString: String = constraint.toString()
                listFiltered = if (charString.length > 6) {
                    val filteredList: MutableList<News> = mutableListOf()
                    for (s: News in newsList) {
                        if (s.title.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(s)
                        }
                    }
                    filteredList
                } else {
                    newsList

                }
                val filterResults = FilterResults()
                filterResults.values = listFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listFiltered = results!!.values as MutableList<News>
                notifyDataSetChanged()
            }

        }
    }
}