package br.unicamp.apicontent.Forms;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import br.unicamp.apicontent.APIconf.RetrofitConfiggg;
import br.unicamp.apicontent.APIconf.Service;
import br.unicamp.apicontent.Models.Nivel;
import br.unicamp.apicontent.Models.User;
import br.unicamp.apicontent.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.provider.MediaStore;
import  android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    EditText edtNome;
    EditText edtEmail;
    EditText edtSenha;
    EditText edtSenha2;
    ImageView idFoto;
    Button btnCadastrar;
    TextView contaExistente;
    Spinner spinner;
    List<Nivel> listaNivel = new ArrayList<>();
    User user;
    Bitmap imagem;
    Nivel nivelll = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        edtSenha2 = findViewById(R.id.edtSenha2);
        idFoto = findViewById(R.id.idFoto);
        spinner = findViewById(R.id.spinner);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        contaExistente = findViewById(R.id.tvContaExixtente);
        idFoto = findViewById(R.id.idFoto);

        Service service = RetrofitConfiggg.getRetrofitInstance().create(Service.class);
        Call<List<Nivel>> call = service.getNveis();
        call.enqueue(new Callback<List<Nivel>>()
        {
            @Override
            public void onResponse(Call<List<Nivel>> call, Response<List<Nivel>> response)
            {
                if(response.isSuccessful())
                {
                    //listaNivel = (List<Nivel>)response.body();
                    // listaNivel.addAll(response.body());
                    for (Nivel respostaNivel : response.body())
                        listaNivel.add(respostaNivel);
                }
            }

            @Override
            public void onFailure(Call<List<Nivel>> call, Throwable t)
            {
                Toast.makeText(MainActivity.this, "Erro ao consultar niveis", Toast.LENGTH_SHORT).show();
            }
        });

        idFoto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                }
                else
                {
                    SelectImage();
                }
            }
        });

        //Redireciona o usuário caso ele já tenha uma conta
        contaExistente.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, ActivityLogin.class));
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email = edtEmail.getText().toString();
                String nome = edtNome.getText().toString();
                String senha = edtSenha.getText().toString();
                String confirmarSenha = edtSenha2.getText().toString();
                String nivel = spinner.getSelectedItem().toString();
                Nivel  nivelSelecionado = null;

//                for (int posicao = 0; posicao < listaNivel.size(); posicao++)
//                {
//                    if(nivel.equals(listaNivel.get(posicao).getNivel()))
//                    {
//                        nivelSelecionado = listaNivel.get(posicao);
//                        break;
//                    }
//                }
                for (Nivel elementoNivel : listaNivel)
                {
                    if (nivel.equals(elementoNivel.getNivel()))
                    {
                        nivelll = elementoNivel;
                        break;
                    }
                }

                try
                {
                    if(email.trim().equals("") || TextUtils.isEmpty(email.trim()))
                    {
                        MensagemDeErro("EMAIL");
                    }
                    else if(nome.trim().equals("") || TextUtils.isEmpty(nome.trim()))
                    {
                        MensagemDeErro("NOME");
                    }
                    else if(senha.trim().equals("") || TextUtils.isEmpty(senha.trim()))
                    {
                        MensagemDeErro("SENHA");
                    }
                    else if(!confirmarSenha.equals(senha))
                    {
                        MensagemDeErro("CONFIRMAR SENHA");
                    }
                    else if(nivel.trim().equals("") || TextUtils.isEmpty(nivel.trim()))
                    {
                        MensagemDeErro("NIVEL ESCOLAR");
                    }
                    else
                    {
                        if(nivelll != null)
                        {
                            user = new User(email, nome, senha, nivelll.getId(), BitmapToString(imagem));
                            IncluirUser(user);
                        }
                    }
                }
                catch (Exception err)
                {
                    System.err.println(err.getMessage());
                }
            }
        });
    }

    public void MensagemDeErro(String digito)
    {
//        new AlertDialog.Builder(MainActivity.this)
//                .setTitle("Erro ao criar o usuário")
//                .setMessage("Por favor, preencha o campo" + digito + "corretamente");
        Toast.makeText(this, "Por favor, preencha o campo " + digito + " corretamente", Toast.LENGTH_LONG).show();
    }

    public void IncluirUser(User user)
    {
        Service service = RetrofitConfiggg.getRetrofitInstance().create(Service.class);
        Call<User> call = service.postUsuario(user);
        call.enqueue(new Callback<User>()
        {
            @Override
            public void onResponse(Call<User> call2, Response<User> response)
            {
                if(response.isSuccessful())
                {
                    Intent intent = new Intent(MainActivity.this, Feed.class);
                    intent.putExtra("serializableUser", (Serializable) user);
                    startActivity(intent);
                    
                    /* Session session = new Session(MainActivity.this);
                    session.setusename(user1.getNome()); */
//                    Intent intent = new Intent(MainActivity.this, Feed.class);
//                    Bundle parametros = new Bundle();
//                    parametros.putBinder("User", (IBinder) user1);
//                    intent.putExtras(parametros);
//                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Usuário já está cadastrado, tente adicionar outro ou logue", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t)
            {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    //Tentativa de conseguir inserir uma imagem no banco!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private void SelectImage()
    {
        try
        {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);

            launchSomeActivity.launch(i);
        }
        catch (Exception err)
        {
            err.getMessage();
        }
    }

    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();

                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        imagem = selectedImageBitmap;
                    }
                }
            });

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
//    {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//        {
//            SelectImage();
//        }
//        else
//        {
//            Toast.makeText(getApplicationContext(), "Permissão negada", Toast.LENGTH_LONG).show();
//        }
//    }

    private String BitmapToString(Bitmap bitmap)
    {
        try
        {
            ByteArrayOutputStream streamDaFoto = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, streamDaFoto);
            byte[] encodeByte = streamDaFoto.toByteArray();
            return Base64.getEncoder().encodeToString(encodeByte);
        }
        catch (Exception err)
        {
            err.getMessage();
            return null;
        }
    }
}