package elnahas.com.pomactask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import elnahas.com.pomactask.R
import elnahas.com.pomactask.data.model.ResultModel
import kotlinx.android.synthetic.main.item_most_popular.view.*

class MostPopularAdapter : RecyclerView.Adapter<MostPopularAdapter.ViewHolder>() {

    val diffCallback = object : DiffUtil.ItemCallback<ResultModel>() {

        override fun areItemsTheSame(oldItem: ResultModel, newItem: ResultModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultModel, newItem: ResultModel): Boolean {
            return oldItem == newItem

        }

    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_most_popular, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = differ.currentList[position]

        holder.itemView.apply {

            txt_title.text = item.title
            txt_date.text = StringBuilder()
                .append(item.publishedDate)

            if (item.media.isNotEmpty())
            item.media[0].let {

                if (it.mediaMetadata.isNotEmpty())
                    Glide.with(context).load(item.media[0].mediaMetadata[0].url)
                        .centerCrop().into(img_news)

                txt_copyright.text = it.copyright

            }


        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}