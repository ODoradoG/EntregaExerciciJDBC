package dam.m6.uf2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Types; 

public class UserPgDAO implements DAO<User> {
    private Connection conn;

    public UserPgDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(User item) {
        // TODO Auto-generated method stub

        if (conn == null)
            return;

        String sql = "INSERT ...";

        throw new UnsupportedOperationException("Unimplemented method 'add'");

    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

          String sql = "SELECT * FROM users";
        try {
            
       
    
        ///Aqui
        ///  cal fer el query i recòrrer el ResultSet per afegir els usuaris 
        /// 
        /// a la llista users
        throw new SQLException("Not implemented yet");


        } catch (SQLException e) {
            System.err.println("Error al buscar usuaris: " + e.getMessage());
        }

        return users;
    }

    public String callFunction(String p1, String p2) {
        CallableStatement cStmt = null;
        try {
          String call = "{ ? = call get_user_name(?, ?) }";
            try (CallableStatement cs = conn.prepareCall(call)) {

                // 1️⃣ Register the return value (first ?)
                cs.registerOutParameter(1, Types.VARCHAR);

                // 2️⃣ Set input parameters
                cs.setString(2, p1);
                cs.setString(3, p2);

                // 3️⃣ Execute the call
                cs.execute();

                // 4️⃣ Retrieve the returned value
                String result = cs.getString(1);
                return result;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    

    }

    public boolean userExists(String name) {
        return false;
    }

}
