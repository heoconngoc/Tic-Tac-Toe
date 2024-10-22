package com.dat.cocaro;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class NguoiChoi {
	private char loaiQC;
	private ArrayList<QuanCo> listQC;
	private String ten;

	public NguoiChoi(char loaiQC, String ten) {
		this.loaiQC = loaiQC;
		this.ten = ten;
		listQC = new ArrayList<QuanCo>();
	}

	public boolean danhCo(int x, int y, NguoiChoi ngChoi2) {
		// TODO Kiểm tra tọa độ x, y
		if (x < BanCo.PADDING || y < BanCo.PADDING || x > BanCo.SOCOT * BanCo.SIZE + BanCo.PADDING
				|| y > BanCo.SOHANG * BanCo.SIZE + BanCo.PADDING) {
			JOptionPane.showMessageDialog(null, "Vị trí đánh không phù hợp");
			return false;
		}

		int cot = (x - BanCo.PADDING) / BanCo.SIZE;
		int hang = (y - BanCo.PADDING) / BanCo.SIZE;

		x = cot * BanCo.SIZE + BanCo.SIZE / 2 + BanCo.PADDING;
		y = hang * BanCo.SIZE + BanCo.SIZE / 2 + BanCo.PADDING;

		QuanCo qc = new QuanCo(x, y, loaiQC);
		int viTri = listQC.indexOf(qc);
		if (viTri >= 0) {
			JOptionPane.showMessageDialog(null, "Err: Vị trí đã có quân cờ " + x + "," + y);
			return false;
		}

		viTri = ngChoi2.listQC.indexOf(qc);
		if (viTri >= 0) {
			JOptionPane.showMessageDialog(null, "Err: Vị trí đã có quân cờ " + x + "," + y);
			return false;
		}

		listQC.add(qc);
		return true;
	}

	public void xoaCo() {
		listQC.clear();
	}

	public ArrayList<QuanCo> getListQC() {
		return listQC;
	}

	public String getTen() {
		return ten;
	}

	public char getLoaiQC() {
		return loaiQC;
	}
}
