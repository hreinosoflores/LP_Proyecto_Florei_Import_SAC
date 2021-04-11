package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.EmpresaInterface;
import modelos.Empresa;
import utils.MySQLConexion;

public class GestionEmpresa implements EmpresaInterface {

	@Override
	public int registrar(Empresa e) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			con.setAutoCommit(false);
			String sql = "INSERT INTO `florei_import`.`empresa_remitente`\r\n" + "(`raz_soc_emp`,\r\n"
					+ "`direc_emp`,\r\n" + "`ruc_emp`,\r\n" + "`email_emp`,\r\n" + "`telefono`,\r\n"
					+ "`usu_creado_emp`)\r\n" + "VALUES\r\n" + "(?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);

			pst.setString(1, e.getRaz_soc_emp());
			pst.setString(2, e.getDirec_emp());
			pst.setString(3, e.getRuc_emp());
			pst.setString(4, e.getEmail_emp());
			pst.setString(5, e.getTelefono());
			pst.setInt(6, e.getUsu_creado_emp());
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
			} catch (SQLException e2) {
				JOptionPane.showMessageDialog(null, "Error al cerrar: " + e2.getMessage());
			}
		}
		return rs;
	}

	@Override
	public ArrayList<Empresa> listado() {
		ArrayList<Empresa> lista = new ArrayList<Empresa>();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = MySQLConexion.getConexion();
			pst = con.prepareStatement("select * from empresa_remitente");
			rs = pst.executeQuery();
			while (rs.next()) {
				Empresa e = new Empresa();
				e.setCod_emp(rs.getInt(1));
				e.setRaz_soc_emp(rs.getString(2));
				e.setDirec_emp(rs.getString(3));
				e.setRuc_emp(rs.getString(4));
				e.setEmail_emp(rs.getString(5));
				e.setTelefono(rs.getString(6));
				e.setUsu_creado_emp(rs.getInt(7));
				lista.add(e);

			}

		} catch (Exception x) {
			JOptionPane.showMessageDialog(null, "Error en la sentencia" + x.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				JOptionPane.showMessageDialog(null, "Error al cerrar: " + e2.getMessage());
			}
		}
		return lista;
	}

	@Override
	public int eliminar(Empresa e) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from empresa_remitente where cod_emp = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, e.getCod_emp());
			rs = pst.executeUpdate();
		} catch (Exception x) {
			JOptionPane.showMessageDialog(null, "Error en la sentencia: " + x.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				JOptionPane.showMessageDialog(null, "Error al cerrar: " + e2.getMessage());
			}
		}
		return rs;
	}

	@Override
	public int actualizar(Empresa e) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			pst = con.prepareStatement("update empresa_remitente " + "set raz_soc_emp=?," + "direc_emp=?,"
					+ "ruc_emp=?," + "email_emp=? ," + "telefono=?," + " where cod_emp=?");
			pst.setString(1, e.getRaz_soc_emp());
			pst.setString(2, e.getDirec_emp());
			pst.setString(3, e.getRuc_emp());
			pst.setString(4, e.getEmail_emp());
			pst.setString(5, e.getTelefono());
			pst.setInt(6, e.getCod_emp());

			rs = pst.executeUpdate();

		} catch (Exception x) {
			JOptionPane.showMessageDialog(null, "Error en la  sentencia: " + x.getMessage());

		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				JOptionPane.showMessageDialog(null, "Error al cerrar: " + e2.getMessage());
			}
		}
		return rs;
	}
}
