/*
 * @项目名称: htglxt
 * @文件名称: EmployeeDao.java
 * @日期: 2011-5-23
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.common;

import java.lang.reflect.Field;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

/**
 * 类名称：BaseDao<br/>.
 * 类描述：此类是dao层的一个基类，实现的功能有实现对数据库数据的增加，删除，修改，查询，<br/>
 * 是基于apache的Dbutils组件实现的对数据库的操作<br/>
 * 增加，修改，查询调用的是同一方法update(String sqlString, Object[] params);<br/>
 * 查询时可以自动完成对结果集的封装,无需手动进行对Bean的属性进行赋值操作<br/>
 * 注意：
 * (1)dbutis组件对结果集的封装是通过查询出的字段名和Bean的get和set方法，实现对属性的自动赋值操作。所以一定要注意<br/>
 * <b>Bean的get和set方法一定要符合JavaBean规范</b>
 * (2)要注意在sql语句中的词语之间要有空格update table1 set name=?, age=? where id=?中
 * age和它前面的逗号(",")之间一定要有一个空格
 * (3)对结果集的封装目前尚不能实现联表查询结果的封装,若要实现联表查询操作，并自动封装结果集，<br/>
 * 可以和结果集相对应的Bean或则在现在的Bean的基础上添加属性，并且Bean的<br/>
 * get和set方法可通过查询字段的名称利用反射得到
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-24 下午04:11:33   <br/>
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-24 下午04:11:33   <br/>
 * 修改备注：     <br/>
 * @version   1.0     g
 */
public class BaseDao {
	protected Connection conn = null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;	
	protected static final Logger log = Logger.getLogger(BaseDao.class);
	private static String strPoolName = null;
	
	/**
	 * 从数据库连接池中提取一个可用数据库连接
	 * @author 常利召
	 * @return Connection对象
	 */
	public static Connection getConnection() {
		strPoolName = "proxool.sqlServer_OA";
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
		} catch (ClassNotFoundException cfe) {
			log.error(DataUtil.getStackTraceAsString(cfe));
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(strPoolName);
		} catch (SQLException e) {
			log.error(DataUtil.getStackTraceAsString(e));;			
			log.error("获取数据库连接失败:" + e.getMessage());
			log.error(DataUtil.getStackTraceAsString(e));
			throw new SystemException("连接数据库失败！");
		}
		return conn;		
	}	
	/**
	 * 
	 * 方法描述：根据指定的连接词名称，获得一个可用的数据库连接<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-24 下午01:58:10<br/>         
	 * @param strPoolID
	 * @return
	 * @version   1.0
	 */
	public static Connection getConnection(String strPoolID){
		strPoolName = "proxool." + strPoolID;
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
		} catch (ClassNotFoundException cfe) {
			log.error(DataUtil.getStackTraceAsString(cfe));
		}
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(strPoolName);
		} catch (SQLException e) {
			log.error("获取数据库连接失败:" + e.getMessage());
			log.error(DataUtil.getStackTraceAsString(e));
			throw new SystemException("连接数据库失败！");
		}
		return conn;
	}


	/**
	 * 
	 * 方法描述：根据指定的SQL语句执行操作，可进行增加，删除，修改操作，<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-24 下午01:50:03<br/>         
	 * @param sql 执行的sql语句
	 * @param params	sql语句中的参数
	 * @return	返回此次操作所影响的数据库记录的行数
	 * @version   1.0
	 */
    public int update(String sql, Object[] params) {
        int result = 0;
        try {
            conn = getConnection();
            QueryRunner qRunner = new QueryRunner();
            if ( null != params && params.length > 0) {
            	result = qRunner.update(conn, sql, params);
            } else {
            	result = qRunner.update(conn, sql);
            }
            log.info("执行SQL语句：" + sql + outPutArray(" 参数列表为：", params));
        } catch (SQLException e) {
        	log.error(DataUtil.getStackTraceAsString(e));
            //log.error(DataUtil.getStackTraceAsString(e));;
        } finally {
        	closeConn(conn);
        }
        return result;
    }
    
	/**
	 * 
	 * 方法描述：执行多条sql语句，放在事务中进行处理<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-24 上午11:17:29<br/>         
	 * @param sqls
	 * @param params
	 * @return
	 * @version   1.0
	 * @throws SQLException 
	 */
	public int update(String[] sqls, Object[][] params) {
		int result = 0;
		if ( null != sqls && sqls.length > 0) {
			try {
				conn = getConnection();
				conn.getAutoCommit();
				conn.setAutoCommit(false);
				for (int i = 0; i < sqls.length; i++) {
					pstmt = conn.prepareStatement(sqls[i]);
					Object[] obj = null;
					if (params != null && params[i] != null) {
						setQueryParamters(pstmt, params[i]);
						obj = params[i];
					}
					result += pstmt.executeUpdate();
					log.info("执行SQL语句:" + sqls[i] + outPutArray("参数列表:", obj));
				}
				conn.commit();
			} catch (SQLException e) {
				log.error(DataUtil.getStackTraceAsString(e));
				try {
					conn.rollback();
				} catch (SQLException e1) {
					log.error(DataUtil.getStackTraceAsString(e1));
					e1.printStackTrace();
				}
				log.error(DataUtil.getStackTraceAsString(e));;
			} finally {
				closeConn(conn);
			}
		}
		return result;
	}
	
	/**
	 * 方法描述:执行批处理，使用了事务进行控制<br/>
	 * 此方法可处理如下情况：要执行多条sql语句，但是多条sql语句类化，仅仅是参数不同<br/>
	 * 例如要向表中插入多条数据："insert into tablename (field1, field2, field3) values (?,?,?)"	 * 
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-1 下午05:40:07<br/>         
	 * @param sql
	 * @param params
	 * @param countSQL
	 * @return
	 * @version   1.0 
	 */
	public int updateBatch(String sql, Object[][] params, int countSQL) {
		int result = 0;
			try {
				conn = getConnection();
				conn.getAutoCommit();
				conn.setAutoCommit(false);
				for (int i = 0; i < countSQL; i++) {
					pstmt = conn.prepareStatement(sql);
					setQueryParamters(pstmt, params[i]);
					result = pstmt.executeUpdate();
					log.info("执行SQL语句:" + sql + outPutArray("参数列表:", params[i]));
				}
				conn.commit();
			} catch (SQLException e) {
				log.error(DataUtil.getStackTraceAsString(e));
				try {
					conn.rollback();
				} catch (SQLException e1) {
					log.error(DataUtil.getStackTraceAsString(e1));
					e1.printStackTrace();
				}
				log.error(DataUtil.getStackTraceAsString(e));;
			} finally {
				try {
					pstmt.close();
				} catch (Exception e2) {
					log.info(e2.getMessage());
				}
				closeConn(conn);
			}
		return result;
	}
	
    /**
     * 
     * 方法描述：根据指定的sql语句查询数据库中的记录，返回此次查询结果的List，<br/>
     * 并将每一行的记录自动封装为实体<br/>
     * 创建人：苏国庆   <br/>
     * 创建时间：2011-5-24 下午01:53:26<br/>         
     * @param sql 查询的sql语句
     * @param clazz	查询的实体的Class,如实体Empolyee,则clazz为Empoloyee.class
     * @return	返回此次查询操作得到结果集封装在list中
     * @version   1.0
     */
    @SuppressWarnings("unchecked")
	public List query(String sql, Class clazz, Object[] params) {
        List beans = null;
        
        try {
            conn = getConnection();
            QueryRunner qRunner = new QueryRunner();
            if ( null != params && params.length > 0) {
            	beans = (List) qRunner.query( conn, sql,
            			new BeanListHandler(clazz), params);
            } else {
            	beans = (List) qRunner.query( conn, sql,
            			new BeanListHandler(clazz));
            }
            log.info("执行SQL语句：" + sql + outPutArray(" 参数列表为：", params));
        } catch (SQLException e) {
        	log.error(DataUtil.getStackTraceAsString(e));
            log.error(DataUtil.getStackTraceAsString(e));;
        } finally {
        	closeConn(conn);
        }
        return beans;
    }
	   
    /**
     * 
     * 方法描述：根据指定的SQL语句查询数据库中的一条记录，并将此条记录封装为一个实体对象<br/>
     * 创建人：苏国庆   <br/>
     * 创建时间：2011-5-24 下午01:55:08<br/>         
     * @param sql 查询的sql语句
     * @param clazz 实体的class
     * @param params sql语句中的参数
     * @return	返回此次查询操作得到的实体
     * @version   1.0
     */
    @SuppressWarnings("unchecked")
	public Object get(String sql, Class clazz,Object[] params) {
        List beans = null;
        Object obj = null;
        Connection conn = null;
        try {
            conn = getConnection();
            QueryRunner qRunner = new QueryRunner();
            if ( null != params && params.length > 0) {
            	beans = (List) qRunner.query(conn,
                		sql,new BeanListHandler(clazz),params);
            } else {
            	beans = (List) qRunner.query(conn,
                		sql,new BeanListHandler(clazz));
            }
            log.info("执行SQL语句：" + sql + outPutArray(" 参数列表为：", params));
        } catch (SQLException e) {
        	log.error(DataUtil.getStackTraceAsString(e));
            log.error(DataUtil.getStackTraceAsString(e));;
        } finally {
            closeConn(conn);
        }
        if(beans!=null && !beans.isEmpty()){ //注意这里
             obj=beans.get(0);
        }
        return obj;
    }
 
    /**
     * 
     * 方法描述：根据指定的SQL语句查询数据库中的记录，并将此条记录封装为一个实体对象list<br/>
     * 创建人：苏国庆   <br/>
     * 创建时间：2011-5-24 下午01:55:08<br/>         
     * @param sql 查询的sql语句
     * @param clazz 实体的class
     * @param params sql语句中的参数
     * @return	返回此次查询操作得到的实体
     * @version   1.0
     */
    @SuppressWarnings("unchecked")
	public List<Object> getList(String sql, Class clazz,Object[] params) {
        List<Object> beans = null;
        Connection conn = null;
        try {
            conn = getConnection();
            QueryRunner qRunner = new QueryRunner();
            if ( null != params && params.length > 0) {
            	beans = (List) qRunner.query(conn,
                		sql,new BeanListHandler(clazz),params);
            } else {
            	beans = (List) qRunner.query(conn,
                		sql,new BeanListHandler(clazz));
            }
            log.info("执行SQL语句：" + sql + outPutArray(" 参数列表为：", params));
        } catch (SQLException e) {
        	log.error(DataUtil.getStackTraceAsString(e));
            log.error(DataUtil.getStackTraceAsString(e));;
        } finally {
            closeConn(conn);
        }
//        if(beans!=null && !beans.isEmpty()){ //注意这里
//             obj=beans.get(0);
//        }
        return beans;
    }
    
    
    /**
     * 
     * 方法描述：向数据库插入一条数据，并返回插入到数据库的数据的自增id<br/>
     * 创建人：苏国庆   <br/>
     * 创建时间：2011-5-24 上午11:11:37<br/>         
     * @param sql 执行插入的sql语句
     * @param params sql语句中的参数
     * @return	返回此次插入操作获得的数据库的自增id
     * @throws SQLException
     * @version   1.0
     */
	public int insertDataAndGetId(String sql, Object[] params ) {
		int insertId = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			setQueryParamters(pstmt, params);
			pstmt.execute();//执行插入
			ResultSet rs = pstmt.getGeneratedKeys();//获得带有返回值的resultset
			rs.next();   
			insertId = rs.getInt(1);//拿到id
			log.info("执行SQL语句：" + sql + outPutArray(" 参数列表为：", params));
		} catch (SQLException e) {
			log.error(DataUtil.getStackTraceAsString(e));
			log.error(DataUtil.getStackTraceAsString(e));;
		} finally {
			close(rs, pstmt, conn);
		}
		return insertId;
	}
	
	/**
	 * 
	 * 方法描述：根据指定的sql语句查询数据库，结果集中只有一条记录，而且只有一个字段，如获得记录总数的查询语句<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-24 下午02:09:59<br/>         
	 * @param sql
	 * @param params
	 * @return
	 * @version   1.0
	 */
	public Object getSingleValue(String sql, Object[] params) {
		Object result = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			setQueryParamters(pstmt, params);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				 result = rs.getObject(1);
			}
			
			log.info("执行的SQL语句为："+sql + outPutArray(" 参数列表为: ",params));
		} catch (SQLException e) {
			log.error("执行的SQL语句出错："+ sql + outPutArray(" 参数列表为: ",params));
			log.error(DataUtil.getStackTraceAsString(e));
			log.error(DataUtil.getStackTraceAsString(e));;
		} finally {
			close(rs, pstmt, conn);
		}
		if(result == null){
			result = "0";
		}
		return result;
	}
	
	/**
	 * 方法描述：根据指定的sql语句查询数据库，结果集中有多条记录，但每条记录都只有一个字段<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-12-29 上午10:13:29<br/>         
	 * @param sql
	 * @param params
	 * @return
	 * @version   1.0  
	 */
	public List<String> getListSingleValue(String sql, Object[] params) {
		List<String> list = new ArrayList<String>();
		String result = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			setQueryParamters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				 result = rs.getString(1);
				 list.add(result);
			}
			log.info("执行的SQL语句为："+sql + outPutArray(" 参数列表为: ",params));
		} catch (SQLException e) {
			log.error("执行的SQL语句出错："+ sql + outPutArray(" 参数列表为: ",params));
			log.error(DataUtil.getStackTraceAsString(e));
			log.error(DataUtil.getStackTraceAsString(e));;
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	
	/**
	 * 
	 * 方法描述：执行关闭ResultSet, Statement,conn<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-24 下午02:14:24<br/>         
	 * @param rs
	 * @param stmt
	 * @param conn
	 * @version   1.0
	 */
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		closeRs(rs);
		closeStmt(pstmt);
		closeConn(conn);
	}
	
	/**
	 * 
	 * 方法描述：关闭结果集<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-24 下午02:14:05<br/>         
	 * @param rs
	 * @version   1.0
	 */
	private static void closeRs(ResultSet rs) {
		try {
			if (null != rs) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			log.error(DataUtil.getStackTraceAsString(e));
			log.error(DataUtil.getStackTraceAsString(e));;
		}
	}

	/**
	 * 
	 * 方法描述：关闭Statement<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-24 下午02:13:39<br/>         
	 * @param stmt
	 * @version   1.0
	 */
	private static void closeStmt(PreparedStatement pstmt) {
		try {
			if (null != pstmt) {
				pstmt.close();
				pstmt = null;
			}
		} catch (SQLException e) {
			log.error(DataUtil.getStackTraceAsString(e));
			log.error(DataUtil.getStackTraceAsString(e));;
		}
	}
	
	/**
	 * 
	 * 方法描述：关闭数据库连接<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-24 下午02:13:27<br/>         
	 * @param conn
	 * @version   1.0
	 */
	private static void closeConn(Connection conn) {
		try {
			if (null != conn) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			log.error(DataUtil.getStackTraceAsString(e));
			log.error(DataUtil.getStackTraceAsString(e));;
		}
	}
	
	/**
	 * 
	 * 方法描述：获得更新的sql语句<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-24 上午10:11:29<br/>         
	 * @param tableName 表的名称
	 * @param fields	字段数组
	 * @param primarykey 数据表的主键
	 * @return
	 * @version   1.0
	 */
	public static String getUpdateSql(String tableName, String[] fields, String primarykey) {
		StringBuilder updateFields = new StringBuilder();
		if (fields != null && fields.length > 0 ) {
			for (String field : fields) {
				updateFields.append(" " + field + "=?, ");
			}
		}
		updateFields.deleteCharAt(updateFields.length()-2);
		String updateSql = "update " + tableName + " set " + updateFields.toString() + " where " + primarykey + "=?" ;
		return updateSql;
	}
	
	/**
	 * 	
	 * 方法描述：完成对PreparedStatement所需参数的设置
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-3 下午04:38:34         
	 * @param pstmt 要设置参数的PreparedStatement
	 * @param params 参数列表
	 * @version   1.0
	 */
	public static void setQueryParamters(PreparedStatement pstmt, Object params[]) {
		if (pstmt != null && params != null) {
			for (int i = 0; i < params.length; i++) {
				try {
					pstmt.setObject(i + 1, params[i]);
				} catch (SQLException e) {
					log.error("对PreparedStatment设置参数错误！");
					log.error(DataUtil.getStackTraceAsString(e));
					log.error(DataUtil.getStackTraceAsString(e));;
				}
			}
		}
	}

	/**
	 * 
	 * 方法描述：完成对SQL语句中排序条件的组装，排序字段及排序方式（升序，降序）封装在LinkedHashMap中<br/>
	 * 此方法会返回SQL语句中order by 部分，如"order by table1.field1 asc table1.field2 desc"
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-3 下午04:39:44         
	 * @param orderby 要排序的字段和排序方式
	 * @return 返回组转完成后的排序条件
	 * @version   1.0
	 */
	public static String buildOrderBy(LinkedHashMap<String, String> orderby) {
		StringBuffer orderbySql = new StringBuffer(" ");
		if (orderby != null && orderby.size() > 0) {
			orderbySql.append(" order by ");
			for(String key : orderby.keySet()){
				orderbySql.append(" ").append(key).append(" ").append(orderby.get(key)).append(", ");
			}
			//删除构建的SQL语句中的最后一个","
			orderbySql.deleteCharAt(orderbySql.length() - 2);
		}
		return orderbySql.toString();
	}
	
	/**
	 * 
	 * 方法描述：根据指定的条件(条件封装在List中)构建SQL语句中的where语句,如"where field1=? and field2=?"
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-4 上午10:03:13         
	 * @param sqlCondition SQL语句中的条件
	 * @return	返回组装完成后的Where语句
	 * @version   1.0
	 */
	@SuppressWarnings("unchecked")
	public static String buildSQLWhere(List sqlCondition) {
		if (sqlCondition != null && sqlCondition.size() > 0) {
			StringBuffer sb = new StringBuffer(" where 1=1 ");
			for (int i = 0; i < sqlCondition.size(); i++) {
				sb.append(" and ").append(sqlCondition.get(i)).append("=? ");
			}
			return sb.toString();
		} else {
			return " ";
		}
	}
	
	/**
	 * 
	 * 方法描述：完成将数组以"[index0,index1]"的形式输出<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-3-9 上午08:36:09<br/>         
	 * @param explain 加在[index0,index1]前对输出内容加以说明的文字,如不需要置为""即可
	 * @param params 要输出的数组
	 * @return	explain + [index0,index1]
	 * @version   1.0
	 */
	public static String outPutArray(String explain, Object[] params) {
		String paramStr = "";
		Object[] obj = null;
		if ( null != params && params.length > 0 ) {
			obj = new Object[params.length];
			for (int i = 0; i < params.length; i++) {
				try {
					if (params[i] instanceof String) {
						if ( ((String)params[i]).length() > 40 ) {
							//如果数组某一元素的内容长度超过20个字符,则对该元素了取子串,避免过多的内容写入日志文件
							obj[i] = ((String)params[i]).substring(0,40) + "......";
						} else {
							obj[i] = params[i];
						}
					} else {
						obj[i] = params[i];
					}
				} catch (Exception e) {
					log.error("强制转换数据出错！");
				}
			}
			paramStr = explain + Arrays.toString( obj );
		}
		return paramStr;
	}
	
	/**
	 * 
	 * 方法描述：将数组的长度扩充为原来的2倍,并使得数组的第n个元素和第n+数组长度的元素的值相等<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-24 上午10:01:17<br/>         
	 * @param queryParams
	 * @return
	 * @version   1.0
	 */
	public static Object[] copyArray(Object[] queryParams) {
		Object[] newParamsArray = null;
		if ( null != queryParams && queryParams.length > 0) {
			//将数组的长度扩充为原来的2倍,并使得数组的第n个元素和第n+数组长度的元素的值相等,如此是为了分页查询时参数设置的需要
			newParamsArray = new Object[queryParams.length * 2];
			for (int i = 0; i < queryParams.length; i++) {
				newParamsArray[i] = queryParams[i];
				newParamsArray[queryParams.length + i] = queryParams[i];
			}
		}
		return newParamsArray;
	}
	
	/**
	 * 方法描述：根据指定的数字返回一个由四个字符组成的字符串<br/>
	 * 若数字小于四位，在数字前面补零,如1，返回0001,100返回0100
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午10:43:48<br/>         
	 * @param value 四位数以内的一个正整数
	 * @return	
	 * @version   1.0  
	 */
	public static String getValue(int value) {
		String reValue;
		if ( value < 10) {
			reValue = "000" + value;
		} else if ( value < 100) {
			reValue = "00" + value;
		} else if ( value < 1000 ) {
			reValue = "0" + value;
		} else if ( value < 10000 ) {
			reValue = "" + value;
		} else {
			reValue = "非法数字";
		}		
		return reValue;
	}

	/**
	 * 
	 * 方法描述：利用反射完成对结果集的封装,可执行多查询<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-7-16 上午08:09:54<br/>         
	 * @param sql
	 * @param params
	 * @param clazz
	 * @param paging
	 * @return
	 * @version   1.0
	 */
	@SuppressWarnings("unchecked")
	public List search(String sql,Class clazz, Object[] params) {
		List beans = new ArrayList();
		Connection conn = getConnection();		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			setQueryParamters(pstmt, params);
			rs = pstmt.executeQuery();
			log.info("执行SQL语句：" + sql + outPutArray("参数列表为：",params));
			while ( rs.next() ) {
				ResultSetMetaData meta = rs. getMetaData(); //获取结果集元数据   
				int columnCount = meta.getColumnCount(); //获得结果集每一行的列数  
				Object instance = null;
				try {
					//生成clazz类的Java Bean实例
					instance = clazz.newInstance();
				} catch (InstantiationException e) {
					log.error(DataUtil.getStackTraceAsString(e));;
				} catch (IllegalAccessException e) {
					log.error(DataUtil.getStackTraceAsString(e));;
				} 
				for ( int index = 1; index <= columnCount; index++ ) {   
					String currentFieldName = meta.getColumnName(index);//获得列名 
					//根据结果集的字段名获得Java Bean的对应属性
					Field currentField = null;
					try {
						currentField = clazz.getDeclaredField ( currentFieldName );
					} catch (SecurityException e) {
						log.error(DataUtil.getStackTraceAsString(e));;
					} catch (NoSuchFieldException e) {
						log.error(DataUtil.getStackTraceAsString(e));;
					}
					//设置属性为可访问   
					currentField.setAccessible( true );
					try {
						//根据结果集中的字段值设置属性值
						currentField.set( instance, rs.getObject( index ) ) ;
					} catch (IllegalArgumentException e) {
						log.error(DataUtil.getStackTraceAsString(e));;
					} catch (IllegalAccessException e) {
						log.error(DataUtil.getStackTraceAsString(e));;
					}
				}
				beans.add(instance);
			}
		} catch (SQLException e) {
			log.error(DataUtil.getStackTraceAsString(e));
			log.error("执行SQL语句出错：" + sql + outPutArray("参数列表为：",params));
			log.error(DataUtil.getStackTraceAsString(e));;
		} finally {
			close(rs, pstmt, conn);
		}
		return beans;
	}
	
	/**
	 * 方法描述：获得数据库的GUID支持SQL Server<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-12-4 上午10:51:30<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String getGUID() {
		String GUID = "";
		String sql = "select uuid() as guid";
		GUID = (String) getSingleValue(sql, null);
		return GUID;
	}
	/**
	 * 类描述：<br/>
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-16 下午04:29:32 
	 * 修改人：
	 * 修改时间：2012-7-16 下午04:29:32  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public int addBatchInfo(Object[][] params) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 
	 * 方法描述：去中间两头的空格。<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-6-26 下午03:57:52<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String trimStr(String str) {
		if (null == str) {
			return "";
		}
		Pattern p = Pattern.compile("\t|\r|\n");
		Matcher matcher = p.matcher(str);
		String dest = matcher.replaceAll(" ");
		return dest.trim();
	}
	
	
	/**
	 * 
	 * 方法描述：过滤非法字符<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2013-3-26 下午06:18:28<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	public  String strFilter(String str){
		if (null == str) {
			return "";
		}
		//str=str.replace("\t|\r|\n", "HNZS");
		System.out.println();
		Pattern p = Pattern.compile("[~!@#$%^&*()_+|/=.,:]");
		Matcher matcher = p.matcher(str);
		String dest = matcher.replaceAll("");
		return dest.trim();
	}
	
	/**
	 * 查询树形菜单子节点的字符串
	 * @param parId
	 * @return
	 */
	public String getChileList(String parId){
		String sql = "select getChildList(?)";
		return this.getSingleValue(sql, new Object[]{parId}).toString();
	}
}