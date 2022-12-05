package br.unicamp.apicontent.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.unicamp.apicontent.Models.Atividades;
import br.unicamp.apicontent.Models.Content;
import br.unicamp.apicontent.R;

public class GridViwenAdpterAtividade extends BaseAdapter
{
    private List<Atividades> listaAtividade;
    TextView tvCorpo;
    TextView idResposta;
    Button btnVerResposta;
    Context context;

    public GridViwenAdpterAtividade(Context context, List<Atividades> parametroLista)
    {
        this.context = context;
        this.listaAtividade = parametroLista;
    }

    @Override
    public int getCount()
    {
        return listaAtividade.size();
    }

    @Override
    public Object getItem(int posicao)
    {
        return listaAtividade.get(posicao);
    }

    @Override
    public long getItemId(int posicao)
    {
        return posicao;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup)
    {
        if(view == null )
        {
            view = LayoutInflater.from(context).inflate(R.layout.card_atividade, viewGroup, false);
        }

            tvCorpo = view.findViewById(R.id.tvCorpo);
            idResposta = view.findViewById(R.id.idResp);
            btnVerResposta = view.findViewById(R.id.btnVerResposta);

            final Atividades atividades = listaAtividade.get(posicao);
            tvCorpo.setText(atividades.getQuest());

            btnVerResposta.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    idResposta.setText(atividades.getResp());
                }
            });

        return view;
    }
}
