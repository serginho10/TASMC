package com.example.vivanco.tasmc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ManejadorBD extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public ManejadorBD(Context context) {
        super(context, "tasmc", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creacion tabla itinerario
        db.execSQL("CREATE TABLE itinerario (idItinerario INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "destino TEXT)");

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
                + "email TEXT PRIMARY KEY,"
                + "catPref TEXT,"
                + "clasePref TEXT, "
                + "tipo TEXT," +
                "Itinerario_idItinerario INTEGER," +
                "Equipaje_idEquipaje INTEGER,"
                + "FOREIGN KEY(Itinerario_idItinerario) REFERENCES itinerario(idItinerario),"
                + "FOREIGN KEY(Equipaje_idEquipaje) REFERENCES equipaje(idEquipaje))");

        //Creacion tabla vuelo
        db.execSQL("CREATE TABLE vuelo ("
                + "idVUelo INTEGER PRIMARY KEY AUTOINCREMENT, " + "categoria TEXT," + "aerolinea TEXT," + "vuelo TEXT,"
                + "fechaSalida TEXT," + " fechaLlegada TEXT," + "origen TEXT,"
                + "destino TEXT," + "estado TEXT," + "horaSalida TEXT,"
                + "horaLlegada TEXT," + "terminal TEXT," + "puerta TEXT," + "escalas TEXT," + "tiempo TEXT," + "precio TEXT," +
                "Usuario_idUsuario INTEGER," +
                "FOREIGN KEY(Usuario_idUsuario) REFERENCES usuario(idUsuario))");

        db.execSQL("INSERT INTO equipaje VALUES ( null, 'default' )");

        //db.execSQL("INSERT INTO itinerario VALUES ( null, 'default' )");
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
        if (existeUsuario())
            db.execSQL("delete from usuario");
        db.execSQL("INSERT INTO usuario VALUES ('" + usuario.getEmail() + "','"
                + usuario.getCategoria() + "','" + usuario.getClase() + "','" + usuario.getTipo() + "',"
                + usuario.getItinerario_idItinerario()
                + "," + usuario.getEquipaje_idEquipaje() + ")");
        if (usuario.getEmail().length() != 0) {
            Object[] objeto = new Object[1];
            objeto[0] = usuario;
            new ManejadorHttp().execute(objeto);
        }
    }

   /* public void guardarVuelo(Vuelo vuelo) {
        db = getWritableDatabase();
        db.execSQL("INSERT INTO vuelo VALUES ( null,'" + vuelo.getCl() + "','" + vuelo.getAerolinea() + "','"
                + vuelo.getVuelo() + "','" + vuelo.getFechaSalida() + "','" + vuelo.getFechaLlegada() + "','"
                + vuelo.getOrigen() + "','" + vuelo.getDestino() + "','" + vuelo.getEstado() + "','"
                + vuelo.getHoraSalida() + "','" + vuelo.getHoraLlegada() + "','" + vuelo.getTerminal() + "','"
                + vuelo.getPuerta() + "','" + vuelo.getTiempo() + "','" + vuelo.getEscalas() + "')");
    }*/

    public void guardarActividad(Actividad actividad) {
        db = getWritableDatabase();
        db.execSQL("INSERT INTO actividad VALUES ( null,'" + actividad.getNombre() + "','"
              + actividad.getItinerario() + "')");
    }


    public void guardarObjeto(Objeto objeto) {
        db = getWritableDatabase();
        db.execSQL("INSERT or replace INTO objeto VALUES ( " + objeto.getId() + ",'" + objeto.getNombre() + "','"
                + objeto.getCategoria() + "')");
    }

    public void guardarEquipaje(Equipaje equipaje) {
        db = getWritableDatabase();
        db.execSQL("INSERT INTO equipaje VALUES ( " + equipaje.getId() + ",'" + equipaje.getNombre() + "')");
    }

    public void guardarEquipajeX(int id, String nombre) {
        db = getWritableDatabase();
        db.execSQL("INSERT or replace INTO equipaje VALUES ( " + id + ",'" + nombre + "')");
    }

    public void guardarItinerario(Itinerario itinerario) {
        db = getWritableDatabase();
        db.execSQL("INSERT INTO itinerario VALUES ( null,'" + itinerario.getDestino() + "')");
        db = getReadableDatabase();
        Cursor c = db.rawQuery("select idItinerario from itinerario where destino = ?",new String[] {itinerario.getDestino()});
        c.moveToFirst();
        for(int i = 0;i < itinerario.getActividades().size();i++){
            Actividad act = itinerario.getActividades().get(i);
            act.setItinerario(c.getInt(0));
            guardarActividad(itinerario.getActividades().get(i));
        }
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
        db.delete("itinerario", "idItinerario=?", new String[]{Integer.toString(itinerario.getIdItinerario())});
        db.delete("actividad", "idItinerario=?", new String[]{Integer.toString(itinerario.getIdItinerario())});
    }

    /*public void borrarVuelo(Vuelo vuelo) {
        db = getWritableDatabase();
        //db.delete(nombreTabla,valores,clausulaWhere,argumentosWhere);
        db.delete("vuelo", "id=?", new String[]{Integer.toString(vuelo.getIdVuelo())});
    }*/

    public void borrarObjeto(Objeto objeto) {
        db = getWritableDatabase();
        //db.delete(nombreTabla,valores,clausulaWhere,argumentosWhere);
        db.delete("objeto", "id=?", new String[]{Integer.toString(objeto.getId())});
    }

    /*public void borrarActividad(Actividad actividad) {
        db = getWritableDatabase();
        //db.delete(nombreTabla,valores,clausulaWhere,argumentosWhere);
        db.delete("actividad", "id=?", new String[]{Integer.toString(actividad.getId())});
    }*/


    public void borrarServicio(Servicio servicio) {
        db = getWritableDatabase();
        //db.delete(nombreTabla,valores,clausulaWhere,argumentosWhere);
        db.delete("servicio", "id=?", new String[]{Integer.toString(servicio.getId())});
    }

    public void actualizarItinerario(Itinerario itinerario) {
        borrarItinerario(itinerario);
        guardarItinerario(itinerario);
    }

    public void closeDatabase() {
        db.close();
    }

    public boolean existeUsuario() {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from usuario", null);
        if (c.moveToFirst()) {
            if(c.getString(1).length() != 0) {
                Object[] objeto = new Object[1];
                objeto[0] = new Usuario(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4), c.getInt(5));
                new ManejadorHttp().execute(objeto);
            }
            return true;
        } else {
            return false;
        }
    }

    public Usuario getUsuario() {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from usuario", null);
        c.moveToFirst();
        return new Usuario(c.getString(0), c.getString(1), c.getString(2), c.getString(3),
                c.getInt(4), c.getInt(5));
    }

    public void guardarEquipajeHasObjeto(EquipajeHasObjeto obj) {
        db = getWritableDatabase();
        db.execSQL("INSERT or replace INTO Equipaje_has_Objeto VALUES ( " + obj.getEquipaje_idEquipaje() + "," + obj.getObjeto_idObjeto() + ")");
    }

    public String[] obtenerNombresEquipajes() {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from equipaje", null);
        String[] nombres = new String[c.getCount() - 1];
        int i = 0;
        while (c.moveToNext()) {
            if ((c.getString(1)).compareTo("default") != 0) {
                nombres[i] = c.getString(1);
                i++;
            }
        }
        return nombres;
    }

    public Objeto[] obtenerObjetosDe(String equipaje) {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("select o.idObjeto,o.nombre,o.categoria " +
                "from equipaje as e, objeto as o, equipaje_has_objeto as h " +
                "where e.idequipaje = h.equipaje_idequipaje " +
                "and o.idobjeto = h.objeto_idobjeto " +
                "and e.nombre=?", new String[]{equipaje});
        Objeto[] obj = new Objeto[c.getCount()];
        int i = 0;
        while (c.moveToNext()) {
            obj[i] = new Objeto(c.getInt(0), c.getString(1), c.getString(2));
            i++;
        }
        return obj;
    }

    public Map<String, String> obtenerObjetosDEquipaje() {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("select e.nombre,o.nombre from equipaje as e, objeto as o, equipaje_has_objeto as h" +
                " where e.idequipaje=h.equipaje_idequipaje and o.idobjeto=h.objeto_idobjeto and idequipaje != 1", null);
        Map<String, String> objetos = new HashMap<String, String>();
        while (c.moveToNext()) {
            if ((c.getString(0)).compareTo("default") != 0) {
                if (objetos.get(c.getString(0)) == null)
                    objetos.put(c.getString(0), c.getString(1));
                else
                    objetos.put(c.getString(0), objetos.get(c.getString(0)) + ", " + c.getString(1));
            }
        }
        return objetos;
    }

    public void borrarTodoEquipaje() {
        db = getWritableDatabase();
        db.execSQL("delete from equipaje");
    }

    public void borrarTodoObjeto() {
        db = getWritableDatabase();
        db.execSQL("delete from objeto");
    }

    public void borrarTodoEquipajeHasObjeto() {
        db = getWritableDatabase();
        db.execSQL("delete from equipaje_has_objeto");
    }

    /*public ArrayList<Actividad> getAllData() {
        ArrayList<Actividad> list = new ArrayList<>();
        String[] campos = {"id", "imagen", "destino", "actividades"};
        db = getReadableDatabase();

        Cursor cursor = db.query("itinerario", campos, null, null, null, null, "id");

        while (cursor.moveToNext()) {
            Actividad actividad = new Actividad(cursor.getInt(0),
                    cursor.getInt(1), cursor.getString(2), cursor.getString(3));
            list.add(actividad);
        }

        return list;
    }*/

    public Objeto[] obtenerObjetos() {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from objeto order by nombre", null);
        Objeto[] obj = new Objeto[c.getCount()];
        int i = 0;
        while (c.moveToNext()) {
            if ((c.getString(1)).compareTo("default") != 0) {
                obj[i] = new Objeto(c.getInt(0),c.getString(1),c.getString(2));
                i++;
            }
        }
        return obj;
    }

    public int getIdObjeto(String nombre) {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("select idObjeto from objeto where nombre = ?",new String[]{nombre});
        int id = 0;
        while(c.moveToNext()){
            id = c.getInt(0);
        }
        return id;
    }

    public ArrayList<Itinerario> obtenerItinerarios() {
        ArrayList<Itinerario> it = new ArrayList<Itinerario>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from itinerario order by destino",null);
        Map<Integer,String> itinerarios = new HashMap<Integer,String>();
        while(c.moveToNext()){
            itinerarios.put(c.getInt(0),c.getString(1));
        }
        Iterator ite = itinerarios.keySet().iterator();
        while(ite.hasNext()){
            int key = (int) ite.next();
            it.add(new Itinerario(key,itinerarios.get(key),obtenerActividadesDe(key)));
        }
        return it;
    }

    private ArrayList<Actividad> obtenerActividadesDe(int idItinerario) {
        ArrayList<Actividad> act = new ArrayList<Actividad>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from actividad where idItinerario = ?", new String[]{idItinerario + ""});
        while(c.moveToNext()){
            act.add(new Actividad(c.getInt(0),c.getString(1),c.getInt(2)));
        }
        return act;
    }

    public String getCategoriaUsuario() {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("select catPref from usuario",null);
        c.moveToFirst();
        return c.getString(0);
    }

    public String getClaseUsuario() {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("select clasePref from usuario",null);
        c.moveToFirst();
        return c.getString(0);
    }
}
