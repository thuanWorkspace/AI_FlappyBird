package flapp2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Principal;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameJFrameTestDrive {
	int second;
	int minute;
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			timerRun();
			printTime();
		}
	};
	JLabel jlb;
	static boolean stop = false;

	public GameJFrameTestDrive(JLabel jlb) {
		// TODO Auto-generated constructor stub
		this.jlb = jlb;
		timer.schedule(task, 0, 1000);
	}

	public String printTime() {
		jlb.setText("actual time-> minute: " + minute + ", second: " + second);
		return "minute: " + minute + ", second: " + second;
	}

	public void timerRun() {
		if (!stop) {
			second++;
			if (second == 60) {
				minute++;
				second = 0;
			}
		}
	}

	public static void main(String[] args) {
		JLabel jlb = new JLabel("???");
		GameJFrameTestDrive gf = new GameJFrameTestDrive(jlb);
		JFrame f = new JFrame("flapp A*");
		JPanel jpnMain = new JPanel();
		jpnMain.setLayout(new BoxLayout(jpnMain, BoxLayout.Y_AXIS));

		f.setSize(GameScreen.width, GameScreen.height + 230);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 50, 1);
		slider.setMinorTickSpacing(2);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		System.out.println(slider.getValue());
		GameScreen screen = new GameScreen(slider, 1000);
		jpnMain.add(screen);
		jpnMain.add(slider);
		jpnMain.add(jlb);

		JPanel jpnAdd = new JPanel();
		jpnAdd.setLayout(new FlowLayout(FlowLayout.CENTER));
		JTextField jtx = new JTextField(10);

		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				jtx.setText(slider.getValue() + "");
			}
		});
		jpnAdd.add(jtx);
		JButton jbt = new JButton("change");
		jpnAdd.add(jbt);
		jbt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					int value = Integer.parseInt(jtx.getText());
					slider.setValue(value);
				} catch (Exception e1) {
					// TODO: handle exception
					System.out.println("type correct number !");
				}
			}
		});
		JButton jbtStop = new JButton("stop");
		jpnAdd.add(jbtStop);
		jbtStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (stop == false) {
					stop = true;
				} else {
					stop = false;
				}
				screen.setStop(stop);
			}
		});
		jpnMain.add(jpnAdd);
		JPanel jpnBorderLayour = new JPanel();
		jpnBorderLayour.setLayout(new BorderLayout());
		jpnBorderLayour.add(jpnMain);
		jpnBorderLayour.add(jpnAdd, BorderLayout.SOUTH);
		f.add(jpnBorderLayour);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}
}
