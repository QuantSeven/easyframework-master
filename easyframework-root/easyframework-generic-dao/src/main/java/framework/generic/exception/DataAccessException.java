package framework.generic.exception;

/**
 * 统一数据访问层异常出口
 * @author QuantSeven
 *
 */
public class DataAccessException extends RuntimeException {

	private static final long serialVersionUID = -4638003323752816534L;

	public DataAccessException(String msg) {
		super(msg);
	}

	public DataAccessException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
