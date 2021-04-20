package shiyan;
//ace:00001561 ace:paper_publish_on ace:44563EAC.

import java.io.BufferedReader;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;


public class shurushuchu {
	
	final static String triplePath = "./1";//实体数据使用
	final static String filePath = "./1111";//关系数据使用
	final static String str[] = {"M","Agricultural and Biological Sciences","Arts and Humanities","Biochemistry, Genetics and Molecular Biology","Business, Management and Accounting","Chemical Engineering","Chemistry","Computer Science","Decision Sciences","Earth and Planetary Sciences","Economics, Econometrics and Finance","Energy","Engineering","Environmental Science","Immunology and Microbiology","Materials Science","Mathematics","Medicine","Neuroscience","Nursing","Pharmacology, Toxicology and Pharmaceutics","Physics and Astronomy","Psychology","Social Sciences","Veterinary","Dentistry","Health Professions"}	;	 
	/* 
	 * 关系入库事物处理，每5000条关系为一个单位
	 * para1：已入库关系数
	 * para2：待读文件*/
	public static void tranRelation(int num, BufferedReader br,PrintStream ps, Map<String,String> pAndPv){
        String triple = null;
        int flag = 0;
        long a[][]=new long[27][27];
        int i=0;
		while(true){
				int count = 0;
		        try{
		            while((triple = br.readLine()) != null){
		            	

		            i++;
		            for(int j=0;j<=26;j++) {
			          a[i-1][j]=Integer.parseInt(triple.split("	")[j]);
			            	}
		            
		             
		            	if(count >= 1000){
            				System.out.println("e："+num);
            				count =0 ;
            			}
		            //endOfWhile
		            
		            }
		           
		            
		            for(int x=0;x<=26;x++) {
		            	for(int y=0;y<=26;y++) {
		            		System.out.print(a[x][y]+"	");
		            	}
		            	System.out.println("");
		            }
		            ps.println("Source,Target,Type,Id,Label,timeset,weight");
		            for(int x=0;x<=26;x++) {
		            	for(int y=0;y<=26;y++) {
		            		ps.println((x+10)+"	"+(y+10)+"	"+(a[x][y]*1000/a[17][17]+a[y][x]*1000/a[17][17]));
		            		
		            		//ps.println((x+10)+","+(y+10)+",Directed,"+","+","+","+(a[x][y]*1000/a[17][17]));
			            	
		            	}
		            }
		            
		            br.close();
		            ps.close();
		            
		            
		            
		            
		            break;
		        }catch(Exception e){
		            //e.printStackTrace();
		            //System.out.println(triple);
		        }
				
			 
			
		}//endOfWhile
	}
	
	/* 
	 * 关系入库
	 * neo4j的每一个操作称为：事务,每一项事务都必须用下面的结构进行包装:
	 * try(Transaction tx = graphDB.beginTx()){
	 * ....
	 * tx.success();
	 * }*/
	public static void relation2Db(){
		File input = new File(triplePath);
		File file = new File(filePath);
		BufferedReader br = null;
		PrintStream ps =null;
		try {
			br = new BufferedReader(new FileReader(input));
            ps = new PrintStream(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Map<String,String> pAndPv = new HashMap<String, String>();
		tranRelation(0, br,ps, pAndPv);
	        
	}
	


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
		System.out.println("start");
		relation2Db();
		System.out.println("success");
	}

}
