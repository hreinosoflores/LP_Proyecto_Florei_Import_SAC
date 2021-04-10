package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.Reporte3Interface;
import modelos.Reporte3;
import utils.MySQLConexion;

public class GestionReporte3 implements Reporte3Interface {

	@Override
	public ArrayList<Reporte3> listaCbo(String tip, int stk1, int stk2) {
		ArrayList<Reporte3> lista = new ArrayList<Reporte3>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			con = MySQLConexion.getConexion();
			String sql = "{call usp_ReporteComprobante(?,?,?)}";
			pst = con.prepareStatement(sql);
			pst.setString(1, tip);
			pst.setInt(2, stk1);
			pst.setInt(3, stk2);
			rs = pst.executeQuery();

			while (rs.next()) {

				Reporte3 r3 = new Reporte3();
				r3.setCod_prod(rs.getInt(1));
				r3.setDesc_prod(rs.getString(2));
				r3.setMarca(rs.getString(3));
				r3.setUni_med_prod(rs.getString(4));
				r3.setStk_prod(rs.getInt(5));
				r3.setPre_unit(rs.getDouble(6));

				lista.add(r3);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en Sentencia" +e.getMessage());
		} finally {

			try {
				pst.close();
				con.close();
			} catch (

			Exception e) {
				JOptionPane.showMessageDialog(null, "Error al cerrar");
			}

		}

		return lista;
	}

}
