package vistas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import mantenimientos.GestionComprobanteProducto;
import modelos.ComprobanteProducto;

public class FrmComprobanteProducto extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtCantidad;
	private JTable tblComprobanteProducto;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private JTextField txtComprobante;
	private JTextField txtGuia;
	private JTextField txtProducto;
	String aviso1 = "Ingrese el valor numerico. Ejm: COM0025 valor numerico->25";
	String aviso2 = "Ingrese el valor numerico. Ejm: N0025 valor numerico->25";
	String aviso3 = "Ingrese el valor numerico. Ejm: PR0025 valor numerico->25";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmComprobanteProducto frame = new FrmComprobanteProducto();
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
	public FrmComprobanteProducto() {
		setFrameIcon(new ImageIcon(FrmComprobanteProducto.class.getResource("/iconos/comprobante.png")));
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Comprobante-Producto");
		setBounds(100, 100, 640, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnCodigos = new JPanel();
		pnCodigos.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				"C\u00F3digos (Valor num\u00E9rico)", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnCodigos.setBounds(10, 14, 323, 135);
		contentPane.add(pnCodigos);
		pnCodigos.setLayout(null);

		JLabel lblNmeroComprobante = new JLabel("N\u00FAmero Comprobante");
		lblNmeroComprobante.setBounds(10, 25, 172, 22);
		pnCodigos.add(lblNmeroComprobante);
		lblNmeroComprobante.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblNmeroGuia = new JLabel("N\u00FAmero Guia");
		lblNmeroGuia.setBounds(10, 58, 116, 20);
		pnCodigos.add(lblNmeroGuia);
		lblNmeroGuia.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblCodigoDelProducto = new JLabel("Codigo del producto");
		lblCodigoDelProducto.setBounds(10, 89, 172, 20);
		pnCodigos.add(lblCodigoDelProducto);
		lblCodigoDelProducto.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtComprobante = new JTextField();
		txtComprobante.setBounds(182, 28, 132, 20);
		pnCodigos.add(txtComprobante);
		txtComprobante.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtComprobante.setColumns(10);

		txtGuia = new JTextField();
		txtGuia.setBounds(182, 60, 132, 20);
		pnCodigos.add(txtGuia);
		txtGuia.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtGuia.setColumns(10);

		txtProducto = new JTextField();
		txtProducto.setBounds(182, 91, 132, 20);
		pnCodigos.add(txtProducto);
		txtProducto.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtProducto.setColumns(10);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCantidad.setBounds(377, 42, 116, 20);
		contentPane.add(lblCantidad);

		txtCantidad = new JTextField();
		txtCantidad.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtCantidad.setBounds(460, 45, 132, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrar();
				listar();
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrar.setIcon(new ImageIcon(FrmComprobanteProducto.class.getResource("/iconos/registrar.png")));
		btnRegistrar.setBounds(10, 166, 145, 41);
		contentPane.add(btnRegistrar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
				listar();
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModificar.setIcon(new ImageIcon(FrmComprobanteProducto.class.getResource("/iconos/modificar.png")));
		btnModificar.setBounds(169, 166, 145, 41);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
				listar();
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEliminar.setIcon(new ImageIcon(FrmComprobanteProducto.class.getResource("/iconos/eliminar.png")));
		btnEliminar.setBounds(322, 166, 145, 41);
		contentPane.add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(169, 218, 298, 316);
		contentPane.add(scrollPane);

		tblComprobanteProducto = new JTable();
		tblComprobanteProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rellenar();
			}
		});
		scrollPane.setViewportView(tblComprobanteProducto);

		modelo = new DefaultTableModel();
		modelo.addColumn("Comprobante");
		modelo.addColumn("Guia");
		modelo.addColumn("Producto");
		modelo.addColumn("Cantidad");
		tblComprobanteProducto.setModel(modelo);

		JButton btnLimpiar = new JButton("LIMPIAR DATOS");
		btnLimpiar.setFont(new Font("Arial", Font.PLAIN, 18));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
			}
		});
		btnLimpiar.setBounds(397, 88, 172, 47);
		contentPane.add(btnLimpiar);

		ajustarAnchoColumnas();
		listar();
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	private void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblComprobanteProducto.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(8));
	}

	void limpiar() {
		txtComprobante.setText("");
		txtGuia.setText("");
		txtProducto.setText("");
		;
		txtCantidad.setText("");
		txtCantidad.requestFocus();
	}

	void rellenar() {
		int fila = tblComprobanteProducto.getSelectedRow();
		txtComprobante.setText(tblComprobanteProducto.getValueAt(fila, 0).toString());
		txtGuia.setText(tblComprobanteProducto.getValueAt(fila, 1).toString());
		txtProducto.setText(tblComprobanteProducto.getValueAt(fila, 2).toString());
		txtCantidad.setText(tblComprobanteProducto.getValueAt(fila, 3).toString());
	}

	void listar() {
		GestionComprobanteProducto gcpr = new GestionComprobanteProducto();
		ArrayList<ComprobanteProducto> lista = gcpr.listado();
		modelo.setRowCount(0);
		for (ComprobanteProducto cpr : lista) {
			Object[] fila = { cpr.getNum_comp(), cpr.getNum_gui(), cpr.getCod_pro(), cpr.getCant(), };
			modelo.addRow(fila);
		}
	}

	void registrar() {
		int num_comp, num_gui, cod_pro, cant;
		num_comp = leerNumCom();
		num_gui = leerNumGuia();
		cod_pro = leerCodPro();
		cant = leerCantidad();

		ComprobanteProducto cpr = new ComprobanteProducto();
		cpr.setNum_comp(num_comp);
		cpr.setNum_gui(num_gui);
		cpr.setCod_pro(cod_pro);
		cpr.setCant(cant);

		GestionComprobanteProducto gcpr = new GestionComprobanteProducto();
		int ok = gcpr.registrar(cpr);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso4);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso1);
		}

	}

	void modificar() {
		int num_comp, num_gui, cod_pro, cant;
		num_comp = leerNumCom();
		num_gui = leerNumGuia();
		cod_pro = leerCodPro();
		cant = leerCantidad();

		ComprobanteProducto cpr = new ComprobanteProducto();
		cpr.setNum_comp(num_comp);
		cpr.setNum_gui(num_gui);
		cpr.setCod_pro(cod_pro);
		cpr.setCant(cant);

		GestionComprobanteProducto gcpr = new GestionComprobanteProducto();
		int ok = gcpr.actualizar(cpr);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso5);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso2);
		}
	}

	void eliminar() {
		int num_comp, num_gui, cod_pro;
		num_comp = leerNumCom();
		num_gui = leerNumGuia();
		cod_pro = leerCodPro();
		ComprobanteProducto cpr = new ComprobanteProducto();
		cpr.setNum_comp(num_comp);
		cpr.setNum_gui(num_gui);
		cpr.setCod_pro(cod_pro);
		GestionComprobanteProducto gcpr = new GestionComprobanteProducto();
		int ok = gcpr.eliminar(cpr);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso6);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso3);
		}
	}

	int leerNumCom() {
		int c = -1;
		try {
			c = Integer.parseInt(txtComprobante.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, aviso1);
		}
		return c;

	}

	int leerNumGuia() {
		int c = -1;
		try {
			c = Integer.parseInt(txtGuia.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, aviso2);
		}
		return c;
	}

	int leerCodPro() {
		int c = -1;
		try {
			c = Integer.parseInt(txtProducto.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, aviso3);
		}
		return c;
	}

	int leerCantidad() {
		int c = -1;
		try {
			c = Integer.parseInt(txtCantidad.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Entrada inválida. Ingrese un número entero");
		}
		return c;

	}

}
