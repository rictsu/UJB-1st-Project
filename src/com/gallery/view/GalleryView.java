package com.gallery.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.gallery.control.GalleryDAO;

import com.gallery.control.GalleryVO;
import com.main.view.MainView;

import java.awt.Font;
import javax.swing.SwingConstants;

public class GalleryView {

	private JFrame frmGallery;
	private JTextField st;
	private JPanel panel_1;
	private JButton btn[];
	private int sc;
	private String joinU;
	private JLabel popupi;

	private JLabel iDLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel SearchUserLabel;

	public GalleryView(String UserID) {
		initialize(UserID);
	}

	private void initialize(final String UserID) {
		frmGallery = new JFrame();
		frmGallery.getContentPane().setBackground(new Color(255, 255, 255));
		frmGallery.setResizable(false);
		frmGallery.setTitle("Gallery");
		frmGallery.setSize(1000, 681);
		frmGallery.getContentPane().setLayout(null);
		final GalleryVO gvo = new GalleryVO();
		gvo.setId(UserID);
		joinU = gvo.getId();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimension2 = frmGallery.getSize();
		int x = (dimension.width - dimension2.width) / 2;
		int y = (dimension.height - dimension2.height) / 2;
		frmGallery.setLocation(x, y);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 152, 960, 436);
		frmGallery.getContentPane().add(scrollPane);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(panel_1);
		panel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_1.setLayout(new GridLayout(0, 4, 5, 10));

		st = new JTextField("");
		st.setBounds(569, 121, 331, 21);
		frmGallery.getContentPane().add(st);
		st.setColumns(10);

		JButton btnSearch = new JButton("");
		btnSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSearch.setIcon(new ImageIcon(GalleryView.class.getResource("/img/searchimg.jpg")));

		btnSearch.setBounds(912, 102, 60, 42);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GalleryDAO gdao = new GalleryDAO();
				String search = st.getText();
				gvo.setId(search);
				if (gdao.outputImage(gvo) == null && !joinU.equals(gvo.getId())) {
					JOptionPane.showMessageDialog(null, "해당 유저가 존재하지 않거나 올린 사진이 없습니다.", "경고",
							JOptionPane.WARNING_MESSAGE);
				} else {
					frmGallery.revalidate();
					frmGallery.repaint();
					panel_1.removeAll();
					panel_1.setVisible(false);
					refresh(gvo);
					panel_1.setVisible(true);
					panel_1.invalidate();
				}
			}
		});

		frmGallery.getContentPane().add(btnSearch);

		iDLabel = new JLabel("사용자 : " + UserID);
		iDLabel.setFont(new Font("굴림", Font.BOLD, 15));
		iDLabel.setBounds(783, 26, 164, 53);
		frmGallery.getContentPane().add(iDLabel);

		JButton BackMainBtn = new JButton("");
		BackMainBtn.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 12));
		BackMainBtn.setIcon(new ImageIcon(GalleryView.class.getResource("/img/arrow-go-back-line.png")));
		BackMainBtn.setBackground(Color.LIGHT_GRAY);
		BackMainBtn.setBounds(12, 598, 97, 33);
		BackMainBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainView view = new MainView(UserID);
				view.ShowWindow();
				frmGallery.dispose();
			}
		});
		frmGallery.getContentPane().add(BackMainBtn);

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(GalleryView.class.getResource("/img/diary.jpg")));
		lblNewLabel_2.setBounds(53, 45, 151, 42);
		frmGallery.getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(GalleryView.class.getResource("/img/dog2.jpg")));
		lblNewLabel_3.setBounds(12, 26, 49, 65);
		frmGallery.getContentPane().add(lblNewLabel_3);

		SearchUserLabel = new JLabel("유저 검색");
		SearchUserLabel.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 14));
		SearchUserLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SearchUserLabel.setBounds(479, 121, 78, 18);
		frmGallery.getContentPane().add(SearchUserLabel);

//      JButton deleteA = new JButton("DeleteALL");
//      deleteA.setBounds(886, 10, 86, 44);
//      frmGallery.add(deleteA);
//      deleteA.addActionListener(new ActionListener() {
//         public void actionPerformed(ActionEvent e) {
//            gdao.deleteAllImage(joinU);
//
//         }
//      });
//      
		refresh(gvo);
		frmGallery.setVisible(true);
		frmGallery.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	private void refresh(final GalleryVO gvo) {
		final GalleryDAO gdao = new GalleryDAO();
		if (joinU.equals(gvo.getId())) {
			JButton inpimageB = new JButton("+"); // 사진 추가 버튼 및 메소드

			inpimageB.setBackground(new Color(255, 255, 255));
			inpimageB.setPreferredSize(new Dimension(200, 200));
			inpimageB.setBackground(new Color(253, 247, 225));
			panel_1.add(inpimageB);
			
			inpimageB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					uploadImage();
					frmGallery.revalidate();
					frmGallery.repaint();
					panel_1.revalidate();
					panel_1.repaint();
				}
			});
		}
		
		
		btn = new JButton[gdao.getCount(gvo)];
		sc = gdao.getmaxIno(gvo);
		for (int i = 0; i < gdao.getCount(gvo); i++) {
			btn[i] = new JButton();
			panel_1.add(btn[i]);
			final int bn = i;
			final int ino = gdao.getDbino(gvo)[i];
			try {
				final ImageIcon icon = new ImageIcon(gdao.outputImage(gvo)[i]);
				Image img = icon.getImage();
				Image nimg = img.getScaledInstance(235, 210, 0);
				ImageIcon nicon = new ImageIcon(nimg);
				btn[i].setIcon(nicon);
				btn[i].setBackground(new Color(255, 255, 255));
				btn[i].setPreferredSize(new Dimension(200, 200));
				btn[i].setBackground(new Color(253, 247, 225));
				btn[i].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						clickImage(gvo, btn[bn], icon, ino, gdao.getFileName(ino), nicon);
						popupi.invalidate();
						popupi.repaint();
						popupi = new JLabel();
						popupi.setIcon(icon);
					}
				});
				panel_1.invalidate();
				panel_1.repaint();
			} catch (

			Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void uploadImage() {
		final JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "JPG", "JPEG", "PNG");
		chooser.setFileFilter(filter);
		chooser.setMultiSelectionEnabled(false); // 다중선택 불가

		try {
			GalleryDAO gdao = new GalleryDAO();
			final GalleryVO gvo = new GalleryVO();
			chooser.showOpenDialog(null);
			gvo.setId(joinU);
			gvo.setImagename(chooser.getSelectedFile().getName());
			if (gdao.getCount(gvo) == 0)
				gvo.setNum(0);
			else
				gvo.setNum(gdao.getmaxIno(gvo) + 1);
			gvo.setIpath(chooser.getSelectedFile() + "");
			gdao.insertImage(gvo);

			final JButton nib = new JButton(); // 추가 된 이미지 화면에 표시
			panel_1.add(nib);
			final ImageIcon icon = new ImageIcon(chooser.getSelectedFile() + "");
			Image img = icon.getImage();
			Image nimg = img.getScaledInstance(235, 210, 0);
			ImageIcon nicon = new ImageIcon(nimg);
			nib.setIcon(nicon);
			nib.setBackground(new Color(255, 255, 255));
			nib.setPreferredSize(new Dimension(200, 200));
			panel_1.invalidate();
			panel_1.repaint();
			nib.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clickImage(gvo, nib, icon, gvo.getNum(), chooser.getSelectedFile().getName(), nicon);
				}
			});
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
		}

	}

	private void clickImage(GalleryVO gvo, final JButton c, ImageIcon icon, final int ino, String name, ImageIcon nicon) {
		final JFrame cf = new JFrame(name);
		cf.setVisible(true);
		cf.revalidate();
		cf.repaint();
		if (icon.getIconWidth() > 900 || icon.getIconHeight() > 900) {
			Image img = icon.getImage();
			Image imp = img.getScaledInstance(900, 900, 0);
			icon = new ImageIcon(imp);
		}
		cf.setSize(icon.getIconWidth(), icon.getIconHeight());
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimension2 = cf.getSize();
		if (dimension2.width < 150 || dimension2.height < 150) {
			cf.setSize(200, 200);
		}
		int x = (dimension.width - dimension2.width) / 2;
		int y = (dimension.height - dimension2.height) / 2;
		cf.setLocation(x, y);
		cf.setResizable(false);
		// 너무 작은 사진이 있을시 버튼이 가려 프레임 크기 최소값 설정.
		JPanel np = new JPanel();
		cf.getContentPane().add(np, BorderLayout.CENTER);
		np.setLayout(new BorderLayout(5, 5));

		System.out.println("clickImage ActionListener");
		
		popupi = new JLabel();
		np.add(popupi, BorderLayout.CENTER);
		popupi.setIcon(icon);

		JPanel menu = new JPanel();
		np.add(menu, BorderLayout.SOUTH);

		if (joinU.equals(gvo.getId())) {
			JButton editb = new JButton("사진 변경");
			menu.add(editb, BorderLayout.SOUTH);

			JButton deleteb = new JButton("삭제");
			menu.add(deleteb, BorderLayout.SOUTH);

			editb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "JPG", "JPEG", "PNG");
					chooser.setFileFilter(filter);
					chooser.setMultiSelectionEnabled(false);
					try {
						GalleryDAO gdao = new GalleryDAO();
						final GalleryVO gvo = new GalleryVO();
						chooser.showOpenDialog(null);
						gdao.deleteImage(joinU, ino);
						gvo.setImagename(chooser.getSelectedFile().getName());
						gvo.setNum(ino);
						gvo.setIpath(chooser.getSelectedFile() + "");
						gvo.setId(joinU);
						gdao.insertImage(gvo);
						final ImageIcon icon = new ImageIcon(chooser.getSelectedFile() + "");
						Image img = icon.getImage();
						Image nimg = img.getScaledInstance(235, 210, 0);
						ImageIcon nicon = new ImageIcon(nimg);
						cf.setSize(nicon.getIconWidth(), nicon.getIconHeight());
						Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
						Dimension dimension2 = cf.getSize();
						int x = (dimension.width - dimension2.width) / 2;
						int y = (dimension.height - dimension2.height) / 2;
						
						cf.dispose();
						cf.setLocation(x, y);
						if (dimension2.width < 150 || dimension2.height < 150) {
							cf.setSize(200, 200);
						}
						cf.setTitle(chooser.getSelectedFile().getName());
						
						c.setIcon(icon);
						c.setBackground(new Color(255, 255, 255));
						c.setPreferredSize(new Dimension(200, 200));
						c.invalidate();
						c.repaint();
						panel_1.invalidate();
						panel_1.repaint();

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					}
				}
			});

			deleteb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					final JFrame frame = new JFrame("경고");
					frame.setResizable(false);
					frame.setBackground(new Color(255, 255, 255));
					frame.setBounds(100, 100, 250, 180);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					Dimension dimension2 = frame.getSize();
					int x = (dimension.width - dimension2.width) / 2;
					int y = (dimension.height - dimension2.height) / 2;
					frame.setLocation(x, y);

					JPanel panel = new JPanel();
					panel.setBackground(new Color(255, 255, 255));
					frame.getContentPane().add(panel, BorderLayout.CENTER);
					panel.setLayout(null);

					JLabel lblNewLabel = new JLabel("이미지를 삭제하시겠습니까?");
					lblNewLabel.setBounds(36, 23, 201, 52);
					panel.add(lblNewLabel);

					JButton btnrd = new JButton("삭제");
					btnrd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					btnrd.setBounds(36, 85, 65, 23);
					panel.add(btnrd);

					btnrd.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							GalleryDAO gdao = new GalleryDAO();
							if (ino == sc)
								sc = sc - 1;
							c.setVisible(false);
							panel_1.remove(c);
							gdao.deleteImage(joinU, ino);
							panel_1.invalidate();
							frame.dispose();
							cf.dispose();
						}
					});

					JButton btnNewButton_1 = new JButton("취소");
					btnNewButton_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.dispose();
						}
					});
					btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					btnNewButton_1.setBounds(128, 85, 65, 23);
					panel.add(btnNewButton_1);
				}
			});
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GalleryView window = new GalleryView(null);
					window.frmGallery.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void ShowWindow() {
		frmGallery.setVisible(true);
	}
}