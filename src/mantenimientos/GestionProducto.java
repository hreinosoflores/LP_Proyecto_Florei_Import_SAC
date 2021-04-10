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
			// "insert into Producto values('PRO05','Panel LED 60x60cm 41W
			// 3400LM 35W',250,'Cajas','Lightech'"
			String sql = "insert into producto(marca,desc_prod,uni_med_prod," + "stk_prod,pre_unit) values(?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, p.getMarca());
			pst.setString(2, p.getDesc_prod());
			pst.setString(3, p.getUni_med_prod());
			pst.setInt(4, p.getStk_prod());
			pst.setDouble(5, p.getPre_unit());
			rs = pst.executeUpdate();

			con.commit();
		} catch (Exception x) {
		
		try {
			
			con.rollback();
			JOptionPane.showMessageDialog(null, "Error en la sentencia: " + x.getMessage());
			
		} catch (Exception ex) {
			
			JOptionPane.showMessageDialog(null, "Error en el rollback");}
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
	public int eliminar(Producto p) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from Producto where Cod_prod = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, p.getCod_prod());
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
	public int actualizar(Producto p) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			/* desc_prod,stk_prod,uni_med_prod,raz_soc_prod */
			String sql = "update Producto set marca = ?," + "desc_prod = ?," + "uni_med_prod = ?, " + "stk_prod = ?,"
					+ "pre_unit = ? where cod_prod = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, p.getMarca());
			pst.setString(2, p.getDesc_prod());
			pst.setString(3, p.getUni_med_prod());
			pst.setInt(4, p.getStk_prod());
			pst.setDouble(5, p.getPre_unit());
			pst.setInt(6, p.getCod_prod());
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
