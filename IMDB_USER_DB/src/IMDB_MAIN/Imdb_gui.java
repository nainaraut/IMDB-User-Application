package IMDB_MAIN;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Imdb_gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	List<FinalResult> result = null;

	/**
	 * Launch the application.
	 */
	static Hw3 obj = null;
	private JTable table;
	public static void main(String[] args) {
		obj = new Hw3();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Imdb_gui frame = new Imdb_gui();
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
	public Imdb_gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 997, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 485, 254);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(10, 221, 446, 22);
		panel.add(panel_7);
		panel_7.setLayout(null);
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"AND", "OR"}));
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(238, 0, 208, 22);
		panel_7.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Search between Attribute values");
		lblNewLabel.setBounds(10, 0, 218, 22);
		panel_7.add(lblNewLabel);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(10, 11, 157, 209);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Genres");
		lblNewLabel_1.setBounds(49, 0, 46, 14);
		panel_4.add(lblNewLabel_1);
		
		final ArrayList<String> temp = obj.getGenres();
		final JList<String> list = new JList<String>();
		list.setModel(new AbstractListModel() {
			String[] values = (String[]) temp.toArray(new String[temp.size()]);
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(10, 133, 124, -119);
		JScrollPane scrollPane  = new JScrollPane(list);
		scrollPane.setLocation(10, 15);
		scrollPane.setSize(140, 160);
		panel_4.add(scrollPane);
		
		JButton btnNewButton_1 = new JButton("Display Countries");
		btnNewButton_1.setBounds(10, 178, 140, 20);
		panel_4.add(btnNewButton_1);
		
		final JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(177, 11, 144, 209);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Country");
		lblNewLabel_2.setBounds(44, 0, 46, 14);
		panel_5.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("Display Locations");
		btnNewButton_2.setBounds(10, 178, 130, 20);
		panel_5.add(btnNewButton_2);
		
		final JList<String> list_1 = new JList<String>();
		JScrollPane scrollPane_1 = new JScrollPane(list_1);
		scrollPane_1.setBounds(10, 15, 130, 160);
		panel_5.add(scrollPane_1);		
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBounds(331, 11, 144, 209);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Filming Location Country");
		lblNewLabel_3.setBounds(0, 0, 144, 14);
		panel_6.add(lblNewLabel_3);
		
		JButton btnNewButton_3 = new JButton("Filter on Locations");
		btnNewButton_3.setBounds(10, 178, 130, 20);
		panel_6.add(btnNewButton_3);
		
		final JList<String> list_2 = new JList<String>();
		JScrollPane scrollPane_2 = new JScrollPane(list_2);
		scrollPane_2.setBounds(10, 15, 130, 160);
		panel_6.add(scrollPane_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(505, 11, 466, 254);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setBounds(10, 11, 201, 152);
		panel_1.add(panel_8);
		panel_8.setLayout(null);
		
		JLabel lblCriticRating = new JLabel("Critic Rating");
		lblCriticRating.setBounds(70, 0, 86, 14);
		panel_8.add(lblCriticRating);
		
		final JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"=", "<", ">", "<=", ">="}));
		comboBox_1.setBounds(68, 21, 88, 20);
		panel_8.add(comboBox_1);
		
		textField = new JTextField();
		textField.setBounds(70, 53, 86, 20);
		panel_8.add(textField);
		textField.setColumns(10);
		
		final JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"=", "<", ">", "<=", ">="}));
		comboBox_2.setBounds(68, 84, 88, 20);
		panel_8.add(comboBox_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(70, 115, 86, 20);
		panel_8.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblRating = new JLabel("Rating");
		lblRating.setBounds(10, 24, 46, 14);
		panel_8.add(lblRating);
		
		JLabel lblValue_1 = new JLabel("Value");
		lblValue_1.setBounds(10, 56, 46, 14);
		panel_8.add(lblValue_1);
		
		JLabel lblReviews = new JLabel("Reviews");
		lblReviews.setBounds(12, 87, 46, 14);
		panel_8.add(lblReviews);
		
		JLabel lblValue_2 = new JLabel("Value");
		lblValue_2.setBounds(10, 118, 46, 14);
		panel_8.add(lblValue_2);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_9.setBounds(221, 11, 235, 152);
		panel_1.add(panel_9);
		panel_9.setLayout(null);
		
		JLabel lblMovieTagValue = new JLabel("Movie Tag Value");
		lblMovieTagValue.setBounds(61, 0, 110, 14);
		panel_9.add(lblMovieTagValue);
		
		final JList<String> list_3 = new JList<String>();
		JScrollPane scrollPane_5 = new JScrollPane(list_3);
		scrollPane_5.setBounds(10, 21, 215, 120);
		panel_9.add(scrollPane_5);
				
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBounds(10, 170, 201, 52);
		panel_1.add(panel_10);
		panel_10.setLayout(null);
		
		textField_4 = new JTextField();
		textField_4.setBounds(70, 3, 86, 17);
		panel_10.add(textField_4);
		textField_4.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(70, 31, 86, 17);
		panel_10.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(10, 3, 46, 14);
		panel_10.add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(10, 32, 46, 14);
		panel_10.add(lblTo);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.setBounds(221, 170, 235, 52);
		panel_1.add(panel_11);
		panel_11.setLayout(null);
		
		final JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] {"=", "<", ">"}));
		comboBox_3.setBounds(84, 3, 62, 20);
		panel_11.add(comboBox_3);
		
		JLabel lblTagWeight = new JLabel("Tag Weight");
		lblTagWeight.setBounds(10, 6, 64, 14);
		panel_11.add(lblTagWeight);
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setBounds(10, 34, 56, 14);
		panel_11.add(lblValue);
		
		textField_2 = new JTextField();
		textField_2.setBounds(72, 31, 86, 18);
		panel_11.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnDone = new JButton("Done");
		btnDone.setBounds(30, 233, 142, 20);
		panel_1.add(btnDone);
		
		JButton btnDone_1 = new JButton("Display Query");
		btnDone_1.setBounds(280, 232, 131, 20);
		panel_1.add(btnDone_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 276, 485, 291);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Execute");
		btnNewButton.setBounds(169, 257, 89, 23);
		panel_2.add(btnNewButton);
		
		JScrollPane scrollPane_4 = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_4.setBounds(10, 11, 465, 240);
		panel_2.add(scrollPane_4);
		
		final JTextPane textPane = new JTextPane();
		scrollPane_4.setViewportView(textPane);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(505, 276, 466, 291);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblResult = new JLabel("Result");
		lblResult.setBounds(239, 11, 56, 14);
		panel_3.add(lblResult);
		final DefaultTableModel model = new DefaultTableModel(new String[] {"Title", "Genre", "Year", "Country", "Locations", "Avg Rating", "Avg Reviews"},0);
		
		JScrollPane scrollPane_3 = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_3.setBounds(10, 32, 446, 248);
		panel_3.add(scrollPane_3);
		table = new JTable(model);
		table.setBounds(10, 32, 446, 248);
		scrollPane_3.setViewportView(table);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				obj.clear();
				list_2.setListData(new String[0]);
				list_3.setListData(new String[0]);
				textPane.setText("");
				model.setRowCount(0);
				List<String> valuesSelected = list.getSelectedValuesList();
				final ArrayList<String> temp = obj.getCountry(valuesSelected,comboBox.getSelectedItem().toString());
				list_1.setModel(new AbstractListModel() {
					String[] values = (String[]) temp.toArray(new String[temp.size()]);
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_3.setListData(new String[0]);
				textPane.setText("");
				model.setRowCount(0);
				final ArrayList<String> temp = obj.getLocation(list_1.getSelectedValuesList(),list.getSelectedValuesList(),comboBox.getSelectedItem().toString());
				list_2.setModel(new AbstractListModel() {
					String[] values = (String[]) temp.toArray(new String[temp.size()]);
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
			}
		});
		
		comboBox.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				obj.clear();
				list.clearSelection();
				list_1.setListData(new String[0]);
				list_2.setListData(new String[0]);
				textPane.setText("");
				model.setRowCount(0);
			}
		});
		
		btnNewButton_3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				list_3.setListData(new String[0]);
				textPane.setText("");
				model.setRowCount(0);
				obj.getFilteredMovieIDs(list_2.getSelectedValuesList(), comboBox.getSelectedItem().toString());
			}
		});
		
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPane.setText("");
				model.setRowCount(0);
				
				String rating = comboBox_1.getSelectedItem().toString();
				String rating_val = textField.getText();
				String review = comboBox_2.getSelectedItem().toString();
				String review_val = textField_1.getText();
				String from = textField_4.getText();
				String to = textField_3.getText();
				String tag_wt = comboBox_3.getSelectedItem().toString();
				String tag_wt_val = textField_2.getText();
				final List<String> tagValues = obj.getTagValues(rating, rating_val, review, review_val, from, to, tag_wt, tag_wt_val, 
		                comboBox.getSelectedItem().toString());
				list_3.setModel(new AbstractListModel() {
					String[] values = (String[]) tagValues.toArray(new String[tagValues.size()]);
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
			}
		});
		
		btnDone_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String finalQuery = obj.getQuery(comboBox.getSelectedItem().toString(), list_3.getSelectedValuesList());
				textPane.setText(finalQuery);
			}	
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.setRowCount(0);
				result = obj.getFinalResult();
				for(FinalResult fr : result){
					String[] arr = {fr.title,fr.genre,String.valueOf(fr.year),fr.country,fr.locations,
							String.valueOf(fr.avgCriticsRating),String.valueOf(fr.avgCriticsReviews)};
					model.addRow(arr);
				}
			}
		});
	}
}
