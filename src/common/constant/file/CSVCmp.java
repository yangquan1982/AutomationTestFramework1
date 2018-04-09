package common.constant.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import common.util.ZIP;

public class CSVCmp {
	   private int lineNo = 0 ; //定义记录结果读取到第几行
	    private String currentline = null ;
	    
	    public boolean csv_Compare(String baseFile, String rsFile) {
	    	
	    	boolean flag = false ;
	    	
	        //此处定义基线文件的存储 HashMap,key是每行的值，value是该行出现的次数
	        Map<String, Integer> baseFileSameItemMap = new HashMap<String, Integer>();
	        try {
	            //读取基线文件
	            File bsfile = new File(baseFile);
	            BufferedReader br = new BufferedReader(new FileReader(bsfile));
	            String line = null;
	            while ((line = br.readLine()) != null) {
	                line = line.trim();
	                if (line.isEmpty())  //空行不会加到map中
	                {
	                	continue;
	                }
	                //key出现一次，value值加1 
	                if (baseFileSameItemMap.containsKey(line)) { //如果map中已经包含该key，给value加1
	                    Integer sameValue = baseFileSameItemMap.get(line);
	                    baseFileSameItemMap.put(line, sameValue + 1);
	                } else {
	                    baseFileSameItemMap.put(line, 1); //第一次出现，value值为1
	                }     
	            }
	            br.close();     //关闭流

	            // read the cmp file . 读取结果文件，并处理其中的每一行
	            File resultFile = new File(rsFile);
	            BufferedReader bufferedReader = new BufferedReader(new FileReader(resultFile));
	            
	            while ((line = bufferedReader.readLine()) != null) {
	                line = line.trim();
	                if (line.isEmpty()) continue;
	                
	                if (baseFileSameItemMap.containsKey(line)) {
	                    Integer value = baseFileSameItemMap.get(line); //在基线map中查找该行
	                    if (value > 1) {   //如果value大于1，说明还存在多次
	                        baseFileSameItemMap.put(line, value - 1);  //出现一次，减1
	                    } else if (value == 1) {  //如果当前value为1，
	                        baseFileSameItemMap.remove(line);  //直接从map中remove掉该项
	                    }
	                } else {//如果找不到该行，基线和结果不一致
//	                    System.out.println("Can not find this line \"" + line + "\" in base file");
	                    bufferedReader.close();
	                    currentline = line ;
	                    flag = false;
	                    return flag;
	                }
	                lineNo++;
	                
	            }
	            bufferedReader.close(); //关闭流
	            /*The Base file large than result file.*/
	            if (baseFileSameItemMap.size() > 0) {  //如果Map中还存在没有被remove掉的项，说明基线比结果多
	                System.out.println(ZIP.NowTime()+"The Base File large than result file:" + baseFileSameItemMap);
	                flag = false;
	                
	            }else {
	            	 /*The Resutl file is the same to Base file.*/  //
	                System.out.println(ZIP.NowTime()+"The Resutl file is the same to Base file.");
	                flag = true;
	                
	            }     
	        } catch ( FileNotFoundException e ){
	        	System.out.println(ZIP.NowTime()+"Baseline or results file does not exist, make sure!");
	            e.printStackTrace();
	        } catch ( IOException e ){
	        	System.out.println(ZIP.NowTime()+"Baseline or results file read and write errors!");
	            e.printStackTrace();
	        }
	        return flag;
	    }
	    public boolean csv_Compare(String baseFile, String rsFile,String[] excludeStartWith) {
	    	
	    	boolean flag = false ;
	    	
	        //此处定义基线文件的存储 HashMap,key是每行的值，value是该行出现的次数
	        Map<String, Integer> baseFileSameItemMap = new HashMap<String, Integer>();
	        try {
	            //读取基线文件
	            File bsfile = new File(baseFile);
	            BufferedReader br = new BufferedReader(new FileReader(bsfile));
	            String line = null;
	            while ((line = br.readLine()) != null) {
	                line = line.trim();
	                if (line.isEmpty())  //空行不会加到map中
	                {
	                	continue;
	                }
	                for(int i =0;i<excludeStartWith.length;i++){
	               	 if (line.startsWith(excludeStartWith[i])) {
	               		 continue; 
	               	 }else{
	                     //key出现一次，value值加1 
	                     if (baseFileSameItemMap.containsKey(line)) { //如果map中已经包含该key，给value加1
	                         Integer sameValue = baseFileSameItemMap.get(line);
	                         baseFileSameItemMap.put(line, sameValue + 1);
	                     } else {
	                         baseFileSameItemMap.put(line, 1); //第一次出现，value值为1
	                     } 
	               	 }
	               }
	    
	            }
	            br.close();     //关闭流

	            // read the cmp file . 读取结果文件，并处理其中的每一行
	            File resultFile = new File(rsFile);
	            BufferedReader bufferedReader = new BufferedReader(new FileReader(resultFile));
	            
	            while ((line = bufferedReader.readLine()) != null) {
	                line = line.trim();
	                if (line.isEmpty()) continue;
	                for(int i =0;i<excludeStartWith.length;i++){
	                	 if (line.startsWith(excludeStartWith[i])) {                		
	                		 continue; 
	                	 }else{
	                         if (baseFileSameItemMap.containsKey(line)) {
	                             Integer value = baseFileSameItemMap.get(line); //在基线map中查找该行
	                             if (value > 1) {   //如果value大于1，说明还存在多次
	                                 baseFileSameItemMap.put(line, value - 1);  //出现一次，减1
	                             } else if (value == 1) {  //如果当前value为1，
	                                 baseFileSameItemMap.remove(line);  //直接从map中remove掉该项
	                             }
	                         } else {//如果找不到该行，基线和结果不一致
//	                             System.out.println("Can not find this line \"" + line + "\" in base file");
	                             bufferedReader.close();
	                             currentline = line ;
	                             flag = false;
	                             return flag;
	                         } 
	                	 }
	                }

	                lineNo++;
	                
	            }
	            bufferedReader.close(); //关闭流
	            /*The Base file large than result file.*/
	            if (baseFileSameItemMap.size() > 0) {  //如果Map中还存在没有被remove掉的项，说明基线比结果多
	                System.out.println(ZIP.NowTime()+"The Base File large than result file:" + baseFileSameItemMap);
	                flag = false;
	                
	            }else {
	            	 /*The Resutl file is the same to Base file.*/  //
	                System.out.println(ZIP.NowTime()+"The Resutl file is the same to Base file.");
	                flag = true;
	                
	            }     
	        } catch ( FileNotFoundException e ){
	        	System.out.println(ZIP.NowTime()+"Baseline or results file does not exist, make sure!");
	            e.printStackTrace();
	        } catch ( IOException e ){
	        	System.out.println(ZIP.NowTime()+"Baseline or results file read and write errors!");
	            e.printStackTrace();
	        }
	        return flag;
	    }
	    /*
	     * 去掉第一个逗号前的字符串 --- 适用于BusyKPI，前面有一个index
	     * */ 
	    public boolean csv_Compare_SubString(String baseFile, String rsFile) {
	    	
	    	boolean flag = false ;
	    	
	        //此处定义基线文件的存储 HashMap,key是每行的值，value是该行出现的次数
	        Map<String, Integer> baseFileSameItemMap = new HashMap<String, Integer>();
	        try {
	            //读取基线文件
	            File bsfile = new File(baseFile);
	            BufferedReader br = new BufferedReader(new FileReader(bsfile));
	            String line = null;
	            int bsPrint =0;
	            while ((line = br.readLine()) != null) {
	            	bsPrint++;
	                line = line.trim();
	                if (line.isEmpty())  //空行不会加到map中
	                {
	                	continue;
	                }
	                //去掉第一个逗号前的字符串
	                int index = line.indexOf(",");
	                String subline = line.substring(index, line.length());
	                if(bsPrint < 5){
	                    System.out.println("subline_bsFile:"+subline);
	                }
	                //key出现一次，value值加1 
	                if (baseFileSameItemMap.containsKey(subline)) { //如果map中已经包含该key，给value加1
	                    Integer sameValue = baseFileSameItemMap.get(subline);
	                    baseFileSameItemMap.put(subline, sameValue + 1);
	                } else {
	                    baseFileSameItemMap.put(subline, 1); //第一次出现，value值为1
	                }     
	            }
	            br.close();     //关闭流

	            // read the cmp file . 读取结果文件，并处理其中的每一行
	            File resultFile = new File(rsFile);
	            BufferedReader bufferedReader = new BufferedReader(new FileReader(resultFile));
	            int rsPrint =0;
	            while ((line = bufferedReader.readLine()) != null) {
	            	rsPrint++;
	                line = line.trim();
	                if (line.isEmpty()) continue;
	                //去掉第一个逗号前的字符串
	                int index = line.indexOf(",");
	                String subline = line.substring(index, line.length());
	                if(rsPrint < 5){
	                    System.out.println("subline_rsFile:"+subline);
	                }
	                //判断是否存在map中
	                if (baseFileSameItemMap.containsKey(subline)) {
	                    Integer value = baseFileSameItemMap.get(subline); //在基线map中查找该行
	                    if (value > 1) {   //如果value大于1，说明还存在多次
	                        baseFileSameItemMap.put(subline, value - 1);  //出现一次，减1
	                    } else if (value == 1) {  //如果当前value为1，
	                        baseFileSameItemMap.remove(subline);  //直接从map中remove掉该项
	                    }
	                } else {//如果找不到该行，基线和结果不一致
//	                    System.out.println("Can not find this line \"" + line + "\" in base file");
	                    bufferedReader.close();
	                    currentline = subline ;
	                    flag = false;
	                    return flag;
	                }
	                lineNo++;
	                
	            }
	            bufferedReader.close(); //关闭流
	            /*The Base file large than result file.*/
	            if (baseFileSameItemMap.size() > 0) {  //如果Map中还存在没有被remove掉的项，说明基线比结果多
	                System.out.println("The Base File large than result file:" + baseFileSameItemMap);
	                flag = false;
	                
	            }else {
	            	 /*The Result file is the same to Base file.*/  //
	                System.out.println("The Resutl file is the same to Base file.");
	                flag = true;
	                
	            }     
	        } catch ( FileNotFoundException e )

	        {
	            e.printStackTrace();
	        } catch ( IOException e )

	        {
	            e.printStackTrace();
	        }
	        return flag;
	    }
	    /*
	     * 去掉第N个逗号和N-1逗号之间的字符串 --- 
	     * */ 
	    public boolean csv_Compare_SubString(String baseFile, String rsFile,int N) {
	    	
	    	boolean flag = false ;
	    	
	        //此处定义基线文件的存储 HashMap,key是每行的值，value是该行出现的次数
	        Map<String, Integer> baseFileSameItemMap = new HashMap<String, Integer>();
	        try {
	            //读取基线文件
	            File bsfile = new File(baseFile);
	            BufferedReader br = new BufferedReader(new FileReader(bsfile));
	            String line = null;
	            int bsPrint =0;
	            while ((line = br.readLine()) != null) {
	            	bsPrint++;
	                line = line.trim();
	                if (line.isEmpty())  //空行不会加到map中
	                {
	                	continue;
	                }

	                //去掉第N个逗号和N-1逗号之间的字符串
	                int index1 = 0;
	                int index2 = 0;
	                int ProcessI =0;
	                String ProcessS = line;
	                for(int i = 1; i <= N;i++){
	                	ProcessI = ProcessS.indexOf(",")+1;
	                	if (ProcessI == -1){
	                		break;
	                	}
	                	ProcessS = ProcessS.substring(ProcessI, ProcessS.length());
	                	index1 = index1 +ProcessI;
	                	if(i == (N-1)){
	                		index2 =index1;
	                	}
	                }
	                String subline1 = line.substring(0, index2);
	                String subline2 = line.substring(index1, line.length());
	                String subline = subline1 + subline2;
	                if(bsPrint < 5){
	                 	 System.out.println("subline_bsFile:"+subline);	
	               }
	                //key出现一次，value值加1 
	                if (baseFileSameItemMap.containsKey(subline)) { //如果map中已经包含该key，给value加1
	                    Integer sameValue = baseFileSameItemMap.get(subline);
	                    baseFileSameItemMap.put(subline, sameValue + 1);
	                } else {
	                    baseFileSameItemMap.put(subline, 1); //第一次出现，value值为1
	                }     
	            }
	            br.close();     //关闭流

	            // read the cmp file . 读取结果文件，并处理其中的每一行
	            File resultFile = new File(rsFile);
	            BufferedReader bufferedReader = new BufferedReader(new FileReader(resultFile));
	            int rsPrint =0;
	            while ((line = bufferedReader.readLine()) != null) {
	            	rsPrint++;
	                line = line.trim();
	                if (line.isEmpty())  //空行不会加到map中
	                {
	                	continue;
	                }
	                
	                //去掉第N个逗号和N-1逗号之间的字符串
	                int index1 = 0;
	                int index2 = 0;
	                int ProcessI =0;
	                String ProcessS = line;
	                for(int i = 1; i <= N;i++){
	                	ProcessI = ProcessS.indexOf(",")+1;
	                	if (ProcessI == -1){
	                		break;
	                	}
	                	ProcessS = ProcessS.substring(ProcessI, ProcessS.length());
	                	index1 = index1 +ProcessI;
	                	if(i == (N-1)){
	                		index2 =index1;
	                	}
	                }
	                String subline1 = line.substring(0, index2);
	                String subline2 = line.substring(index1, line.length());
	                String subline = subline1 + subline2;
	                if(rsPrint < 5){
	                    System.out.println("subline_rsFile:"+subline);
	                }
	                
	                //判断是否存在map中
	                if (baseFileSameItemMap.containsKey(subline)) {
	                    Integer value = baseFileSameItemMap.get(subline); //在基线map中查找该行
	                    if (value > 1) {   //如果value大于1，说明还存在多次
	                        baseFileSameItemMap.put(subline, value - 1);  //出现一次，减1
	                    } else if (value == 1) {  //如果当前value为1，
	                        baseFileSameItemMap.remove(subline);  //直接从map中remove掉该项
	                    }
	                } else {//如果找不到该行，基线和结果不一致
//	                    System.out.println("Can not find this line \"" + line + "\" in base file");
	                    bufferedReader.close();
	                    currentline = subline ;
	                    flag = false;
	                    return flag;
	                }
	                lineNo++;
	                
	            }
	            bufferedReader.close(); //关闭流
	            /*The Base file large than result file.*/
	            if (baseFileSameItemMap.size() > 0) {  //如果Map中还存在没有被remove掉的项，说明基线比结果多
	                System.out.println("The Base File large than result file:" + baseFileSameItemMap);
	                flag = false;
	                
	            }else {
	            	 /*The Result file is the same to Base file.*/  //
	                System.out.println("The Resutl file is the same to Base file.");
	                flag = true;
	                
	            }     
	        } catch ( FileNotFoundException e )

	        {
	            e.printStackTrace();
	        } catch ( IOException e )

	        {
	            e.printStackTrace();
	        }
	        return flag;
	    }
	    /*
	     * 去掉第N个逗号和N-m逗号之间的字符串 --- 
	     * */ 
	    public boolean csv_Compare_SubString(String baseFile, String rsFile,int endIndex,int[] O) {
	    	
	    	boolean flag = false ;
	    	
	        //此处定义基线文件的存储 HashMap,key是每行的值，value是该行出现的次数
	        Map<String, Integer> baseFileSameItemMap = new HashMap<String, Integer>();
	        try {
	            //读取基线文件
	            File bsfile = new File(baseFile);
	            BufferedReader br = new BufferedReader(new FileReader(bsfile));
	            String line = null;
	            int bsPrint =0;
	            while ((line = br.readLine()) != null) {
	            	bsPrint++;
	                line = line.trim();
	                if (line.isEmpty())  //空行不会加到map中
	                {
	                	continue;
	                }
	                int end = 0;
	                if(-1 == endIndex){
	                		end = line.length();	
	                }else{
	                	int endNumber = 0;
	                	String endProcess = line;
	                    for(int i = 1; i <= endIndex;i++){
	                    	endNumber = endProcess.indexOf(",")+1;
	                    	if (endNumber == -1){
	                    		break;
	                    	}
	                    	endProcess = endProcess.substring(endNumber, endProcess.length());
	                    	end = end +endNumber;
	                    }
	                }
	              
	            	
	                //去掉第N个逗号和N-1逗号之间的字符串
	                int[] Number  = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	                for(int j = 0;j<O.length;j++){
	                	int Index = 0;
	                	String Process = line;
	                    for(int i = 1; i <= O[j];i++){
	                    	Index = Process.indexOf(",")+1;
	                    	if (Index == -1){
	                    		break;
	                    	}
	                    	Process = Process.substring(Index, Process.length());
	                    	Number[j] = Number[j] +Index;
	                    	if(i == (O[j]-1)){
	                    		 Number[j+10] =Number[j];
	                    	}
	                    }
	                }
	                int aa = 0;
	                for(int i = 0 ;i<(Number.length)/2 &&Number[i]!=0 ;i++){
	                	aa ++;
	                }
	                String subline ="";
	            	if(aa == 1){
	            		 subline = line.substring(0, Number[10])+line.substring(Number[0], end);
	            	}else if(aa == 2){
	            		 subline = line.substring(0, Number[10])+line.substring(Number[0], Number[11]) + line.substring(Number[1], end);
	            	}else if(aa == 3){
	            		 subline = line.substring(0, Number[10])+line.substring(Number[0], Number[11])+ line.substring(Number[1], Number[12]) + line.substring(Number[2], end);
	            	}else if(aa == 4){
	            		 subline = line.substring(0, Number[10])+line.substring(Number[0], Number[11])+ line.substring(Number[1], Number[12])+ line.substring(Number[2], Number[13]) + line.substring(Number[3], end);
	            	}else if(aa == 5){
	            		 subline = line.substring(0, Number[10])+line.substring(Number[0], Number[11])+ line.substring(Number[1], Number[12])+ line.substring(Number[2], Number[13])+ line.substring(Number[3], Number[14]) + line.substring(Number[4], end);
	            	}
	                if(bsPrint < 5){
	                  	 System.out.println("subline_bsFile:"+subline);	
	                }
	                //key出现一次，value值加1 
	                if (baseFileSameItemMap.containsKey(subline)) { //如果map中已经包含该key，给value加1
	                    Integer sameValue = baseFileSameItemMap.get(subline);
	                    baseFileSameItemMap.put(subline, sameValue + 1);
	                } else {
	                    baseFileSameItemMap.put(subline, 1); //第一次出现，value值为1
	                }     
	            }
	            br.close();     //关闭流

	            // read the cmp file . 读取结果文件，并处理其中的每一行
	            File resultFile = new File(rsFile);
	            BufferedReader bufferedReader = new BufferedReader(new FileReader(resultFile));
	            int rsPrint = 0;
	            while ((line = bufferedReader.readLine()) != null) {
	            	rsPrint++;
	                line = line.trim();
	                if (line.isEmpty())  //空行不会加到map中
	                {
	                	continue;
	                }
	                int end = 0;
	                if(-1 == endIndex){
	                		end = line.length();	
	                }else{
	                	int endNumber = 0;
	                	String endProcess = line;
	                    for(int i = 1; i <= endIndex;i++){
	                    	endNumber = endProcess.indexOf(",")+1;
	                    	if (endNumber == -1){
	                    		break;
	                    	}
	                    	endProcess = endProcess.substring(endNumber, endProcess.length());
	                    	end = end +endNumber;
	                    }
	                }
	                //去掉第N个逗号和N-1逗号之间的字符串
	                int[] Number  = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	                for(int j = 0;j<O.length;j++){
	                	int Index = 0;
	                	String Process = line;
	                    for(int i = 1; i <= O[j];i++){
	                    	Index = Process.indexOf(",")+1;
	                    	if (Index == -1){
	                    		break;
	                    	}
	                    	Process = Process.substring(Index, Process.length());
	                    	Number[j] = Number[j] +Index;
	                    	if(i == (O[j]-1)){
	                    		 Number[j+10] =Number[j];
	                    	}
	                    }
	                }
	                int aa = 0;
	                for(int i = 0 ;i<(Number.length)/2 &&Number[i]!=0 ;i++){
	                	aa ++;
	                }
	                String subline ="";
	            	if(aa == 1){
	            		 subline = line.substring(0, Number[10])+line.substring(Number[0], end);
	            	}else if(aa == 2){
	            		 subline = line.substring(0, Number[10])+line.substring(Number[0], Number[11]) + line.substring(Number[1], end);
	            	}else if(aa == 3){
	            		 subline = line.substring(0, Number[10])+line.substring(Number[0], Number[11])+ line.substring(Number[1], Number[12]) + line.substring(Number[2], end);
	            	}else if(aa == 4){
	            		 subline = line.substring(0, Number[10])+line.substring(Number[0], Number[11])+ line.substring(Number[1], Number[12])+ line.substring(Number[2], Number[13]) + line.substring(Number[3], end);
	            	}else if(aa == 5){
	            		 subline = line.substring(0, Number[10])+line.substring(Number[0], Number[11])+ line.substring(Number[1], Number[12])+ line.substring(Number[2], Number[13])+ line.substring(Number[3], Number[14]) + line.substring(Number[4], end);
	            	}

	                if(rsPrint < 5){
	               	 System.out.println("subline_rsFile:"+subline);	
	               }
	                
	                //判断是否存在map中
	                if (baseFileSameItemMap.containsKey(subline)) {
	                    Integer value = baseFileSameItemMap.get(subline); //在基线map中查找该行
	                    if (value > 1) {   //如果value大于1，说明还存在多次
	                        baseFileSameItemMap.put(subline, value - 1);  //出现一次，减1
	                    } else if (value == 1) {  //如果当前value为1，
	                        baseFileSameItemMap.remove(subline);  //直接从map中remove掉该项
	                    }
	                } else {//如果找不到该行，基线和结果不一致
//	                    System.out.println("Can not find this line \"" + line + "\" in base file");
	                    bufferedReader.close();
	                    currentline = subline ;
	                    flag = false;
	                    return flag;
	                }
	                lineNo++;
	                
	            }
	            bufferedReader.close(); //关闭流
	            /*The Base file large than result file.*/
	            if (baseFileSameItemMap.size() > 0) {  //如果Map中还存在没有被remove掉的项，说明基线比结果多
	                System.out.println("The Base File large than result file:" + baseFileSameItemMap);
	                flag = false;
	                
	            }else {
	            	 /*The Result file is the same to Base file.*/  //
	                System.out.println("The Resutl file is the same to Base file.");
	                flag = true;
	                
	            }     
	        } catch ( FileNotFoundException e ){
	            e.printStackTrace();
	        } catch ( IOException e ){
	            e.printStackTrace();
	        }
	        return flag;
	    }
		public int getLineNo() {
			return lineNo;
		}
		
		public String getLineStr() {
			return currentline;
		}

}
