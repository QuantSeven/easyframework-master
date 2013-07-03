package framework.generic.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.Assert;

import com.easyframework.common.orm.PropertyFilter;
import com.easyframework.common.orm.PropertyFilter.MatchType;
import com.easyframework.common.utils.reflection.ReflectionUtils;

import framework.generic.page.Pagination;

public abstract class GenericHibernateDaoImpl<T extends Serializable, PK extends Serializable> implements GenericHibernateDao<T, PK> {

	private final Class<?> entityClass;

	private SessionFactory sessionFactory;
	private final String HQL_LIST_ALL;
	private final String HQL_COUNT_ALL;

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 获取当前的session
	 */
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public GenericHibernateDaoImpl() {
		this.entityClass = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		HQL_LIST_ALL = "FROM " + this.entityClass.getSimpleName();
		HQL_COUNT_ALL = "SELECT COUNT(*) FROM " + this.entityClass.getSimpleName();
	}

	public GenericHibernateDaoImpl(SessionFactory sessionFactory, Class<T> entityClass) {
		this.setSessionFactory(sessionFactory);
		this.entityClass = entityClass;
		HQL_LIST_ALL = "FROM " + this.entityClass.getSimpleName();
		HQL_COUNT_ALL = "SELECT COUNT(*) FROM " + this.entityClass.getSimpleName();
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象
	 * 
	 * @param queryString
	 *            HQL语句
	 * @return Query
	 */
	private Query createQuery(final String queryString) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getCurrentSession().createQuery(queryString);
		return query;
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象
	 * 
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            数量可变的参数,按顺序绑定
	 * @return Query
	 */
	private Query createQuery(final String hql, final Object... values) {
		Assert.hasText(hql, "hql不能为空");
		Query query = getCurrentSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象
	 * 
	 * @param values
	 * 
	 */
	/**
	 * 根据查询HQL与参数列表创建Query对象
	 * 
	 * @param hql
	 *            HQL语句
	 * @param paramMap
	 *            命名参数,按名称绑定.
	 * @return Query
	 */
	private Query createQuery(final String hql, final Map<String, ?> paramMap) {
		Assert.hasText(hql, "hql不能为空");
		Query query = getCurrentSession().createQuery(hql);
		if (paramMap != null) {
			query.setProperties(paramMap);
		}
		return query;
	}

	/**
	 * 设置分页参数到Query对象,辅助函数.
	 */
	protected Query setPageParameterToQuery(final Query q, final Pagination<T> pagination) {
		Assert.isTrue(pagination.getPageSize() > 0, "Page Size must larger than zero");
		// hibernate的firstResult的序号从0开始
		q.setFirstResult(pagination.getFirst() - 1);
		q.setMaxResults(pagination.getPageSize());
		return q;
	}

	/**
	 * 根据Criterion条件创建Criteria.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	private Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	@Override
	public List<T> read() {
		return (List<T>) createQuery(HQL_LIST_ALL).list();
	}

	@Override
	public List<T> read(Pagination<T> pagination) {
		Query query = createQuery(HQL_LIST_ALL);
		query.setFirstResult(pagination.getFirst());
		query.setMaxResults(pagination.getPageSize());
		return (List<T>) query.list();
	}

	@Override
	public long getTotalCount() {
		return count(HQL_COUNT_ALL);
	}

	@Override
	public T get(PK parmaryKey) {
		Assert.notNull(parmaryKey, "parmaryKey不能为空");
		return (T) getCurrentSession().get(entityClass, parmaryKey);
	}

	@Override
	public T save(T entity) {
		Assert.notNull(entity, "entity不能为空");
		Serializable pk = getCurrentSession().save(entity);
		return (T) getCurrentSession().get(entityClass, pk);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.generic.dao.GenericDao#saveAll(java.util.Collection)
	 */
	@Override
	public void saveAll(Collection<T> entities) {
		if (entities != null && !entities.isEmpty()) {
			for (T entity : entities) {
				getCurrentSession().save(entity);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.generic.dao.GenericDao#saveOrUpdate(java.io.Serializable)
	 */
	@Override
	public T saveOrUpdate(T entity) {
		Assert.notNull(entity, "entity不能为空");
		if (entity != null) {
			getCurrentSession().saveOrUpdate(entity);
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.generic.dao.GenericDao#saveOrUpdateAll(java.util.Collection)
	 */
	@Override
	public void saveOrUpdateAll(Collection<T> entities) {
		if (entities != null && !entities.isEmpty()) {
			for (T entity : entities) {
				getCurrentSession().saveOrUpdate(entity);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.generic.dao.GenericDao#update(java.io.Serializable)
	 */
	@Override
	public T update(T entity) {
		Assert.notNull(entity, "entity不能为空");
		if (entity != null) {
			getCurrentSession().update(entity);
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.generic.dao.GenericDao#updateAll(java.util.Collection)
	 */
	@Override
	public void updateAll(Collection<T> entities) {
		if (entities != null && !entities.isEmpty()) {
			for (T entity : entities) {
				getCurrentSession().update(entity);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.generic.dao.GenericDao#deleteByPK(java.io.Serializable)
	 */
	@Override
	public void deleteByPK(PK parmaryKey) {
		Assert.notNull(parmaryKey, "parmaryKey不能为空");
		T entity = null;
		if (parmaryKey != null) {
			entity = (T) getCurrentSession().get(entityClass, parmaryKey);
		}
		if (entity != null) {
			getCurrentSession().delete(entity);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.generic.dao.GenericDao#delete(java.io.Serializable)
	 */
	@Override
	public void delete(T entity) {
		Assert.notNull(entity, "entity不能为空");
		if (entity != null) {
			getCurrentSession().delete(entity);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.generic.dao.GenericDao#deleteAll(java.util.Collection)
	 */
	@Override
	public void deleteAll(Collection<T> entities) {
		if (entities != null && !entities.isEmpty()) {
			for (T entity : entities) {
				getCurrentSession().delete(entity);
			}
		}

	}

	@Override
	public List<T> find(String hql) {
		return createQuery(hql).list();
	}

	@Override
	public List<T> find(String hql, Object... paramList) {
		return createQuery(hql, paramList).list();
	}

	@Override
	public List<T> find(String hql, Map<String, ?> paramMap) {
		return createQuery(hql, paramMap).list();
	}

	@Override
	public T findUnique(String hql, Object... paramList) {
		return (T) createQuery(hql, paramList).uniqueResult();
	}

	@Override
	public T findUnique(String hql, Map<String, ?> paramMap) {
		return (T) createQuery(hql, paramMap).uniqueResult();
	}

	@Override
	public int batchExecute(String hql, Object... paramList) {
		return createQuery(hql, paramList).executeUpdate();
	}

	@Override
	public int batchExecute(String hql, Map<String, ?> paramMap) {
		return createQuery(hql, paramMap).executeUpdate();
	}

	@Override
	public Pagination<T> findPage(String hql, Pagination<T> pagination, Object... paramList) {
		Assert.notNull(pagination, "pagination不能为空");
		Query query = createQuery(hql, paramList);
		long totalCount = count(hql, paramList);
		pagination.setTotalCount(totalCount);
		setPageParameterToQuery(query, pagination);
		List<T> result = (List<T>) query.list();
		pagination.setResult(result);
		return pagination;
	}

	@Override
	public Pagination<T> findPage(String hql, Pagination<T> pagination, Map<String, ?> paramMap) {
		Assert.notNull(pagination, "pagination不能为空");
		Query query = createQuery(hql, paramMap);
		long totalCount = count(hql, paramMap);
		pagination.setTotalCount(totalCount);
		setPageParameterToQuery(query, pagination);
		List<T> result = (List<T>) query.list();
		pagination.setResult(result);
		return pagination;
	}

	private String prepareCountHql(String orgHql) {
		String fromHql = orgHql;
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "FROM " + StringUtils.substringAfter(fromHql, "FROM");
		fromHql = StringUtils.substringBefore(fromHql, "ORDER BY");
		String countHql = "SELECT COUNT(*) " + fromHql;
		return countHql;
	}

	@Override
	public long count(String hql, Object... paramList) {
		String countHql = prepareCountHql(hql);

		try {
			Long count = (Long) findUnique(countHql, paramList);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	@Override
	public long count(String hql, Map<String, ?> paramMap) {
		String countHql = prepareCountHql(hql);

		try {
			Long count = (Long) findUnique(countHql, paramMap);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	@Override
	public List<T> findByCriteria(Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	@Override
	public Pagination<T> findPage(Pagination<T> pagination, Criterion... criterions) {
		Assert.notNull(pagination, "pagination不能为空");
		Criteria c = createCriteria(criterions);
		long totalCount = count(c);
		pagination.setTotalCount(totalCount);
		setPageParameterToCriteria(c, pagination);
		List<T> result = (List<T>) c.list();
		pagination.setResult(result);
		return pagination;
	}

	/**
	 * 设置分页参数到Criteria对象,辅助函数.
	 */
	protected Criteria setPageParameterToCriteria(final Criteria c, final Pagination<T> pagination) {

		Assert.isTrue(pagination.getPageSize() > 0, "Page Size must larger than zero");

		// hibernate的firstResult的序号从0开始
		c.setFirstResult(pagination.getFirst() - 1);
		c.setMaxResults(pagination.getPageSize());

		if (pagination.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(pagination.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(pagination.getOrder(), ',');

			Assert.isTrue(orderByArray.length == orderArray.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");

			for (int i = 0; i < orderByArray.length; i++) {
				if (Pagination.ASC.equals(orderArray[i])) {
					c.addOrder(Order.asc(orderByArray[i]));
				} else {
					c.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}
		return c;
	}

	@Override
	public long count(Criteria criteria) {
		CriteriaImpl impl = (CriteriaImpl) criteria;
		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) ReflectionUtils.getFieldValue(impl, "orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
		}

		// 执行Count查询
		Long totalCountObject = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		long totalCount = (totalCountObject != null) ? totalCountObject : 0;

		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
		criteria.setProjection(projection);

		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			criteria.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
		}
		return totalCount;
	}

	// -- 属性过滤条件(PropertyFilter)查询函数 --//

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.generic.dao.GenericHibernateDao#findBy(java.lang.String, java.lang.Object, com.easyframework.common.orm.PropertyFilter.MatchType)
	 */
	@Override
	public List<T> findBy(final String propertyName, final Object value, final MatchType matchType) {
		Criterion criterion = buildCriterion(propertyName, value, matchType);
		return findByCriteria(criterion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.generic.dao.GenericHibernateDao#find(java.util.List)
	 */
	@Override
	public List<T> find(List<PropertyFilter> filters) {
		Criterion[] criterions = buildCriterionByPropertyFilter(filters);
		return findByCriteria(criterions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.generic.dao.GenericHibernateDao#findPage(framework.generic.page.Pagination, java.util.List)
	 */
	@Override
	public Pagination<T> findPage(final Pagination<T> pagination, final List<PropertyFilter> filters) {
		Criterion[] criterions = buildCriterionByPropertyFilter(filters);
		return findPage(pagination, criterions);
	}

	/**
	 * 按属性条件参数创建Criterion,辅助函数.
	 */
	protected Criterion buildCriterion(final String propertyName, final Object propertyValue, final MatchType matchType) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = null;
		// 根据MatchType构造criterion
		switch (matchType) {
		case EQ:
			criterion = Restrictions.eq(propertyName, propertyValue);
			break;
		case LIKE:
			criterion = Restrictions.like(propertyName, (String) propertyValue, MatchMode.ANYWHERE);
			break;

		case LE:
			criterion = Restrictions.le(propertyName, propertyValue);
			break;
		case LT:
			criterion = Restrictions.lt(propertyName, propertyValue);
			break;
		case GE:
			criterion = Restrictions.ge(propertyName, propertyValue);
			break;
		case GT:
			criterion = Restrictions.gt(propertyName, propertyValue);
		}
		return criterion;
	}

	/**
	 * 按属性条件列表创建Criterion数组,辅助函数.
	 */
	protected Criterion[] buildCriterionByPropertyFilter(final List<PropertyFilter> filters) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		for (PropertyFilter filter : filters) {
			if (!filter.hasMultiProperties()) { // 只有一个属性需要比较的情况.
				Criterion criterion = buildCriterion(filter.getPropertyName(), filter.getMatchValue(), filter.getMatchType());
				criterionList.add(criterion);
			} else {// 包含多个属性需要比较的情况,进行or处理.
				Disjunction disjunction = Restrictions.disjunction();
				for (String param : filter.getPropertyNames()) {
					Criterion criterion = buildCriterion(param, filter.getMatchValue(), filter.getMatchType());
					disjunction.add(criterion);
				}
				criterionList.add(disjunction);
			}
		}
		return criterionList.toArray(new Criterion[criterionList.size()]);
	}
}
