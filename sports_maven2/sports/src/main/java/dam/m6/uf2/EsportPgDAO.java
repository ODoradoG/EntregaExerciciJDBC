package dam.m6.uf2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EsportPgDAO implements DAO<Esport> {
    private Connection conn;

    public EsportPgDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Esport item) {
        if (conn == null || item == null) return;
        String sql = "INSERT INTO DEPORTES(NOMBRE) VALUES(?) RETURNING COD";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, item.getNom());
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    item.setCod(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error afegint esport: " + e.getMessage());
        }
    }

    @Override
    public List<Esport> getAll() {
        List<Esport> llista = new ArrayList<>();
        if (conn == null) return llista;

        String sql = "SELECT * FROM llista_esports()";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    int cod = rs.getInt("cod");
                    String nom = rs.getString("nom");
                    llista.add(new Esport(cod, nom));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error recuperant esports: " + e.getMessage());
        }

        return llista;
    }
}
