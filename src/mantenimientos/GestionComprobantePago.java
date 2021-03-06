package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.ComprobantePagoInterface;
import modelos.ComprobantePago;
import utils.MySQLConexion;

public class GestionComprobantePago implements ComprobantePagoInterface {

	@Override
	public int registrar(ComprobantePago cp) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			con.setAutoCommit(false);
			String sql = "insert into comprobante_pago" + "(tip_comp,lug_comp,cod_cli,usu_creador_comp)"
					+ " values(?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, cp.getTip_comp());
			pst.setString(2, cp.getLug_comp());
			pst.setInt(3, cp.getCod_cli());
			pst.setInt(4, cp.getUsu_creador_comp());
			rs = pst.executeUpdate();

			con.commit();

		} catch (Exception e) {

			try {

				con.rollback();
				JOptionPane.showMessageDialog(null, "Error en la sentencia: " + e.getMessage());

			} catch (Exception ex) {

				JOptionPane.showMessageDialog(null, "Error en el rollback");

			}
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
		return rs;
	}

	@Override
	public ArrayList<ComprobantePago> listado() {
		ArrayList<ComprobantePago> lista = new ArrayList<>();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			pst = con.prepareStatement("select * from comprobante_pago");
			rs = pst.executeQuery();
			while (rs.next()) {
				ComprobantePago cp = new ComprobantePago();
				cp.setNum_comp(rs.getInt(1));
				cp.setFec_comp(rs.getTimestamp(2));
				cp.setTip_comp(rs.getString(3));
				cp.setLug_comp(rs.getString(4));
				cp.setCod_cli(rs.getInt(5));
				cp.setUsu_creador_comp(rs.getInt(6));
				lista.add(cp);
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

	@Override
	public int eliminar(ComprobantePago cp) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from comprobante_pago where num_comp = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, cp.getNum_comp());
			rs = pst.executeUpdate();
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
		return rs;
	}

	@Override
	public int actualizar(ComprobantePago cp) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			pst = con.prepareStatement("update comprobante_pago set " + "tip_comp = ?," + "lug_comp = ?,"
					+ "cod_cli = ? where num_comp = ?");
			pst.setString(1, cp.getTip_comp());
			pst.setString(2, cp.getLug_comp());
			pst.setInt(3, cp.getCod_cli());
			pst.setInt(4, cp.getNum_comp());
			rs = pst.executeUpdate();

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
		return rs;
	}

}
