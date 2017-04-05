package it.polito.tdp.anagrammi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.anagrammi.DAO.ConnectDB;
import it.polito.tdp.anagrammi.model.Model;

public class DizionarioDAO {
	
	public boolean cercaParola(String parola){
		final String sql="SELECT nome FROM parola WHERE nome=?";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,parola);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {	
				return true;
			}

			return false;

		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

}
