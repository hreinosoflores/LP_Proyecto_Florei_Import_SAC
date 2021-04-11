package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.GuiaInterface;
import modelos.Guia;
import utils.MySQLConexion;

public class GestionGuia implements GuiaInterface {

	@Override
	public ArrayList<Guia> listado() {
		ArrayList<Guia> lista = new ArrayList<>();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			pst = con.prepareStatement("select * from guia");
			rs = pst.executeQuery();
			while (rs.next()) {
				Guia g = new Guia();
				g.setNum_gui(rs.getInt(1));
				g.setFec_guia(rs.getTimestamp(2));
				g.setFec_trasl(rs.getTimestamp(3));
				g.setDirec_part(rs.getString(4));
				g.setDirec_lleg(rs.getString(5));
				g.setMotiv_trasl(rs.getString(6));
				g.setCod_cli(rs.getInt(7));
				g.setCod_trans(rs.getInt(8));
				g.setCod_emp(rs.getInt(9));
				g.setUsu_creador_gui(rs.getInt(10));
				lista.add(g);
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
	public int registrar(Guia g) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			con.setAutoCommit(false);
			String sql = "INSERT INTO `florei_import`.`guia`\r\n" + "(`fec_trasl`,\r\n" + "`direc_part`,\r\n"
					+ "`direc_lleg`,\r\n" + "`motiv_trasl`,\r\n" + "`cod_cli`,\r\n" + "`cod_trans`,\r\n"
					+ "`cod_emp`,\r\n" + "`usu_creador_gui`)\r\n" + "VALUES\r\n" + "(?,?,?,?,?,?,?,?);";
			pst = con.prepareStatement(sql);
			pst.setTimestamp(1, g.getFec_trasl());
			pst.setString(2, g.getDirec_part());
			pst.setString(3, g.getDirec_lleg());
			pst.setString(4, g.getMotiv_trasl());
			pst.setInt(5, g.getCod_cli());
			pst.setInt(6, g.getCod_trans());
			pst.setInt(7, g.getCod_emp());
			pst.setInt(8, g.getUsu_creador_gui());
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
	public int eliminar(Guia g) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from guia where num_gui = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, g.getNum_gui());
			rs = pst.executeUpdate();
		} catch (Exception x) {
			JOptionPane.showMessageDialog(null, "Error en la sentencia: " + x.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException x) {
				JOptionPane.showMessageDialog(null, "Error al cerrar: " + x.getMessage());
			}
		}
		return rs;
	}

	@Override
	public int actualizar(Guia g) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			pst = con.prepareStatement("update guia set fec_trasl=?, direc_part=?," + "direc_lleg=?, motiv_trasl=?,"
					+ "cod_cli=?," + "cod_trans=?," + " cod_emp=?" + " where num_gui=? ");

			pst.setTimestamp(1, g.getFec_trasl());
			pst.setString(2, g.getDirec_part());
			pst.setString(3, g.getDirec_lleg());
			pst.setString(4, g.getMotiv_trasl());
			pst.setInt(5, g.getCod_cli());
			pst.setInt(6, g.getCod_trans());
			pst.setInt(7, g.getCod_emp());
			pst.setInt(8, g.getNum_gui());
			rs = pst.executeUpdate();

		} catch (Exception x) {
			JOptionPane.showMessageDialog(null, "Error en la sentencia: " + x.getMessage());

		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();

			} catch (SQLException x) {
				JOptionPane.showMessageDialog(null, "Error al cerrar: " + x.getMessage());
			}
		}
		return rs;
	}

}
