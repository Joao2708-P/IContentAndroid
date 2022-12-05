package br.unicamp.apicontent.Forms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.List;
import br.unicamp.apicontent.APIconf.RetrofitConfiggg;
import br.unicamp.apicontent.APIconf.Service;
import br.unicamp.apicontent.Adapters.GridViwenAdapter;
import br.unicamp.apicontent.Models.Content;
import br.unicamp.apicontent.Models.LogUser;
import br.unicamp.apicontent.Models.User;
import br.unicamp.apicontent.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Feed extends AppCompatActivity
{
    private GridView grid;
    ImageButton home;
    ImageButton work;
    ImageButton user;

    public void populateGridView(List<Content> listaContent)
    {
        grid = findViewById(R.id.feed_gruop);
        GridViwenAdapter adapter = new GridViwenAdapter(this, listaContent);
        grid.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        Intent intent1 = getIntent();
        LogUser logUser = (LogUser) intent1.getSerializableExtra("SerializebleLogin");
        User users = (User) intent.getSerializableExtra("serializableUser");

        home = findViewById(R.id.idHome);
        work = findViewById(R.id.idWork);
        user = findViewById(R.id.idUser);

        user.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(users != null)
                {
                    Intent intent = new Intent(Feed.this, ActivityPage.class);
                    intent.putExtra("serializableUserPage", users);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Feed.this, "Deu Erro!", Toast.LENGTH_LONG).show();
                }

                if(logUser != null)
                {
                    Intent intent2 = new Intent(Feed.this, ActivityPage.class);
                    intent2.putExtra("SerializebleLogin", logUser);
                    startActivity(intent2);
                }
                else
                {
                    Toast.makeText(Feed.this, "Deu erro!", Toast.LENGTH_LONG).show();
                }
            }
        });

        work.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Feed.this, ActivityAtividades.class));
            }
        });

        Service service = RetrofitConfiggg.getRetrofitInstance().create(Service.class);
        Call<List<Content>> call = service.getConteudo();
        call.enqueue(new Callback<List<Content>>() {
            @Override
            public void onResponse(Call<List<Content>> call, Response<List<Content>> response)
            {
                 if(response.isSuccessful())
                 {
                     populateGridView(response.body());
                 }
                 else
                 {
                     Toast.makeText(Feed.this, "Erro ao puxar o Conteudo", Toast.LENGTH_LONG).show();
                 }
            }
            @Override
            public void onFailure(Call<List<Content>> call, Throwable t)
            {
                Toast.makeText(Feed.this, "Sem conexão com a API", Toast.LENGTH_LONG).show();
            }
        });
    }
}