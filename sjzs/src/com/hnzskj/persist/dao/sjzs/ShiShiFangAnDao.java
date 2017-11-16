package com.hnzskj.persist.dao.sjzs;

import java.util.LinkedHashMap;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.ShiShiFangAnDTO;
/**
 * 
 * 描述：审计实施方案dao接口<br/>
 * 创建人：wenxuanzhen <br/>
 * 创建时间：2013-3-27   <br/>  
 * 修改备注：     <br/>
 * @version   1.0
 */
public interface ShiShiFangAnDao {


   public String addSSFA(ShiShiFangAnDTO shiShiFangAn);

   public int deleteSSFA(String id);

   public ShiShiFangAnDTO getSSFAById(String id);

   public Page<ShiShiFangAnDTO> searchByCondition(Page<ShiShiFangAnDTO> page, String fields, String string, Object[] array, LinkedHashMap<String, String> orderBy);

   public Page<ShiShiFangAnDTO> searchSSFAByParentId(Page<ShiShiFangAnDTO> page, String fields,String sqlCondition, Object[] params, LinkedHashMap<String, String> orderBy);

   public int updateSSFA(ShiShiFangAnDTO shiShiFangAn);
   
	/**
	 * 
	 * 方法描述: 批量删除实施方案  <br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-6 上午09:26:32
	 * @param ids id数组  以 "id,id..."格式拼接
	 * @param phDel 是否物理删除  true-物理删除
	 *
	 */
	public int batchDeleteSSFA(String ids, boolean phDel);

}
