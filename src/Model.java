
public class Model {

    private View view;
    private Controller controller;
    private State state;
    
    public static final int SCALE = 50; //画像のサイズ
    // Sample instance variables:
    private int time;
    private String typedChar = "";
    private boolean enableKeyRollover = false;
    private boolean result;
    
    public Model() {
        view = new View(this);
        controller = new Controller(this);
        state = new TitleState(this);
    }

    public synchronized void processTimeElapsed(int msec) {
        time++;
        state = state.processTimeElapsed(msec);
        view.repaint();
    }

    public synchronized void processKeyTyped(String typed) {
    	typedChar = typed;
    	state = state.processKeyTyped(typed);
        
    }

    public void start() {
        controller.start();
    }

    public View getView() {
        return view;
    }

    public Controller getController() {
        return controller;
    }

    public int getTime() {
        return time;
    }

    public String getTypedChar() {
        return typedChar;
    }

    public boolean getEnableKeyRollover() { 
        return enableKeyRollover;
    }

	public State getState() {
		
		return state;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
