package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import game.UserDA;
import game.starGame;

public class LoginSystem {


	public LoginSystem() {
		JFrame frame = new JFrame();

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel label0 = new JLabel("         植物大战僵尸");
		JLabel label1 = new JLabel("用户名:"); 
		JLabel label2 = new JLabel("密码:");
		JLabel reminder = new JLabel("连接服务器中，请稍后");
		
		
		
		JTextField username = new JTextField(10);// 用户名
		JPasswordField password = new JPasswordField(10);// 密码
		JButton btn_login = new JButton("登录");
		JButton btn_reg = new JButton("注册");

		Font font1 = new Font("SansSerif", Font.BOLD, 50);
		Font font2 = new Font("SansSerif", Font.BOLD, 25);
		Font font3 = new Font("SansSerif", Font.BOLD, 35);
		// ImageIcon icon1 = new ImageIcon("1.png");
		// ImageIcon icon2 = new ImageIcon("2.png");
		// ImageIcon icon3 = new ImageIcon("3.png");
		// ImageIcon icon4 = new ImageIcon("4.png");
		// ImageIcon icon5 = new ImageIcon("5.png");

		frame.add(panel1);
		panel1.setSize(600, 150);
		panel1.setBackground(Color.CYAN);
		panel1.setLayout(new BorderLayout());
		panel1.add(label0, BorderLayout.CENTER);
		label0.setFont(font1);
		label0.setForeground(Color.BLUE);

		frame.add(panel2);
		panel2.setSize(460, 220);
		panel2.setLocation(60, 200);
		panel2.setLayout(new GridLayout(4, 2, 40, 20));
		panel2.add(label1);
		panel2.add(username);
		panel2.add(label2);
		
		panel2.add(password);		
		panel2.add(btn_login);
		panel2.add(btn_reg);
		panel2.add(reminder);
		
		label1.setFont(font2);
		label2.setFont(font2);
		btn_login.setFont(font3);
		btn_reg.setFont(font3);
//		label1.setIcon(icon2);
//		label2.setIcon(icon3);
//		btn_login.setIcon(icon4);
//		btn_reg.setIcon(icon5);
		label1.setForeground(Color.BLUE);
		label2.setForeground(Color.BLUE);
		btn_login.setForeground(Color.BLUE);
		btn_reg.setForeground(Color.BLUE);

		frame.setTitle("欢迎来到植物大战僵尸");
//		frame.setIconImage(icon1.getImage());
		frame.setSize(600, 500);
		frame.setLocation(400, 300);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// init UserDA
		if(UserDA.NotInit) {
			UserDA userda = new UserDA();
			System.out.println("UserDA init success!");
			UserDA.NotInit = false;
		}
		reminder.setText("连接服务器成功！");
		
		btn_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp_username = username.getText();
				String temp_password = password.getText();
				
				System.out.println("登录按钮被点击");
				System.out.println("用户名：" + temp_username + "   密码：" + temp_password);
				
				if(temp_username.isEmpty() || temp_password.isEmpty()) {
					reminder.setText("您输入的信息有误！");
					return;
				}
				
				// 执行登录
				if(UserDA.login(temp_username, temp_password)) {
					reminder.setText("登录成功！");

					//进入游戏开始界面
					JFrame f = new starGame();
					frame.dispose();
					
				} else {
					reminder.setText("用户名或密码有误，登录失败");
				}
			}
		});

		btn_reg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("注册按钮被点击");
				
				String temp_username = username.getText();
				String temp_password = password.getText();
				
				System.out.println("注册按钮被点击");
				System.out.println("用户名：" + temp_username + "   密码：" + temp_password);
				
				if(temp_username.isEmpty() || temp_password.isEmpty()) {
					reminder.setText("您输入的信息有误！");
					return;
				}
				
				// 检查用户名是否被占用
				if(UserDA.checkUserName(temp_username)) {
					reminder.setText("用户名被占用！");
					return;
				}
				
				// 执行注册
				UserDA.addUser(temp_username, temp_password);
				reminder.setText("注册成功，正在自动登录中");
				
				if(UserDA.login(temp_username, temp_password)) {
					//进入游戏画面
					JFrame f = new starGame();
					frame.dispose();
				}
				
				
//				frame.dispose();// 销毁当前界面
//				RegSystem.main(args);
			}
		});
	}
}