package es.marcos.muestracasa;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>  {

    private TextView tv;
    Adaptador adaptador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv = (TextView)findViewById(R.id.tv);

        Uri uri = Contrato.TablaInmuebles.CONTENT_URI;
        String [] proyeccion = null;
        String condicion = null;
        String [] parametros = null;
        String orden = null;

        Cursor cursor =  getContentResolver().query (
                uri,
                proyeccion,
                condicion,
                parametros,
                orden);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String col1 = cursor.getString(1)+"";
            System.out.print("fallo "+col1);
            String col2 = cursor.getString(2);
            String col3 = cursor.getString(3);

            System.out.print("fallo "+col1+col2+col3);
            tv.append (col1+" "+col2+" "+col3+" "+"\n");
            cursor.moveToNext();
        }
    }


    public void insert (View v) {
        Uri uri = Contrato.TablaInmuebles.CONTENT_URI;
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaInmuebles.DIRECCION, "Prueba");
        valores.put(Contrato.TablaInmuebles.TIPO, "MuestraCasa");
        valores.put(Contrato.TablaInmuebles.PRECIO, "500000");
        Uri u = getContentResolver().insert(uri, valores);
        tv.append(u.toString() + "\n");

        Cursor cursor = getContentResolver().query(
                u, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String col1 = cursor.getString(1);
            String col2 = cursor.getString(2);
            String col3 = cursor.getString(3);

            tv.append(col1 + " " + col2 + " " + col3 + " "  + "\n");
            cursor.moveToNext();
        }
    }

    public void  update (View v){

        Uri uri = Contrato.TablaInmuebles.CONTENT_URI;
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaInmuebles.DIRECCION, "Prueba");
        valores.put(Contrato.TablaInmuebles.TIPO, "Actualizacion");
        String where = Contrato.TablaInmuebles.PRECIO+ " = ?";
        String[] args = new String[]{""};
        int i = getContentResolver().update(uri, valores, where,args);
        tv.append(i+ "registros modificados\n");
    }


    public void delete (View v){
        Uri uri= Uri.withAppendedPath(Contrato.TablaInmuebles.CONTENT_URI, "5" );
        int i = getContentResolver().delete(uri,null,null);
        tv.append(i + " registros eliminados\n");
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = Contrato.TablaInmuebles.CONTENT_URI;
        return  new CursorLoader(
                this,uri,null,null,null,
                Contrato.TablaInmuebles.DIRECCION+ " collate localized asc");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adaptador.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adaptador.swapCursor(null);
    }
}
