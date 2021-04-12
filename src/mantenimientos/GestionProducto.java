package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.ProductoInterface;
import modelos.Producto;
import utils.MySQLConexion;

public class GestionProducto implements ProductoInterface {

	@Override
	public ArrayList<Producto> listado() {
		ArrayList<Producto> lista = new ArrayList<>();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			pst = con.prepareStatement("select * from producto");
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

	@Override
	public int registrar(Producto p) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = MySQLConexion.getConexion();
			con.setAutoCommit(false);
			String sql = "INSERT INTO `florei_import`.`producto`\r\n" + "(`marca`,\r\n" + "`desc_prod`,\r\n"
					+ "`uni_med_prod`,\r\n" + "`stk_prod`,\r\n" + "`pre_unit`,\r\n" + "`pes_unit`,\r\n"
					+ "`usu_creador_prod`)\r\n" + "VALUES\r\n" + "(?,?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, p.getMarca());
			pst.setString(2, p.getDesc_prod());
			pst.setString(3, p.getUni_med_prod());
			pst.setInt(4, p.getStk_prod());
			pst.setDouble(5, p.getPre_unit());
			pst.setDouble(6, p.getPes_unit());
			pst.setInt(7, p.getUsu_creador_prod());
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
	public int eliminar(Producto p) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			con.setAutoCommit(false);
			String sql = "delete from Producto where Cod_prod = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, p.getCod_prod());
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
	public int actualizar(Producto p) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			con.setAutoCommit(false);
			String sql = "UPDATE `florei_import`.`producto`\r\n" + "SET\r\n" + "`marca` = ?,\r\n"
					+ "`desc_prod` = ?,\r\n" + "`uni_med_prod` = ?,\r\n" + "`stk_prod` = ?,\r\n" + "`pre_unit` = ?,\r\n"
					+ "`pes_unit` = ?\r\n" + "WHERE `cod_prod` = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, p.getMarca());
			pst.setString(2, p.getDesc_prod());
			pst.setString(3, p.getUni_med_prod());
			pst.setInt(4, p.getStk_prod());
			pst.setDouble(5, p.getPre_unit());
			pst.setDouble(6, p.getPes_unit());
			pst.setInt(7, p.getCod_prod());
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
}
