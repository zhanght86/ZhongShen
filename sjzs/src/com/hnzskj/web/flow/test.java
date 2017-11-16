/**
 * OA
 *com.hnzskj.web.flow
 *2012-4-182012下午07:30:40
 *郑辉
 *
 */
package com.hnzskj.web.flow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptException;

/**
 *创建人：郑辉 描述： 创建时间：2012-4-18 下午07:30:40 修改人：郑辉 修改时间：
 */
public class test {

	/**
	 *创建人：郑辉 描述： 创建时间：2012-4-18 下午07:30:40 修改人：郑辉 修改时间： 返回类型： void
	 * @throws ParseException 
	 * @throws ScriptException 
	 */
	public static void main(String[] args) throws ParseException, ScriptException {
		// TODO Auto-generated method stub
//		List<Tache> list = new ArrayList<Tache>();
//		Tache t1 = new Tache();
//		t1.setTache_id(1);
//		Tache t2 = new Tache();
//		t2.setTache_id(2);
//		list.add(t2);
//		list.add(t1);
//
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getTache_id());
//		}
//		Collections.sort(list, new Comparator<Tache>() {
//			public int compare(Tache s1, Tache s2) {
//				Integer p1 = s1.getTache_id();
//				Integer p2 = s2.getTache_id();
//				return p1.compareTo(p2);
//			}
//		});
//
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getTache_id());
//		}
		    
//		String a ="912FA215-7C05-47AA-95F1-D23EE3E38C49,2DAD7B70-CE19-499D-9465-8F0DC52FA018,138C3FB5-49EA-4BE6-8C7C-D6A395A7C565,";
//		String b ="912FA215-7C05-47AA-95F1-D23EE3E38C49";
//		
//		//System.out.println(a.indexOf(b));
//		
//		if(a.indexOf(b)!=-1) System.out.println("包含");
//		
//		StringBuffer sb =new StringBuffer();
//		sb.append(a.toCharArray());
//		//System.out.println(sb.toString());
//		char[] c ={'我','是','中','国','人'};
//		sb.insert(sb.length()-1, c);
//		//System.out.println(sb.toString());
//		
//		
//		String time =DataUtil.getNowTime();
//		java.text.SimpleDateFormat df =new java.text.SimpleDateFormat ("yyyy-mm-dd");
//
//			Date nowTime =df.parse(time);
//			Date oldTime =df.parse("2012-07-05");
//			//System.out.println((nowTime.before(oldTime)));
//		
//			String mes ="我是忠过人,呢";
//			
//			
//			//String mes =<tr><td align="middle">郑辉</td><td align="middle"><input style="border-bottom: 0px;border-left: 0px;border-top: 0px;border-right: 0px" onclick="javascript:delPer('郑辉','2E6995F0-2DB0-4A2D-9A7C-CBA91AEAFE40')" value=删除 type=button></td></tr>                              <tr><td align=middle>郑辉</td><td align=middle><input style="border-bottom: 0px; border-left: 0px; border-top: 0px; border-right: 0px" onclick="javascript:delper('郑辉','2e6995f0-2db0-4a2d-9a7c-cba91aeafe40')" value=删除 type=button></td></tr>
//			
//			String old ="sddsdsds hello 阿百   abcdefg A";
//			String news ="sddsdsds 阿百   abcdefg Abcdesfffff";
//			
//			
			//System.out.println(longestCommonSubstring(old,news));
			
			@SuppressWarnings("unused")
			List<String> emps = new ArrayList<String>();
			
//			 
//			emps.add(0, "我");
//			emps.add(1, "是");
//			emps.add(2, "中");
//			emps.add(4, "人");
//			emps.add(3, "国");
//			
//			for(int i =0 ;i< emps.size();i++){
//				System.out.println(emps.get(i).toString());
//			}
////
//			
//			 List list =new ArrayList();     
//			 String[] a =new String[]{"1","3","4","5"};   
//			 String[] b =new String[5];     
//			 System.arraycopy(a, 0, b, 0, 1);   
//			 b [1]="2";  
//			 System.arraycopy(a,1,b,2,a.length-1);     
//			 for(int i=0;i<b.length;i++){   
//				 System.out.println(b[i]); 
//				 }
//
//			String type ="FFBDD4D4-5A64-40DE-85F4-B5EC40177B82,,4410D6B7-AE79-4405-825A-13CBFD784578,7920D6A2-4206-4353-A1AE-0CB5DA198FE1,D95A0ACA-05E0-41A1-AE66-0A5D00F6D882,1096218F-CBAF-4938-B6CF-2C764E9A30A4,204CEA81-9A8E-47B6-BB2A-092F55DB6E44,32D60686-2D4B-46A8-9C0C-0B2825AB182A,377475D3-E0DE-4D71-ABE1-8FB303B7E389,58C00237-25E5-40B0-83F4-81FF83A90170,90A8D88A-2BF0-43CC-93A0-B9A77FE87538,9A232F96-C3BD-4F3B-9431-AAEB42DE1B5A,AD65BD3A-5F1D-473A-BB9E-8C88871C2C13,B1B4E201-CFF7-4EB5-A486-23F2EEDC5D5C,DB8205C1-788A-4697-A59A-38ED0B356A32,E909F95E-B6FD-4EED-A533-F88A573C07DB,4D3CDDA5-BAF7-4C84-AD18-581E9AE37FB6,89AAC02D-1ECA-4568-B053-D27EFF8B6244,95E8C662-2341-49E0-9772-1C0175F0C9A1,45EFC6F0-DA40-4820-9910-ADF4A5FFD846,BE847C40-FB63-43DE-8828-C0B734130C25,3FE30279-C1C8-4F31-B97C-9D7F9832E5A9,B14BCDC2-F195-4910-AC11-263B916CEDE9,A4D52E9D-F18C-494F-B319-EAF574193C1A,9BA062F2-E75F-4C95-A6B9-76EF34CC7D48,09CEB12F-6585-424A-907B-FE6B9F4A1A59,11483CA7-28BF-4764-BD95-8CCC8FD18110,19AEADDF-85BA-4C17-B19B-DD3236D328A5,2E6995F0-2DB0-4A2D-9A7C-CBA91AEAFE40,4B4FAFE4-61A3-48A6-9B91-1A9C42141EA2,8A1CDB62-4C10-4A53-9AF9-E37D0625C8EB,C2417F5D-24E0-4CCE-99BE-DFA11299DEEA,D14CC48B-D1E2-440F-97F1-EF443978B6D5,4E1B75F0-6FFE-4008-A9F6-DF9B2CD72FCD,4561EC4F-5122-490D-848C-E42615AFEB0E";
//			System.out.println(type.length());
//
//			String myString = "1<3";
//			ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
//			boolean res = (Boolean) jse.eval(myString);
//			if(res){
//				System.out.println("hello accp");
//			}
//			
//			String journalDate="2012-08-12 08:30:00";
//			String journalCommit="2012-08-13 08:31:00";
//			Calendar calendar = Calendar.getInstance();
//			Date date =DataUtil.getDate(journalDate);
//			calendar.setTime(date);
//			calendar.add(Calendar.DATE,1);
//			//System.out.println(calendar.getTime().after(DataUtil.getDate(journalCommit)));
//			
//			  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			  java.util.Date now = df.parse("2004-03-26 13:31:40");
//			  java.util.Date date=df.parse("2004-03-2 11:30:24");
//			  long l=now.getTime()-date.getTime();
//			  long day=l/(24*60*60*1000);
//			  long hour=(l/(60*60*1000)-day*24);
//			  long min=((l/(60*1000))-day*24*60-hour*60);
//			  long s=(l/1000-day*24*60*60-hour*60*60-min*60);
//			  System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");


			 
			
			 

	}
	
	
	
	
	public static String longestCommonSubstring(String first, String second) {
		String tmp = "";
		String max = "";
		for (int i=0; i < first.length(); i++){
		for (int j = 0; j < second.length(); j++){
		for (int k = 1; (k+i) <= first.length() && (k+j) <= second.length(); k++){
		if (first.substring(i, k + i).equals(second.substring(j, k + j))){
		tmp = first.substring(i, k + i);
		}
		else{
		if (tmp.length() > max.length())
		max = tmp;
		tmp = "";
		}
		}
		if (tmp.length() > max.length())
		max = tmp;
		tmp = "";
		}
		}
		return max;       
		}

}


