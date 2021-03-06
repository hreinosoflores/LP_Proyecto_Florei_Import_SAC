package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.ReporteComprobanteInterface;
import modelos.ReporteComprobante;
import utils.MySQLConexion;

public class GestionReporteComprobante implements ReporteComprobanteInterface {

	@Override
	public ArrayList<ReporteComprobante> listado(String ty, String fec_ini, String fec_fin) {
		ArrayList<ReporteComprobante> lista = new ArrayList<>();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			pst = con.prepareStatement("{call usp_reportarcomprobante(?,?,?)}");
			pst.setString(1, ty);
			pst.setString(2, fec_ini);
			pst.setString(3, fec_fin);
			rs = pst.executeQuery();
			while (rs.next()) {
				ReporteComprobante r = new ReporteComprobante();
				r.setCodigo(rs.getInt(1));
				r.setTipo(rs.getString(2));
				r.setFeccomp(rs.getDate(3));
				r.setFecharegistro(rs.getString(4));
				r.setCreador(rs.getString(5));
				r.setCliente(rs.getString(6));
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
