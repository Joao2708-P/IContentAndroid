package br.unicamp.apicontent.Forms;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.unicamp.apicontent.APIconf.RetrofitConfiggg;
import br.unicamp.apicontent.APIconf.Service;
import br.unicamp.apicontent.Adapters.GridViwenAdapter;
import br.unicamp.apicontent.Models.LogUser;
import br.unicamp.apicontent.Models.Nivel;
import br.unicamp.apicontent.Models.User;
import br.unicamp.apicontent.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPage extends AppCompatActivity
{
    TextView tvNomeUser;
    TextView tvNomeNivel;
    TextView tvNomeEmail;
    ImageView imageUser;

    ImageButton home;
    ImageButton work;
    ImageButton user;
    Nivel nivel;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        User user2 = (User) intent.getSerializableExtra("serializableUserPage");

        home = findViewById(R.id.idHome);
        work = findViewById(R.id.idWork);
        user = findViewById(R.id.idUser);

        home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ActivityPage.this, ActivityAtividades.class));
            }
        });

        work.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ActivityPage.this, ActivityAtividades.class));
            }
        });

        tvNomeUser = findViewById(R.id.tvNomeUser);
        tvNomeNivel = findViewById(R.id.tvNomeNivel);
        tvNomeEmail = findViewById(R.id.tvNomeEmail);
        imageUser= findViewById(R.id.imageUser);
        String simImage = user2.getImage();

        if(user2 != null)
        {
            imageUser.setImageBitmap(StringToBitmap(simImage));
            tvNomeUser.setText(user2.getNome());
            tvNomeEmail.setText(user2.getEmail());
        }

        Service service2 = RetrofitConfiggg.getRetrofitInstance().create(Service.class);
        Call<Nivel> call2 = service2.getNivelPorNome(user2.getNivelEscolar());
        call2.enqueue(new Callback<Nivel>()
        {
            @Override
            public void onResponse(Call<Nivel> call2, Response<Nivel> response)
            {
                if(response.isSuccessful())
                {
                    nivel = response.body();
                    tvNomeNivel.setText(nivel.getNivel());
                    Toast.makeText(ActivityPage.this, "Deu Certo!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ActivityPage.this, "Não funcionou", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Nivel> call2, Throwable t)
            {
                Toast.makeText(ActivityPage.this, "Erro de conexão com a API", Toast.LENGTH_SHORT).show();
            }
        });


        Service service = RetrofitConfiggg.getRetrofitInstance().create(Service.class);
        Call<List<User>> call = service.getUser();
        call.enqueue(new Callback<List<User>>()
        {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response)
            {
                if(response.isSuccessful())
                {
                    Toast.makeText(ActivityPage.this, "Deu Certo!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ActivityPage.this, "Não funcionou", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t)
            {
                Toast.makeText(ActivityPage.this, "Erro de conexão com a API", Toast.LENGTH_SHORT).show();
            }
        });
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