package com.example.vivanco.tasmc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ManejadorBD extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public ManejadorBD(Context context) {
        super(context, "tasmc", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creacion tabla itinerario
        db.execSQL("CREATE TABLE itinerario ("
                + "idItinerario INTEGER PRIMARY KEY AUTOINCREMENT, " + "nombre TEXT)");

        //Creacion tabla equipaje
        db.execSQL("CREATE TABLE equipaje ("
                + "idEquipaje INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT)");

        //Creacion tabla objeto
        db.execSQL("CREATE TABLE objeto ("
                + "idObjeto INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, categoria TEXT)");

        //Creacion tabla actividad
        db.execSQL("CREATE TABLE actividad ("
                + "idActividad INTEGER,nombre TEXT,"
                + "idItinerario INTEGER," +
                " PRIMARY KEY(idActividad,idItinerario)," +
                "FOREIGN KEY(idItinerario) REFERENCES itinerario(idItinerario))");

        //Crea tabla Equipaje_has_Objeto
        db.execSQL("" +
                "CREATE TABLE Equipaje_has_Objeto(" +
                "Equipaje_idEquipaje INTEGER," +
                "Objeto_idObjeto INTEGER," +
                "PRIMARY KEY(Equipaje_idEquipaje,Objeto_idObjeto)," +
                "FOREIGN KEY(Equipaje_idEquipaje) REFERENCES equipaje(idEquipaje)," +
                "FOREIGN KEY(Objeto_idObjeto) REFERENCES objeto(idObjeto))");

        //Creacion tabla usuario
        db.execSQL("CREATE TABLE usuario ("
                + "idUsuario INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "email TEXT,"
                + "catPref TEXT,"
                + "clasePref TEXT, "
                + "tipo TEXT,"
                + "tipoVuelo TEXT," +
                "Itinerario_idItinerario INTEGER," +
                "Equipaje_idEquipaje INTEGER,"
                + "FOREIGN KEY(Itinerario_idItinerario) REFERENCES itinerario(idItinerario),"
                + "FOREIGN KEY(Equipaje_idEquipaje) REFERENCES equipaje(idEquipaje))");

        //Creacion tabla vuelo
        db.execSQL("CREATE TABLE vuelo ("
                + "idVUelo INTEGER PRIMARY KEY AUTOINCREMENT, " + "categoria TEXT," + "aerolinea TEXT," + "vuelo TEXT,"
                + "fechaSalida TEXT," + " fechaLlegada TEXT," + "origen TEXT,"
                + "destino TEXT," + "estado TEXT," + "horaSalida TEXT,"
                + "horaLlegada TEXT," + "terminal TEXT," + "puerta TEXT," + "escalas TEXT," + "tiempo TEXT," +  "precio TEXT,"+
                "Usuario_idUsuario INTEGER," +
                "FOREIGN KEY(Usuario_idUsuario) REFERENCES usuario(idUsuario))");

        db.execSQL("INSERT INTO equipaje VALUES ( null, 'default' )");

        db.execSQL("INSERT INTO itinerario VALUES ( null, 'default' )");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS vuelo");
        db.execSQL("DROP TABLE IF EXISTS actividad");
        db.execSQL("DROP TABLE IF EXISTS objeto");
        db.execSQL("DROP TABLE IF EXISTS equipaje");
        db.execSQL("DROP TABLE IF EXISTS itinerario");
        db.execSQL("DROP TABLE IF EXISTS Equipaje_has_Objeto");
        onCreate(db);
    }

    public void guardarUsuario(Usuario usuario) {
        db = getWritableDatabase();
        if(existeUsuario())
            db.execSQL("delete from usuario");
        db.execSQL("INSERT INTO usuario VALUES ( null,'" + usuario.getEmail() + "','"
                + usuario.getCategoria() + "','" + usuario.getClase() + "','" + usuario.getTipo() + "','"
                + usuario.getTipoVuelo() + "','" + usuario.getItinerario_idItinerario()
                + "','" + usuario.getEquipaje_idEquipaje() + "')");

    }

    public void guardarVuelo(Vuelo vuelo) {
        db = getWritableDatabase();
        db.execSQL("INSERT INTO vuelo VALUES ( null,'" + vuelo.getCategoria() + "','"+ vuelo.getAerolinea() + "','"
                + vuelo.getVuelo() + "','"+ vuelo.getFechaSalida() + "','" + vuelo.getFechaLlegada() + "','"
                + vuelo.getOrigen() + "','" + vuelo.getDestino() + "','" + vuelo.getEstado() + "','"
                + vuelo.getHoraSalida() + "','" + vuelo.getHoraLlegada() + "','" + vuelo.getTerminal() + "','"
                + vuelo.getPuerta() + "','" + vuelo.getTiempo() + "','"+ vuelo.getEscalas() + "')");
    }

    public void guardarActividad(Actividad actividad) {
        db = getWritableDatabase();
        //db.execSQL("INSERT INTO actividad VALUES ( null,'" + actividad.getNombre() + "','"
          //      + actividad.getFecha() + "')");
    }


    public void guardarObjeto(Objeto objeto) {
        db = getWritableDatabase();
        db.execSQL("INSERT INTO objeto VALUES ( null,'" + objeto.getNombre() + "')");
    }

    public void guardarEquipaje(Equipaje equipaje) {
        db = getWritableDatabase();
        db.execSQL("INSERT INTO equipaje VALUES ( null,'" + equipaje.getNombre() + "','"
                + equipaje.getCategoria() + "')");
    }

    public void guardarItinerario(Itinerario itinerario) {
        db = getWritableDatabase();
       // db.execSQL("INSERT INTO itinerario VALUES ( null,'" + itinerario.getDestino() + "')");
    }

    public void guardarServicio(Servicio servicio) {
        db = getWritableDatabase();
        db.execSQL("INSERT INTO servicio VALUES ( null,'" + servicio.getNombre() + "','"
                + servicio.getGiro() + "')");
    }

    //Funciones para borrar
    public void borrarEquipaje(Equipaje equipaje) {
        db = getWritableDatabase();
        //db.delete(nombreTabla,valores,clausulaWhere,argumentosWhere);
        db.delete("equipaje", "id=?", new String[]{Integer.toString(equipaje.getId())});
    }

    public void borrarItinerario(Itinerario itinerario) {
        db = getWritableDatabase();
        //db.delete(nombreTabla,valores,clausulaWhere,argumentosWhere);
      //  db.delete("itinerario", "id=?", new String[]{Integer.toString(itinerario.getId())});
    }

    public void borrarVuelo(Vuelo vuelo) {
        db = getWritableDatabase();
        //db.delete(nombreTabla,valores,clausulaWhere,argumentosWhere);
        db.delete("vuelo", "id=?", new String[]{Integer.toString(vuelo.getId())});
    }

    public void borrarObjeto(Objeto objeto) {
        db = getWritableDatabase();
        //db.delete(nombreTabla,valores,clausulaWhere,argumentosWhere);
        db.delete("objeto", "id=?", new String[]{Integer.toString(objeto.getId())});
    }

    public void borrarActividad(Actividad actividad) {
        db = getWritableDatabase();
        //db.delete(nombreTabla,valores,clausulaWhere,argumentosWhere);
        db.delete("actividad", "id=?", new String[]{Integer.toString(actividad.getId())});
    }


    public void borrarServicio(Servicio servicio) {
        db = getWritableDatabase();
        //db.delete(nombreTabla,valores,clausulaWhere,argumentosWhere);
        db.delete("servicio", "id=?", new String[]{Integer.toString(servicio.getId())});
    }

    /*
        public List<Hotel> consultaHotel() {
            String[] campos = {"id", "nombre", "categoria", "precio", "zona",
                    "ocupabilidad", "telefono"};
            List<Hotel> resultado = new ArrayList<Hotel>();
            db = getReadableDatabase();
            //db.query(nombreTabla,columnas,selection,selectionArgs,groupBy,having,orderBy)
            Cursor cursor = db.query("hotel", campos, null, null, null, null, "id");
            while (cursor.moveToNext()) {
                Hotel hotel = new Hotel(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6));
                resultado.add(hotel);
            }
            cursor.close();
            return resultado;
        }
    */
    public void closeDatabase() {
        db.close();
    }

    public boolean existeUsuario() {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from usuario",null);
        if (c.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }

    public Usuario getUsuario() {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from usuario",null);
        c.moveToFirst();
        return new Usuario(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),
                c.getInt(6),c.getInt(7));
    }
}
