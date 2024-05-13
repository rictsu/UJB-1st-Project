package com.diary.view;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.diaryInsert.view.DiaryInsetView;
import com.main.view.MainView;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class DiaryView {

	private JFrame frame;
	private JPanel CalendarPanel;
	private Calendar CurrentCalendar;
	private JButton DayButtons;
	private int year;
	private int month;
	private String titleStr[] = { "일", "월", "화", "수", "목", "금", "토" };
	private JPanel TitlePanel;
	private JComboBox<Integer> YearCombo;
	private JComboBox<Integer> MonthCombo;
	private JButton PrevBtn;
	private JButton NextBtn;
	private JLabel YearLabel;
	private JLabel IDLabel;
	private String UserID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiaryView window = new DiaryView(null);
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
	public DiaryView(String UserID) {
		this.UserID = UserID;
		initialize(UserID);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String UserID) {
		CurrentCalendar = Calendar.getInstance();
		year = CurrentCalendar.get(Calendar.YEAR);
		month = CurrentCalendar.get(Calendar.MONTH);

		frame = new JFrame("다이어리");
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 951, 719);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel HeadPanel = new JPanel();
		HeadPanel.setBackground(new Color(255, 255, 255));
		HeadPanel.setBounds(0, 0, 935, 77);
		frame.getContentPane().add(HeadPanel);
		HeadPanel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(12, 10, 49, 65);
		HeadPanel.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(DiaryView.class.getResource("/img/dog2.jpg")));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(53, 29, 151, 42);
		HeadPanel.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(DiaryView.class.getResource("/img/diary.jpg")));

		IDLabel = new JLabel("사용자 : " + UserID);
		IDLabel.setFont(new Font("굴림", Font.BOLD, 15));
		IDLabel.setBounds(736, 10, 164, 53);
		HeadPanel.add(IDLabel);

		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBackground(new Color(255, 255, 255));
		ButtonPanel.setBounds(0, 76, 935, 77);
		frame.getContentPane().add(ButtonPanel);
		ButtonPanel.setLayout(null);

		PrevBtn = new JButton("");
		PrevBtn.setBackground(new Color(255, 255, 255));
		PrevBtn.setBorder(new EmptyBorder(0,0,0,0));
		PrevBtn.setIcon(new ImageIcon(DiaryView.class.getResource("/img/arrow-left-s-line.png")));
		PrevBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (month == 1) {
					year--;
					month = 12;
				} else {
					month--;
				}
				CalendarPanel.setVisible(false);
				CalendarPanel.removeAll();
				setday(year, month);
				CalendarPanel.setVisible(true);
			}
		});

		PrevBtn.setBounds(320, 10, 78, 32);
		ButtonPanel.add(PrevBtn);

		NextBtn = new JButton();
		NextBtn.setIcon(new ImageIcon(DiaryView.class.getResource("/img/arrow-right-s-line (1).png")));
		NextBtn.setBackground(new Color(255, 255, 255));
		NextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (month == 12) {
					year++;
					month = 1;
				} else {
					month++;
				}
				CalendarPanel.setVisible(false);
				CalendarPanel.removeAll();
				setday(year, month);
				CalendarPanel.setVisible(true);
			}
		});
		NextBtn.setBorder(new EmptyBorder(0,0,0,0));
		NextBtn.setBounds(567, 10, 78, 32);
		ButtonPanel.add(NextBtn);

		YearCombo = new JComboBox<Integer>();
		YearCombo.setBackground(new Color(255, 255, 255));
		YearCombo.setBounds(84, 19, 62, 23);
		YearCombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				year = (int) YearCombo.getSelectedItem();
				CalendarPanel.setVisible(false);
				CalendarPanel.removeAll();
				setday(year, month);
				CalendarPanel.setVisible(true);
			}
		});
		ButtonPanel.add(YearCombo);

		MonthCombo = new JComboBox<Integer>();
		MonthCombo.setBackground(new Color(255, 255, 255));
		MonthCombo.setBounds(158, 20, 69, 23);
		MonthCombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

				month = (int) MonthCombo.getSelectedItem();
				CalendarPanel.setVisible(false);
				CalendarPanel.removeAll();
				setday(year, month);
				CalendarPanel.setVisible(true);
			}
		});
		ButtonPanel.add(MonthCombo);

		YearLabel = new JLabel("");
		YearLabel.setFont(new Font("경기천년제목 Bold", Font.BOLD, 15));
		YearLabel.setHorizontalAlignment(SwingConstants.CENTER);
		YearLabel.setBounds(419, 10, 127, 32);
		ButtonPanel.add(YearLabel);

		CalendarPanel = new JPanel(new GridLayout(0, 7));
		CalendarPanel.setBackground(new Color(255, 255, 255));
		CalendarPanel.setBounds(0, 224, 935, 379);
		frame.getContentPane().add(CalendarPanel, BorderLayout.CENTER);

		JButton backBtn = new JButton("");
		backBtn.setIcon(new ImageIcon(DiaryView.class.getResource("/img/arrow-go-back-line.png")));
		backBtn.setBackground(new Color(192, 192, 192));
		backBtn.setBounds(33, 623, 97, 34);
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainView view = new MainView(UserID);
				view.ShowWindow();
				frame.dispose();
			}
		});
		frame.getContentPane().add(backBtn);

		TitlePanel = new JPanel(new GridLayout(1, 7));

		TitlePanel.setBackground(new Color(255, 255, 255));
		TitlePanel.setBounds(0, 154, 935, 70);
		frame.getContentPane().add(TitlePanel);

		setTitle();
		setYear(year);
		setMonth(month);

	}

	public void setTitle() {
		for (int i = 0; i < titleStr.length; i++) {
			JLabel label = new JLabel(titleStr[i], JLabel.CENTER);
			label.setFont(new Font("경기천년제목 Bold", Font.BOLD, 15));;
			if (i == 0) {
				label.setForeground(Color.red);
			}
			if (i == 6) {
				label.setForeground(Color.blue);
			}
			TitlePanel.add(label);
		}
	}

	public void setYear(int year) {
		CurrentCalendar = Calendar.getInstance();
		year = CurrentCalendar.get(Calendar.YEAR);
		for (int i = year - 50; i < year + 30; i++) {
			YearCombo.addItem(i);
		}

		YearCombo.setSelectedItem(year);
	}

	public void setMonth(int month) {

		for (int i = 1; i <= 12; i++) {
			MonthCombo.addItem(i);
		}

		MonthCombo.setSelectedItem(month + 1);
	}

	public void setday(final int year, final int month) {

		CurrentCalendar.set(year, month - 1, 1);
		int week = CurrentCalendar.get(Calendar.DAY_OF_WEEK);
		int LastDay = CurrentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		YearLabel.setText(new SimpleDateFormat("YYYY년 MMMM").format(CurrentCalendar.getTime()));

		CalendarPanel.removeAll();
		for (int i = 1; i < week; i++) {
			CalendarPanel.add(new JLabel(""));
		}

		for (int i = 1; i <= LastDay; i++) {
			final int day = i;
			DayButtons = new JButton(String.valueOf(i));
			DayButtons.setBackground(Color.white);
			DayButtons.setBorder(new EmptyBorder(0,0,0,0));
			DayButtons.setFont(new Font("경기천년제목 Bold", Font.BOLD, 15));
			Calendar temCal = Calendar.getInstance();
			temCal.set(year, month - 1, i);
			int dayOfWeek = temCal.get(Calendar.DAY_OF_WEEK);
			if(dayOfWeek == Calendar.SATURDAY) {
				DayButtons.setForeground(Color.blue);
			}
			if(dayOfWeek == Calendar.SUNDAY) {
				DayButtons.setForeground(Color.red);
			}
			
			CalendarPanel.add(DayButtons);
			DayButtons.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String selectedDate = String.valueOf(day);
					System.out.println(year + " " + month + " " + selectedDate);
					DiaryInsetView view = new DiaryInsetView(UserID, year, month, day);
					view.ShowWindow();
					frame.dispose();
				}
			});
		}
	}

	public void ShowWindow() {
		// TODO Auto-generated method stub
		frame.setVisible(true);
	}

}
