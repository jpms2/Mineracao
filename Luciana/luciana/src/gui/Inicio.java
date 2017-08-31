package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import lucene.LuceneReadIndexFromFileExample;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Inicio extends JFrame{

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio window = new Inicio();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Digite sua consulta:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(92, 11, 297, 44);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(66, 120, 323, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnProcurar = new JButton("PROCURAR");
		btnProcurar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String consulta = textField.getText();
				LuceneReadIndexFromFileExample findMatches = new LuceneReadIndexFromFileExample();
				try {
					Consulta consultaT = new Consulta(findMatches.makeQuery(consulta));
					consultaT.setLocationRelativeTo(frame.getContentPane());
					consultaT.setResizable(true);
					consultaT.setVisible(true);
					Inicio.this.dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnProcurar.setBounds(155, 211, 135, 23);
		frame.getContentPane().add(btnProcurar);
	}
}
