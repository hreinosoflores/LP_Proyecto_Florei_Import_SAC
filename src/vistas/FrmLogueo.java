package vistas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import hilos.HiloCerrar;
import mantenimientos.GestionUsuario;
import modelos.Usuario;

public class FrmLogueo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JButton btnAceptar;
	public static JLabel lblTiempo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogueo frame = new FrmLogueo();
					frame.setLocationRelativeTo(null);
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
	public FrmLogueo() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmLogueo.class.getResource("/iconos/FloreiLogo.png")));
		setTitle("FLOREI IMPORT S.A.C.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 409, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsuario.setBounds(137, 36, 96, 20);
		contentPane.add(lblUsuario);

		JLabel lblClave = new JLabel("Contrase\u00F1a:");
		lblClave.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblClave.setBounds(137, 83, 96, 20);
		contentPane.add(lblClave);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtUsuario.setBounds(270, 37, 103, 22);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtClave = new JPasswordField();
		txtClave.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtClave.setBounds(270, 85, 103, 20);
		contentPane.add(txtClave);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirPrincipal();
			}
		});
		btnAceptar.setBounds(137, 127, 96, 29);
		contentPane.add(btnAceptar);

		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(270, 127, 96, 29);
		contentPane.add(btnSalir);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(FrmLogueo.class.getResource("/iconos/Usuario1.png")));
		lblFondo.setBounds(10, 11, 117, 158);
		contentPane.add(lblFondo);

		lblTiempo = new JLabel("10s");
		lblTiempo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTiempo.setBounds(318, 12, 46, 14);
		contentPane.add(lblTiempo);

		JLabel lblMensaje = new JLabel("Esta ventana se cerrar\u00E1 en ");
		lblMensaje.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMensaje.setHorizontalTextPosition(SwingConstants.LEFT);
		lblMensaje.setBounds(137, 11, 236, 14);
		contentPane.add(lblMensaje);
		iniciaTiempo();
	}

	void iniciaTiempo() {
		HiloCerrar hilo = new HiloCerrar(this);
		hilo.start();
	}

	void abrirPrincipal() {
		String usuario, clave;
		usuario = leerUsuario();
		clave = leerClave();

		GestionUsuario gu = new GestionUsuario();
		Usuario u = gu.validaAcceso(usuario, clave);

		if (u == null) {
			aviso("Usuario o clave incorrectos");
		} else {
			aviso("Bienvenido : " + u.getNom_usua() + " " + u.getApe_usua());
			FrmPrincipal v = new FrmPrincipal();
			v.setVisible(true);
			v.setExtendedState(MAXIMIZED_BOTH);
			dispose();

		}
	}

	private void aviso(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	private String leerUsuario() {
		return txtUsuario.getText();
	}

	private String leerClave() {
		return txtClave.getText();
	}
}
