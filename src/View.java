import java.awt.*;
import java.io.*;
import java.net.URL;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel {

    private Model model;
    private Image backgroundImage;
    private Font customFont;
	private Font titleFont;
	private URL fontURL = getClass().getResource("PixelMplus12-Regular.ttf"); //普通のフォント
    private URL titleFontURL = getClass().getResource("SamuraiBlast.ttf");//タイトルフォント
    
    public View(Model model) {
        this.model = model;
        // 画像を読み込む．画像ファイルは src においておくと bin に自動コピーされる
        backgroundImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("background.jpg"));
        
        try {
            //create the font to use. Specify the size!
        	customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontURL.getPath())).deriveFont(32f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
            
            titleFont = Font.createFont(Font.TRUETYPE_FONT, new File(titleFontURL.getPath())).deriveFont(60f);
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(titleFont);
            
            
        } catch (IOException|FontFormatException e) {
            e.printStackTrace();
        } 
        
    }

	/**
     * 画面を描画する
     * @param g  描画用のグラフィックスオブジェクト
     */
    @Override
    public void paintComponent(Graphics g) {
        // 画面をいったんクリア
        clear(g);
        g.drawImage(backgroundImage, 0,0,Game.WIN_WIDTH,Game.WIN_HEIGHT,this);
        // 描画する
        g.setFont(customFont);
        g.setColor(Color.WHITE);
        
        // モデルから状態を取得し，状態に応じて描画
        State state = model.getState();
        state.paintComponent(g);
        
        // Linux でアニメーションをスムーズに描画するため（描画結果が勝手に間引かれることを防ぐ）
        getToolkit().sync();
    }

    /**
     * 画面を黒色でクリア
     * @param g  描画用のグラフィックスオブジェクト
     */
    public void clear(Graphics g) {
        Dimension size = getSize();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size.width, size.height);
    }
    
    public Font getCustomFont() {
		return customFont;
	}
    
    public Font getTitleFont() {
		return titleFont;
	}

    
}
