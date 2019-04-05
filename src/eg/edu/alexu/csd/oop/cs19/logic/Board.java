package eg.edu.alexu.csd.oop.cs19.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Board extends LeaderBoard {

	private String Fname;
	private int score;
	private PrintWriter writer;
	private ArrayList<Integer> All = new ArrayList<>();
	private ArrayList<Integer> top = new ArrayList<>();

	public Board(String name, int score) {
		this.Fname = name + ".txt";
		this.score = score;
	}

	public Board(ArrayList<Integer> top) {
		// TODO Auto-generated constructor stub
		this.top = top;
	}

	public Board() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setFileName() {
		// TODO Auto-generated method stub
		try {
			this.writer = new PrintWriter(new FileOutputStream(this.Fname, true));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addScore() {
		// TODO Auto-generated method stub
		writer.println(score);
		writer.close();
	}

	@Override
	public void getAllscores() {
		File file = new File("Score.txt");
		BufferedReader br;
		if (!file.exists()) {
			return;
		}
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null && !st.equals("")) {
				this.All.add(Integer.parseInt(st.trim()));
			}
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

	}

	@Override
	public void getTopScores() {
		// TODO Auto-generated method stub
		Collections.sort(All);
		int i = 0;
		while (i < 10 && i < All.size()) {
			this.top.add(All.get(All.size() - i - 1));
			i++;
		}
	}

}
