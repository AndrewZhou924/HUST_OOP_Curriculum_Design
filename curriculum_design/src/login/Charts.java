package login;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import game.UserDA;

public class Charts {
	JFrame jf = new JFrame("ֲ���ս��ʬ���а�");
	
	JTable table;
//	// �����ά������Ϊ�������
//	Object[][] tableData = { new Object[] { 1, "YQ", 100 }, new Object[] { 2, "ZZK", 99 },
//			new Object[] { 3, "FYJ", 98 }, new Object[] { 4, "YYX", 97 }, new Object[] { 5, "LYH", 96 },
//			new Object[] { 6, "AAA", 90 }, new Object[] { 7, "BBB", 88 }, new Object[] { 8, "CCC", 70 },
//			new Object[] { 9, "DDD", 59 }, new Object[] { 10, "EEE", 10 } };
	// ����һά������Ϊ�б���

	Object[] columnTitle = { "����", "�û���","ʱ��","�÷�" };

	public void init() {
//		jf.setBounds(1000,1000, 0, 0);
//		jf.setSize(5000,5000);
		jf.setPreferredSize(new Dimension(730,510));
		
		if(UserDA.NotInit) {
			UserDA userda = new UserDA();
			System.out.println("UserDA init success!");
			UserDA.NotInit = false;
		}
		
		ArrayList<String> info = new ArrayList<String>();
		ArrayList<Integer> score = new ArrayList<Integer>();
		UserDA.getLeaderBoard(info, score);
		
		// ����info �� score ��������
		for(int i=0; i<score.size()-1; i++) {
			for(int j=0;j<score.size()-1-i;j++){
				if(score.get(j) < score.get(j+1)) {
					
					int temp = score.get(j);
					score.remove(j);
					score.add(j,score.get(j));
					score.remove(j+1);
					score.add(j+1,temp);
					
					String temp_str = info.get(j);
					info.remove(j);
					info.add(j,info.get(j));
					info.remove(j+1);
					info.add(j+1,temp_str);
					
				}
			}
		}
		
		//to do
		
		String[] s0 = info.get(0).split("\\s+");
		String[] s1 = info.get(1).split("\\s+");
		String[] s2 = info.get(2).split("\\s+");
		String[] s3 = info.get(3).split("\\s+");
		String[] s4 = info.get(4).split("\\s+");
		String[] s5 = info.get(5).split("\\s+");
		String[] s6 = info.get(6).split("\\s+");
		String[] s7 = info.get(7).split("\\s+");
		String[] s8 = info.get(8).split("\\s+");
		String[] s9 = info.get(9).split("\\s+");
		
		Object[][] tableData = { new Object[] { 1, s0[0],s0[1]+" "+s0[2], score.get(0) }, new Object[] { 2, s1[0],s1[1]+" "+s1[2], score.get(1) },
				new Object[] { 3, s2[0],s2[1]+" "+s2[2], score.get(2) }, new Object[] { 4, s3[0],s3[1]+" "+s3[2], score.get(3) }, new Object[] { 5, s4[0],s4[1]+" "+s4[2], score.get(4) },
				new Object[] { 6, s5[0],s5[1]+" "+s5[2], score.get(5) }, new Object[] { 7, s6[0],s6[1]+" "+s6[2], score.get(6) }, new Object[] { 8, s7[0],s7[1]+" "+s7[2], score.get(7) },
				new Object[] { 9, s8[0],s8[1]+" "+s8[2], score.get(8) }, new Object[] { 10, s9[0],s9[1]+" "+s9[2], score.get(9) } };
		
		// �Զ�ά�����һά����������һ��JTable����
		table = new JTable(tableData, columnTitle);
		setTableColumnCenter(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.setRowHeight(40);// ���ñ���п�
		table.setFont(new Font("΢���ź�", Font.PLAIN, 30));// ���ñ������
		table.getTableHeader().setFont(new Font("΢���ź�", Font.PLAIN, 30));// ���ñ�ͷ����

		// ��JTable�������JScrollPane�У�������JScrollPane���ڴ�������ʾ����
		jf.add(new JScrollPane(table));
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
		

	
	}

	public static void main(String[] args) {
		new Charts().init();
	}

	public void setTableColumnCenter(JTable table) {// ������ݾ���
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
	}
}