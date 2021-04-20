package shiyan;
//ace:00001561 ace:paper_publish_on ace:44563EAC.

import java.io.BufferedReader;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Transaction;

import dbhelper.Neo4jDBHelper;

public class r_author_r {
	
	private static Neo4jDBHelper ndp = null;
	private static GraphDatabaseService graphDb = null;
	final static String relationPath = "E:\\Field\\author_is_in_field(25886).txt";//关系数据使用
	
	/* 
	 * 关系入库事物处理，每5000条关系为一个单位
	 * para1：已入库关系数
	 * para2：待读文件*/
	public static void tranRelation(int num, BufferedReader br, Map<String,String> pAndPv){
        String triple = null;
        String hEntity = null, relation = null, rEntity = null;
        
        int flag = 0;
        
		while(true){
			try(Transaction tx = graphDb.beginTx()){
				int count = 0;
		        try{
		            while((triple = br.readLine()) != null){
	    	            if((triple.charAt(0)=='a')) {
		    	    		String strs[]=triple.split(" ");//split函数用来分割字符串
		    	    		String str1[]=strs[0].split(":");
		    	    		String str2[]=strs[1].split(":");
		    	    		String str3[]=strs[3].split(":");
		    	    		
//		    	    		System.out.println(str1[1]);//str1[1]=000025C5就是id
//		    	    		System.out.println(str2[1]);
//		    	    		System.out.println(s);
		            	
			            	hEntity = str1[1];
			            	relation = str2[1];
			            	rEntity = str3[1];
			            	
				            Node hNode = ndp.getNode("Author", "author_id", hEntity);
				            Node rNode = ndp.getNode("Field", "field_id", rEntity);
				            	
				            if(hNode != null && rNode != null){
				            	ndp.addRelation(hNode, rNode, relation, pAndPv);
				            	pAndPv.clear();
				            		
				            	count ++;
				            	num ++;
				            	if(count >= 5000){
				            		flag = 1;
			            			System.out.println("r："+num);
			            			break;
				            	}
				            }
	    	            }
		            }//endOfWhile
		            if(flag == 0){
		            	tx.success();
					    tx.close();
		            	br.close();
		            	System.out.println("r："+num);
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
		File input = new File(relationPath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(input));
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Map<String,String> pAndPv = new HashMap<String, String>();
		tranRelation(0, br, pAndPv);
	        
	}
	
	/* 
	 * 创建索引
	 * 加快创建边的速度*/
	public static void createIndex(){
		try(Transaction tx = graphDb.beginTx()){
			
			graphDb.schema().indexFor(Label.label("Author")).on("author_id").create();
			graphDb.schema().indexFor(Label.label("Field")).on("field_id").create();
			
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
		
		ndp = new Neo4jDBHelper("E:\\Neo4j\\Ace-Geo.db");
		graphDb = ndp.createNewGraph();
		ndp.registerShutdownHook();
		
//		createIndex();
		relation2Db();
		
		ndp.close();
		
		System.out.println("success");
	}

}
