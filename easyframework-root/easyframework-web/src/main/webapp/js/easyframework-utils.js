;
(function($, window, undefined) {

	// 公用函数
	function EasyframeworkUI() {
		// 缓存实例
		var instance;
		EasyframeworkUI = function EasyframeworkUI() {
			return instance;
		};
		// 后期处理原型属性
		EasyframeworkUI.prototype = this;
		// 重设构造函数指针
		instance = new EasyframeworkUI();
		instance.constructor = EasyframeworkUI;
		instance.statusCode = {};
		instance.statusCode.ok = 2000;// 请求成功
		instance.statusCode.error = 4000;// 请求失败
		instance.statusCode.timeout = 202;// 请求超时
		instance.statusCode.warn = 3000;// 请求超时
		instance.statusCode.prompt = 5000;
		return instance;
	}

	// 动态添加方法
	EasyframeworkUI.prototype.addMethod = function(name, func) {
		if (!EasyframeworkUI.prototype[name]) {
			EasyframeworkUI.prototype[name] = func;
		}
	};

	// 将json字符串转换成json对象
	EasyframeworkUI.prototype.converToJson = function(jsonString) {
		try {
			if ($.type(jsonString) == 'string')
				return eval('(' + jsonString + ')');
			else
				return data;
		} catch (e) {
			return {};
		}
	};
	// 将json对象转换成json字符串
	EasyframeworkUI.prototype.converToJsonStr = function(jsonObj) {
		try {
			return JSON.stringify(jsonObj);
		} catch (e) {
			return '';
		}
	};
	// 封装ajax动态加载数据
	EasyframeworkUI.prototype.ajaxData = function(url, settings, callback) {
		if (!url)
			return false;
		if ($.isFunction(settings)) {
			callback = settings;
			settings = {};
		}
		settings = settings || {};
		var load = $.ajax({
			type : settings.type || 'POST',
			dataType : settings.dataType || "json",
			url : url,
			data : settings.data || {},
			async : settings.async == undefined ? true : false,
			contentType : settings.contentType || "application/x-www-form-urlencoded",
			cache : settings.cache || false,
			success : function(response) {
				if (response !== null) {
					var result = EasyUtil.converToJson(response);
					if (result && result.message && result.message != "") {
						if (result.success) {
							EasyUtil.successMsg(result.message);
						} else {
							EasyUtil.errorMsg(result.message);
						}
					}
					callback && callback(response);
				} else {
					return {};
				}

			},
			error : function(xhr, ajaxOptions, thrownError) {
				easyframework.errorMsg(thrownError);
			}
		});
		return load;
	};
	// 成功消息提示
	EasyframeworkUI.prototype.successMsg = function(message) {
		$.messager.show({
			title : i18nCommon.txt.warmtip,
			msg : EasyUtil.converToJson(message).msg ? EasyUtil.converToJson(message).msg : message,
			timeout : 5000,
			showType : 'slide'
		});
	};
	// 错误消息提示
	EasyframeworkUI.prototype.errorMsg = function(message) {
		$.messager.show({
			title : i18nCommon.txt.warmtip,
			msg : EasyUtil.converToJson(message).msg ? EasyUtil.converToJson(message).msg : message,
			timeout : 5000,
			showType : 'slide'
		});
	};
	// 错误消息提示
	EasyframeworkUI.prototype.confirm = function(message, callback) {
		$.messager.confirm(i18nCommon.txt.warmtip, message, function(r) {
			if (r) {
				if ($.isFunction(callback))
					callback.call(this, true);
			} else {
				if ($.isFunction(callback))
					callback.call(this, false);
			}
		});

	};
	// 打开进度条
	EasyframeworkUI.prototype.openProgress = function() {
		$.messager.progress();
	};
	// 关闭进度条
	EasyframeworkUI.prototype.closeProgress = function() {
		$.messager.progress("close");
	};
	// 获取所有国际化文件的方法
	EasyframeworkUI.prototype.getI18NMessage = function(prefix) {
		var result = {};
		EasyUtil.ajaxData("common/getI18NMessage", {
			data : {
				"prefix" : prefix
			},
			async : false
		}, function(data) {

			$.map(data || {}, function(v, i) {
				list2json(v, i.replace(prefix + ".", ""), result);
			});
		});
		function list2json(val, key, slt) {
			var k = key.split(".");
			if (k.length == 1) {
				slt[k[0]] = val;
			} else {
				slt[k[0]] = slt[k[0]] || {};
				list2json(val, key.replace(k[0] + ".", ""), slt[k[0]]);
			}
		}
		return result;
	};

	EasyframeworkUI.prototype.serializeFieldValues = function(form) {
		var ret = {};
		// 文本类型
		form.find('input[type=text], input[type=password], textarea, select:not([multiple])').each(function() {
			var name = $(this).prop('name');
			ret[name] = $(this).val();
		});
		form.find('input[type=radio]').each(function() {
			var name = $(this).prop('name');
			if (!ret[name]) {
				ret[name] = null;
			}
			if ($(this).prop('checked')) {
				ret[name] = $(this).prop('value');
			}
		});
		// 数值类型
		form.find('input[type=number]').each(function() {
			var name = $(this).prop('name');
			ret[name] = Number($(this).val());
		});
		// 布尔类型
		form.find('input[type=checkbox]:not([value])').each(function() {
			var name = $(this).prop('name');
			ret[name] = $(this).prop('checked');
		});
		// 数组
		form.find('select[multiple]').each(function() {
			var name = $(this).prop('name');
			ret[name] = $(this).val() || [];
		});
		form.find('input[type=checkbox][value]').each(function() {
			var name = $(this).prop('name');
			if (!ret[name]) {
				ret[name] = [];
			}
			if ($(this).prop('checked')) {
				ret[name].push($(this).prop('value'));
			}
		});
		return ret;
	};

	window.EasyUtil = $.EasyUtil = new EasyframeworkUI();
	var i18nCommon = EasyUtil.getI18NMessage("common");
})(jQuery, window);
