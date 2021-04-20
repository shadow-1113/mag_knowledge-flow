package n11_23;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
public class SortURL {
	
	public static String FilePath = "C:\\Users\\13828\\Desktop\\317-\\2015";
	final static String filePath = "./1111";//关系数据使用
	public static int rows = 5500000;
	public static List<String> fileList = null;
	public static ConcurrentHashMap<String, Integer> urlMap = new ConcurrentHashMap<>();
	
	public static void main(String[] args) {
		
		//拆分文件
		cutFile(FilePath,rows);
		//多线程处理文件，排序文件内容
		threadFile();
	}
	
	
	/**
	 * 大文件切割
	 * @param sourceFile
	 * @param curRows
	 */
	public static void cutFile(String sourceFile,int curRows) {
		System.out.println("开始拆每  "+curRows+"  行，拆分文件");
		FileInputStream inputstream = null;
		Scanner sc = null;
		StringBuilder sbu = null;
		BufferedWriter bw = null;
		//
		try {
			inputstream = new FileInputStream(sourceFile);
			sbu = new StringBuilder();
			fileList = new ArrayList<>();
			
			long begin = System.currentTimeMillis();
			// Scanner 方法消耗内存低
			sc = new Scanner(inputstream);
			int i = 0;
			while(sc.hasNextLine()) {
				sbu.append(sc.nextLine()).append("\r\n");
				i++;
				if ((i % curRows) == 0) {
					bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(sourceFile+i+".txt")),"UTF-8"));
					bw.write(sbu.toString());
					fileList.add(sourceFile+i+".txt");
					bw.close();
					sbu.setLength(0);
				}
			}
			// 余下行数生成文件
			if(((i-1) % curRows) != 0 ) {
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(sourceFile+i+".txt")),"UTF-8"));
				bw.write(sbu.toString());
				fileList.add(sourceFile+i+".txt");
				bw.close();
				sbu.setLength(0);
			}
			long end = System.currentTimeMillis();
			System.out.println("切割文件耗时: "+(end - begin)+" 毫秒");
			inputstream.close();
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
	}
	/**
	 * 线程 解析文件汇总url
	 * @author ThinkPad
	 *
	 */
	private class readFile implements Runnable {
		
		File tFile = null;
		
		public readFile(File f) {
			// TODO Auto-generated constructor stub
			this.tFile = f;
		}
 
		@Override
		public void run() {
			resolverFile();
		}
		
		/**
		 * 解析文件
		 */
		private void resolverFile() {
			FileInputStream fis = null;
			Scanner sc = null;
			
			Map<String, Integer> map = new HashMap<>();
			try {
				fis = new FileInputStream(tFile);
				sc = new Scanner(fis);
				while(sc.hasNextLine()) {
					String len = sc.nextLine().trim();
					String len1=len.split(",")[0]+"	"+len.split(",")[1];
//					int len2=1;
					int len2=Integer.parseInt(len.split(",")[3]);
					if (map.containsKey(len1)) {
						int mValue = map.get(len1);
						map.put(len1, mValue+len2);
					}else {
						map.put(len1, len2);
					}
				}
				sc.close();
				//合并总得urlMap   ConcurrentHashMap 线程安全
				if(urlMap.size() == 0) {
					urlMap.putAll(map);
				} else {
					for (String key : map.keySet()) {
						if (urlMap.containsKey(key)) {
							int mValue = urlMap.get(key) + map.get(key);
							urlMap.put(key, mValue);
						}else {
							urlMap.put(key, 1);
						}
					}
				}
//				//清空临时map内存
//				map.clear();
				System.out.println(tFile.getName()+"   mapsize:"+map.size() + "   urlmapSize:"+urlMap.size());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	/**
	 *  创建线程池 ,启动线程处理文件
	 */
	public static void threadFile() {
		System.out.println("多线程分析文件....");
		long begin = System.currentTimeMillis();
		
		//线程数 最好依据cpu 分配
		ExecutorService es = Executors.newCachedThreadPool();
		SortURL su = new SortURL();
		for (int i=0 ; i< fileList.size();i++) {
			File file = new File(fileList.get(i));
			if (file.isFile()) {
				es.execute(su.new readFile(file));
			} else {
				System.out.println("未找到文件");
			}
		}
		es.shutdown();
		while(true) {
			if (es.isTerminated()) {
				long end = System.currentTimeMillis();
				System.out.println("解析 "+fileList.size()+" 个文件  耗时:"+(end-begin)+" 毫秒");
				File file = new File(filePath);
				PrintStream ps =null;
				try {
		            ps = new PrintStream(new FileOutputStream(file));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				sortMap(ps);
				break;
			}
		}
	}
	/**
	 *  排序文件内容
	 */
	public static void sortMap(PrintStream ps) {
		// 频率排序
		List<Map.Entry<String, Integer>> sortList = new ArrayList<>(urlMap.entrySet());
		Collections.sort(sortList, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		//取前100 最多频率
//		List<String> url = new ArrayList<>();
		for(int i=0,j=0;i<sortList.size();i++,j++) {
			if(j>=5000) {System.out.println(i);j=0;}
			ps.println(sortList.get(i).getKey() +"	"+sortList.get(i).getValue());
		}
	}

	
	
	
}