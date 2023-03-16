package javeriana.ms.rest.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javeriana.ms.rest.entities.Paseo;
import javeriana.ms.rest.entities.PaseoDTO1;
import javeriana.ms.rest.entities.PaseoDTO2;

public class PaseoControl {
    private static String database_url = "jdbc:postgresql://database:5432/postgres";
    private static String database_user = "postgres";
    private static String database_password = "postgres";
    private static String database_table = "Paseos";
    private static Connection con;

    public static ArrayList<Paseo> getPaseos() {
        ArrayList<Paseo> paseos = new ArrayList<Paseo>();
        Properties properties = new Properties();
        properties.setProperty("user", database_user);
        properties.setProperty("password", database_password);
        try {
            con = DriverManager.getConnection(database_url, properties);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM " + database_table + ";");
            while (rs.next()) {
                Long id = rs.getLong("ID");
                String name = rs.getString("NOMBRE");
                String price = rs.getString("LUGAR_SALIDA");
                String sales = rs.getString("LUGAR_LLEGADA");
                Date total = rs.getDate("FECHA");
                paseos.add(new Paseo(id, name, price, sales, total));
            }
        } catch (SQLException e) {

        }

        return paseos;
    }

    public static boolean deletePaseo(Long id) {
        Properties properties = new Properties();
        properties.setProperty("user", database_user);
        properties.setProperty("password", database_password);
        try {
            con = DriverManager.getConnection(database_url, properties);
            con.createStatement().execute("DELETE FROM " + database_table + " WHERE ID=" + String.valueOf(id) + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static Paseo updatePaseo(PaseoDTO1 paseo) {
        Paseo p = null;
        Properties properties = new Properties();
        properties.setProperty("user", database_user);
        properties.setProperty("password", database_password);
        try {
            con = DriverManager.getConnection(database_url, properties);
            con.createStatement()
                    .execute("UPDATE " + database_table + " SET LUGAR_SALIDA='" + paseo.getLugar_salida()
                            + "',LUGAR_LLEGADA='" + paseo.getLugar_llegada() + "' WHERE ID="
                            + String.valueOf(paseo.getId()) + ";");
            ResultSet rs = con.createStatement().executeQuery(
                    "SELECT * FROM " + database_table + " WHERE ID=" + String.valueOf(paseo.getId()) + ";");
            while (rs.next()) {
                Long id = rs.getLong("ID");
                String name = rs.getString("NOMBRE");
                String price = rs.getString("LUGAR_SALIDA");
                String sales = rs.getString("LUGAR_LLEGADA");
                Date total = rs.getDate("FECHA");
                p = (new Paseo(id, name, price, sales, total));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

    public static boolean newPaseo(PaseoDTO2 p) {
        Properties properties = new Properties();
        properties.setProperty("user", database_user);
        properties.setProperty("password", database_password);
        try {
            con = DriverManager.getConnection(database_url, properties);
            con.createStatement()
                    .execute("INSERT INTO " + database_table + " (NOMBRE, LUGAR_SALIDA, LUGAR_LLEGADA, FECHA) VALUES ('"
                            + p.getNombre() + "','" + p.getLugar_salida() + "','" + p.getLugar_llegada() + "', TIMESTAMP '"
                            +  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(p.getFecha())+ "');");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
