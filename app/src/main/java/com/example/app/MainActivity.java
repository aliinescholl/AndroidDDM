package com.example.app;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    PackageManager pm;
    ArrayList<Aplicativo> listaAplicativos;
    Button btnAll, btnLancaveis;

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        btnAll = findViewById(R.id.btnAllApps);

        btnLancaveis = findViewById(R.id.btnAllLancaveis);
        listaAplicativos = new ArrayList<>();

        pm = getPackageManager();

        btnAll.setOnClickListener(v->{atualizaListagemListView(listaTodosApps());});
        btnLancaveis.setOnClickListener(v->{atualizaListagemListView(listaTodosApps());});

        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            Aplicativo aplicativo = (Aplicativo) adapterView.getItemAtPosition(position);
            Intent launchIntent = pm.getLaunchIntentForPackage(aplicativo.packageName);
            if (launchIntent != null) {
                startActivity(launchIntent);
            }
        });
        btnAll.performClick();
    }
    public  ArrayList<Aplicativo> listaTodosApps() {
        listaAplicativos.clear();

        Intent launcherIntent = new Intent(Intent.ACTION_MAIN, null);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> launchables = pm.queryIntentActivities(launcherIntent, PackageManager.MATCH_ALL);
        Set<String> pacoteLancavel = new HashSet<>();
        for (ResolveInfo info : launchables) {
            pacoteLancavel.add(info.activityInfo.packageName);
        }

        for (ApplicationInfo app : pm.getInstalledApplications(PackageManager.MATCH_ALL)) {
            if (!pacoteLancavel.contains(app.packageName)) continue;
            Aplicativo aplicativo = new Aplicativo();
            aplicativo.nome = app.loadLabel(pm).toString();
            aplicativo.resolveInfo = pm.resolveActivity(new Intent(Intent.ACTION_MAIN).setPackage(app.packageName), PackageManager.MATCH_DEFAULT_ONLY);
            aplicativo.packageName = app.packageName;
            aplicativo.icone = app.loadIcon(pm);
            listaAplicativos.add(aplicativo);
        }
        return listaAplicativos;
    }
    public void atualizaListagemListView(ArrayList<Aplicativo> listapps) {
        AppAdapter adapter = new AppAdapter(this, R.layout.item_app, listapps);
        listView.setAdapter(adapter);
    }
}