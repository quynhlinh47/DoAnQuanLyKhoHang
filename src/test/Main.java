package test;

import javax.swing.SwingUtilities;

import view.QuanLyKhoHangGUI;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new QuanLyKhoHangGUI());
	}
}
