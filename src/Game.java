import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
	private boolean isRunning;
	private Thread thread;
	private static final int WIDTH = 160;
	private static final int HEIGHT = 120;
	private static final int SCALE = 3;
	private JFrame frame;

	public static void main(String[] args) {
		
		Game game = new Game();
		game.start();

	}
	
	public Game() {
		// declarando tamanho do canvas
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();

	}
	
	public void initFrame() {
		frame = new JFrame();
		frame.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
	
	//entender mais sobre thread
    public synchronized void start() {
    	isRunning = true;
		thread = new Thread(this);
		thread.start();
    	
    }
	
	public void update(){
		System.out.println("Atualiza");
		
	}
	
	public void render(){
		System.out.println("Renderiza");

		
	}


	@Override
	public void run() {
		
		// quando a diferença entre currentTime e lastTime for igual  a 1
		long initialTime = System.currentTimeMillis();
		double framesPerSecond = 60.0;
		// Eu quero que o código seja executado sempre que passar esse tempo
		double fractionOfSecondsPerFrame = 1000/framesPerSecond;  //  1/60
		int frames = 0;
		double timer = System.currentTimeMillis();
		double delta = 0;
		
		// GAMELOOP
		while(isRunning) {
			long currentTime = System.currentTimeMillis();
			// irá incrementar até atingir 1 e com isso  a diferença de tempo será igual à fractionOfSecondsPerFrame
			delta += (currentTime - initialTime)/fractionOfSecondsPerFrame;
			// O valor de delta se torna 1 depois de 1/60 seconds

			if(delta>=1) {
				update();
				render();
				 // o tempo inicial se torna o momento no qual ocorreu a atualização
				frames++;
				delta=0;
			}
			
			initialTime = currentTime;

			
			if(System.currentTimeMillis()-timer >=1000) {
				// A quantidade de frames após um segundo será mostrada
				System.out.println("FPS: "+frames);
				frames = 0;
				timer+=1000;
				
			}
			
			

			
		}
		
		
	}
	

	
}
