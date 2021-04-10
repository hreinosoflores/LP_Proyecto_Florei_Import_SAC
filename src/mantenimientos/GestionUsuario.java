package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import interfaces.UsuarioInterface;
import modelos.Usuario;
import utils.MySQLConexion;

public class GestionUsuario implements UsuarioInterface {

	@Override
	public Usuario validaAcceso(String usuario, String clave) {
		Usuario u = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "{CALL usp_validaAcceso (?,?)}";
			pst = con.prepareStatement(sql);
			pst.setString(1, usuario);
			pst.setString(2, clave);
			rs = pst.executeQuery();
			while (rs.next()) {
				u = new Usuario();
				u.setCodi(rs.getInt(1));
				u.setNom_usua(rs.getString(2));
				u.setApe_usua(rs.getString(3));
				u.setUsua(rs.getString(4));
				u.setClav(rs.getString(5));;
			}

		} catch (Exception e) {
			System.out.println("Error en la sentencia" + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					pst.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar");
			}
		}
		return u;
	}

}
