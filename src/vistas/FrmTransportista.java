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

import mantenimientos.GestionTransportista;
import modelos.Transportista;

public class FrmTransportista extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtRuc;
	private JTextField txtLicencia;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JButton btnRegistrar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnLimpiar;
	private JTable tblTransportista;
	private JScrollPane scrollPane;
	private DefaultTableModel modelo;
	private JLabel lblCodigo;
	private JLabel lblNombres;
	private JLabel lblDireccion;
	private JLabel lblRUC;
	private JLabel lblLicencia;
	private JLabel lblApellidos;
	private JLabel lblTelefono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmTransportista frame = new FrmTransportista();
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
	public FrmTransportista() {
		setFrameIcon(new ImageIcon(FrmTransportista.class.getResource("/iconos/transportista.png")));
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Transportista");
		setBounds(100, 100, 642, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigo.setBounds(10, 17, 74, 14);
		contentPane.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtCodigo.setBounds(102, 11, 145, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		lblNombres = new JLabel("Nombres");
		lblNombres.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombres.setBounds(10, 54, 74, 14);
		contentPane.add(lblNombres);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtNombre.setBounds(102, 48, 145, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDireccion.setBounds(10, 91, 74, 14);
		contentPane.add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtDireccion.setBounds(102, 85, 145, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);

		lblRUC = new JLabel("RUC");
		lblRUC.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRUC.setBounds(10, 131, 62, 14);
		contentPane.add(lblRUC);

		txtRuc = new JTextField();
		txtRuc.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtRuc.setBounds(102, 125, 145, 20);
		contentPane.add(txtRuc);
		txtRuc.setColumns(10);

		lblLicencia = new JLabel("Nro Licencia");
		lblLicencia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLicencia.setBounds(308, 131, 124, 14);
		contentPane.add(lblLicencia);

		txtLicencia = new JTextField();
		txtLicencia.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtLicencia.setBounds(442, 125, 154, 20);
		contentPane.add(txtLicencia);
		txtLicencia.setColumns(10);

		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApellidos.setBounds(308, 54, 92, 14);
		contentPane.add(lblApellidos);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtApellido.setBounds(442, 54, 154, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);

		lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelefono.setBounds(308, 91, 92, 14);
		contentPane.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTelefono.setBounds(442, 91, 154, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 238, 606, 204);
		contentPane.add(scrollPane);

		tblTransportista = new JTable();
		tblTransportista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rellenarTrans();
			}
		});
		scrollPane.setViewportView(tblTransportista);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
				listadoTrans();
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrar.setIcon(new ImageIcon(FrmTransportista.class.getResource("/iconos/registrar.png")));
		btnRegistrar.setBounds(10, 176, 145, 33);
		contentPane.add(btnRegistrar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar();
				listadoTrans();
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModificar.setIcon(new ImageIcon(FrmTransportista.class.getResource("/iconos/modificar.png")));
		btnModificar.setBounds(165, 176, 145, 33);
		contentPane.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
				listadoTrans();
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEliminar.setIcon(new ImageIcon(FrmTransportista.class.getResource("/iconos/eliminar.png")));
		btnEliminar.setBounds(320, 176, 145, 33);
		contentPane.add(btnEliminar);

		modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Nombres");
		modelo.addColumn("Apellidos");
		modelo.addColumn("Dirección");
		modelo.addColumn("Teléfono");
		modelo.addColumn("RUC");
		modelo.addColumn("Número Licencia");
		tblTransportista.setModel(modelo);

		btnLimpiar = new JButton("LIMPIAR DATOS");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtCodigo.setText("");
				txtNombre.setText("");
				txtApellido.setText("");
				txtDireccion.setText("");
				txtTelefono.setText("");
				txtRuc.setText("");
				txtLicencia.setText("");
				txtNombre.requestFocus();
			}
		});
		btnLimpiar.setBounds(308, 10, 154, 33);
		contentPane.add(btnLimpiar);

		listadoTrans();
		ajustarAnchoColumnas();
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	private void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblTransportista.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(5).setPreferredWidth(anchoColumna(8));

	}

	void rellenarTrans() {
		int fila = tblTransportista.getSelectedRow();
		txtCodigo.setText(tblTransportista.getValueAt(fila, 0).toString());
		txtNombre.setText(tblTransportista.getValueAt(fila, 1).toString());
		txtApellido.setText(tblTransportista.getValueAt(fila, 2).toString());
		txtDireccion.setText(tblTransportista.getValueAt(fila, 3).toString());
		txtTelefono.setText(tblTransportista.getValueAt(fila, 4).toString());
		txtRuc.setText(tblTransportista.getValueAt(fila, 5).toString());
		txtLicencia.setText(tblTransportista.getValueAt(fila, 6).toString());

	}

	void registrar() {
		int usu_creador_trans;
		String Nombre, Apellido, Direccion, Telefono, Ruc, NroLic;

		Nombre = leerNombre();
		Apellido = leerApellido();
		Direccion = leerDireccion();
		Telefono = leerTelefono();
		Ruc = leerRuc();
		NroLic = leerNumeeroLic();
		usu_creador_trans = 1;

		Transportista t = new Transportista();
		t.setNom_trans(Nombre);
		t.setApe_trans(Apellido);
		t.setDirec_trans(Direccion);
		t.setTelf_trans(Telefono);
		t.setRuc_trans(Ruc);
		t.setNum_lic(NroLic);
		t.setUsu_creador_trans(usu_creador_trans);

		GestionTransportista gt = new GestionTransportista();
		int ok = gt.registrar(t);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso4);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso1);
		}

	}

	void actualizar() {
		int Codigo;
		String Nombre, Apellido, Direccion, Telefono, Ruc, NroLic;

		Codigo = leerCodigo();
		Nombre = leerNombre();
		Apellido = leerApellido();
		Direccion = leerDireccion();
		Telefono = leerTelefono();
		Ruc = leerRuc();
		NroLic = leerNumeeroLic();

		Transportista t = new Transportista();
		t.setCod_trans(Codigo);
		t.setNom_trans(Nombre);
		t.setApe_trans(Apellido);
		t.setDirec_trans(Direccion);
		t.setTelf_trans(Telefono);
		t.setRuc_trans(Ruc);
		t.setNum_lic(NroLic);
		GestionTransportista gt = new GestionTransportista();
		int ok = gt.actualizar(t);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso5);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso2);
		}

	}

	void eliminar() {
		int cod_trans = leerCodigo();
		Transportista t = new Transportista();
		t.setCod_trans(cod_trans);
		GestionTransportista gt = new GestionTransportista();
		int ok = gt.eliminar(t);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso6);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso3);
		}
	}

	void listadoTrans() {
		GestionTransportista gt = new GestionTransportista();
		ArrayList<Transportista> lista = gt.listado();
		modelo.setRowCount(0);
		for (Transportista t : lista) {
			Object[] datos = { t.getCod_trans(), t.getNom_trans(), t.getApe_trans(), t.getDirec_trans(),
					t.getTelf_trans(), t.getRuc_trans(), t.getNum_lic(), t.getUsu_creador_trans() };
			modelo.addRow(datos);
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
		return txtNombre.getText();
	}

	String leerApellido() {
		return txtApellido.getText();
	}

	String leerDireccion() {
		return txtDireccion.getText();
	}

	String leerTelefono() {
		String tel = null;
		tel = txtTelefono.getText().trim();
		if (tel.matches("([0-9]{9})?")) {
			return tel;
		} else {
			JOptionPane.showMessageDialog(this, "Campo Teléfono Incorrecto: Formato Celular");
			return null;
		}

	}

	String leerRuc() {
		String ruc = txtRuc.getText().trim();
		if (ruc.matches("([0-9]{11})?"))
			return ruc;
		else
			JOptionPane.showMessageDialog(this, "Campo Ruc Incorrecto: 11 dígitos");
		return null;
	}

	String leerNumeeroLic() {
		String NroLic = txtLicencia.getText().trim();
		if (NroLic.matches("([0-9]{5})?"))
			return NroLic;
		else
			JOptionPane.showMessageDialog(this,
					"Campo Nro Licencia incorrecto: una letra mayúscula seguida de 8 dígitos ");
		return null;
	}
}
