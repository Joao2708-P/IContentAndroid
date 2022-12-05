package br.unicamp.apicontent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.apicontent.Models.Content;


public class MyAdapterConteudo extends RecyclerView.Adapter<MyAdapterConteudo.MyHolder>
{
   List<Content> list = new ArrayList<>();
   ItemClickListener intemClickLIstener;

    public MyAdapterConteudo(List<Content> list)
    {
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapterConteudo.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_feed, parent, false);
       return new MyAdapterConteudo.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterConteudo.MyHolder holder, int position)
    {
       final Content contentData = list.get(position);
       holder.tvTitle.setText(contentData.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder
    {
        TextView tvTitle;

        public MyHolder(@NonNull View itemView)
        {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvCard);
        }
    }

    public  void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.intemClickLIstener = itemClickListener;
    }
}