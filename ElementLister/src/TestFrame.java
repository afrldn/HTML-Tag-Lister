import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;

import javax.swing.JTextArea;

public class TestFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtURL;
	private JTextField txtTag;
	private JOptionPane optionPg;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
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
	public TestFrame() {
		setResizable(false);
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		txtURL = new JTextField();
		txtURL.setBounds(94, 38, 116, 22);
		txtURL.setText("http://www.yahoo.com");
		contentPane.add(txtURL);
		txtURL.setColumns(10);
		

		JLabel lblUrl = new JLabel("URL");
		lblUrl.setBounds(5, 41, 53, 16);
		contentPane.add(lblUrl);
		
		JLabel lblHtmlTag = new JLabel("HTML Tag");
		lblHtmlTag.setBounds(215, 41, 83, 16);
		contentPane.add(lblHtmlTag);
		
		txtTag = new JTextField();
		txtTag.setBounds(273, 38, 116, 22);
		txtTag.setText("h2");
		contentPane.add(txtTag);
		txtTag.setColumns(10);
		



		ElementLister A = new ElementLister(txtURL.getText(),txtTag.getText());
		
		
		/*/****
		Generating UL Button
		*/
		JButton btnGenUl = new JButton("Generate UL");
		btnGenUl.setEnabled(false);
		btnGenUl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A.printToUL();
				JOptionPane.showMessageDialog(contentPane, "List of tags sent to HTML file as an unordered list", "Out to UL", 1);
			}
		});
		btnGenUl.setBounds(5, 376, 127, 25);
		contentPane.add(btnGenUl);
		
		JButton btnTextGen = new JButton("Generate Text File");
		btnTextGen.setEnabled(false);
		btnTextGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String m;
				
				m = JOptionPane.showInputDialog("Input the file name") + ".txt";
				
				A.printToText("m");
				/*try{
				
				JOptionPane.showMessageDialog(null, "New File Made");
				}
				*/
				
			}
		});
		btnTextGen.setBounds(159, 376, 137, 25);
		contentPane.add(btnTextGen);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			int Open = 0;
			public void actionPerformed(ActionEvent e) {
				
				Open = JOptionPane.showInternalConfirmDialog(contentPane, "Are you SURE you want to exit","Exit"
						+ "Confirmation" , 0);
				if( Open == 0){
				
					System.exit(0);	
				}
					
				
				
			}
		});
		btnClose.setBounds(320, 376, 97, 25);
		contentPane.add(btnClose);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(15, 115, 140, 217);
		contentPane.add(textArea);
		
		JScrollPane scpListScroll = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scpListScroll.setLocation(25, 65);
		scpListScroll.setSize(200, 300);
		textArea.setLineWrap(true);
		contentPane.add(scpListScroll); /**/
		
		JButton btnGenerateList = new JButton("Generate List");
		btnGenerateList.setBounds(300, 65, 127, 31);
		contentPane.add(btnGenerateList);
		
		btnGenerateList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				listModel.clear();
				textArea.setText("");
			for (int i = 1; i< A.tagList.size();i++){
		
				listModel.addElement(A.tagList.get(i));
				textArea.append(A.tagList.get(i) +"\n");
			}
			btnGenUl.setEnabled(true);
			btnTextGen.setEnabled(true);
			
				/*	
			*/	
			}
		});
	}
}
