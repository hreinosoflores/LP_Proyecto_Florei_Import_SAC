package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hilos.HiloFunciona;

public class FrmPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDesktopPane escritorio;
	public static JMenu mnHoraDelDia;
	public static String aviso1 = "Registro correcto";
	public static String aviso2 = "Actualización correcta";
	public static String aviso3 = "Eliminación correcta";
	public static String aviso4 = "No se puede registrar. Compruebe datos";
	public static String aviso5 = "No se puede modificar. Compruebe datos";
	public static String aviso6 = "No se puede eliminar. Compruebe datos";
	private JMenuItem mntmReporte3;
	private JMenuItem mntmReporte1;
	private JMenuItem mntmReporte2;
	private JMenuItem mntmCliente;
	private JMenuItem mntmComprobantePago;
	private JMenuItem mntmEmpresaRemitente;
	private JMenuItem mntmGuia;
	private JMenuItem mntmProducto;
	private JMenuItem mntmTransportista;
	private JMenuBar mbPrincipal;
	private JMenu mnArchivo;
	private JMenuItem mntmSalir;
	private JMenu mnMantenimiento;
	private JMenu mnReportes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
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
	public FrmPrincipal() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmPrincipal.class.getResource("/iconos/FloreiLogo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		mbPrincipal = new JMenuBar();
		mbPrincipal.setToolTipText("00:00:00");
		mbPrincipal.setBackground(new Color(153, 255, 102));
		setJMenuBar(mbPrincipal);

		mnArchivo = new JMenu("Archivo");
		mnArchivo.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/iconos/archivo.png")));
		mbPrincipal.add(mnArchivo);

		mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/iconos/salir.png")));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);

		mnMantenimiento = new JMenu("Mantenimiento");
		mnMantenimiento.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/iconos/mantenimiento.png")));
		mbPrincipal.add(mnMantenimiento);

		mntmCliente = new JMenuItem("Cliente");
		mntmCliente.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/iconos/clientedestinatario.png")));
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCliente v = new FrmCliente();
				escritorio.add(v);
				v.setVisible(true);
				Dimension desktopSize = escritorio.getSize();
				Dimension FrameSize = v.getSize();
				v.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
				v.show();
			}
		});
		mnMantenimiento.add(mntmCliente);

		mntmEmpresaRemitente = new JMenuItem("Empresa");
		mntmEmpresaRemitente.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/iconos/empresa.png")));
		mntmEmpresaRemitente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmEmpresa v = new FrmEmpresa();
				escritorio.add(v);
				v.setVisible(true);
				Dimension desktopSize = escritorio.getSize();
				Dimension FrameSize = v.getSize();
				v.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
				v.show();
			}
		});

		mntmTransportista = new JMenuItem("Transportista");
		mntmTransportista.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/iconos/transportista.png")));
		mntmTransportista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmTransportista v = new FrmTransportista();
				escritorio.add(v);
				v.setVisible(true);
				Dimension desktopSize = escritorio.getSize();
				Dimension FrameSize = v.getSize();
				v.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
				v.show();
			}
		});
		mnMantenimiento.add(mntmTransportista);
		mnMantenimiento.add(mntmEmpresaRemitente);

		mntmProducto = new JMenuItem("Producto");
		mntmProducto.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/iconos/producto.png")));
		mntmProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmProducto v = new FrmProducto();
				escritorio.add(v);
				v.setVisible(true);
				Dimension desktopSize = escritorio.getSize();
				Dimension FrameSize = v.getSize();
				v.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
				v.show();
			}
		});
		mnMantenimiento.add(mntmProducto);

		mntmGuia = new JMenuItem("Gu\u00EDa");
		mntmGuia.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/iconos/comprobante.png")));
		mntmGuia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmGuia v = new FrmGuia();
				escritorio.add(v);
				v.setVisible(true);
				Dimension desktopSize = escritorio.getSize();
				Dimension FrameSize = v.getSize();
				v.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
				v.show();
			}
		});

		mntmComprobantePago = new JMenuItem("Comprobante Pago");
		mntmComprobantePago.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/iconos/comprobante.png")));
		mntmComprobantePago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmComprobantePago v = new FrmComprobantePago();
				escritorio.add(v);
				v.setVisible(true);
				Dimension desktopSize = escritorio.getSize();
				Dimension FrameSize = v.getSize();
				v.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
				v.show();
			}
		});
		mnMantenimiento.add(mntmComprobantePago);
		mnMantenimiento.add(mntmGuia);

		mnReportes = new JMenu("Reportes");
		mnReportes.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/iconos/reporte.png")));
		mbPrincipal.add(mnReportes);

		mntmReporte1 = new JMenuItem("Reporte de comprobantes por fecha");
		mntmReporte1.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/iconos/reporte.png")));
		mntmReporte1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmReporteComprobante v = new FrmReporteComprobante();
				escritorio.add(v);
				v.setVisible(true);
				Dimension desktopSize = escritorio.getSize();
				Dimension FrameSize = v.getSize();
				v.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
				v.show();
			}
		});
		mnReportes.add(mntmReporte1);

		mntmReporte2 = new JMenuItem("Reporte por Documento");
		mntmReporte2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmReporte2 r = new FrmReporte2();
				escritorio.add(r);
				r.setVisible(true);
				Dimension desktopSize = escritorio.getSize();
				Dimension FrameSize = r.getSize();
				r.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
				r.show();

			}
		});

		mntmReporte3 = new JMenuItem("Reporte de Producto en Stock");
		mntmReporte3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmReporte3 r = new FrmReporte3();
				escritorio.add(r);
				r.setVisible(true);
				Dimension desktopSize = escritorio.getSize();
				Dimension FrameSize = r.getSize();
				r.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
				r.show();

			}
		});

		mntmReporte2.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/iconos/reporte.png")));
		mnReportes.add(mntmReporte2);
		mntmReporte3.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/iconos/reporte.png")));
		mnReportes.add(mntmReporte3);

		mnHoraDelDia = new JMenu("00:00:00");
		mbPrincipal.add(mnHoraDelDia);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		escritorio = new JDesktopPane();
		escritorio.setBackground(new Color(173, 255, 47));
		contentPane.add(escritorio, BorderLayout.CENTER);
		IniciaReloj();
	}

	void IniciaReloj() {
		HiloFunciona hilo = new HiloFunciona();
		hilo.start();
	}
}
