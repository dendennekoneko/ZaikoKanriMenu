package �݌ɊǗ��V�X�e��;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//�݌Ɉꗗ��ʁB�@Windows�N���X�̎q��
public class Zaiko_List extends Windows {
	
	private static final long serialVersionUID = 1L;
	
	private String[] column = {"���i�b�c", "���i��", "�݌ɐ�"};
	String[][] table_Column = new String[SQL.result_Column.size()][3];

	//�R���X�g���N�^(��ʍ\�����`)
	Zaiko_List(){
		setTitle("�₢���킹�݌� �ꗗ�\��");
		setBounds(400,300,500,370);										//setBounds�ŕ\���ʒu�Ƒ傫���w��
		
		DefaultTableModel tableModel = new DefaultTableModel(column,0);
		JTable table = new JTable(tableModel);
		for(int i = 0 ; i < SQL.result_Column.size(); i++) {
			for(int j = 0;j < 3;j++) {
				table_Column[i][j] = SQL.result_Column.get(i).get(j); 
			}
		}
		
		for(int i = 0 ; i < SQL.result_Column.size() ; i++){
		      tableModel.addRow(table_Column[i]);
		}
		
	    JScrollPane sp = new JScrollPane(table);
	    sp.setPreferredSize(new Dimension(300,150));
		
	    JPanel p = new JPanel();
	    p.add(sp);
	    
	    getContentPane().add(p, BorderLayout.CENTER);
	}
}
