package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.ClienteInterface;
import modelos.Cliente;
import utils.MySQLConexion;

public class GestionCliente implements ClienteInterface {

	@Override
	public int registrar(Cliente c) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			con.setAutoCommit(false);
			String sql = "insert into cliente_destinatario" + "(nom_cli,ape_cli,tip_doc,num_doc,"
					+ "ruc_cli,direc_cli,telef_cli,email_cli)" + "values(?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, c.getNom_cli());
			pst.setString(2, c.getApe_cli());
			pst.setString(3, c.getTip_doc());
			pst.setString(4, c.getNum_doc());
			pst.setString(5, c.getRuc_cli());
			pst.setString(6, c.getDirec_cli());
			pst.setString(7, c.getTelef_cli());
			pst.setString(8, c.getEmail_cli());
			rs = pst.executeUpdate();
			
			con.commit();
		} catch (Exception e) {
			
			try {con.rollback();
			JOptionPane.showMessageDialog(null, "Error en la sentencia: " + e.getMessage());
			
			}catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error en el rollback: ");
		}} finally {
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
	public ArrayList<Cliente> listado() {
		ArrayList<Cliente> lista = new ArrayList<>();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			pst = con.prepareStatement("select*from Cliente_Destinatario");
			rs = pst.executeQuery();
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setCod_cli(rs.getInt(1));
				c.setNom_cli(rs.getString(2));
				c.setApe_cli(rs.getString(3));
				c.setTip_doc(rs.getString(4));
				c.setNum_doc(rs.getString(5));
				c.setRuc_cli(rs.getString(6));
				c.setDirec_cli(rs.getString(7));
				c.setTelef_cli(rs.getString(8));
				c.setEmail_cli(rs.getString(9));
				lista.add(c);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en la sentencia: " + e.getMessage());
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error al cerrar: " + e.getMessage());
			}
		}
		return lista;
	}

	@Override
	public int eliminar(Cliente c) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from Cliente_Destinatario where cod_cli = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, c.getCod_cli());
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
	public int actualizar(Cliente c) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			pst = con.prepareStatement(
					"update cliente_destinatario set nom_cli = ?," + "ape_cli = ?," + "tip_doc = ?," + "num_doc = ?,"
							+ "ruc_cli = ?," + "direc_cli = ?," + "telef_cli = ?," + "email_cli = ? where cod_cli = ?");

			pst.setString(1, c.getNom_cli());
			pst.setString(2, c.getApe_cli());
			pst.setString(3, c.getTip_doc());
			pst.setString(4, c.getNum_doc());
			pst.setString(5, c.getRuc_cli());
			pst.setString(6, c.getDirec_cli());
			pst.setString(7, c.getTelef_cli());
			pst.setString(8, c.getEmail_cli());
			pst.setInt(9, c.getCod_cli());
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
