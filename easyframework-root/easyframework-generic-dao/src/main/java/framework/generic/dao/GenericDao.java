package framework.generic.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import framework.generic.page.Pagination;

/**
 * 数据访问接口
 * 
 * @author QuantSeven
 * 
 * @param <T>
 *            实体类
 * @param <PK>
 *            主键的数据类型
 */
public interface GenericDao<T extends Serializable, PK extends Serializable> {

	/**
	 * 查询所有
	 * 
	 * @return List<T> 列表
	 */
	abstract List<T> read();

	/**
	 * 分页查询
	 * 
	 * @param pagination
	 *            分页工具类
	 * @return List<T> 列表
	 */
	abstract List<T> read(Pagination<T> pagination);

	/**
	 * 查询所有总数
	 * 
	 * @return 总数
	 */
	abstract long getTotalCount();

	/**
	 * 根据主键查询一条记录
	 * 
	 * @param parmaryKey
	 *            主键
	 * @return T 实体类
	 */
	abstract T get(PK parmaryKey);

	/**
	 * 保存数据
	 * 
	 * @param entity
	 *            实体类
	 * @return T 当前保存后的实体类
	 */
	abstract T save(T entity);

	/**
	 * 批量保存数据
	 * 
	 * @param entities
	 *            数据列表
	 */
	abstract void saveAll(final Collection<T> entities);

	/**
	 * 保存或者更新数据
	 * 
	 * @param entity
	 *            实体类
	 * @return 当前保存或者更新后的实体类
	 */
	abstract T saveOrUpdate(T entity);

	/**
	 * 批量保存或者更新
	 * 
	 * @param entities
	 *            实体列表
	 */
	abstract void saveOrUpdateAll(final Collection<T> entities);

	/**
	 * 更新实体类信息
	 * 
	 * @param entity
	 *            实体类
	 * @return T 更新后的实体类
	 */
	abstract T update(T entity);

	/**
	 * 批量更新数据
	 * 
	 * @param entities
	 *            实体列表
	 */
	abstract void updateAll(final Collection<T> entities);

	/**
	 * 根据主键删除
	 * 
	 * @param parmaryKey
	 *            主键
	 * @return int 受影响的行数
	 */
	abstract void deleteByPK(PK parmaryKey);

	/**
	 * 根据实体类删除一条数据
	 * 
	 * @param entity
	 *            实体类
	 * @return int 受影响的行数
	 */
	abstract void delete(T entity);

	/**
	 * 批量删除列表数据
	 * 
	 * @param entities
	 */
	abstract void deleteAll(final Collection<T> entities);

}
