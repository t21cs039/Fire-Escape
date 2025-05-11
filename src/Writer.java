import java.io.*;
import java.net.URL;

public class Writer {
	
	private URL textURL ;
	private String [] ranking = new String [5];
	private Score score;
	
	public Writer(String filename, Score score) {
		textURL = getClass().getResource(filename);
		this.score = score;
		this.readRanking();
		this.sortRanking();
		this.rewriteRanking();
	}

	public void readRanking() {
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(textURL.openStream()))){
			String line = reader.readLine(); 
			int i = 0;
			while((line = reader.readLine()) != null) {
				String [ ] s=line.split(" "); //スペースで文を分けて
				ranking[i] = s[1]; //スコアの方を格納する
				i++;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//ランキングを並び替える
	public void sortRanking() {
		
		int temp ;
		int n = score.getScore();
		for(int i=0; i<5; i++) {
			int m = Integer.parseInt(ranking[i]);
			//より大きいであれば、並び替える
			if(n > m) {
				temp = m;
				ranking[i] = "" + n;
				n = temp;
			}
		}
	}
	
	//ランキングを書き込む
	public void rewriteRanking() {
		
		try (PrintStream out = new PrintStream(new FileOutputStream(textURL.getPath()))){
			out.println("RANKING:"); //最初にRANKINGを書く
			//ランキングを5位まで書く
			for(int i=0; i<5; i++) {
				out.println((i+1) + "." + " " + ranking[i]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
