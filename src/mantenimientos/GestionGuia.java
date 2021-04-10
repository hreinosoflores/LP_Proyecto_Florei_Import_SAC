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
				g.setFec_guia(rs.getString(2));
				g.setFec_trasl(rs.getString(3));
				g.setDirec_part(rs.getString(4));
				g.setDirec_lleg(rs.getString(5));
				g.setMotiv_trasl(rs.getString(6));
				g.setPes_tot(rs.getDouble(7));
				g.setCod_cli(rs.getInt(8));
				g.setCod_trans(rs.getInt(9));
				g.setCod_emp(rs.getInt(10));
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
			/*
			 * insert into
			 * Guia(num_gui,fec_guia,fec_trasl,direc_part,direc_lleg,motiv_trasl
			 * ,pes_tot,cod_cli,cod_trans,cod_emp)
			 * values('N000001','2013/05/22','2013/05/30','Av. Belisario Suarez
			 * 1080','Pz Las Azucenas Mz O6B LT1','Venta al por
			 * mayor',1550.82,'CLI01','TRA01','EMP01');
			 */
			String sql = "insert into Guia values(null,?,?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, g.getFec_guia());
			pst.setString(2, g.getFec_trasl());
			pst.setString(3, g.getDirec_part());
			pst.setString(4, g.getDirec_lleg());
			pst.setString(5, g.getMotiv_trasl());
			pst.setDouble(6, g.getPes_tot());
			pst.setInt(7, g.getCod_cli());
			pst.setInt(8, g.getCod_trans());
			pst.setInt(9, g.getCod_emp());
			rs = pst.executeUpdate();
			
			con.commit();
			
		} catch (Exception x) {
		
		try {
			
			con.rollback();
			JOptionPane.showMessageDialog(null, "Error en la sentencia: " + x.getMessage());
			
		} catch (Exception ex) {
			
			JOptionPane.showMessageDialog(null, "Error en el rollback");
			
		}
		}finally {
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
			pst = con.prepareStatement(
					"update guia set fec_guia=?," + "fec_trasl=?, direc_part=?," + "direc_lleg=?, motiv_trasl=?,"
							+ "pes_tot=?, cod_cli=?," + "cod_trans=?, cod_emp=? where num_gui=? ");

			pst.setString(1, g.getFec_guia());
			pst.setString(2, g.getFec_trasl());
			pst.setString(3, g.getDirec_part());
			pst.setString(4, g.getDirec_lleg());
			pst.setString(5, g.getMotiv_trasl());
			pst.setDouble(6, g.getPes_tot());
			pst.setInt(7, g.getCod_cli());
			pst.setInt(8, g.getCod_trans());
			pst.setInt(9, g.getCod_emp());
			pst.setInt(10, g.getNum_gui());
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
