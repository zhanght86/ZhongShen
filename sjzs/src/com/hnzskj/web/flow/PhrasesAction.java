/**
 * OA
 *com.hnzskj.web.flow
 *2012-6-122012上午10:06:28
 *郑辉
 *
 */
package com.hnzskj.web.flow;

import java.util.ArrayList;
import java.util.List;

import com.hnzskj.persist.bean.flow.Phrases;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.service.flow.PhrasesService;
import com.hnzskj.web.BaseAction;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-6-12 上午10:06:28
 *修改人：郑辉
 *修改时间：
 */
@SuppressWarnings("serial")
public class PhrasesAction extends BaseAction {
	Phrases phrases =new Phrases();
	Employee employee =new Employee();
	private PhrasesService phrasesService =null;
	private List<Phrases> list =new ArrayList<Phrases>();
	public Phrases getPhrases() {
		return phrases;
	}

	public void setPhrases(Phrases phrases) {
		this.phrases = phrases;
	}

	public PhrasesService getPhrasesService() {
		return phrasesService;
	}

	public void setPhrasesService(PhrasesService phrasesService) {
		this.phrasesService = phrasesService;
	}

	public List<Phrases> getList() {
		return list;
	}

	public void setList(List<Phrases> list) {
		this.list = list;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String toPhrasesList(){
	    employee =this.getEmplFromSession();
		list =phrasesService.getPhrsesList(employee.getEmplId());
		return "toListPage";
	}
	
	public String doAddPage(){
		list =phrasesService.getPhrsesList(employee.getEmplId());
		employee =this.getEmplFromSession();
		if(list.size()!=0){
			phrasesService.deleteInfo(employee.getEmplId());
		}
		phrasesService.addInfo(phrases);
		return "doAdd";
	}
	
	public String choosePhrasesList(){
		employee =this.getEmplFromSession();
		list =phrasesService.getPhrsesList(employee.getEmplId());
		return "choosePhrases";
	}
}
