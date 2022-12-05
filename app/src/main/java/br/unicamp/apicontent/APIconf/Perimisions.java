package br.unicamp.apicontent.APIconf;

import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.apicontent.Forms.MainActivity;

public class Perimisions
{
  public static boolean validandoPermision(String[] permissions, MainActivity mainActivity, int request)
  {
      if(Build.VERSION.SDK_INT >= 25)
      {
          List<String> listaPermissions = new ArrayList<>();
          for(String permission: permissions)
          {
              Boolean temPermissao = ContextCompat.checkSelfPermission(mainActivity, permission) == PackageManager.PERMISSION_GRANTED;
              if ( !temPermissao ) listaPermissions.add(permission);
          }

          if(listaPermissions.isEmpty()) return true;
          String[] novaPermissao = new String[listaPermissions.size()];
          listaPermissions.toArray(novaPermissao);

          ActivityCompat.requestPermissions(mainActivity, novaPermissao, request);
      }
      return true;
  }
}
