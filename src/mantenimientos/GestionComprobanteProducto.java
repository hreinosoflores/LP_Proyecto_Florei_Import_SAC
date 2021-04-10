package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.ComprobanteProductoInterface;
import modelos.ComprobanteProducto;
import utils.MySQLConexion;

public class GestionComprobanteProducto implements ComprobanteProductoInterface {

	@Override
	public int registrar(ComprobanteProducto cpr) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			con.setAutoCommit(false);
			String sql = "insert into Detalle_Guia_Comprobante_Producto" + "(num_comp,num_gui,cod_prod,cant) "
					+ "values(?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, cpr.getNum_comp());
			pst.setInt(2, cpr.getNum_gui());
			pst.setInt(3, cpr.getCod_pro());
			pst.setInt(4, cpr.getCant());

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
	public ArrayList<ComprobanteProducto> listado() {
		ArrayList<ComprobanteProducto> lista = new ArrayList<>();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			pst = con.prepareStatement("select * from detalle_guia_comprobante_producto");
			rs = pst.executeQuery();
			while (rs.next()) {
				ComprobanteProducto cpr = new ComprobanteProducto();
				cpr.setNum_comp(rs.getInt(1));
				cpr.setNum_gui(rs.getInt(2));
				cpr.setCod_pro(rs.getInt(3));
				cpr.setCant(rs.getInt(4));

				lista.add(cpr);
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
	public int eliminar(ComprobanteProducto cpr) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from detalle_guia_comprobante_producto"
					+ " where (num_comp,num_gui,cod_prod) = (?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, cpr.getNum_comp());
			pst.setInt(2, cpr.getNum_gui());
			pst.setInt(3, cpr.getCod_pro());
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
	public int actualizar(ComprobanteProducto cpr) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			pst = con.prepareStatement("update detalle_guia_comprobante_producto" + " set cant = ?"
					+ " where (num_comp,num_gui,cod_prod) = (?,?,?)");

			pst.setInt(1, cpr.getCant());
			pst.setInt(2, cpr.getNum_comp());
			pst.setInt(3, cpr.getNum_gui());
			pst.setInt(4, cpr.getCod_pro());
			rs = pst.executeUpdate();

			} catch (Exception e) {
			
			try {
				
				con.rollback();
				JOptionPane.showMessageDialog(null, "Error en la sentencia: " + e.getMessage());
				
			} catch (Exception ex) {
				
				JOptionPane.showMessageDialog(null, "Error en el rollback");
				
			}
		}  finally {
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
