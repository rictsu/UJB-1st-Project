package com.MyPageView.com;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.join.VO.JoinVo;
import com.join.dao.JoinDAO;
import com.login.view.LoginMain;
import com.main.view.MainView;
import javax.swing.ImageIcon;

public class MyPageView {

	private JFrame frame;
	private JTextField idField;
	private JTextField telField;
	private JTextField nameField;
	private JTextField dognameField;
	private JTextField dogsexField;
	private JTextField dogbirthField;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;

	JoinVo joinVo = new JoinVo();
	JoinDAO joinDAO = new JoinDAO();
	private JButton BackBtn;
	private JLabel lblNewLabel_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyPageView window = new MyPageView(null, null, null, null, null, null);
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
	public MyPageView(String id, String tel, String name, String dogname, String dogsex, String dogbirth) {
		initialize(id, tel, name, dogname, dogsex, dogbirth);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String id, String tel, String name, String dogname, String dogsex, String dogbirth) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 660, 593);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("My Page");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("HY강M", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(256, 47, 195, 77);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 12));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(172, 134, 72, 15);
		frame.getContentPane().add(lblNewLabel_1);

		idField = new JTextField();
		idField.setEditable(false);
		idField.setFont(new Font("굴림", Font.PLAIN, 12));
		idField.setText(id);
		idField.setBackground(new Color(255, 255, 255));
		idField.setBounds(256, 131, 195, 21);
		frame.getContentPane().add(idField);
		idField.setColumns(10);

		telField = new JTextField();
		telField.setEditable(false);
		telField.setFont(new Font("굴림", Font.PLAIN, 12));
		telField.setText(tel);
		telField.setBackground(new Color(255, 255, 255));
		telField.setColumns(10);
		telField.setBounds(256, 183, 195, 21);
		frame.getContentPane().add(telField);

		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setFont(new Font("굴림", Font.PLAIN, 12));
		nameField.setText(name);
		nameField.setBackground(new Color(255, 255, 255));
		nameField.setColumns(10);
		nameField.setBounds(256, 238, 195, 21);
		frame.getContentPane().add(nameField);

		dognameField = new JTextField();
		dognameField.setEditable(false);
		dognameField.setFont(new Font("굴림", Font.PLAIN, 12));
		dognameField.setText(dogname);
		dognameField.setBackground(new Color(255, 255, 255));
		dognameField.setColumns(10);
		dognameField.setBounds(256, 297, 195, 21);
		frame.getContentPane().add(dognameField);

		dogsexField = new JTextField();
		dogsexField.setEditable(false);
		dogsexField.setFont(new Font("굴림", Font.PLAIN, 12));
		dogsexField.setText(dogsex);
		dogsexField.setBackground(new Color(255, 255, 255));
		dogsexField.setColumns(10);
		dogsexField.setBounds(256, 357, 195, 21);
		frame.getContentPane().add(dogsexField);

		dogbirthField = new JTextField();
		dogbirthField.setEditable(false);
		dogbirthField.setFont(new Font("굴림", Font.PLAIN, 12));
		dogbirthField.setText(dogbirth);
		dogbirthField.setBackground(new Color(255, 255, 255));
		dogbirthField.setColumns(10);
		dogbirthField.setBounds(256, 416, 195, 21);
		frame.getContentPane().add(dogbirthField);

		lblNewLabel_2 = new JLabel("전화번호");
		lblNewLabel_2.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 12));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(172, 186, 72, 15);
		frame.getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("이름");
		lblNewLabel_3.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 12));
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(172, 241, 72, 15);
		frame.getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("강아지이름");
		lblNewLabel_4.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 12));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(172, 300, 71, 15);
		frame.getContentPane().add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("강아지 성별");
		lblNewLabel_5.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 12));
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(172, 360, 72, 15);
		frame.getContentPane().add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("강아지 생일");
		lblNewLabel_6.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 12));
		lblNewLabel_6.setBackground(new Color(255, 255, 255));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(172, 419, 72, 15);
		frame.getContentPane().add(lblNewLabel_6);

		final JButton btnNewButton_1 = new JButton("회원탈퇴");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("나눔고딕", Font.BOLD, 13));
		btnNewButton_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton_1.setBackground(new Color(0, 0, 0));
		btnNewButton_1.setBounds(270, 467, 109, 23);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int answer = JOptionPane.showConfirmDialog(btnNewButton_1, "정말 삭제하시겠습니까?", "delete",
						JOptionPane.YES_NO_OPTION);

				if (answer == JOptionPane.YES_OPTION) {
					joinVo.setId(id);
					joinDAO.DeleteUser(joinVo);
					LoginMain view = new LoginMain();
					view.ShowWindow();
					frame.dispose();
				} else {

				}
			}
		});
		frame.getContentPane().add(btnNewButton_1);

		BackBtn = new JButton("BACK");
		BackBtn.setForeground(Color.WHITE);
		BackBtn.setFont(new Font("나눔고딕", Font.BOLD, 13));
		BackBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		BackBtn.setBackground(Color.BLACK);
		BackBtn.setBounds(280, 500, 91, 23);
		BackBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainView view = new MainView(id);
				view.ShowWindow();
				frame.dispose();
			}
		});
		frame.getContentPane().add(BackBtn);
		
		lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setIcon(new ImageIcon("D:\\s\\dogfoot-removebg-preview.png"));
		lblNewLabel_7.setBounds(184, 47, 71, 62);
		frame.getContentPane().add(lblNewLabel_7);
	}

	public void showWindow() {
		// TODO Auto-generated method stub
		frame.setVisible(true);
	}
}