package br.unicamp.apicontent.Forms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.unicamp.apicontent.APIconf.RetrofitConfiggg;
import br.unicamp.apicontent.APIconf.Service;
import br.unicamp.apicontent.Adapters.GridViwenAdapter;
import br.unicamp.apicontent.Adapters.GridViwenAdpterAtividade;
import br.unicamp.apicontent.Models.Atividades;
import br.unicamp.apicontent.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAtividades extends AppCompatActivity
{
    private GridView gridView;
    ImageButton home, work, user;

    public void populationGridViewn(List<Atividades> listaAtividade)
    {
//        gridView = findViewById(R.id.feelGroup);
        Log.i("lista atividade", listaAtividade.get(0).getQuest());
        GridViwenAdpterAtividade adapterss = new GridViwenAdpterAtividade(this, listaAtividade);
        gridView.setAdapter(adapterss);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades2);
        getSupportActionBar().hide();

        home = findViewById(R.id.idHome);
        work = findViewById(R.id.idWork);
        user = findViewById(R.id.idUser);

        gridView = findViewById(R.id.feelGroup);

        Service service = RetrofitConfiggg.getRetrofitInstance().create(Service.class);
        Call<List<Atividades>> call = service.getAtividade();
        call.enqueue(new Callback<List<Atividades>>()
        {
            @Override
            public void onResponse(Call<List<Atividades>> call, Response<List<Atividades>> response)
            {
                if(response.isSuccessful())
                {
                    populationGridViewn(response.body());
                }
                else
                {
                    Toast.makeText(ActivityAtividades.this, "Erro ao puxar as Atividades", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Atividades>> call, Throwable t)
            {
                Toast.makeText(ActivityAtividades.this, "Sem conexão com a API", Toast.LENGTH_LONG).show();
            }
        });

        work.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ActivityAtividades.this, ActivityAtividades.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ActivityAtividades.this, Feed.class));
            }
        });
    }
}