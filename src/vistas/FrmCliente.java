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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombres;
	private JTextField txtRuc;
	private JTextField txtTelefono;
	private JTextField txtApellidos;
	private JTextField txtNumerodoc;
	private JTextField txtDireccion;
	private JTextField txtEmail;
	private JTable tblCliente;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private JComboBox<String> cboTipoDocumento;
	private JLabel lblCodigo;
	private JLabel lblNombres;
	private JLabel lblTipoDocumento;
	private JLabel lblRuc;
	private JLabel lblTelefono;
	private JLabel lblApellidos;
	private JLabel lblNumerodoc;
	private JLabel lblDireccion;
	private JLabel lblEmail;
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

		lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigo.setBounds(10, 21, 83, 14);
		contentPane.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtCodigo.setBounds(103, 15, 101, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		lblNombres = new JLabel("Nombres");
		lblNombres.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombres.setBounds(10, 52, 83, 14);
		contentPane.add(lblNombres);

		txtNombres = new JTextField();
		txtNombres.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtNombres.setBounds(103, 46, 173, 20);
		contentPane.add(txtNombres);
		txtNombres.setColumns(10);

		lblTipoDocumento = new JLabel("Tipo Documento");
		lblTipoDocumento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoDocumento.setBounds(10, 83, 135, 14);
		contentPane.add(lblTipoDocumento);

		cboTipoDocumento = new JComboBox<String>();
		cboTipoDocumento.setModel(
				new DefaultComboBoxModel<String>(new String[] {"Seleccione", "DNI", "CARNET DE EXTRANJERIA"}));
		cboTipoDocumento.setFont(new Font("Tahoma", Font.BOLD, 10));
		cboTipoDocumento.setBounds(148, 77, 128, 20);
		contentPane.add(cboTipoDocumento);

		lblRuc = new JLabel("RUC");
		lblRuc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRuc.setBounds(10, 114, 83, 14);
		contentPane.add(lblRuc);

		txtRuc = new JTextField();
		txtRuc.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtRuc.setBounds(103, 108, 173, 20);
		contentPane.add(txtRuc);
		txtRuc.setColumns(10);

		lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelefono.setBounds(10, 145, 83, 14);
		contentPane.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTelefono.setBounds(103, 139, 173, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);

		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApellidos.setBounds(322, 52, 89, 14);
		contentPane.add(lblApellidos);

		txtApellidos = new JTextField();
		txtApellidos.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtApellidos.setBounds(421, 46, 194, 20);
		contentPane.add(txtApellidos);
		txtApellidos.setColumns(10);

		lblNumerodoc = new JLabel("N\u00FAmero documento");
		lblNumerodoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNumerodoc.setBounds(322, 83, 155, 14);
		contentPane.add(lblNumerodoc);

		txtNumerodoc = new JTextField();
		txtNumerodoc.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtNumerodoc.setBounds(472, 77, 143, 20);
		contentPane.add(txtNumerodoc);
		txtNumerodoc.setColumns(10);

		lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDireccion.setBounds(322, 114, 89, 14);
		contentPane.add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtDireccion.setBounds(421, 108, 194, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);

		lblEmail = new JLabel("E-Mail");
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
				rellenar();
			}
		});
		tblCliente.setSurrendersFocusOnKeystroke(true);
		scrollPane.setViewportView(tblCliente);

		btnRegistrar = new JButton("Registrar");
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

		btnModificar = new JButton("Modificar");
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

		btnEliminar = new JButton("Eliminar");
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

		btnLimpiar = new JButton("LIMPIAR DATOS");
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
		txtNombres.setText("");
		txtApellidos.setText("");
		cboTipoDocumento.setSelectedIndex(0);
		txtNumerodoc.setText("");
		txtRuc.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		txtEmail.setText("");
		txtNombres.requestFocus();
	}

	public void listadoClientes() {
		GestionCliente gc = new GestionCliente();
		ArrayList<Cliente> lista = gc.listado();
		modelo.setRowCount(0);
		for (Cliente c : lista) {
			Object[] fila = { c.getCod_cli(), c.getNom_cli(), c.getApe_cli(), tipo_doc_formateado(c.getTip_doc()), c.getNum_doc(),
					c.getRuc_cli(), c.getDirec_cli(), c.getTelef_cli(), c.getEmail_cli(),c.getUsu_creador_cli()
			};
			modelo.addRow(fila);
		}
	}
	
	String tipo_doc_formateado(String ty) {
		if(ty.equals("dni")) return "DNI";
		else return "CARNET DE EXTRANJERIA";
	}

	public void registrarDatos() {
		int usu_creador_cli;
		String nom_cli, ape_cli, tip_doc, num_doc, ruc_cli, direc_cli, telef_cli, email_cli;
		nom_cli = leerNombre();
		ape_cli = leerApellido();
		tip_doc = leerTipoDocumento();
		num_doc = leerNumeroDocumento();
		ruc_cli = leerRuc();
		direc_cli = leerDireccion();
		telef_cli = leerTelefono();
		email_cli = leerEmail();
		usu_creador_cli = 1;
		Cliente c = new Cliente();
		c.setNom_cli(nom_cli);
		c.setApe_cli(ape_cli);
		c.setTip_doc(tip_doc);
		c.setNum_doc(num_doc);
		c.setRuc_cli(ruc_cli);
		c.setDirec_cli(direc_cli);
		c.setTelef_cli(telef_cli);
		c.setEmail_cli(email_cli);
		c.setUsu_creador_cli(usu_creador_cli);
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
		GestionCliente gc = new GestionCliente();
		int ok = gc.eliminar(c);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso6);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso3);
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
		GestionCliente gc = new GestionCliente();
		int ok = gc.actualizar(c);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso4);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso2);
		}

	}

	int leerCodigo() {
		int cod = 0;
		try {
			cod = Integer.parseInt(txtCodigo.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return cod;

	}

	String leerNombre() {
		return txtNombres.getText();
	}

	String leerApellido() {
		return txtApellidos.getText();
	}

	String leerTipoDocumento() {
		int tp = 0;
		tp = cboTipoDocumento.getSelectedIndex();
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

	void rellenar() {
		int fila = tblCliente.getSelectedRow();
		txtCodigo.setText(tblCliente.getValueAt(fila, 0).toString());
		txtNombres.setText(tblCliente.getValueAt(fila, 1).toString());
		txtApellidos.setText(tblCliente.getValueAt(fila, 2).toString());
		cboTipoDocumento.setSelectedItem(tblCliente.getValueAt(fila, 3).toString());
		txtNumerodoc.setText(tblCliente.getValueAt(fila, 4).toString());
		txtRuc.setText(tblCliente.getValueAt(fila, 5).toString());
		txtDireccion.setText(tblCliente.getValueAt(fila, 6).toString());
		txtTelefono.setText(tblCliente.getValueAt(fila, 7).toString());
		txtEmail.setText(tblCliente.getValueAt(fila, 8).toString());

	}
}
