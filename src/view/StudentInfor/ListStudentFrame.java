package view.StudentInfor;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.StudentInfoDao;
import model.Room;
import model.StudentInfo;
import model.StudentRoom;
import view.Admin.IndexFrameAdmin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import javax.swing.ImageIcon;

public class ListStudentFrame extends JInternalFrame {
	private JTable stuListTable;
	private JTextField usename;
	private JTextField name;
	private JTextField grade;
	private JTextField academy;
	private JTextField major;
	private JTextField sex;
	private JButton editButton;
	private JButton deleteButton;
	private DefaultTableModel dtm = null;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ListStudentFrame() {
		setBounds(100, 100, 848, 594);
		getContentPane().setLayout(null);
		setClosable (true);
		setIconifiable(true);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 816, 430);
		getContentPane().add(scrollPane);
		stuListTable = new JTable();
		stuListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectRow();
			}
		});
		stuListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u53F7", "\u59D3\u540D", "\u5E74\u7EA7", "\u5B66\u9662", "\u4E13\u4E1A", "\u6027\u522B", "\u5E74\u9F84", "\u624B\u673A\u53F7\u7801"
			}
		) {
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		}
		);
		stuListTable.getTableHeader().setReorderingAllowed(false);
		stuListTable.setRowHeight(25);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		stuListTable.setDefaultRenderer(Object.class,r);
		stuListTable.getColumnModel().getColumn(6).setPreferredWidth(53);
		stuListTable.getColumnModel().getColumn(7).setPreferredWidth(116);
		scrollPane.setViewportView(stuListTable);
		
		JLabel lblNewLabel = new JLabel("??????");
		lblNewLabel.setFont(new Font("????????????", Font.BOLD, 13));
		lblNewLabel.setBounds(21, 471, 58, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("??????");
		lblNewLabel_1.setFont(new Font("????????????", Font.BOLD, 13));
		lblNewLabel_1.setBounds(21, 515, 58, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("??????");
		lblNewLabel_2.setFont(new Font("????????????", Font.BOLD, 13));
		lblNewLabel_2.setBounds(209, 472, 58, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("??????");
		lblNewLabel_3.setFont(new Font("????????????", Font.BOLD, 13));
		lblNewLabel_3.setBounds(209, 516, 58, 15);
		getContentPane().add(lblNewLabel_3);
		
		usename = new JTextField();
		usename.setBounds(65, 469, 123, 21);
		getContentPane().add(usename);
		usename.setColumns(10);
		
		name = new JTextField();
		name.setBounds(65, 513, 123, 21);
		getContentPane().add(name);
		name.setColumns(10);
		
		grade = new JTextField();
		grade.setColumns(10);
		grade.setBounds(248, 469, 123, 21);
		getContentPane().add(grade);
		
		academy = new JTextField();
		academy.setColumns(10);
		academy.setBounds(248, 513, 123, 21);
		getContentPane().add(academy);
		
		JLabel lblNewLabel_2_1 = new JLabel("??????");
		lblNewLabel_2_1.setFont(new Font("????????????", Font.BOLD, 13));
		lblNewLabel_2_1.setBounds(393, 472, 58, 15);
		getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("??????");
		lblNewLabel_2_2.setFont(new Font("????????????", Font.BOLD, 13));
		lblNewLabel_2_2.setBounds(393, 516, 58, 15);
		getContentPane().add(lblNewLabel_2_2);
		
		major = new JTextField();
		major.setColumns(10);
		major.setBounds(435, 469, 123, 21);
		getContentPane().add(major);
		
		sex = new JTextField();
		sex.setColumns(10);
		sex.setBounds(435, 513, 123, 21);
		getContentPane().add(sex);
		
		JButton btnNewButton = new JButton("??????");
		btnNewButton.setIcon(new ImageIcon(ListStudentFrame.class.getResource("/image/??? ???.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				selectSomeStu(ae);
			}
		});
		btnNewButton.setFont(new Font("????????????", Font.BOLD, 13));
		btnNewButton.setBounds(586, 467, 97, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("??????");
		btnNewButton_1.setIcon(new ImageIcon(ListStudentFrame.class.getResource("/image/??????.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton();
			}
		});
		btnNewButton_1.setFont(new Font("????????????", Font.BOLD, 13));
		btnNewButton_1.setBounds(586, 511, 97, 23);
		getContentPane().add(btnNewButton_1);
		
		editButton = new JButton("??????");
		editButton.setIcon(new ImageIcon(ListStudentFrame.class.getResource("/image/??????.png")));
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editStu();
			}
		});
		editButton.setFont(new Font("????????????", Font.BOLD, 13));
		editButton.setBounds(706, 468, 97, 23);
		getContentPane().add(editButton);
		
		deleteButton = new JButton("??????");
		deleteButton.setIcon(new ImageIcon(ListStudentFrame.class.getResource("/image/??? ???.png")));
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteButton();
			}
		});
		deleteButton.setFont(new Font("????????????", Font.BOLD, 13));
		deleteButton.setBounds(706, 512, 97, 23);
		getContentPane().add(deleteButton);

		this.dtm = (DefaultTableModel) stuListTable.getModel();
		queryAllStu();
		
	}
	protected void editStu() {
		// TODO Auto-generated method stub
		StudentInfo tempStu = new StudentInfo();
		tempStu.setUsername(dtm.getValueAt(stuListTable.getSelectedRow(),0).toString());
		tempStu.setName(dtm.getValueAt(stuListTable.getSelectedRow(),1).toString());
		tempStu.setGrade(dtm.getValueAt(stuListTable.getSelectedRow(),2).toString());
		tempStu.setAcademy(dtm.getValueAt(stuListTable.getSelectedRow(),3).toString());
		tempStu.setMajor(dtm.getValueAt(stuListTable.getSelectedRow(),4).toString());
		tempStu.setSex(dtm.getValueAt(stuListTable.getSelectedRow(),5).toString());
		tempStu.setAge(Integer.parseInt(dtm.getValueAt(stuListTable.getSelectedRow(),6).toString()));
		tempStu.setTelephone(dtm.getValueAt(stuListTable.getSelectedRow(),7).toString());
		
		StudentInfoDao studeldao = new StudentInfoDao();
		StudentRoom SR = new StudentRoom();
		SR = studeldao.queryStuRoom(tempStu.getUsername());
		
		
		if(IndexFrameAdmin.addStudentFrame == null) {
			IndexFrameAdmin.addStudentFrame = new AddStudentFrame();
			IndexFrameAdmin.desktopPane.add(IndexFrameAdmin.addStudentFrame);
		}
		IndexFrameAdmin.addStudentFrame.editStudentInfo(tempStu,SR);
		IndexFrameAdmin.addStudentFrame.setVisible(true);
		try {
			IndexFrameAdmin.addStudentFrame.setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void deleteButton() {
		// TODO Auto-generated method stub
		if(JOptionPane.showConfirmDialog(this, "????????????????????????","????????????......",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION) {
			String username = dtm.getValueAt(this.stuListTable.getSelectedRow(),0).toString();
			StudentInfoDao studeldao = new StudentInfoDao();
			JOptionPane.showMessageDialog(this, studeldao.delStuDao(username));
			queryAllStu();
		}

	}
	protected void selectRow() {
		// TODO Auto-generated method stub
		this.usename.setText(dtm.getValueAt(this.stuListTable.getSelectedRow(),0).toString());
		this.name.setText(dtm.getValueAt(this.stuListTable.getSelectedRow(),1).toString());
		this.grade.setText(dtm.getValueAt(this.stuListTable.getSelectedRow(),2).toString());
		this.academy.setText(dtm.getValueAt(this.stuListTable.getSelectedRow(),3).toString());
		this.major.setText(dtm.getValueAt(this.stuListTable.getSelectedRow(),4).toString());
		this.sex.setText(dtm.getValueAt(this.stuListTable.getSelectedRow(),5).toString());
		this.editButton.setEnabled(true);
		this.deleteButton.setEnabled(true);
		
	}
	protected void selectSomeStu(ActionEvent ae) {
		// TODO Auto-generated method stub
		String username = this.usename.getText();
		String name = this.name.getText();
		String grade = this.grade.getText();
		String academy = this.academy.getText();
		String sex = this.sex.getText();
		String major = this.major.getText();
		
		dtm.setRowCount(0);
		if(("".equals(username)||username==null)&&("".equals(name)||name==null)&&("".equals(grade)||grade==null)&&("".equals(academy)||academy==null)&&("".equals(sex)||sex==null)&&("".equals(major)||major==null)) {
			queryAllStu();
			return;
		}
		StudentInfo tempStu = new StudentInfo();
		tempStu.setUsername(username);
		tempStu.setName(name);
		tempStu.setGrade(grade);
		tempStu.setAcademy(academy);
		tempStu.setSex(sex);
		tempStu.setMajor(major);
		
		StudentInfoDao studentInfoDao =new StudentInfoDao();
		List<StudentInfo> allStudentList = studentInfoDao.querySomeStu(tempStu);
		for(StudentInfo stu : allStudentList) {
			Vector v = new Vector();
			v.add(stu.getUsername());
			v.add(stu.getName());
			v.add(stu.getGrade());
			v.add(stu.getAcademy());
			v.add(stu.getMajor());
			v.add(stu.getSex());
			v.add(stu.getAge());
			v.add(stu.getTelephone());
			dtm.addRow(v);
		}
		
	}

	protected void resetButton() {
		// TODO Auto-generated method stub
		this.usename.setText("");
		this.name.setText("");
		this.grade.setText("");
		this.academy.setText("");
		this.major.setText("");
		this.sex.setText("");
	}

	public void queryAllStu() {
		dtm.setRowCount(0);
		StudentInfoDao studentInfoDao =new StudentInfoDao();
		List<StudentInfo> allStudentList = studentInfoDao.queryAllStu();
		for(StudentInfo stu : allStudentList) {
			Vector v = new Vector();
			v.add(stu.getUsername());
			v.add(stu.getName());
			v.add(stu.getGrade());
			v.add(stu.getAcademy());
			v.add(stu.getMajor());
			v.add(stu.getSex());
			v.add(stu.getAge());
			v.add(stu.getTelephone());
			dtm.addRow(v);
		}
		this.editButton.setEnabled(false);
		this.deleteButton.setEnabled(false);
	}
	public void doDefaultCloseAction() {
		setVisible(false);
		resetButton();
		IndexFrameAdmin.listStudentFrame = null;
	}
}
