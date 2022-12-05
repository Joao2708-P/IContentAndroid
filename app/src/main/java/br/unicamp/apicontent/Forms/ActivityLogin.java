package br.unicamp.apicontent.Forms;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import br.unicamp.apicontent.APIconf.RetrofitConfiggg;
import br.unicamp.apicontent.APIconf.Service;
import br.unicamp.apicontent.APIconf.Session;
import br.unicamp.apicontent.Models.LogUser;
import br.unicamp.apicontent.Models.Token;
import br.unicamp.apicontent.Models.User;
import br.unicamp.apicontent.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity
{
    TextView tvCadastro;
    EditText edtEmail;
    EditText edtSenha;
    Button btnEntrar;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();

        tvCadastro = findViewById(R.id.tvCadastro);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnEntrar = findViewById(R.id.btnEntrar);

        tvCadastro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ActivityLogin.this, MainActivity.class));
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();

                if(TextUtils.isEmpty(email) || email.equals(""))
                {
                    MensagemDeErro("EMAIL");
                }
                else if(TextUtils.isEmpty(senha) || senha.equals(""))
                {
                    MensagemDeErro("SENHA");
                }
                else
                {
                    LogUser logUser = new LogUser(email, senha);

                    getLogin(logUser);
                }
            }
        });
    }

    private void setSession(User user)
    {
        Session session = new Session(ActivityLogin.this);
        session.setusename(user.getNome());
    }

    private void getLogin(LogUser logUser)
    {
        Service service = RetrofitConfiggg.getRetrofitInstance().create(Service.class);
        Call<Token> call = service.logUser(logUser);
        call.enqueue(new Callback<Token>()
        {
            @Override
            public void onResponse(Call<Token> call2, Response<Token> response)
            {
                try
                {
                    if(response.isSuccessful())
                    {
                        Token token1 = response.body();
                         /* Session session = new Session(MainActivity.this);
                          session.setusename(user1.getNome()); */
                        Intent intent = new Intent(ActivityLogin.this, Feed.class);
                        intent.putExtra("SerializebleLogin", (Serializable) logUser);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(ActivityLogin.this,"Usuário não autenticado", Toast.LENGTH_LONG).show();
                    }

                    Toast.makeText(ActivityLogin.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception err)
                {
                    System.err.println(err.getMessage());
                }
                /*
                if(response.isSuccessful())
                {
                 */
                    /*
                    String token = response.body();

                    if(token != null)
                    {
                        if(token.getSenha().equals(senha))
                        {
                            setSession(token);
                            startActivity(new Intent(ActivityLogin.this, Feed.class));
                        }
                        else
                        {
                            Toast.makeText(ActivityLogin.this,"Senha Inválida", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(ActivityLogin.this,"Usuário Inesistente", Toast.LENGTH_LONG).show();
                    }*
                }
                else
                {
                    try
                    {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String erro = jsonObject.getJSONObject("erro").getString("Message!!");
                        Toast.makeText(ActivityLogin.this, erro , Toast.LENGTH_LONG).show();
                    }
                    catch (Exception err)
                    {
                        Toast.makeText(ActivityLogin.this, err.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }*/
            }
            @Override
            public void onFailure(Call<Token> call3, Throwable tt)
            {
                String problem = tt.getMessage().toString();
                Toast.makeText(ActivityLogin.this, problem, Toast.LENGTH_LONG).show();
                Toast.makeText(ActivityLogin.this, "Erro de conexão com a API/Usuário não encontrado", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void MensagemDeErro(String digito)
    {
        //   new AlertDialog.Builder(MainActivity.this)
        //   .setTitle("Erro ao criar o usuário")
        //     .setMessage("Por favor, preencha o campo" + digito + "corretamente");
        Toast.makeText(this, "Por favor, preencha o campo " + digito + " corretamente", Toast.LENGTH_LONG).show();
    }
}