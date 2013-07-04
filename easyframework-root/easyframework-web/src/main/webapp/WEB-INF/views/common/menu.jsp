<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${basePath }/css/menu.css" />
<script type="text/javascript">
$(function() {
	$.getJSON("menu/leftMenu", function(data) {
		$("#menuContainer" ).html($("#menuTemplate").render(data));
		$('#menuContainer').accordion({animate:false,fit:true,border:false }); 
		$(".link").bind("click",function(){
			$.messager.progress();
			$(".link").removeClass("selected");
			$(this).addClass("selected");
			var menuItem = $(this).find("a[data-ref='link']");
			addTab(menuItem.attr("ref"),menuItem.attr("title"),menuItem.attr("iconCls"));
		}); 
	});
});

//tab添加一个选项卡
function addTab(url,title,iconCls) {
	var t = $('#index_tabs');
	var opts = {
		title : title,
		closable : true,
		iconCls : iconCls,
		href : url,
		border : false
	};
	if (t.tabs('exists', opts.title)) {
		t.tabs('select', opts.title);
		$.messager.progress('close');
	} else {
		t.tabs('add', opts);
		$.messager.progress('close');
	}
}
</script>


<script id="menuTemplate" type="text/x-jsrender">
{{for #data}}
	<div title="{{:name}}" border="false" iconCls="{{:iconCls}}">
		{{if children}}
			<ul class="bigicon menuItem">
                 {{for children}}
					<li>
						<div class="link">
							<a href="#" data-ref="link" ref="{{:url}}" iconCls="{{:iconCls}}" title="{{:name}}">
								<span class="img">
                          		 	<img src="{{:icon}}">
					    		</span>
								<span class="nav">{{:name}}</span>
							</a>
						</div>
					</li>
			      {{/for}}
			</ul>
       {{/if}}
	</div>
{{/for}}
</script>

<div id="menuContainer"	>
</div>