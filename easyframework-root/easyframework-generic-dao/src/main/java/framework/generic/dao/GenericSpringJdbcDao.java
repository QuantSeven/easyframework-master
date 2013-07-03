package framework.generic.dao;

import java.io.Serializable;

/**
 * 基于SpringJDBC的数据访问接口
 * 
 * @author QuantSeven
 * 
 * @param <T>
 *            实体类
 * @param <PK>
 *            主键的数据类型
 */
public interface GenericSpringJdbcDao<T extends Serializable, PK extends Serializable> extends GenericDao<T, PK> {

}
