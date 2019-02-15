package �݌ɊǗ��V�X�e��;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class Zaiko extends Mado{

	static Statement st;										//SQL���s�p�C���X�^���X
	static ResultSet rset;                                      //SQL���ʊi�[�p�C���X�^���X
	static ResultSetMetaData rsmd;
	
	static String selectSql;
	public static int get_shocd = 0;
	public static String get_shoname = "";
	
	static boolean string_check_ok;
	
    public static void main(String[] args) throws SQLException {
    	
    	
        // (1) �ڑ��p��URI��p�ӂ���(�K�v�ɉ����ĔF�؎w��user/password��t����)
        String uri = "jdbc:postgresql://localhost:5432/sample";
        String user = "postgres";
        String password = "1818";
        
        // (2) DriverManager�N���X�̃��\�b�h�Őڑ�����
        Connection conn = DriverManager.getConnection(uri,user,password);
        
        // (3) SQL���M�p�C���X�^���X�̍쐬
       st = conn.createStatement();
       
       Zaiko z = new Zaiko();
        
        //updateSql(a);															//SQL��������

    }
    
    public static void getSql() throws SQLException {									//SQL���ʕ\���p���\�b�h 	
    	
        selectSql = "SELECT * FROM hatzaiko ORDER BY shocd ASC";
        l_error.setText("");
        string_check_ok = false;
        
    	rset = st.executeQuery(selectSql);
    	
        //�܂������ȕ����񂪓����Ă��Ȃ����m�F����I�I
    	if(!get_shoname.equals("")) {
            stringCheck();
            if(!string_check_ok) {
            	l_error.setText("���݂��Ȃ����i�������͂���Ă��܂�");
            }
    	}else {
    		string_check_ok = true;
    	}

        //���݂��Ȃ�������G���[���o�Ă��Ȃ����
    	if(string_check_ok) {
    		System.out.println("check�@�I");
            search();
    	}
        
        //���X�̃G���[���o�Ă��Ȃ����
    	if(l_error.getText().equals("")) {
    		
    		rset = st.executeQuery(selectSql);
    		
            //�J���������擾
            rsmd = rset.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnName(i)+"�@�F�@");
             }
            System.out.println("");
            
            //���R�[�h���擾
            while(rset.next()) {
            	System.out.print(rset.getInt("shocd") + "�@�@�@�@�F�@");
            	System.out.print(rset.getString("shoname").trim() +"�@�@�@�F�@");
            	System.out.print(rset.getInt("zaisu"));       	
            	System.out.println();
            }
    	}else {
    		System.out.println("�G���[�ŏI���܂���");
    	}
    }
    
     //���݂��Ȃ����i�������͂���Ă��Ȃ����Ď�
    public static void stringCheck() throws SQLException {									//SQL���ʕ\���p���\�b�h 	

    	if(!get_shoname.equals("")) {
            while(rset.next()) {
            	if(rset.getString("shoname").trim().equals(get_shoname)) {
            		string_check_ok = true;
            		break;
            	}
            }
    	}
    }        
    
    //���͂��ꂽ���i������v���Ă��邩�`�F�b�N
    public static void search() throws SQLException {
    	
    	//���i�b�c�����͂���Ă���
    	if(get_shocd != 0) {
    		selectSql = "SELECT * FROM hatzaiko WHERE shocd="+get_shocd+"";
            rset = st.executeQuery(selectSql);
            //���i�������͂���Ă���
            if(!get_shoname.trim().equals("")) {
                while(rset.next()) {
                	//���͂��ꂽ���i�����ƁA���͂��ꂽ���i�b�c�ɑΉ����鏤�i������������v���Ă���
                	if(rset.getString("shoname").trim().equals(get_shoname)) {
                		break;
                	}else {
                    	l_error.setText("���i�b�c�Ə��i������v���܂���");
                    	break;
                	}
                }
            }
         //���i�b�c�����͂���A���i�������͂���Ă��Ȃ��B
    	}else if(!get_shoname.trim().equals("")){
            rset = st.executeQuery(selectSql);
    		while(rset.next()) {
        		//���͂��ꂽ���i����DB��̏��i���ƈ�v���Ă���
            	if(rset.getString("shoname").trim().equals(get_shoname.trim())) {
            		System.out.println("���i����v�H");
            		selectSql = "SELECT * FROM hatzaiko WHERE shoname like '%"+get_shoname+"%'";
            		break;
            	}
    		}
    	}
    }
    
    
   /* public static void updateSql(int a) throws SQLException {								//SQL���������p���\�b�h
        String updateAll = "update hatzaiko SET zaisu="+a+"";
        st.executeUpdate(updateAll);
    }*/
    
}