package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import interfaces.ReporteComprobanteInterface;
import modelos.ReporteComprobante;
import utils.MySQLConexion;

public class GestionReporte2 implements ReporteComprobanteInterface {

	@Override
	public ArrayList<ReporteComprobante> listado(String tipo) {
		ArrayList<ReporteComprobante> lista = new ArrayList<ReporteComprobante>();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select *from Cliente_Destinatario   where tip_doc=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, tipo);

			rs = pst.executeQuery();//

			while (rs.next()) {

				ReporteComprobante u = new ReporteComprobante();
				u.setCod_cli(rs.getInt(1));
				u.setNom_cli(rs.getString(2));
				u.setApe_cli(rs.getString(3));
				u.setTip_doc(rs.getString(4));
				u.setNum_doc(rs.getString(5));
				u.setRuc_cli(rs.getString(6));
				u.setDirec_cli(rs.getString(7));
				u.setTelef_cli(rs.getString(8));
				u.setEmail_cli(rs.getString(9));

				lista.add(u);
			}

		} catch (Exception e) {
			System.out.println("Error al listar: " + e.getMessage());
		} finally {
			try {
				pst.close();
				con.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar: " + e2.getMessage());
			}

		}
		return lista;
	}

	@Override
	public ArrayList<ReporteComprobante> listado() {
		ArrayList<ReporteComprobante> lista = new ArrayList<ReporteComprobante>();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select *from Cliente_Destinatario group by tip_doc";
			pst = con.prepareStatement(sql);

			rs = pst.executeQuery();//

			while (rs.next()) {

				ReporteComprobante u = new ReporteComprobante();
				u.setCod_cli(rs.getInt(1));
				u.setTip_doc(rs.getString(4));

				lista.add(u);
			}

		} catch (Exception e) {
			System.out.println("Error al listar: " + e.getMessage());
		} finally {
			try {
				pst.close();
				con.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar: " + e2.getMessage());
			}

		}
		return lista;
	}

}
