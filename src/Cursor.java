
public class Cursor {
	
	protected int cursorValue; //タイトルと結果画面の選択を指す
	protected int pointer; //cursor の座標を格納する
	
	public void cursorDown() {
		pointer+=50;
		cursorValue++;
	}

	public void cursorUp() {
		pointer-=50;
		cursorValue--;
	}
	

}
