package framework.generic.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

import com.easyframework.common.orm.PropertyFilter;
import com.easyframework.common.orm.PropertyFilter.MatchType;

import framework.generic.page.Pagination;

public interface GenericHibernateDao<T extends Serializable, PK extends Serializable> extends GenericDao<T, PK> {

	// -------------------------------------------------------------------------
	// 以下使用HQL语言查询
	// -------------------------------------------------------------------------
	/**
	 * 根据HQL语句查询列表
	 * 
	 * @param hql
	 *            HQL语句
	 * @return List<T> 列表
	 */
	abstract List<T> find(String hql);

	/**
	 * 根据HQL语句和参数值查询列表
	 * 
	 * @param hql
	 *            HQL语句
	 * @param paramList
	 *            数量可变的参数,按顺序绑定.
	 * @return List<T> 列表
	 */
	abstract List<T> find(final String hql, final Object... paramList);

	/**
	 * 根据多个参数名称和值查询
	 * 
	 * @param hql
	 *            HQL语句
	 * @param paramMap
	 *            命名参数,按名称绑定.
	 * @return List<T> 列表
	 */
	abstract List<T> find(final String hql, final Map<String, ?> paramMap);

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param hql
	 *            HQL语句
	 * @param paramList
	 *            数量可变的参数,按顺序绑定.
	 */
	public T findUnique(final String hql, final Object... paramList);

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param hql
	 *            HQL语句
	 * @param paramMap
	 *            命名参数,按名称绑定.
	 */
	public T findUnique(final String hql, final Map<String, ?> paramMap);

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @param paramList
	 *            数量可变的参数,按顺序绑定.
	 * @return 更新记录数.
	 */
	public int batchExecute(final String hql, final Object... paramList);

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @param paramMap
	 *            命名参数,按名称绑定.
	 * @return 更新记录数.
	 */
	public int batchExecute(final String hql, final Map<String, ?> paramMap);

	/**
	 * 根据hql分页加载数据，定页大小和起始位置
	 * 
	 * @param hql
	 *            HQL语句
	 * @param Pagination
	 *            分页实体类
	 * @param paramList
	 *            参数列表
	 * @return Pagination 分页实体类
	 */
	abstract Pagination<T> findPage(final String hql, final Pagination<T> pagination, Object... paramList);

	/**
	 * 根据hql分页加载数据，定页大小和起始位置
	 * 
	 * @param hql
	 *            HQL语句
	 * @param pageNo
	 *            当前页
	 * @param pageSize
	 *            显示数量
	 * @param paramNames
	 *            参数名称数组
	 * @param values
	 *            参数值数组
	 * @return
	 */
	abstract Pagination<T> findPage(final String hql, Pagination<T> pagination, final Map<String, ?> paramMap);

	/**
	 * 根据条件查询总数
	 * 
	 * @param hql
	 *            HQL语句
	 * @param paramList
	 *            参数
	 * @return 条件查询后总数
	 */
	abstract long count(final String hql, final Object... paramList);

	/**
	 * 根据条件查询总数
	 * 
	 * @param hql
	 *            HQL语句
	 * @param paramNames
	 *            参数名称数组
	 * @param values
	 *            参数值数组
	 * @return 条件查询后总数
	 */
	abstract long count(final String hql, final Map<String, ?> paramMap);

	// -------------------------------------------------------------------------
	// 以下使用Criterion语言查询
	// -------------------------------------------------------------------------
	/**
	 * 根据Criteria 查询所有
	 * 
	 * @param criterions
	 *            条件参数
	 * @return List<T> 列表
	 */
	abstract List<T> findByCriteria(final Criterion... criterions);

	/**
	 * 根据Criterion分页加载数据，定页大小和起始位置
	 * 
	 * @param hql
	 *            HQL语句
	 * @param pagination
	 *            分页工具类
	 * @return Pagination 分页工具类
	 */
	abstract Pagination<T> findPage(final Pagination<T> pagination, final Criterion... criterions);

	/**
	 * 根据Criteria统计总数
	 * 
	 * @param criteria
	 *            条件参数
	 * @return long 总数
	 */
	abstract long count(final Criteria criteria);

	// -- 属性过滤条件(PropertyFilter)查询函数 --//
	// --源自springside
	/**
	 * 按属性过滤条件列表查找对象列表
	 * 
	 * @param filters
	 *            过滤条件列表
	 * @return 按属性过滤条件列表查找对象列表
	 */
	abstract List<T> find(List<PropertyFilter> filters);

	/**
	 * 按属性过滤条件列表查找对象列表.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            值
	 * @param matchType
	 *            匹配方式,目前支持的取值见PropertyFilter的MatcheType enum.
	 * @return
	 */
	abstract List<T> findBy(final String propertyName, final Object value, final MatchType matchType);

	/**
	 * 按属性过滤条件列表分页查找对象.
	 * 
	 * @param pagination
	 *            分页工具类
	 * @param filters
	 *            过滤条件列表
	 * @return Pagination<T>
	 */
	abstract Pagination<T> findPage(final Pagination<T> pagination, final List<PropertyFilter> filters);
}
