package com.diaryInsert.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


import com.diary.control.DiaryDAO;
import com.diary.control.DiaryDAOImpl;
import com.diary.control.DiaryVO;
import com.diary.view.DiaryView;
import com.showPost.view.ShowPost;

import javax.swing.JButton;
import javax.swing.JScrollPane;

public class DiaryInsetView {

	private JFrame frame;
	private final JPanel panel = new JPanel();
	private JTextField textField_1;
	private JButton deleteButton;
	private JPanel ScheduleForm;
	private String UserID;
	private int year;
	private int month;
	private int day;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiaryInsetView window = new DiaryInsetView(null, 0,0,0);
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
	public DiaryInsetView(String UserID, int year, int month, int day) {
		this.UserID = UserID;
		this.year = year;
		this.month = month;
		this.day = day;
		initialize(UserID);
		displaySchedule();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String UserID) {
		frame = new JFrame("일정");
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 456, 662);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 0, 418, 102);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(DiaryInsetView.class.getResource("/img/diary.jpg")));
		lblNewLabel_1.setBounds(55, 35, 151, 42);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(DiaryInsetView.class.getResource("/img/dog2.jpg")));
		lblNewLabel_2.setBounds(12, 23, 49, 65);
		panel.add(lblNewLabel_2);
		
		JLabel IDLabel = new JLabel("사용자 : " + UserID);
		IDLabel.setFont(new Font("굴림", Font.BOLD, 15));
		IDLabel.setBounds(252, 35, 164, 53);
		panel.add(IDLabel);
		
		JButton backBtn = new JButton("");
		backBtn.setIcon(new ImageIcon(DiaryInsetView.class.getResource("/img/arrow-go-back-line.png")));
		backBtn.setBackground(Color.LIGHT_GRAY);
		backBtn.setBounds(10, 579, 97, 34);
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DiaryView view = new DiaryView(UserID);
				view.ShowWindow();
				frame.dispose();
			}
		});
		frame.getContentPane().add(backBtn);
		
		final JButton InsertBtn = new JButton("일정추가");
		InsertBtn.setIcon(new ImageIcon(DiaryInsetView.class.getResource("/img/baidu-line.png")));
		InsertBtn.setFont(new Font("굴림", Font.BOLD, 12));
		InsertBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ScheduleText = textField_1.getText();
				if (!ScheduleText.isEmpty()) {
					DiaryVO vo = new DiaryVO();
					DiaryDAO dao = new DiaryDAOImpl();

					vo.setYear(year);;
					vo.setWriter(UserID);
					vo.setContent(ScheduleText);
					vo.setMonth(month);
					vo.setDay(day);
					dao.InsertDiary(vo);

					

					// 새로운 댓글 패널 생성
					JPanel SchedulePanel = new JPanel();
					SchedulePanel.setBackground(Color.white);

					JLabel nameLabel = new JLabel(UserID);
					nameLabel.setPreferredSize(new Dimension(50, 20));

					JLabel ScheduleLabel = new JLabel(ScheduleText);
					ScheduleLabel.setPreferredSize(new Dimension(500, 20));
					ScheduleLabel.setHorizontalAlignment(SwingConstants.CENTER);

					

					displaySchedule();

				} else {
					JOptionPane.showMessageDialog(InsertBtn, "댓글을 입력해주세요.");
				}
			}
		});
			
		InsertBtn.setBackground(Color.LIGHT_GRAY);
		InsertBtn.setBounds(301, 582, 127, 31);
		frame.getContentPane().add(InsertBtn);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 537, 418, 32);
		frame.getContentPane().add(textField_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(true);
		scrollPane.setBounds(10, 112, 418, 415);
		frame.getContentPane().add(scrollPane);
		
		ScheduleForm = new JPanel();
		scrollPane.setViewportView(ScheduleForm);
		ScheduleForm.setBackground(new Color(255, 255, 255));
		ScheduleForm.setLayout(new BoxLayout(ScheduleForm, BoxLayout.Y_AXIS));
		ScheduleForm.setSize(418, 40);
	}
	public void ShowWindow() {
		// TODO Auto-generated method stub
		frame.setVisible(true);
	}
	
	public void displaySchedule() {
		DiaryDAO dao = new DiaryDAOImpl();
		List<DiaryVO> diaryvo = dao.select(UserID, year, month, day);

		ScheduleForm.removeAll();
		for (final DiaryVO diary : diaryvo) {
			JPanel SchedulePanel = new JPanel();
			SchedulePanel.setBackground(Color.white);

			JLabel nameLabel = new JLabel(diary.getWriter());
			nameLabel.setPreferredSize(new Dimension(80, 20));

			JLabel contentLabel = new JLabel(diary.getContent());
			contentLabel.setPreferredSize(new Dimension(200, 20));
			contentLabel.setHorizontalAlignment(SwingConstants.CENTER);

			

			textField_1.setText("");

			deleteButton = new JButton("삭제");
			deleteButton.setBackground(new Color(255, 255, 255));
			deleteButton.setBorder(new EmptyBorder(0, 0, 0, 0));
			deleteButton.setIcon(new ImageIcon(ShowPost.class.getResource("/img/chat-delete-line.png")));
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 삭제 버튼을 눌렀을 때 실행되는 동작 구현
					if (UserID.equals(diary.getWriter())) {
						DiaryVO vo = new DiaryVO();
						DiaryDAO dao = new DiaryDAOImpl();

						String writer = diary.getWriter();
						int num = diary.getNum();
						System.out.println(num);
						System.out.println(writer);
						System.out.println(year);
						System.out.println(month);
						System.out.println(day);
						
						vo.setNum(num);
						vo.setYear(year);
						vo.setMonth(month);
						vo.setDay(day);
						vo.setWriter(writer);
						dao.delete(vo);

						displaySchedule();
					} else {
						JOptionPane.showMessageDialog(deleteButton, "권한이 없습니다.");
					}
				}
			});

			SchedulePanel.add(nameLabel);
			SchedulePanel.add(contentLabel);
			SchedulePanel.add(deleteButton);

			ScheduleForm.add(SchedulePanel);
		}

		ScheduleForm.revalidate();
		ScheduleForm.repaint();
	}
}
