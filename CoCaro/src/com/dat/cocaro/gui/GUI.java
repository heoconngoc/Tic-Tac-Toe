package com.dat.cocaro.gui;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;

import com.dat.cocaro.BanCo;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int W_FRAME = 800;
	private static final int H_FRAME = 630;

	public GUI() {
		initViews();
	}

	private void initViews() {
		setSize(W_FRAME, H_FRAME);
		setTitle("Game Caro");
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new CardLayout());
		add(new BanCo(Color.black, "Nam", "Hạnh"));
	}

}
