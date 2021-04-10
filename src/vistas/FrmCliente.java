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

import mantenimientos.GestionCliente;
import modelos.Cliente;

public class FrmCliente extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtRuc;
	private JTextField txtTelefono;
	private JTextField txtApellido;
	private JTextField txtNumerodoc;
	private JTextField txtDireccion;
	private JTextField txtEmail;
	private JTable tblCliente;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private JComboBox cboTipoDoc;
	String aviso1 = "Ingrese el valor numerico del codigo. Ejm: CLI0025 valor numerico->25";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCliente frame = new FrmCliente();
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
	public FrmCliente() {
		setFrameIcon(new ImageIcon(FrmCliente.class.getResource("/iconos/clientedestinatario.png")));
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Cliente");
		setBounds(100, 100, 640, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 21, 83, 14);
		contentPane.add(lblNewLabel);

		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtCodigo.setBounds(103, 15, 173, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nombres");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 52, 83, 14);
		contentPane.add(lblNewLabel_1);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtNombre.setBounds(103, 46, 173, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblTipoDocumento = new JLabel("Tipo Documento");
		lblTipoDocumento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoDocumento.setBounds(10, 83, 135, 14);
		contentPane.add(lblTipoDocumento);

		cboTipoDoc = new JComboBox();
		cboTipoDoc.setModel(new DefaultComboBoxModel(new String[] { "Seleccione\t", "DNI", "CARNET DE EXTRANJERIA" }));
		cboTipoDoc.setFont(new Font("Tahoma", Font.BOLD, 10));
		cboTipoDoc.setBounds(148, 77, 128, 20);
		contentPane.add(cboTipoDoc);

		JLabel lblRuc = new JLabel("RUC");
		lblRuc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRuc.setBounds(10, 114, 83, 14);
		contentPane.add(lblRuc);

		txtRuc = new JTextField();
		txtRuc.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtRuc.setBounds(103, 108, 173, 20);
		contentPane.add(txtRuc);
		txtRuc.setColumns(10);

		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelfono.setBounds(10, 145, 83, 14);
		contentPane.add(lblTelfono);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTelefono.setBounds(103, 139, 173, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(322, 52, 89, 14);
		contentPane.add(lblNewLabel_2);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtApellido.setBounds(421, 46, 194, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);

		JLabel lblNmeroDocumento = new JLabel("N\u00FAmero documento");
		lblNmeroDocumento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNmeroDocumento.setBounds(322, 83, 155, 14);
		contentPane.add(lblNmeroDocumento);

		txtNumerodoc = new JTextField();
		txtNumerodoc.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtNumerodoc.setBounds(472, 77, 143, 20);
		contentPane.add(txtNumerodoc);
		txtNumerodoc.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Direcci\u00F3n");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(322, 114, 89, 14);
		contentPane.add(lblNewLabel_3);

		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtDireccion.setBounds(421, 108, 194, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(322, 145, 89, 14);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtEmail.setBounds(421, 139, 194, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 230, 605, 217);
		contentPane.add(scrollPane);

		tblCliente = new JTable();
		tblCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rellenarformulario();
			}
		});
		tblCliente.setSurrendersFocusOnKeystroke(true);
		scrollPane.setViewportView(tblCliente);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrarDatos();
				listadoClientes();
			}
		});
		btnRegistrar.setIcon(new ImageIcon(FrmCliente.class.getResource("/iconos/registrar.png")));
		btnRegistrar.setBounds(10, 185, 145, 34);
		contentPane.add(btnRegistrar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarDatos();
				listadoClientes();
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModificar.setIcon(new ImageIcon(FrmCliente.class.getResource("/iconos/modificar.png")));
		btnModificar.setBounds(165, 185, 145, 34);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				eliminardatos();
				listadoClientes();
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEliminar.setIcon(new ImageIcon(FrmCliente.class.getResource("/iconos/eliminar.png")));
		btnEliminar.setBounds(320, 185, 145, 34);
		contentPane.add(btnEliminar);

		modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Nombres");
		modelo.addColumn("Apellidos");
		modelo.addColumn("Tipo");
		modelo.addColumn("Número");
		modelo.addColumn("RUC");
		modelo.addColumn("Dirección");
		modelo.addColumn("Teléfono");
		modelo.addColumn("Email");
		tblCliente.setModel(modelo);

		JButton btnLimpiar = new JButton("LIMPIAR DATOS");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setBounds(322, 11, 155, 30);
		contentPane.add(btnLimpiar);

		ajustarAnchoColumnas();

		listadoClientes();

	}

	/*----------------------------------------------------------------*/
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	private void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblCliente.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(6));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(6));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(8));
	}

	public void limpiar() {
		txtCodigo.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		cboTipoDoc.setSelectedIndex(0);
		txtNumerodoc.setText("");
		txtRuc.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		txtEmail.setText("");
		txtCodigo.requestFocus();
	}

	public void listadoClientes() {
		GestionCliente gc = new GestionCliente();
		ArrayList<Cliente> lista = gc.listado();
		modelo.setRowCount(0);
		for (Cliente c : lista) {
			Object[] fila = { c.codigochar(), c.getNom_cli(), c.getApe_cli(), c.getTip_doc(), c.getNum_doc(),
					c.getRuc_cli(), c.getDirec_cli(), c.getTelef_cli(), c.getEmail_cli()

			};
			modelo.addRow(fila);
		}

	}

	public void registrarDatos() {
		String nom_cli, ape_cli, tip_doc, num_doc, ruc_cli, direc_cli, telef_cli, email_cli;
		nom_cli = leerNombre();
		ape_cli = leerApellido();
		tip_doc = leerTipoDocumento();
		num_doc = leerNumeroDocumento();
		ruc_cli = leerRuc();
		direc_cli = leerDireccion();
		telef_cli = leerTelefono();
		email_cli = leerEmail();
		Cliente c = new Cliente();
		c.setNom_cli(nom_cli);
		c.setApe_cli(ape_cli);
		c.setTip_doc(tip_doc);
		c.setNum_doc(num_doc);
		c.setRuc_cli(ruc_cli);
		c.setDirec_cli(direc_cli);
		c.setTelef_cli(telef_cli);
		c.setEmail_cli(email_cli);
		GestionCliente gc = new GestionCliente();
		int ok = gc.registrar(c);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso4);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso1);
		}
	}

	void eliminardatos() {

		int cod_cli = leerCodigo();
		Cliente c = new Cliente();
		c.setCod_cli(cod_cli);

		if (cod_cli >= 1) {
			GestionCliente gc = new GestionCliente();
			int ok = gc.eliminar(c);
			if (ok == 0) {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso6);
			} else {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso3);
			}

		} else {
			JOptionPane.showMessageDialog(null, aviso1);
		}

	}

	void modificarDatos() {
		int cod_cli;
		String nom_cli, ape_cli, tip_doc, num_doc, ruc_cli, direc_cli, telef_cli, email_cli;
		cod_cli = leerCodigo();
		nom_cli = leerNombre();
		ape_cli = leerApellido();
		tip_doc = leerTipoDocumento();
		num_doc = leerNumeroDocumento();
		ruc_cli = leerRuc();
		direc_cli = leerDireccion();
		telef_cli = leerTelefono();
		email_cli = leerEmail();

		Cliente c = new Cliente();
		c.setCod_cli(cod_cli);
		c.setNom_cli(nom_cli);
		c.setApe_cli(ape_cli);
		c.setTip_doc(tip_doc);
		c.setNum_doc(num_doc);
		c.setRuc_cli(ruc_cli);
		c.setDirec_cli(direc_cli);
		c.setTelef_cli(telef_cli);
		c.setEmail_cli(email_cli);
		if (cod_cli >= 1) {
			GestionCliente gc = new GestionCliente();
			int ok = gc.actualizar(c);
			if (ok == 0) {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso4);
			} else {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso2);
			}

		} else {
			JOptionPane.showMessageDialog(null, aviso1);
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

	String leerNombre() {
		return txtNombre.getText();
	}

	String leerApellido() {
		return txtApellido.getText();
	}

	String leerTipoDocumento() {
		int tp = 0;
		tp = cboTipoDoc.getSelectedIndex();
		switch (tp) {
		case 2:
			return "cex";
		default:
			return "dni";
		}

	}

	String leerNumeroDocumento() {
		String nd = null;
		nd = txtNumerodoc.getText().trim();
		if (nd.matches("[0-9]{8,12}")) {
			return nd;
		} else {
			JOptionPane.showMessageDialog(null, "Numero Documento incorrecto: [0-9]{1,20}");
			return null;
		}
	}

	String leerRuc() {
		String c = null;
		c = txtRuc.getText().trim();
		if (c.matches("([0-9]{11})?")) {
			return c;
		} else {
			JOptionPane.showMessageDialog(null, "RUC incorrecto: Ingrese 11 digitos");
			return null;
		}
	}

	String leerDireccion() {
		return txtDireccion.getText();

	}

	String leerTelefono() {
		String t = null;
		t = txtTelefono.getText().trim();
		if (t.matches("([0-9]{9})?")) {
			return t;
		} else {
			JOptionPane.showMessageDialog(null, "Teléfono incorrecto: Ingrese 9 digitos");
			return null;
		}
	}

	String leerEmail() {
		String e = null;
		e = txtEmail.getText().trim();
		if (e.matches("([[a-zA-Z0-9][_.]]{1,20}@[a-z]{5,10}.[a-z]{2,3})?")) {
			return e;
		} else {
			JOptionPane.showMessageDialog(null, "Ingrese una direccion email correcta. Ejm: ejemplo@outlook.com");
			return null;
		}
	}

	void rellenarformulario() {
		int fila = tblCliente.getSelectedRow();

		txtCodigo.setText(tblCliente.getValueAt(fila, 0).toString());
		txtNombre.setText(tblCliente.getValueAt(fila, 1).toString());
		txtApellido.setText(tblCliente.getValueAt(fila, 2).toString());
		cboTipoDoc.setSelectedItem(tblCliente.getValueAt(fila, 3));
		txtNumerodoc.setText(tblCliente.getValueAt(fila, 4).toString());
		txtRuc.setText(tblCliente.getValueAt(fila, 5).toString());
		txtDireccion.setText(tblCliente.getValueAt(fila, 6).toString());
		txtTelefono.setText(tblCliente.getValueAt(fila, 7).toString());
		txtEmail.setText(tblCliente.getValueAt(fila, 8).toString());

	}
}
