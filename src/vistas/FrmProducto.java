package vistas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import mantenimientos.GestionProducto;
import modelos.Producto;

public class FrmProducto extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JTextField txtMarca;
	private JTextField txtStock;
	private JTextField txtPrecioUnit;
	private JTextField txtPesoUnit;
	private JScrollPane scrollPane;
	private JTable tblProducto;
	private DefaultTableModel modelo;
	private JComboBox<String> cboUniMed;
	private JLabel lblCodigo;
	private JLabel lblDescripcion;
	private JLabel lblStock;
	private JLabel lblMarca;
	private JLabel lblUnidadDeMedida;
	private JLabel lblPrecioUnit;
	private JLabel lblPesoUnit;
	private JButton btnRegistrar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnLimpiar;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmProducto frame = new FrmProducto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmProducto() {
		setFrameIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/producto.png")));
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Producto");
		setBounds(100, 100, 640, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigo.setBounds(10, 16, 65, 20);
		contentPane.add(lblCodigo);

		lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescripcion.setBounds(10, 76, 106, 20);
		contentPane.add(lblDescripcion);

		lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStock.setBounds(10, 138, 65, 14);
		contentPane.add(lblStock);

		lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMarca.setBounds(10, 46, 116, 14);
		contentPane.add(lblMarca);

		lblUnidadDeMedida = new JLabel("Unidad de medida");
		lblUnidadDeMedida.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUnidadDeMedida.setBounds(10, 107, 139, 14);
		contentPane.add(lblUnidadDeMedida);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtCodigo.setBounds(146, 16, 116, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(146, 76, 167, 20);
		contentPane.add(txtDescripcion);

		txtStock = new JTextField();
		txtStock.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtStock.setColumns(10);
		txtStock.setBounds(146, 138, 167, 20);
		contentPane.add(txtStock);

		txtMarca = new JTextField();
		txtMarca.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtMarca.setColumns(10);
		txtMarca.setBounds(145, 46, 167, 20);
		contentPane.add(txtMarca);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 295, 604, 234);
		contentPane.add(scrollPane);

		tblProducto = new JTable();
		tblProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rellenar();
			}
		});
		scrollPane.setViewportView(tblProducto);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
				listadoProd();
			}

		});
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrar.setIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/registrar.png")));
		btnRegistrar.setBounds(10, 243, 145, 41);
		contentPane.add(btnRegistrar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Actualizar();
				listadoProd();
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModificar.setIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/modificar.png")));
		btnModificar.setBounds(165, 243, 145, 41);
		contentPane.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Eliminar();
				listadoProd();
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEliminar.setIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/eliminar.png")));
		btnEliminar.setBounds(320, 243, 145, 41);
		contentPane.add(btnEliminar);

		modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Marca");
		modelo.addColumn("Descripción");
		modelo.addColumn("Unidad Medida");
		modelo.addColumn("Stock");
		modelo.addColumn("Precio (S/.)");
		modelo.addColumn("Peso (Kg.)");

		tblProducto.setModel(modelo);

		btnLimpiar = new JButton("LIMPIAR DATOS");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtCodigo.setText("");
				txtMarca.setText("");
				txtDescripcion.setText("");
				cboUniMed.setSelectedIndex(0);
				txtStock.setText("");
				txtPrecioUnit.setText("");
				txtPesoUnit.setText("");
				txtMarca.requestFocus();
			}
		});
		btnLimpiar.setBounds(399, 9, 154, 33);
		contentPane.add(btnLimpiar);

		lblPrecioUnit = new JLabel("Precio (S/.)");
		lblPrecioUnit.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrecioUnit.setBounds(10, 169, 139, 14);
		contentPane.add(lblPrecioUnit);

		txtPrecioUnit = new JTextField();
		txtPrecioUnit.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtPrecioUnit.setColumns(10);
		txtPrecioUnit.setBounds(146, 169, 167, 20);
		contentPane.add(txtPrecioUnit);

		cboUniMed = new JComboBox<String>();
		cboUniMed.setModel(new DefaultComboBoxModel<String>(new String[] {"Seleccione", "Unidad", "Caja"}));
		cboUniMed.setFont(new Font("Tahoma", Font.BOLD, 10));
		cboUniMed.setBounds(146, 106, 167, 20);
		contentPane.add(cboUniMed);
		
		lblPesoUnit = new JLabel("Peso (Kg.)");
		lblPesoUnit.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPesoUnit.setBounds(10, 197, 139, 20);
		contentPane.add(lblPesoUnit);
		
		txtPesoUnit = new JTextField();
		txtPesoUnit.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtPesoUnit.setColumns(10);
		txtPesoUnit.setBounds(146, 197, 167, 20);
		contentPane.add(txtPesoUnit);
		ajustarAnchoColumnas();
		listadoProd();
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	private void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblProducto.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(3));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(1));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(8));
	}

	void rellenar() {
		int fila = tblProducto.getSelectedRow();
		txtCodigo.setText(tblProducto.getValueAt(fila, 0).toString());
		txtMarca.setText(tblProducto.getValueAt(fila, 1).toString());
		txtDescripcion.setText(tblProducto.getValueAt(fila, 2).toString());
		cboUniMed.setSelectedItem(tblProducto.getValueAt(fila, 3).toString());
		txtStock.setText(tblProducto.getValueAt(fila, 4).toString());
		txtPrecioUnit.setText(tblProducto.getValueAt(fila, 5).toString());
		txtPesoUnit.setText(tblProducto.getValueAt(fila, 6).toString());

	}

	void registrar() {
		String marca, desc_prod, uni_med_prod;
		int usu_creador_prod,stk_prod;
		double pre_unit,pes_unit;
		marca = leerMarca();
		desc_prod = leerDescripcion();
		uni_med_prod = leerUnidadMedida();
		stk_prod = leerStock();
		pre_unit = leerPrecioUnit();
		pes_unit = leerPesoUnit();
		usu_creador_prod=1;

		Producto p = new Producto();
		p.setMarca(marca);
		p.setDesc_prod(desc_prod);
		p.setUni_med_prod(uni_med_prod);
		p.setStk_prod(stk_prod);
		p.setPre_unit(pre_unit);
		p.setPes_unit(pes_unit);
		p.setUsu_creador_prod(usu_creador_prod);

		GestionProducto gp = new GestionProducto();
		int ok = gp.registrar(p);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso4);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso1);
		}

	}

	void Actualizar() {
		String marca, desc_prod, uni_med_prod;
		int cod_prod,stk_prod;
		double pre_unit,pes_unit;
		cod_prod = leerCodigo();
		marca = leerMarca();
		desc_prod = leerDescripcion();
		uni_med_prod = leerUnidadMedida();
		stk_prod = leerStock();
		pre_unit = leerPrecioUnit();
		pes_unit = leerPesoUnit();

		Producto p = new Producto();
		p.setCod_prod(cod_prod);
		p.setMarca(marca);
		p.setDesc_prod(desc_prod);
		p.setUni_med_prod(uni_med_prod);
		p.setStk_prod(stk_prod);
		p.setPre_unit(pre_unit);
		p.setPes_unit(pes_unit);
		GestionProducto gp = new GestionProducto();
			int ok = gp.actualizar(p);
			if (ok == 0) {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso5);
			} else {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso2);
			}

	}

	void Eliminar() {

		int cod_prod = leerCodigo();
		Producto p = new Producto();
		p.setCod_prod(cod_prod);

			GestionProducto gc = new GestionProducto();
			int ok = gc.eliminar(p);
			if (ok == 0) {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso6);
			} else {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso3);
			}

	}

	void listadoProd() {
		GestionProducto gp = new GestionProducto();
		ArrayList<Producto> lista = gp.listado();
		modelo.setRowCount(0);
		for (Producto p : lista) {
			Object[] datos = { p.getCod_prod(), p.getMarca(), p.getDesc_prod(), uni_med_formateado(p.getUni_med_prod()), p.getStk_prod(),
					p.getPre_unit(),p.getPes_unit(),p.getUsu_creador_prod() };
			modelo.addRow(datos);
		}

	}
	
	String uni_med_formateado(String u) {
		if(u.equals("u")) return "Unidad";
		else return "Caja";
	}
	
	int leerCodigo() {
		int c = 0;
		try {
			c = Integer.parseInt(txtCodigo.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return c;
	}

	String leerMarca() {
		return txtMarca.getText();
	}

	String leerDescripcion() {
		return txtDescripcion.getText();
	}

	String leerUnidadMedida() {
		int tp = 0;
		tp = cboUniMed.getSelectedIndex();
		switch (tp) {
		case 2:
			return "c";
		default:
			return "u";
		}
	}

	int leerStock() {
		int c = 0;
		try {
			c = Integer.parseInt(txtStock.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Stock: Ingresar un numero entero positivo");
		}
		return c;

	}

	double leerPrecioUnit() {
		double c = 0;
		try {
			c = Double.parseDouble(txtPrecioUnit.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ingresar un valor numerico positivo en Precio");
		}
		return c;
	}
	
	double leerPesoUnit() {
		double c = 0;
		try {
			c = Double.parseDouble(txtPesoUnit.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ingresar un valor numerico positivo en Peso");
		}
		return c;
	}
}
