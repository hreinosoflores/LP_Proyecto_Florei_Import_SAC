package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.TransportistaInterface;
import modelos.Transportista;
import utils.MySQLConexion;

public class GestionTransportista implements TransportistaInterface {

	@Override
	public int registrar(Transportista trans) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			con.setAutoCommit(false);
			String sql = "INSERT INTO `florei_import`.`transportista`\r\n" + "(`nom_trans`,\r\n" + "`ape_trans`,\r\n"
					+ "`direc_trans`,\r\n" + "`telf_trans`,\r\n" + "`ruc_trans`,\r\n" + "`num_lic`,\r\n"
					+ "`usu_creador_trans`)\r\n" + "VALUES\r\n" + "(?,?,?,?,?,?,?);";
			pst = con.prepareStatement(sql);
			pst.setString(1, trans.getNom_trans());
			pst.setString(2, trans.getApe_trans());
			pst.setString(3, trans.getDirec_trans());
			pst.setString(4, trans.getTelf_trans());
			pst.setString(5, trans.getRuc_trans());
			pst.setString(6, trans.getNum_lic());
			pst.setInt(7, trans.getUsu_creador_trans());

			rs = pst.executeUpdate();
			con.commit();
		} catch (Exception x) {

			try {

				con.rollback();
				JOptionPane.showMessageDialog(null, "Error en la sentencia: " + x.getMessage());

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
	public ArrayList<Transportista> listado() {
		ArrayList<Transportista> lista = new ArrayList<Transportista>();
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT * FROM transportista";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Transportista t = new Transportista();
				t.setCod_trans(rs.getInt(1));
				t.setNom_trans(rs.getString(2));
				t.setApe_trans(rs.getString(3));
				t.setDirec_trans(rs.getString(4));
				t.setTelf_trans(rs.getString(5));
				t.setRuc_trans(rs.getString(6));
				t.setNum_lic(rs.getString(7));
				t.setUsu_creador_trans(rs.getInt(8));
				lista.add(t);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en la Sentencia" + e.getMessage());
		}

		finally {
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
	public int eliminar(Transportista trans) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from Transportista where cod_trans = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, trans.getCod_trans());
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
	public int actualizar(Transportista trans) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update Transportista set nom_trans=?," + "ape_trans=?," + "direc_trans=?," + "telf_trans=?,"
					+ "ruc_trans=?," + "num_lic=?" + "where cod_trans = ? ";
			pst = con.prepareStatement(sql);
			pst.setString(1, trans.getNom_trans());
			pst.setString(2, trans.getApe_trans());
			pst.setString(3, trans.getDirec_trans());
			pst.setString(4, trans.getTelf_trans());
			pst.setString(5, trans.getRuc_trans());
			pst.setString(6, trans.getNum_lic());
			pst.setInt(7, trans.getCod_trans());

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
