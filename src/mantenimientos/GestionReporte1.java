package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.Reporte1Interface;
import modelos.Reporte1;
import utils.MySQLConexion;

public class GestionReporte1 implements Reporte1Interface {

	@Override
	public ArrayList<Reporte1> listado(String tipo, String fec_ini, String fec_fin) {
		ArrayList<Reporte1> lista = new ArrayList<>();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			pst = con.prepareStatement("{call usp_reportarcomprobante(?,?,?)}");
			pst.setString(1, tipo);
			pst.setString(2, fec_ini);
			pst.setString(3, fec_fin);
			rs = pst.executeQuery();
			while (rs.next()) {
				Reporte1 r = new Reporte1();
				r.setCodigo(rs.getInt(1));
				r.setRegistro(rs.getString(2));
				r.setCliente(rs.getString(3));
				r.setProducto(rs.getString(4));
				r.setCant(rs.getInt(5));
				r.setPago(rs.getDouble(6));
				lista.add(r);
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
