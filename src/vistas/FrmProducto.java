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
	private JScrollPane scrollPane;
	private JTable tblProducto;
	private DefaultTableModel modelo;
	String aviso1 = "Ingrese el valor numerico del codigo. Ejm: PR0025 valor numerico->25";
	private JTextField txtPrecio;
	private JComboBox cboUniMed;

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

		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 16, 65, 20);
		contentPane.add(lblNewLabel);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescripcin.setBounds(10, 76, 106, 20);
		contentPane.add(lblDescripcin);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStock.setBounds(10, 138, 65, 14);
		contentPane.add(lblStock);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMarca.setBounds(10, 46, 116, 14);
		contentPane.add(lblMarca);

		JLabel lblUnidadDeMedida = new JLabel("Unidad de medida");
		lblUnidadDeMedida.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUnidadDeMedida.setBounds(10, 107, 139, 14);
		contentPane.add(lblUnidadDeMedida);

		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtCodigo.setBounds(146, 16, 86, 20);
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
		txtStock.setBounds(146, 138, 86, 20);
		contentPane.add(txtStock);

		txtMarca = new JTextField();
		txtMarca.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtMarca.setColumns(10);
		txtMarca.setBounds(145, 46, 167, 20);
		contentPane.add(txtMarca);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 282, 604, 247);
		contentPane.add(scrollPane);

		tblProducto = new JTable();
		tblProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rellenarPro();
			}
		});
		scrollPane.setViewportView(tblProducto);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
				listadoProd();
			}

		});
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrar.setIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/registrar.png")));
		btnRegistrar.setBounds(10, 218, 145, 41);
		contentPane.add(btnRegistrar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Actualizar();
				listadoProd();
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModificar.setIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/modificar.png")));
		btnModificar.setBounds(165, 218, 145, 41);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Eliminar();
				listadoProd();
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEliminar.setIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/eliminar.png")));
		btnEliminar.setBounds(320, 218, 145, 41);
		contentPane.add(btnEliminar);

		modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Marca");
		modelo.addColumn("Descripción");
		modelo.addColumn("Unidad Medida");
		modelo.addColumn("Stock");
		modelo.addColumn("Precio Unitario");

		tblProducto.setModel(modelo);

		JButton btnLimpiar = new JButton("LIMPIAR DATOS");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtCodigo.setText("");
				txtMarca.setText("");
				txtDescripcion.setText("");
				cboUniMed.setSelectedIndex(0);
				txtStock.setText("");
				txtPrecio.setText("");
				txtCodigo.requestFocus();
			}
		});
		btnLimpiar.setBounds(399, 9, 154, 33);
		contentPane.add(btnLimpiar);

		JLabel lblPrecio = new JLabel("Precio unidad");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrecio.setBounds(10, 169, 139, 14);
		contentPane.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(146, 169, 86, 20);
		contentPane.add(txtPrecio);

		cboUniMed = new JComboBox();
		cboUniMed.setModel(new DefaultComboBoxModel(new String[] { "Seleccione", "Unidad", "Caja" }));
		cboUniMed.setBounds(146, 106, 139, 20);
		contentPane.add(cboUniMed);
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

	void rellenarPro() {
		int fila = tblProducto.getSelectedRow();
		txtCodigo.setText(tblProducto.getValueAt(fila, 0).toString());
		txtMarca.setText(tblProducto.getValueAt(fila, 1).toString());
		txtDescripcion.setText(tblProducto.getValueAt(fila, 2).toString());
		cboUniMed.setSelectedIndex(0);
		txtStock.setText(tblProducto.getValueAt(fila, 4).toString());
		txtPrecio.setText(tblProducto.getValueAt(fila, 5).toString());

	}

	void registrar() {
		String marca, Descripcion, UniMedi;
		int Stock;
		double pre_unit;
		marca = leerMarca();
		Descripcion = leerDescripcion();
		UniMedi = leerUnidadMedida();
		Stock = leerStock();
		pre_unit = leerPrecioUnit();

		Producto p = new Producto();
		p.setMarca(marca);
		p.setDesc_prod(Descripcion);
		p.setUni_med_prod(UniMedi);
		p.setStk_prod(Stock);
		p.setPre_unit(pre_unit);

		GestionProducto gp = new GestionProducto();
		int ok = gp.registrar(p);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso4);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso1);
		}

	}

	void Actualizar() {
		String marca, Descripcion, UniMedi;
		int Stock, Codigo;
		double pre_unit;
		Codigo = leerCodigo();
		marca = leerMarca();
		Descripcion = leerDescripcion();
		UniMedi = leerUnidadMedida();
		Stock = leerStock();
		pre_unit = leerPrecioUnit();

		Producto p = new Producto();
		p.setCod_prod(Codigo);
		p.setMarca(marca);
		p.setDesc_prod(Descripcion);
		p.setUni_med_prod(UniMedi);
		p.setStk_prod(Stock);
		p.setPre_unit(pre_unit);
		GestionProducto gp = new GestionProducto();
		if (Codigo >= 1) {

			int ok = gp.actualizar(p);
			if (ok == 0) {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso5);
			} else {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso2);
			}
		} else {
			JOptionPane.showMessageDialog(null, aviso1);

		}

	}

	void Eliminar() {

		int cod_prod = leerCodigo();
		Producto p = new Producto();
		p.setCod_prod(cod_prod);
		if (cod_prod >= 1) {
			GestionProducto gc = new GestionProducto();
			int ok = gc.eliminar(p);
			if (ok == 0) {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso6);
			} else {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso3);
			}

		} else {
			JOptionPane.showMessageDialog(null, aviso1);
		}

	}

	void listadoProd() {
		GestionProducto gp = new GestionProducto();
		ArrayList<Producto> lista = gp.listado();
		modelo.setRowCount(0);
		for (Producto p : lista) {
			Object[] datos = { p.codigochar(), p.getMarca(), p.getDesc_prod(), p.getUni_med_prod(), p.getStk_prod(),
					p.getPre_unit() };
			modelo.addRow(datos);
		}

	}

	int leerCodigo() {
		int c = -1;
		try {
			c = Integer.parseInt(txtCodigo.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, aviso1);
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
			return "C";
		default:
			return "U";
		}
	}

	int leerStock() {
		int c = -1;
		try {
			c = Integer.parseInt(txtStock.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Stock: Ingresar un numero entero positivo");
		}
		return c;

	}

	double leerPrecioUnit() {
		double c = -1;
		try {
			c = Double.parseDouble(txtPrecio.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ingresar un valor numerico positivo en Precio Unidad");
		}
		return c;
	}

}
