/*基于jQuery Validate的自定义验证规则*/
/*
 * 已经存在的验证规则：
 * 		mobile				手机号
 * 		idCard				身份证号
 * 		phone				电话号码
 * 		zipCode				邮政编码
 * 		qq					QQ号码
 * 		ip					Ip地址
 * 		chrnum				字母和数字组合
 * 		chinese				中文字母
 * 		byteRangeLength		字节长度
 * 		stringCheck			字符验证,中文，英文字母，数字，下划线
 * 		dateSize 			验证时间大小
 * 		numCompare 			金额的对比
 * 		stringNumber        验证字母，数字，下划线
 * 		hourAndMin          验证 时间格式   HH：mm
 */

/**
 * 自定义的验证方法－－验证手机号码格式是否正确
 */
$.validator.addMethod(
	'mobile',		
	function (value, element, param) {
		return this.optional(element) || checkPhoneNumber(value);					
	},
	'请输入格式正确的手机号'
);

/**
 * 判断字符串是否是手机号码
 * 0是手机号码 1不是11位数字 2号码前缀不正确
 * 用于检验手机号的位数以及检验此手机中是否为中国移动的手机号
 * 由于存在携号转网的情况 允许3个运营商的全部号段（试点中）
 * 中国移动号码段：134(0至8号段) 135 136 137 138 139 147 150 151 152 157 158 159 182 183 187 188
 * 中国联通号码段：130 131 132 145 155 156 186
 * 中国电信号码段：133 153 180 189
 * @param phone	待验证的手机号
 * @return
 */
function checkPhoneNumber(phone) {
	var mbphnoM = /^(13[4-9])|^(147)|^(150)|^(151)|^(152)|^(157)|^(158)|^(159)|^(182)|^(183)|^(187)|^(188)/;
	var mbphnoU = /^(130)|^(131)|^(132)|^(145)|^(155)|^(156)|^(186)/;
	var mbphnoT = /^(133)|^(153)|^(180)|^(189)/;      
	var num11 = /\d{11}/; // 11位数字;      
	if (null != phone && "" != phone && num11.exec(phone)) {		
		if (mbphnoM.exec(phone) || mbphnoU.exec(phone) || mbphnoT.exec(phone)) {
			return true;
		} else {
			return false;
		}
	} else {
		return false;
	}
}

/*
 * ====================================自定义验证方法分隔线==============================================
 */

/**
 * 自定义的验证方法－－验证身份证（15位和18位）格式是否正确
 */
$.validator.addMethod(
	'idCard',		
	function (value, element, param) {
		return this.optional(element) || checkIdCard(value);					
	},
	'请输入格式正确的身份证号'
);

/**
 * 验证身份证号码格式是否正确
 * @param idCard	待验证的手机号码
 * @return
 */
function checkIdCard(idCard) {
	var isIDCard1 = new Object();
	var isIDCard2 = new Object();
	// 身份证正则表达式(15位)
	isIDCard1 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
	// 身份证正则表达式(18位)
	isIDCard2 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/;
	if (isIDCard1.test(idCard) || isIDCard2.test(idCard)) {
		return true;
	}
	return false;
}

/*
 * ====================================自定义验证方法分隔线==============================================
 */

/**
 * 自定义的验证方法－－验证电话号码 
 */   
$.validator.addMethod(
	"phone",
	function(value, element) { 
		var tel = /^0\d{2,3}-\d{5,9}$/ ;//电话号码格式010-12345678 
		if(this.optional(element) || (tel.test(value)))
		{
			return true;
		}
	}, 
	"电话号码格式错误"
); 

/*
 * ====================================自定义验证方法分隔线==============================================
 */

/**
 * 自定义的验证方法－－邮政编码验证 
 */   
$.validator.addMethod(
	"zipCode", 
	function(value, element) {   
	    var tel = /^[0-9]{6}$/;   
	    return this.optional(element) || (tel.test(value));   
	}, 
	"邮政编码格式错误"
);   

/*
 * ====================================自定义验证方法分隔线==============================================
 */
  
/**
 * 自定义的验证方法－－QQ号码验证
 */  
$.validator.addMethod(
	"qq", 
	function(value, element) {   
	    var tel = /^[1-9]\d{4,10}$/;   
	    return this.optional(element) || (tel.test(value));   
	}, 
	"qq号码格式错误"
);   

/*
 * ====================================自定义验证方法分隔线==============================================
 */
 
/**
 * 自定义的验证方法－－IP地址验证 
 */ 
$.validator.addMethod(
	"ip", 
	function(value, element) {   
	    var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;   
	    return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));   
	}, 
	"Ip地址格式错误"
);   

/*
 * ====================================自定义验证方法分隔线==============================================
 */
 
/**
 * 自定义的验证方法－－字母和数字的验证 
 */ 
$.validator.addMethod(
	"chrnum", 
	function(value, element) {   
	    var chrnum = /^([a-zA-Z0-9]+)$/;   
	    return this.optional(element) || (chrnum.test(value));   
	}, 
	"只能输入数字和字母(字符A-Z, a-z, 0-9)"
);   

/*
 * ====================================自定义验证方法分隔线==============================================
 */

/**
 * 自定义的验证方法－－中文的验证 
 */
$.validator.addMethod(
	"chinese", 
	function(value, element) {   
		var chinese = /^[\u4e00-\u9fa5]+$/;   
		return this.optional(element) || (chinese.test(value));   
	}, 
	"只能输入中文"
);   

/*
 * ====================================自定义验证方法分隔线==============================================
 */
  
/**
 * 自定义的验证方法－－字节长度验证   
 */
$.validator.addMethod(
	"byteRangeLength", 
	function(value, element, param) {   
		var length = value.length;   
		for (var i = 0; i < value.length; i++) {   
			if (value.charCodeAt(i) > 127) {   
	            length++;   
	        }   
	    }   
		return this.optional(element) || (length >= param[0] && length <= param[1]);   
	}, 
	$.validator.format("请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)")
);  

/*
 * ====================================自定义验证方法分隔线==============================================
 */
  
/**
 * 自定义的验证方法－－字符验证,中文，英文字母，数字，下划线stringCheck
 */
$.validator.addMethod(
	"stringCheck", 
	function(value, element) { 
		return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value); 
	}, 
	"只能输入中文字、英文字母、数字和下划线"
); 

/**
 * 自定义的验证方法————验证时间的大小
 */
$.validator.addMethod(
		"dateSize",
		function(value, element,param) { 			
		  var arr1=value.replace("-","").replace("-","");
		  var arr2=param.replace("-","").replace("-","");		
		return this.optional(element) || arr1>arr2; 
		}, 
		"截至日期不能小于开始日期"
	); 


/**
 * 自定义的验证方法————验证投资的大小
 */
$.validator.addMethod(
		"numCompare",
		function(value, element,param) { 
			value = parseInt(value);
			param = parseInt(param);
			return this.optional(element) || value<=param; 
		}, 
		"已下达投资不能大于总投资"
	); 
/*
 * ====================================自定义验证方法分隔线==============================================
 */
  
/**
 * 自定义的验证方法－－字符验证,英文字母，数字，下划线stringNumber
 */
$.validator.addMethod(
	"stringNumber", 
	function(value, element) { 
		return this.optional(element) || /^[\w]+$/.test(value); 
	}, 
	"只能输入中文字、英文字母、数字和下划线"
); 
/**
 * 自定义的验证方法－－验证时间格式 HH：mm
 */
$.validator.addMethod(
	"hourAndMin", 
	function(value, element) { 
		return this.optional(element) || /^(([01][0-9])|20|21|22|23):[012345][0-9]$/.test(value); 
	}, 
	"请输入正确的时间格式  如08:00"
);

/**
 * 自定义的验证方法－－验证时间格式 yyyy-mm-dd
 */
$.validator.addMethod(
	"dateyyyymmdd", 
	function(value, element) { 
		return this.optional(element) || /^^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/.test(value); 
	}, 
	"请输入正确的时间格式  如2012-07-01"
);