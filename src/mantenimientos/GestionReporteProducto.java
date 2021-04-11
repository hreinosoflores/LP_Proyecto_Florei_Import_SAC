package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.ReporteProductoInterface;
import modelos.Producto;
import utils.MySQLConexion;

public class GestionReporteProducto implements ReporteProductoInterface {

	@Override
	public ArrayList<Producto> listado(String tip_uni, int stock1, int stock2, String desc_cont) {
		ArrayList<Producto> lista = new ArrayList<>();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			pst = con.prepareStatement("{call usp_reportarproducto(?,?,?,?)}");
			pst.setString(1, tip_uni);
			pst.setInt(2, stock1);
			pst.setInt(3, stock2);
			pst.setString(4, desc_cont);
			rs = pst.executeQuery();
			while (rs.next()) {
				Producto p = new Producto();
				p.setCod_prod(rs.getInt(1));
				p.setMarca(rs.getString(2));
				p.setDesc_prod(rs.getString(3));
				p.setUni_med_prod(rs.getString(4));
				p.setStk_prod(rs.getInt(5));
				p.setPre_unit(rs.getDouble(6));
				p.setPes_unit(rs.getDouble(7));
				p.setUsu_creador_prod(rs.getInt(8));
				lista.add(p);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en la sentencia: " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error al cerrar: " + e.getMessage());
			}
		}
		return lista;
	}

}
