package com.dat.cocaro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BanCo extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int SOHANG = 10;
	public static final int SOCOT = 10;
	public static final int SIZE = 50;
	public static final String RESET = "0:0";
	public static final int PADDING = 50;

	private Color mauSac;
	private String tySo;
	private NguoiChoi nguoiChoi1, nguoiChoi2;
	private char loaiQC = 'X';

	public BanCo(Color mauSac, String ten1, String ten2) {
		this.mauSac = mauSac;
		nguoiChoi1 = new NguoiChoi(QuanCo.X, ten1);
		nguoiChoi2 = new NguoiChoi(QuanCo.O, ten2);
		tySo = RESET;

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				char type = danhCo(e.getX(), e.getY(), loaiQC);
				kiemTraThangCuoc();
				loaiQC = type;
			}
		});
		requestFocus();

	}

	public void veBanCo2(Graphics2D g) {
		setBackground(mauSac);
		for (int i = 0; i <= SOHANG; i++) {
			g.setColor(Color.yellow);
			g.drawLine(PADDING, i * SIZE + PADDING, SOCOT * SIZE + PADDING, i * SIZE + PADDING);
			g.setColor(Color.white.brighter());
			g.drawLine(i * SIZE + PADDING, PADDING, i * SIZE + PADDING, SOCOT * SIZE + PADDING);
		}
		g.setColor(Color.yellow);
		Font f = new Font("Tahoma", Font.BOLD, 30);
		g.setFont(f);
		int h = getFontMetrics(f).getHeight();
		int w = getFontMetrics(f).stringWidth("X");

		for (int i = 0; i <= SOHANG; i++) {
			for (int j = 0; j <= SOCOT; j++) {
				int x = j * SIZE + SIZE / 2;
				int y = i * SIZE + SIZE / 2;
				QuanCo qc = new QuanCo(x + PADDING, y + PADDING, ' ');
				if (nguoiChoi1.getListQC().indexOf(qc) >= 0) {
					g.setColor(Color.yellow);
					g.drawString("X", x + PADDING - w / 2, y + PADDING + h * 1 / 3);

				} else if (nguoiChoi2.getListQC().indexOf(qc) >= 0) {
					g.setColor(Color.cyan);
					g.drawString("O", x + PADDING - w / 2, y + PADDING + h * 1 / 3);

				}
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		veMenu(g);
		veBanCo2((Graphics2D) g);
	}

	private void veMenu(Graphics g) {
		Font f = new Font("Tahoma", Font.PLAIN, 30);
		g.setFont(f);
		g.setColor(Color.yellow);
		g.drawString(nguoiChoi1.getTen(), 590, 250);

		Font f1 = new Font("Tahoma", Font.PLAIN, 20);
		g.setFont(f1);
		g.setColor(Color.white);
		g.drawString("VS", 663, 250);

		g.setFont(f);
		g.setColor(Color.CYAN);
		g.drawString(nguoiChoi2.getTen(), 700, 250);

		Font f2 = new Font("Tahoma", Font.PLAIN, 50);
		g.setFont(f2);
		g.setColor(Color.white);
		g.drawString(tySo, 640, 300);

	}

	public char danhCo(int x, int y, char loaiQC) {
		if (loaiQC == nguoiChoi1.getLoaiQC()) {
			if (nguoiChoi1.danhCo(x, y, nguoiChoi2) == true) {
				repaint();
				return nguoiChoi2.getLoaiQC();
			}
		} else {
			if (nguoiChoi2.danhCo(x, y, nguoiChoi1) == true) {
				repaint();
				return nguoiChoi1.getLoaiQC();
			}
		}
		return loaiQC;
	}

	public void xoaBanCo() {
		nguoiChoi1.xoaCo();
		nguoiChoi2.xoaCo();
	}

	public void choiLai() {
		xoaBanCo();
		tySo = RESET;
	}

	public char getLoaiQCNC1() {
		return nguoiChoi1.getLoaiQC();
	}

	public void kiemTraThangCuoc() {
		if (nguoiChoi1.getListQC().size() + nguoiChoi2.getListQC().size() < 9) {
			return;
		}

		if (kiemTraThangNgang(nguoiChoi1) == true || kiemTraThangDoc(nguoiChoi1) == true
				|| kiemTraThangCheoPhai(nguoiChoi1) == true || kiemTraThangCheoTrai(nguoiChoi1) == true) {
			String item[] = tySo.split(":");
			tySo = Integer.parseInt(item[0]) + 1 + ":" + item[1];
			JOptionPane.showMessageDialog(null, "Người chơi 1 thắng " + tySo);

			xoaBanCo();
		} else if (kiemTraThangNgang(nguoiChoi2) || kiemTraThangDoc(nguoiChoi2) || kiemTraThangCheoPhai(nguoiChoi2)
				|| kiemTraThangCheoTrai(nguoiChoi2) == true) {
			String item[] = tySo.split(":");
			tySo = item[0] + ":" + (Integer.parseInt(item[1]) + 1);
			JOptionPane.showMessageDialog(null, "Người chơi 2 thắng " + tySo);

			xoaBanCo();

		}

	}

	private Comparator<QuanCo> sxNgangPhai = new Comparator<QuanCo>() {

		@Override
		public int compare(QuanCo qc1, QuanCo qc2) {
			return qc1.getX() - qc2.getX();
		}
	};

	private Comparator<QuanCo> sxNgangTrai = new Comparator<QuanCo>() {

		@Override
		public int compare(QuanCo qc1, QuanCo qc2) {
			return -(qc1.getX() - qc2.getX());
		}
	};
	private Comparator<QuanCo> sxDoc = new Comparator<QuanCo>() {

		@Override
		public int compare(QuanCo qc1, QuanCo qc2) {
			return qc1.getX() - qc2.getX();
		}
	};

	private boolean kiemTraThangCheoPhai(NguoiChoi nguoiChoi) {
		nguoiChoi.getListQC().sort(sxNgangPhai);
		for (int i = 0; i < nguoiChoi.getListQC().size(); i++) {
			int demQC = 1;
			QuanCo qcTruoc = nguoiChoi.getListQC().get(i);

			for (int j = i + 1; j < nguoiChoi.getListQC().size(); j++) {
				QuanCo qcSau = nguoiChoi.getListQC().get(j);
				if (qcSau.getY() == qcTruoc.getY() + SIZE && qcSau.getX() == qcTruoc.getX() + SIZE) {
					demQC++;
					qcTruoc = qcSau;
				}
				if (demQC >= 5) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean kiemTraThangCheoTrai(NguoiChoi nguoiChoi) {
		nguoiChoi.getListQC().sort(sxNgangTrai);
		for (int i = 0; i < nguoiChoi.getListQC().size(); i++) {
			int demQC = 1;
			QuanCo qcTruoc = nguoiChoi.getListQC().get(i);

			for (int j = i + 1; j < nguoiChoi.getListQC().size(); j++) {
				QuanCo qcSau = nguoiChoi.getListQC().get(j);
				if (qcSau.getY() == qcTruoc.getY() + SIZE && qcSau.getX() == qcTruoc.getX() - SIZE) {
					demQC++;
					qcTruoc = qcSau;
				}
				if (demQC >= 5) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean kiemTraThangDoc(NguoiChoi nguoiChoi) {
		nguoiChoi.getListQC().sort(sxDoc);
		for (int i = 0; i < nguoiChoi.getListQC().size(); i++) {
			int demQC = 1;
			QuanCo qcTruoc = nguoiChoi.getListQC().get(i);

			for (int j = 0; j < nguoiChoi.getListQC().size(); j++) {
				QuanCo qcSau = nguoiChoi.getListQC().get(j);
				if (qcSau.getY() == qcTruoc.getY() + SIZE && qcSau.getX() == qcTruoc.getX()) {
					demQC++;
					qcTruoc = qcSau;
				}
				if (demQC >= 5) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean kiemTraThangNgang(NguoiChoi nguoiChoi) {
		nguoiChoi.getListQC().sort(sxNgangPhai);
		for (int i = 0; i < nguoiChoi.getListQC().size(); i++) {
			int demQC = 1;
			QuanCo qcTruoc = nguoiChoi.getListQC().get(i);

			for (int j = i + 1; j < nguoiChoi.getListQC().size() - 1; j++) {
				QuanCo qcSau = nguoiChoi.getListQC().get(j);
				if (qcSau.getX() == qcTruoc.getX() + 50 && qcSau.getY() == qcTruoc.getY()) {
					demQC++;
					qcTruoc = qcSau;
				}
				if (demQC >= 5) {
					return true;
				}
			}
		}
		return false;
	}
}
