package vistas;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import mantenimientos.GestionEmpresa;
import modelos.Empresa;

public class FrmEmpresa extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtDireccion;
	private JTextField txtEmail;
	private JTextField txtRazonso;
	private JTextField txtRuc;
	private JTextField txtTelefono;
	private JTable tblEmpresa;
	private JScrollPane scrollPane;
	private DefaultTableModel modelo;
	String aviso1 = "Ingrese el valor numerico del codigo. Ejm: EMP0025 valor numerico->25";
	private JTextField txtCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEmpresa frame = new FrmEmpresa();
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
	public FrmEmpresa() {
		setFrameIcon(new ImageIcon(FrmEmpresa.class.getResource("/iconos/empresa.png")));
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Empresa");
		setBounds(100, 100, 640, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 16, 85, 20);
		contentPane.add(lblNewLabel);

		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtCodigo.setBounds(98, 10, 108, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Direccion");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 59, 85, 14);
		contentPane.add(lblNewLabel_1);

		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtDireccion.setBounds(98, 53, 108, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(10, 104, 60, 14);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtEmail.setBounds(98, 98, 111, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblRaznSocial = new JLabel("Raz\u00F3n social");
		lblRaznSocial.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRaznSocial.setBounds(293, 16, 106, 14);
		contentPane.add(lblRaznSocial);

		txtRazonso = new JTextField();
		txtRazonso.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtRazonso.setBounds(397, 10, 169, 20);
		contentPane.add(txtRazonso);
		txtRazonso.setColumns(10);

		JLabel lblRuc = new JLabel("Ruc");
		lblRuc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRuc.setBounds(293, 59, 46, 14);
		contentPane.add(lblRuc);

		txtRuc = new JTextField();
		txtRuc.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtRuc.setBounds(397, 58, 116, 20);
		contentPane.add(txtRuc);
		txtRuc.setColumns(10);

		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelfono.setBounds(293, 104, 85, 14);
		contentPane.add(lblTelfono);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTelefono.setBounds(397, 98, 116, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 245, 604, 241);
		contentPane.add(scrollPane);

		tblEmpresa = new JTable();
		tblEmpresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rellenarformulario();
			}
		});
		scrollPane.setViewportView(tblEmpresa);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
				listadoEmpleados();
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrar.setIcon(new ImageIcon(FrmEmpresa.class.getResource("/iconos/registrar.png")));
		btnRegistrar.setBounds(10, 193, 145, 41);
		contentPane.add(btnRegistrar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificarDatos();
				listadoEmpleados();
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModificar.setIcon(new ImageIcon(FrmEmpresa.class.getResource("/iconos/modificar.png")));
		btnModificar.setBounds(165, 196, 145, 38);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminardatos();
				listadoEmpleados();
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEliminar.setIcon(new ImageIcon(FrmEmpresa.class.getResource("/iconos/eliminar.png")));
		btnEliminar.setBounds(318, 196, 145, 38);
		contentPane.add(btnEliminar);

		modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Razón Social");
		modelo.addColumn("Dirección");
		modelo.addColumn("RUC");
		modelo.addColumn("Email");
		modelo.addColumn("Teléfono");
		modelo.addColumn("Cliente");
		tblEmpresa.setModel(modelo);

		JLabel lblCliente = new JLabel("Codigo Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCliente.setBounds(10, 145, 145, 20);
		contentPane.add(lblCliente);

		txtCliente = new JTextField();
		txtCliente.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtCliente.setColumns(10);
		txtCliente.setBounds(142, 148, 111, 20);
		contentPane.add(txtCliente);

		ajustarAnchoColumnas();
		listadoEmpleados();
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	private void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblEmpresa.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(2));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(5).setPreferredWidth(anchoColumna(8));
	}

	public void registrar() {
		int cod_cli;
		String raz_soc_emp, direc_emp, ruc_emp, email_emp, telefono;
		raz_soc_emp = leerRazSocial();
		direc_emp = leerDireccion();
		ruc_emp = leerRuc();
		email_emp = leerEmail();
		telefono = leerTelefono();
		cod_cli = leerCliente();
		Empresa e = new Empresa();
		e.setRaz_soc_emp(raz_soc_emp);
		e.setDirec_emp(direc_emp);
		e.setRuc_emp(ruc_emp);
		e.setEmail_emp(email_emp);
		e.setTelefono(telefono);
		e.setCod_cli(cod_cli);
		GestionEmpresa ge = new GestionEmpresa();
		int ok = ge.registrar(e);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso4);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso1);
		}

	}

	void eliminardatos() {

		int cod_emp = leerCodigo();
		Empresa e = new Empresa();
		e.setCod_emp(cod_emp);
		if (cod_emp >= 1) {
			GestionEmpresa gc = new GestionEmpresa();
			int ok = gc.eliminar(e);
			if (ok == 0) {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso6);
			} else {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso3);
			}

		} else {
			JOptionPane.showMessageDialog(null, aviso1);
		}

	}

	public void modificarDatos() {
		int cod_emp, cod_cli;
		String raz_soc_emp, direc_emp, ruc_emp, email_emp, telefono;
		cod_emp = leerCodigo();
		raz_soc_emp = leerRazSocial();
		direc_emp = leerDireccion();
		ruc_emp = leerRuc();
		email_emp = leerEmail();
		telefono = leerTelefono();
		cod_cli = leerCliente();
		Empresa e = new Empresa();
		e.setCod_emp(cod_emp);
		e.setRaz_soc_emp(raz_soc_emp);
		e.setDirec_emp(direc_emp);
		e.setRuc_emp(ruc_emp);
		e.setEmail_emp(email_emp);
		e.setTelefono(telefono);
		e.setCod_cli(cod_cli);
		GestionEmpresa ge = new GestionEmpresa();
		if (cod_emp >= 1) {
			int ok = ge.actualizar(e);
			if (ok == 0) {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso5);
			} else {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso2);
			}
		} else {
			JOptionPane.showMessageDialog(null, aviso1);

		}
	}

	public void listadoEmpleados() {
		GestionEmpresa ge = new GestionEmpresa();
		ArrayList<Empresa> lista = ge.listado();
		modelo.setRowCount(0);
		for (Empresa em : lista) {
			Object[] fila = { em.codigochar(), em.getRaz_soc_emp(), em.getDirec_emp(), em.getRuc_emp(),
					em.getEmail_emp(), em.getTelefono(), em.getCod_cli()

			};
			modelo.addRow(fila);

		}
	}

	int leerCodigo() {
		int cod = -1;
		try {
			cod = Integer.parseInt(txtCodigo.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, aviso1);
		}
		return cod;

	}

	String leerRazSocial() {
		String rs = null;
		rs = txtRazonso.getText();
		return rs;
	}

	String leerDireccion() {
		String d = null;
		d = txtDireccion.getText();
		return d;
	}

	String leerRuc() {
		String r = null;
		r = txtRuc.getText().trim();
		if (r.matches("([0-9]{11})?")) {
			return r;
		} else {
			JOptionPane.showMessageDialog(null, "Se necesitan 11 digitos");
			return null;
		}

	}

	String leerEmail() {
		String e = null;
		e = txtEmail.getText().trim();
		if (e.matches("([[a-zA-Z0-9][_.]]{1,20}@[a-z]{1,15}.[a-z]{1,3}.*[a-z]{1,3})?")) {
			return e;
		} else {
			JOptionPane.showMessageDialog(null,
					"Direccion de correo invalida. Tome como referencia examen@dominio.com ");
			return null;
		}

	}

	String leerTelefono() {
		String e = null;
		e = txtTelefono.getText().trim();
		if (e.matches("([0-9]{9})?")) {
			return e;
		} else {
			JOptionPane.showMessageDialog(null, "Ingrese su número de celular de 9 digitos");
			return null;
		}

	}

	int leerCliente() {
		int c = -1;
		try {
			c = Integer.parseInt(txtCliente.getText().trim());
			;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Ingrese el valor numerico del codigo Cliente. Ejm: CLI0025 valor numerico->25");
		}
		return c;
	}

	public void rellenarformulario() {
		int fila = tblEmpresa.getSelectedRow();
		txtCodigo.setText(tblEmpresa.getValueAt(fila, 0).toString());
		txtRazonso.setText(tblEmpresa.getValueAt(fila, 1).toString());
		txtDireccion.setText(tblEmpresa.getValueAt(fila, 2).toString());
		txtRuc.setText(tblEmpresa.getValueAt(fila, 3).toString());
		txtEmail.setText(tblEmpresa.getValueAt(fila, 4).toString());
		txtTelefono.setText(tblEmpresa.getValueAt(fila, 5).toString());

	}

}
