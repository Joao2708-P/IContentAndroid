package br.unicamp.apicontent.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import br.unicamp.apicontent.Forms.ActvityConteudo;
import br.unicamp.apicontent.Forms.Feed;
import br.unicamp.apicontent.Models.Content;
import br.unicamp.apicontent.R;

public class GridViwenAdapter extends BaseAdapter
{

    private List<Content> listaContent;
    Context context;
    ImageView tvImage;
    TextView tvTitulo;
    TextView tvCorpo;

    public GridViwenAdapter(Context contex, List<Content> parametrosLista)
    {
        this.context = contex;
        this.listaContent = parametrosLista;
    }

    @Override
    public int getCount()
    {
        return listaContent.size();
    }

    @Override
    public Object getItem(int posicao)
    {
        return listaContent.get(posicao);
    }

    @Override
    public long getItemId(int posicao)
    {
        return posicao;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.cards_feed, viewGroup, false);
        }
        tvTitulo = view.findViewById(R.id.tvCard);
        tvCorpo = view.findViewById(R.id.tvCorpo);
        tvImage = view.findViewById(R.id.tvImageContent);
        TextView tvContentTittle = view.findViewById(R.id.tvTitleContent);

        final Content content = listaContent.get(posicao);

        String simImage = content.getImage();
        tvTitulo.setText(content.getTitle());
        //tvImage.setImageBitmap(StringToBitmap(simImage));

        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                  Intent intent = new Intent(context, ActvityConteudo.class);
                  intent.putExtra("serializebleContent", (Serializable) content);
                  context.startActivity(intent);
            }
        });
        return view;
    }

    private Bitmap StringToBitmap(String encodeString)
    {
        try
        {
            byte[] encodeByte = java.util.Base64.getDecoder().decode(encodeString);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch (Exception err)
        {
            err.getMessage();
            return null;
        }
    }
}