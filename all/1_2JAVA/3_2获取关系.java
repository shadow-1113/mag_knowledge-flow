package Geo;
//ace:00001561 ace:paper_publish_on ace:44563EAC.

import java.io.BufferedReader;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Transaction;

import dbhelper.Neo4jDBHelper;

public class _3g{
	
	private static Neo4jDBHelper ndp = null;
	private static GraphDatabaseService graphDb = null;
	final static String relationPath = "E:\\学习相关文件\\mag\\zhongyao\\P\\2_J_year_Count.txt";//关系数据使用

	final static String filePath = "C:\\Users\\13828\\Desktop\\308_j\\F_14779";//关系数据使用
	/* 
	 * 关系入库事物处理，每5000条关系为一个单位
	 * para1：已入库关系数
	 * para2：待读文件*/
	public static void tranRelation(int num, BufferedReader br,PrintStream ps, Map<String,String> pAndPv){
        String triple = null;
        int num1=0,num2=0;
        String hEntity = null, relation = null, rEntity = null;
        int flag = 0;
        
		while(true){
			try(Transaction tx = graphDb.beginTx()){
				int count = 0;
		        try{
		            while((triple = br.readLine()) != null){
		            	count ++;
		            	num1++;

//		    	    		System.out.println(str1[1]);//str1[1]=000025C5就是id
//		    	    		System.out.println(str2[1]);
//		    	    		System.out.println(s);
				            Node hNode = ndp.getNode("J", "J_ISSN",triple.split(",")[0]);
//				            Node rNode = ndp.getNode("J", "J_ISSN",triple.split(",")[1]);
				            	
				            if((hNode != null)){
//				            	System.out.println(triple);
				            	num2 ++;
				            	ps.println(hNode.getProperty("J_f")+","+triple.split(",")[1]+","+triple.split(",")[2]);
//					            ps.println(triple);
				            	if(count >= 5000){
				            		flag = 1;
			            			System.out.println("r1："+num1);
						            System.out.println("r2："+num2);
			            			break;
				            	}
				            }
				            //else {ps.println(triple+",0");}
		            }//endOfWhile
		            if(flag == 0){
		            	tx.success();
					    tx.close();
		            	br.close();
		            	System.out.println("r1："+num1);
			            System.out.println("r2："+num2);
		            	break;
		            }else{
		            	flag = 0;
		            }
		               
		        }catch(Exception e){
		            e.printStackTrace();
		            System.out.println(triple);
		        }
				
			    tx.success();
			    tx.close();
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
		File input1 = new File(relationPath);
		File file = new File(filePath);

		BufferedReader br = null;
		PrintStream ps =null;

		try {
			br = new BufferedReader(new FileReader(input1));
            ps = new PrintStream(new FileOutputStream(file));

		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Map<String,String> pAndPv = new HashMap<String, String>();
        tranRelation(0,br,ps,pAndPv);
	        
	}
	
	/* 
	 * 创建索引
	 * 加快创建边的速度*/
	public static void createIndex(){
		try(Transaction tx = graphDb.beginTx()){
			
			graphDb.schema().indexFor(Label.label("J")).on("J_ISSN").create();
			graphDb.schema().indexFor(Label.label("J")).on("J_f").create();
			
		    tx.success();
		    tx.close();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
		System.out.println("start");
		
//		ndp = new Neo4jDBHelper("C:\\Users\\13828\\Desktop\\301\\J2017.db");

		ndp = new Neo4jDBHelper("C:\\Users\\13828\\Desktop\\308_j\\J_14779.db");
		graphDb = ndp.createNewGraph();
		ndp.registerShutdownHook();
		
//		createIndex();
		relation2Db();
		
		ndp.close();
		
		System.out.println("success");
	}

}
