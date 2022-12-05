package br.unicamp.apicontent.Forms;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import br.unicamp.apicontent.Models.Content;
import br.unicamp.apicontent.R;

public class ActvityConteudo extends AppCompatActivity
{
     TextView tvTittleContent;
     ImageView tvImageContent;
     ImageButton home, work, user;
     TextView tvCorpo;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo);
        getSupportActionBar().hide();

        home = findViewById(R.id.idHome);
        work = findViewById(R.id.idWork);
        user = findViewById(R.id.idUser);

        work.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ActvityConteudo.this, ActivityAtividades.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ActvityConteudo.this, Feed.class));
            }
        });


        tvTittleContent = findViewById(R.id.tvTitleContent);
        tvImageContent = findViewById(R.id.tvImageContent);
        tvCorpo = findViewById(R.id.tvCorpo);

        Intent intent = getIntent();
        Content content = (Content) intent.getSerializableExtra("serializebleContent");
        String simImage = content.getImage();

        if(content != null)
        {
            //byte[] bytes = Base64.decode(simImage, Base64.DEFAULT);
//bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            tvTittleContent.setText(content.getTitle());
            tvImageContent.setImageBitmap(StringToBitmap(simImage));
            tvCorpo.setText(content.getCorpo());
        }
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