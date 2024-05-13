package com.showPost.view;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.UpdateBoard.View.UpdateBoardView;
import com.board.control.BoardDAO;
import com.board.control.BoardDAOImpl;

import com.board.control.BoardVO;
import com.boardlist.View.BoardListView;
import com.comment.control.CommentDAO;
import com.comment.control.CommentDAOImpl;
import com.comment.control.CommentVO;
import com.like.control.UserLikeDAO;
import com.like.control.UserLikeDAOImpl;
import com.like.control.UserLikeVO;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Color;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.Font;

public class ShowPost {
	private JFrame frame;
	private int boardNum;
	private JTextField CommentField;
	private JPanel CommentForm;
	private JButton Submit;
	private String userID;
	private JButton deleteButton;
	private int LikeCnt;
	JLabel LikeLabel;

	BoardVO vo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowPost window = new ShowPost(null, null, null, null, 0, 0);
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
	public ShowPost(String userID, String writer, String title, String content, int boardNum, int LikeCnt) {

		this.userID = userID;
		this.boardNum = boardNum;
		this.LikeCnt = LikeCnt;
		initialize(writer, title, content, LikeCnt);
		displayComments();
		UpdateLabel();
		System.out.println(userID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String writer, final String title, final String content, int LikeCnt) {
		frame = new JFrame(title);
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 952, 883);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblWriter = new JLabel("작성자 : " + writer);
		lblWriter.setFont(new Font("굴림", Font.BOLD, 14));
		lblWriter.setBounds(22, 81, 200, 34);
		frame.getContentPane().add(lblWriter);

		JLabel lblTitle = new JLabel("제목 : " + title);
		lblTitle.setFont(new Font("굴림", Font.BOLD, 18));
		lblTitle.setBounds(22, 125, 716, 32);
		frame.getContentPane().add(lblTitle);

		final JButton UpdateBtn = new JButton("수정");
		UpdateBtn.setFont(new Font("굴림", Font.BOLD, 12));
		UpdateBtn.setIcon(new ImageIcon(ShowPost.class.getResource("/img/edit-line.png")));
		UpdateBtn.setBackground(new Color(192, 192, 192));
		UpdateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				BoardVO vo = new BoardVO();
				BoardDAO dao = new BoardDAOImpl();
				vo.setNum(boardNum);
				String postAuthorId = dao.SearchUser(vo);
				if (userID.equals(postAuthorId)) {

					UpdateBoardView view = new UpdateBoardView(userID, writer, title, content, boardNum,
							ShowPost.this.LikeCnt);
					view.showWindow();
					UpdateLabel();
					frame.dispose();

				} else {
					JOptionPane.showMessageDialog(UpdateBtn, "권한이 없습니다.");
				}

			}
		});
		UpdateBtn.setBounds(711, 536, 97, 34);
		frame.getContentPane().add(UpdateBtn);

		JButton DeleteBtn = new JButton("삭제");
		DeleteBtn.setBackground(new Color(192, 192, 192));
		DeleteBtn.setFont(new Font("굴림", Font.BOLD, 12));
		DeleteBtn.setIcon(new ImageIcon(ShowPost.class.getResource("/img/delete-bin-line.png")));
		DeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardVO vo = new BoardVO();
				BoardDAO dao = new BoardDAOImpl();
				vo.setNum(boardNum);
				String postAuthorId = dao.SearchUser(vo);
				if (userID.equals(postAuthorId)) {
					dao.delete(vo);
					frame.dispose();
					BoardListView view = new BoardListView(userID);
					view.populateTable(1, 10);
					view.showWindow();
				} else {
					JOptionPane.showMessageDialog(UpdateBtn, "권한이 없습니다.");
				}

			}
		});
		DeleteBtn.setBounds(820, 536, 97, 34);
		frame.getContentPane().add(DeleteBtn);

		CommentField = new JTextField();
		CommentField.setBounds(131, 798, 657, 32);
		frame.getContentPane().add(CommentField);
		CommentField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Submit.doClick(); // 엔터처리
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		CommentField.setColumns(10);

		Submit = new JButton("등록");
		Submit.setBackground(new Color(240, 240, 240));
		Submit.setFont(new Font("굴림", Font.BOLD, 12));
		Submit.setIcon(new ImageIcon(ShowPost.class.getResource("/img/message-3-fill.png")));
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String CommentText = CommentField.getText();
				if (!CommentText.isEmpty()) {
					CommentVO vo = new CommentVO();
					CommentDAO dao = new CommentDAOImpl();

					vo.setPostNum(boardNum);
					vo.setWriter(userID);
					vo.setContent(CommentText);
					dao.insert(vo);

					// 현재 시간 가져오기
					LocalDateTime currentTime = LocalDateTime.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					String time = currentTime.format(formatter);

					// 새로운 댓글 패널 생성
					JPanel commentPanel = new JPanel();
					commentPanel.setBackground(Color.white);

					JLabel nameLabel = new JLabel(userID);
					nameLabel.setPreferredSize(new Dimension(50, 20));

					JLabel commentLabel = new JLabel(CommentText);
					commentLabel.setPreferredSize(new Dimension(500, 20));
					commentLabel.setHorizontalAlignment(SwingConstants.CENTER);

					JLabel timeLabel = new JLabel(time);
					timeLabel.setPreferredSize(new Dimension(150, 20));
					CommentField.setText(""); // 입력 필드 초기화

					displayComments();

				} else {
					JOptionPane.showMessageDialog(Submit, "댓글을 입력해주세요.");
				}
			}
		});

		Submit.setBounds(800, 797, 117, 33);
		frame.getContentPane().add(Submit);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		scrollPane_1.setBounds(22, 167, 895, 297);
		frame.getContentPane().add(scrollPane_1);

		JTextArea ContentArea = new JTextArea(content);
		scrollPane_1.setViewportView(ContentArea);
		ContentArea.setFont(new Font("굴림", Font.BOLD, 14));
		ContentArea.setEditable(false);
		ContentArea.setWrapStyleWord(true);
		ContentArea.setBorder(new EmptyBorder(0, 0, 0, 0));
		ContentArea.setBackground(new Color(255, 255, 255));

		JButton BackBtn = new JButton("");
		BackBtn.setBackground(new Color(255, 255, 255));
		BackBtn.setIcon(new ImageIcon(ShowPost.class.getResource("/img/arrow-go-back-line.png")));
		BackBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		BackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				BoardListView view = new BoardListView(userID);
				view.populateTable(1, 10);
				view.showWindow();
			}
		});
		BackBtn.setBounds(22, 798, 97, 34);
		frame.getContentPane().add(BackBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 580, 895, 207);
		frame.getContentPane().add(scrollPane);
		scrollPane.setEnabled(true);

		CommentForm = new JPanel();
		scrollPane.setViewportView(CommentForm);
		CommentForm.setBackground(new Color(255, 255, 255));
		CommentForm.setLayout(new BoxLayout(CommentForm, BoxLayout.Y_AXIS));

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(ShowPost.class.getResource("/img/thumb-up-fill.png")));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setBounds(424, 474, 80, 52);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UserLikeVO vo = new UserLikeVO();
				UserLikeDAO dao = new UserLikeDAOImpl();
				vo.setWriter(userID);
				vo.setPostNum(boardNum);
				int LikeSet = dao.LikeUpdate(vo);
				if (LikeSet == 0) {
					dao.ClickLike(vo);
					handleLikeButtonClick();
					frame.dispose();
					showWindow();
					System.out.println(LikeSet);
				} else if (LikeSet == 1) {
					JOptionPane.showMessageDialog(btnNewButton, "이미 추천을 누르셨습니다.");
				}
			}
		});
		frame.getContentPane().add(btnNewButton);

		LikeLabel = new JLabel("추천수 : " + LikeCnt);
		LikeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LikeLabel.setFont(new Font("나눔고딕", Font.BOLD, 15));
		LikeLabel.setBounds(415, 536, 104, 32);
		frame.getContentPane().add(LikeLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ShowPost.class.getResource("/img/diary.jpg")));
		lblNewLabel_1.setBounds(53, 25, 151, 42);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ShowPost.class.getResource("/img/dog2.jpg")));
		lblNewLabel_2.setBounds(12, 6, 49, 65);
		frame.getContentPane().add(lblNewLabel_2);
	}

	public void displayComments() {
		CommentDAO dao = new CommentDAOImpl();
		List<CommentVO> comments = dao.select(boardNum);

		CommentForm.removeAll();
		for (final CommentVO comment : comments) {
			JPanel commentPanel = new JPanel();
			commentPanel.setBackground(Color.white);

			JLabel nameLabel = new JLabel(comment.getWriter());
			nameLabel.setPreferredSize(new Dimension(100, 20));

			JLabel commentLabel = new JLabel(comment.getContent());
			commentLabel.setPreferredSize(new Dimension(500, 20));
			commentLabel.setHorizontalAlignment(SwingConstants.CENTER);

			JLabel timeLabel = new JLabel(comment.getReg_date().toString());
			timeLabel.setPreferredSize(new Dimension(150, 20));

			CommentField.setText("");

			deleteButton = new JButton("삭제");
			deleteButton.setBackground(new Color(255, 255, 255));
			deleteButton.setBorder(new EmptyBorder(0, 0, 0, 0));
			deleteButton.setIcon(new ImageIcon(ShowPost.class.getResource("/img/chat-delete-line.png")));
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 삭제 버튼을 눌렀을 때 실행되는 동작 구현
					if (userID.equals(comment.getWriter())) {
						CommentVO vo = new CommentVO();
						CommentDAO dao = new CommentDAOImpl();

						String writer = comment.getWriter();
						int num = comment.getNum();
						System.out.println(num);
						System.out.println(writer);
						System.out.println(boardNum);

						vo.setNum(num);
						vo.setPostNum(boardNum);
						vo.setWriter(writer);
						dao.delete(vo);

						displayComments();
					} else {
						JOptionPane.showMessageDialog(deleteButton, "권한이 없습니다.");
					}
				}
			});

			commentPanel.add(nameLabel);
			commentPanel.add(commentLabel);
			commentPanel.add(timeLabel);

			commentPanel.add(deleteButton);

			CommentForm.add(commentPanel);
		}

		CommentForm.revalidate();
		CommentForm.repaint();
	}

	public void showWindow() {
		frame.setVisible(true);
	}

	public void UpdateLabel() {
		LikeLabel.setText("추천수 : " + LikeCnt);
	}

	public void handleLikeButtonClick() {
		BoardDAO dao = new BoardDAOImpl();
		BoardVO vo = new BoardVO();
		vo.setNum(boardNum);
		vo.setLikeCnt(LikeCnt);
		dao.LikeInCrease(vo);
		LikeCnt++; // 추천수 증가
		UpdateLabel(); // 라벨 새로고침
		frame.revalidate();
		frame.repaint();
	}
}
