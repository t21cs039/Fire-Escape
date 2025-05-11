import java.awt.Font;
import java.awt.Graphics;
import java.io.*;
import java.net.URL;

public class Reader {
	
	private URL textURL ;
	public Reader(String filename) {
		textURL = getClass().getResource(filename);//ファイルのURL習得
		
	}
	
	//ファイルを読み取る
	public void read(Graphics g) {

		int y=100;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(textURL.openStream()))) {
			String line = reader.readLine();
	
			g.setFont(g.getFont().deriveFont(Font.PLAIN, 16));
			while (line != null) {
				g.drawString(line, 180, y);//画面に表示する
				y+=50;
				line = reader.readLine();//次の行を読みとる
			}
		} catch (IOException e) {
			e.printStackTrace();
	    }
		
		g.setFont(g.getFont().deriveFont(Font.BOLD
				));
		g.drawString("> Return", 260, 400);
	}
}
