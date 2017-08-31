package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import lucene.LuceneReturn;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class Consulta extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consulta frame = new Consulta(null);
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
	public Consulta(LuceneReturn lr) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		DefaultListModel dlm = new DefaultListModel();
		for(String path : lr.getPaths()){
			dlm.addElement(path);
		}
				
		JList list = new JList(dlm);
		list.setVisibleRowCount(100);
		list.setBounds(58, 112, 308, 138);
		list.setLayoutOrientation(JList.VERTICAL);
        JScrollPane scrollPane = new JScrollPane(list);
		contentPane.add(scrollPane);
		
		JLabel lblEmDocumentos = new JLabel(lr.getTotalFiles() +  " documento(s) encontrado(s)");
		scrollPane.setColumnHeaderView(lblEmDocumentos);
		lblEmDocumentos.setBounds(10, 62, 414, 39);
		lblEmDocumentos.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmDocumentos.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
	}

}
