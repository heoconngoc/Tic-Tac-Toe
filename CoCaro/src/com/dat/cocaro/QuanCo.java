package com.dat.cocaro;

public class QuanCo {
	public static final char X = 'X';
	public static final char O = 'Y';

	private int x, y;
	private char loai;

	public QuanCo(int x, int y, char loai) {
		this.x = x;
		this.y = y;
		this.loai = loai;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public char getLoai() {
		return loai;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof QuanCo) {
			QuanCo qc2 = (QuanCo) obj;
			return x == qc2.x && y == qc2.y;
		}
		return super.equals(obj);
	}
}
