package view.StudentInfor;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import dao.StudentInfoDao;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class DelStudentFrame extends JInternalFrame {
	private JTextField username;

	public DelStudentFrame() {
		setTitle("删除学生");
		setBounds(100, 100, 637, 372);
		setClosable (true);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("学生学号");
		lblNewLabel.setIcon(new ImageIcon(DelStudentFrame.class.getResource("/image/学号管理.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 13));
		lblNewLabel.setBounds(117, 150, 97, 15);
		getContentPane().add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(279, 148, 214, 21);
		getContentPane().add(username);
		username.setColumns(10);
		
		JButton btnNewButton = new JButton("删除");
		btnNewButton.setIcon(new ImageIcon(DelStudentFrame.class.getResource("/image/删 除.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmButton(e);
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 13));
		btnNewButton.setBounds(102, 292, 97, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.setIcon(new ImageIcon(DelStudentFrame.class.getResource("/image/重置.png")));
		btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 13));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton();
			}
		});
		btnNewButton_1.setBounds(366, 292, 97, 23);
		getContentPane().add(btnNewButton_1);

	}

	protected void confirmButton(ActionEvent e) {
		// TODO Auto-generated method stub
		String username = this.username.getText();
		if("".equals(username)||username==null) {
			JOptionPane.showMessageDialog(this, "请输入学号");
			return;
		}
		StudentInfoDao studeldao = new StudentInfoDao();
		JOptionPane.showMessageDialog(this, studeldao.delStuDao(username));
	}
	public void doDefaultCloseAction() {
		setVisible(false);
		resetButton();
	}

	private void resetButton() {
		// TODO Auto-generated method stub
		this.username.setText("");
	}
}
