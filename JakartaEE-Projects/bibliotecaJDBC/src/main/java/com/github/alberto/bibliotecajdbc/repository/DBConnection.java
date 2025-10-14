package com.github.alberto.bibliotecajdbc.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection conn = null;

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {

        if (conn == null) {
            Properties prop = new Properties();

            try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("JDBC.properties")) {

                Class.forName("org.h2.Driver");
                prop.load(input);
                conn = DriverManager.getConnection(prop.getProperty("url"), prop);

            }catch (FileNotFoundException ex) {
                ex.printStackTrace();
                throw new SQLException("No se ha encontrado el fichero de propiedades");
            }catch (IOException ex) {
                ex.printStackTrace();
                throw new SQLException("Error cargando el fichero de propiedades");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                throw new SQLException("No se ha encontrado el driver de conexión");
            }
        }
        return conn;
    }

    public static void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

}
