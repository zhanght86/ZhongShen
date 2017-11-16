package com.hnzskj.service.sjzs;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.ShiShiFangAnDTO;
/**
 * 
 * 描述：审计实施方案service类<br/>
 * 创建人：wenxuanzhen <br/>
 * 创建时间：2013-3-27   <br/>  
 * 修改备注：     <br/>
 * @version   1.0
 */
public interface ShiShiFangAnService {

    public	ShiShiFangAnDTO getSSFAById(String id);

    public	Page<ShiShiFangAnDTO> searchSSFAByParentId(ShiShiFangAnDTO shiShiFangAn, Page<ShiShiFangAnDTO> page);

    public	Page<ShiShiFangAnDTO> searchByCondition(String string, ShiShiFangAnDTO shiShiFangAn, Page<ShiShiFangAnDTO> page);

    public	int updateSSFA(ShiShiFangAnDTO shiShiFangAn);

    public	int deleteSSFA(String string);

    public	String addSSFA(ShiShiFangAnDTO shiShiFangAn);

}
