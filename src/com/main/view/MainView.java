package com.main.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.Map.view.MapView;
import com.MyPageView.com.MyPageView;
import com.board.control.BoardDAO;
import com.board.control.BoardDAOImpl;
import com.board.control.BoardVO;
import com.boardlist.View.BoardListView;
import com.diary.control.DiaryDAO;
import com.diary.control.DiaryDAOImpl;
import com.diary.control.DiaryVO;
import com.diary.view.DiaryView;
import com.gallery.view.GalleryView;
import com.join.VO.JoinVo;
import com.join.dao.JoinDAO;
import com.login.view.LoginMain;
import com.showPost.view.ShowPost;
import com.updateUserInfo.view.UpdateUserInfoView;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.border.EmptyBorder;

public class MainView {
	private final String UserID;
	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private int LikeCnt;
	private int currentPage = 1;
	private int itemsPerPage;
	private int year;
	private int month;
	private int day;
	private JLabel WeatherLabel;
	private JLabel WeatherImgLabel;
	private Calendar currentCalendar;
	private JButton deleteButton;
	private JPanel CalendarForm;
	private JTextArea WeatherInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView(null);
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
	public MainView(String id) {
		this.UserID = id;
		initialize(UserID);
		populateTable(1, 3);
		displaySchedule();
		getWeather();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String UserID) {
		currentCalendar = Calendar.getInstance();
		year = currentCalendar.get(Calendar.YEAR);
		month = currentCalendar.get(Calendar.MONTH) + 1;
		day = currentCalendar.get(Calendar.DAY_OF_MONTH);

		frame = new JFrame("Main");
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1050, 856);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		tableModel = new DefaultTableModel();

		tableModel.addColumn("No.");
		tableModel.addColumn("작성자");
		tableModel.addColumn("제목");
		tableModel.addColumn("시간");
		tableModel.addColumn("추천");

		table = new JTable(tableModel);

		DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
		centerRender.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRender);
		}

		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(11, 85, 240, 722);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel BoardLabel = new JLabel("게시판");
		BoardLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BoardLabel.setIcon(new ImageIcon(MainView.class.getResource("/img/list-check.png")));
		BoardLabel.setBounds(48, 53, 144, 59);
		panel.add(BoardLabel);
		BoardLabel.setFont(new Font("경기천년제목 Medium", Font.PLAIN, 33));

		JLabel CalendarLabel = new JLabel("캘린더");
		CalendarLabel.setIcon(new ImageIcon(MainView.class.getResource("/img/calendar-check-line.png")));
		CalendarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CalendarLabel.setFont(new Font("경기천년제목 Medium", Font.PLAIN, 33));
		CalendarLabel.setBounds(48, 192, 144, 59);
		panel.add(CalendarLabel);

		JLabel GalleryLabel = new JLabel("갤러리");
		GalleryLabel.setIcon(new ImageIcon(MainView.class.getResource("/img/gallery-line.png")));
		GalleryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GalleryLabel.setFont(new Font("경기천년제목 Medium", Font.PLAIN, 33));
		GalleryLabel.setBounds(48, 359, 144, 59);
		panel.add(GalleryLabel);
		
		JLabel hospitalLabel = new JLabel("병원찾기");
		hospitalLabel.setIcon(new ImageIcon(MainView.class.getResource("/img/hospital-line.png")));
		hospitalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hospitalLabel.setFont(new Font("경기천년제목 Medium", Font.PLAIN, 33));
		hospitalLabel.setBounds(48, 527, 144, 59);
		panel.add(hospitalLabel);
		
		BoardLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				BoardListView view = new BoardListView(UserID);
				view.showWindow();
				frame.dispose();
			}
		});

		CalendarLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				DiaryView view = new DiaryView(UserID);
				view.ShowWindow();
				frame.dispose();
			}
		});

		GalleryLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				GalleryView view = new GalleryView(UserID);
				view.ShowWindow();
				frame.dispose();
			}
		});
		
		hospitalLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				MapView view = new MapView();
				view.ShowWindow();
			}
		});
		
		JLabel lblNewLabel = new JLabel("사용자 : " + UserID);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel.setBounds(654, 29, 164, 53);
		lblNewLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JoinVo vo = new JoinVo();
				vo.setId(UserID);
				JoinDAO dao = new JoinDAO();
				dao.selectUser(vo);
				String tel = vo.getTel();
				String name = vo.getName();
				String dogname = vo.getDogname();
				String dogbirth = vo.getDogbirth();
				String dogsex = vo.getDogsex();

				MyPageView view = new MyPageView(UserID, tel, name, dogname, dogsex, dogbirth);
				view.showWindow();
				frame.dispose();
			}
		});
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(MainView.class.getResource("/img/dog2.jpg")));
		lblNewLabel_2.setBounds(25, 10, 49, 65);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(MainView.class.getResource("/img/diary.jpg")));
		lblNewLabel_3.setBounds(66, 29, 151, 42);
		frame.getContentPane().add(lblNewLabel_3);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(263, 85, 759, 722);

		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_1 = new JLabel("인기 게시물");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setIcon(new ImageIcon(MainView.class.getResource("/img/thumb-up-fill.png")));
		lblNewLabel_1_1.setBounds(12, 10, 201, 59);
		panel_1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("경기천년제목 Medium", Font.PLAIN, 33));

		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowGrid(false);
		table.setDragEnabled(false);
		table.getTableHeader().setBackground(Color.white);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(140);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(140);
		table.setRowHeight(77);

		table.setDropMode(DropMode.USE_SELECTION);
		table.setEnabled(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) { // 한 번 클릭 시
					int column = table.columnAtPoint(e.getPoint());
					int row = table.rowAtPoint(e.getPoint());
					if (column == 2) { // 제목 열 클릭 시
						String num = table.getValueAt(row, 0).toString();
						int boardNum = Integer.parseInt(num);
						// 해당 게시글로 이동
						System.out.println("클릭된 제목: " + boardNum);

						BoardDAO dao = new BoardDAOImpl();
						BoardVO vo = new BoardVO();

						vo.setNum(boardNum);
						dao.selectBoard(vo);
						String writer = vo.getName();
						String title = vo.getTitle();
						String content = vo.getContent();
						LikeCnt = vo.getLikeCnt();
						if (writer != null && title != null && content != null) { // 데이터가 있는지 확인
							// 데이터가 있다면 해당 정보를 이용하여 ShowPost 창을 열거나 처리합니다.
							ShowPost post = new ShowPost(UserID, writer, title, content, boardNum, LikeCnt);
							post.showWindow();
							frame.dispose();
						} else {
							System.out.println("게시글을 찾을수 없습니다.");
						}
					}
				}
			}

		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(12, 79, 724, 254);
		panel_1.add(scrollPane);

		JPanel WeatherPanel = new JPanel();
		WeatherPanel.setBackground(new Color(255, 255, 255));
		WeatherPanel.setBounds(12, 424, 349, 288);
		panel_1.add(WeatherPanel);
		WeatherPanel.setLayout(null);

		WeatherLabel = new JLabel("New label");
		WeatherLabel.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 16));
		WeatherLabel.setHorizontalAlignment(SwingConstants.CENTER);
		WeatherLabel.setBounds(128, 10, 95, 27);
		WeatherPanel.add(WeatherLabel);

		WeatherImgLabel = new JLabel("");
		WeatherImgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		WeatherImgLabel.setBounds(12, 35, 325, 160);
		WeatherPanel.add(WeatherImgLabel);

		WeatherInfo = new JTextArea();
		WeatherInfo.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 14));
		WeatherInfo.setEditable(false);
		WeatherInfo.setBounds(12, 218, 325, 60);
		WeatherPanel.add(WeatherInfo);

		CalendarForm = new JPanel();
		CalendarForm.setBackground(new Color(255, 255, 255));
		CalendarForm.setLayout(new GridLayout(0, 1));
		CalendarForm.setBounds(387, 424, 349, 288);
		panel_1.add(CalendarForm);

		JLabel lblNewLabel_1_1_1 = new JLabel("오늘의 할 일");
		lblNewLabel_1_1_1.setIcon(new ImageIcon(MainView.class.getResource("/img/check-line.png")));
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("경기천년제목 Medium", Font.PLAIN, 33));
		lblNewLabel_1_1_1.setBounds(387, 357, 201, 59);
		panel_1.add(lblNewLabel_1_1_1);

		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String time = currentTime.format(formatter);

		JLabel timeLabel = new JLabel(time);
		timeLabel.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 14));
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setBounds(600, 385, 136, 29);
		panel_1.add(timeLabel);

		JLabel TodayWeatherLabel = new JLabel("오늘의 날씨\r\n");
		TodayWeatherLabel.setIcon(new ImageIcon(MainView.class.getResource("/img/sun-cloudy-fill.png")));
		TodayWeatherLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TodayWeatherLabel.setFont(new Font("경기천년제목 Medium", Font.PLAIN, 33));
		TodayWeatherLabel.setBounds(12, 357, 201, 59);
		panel_1.add(TodayWeatherLabel);

		JLabel lblNewLabel_4 = new JLabel("정보수정");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_4.setBounds(830, 34, 78, 42);
		lblNewLabel_4.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				UpdateUserInfoView infoView = new UpdateUserInfoView(UserID);
				infoView.ShowWindow();
				frame.dispose();
			}
		});
		frame.getContentPane().add(lblNewLabel_4);

		JLabel LogOutBtn = new JLabel("로그아웃");
		LogOutBtn.setHorizontalAlignment(SwingConstants.CENTER);
		LogOutBtn.setFont(new Font("굴림", Font.BOLD, 14));
		LogOutBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				LoginMain loginMain = new LoginMain();
				loginMain.ShowWindow();
				frame.dispose();
			}
		});

		LogOutBtn.setBounds(920, 34, 78, 42);
		frame.getContentPane().add(LogOutBtn);

	}

	public void ShowWindow() {
		frame.setVisible(true);
	}

	public void populateTable(int currentPage, int itemsPerPage) {

		this.setCurrentPage(currentPage);
		this.setItemsPerPage(itemsPerPage);
		BoardDAO dao = new BoardDAOImpl();
		List<BoardVO> boardData = dao.LikeCntASC();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // 기존의 테이블 내용 삭제
		int startIndex = (currentPage - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, boardData.size());

		// 첫 번째부터 itemsPerPage번째 게시글까지만 테이블에 추가
		for (int i = startIndex; i < endIndex; i++) {
			if (boardData.get(i) != null) {
				model.addRow(boardData.get(i).toArray());
			}
		}

	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public void displaySchedule() {
		DiaryDAO dao = new DiaryDAOImpl();
		List<DiaryVO> diaryvo = dao.select(UserID, year, month, day);
		CalendarForm.removeAll();
		for (final DiaryVO diary : diaryvo) {
			JPanel CalendarPanel = new JPanel();
			CalendarPanel.setBackground(Color.white);

			JLabel contentLabel = new JLabel(diary.getContent());
			contentLabel.setPreferredSize(new Dimension(200, 20));
			contentLabel.setHorizontalAlignment(SwingConstants.CENTER);

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
						CalendarForm.revalidate();
						CalendarForm.repaint();
					} else {
						JOptionPane.showMessageDialog(deleteButton, "권한이 없습니다.");
					}
				}
			});

			CalendarPanel.add(contentLabel);
			CalendarPanel.add(deleteButton);
			CalendarForm.add(CalendarPanel);

		}

	}

	public void getWeather() {
		try {
			// 위도 경도 설정
			String location = "의정부시 날씨";
			String lat = "37.7423707";
			String lon = "127.0518480";
			WeatherLabel.setText(location);
			String urlstr = "http://api.openweathermap.org/data/2.5/weather?" + "lat=" + lat + "&lon=" + lon
					+ "&appid=8cf9e0ff067ee01fee7ba4bbd86891e5";
			URL url = new URL(urlstr);
			BufferedReader bf;
			String line;
			StringBuilder result = new StringBuilder();
			bf = new BufferedReader(new InputStreamReader(url.openStream()));

			while ((line = bf.readLine()) != null) {
				result.append(line);
			}

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(result.toString());

			JSONArray weatherArray = (JSONArray) jsonObj.get("weather");
			JSONObject obj = (JSONObject) weatherArray.get(0);
			String weatherMain = (String) obj.get("main");

			String weatherString = "";
			JSONObject mainArray = (JSONObject) jsonObj.get("main");
			double ktemp = Double.parseDouble(mainArray.get("temp").toString());
			double temp = ktemp - 273.15;
			String TempStr = String.format("온도 : %.2f\n", temp);
			JoinVo vo = new JoinVo();
			JoinDAO dao = new JoinDAO();
			vo.setId(UserID);
			dao.selectUser(vo);
			String DogName = vo.getDogname();

			switch (weatherMain) {
			case "Clouds":
				weatherString = "흐림";
				String labelText = "날씨 : " + weatherString + System.lineSeparator() + "비가 올수도 있습니다. " + DogName
						+ "(이)와 산책하기 애매합니다!" + System.lineSeparator() + TempStr;
				WeatherInfo.setText(labelText);
				WeatherImgLabel.setIcon(new ImageIcon(getClass().getResource("/img/clouds.jpg")));
				break;
			case "Clear":
				weatherString = "맑음";
				labelText = "날씨 : " + weatherString + "\n" + "화창한 날씨입니다. " + DogName + "(이)와 산책하기 좋은 날입니다!"
						+ System.lineSeparator() + TempStr;
				WeatherInfo.setText(labelText);
				WeatherImgLabel.setIcon(new ImageIcon(getClass().getResource("/img/clear.jpg")));
				break;
			case "Snow":
				weatherString = "눈";
				labelText = "날씨 : " + weatherString + System.lineSeparator() + "눈이 옵니다. " + DogName + "(이)와 집에서 놀죠!"
						+ System.lineSeparator() + TempStr;
				WeatherInfo.setText(labelText);
				WeatherImgLabel.setIcon(new ImageIcon(getClass().getResource("/img/snow.jpg")));
				break;
			case "Rain":
				weatherString = "비";
				labelText = "날씨 : " + weatherString + System.lineSeparator() + "비가 옵니다. " + DogName + "(이)와 집에서 놀죠!"
						+ System.lineSeparator() + TempStr;
				WeatherInfo.setText(labelText);
				WeatherImgLabel.setIcon(new ImageIcon(getClass().getResource("/img/rain.jpg")));
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
