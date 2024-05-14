package com.Map.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;


import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;



import javax.swing.JButton;

import java.awt.Font;

public class MapView {

	private JFrame frame;
	private JTextField SearchHospitalText;
	private JButton SearchBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapView window = new MapView();
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
	public MapView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 498, 510);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(MapView.class.getResource("/img/diary.jpg")));
		lblNewLabel_3.setBounds(53, 29, 151, 42);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(MapView.class.getResource("/img/dog2.jpg")));
		lblNewLabel_2.setBounds(12, 10, 49, 65);
		frame.getContentPane().add(lblNewLabel_2);
		
		SearchHospitalText = new JTextField();
		SearchHospitalText.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 14));
		SearchHospitalText.setBounds(12, 428, 349, 32);
		frame.getContentPane().add(SearchHospitalText);
		SearchHospitalText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SearchBtn.doClick();
			}
		});
		
			
			
		SearchHospitalText.setColumns(10);
		
		SearchBtn = new JButton("검색");
		SearchBtn.setBackground(new Color(192, 192, 192));
		SearchBtn.setIcon(new ImageIcon(MapView.class.getResource("/img/search-line.png")));
		SearchBtn.setBounds(373, 428, 97, 33);
		SearchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String address = SearchHospitalText.getText();
					if(!address.isEmpty()) {
					String searchQuery = address + "%20동물병원";
					String searchURL = "https://map.kakao.com/?q=" + searchQuery;
					openURL(searchURL);
					}else {
						JOptionPane.showMessageDialog(SearchBtn, "지역을 입력해주세요");
					}

					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
			
		});
		frame.getContentPane().add(SearchBtn);
		
	}
	public void ShowWindow() {
		frame.setVisible(true);
	}
	
	private void openURL(String url) {
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
