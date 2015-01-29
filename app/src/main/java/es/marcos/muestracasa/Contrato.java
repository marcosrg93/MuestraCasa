package es.marcos.muestracasa;

import android.net.Uri;
import android.provider.BaseColumns;

import java.net.URI;

/**
 * Created by MarcosFrancisco on 20/01/2015.
 */
public class Contrato {

    private Contrato (){

    }

    public static  abstract class  TablaInmuebles implements BaseColumns {
        public static final String TABLA = "inmuebles";
        public static final String DIRECCION = "direccion";
        public static final String TIPO = "tipo";
        public static final String PRECIO = "precio";
        public static final String CONTENT_TYPE_INMUEBLES =
                "vnd.android.cursor.dir/vnd.inmuebles";
        public static final String CONTENT_TYPE_INMUEBLE_ID=
                "vnd.android.cursor.item/vnd.inmuebles";
        public static final Uri CONTENT_URI =
                Uri.parse("content://" + Proveedor.AUTORIDAD + "/" + TABLA);
    }
}
