package vistas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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
import mantenimientos.GestionComprobantePago;
import modelos.Cliente;
import modelos.ComprobantePago;

public class FrmComprobantePago extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumero;
	private JTable tblComprobantePago;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private JComboBox<String> cboTipo;
	private JComboBox<String> cboCliente;
	private JComboBox<String> cboLugar;
	private JLabel lblNmero;
	private JLabel lblTipo;
	private JLabel lblLugar;
	private JButton btnRegistrar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnLimpiar;
	private JLabel lblCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmComprobantePago frame = new FrmComprobantePago();
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
	public FrmComprobantePago() {
		setFrameIcon(new ImageIcon(FrmComprobantePago.class.getResource("/iconos/comprobante.png")));
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Comprobante Pago");
		setBounds(100, 100, 640, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNmero.setBounds(10, 26, 71, 14);
		contentPane.add(lblNmero);

		txtNumero = new JTextField();
		txtNumero.setEditable(false);
		txtNumero.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtNumero.setBounds(91, 20, 111, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);

		lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipo.setBounds(10, 78, 71, 19);
		contentPane.add(lblTipo);

		cboTipo = new JComboBox<String>();
		cboTipo.setModel(new DefaultComboBoxModel<String>(new String[] { "Seleccione", "Boleta", "Factura" }));
		cboTipo.setFont(new Font("Tahoma", Font.BOLD, 10));
		cboTipo.setBounds(91, 72, 191, 20);
		contentPane.add(cboTipo);

		lblLugar = new JLabel("Lugar");
		lblLugar.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLugar.setBounds(309, 73, 46, 19);
		contentPane.add(lblLugar);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
				limpiar();
				listar_comprobantes();
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrar.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/iconos/registrar.png")));
		btnRegistrar.setBounds(10, 188, 145, 45);
		contentPane.add(btnRegistrar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
				limpiar();
				listar_comprobantes();
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModificar.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/iconos/modificar.png")));
		btnModificar.setBounds(165, 188, 145, 45);
		contentPane.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
				limpiar();
				listar_comprobantes();
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEliminar.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/iconos/eliminar.png")));
		btnEliminar.setBounds(320, 188, 145, 45);
		contentPane.add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 244, 606, 206);
		contentPane.add(scrollPane);

		tblComprobantePago = new JTable();
		tblComprobantePago.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rellenar();
			}
		});
		tblComprobantePago.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblComprobantePago);

		modelo = new DefaultTableModel();
		modelo.addColumn("Nro Comprobante");
		modelo.addColumn("Fecha");
		modelo.addColumn("Tipo");
		modelo.addColumn("Lugar");
		modelo.addColumn("Cliente");
		modelo.addColumn("Creador Comprobante");
		tblComprobantePago.setModel(modelo);

		ajustarAnchoColumnas();

		btnLimpiar = new JButton("LIMPIAR DATOS");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setBounds(274, 10, 191, 39);
		contentPane.add(btnLimpiar);

		lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCliente.setBounds(10, 132, 71, 14);
		contentPane.add(lblCliente);

		cboCliente = new JComboBox<String>();
		cboCliente.setModel(new DefaultComboBoxModel<String>(listar_clientes().toArray(new String[0])));
		cboCliente.setFont(new Font("Tahoma", Font.BOLD, 10));
		cboCliente.setBounds(91, 130, 191, 20);
		contentPane.add(cboCliente);

		cboLugar = new JComboBox<String>();
		cboLugar.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Seleccione", "Nicolini", "Centro Ferretero", "La bellota", "SJL", "Paruro" }));
		cboLugar.setFont(new Font("Tahoma", Font.BOLD, 10));
		cboLugar.setBounds(376, 72, 165, 20);
		contentPane.add(cboLugar);

		listar_comprobantes();
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	private void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblComprobantePago.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(14));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(8));
	}

	void limpiar() {
		txtNumero.setText("");
		cboTipo.setSelectedIndex(0);
		cboLugar.setSelectedIndex(0);
		cboCliente.setSelectedIndex(0);
		txtNumero.requestFocus();
	}

	List<String> listar_clientes() {
		List<String> cboItems =  new ArrayList<String>();
		cboItems.add("Seleccione");
		GestionCliente gc = new GestionCliente();
		List<Cliente> lista = gc.listado();
		for (Cliente cliente : lista) {
			cboItems.add(cliente.getNom_cli() + " " + cliente.getApe_cli());
		}
		return cboItems;
	}

	void listar_comprobantes() {
		GestionComprobantePago gcp = new GestionComprobantePago();
		ArrayList<ComprobantePago> lista = gcp.listado();
		modelo.setRowCount(0);
		for (ComprobantePago cp : lista) {
			Object[] fila = { cp.getNum_comp(), cp.getFec_comp(), tipo_comp_formateado(cp.getTip_comp()), cp.getLug_comp(), cp.getCod_cli(),
					cp.getUsu_creador_comp() };
			modelo.addRow(fila);
		}
	}
	
 
	
	String tipo_comp_formateado(String ty) {
		if(ty.equals("f")) return "Factura";
		else return "Boleta";
	}

	void rellenar() {
		int fila = tblComprobantePago.getSelectedRow();
		txtNumero.setText(tblComprobantePago.getValueAt(fila, 0).toString());
		cboTipo.setSelectedItem(tblComprobantePago.getValueAt(fila, 2).toString());
		cboLugar.setSelectedItem(tblComprobantePago.getValueAt(fila, 3).toString());
		cboCliente.setSelectedIndex(Integer.parseInt(tblComprobantePago.getValueAt(fila, 4).toString()));
	}

	void registrar() {
		String tipo_com, lugar;
		int cod_cli,usu_creador_comp;
		tipo_com = leerTipo();
		lugar = leerLugar();
		cod_cli = leerCliente();
		usu_creador_comp=1;//obtener de la sesion tras el logueo

		ComprobantePago cp = new ComprobantePago();
		cp.setTip_comp(tipo_com);
		cp.setLug_comp(lugar);
		cp.setCod_cli(cod_cli);
		cp.setUsu_creador_comp(usu_creador_comp);

		GestionComprobantePago gcp = new GestionComprobantePago();
		int ok = gcp.registrar(cp);
		System.out.println(tipo_com + " " + lugar + " "+cod_cli + " "+usu_creador_comp);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso4);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso1);
		}
	}

	void modificar() {
		int num_com, cod_cli;
		String tipo_com, lugar;
		num_com = leerNumCom();
		tipo_com = leerTipo();
		lugar = leerLugar();
		cod_cli = leerCliente();

		ComprobantePago cp = new ComprobantePago();
		cp.setNum_comp(num_com);
		cp.setTip_comp(tipo_com);
		cp.setLug_comp(lugar);
		cp.setCod_cli(cod_cli);

		GestionComprobantePago gcp = new GestionComprobantePago();
		int ok = gcp.actualizar(cp);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso5);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso2);
		}

	}

	void eliminar() {
		int num_com;
		num_com = leerNumCom();
		ComprobantePago cp = new ComprobantePago();
		cp.setNum_comp(num_com);
		GestionComprobantePago gcp = new GestionComprobantePago();
		int ok = gcp.eliminar(cp);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso6);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso3);
		}

	}

	int leerNumCom() {
		int c = 0;
		try {
			c = Integer.parseInt(txtNumero.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "");
		}
		return c;
	}

	String leerTipo() {
		int tp = 0;
		tp = cboTipo.getSelectedIndex();
		switch (tp) {
		case 1:
			return "b";
		case 2:
			return "f";
		default:
			return "";
		}
	}

	String leerLugar() {
		String c = null;
		c = cboLugar.getSelectedItem().toString();
		return c;
	}

	int leerCliente() {
		int c = 0;
		c = cboCliente.getSelectedIndex();
		return c;
	}
}
