/**
 * Created by changwang.song on 2017/12/26.
 */
function extend(obj1, obj2) {
    for (var attr in obj2) {
        obj1[attr] = obj2[attr];
    }
}

function SetStep(arg) {
    this.body = document.body;
    this.opt = {
        show: false,
        content: '#stepCont',
        pageCont: '#pageCont',
        imgWidth: 20,
        stepContainerMar: 20,
        nextBtn: '.nextBtn',
        prevBtn: '.prevBtn',
        steps: ['提报', '派工', '接受', '确认', '完成', '评价'],
        //pageClass:'',//分页的类或则id
        stepCounts: 6, //总共的步骤数
        curStep: 1, //当前显示第几页
        animating: false,
        showBtn: false, //是否生成上一步下一步操作按钮
        clickAble: false, //是否可以通过点击进度条的节点操作进度
        shownextBtn:true,
        showpreBtn:true,
        pageId: 0,
        onLoad: function() {
        }
    };
    this.init(arg);
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////

//初始化 生成页面的进度条和按钮
SetStep.prototype.init = function(arg) {
        var _that = this;
        extend(this.opt, arg);
        this.opt.stepCounts = this.opt.steps.length;
        this.content = $(this.opt.content);
        this.pageCont = this.content.find(this.opt.pageCont);
        var w_con = $(this.content).width();
        var w_li = (w_con - this.opt.stepContainerMar * 2) / this.opt.stepCounts / 2;
        var stepContainer = this.content.find('.ystep-container');
        this.stepContainer = stepContainer;
        var pageId=this.opt.pageId;
        var stepsHtml = $("<ul class='ystep-container-steps' ></ul>");
        var stepDisc = "<li class='ystep-step ystep-step-undone'></li>";
        var stepP = $("<div class='ystep-progress'>" +
            "<p class='ystep-progress-bar'><span class='ystep-progress-highlight' style='width:0%'></span></p>" +
            "</div>");
        var stepButtonHtml = $("<div class='step-button'><button type='button' class='btn btn-default prevBtn' id='prevBtn' class='prevBtn'>上一步</button>" +
            "<button type='button' class='btn btn-default nextBtn' id='nextBtn' class='nextBtn'>下一步</button></div>");
        stepP.css('width', w_li * 2 * (this.opt.stepCounts - 1));
        stepP.find('.ystep-progress-bar').css('width', w_li * 2 * (this.opt.stepCounts - 1))

        for (var i = 0; i < this.opt.stepCounts; i++) {
            if (i == 0) {
                var _s = $(stepDisc).text(this.opt.steps[i]).addClass('');
                
            } else {
                var _s = $(stepDisc).text(this.opt.steps[i]);
            }
            stepsHtml.append(_s);
        }
        stepsHtml.find('li').css('width', '100px').css('marginRight', w_li * 2 - 100)
        stepsHtml.find('li').css('line-height', '20px').css('marginRight', w_li * 2 - 100)
        //stepsHtml.find('li').css('margin', '0px auto')
        stepContainer.append(stepsHtml).append(stepP);

        stepContainer.css('left', (w_con - stepP.width() - this.opt.imgWidth - 10 - this.opt.stepContainerMar * 2) / 2)
        this.content.css('overflow', 'hidden')
        this.setProgress(this.stepContainer, this.opt.curStep, this.opt.stepCounts)
            //判断参数 是否显示按钮 并绑定点击事件
        /*if (this.opt.showBtn) {
            this.content.append(stepButtonHtml)
            this.prevBtn = this.content.find(this.opt.prevBtn)
            this.nextBtn = this.content.find(this.opt.nextBtn)
            this.prevBtn.on('click', function() {
                // if($(this).hasClass('handleAble')){
                if ($(_that).attr('disabled') || _that.opt.animating) {
                    return false;
                } else {
                    _that.opt.animating = true;
                    _that.opt.curStep--;
                    _that.setProgress(_that.stepContainer, _that.opt.curStep, _that.opt.stepCounts)
                }
            })
            this.nextBtn.on('click', function() {
                // if($(this).hasClass('handleAble')){
                if ($(_that).attr('disabled') || _that.opt.animating) {
                    return false;
                } else {
                    _that.opt.animating = true;
                    _that.opt.curStep++;
                    _that.setProgress(_that.stepContainer, _that.opt.curStep, _that.opt.stepCounts)
                }
            })
        }*/
//下一步按钮！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
        if(this.opt.shownextBtn){
            this.nextBtn = $("#nextBtn");
            //alert("hhh");
            //alert(this.nextBtn);
            
            if(ZUEL_proFlowInfo.data.length==3 )
            {//第四步 提交负责人
            	//alert("111")
            	this.nextBtn.bind('click',function() {
            		//alert("hhh");
            		//alert($("#appointPrinciple option:selected").val());
            		if($("#appointPrinciple option:selected").val()=="点击此处选择")
        			{
        				layer.msg("请为项目指定负责人！", {
  			 			  time: 1000
            			});	
        			}else{
        				
        				layer.confirm('确认进入下一步流程？', {
                      	  btn: ['确定','取消'] //按钮
                      	}, function(index, layero){
                      		$.ajax({
                          		type: "POST",
                          		url:"/profession/wkflow/nextFlow",
                          		datatype:'json',
                          		//async:false,
                          		data:{
                          			"id":$.cookie("ZUEL_proid"),
                          			"fid":ZUEL_fid,//$("#step_back option:selected").val(),
                          			"explain":$("#remarks").val(),
                          			"manager":$("#appointPrinciple option:selected").val()
                          				}, // 发送数据 
                          		error:function(jsonobj){ 
                          			layer.msg("请求失败！", {
                			 			  time: 1000
                          			});
                          		},
                          		beforeSend:function(){
                          	        index = layer.load(1, {
                          	            shade: [0.5,'#fff'] //0.1透明度的白色背景
                          	        });
                          	    },
                          	    complete: function () {
                          	        layer.close(index);
                          	    },
                          		success : function(jsonobj) {
                          			//alert(JSON.stringify(jsonobj));
                      				if (jsonobj.resultCode == "50000") {
                      					//alert(JSON.stringify(jsonobj.data[0].link.columns));
                      					
                      					layer.msg(jsonobj.resultDesc+"！2秒后跳转", {
                      			 			  time: 2500
                      					});
										  $.cookie('ctype','project_handling');
										window.setTimeout(function(){
											window.location.reload();
										},2600)
                  						
                      					//$.cookie("ZUEL_prostep",0)
                      					//window.location.reload();
                      					//putSomeProFlowDetail(ZUEL_proFlowInfo.data.length-2);
                      					/*getProFlowDetail();
                      					putSomeProFlowDetail(ZUEL_proFlowInfo.data.length-1);
                      					putProFlow();*/
                      				}else {
                      					layer.msg(jsonobj.resultDesc+"！", {
                    			 			  time: 1000
                    			 		});
                      			}
                      		}, 
                          	});
                      	}, function(index, layero){
                      		layer.close(index);
                      	  });
        			}            
                });
            }else if(ZUEL_proFlowInfo.data.length ==4)
        	{//提交项目组织和成交方式
            	//alert("222");
            	this.nextBtn.bind('click',function() {
            		if($("#organization option:selected").val()=="")
        			{
            			layer.msg("请选择组织方式！", {
    			 			  time: 2000
              			});
        			}else if($("#buy option:selected").val()==""){
        				layer.msg("请选择购买方式！", {
  			 			  time: 2000
            			});
        			}else if($("#projectType option:selected").val()==""){
        				layer.msg("请选择项目类型！", {
    			 			  time: 2000
              			});
          			}else{
        				layer.confirm('确认进入下一步流程？', {
                      	  btn: ['确定','取消'] //按钮
                      	}, function(index, layero){
                      		$.ajax({
                          		type: "POST",
                          		url:"/profession/wkflow/nextFlow",
                          		datatype:'json',
                          		//async:false,
                          		data:{
                          			"id":$.cookie("ZUEL_proid"),
                          			"fid":ZUEL_fid,//$("#step_back option:selected").val(),
                          			"explain":$("#remarks").val(),
                          			"org":$("#organization option:selected").val(),
                          			"buy":$("#buy option:selected").val(),
                          			"type":$("#projectType option:selected").val()
                          				}, // 发送数据 
                  				beforeSend:function(){
                          	        index = layer.load(1, {
                          	            shade: [0.5,'#fff'] //0.1透明度的白色背景
                          	        });
                          	    },
                          	    complete: function () {
                          	        layer.close(index);
                          	    },
                          		error:function(jsonobj){ 
                          			layer.msg("请求失败！", {
                			 			  time: 1000
                          			});
                          		},
                          		success : function(jsonobj) {
                          			//alert(JSON.stringify(jsonobj));
                      				if (jsonobj.resultCode == "50000") {
                      					//alert(JSON.stringify(jsonobj.data[0].link.columns));
                      					layer.msg(jsonobj.resultDesc+"！2秒后跳转", {
                      			 			  time: 2500
                      					});
                      					$.cookie('ctype','project_handling');
										  window.setTimeout(function(){
											window.location.reload();
										},2600)
                      					/*getProFlowDetail();
                      					putSomeProFlowDetail(ZUEL_proFlowInfo.data.length-1);
                      					putProFlow();*/
                      					//$.cookie("ZUEL_prostep",0)
                      					//window.location.reload();
                      					//putSomeProFlowDetail(ZUEL_proFlowInfo.data.length-1);
                      				}else {
                      					layer.msg(jsonobj.resultDesc+"！", {
                    			 			  time: 1000
                    			 		});
                      			}
                      		}, 
                          	});
                      	}, function(index, layero){
                      		layer.close(index);
                      	  });
        			}           
                });
        	}else{
        		//alert("333");	
        		this.nextBtn.bind('click',function() {   			
        				layer.confirm('确认进入下一步流程？', {
                      	  btn: ['确定','取消'] //按钮
                      	}, function(index, layero){
                      		$.ajax({
                          		type: "POST",
                          		url:"/profession/wkflow/nextFlow",
                          		datatype:'json',
                          		//async:false,
                          		data:{
                          			"id":$.cookie("ZUEL_proid"),
                          			"fid":ZUEL_fid,//$("#step_back option:selected").val(),
                          			"explain":$("#remarks").val()
                          				}, // 发送数据 
                          		error:function(jsonobj){ 
                          			layer.msg("请求失败！", {
                			 			  time: 1000
                          			});
                          		},
                          		beforeSend:function(){
                          	        index = layer.load(1, {
                          	            shade: [0.5,'#fff'] //0.1透明度的白色背景
                          	        });
                          	    },
                          	    complete: function () {
                          	        layer.close(index);
                          	    },
                          		success : function(jsonobj) {
                          			//alert(JSON.stringify(jsonobj));
                      				if (jsonobj.resultCode == "50000") {
                      					//alert(JSON.stringify(jsonobj.data[0].link.columns));
                      					layer.msg(jsonobj.resultDesc+"！2秒后跳转", {
                      			 			  time: 2500
                      					});
                      					$.cookie('ctype','project_handling');
										  window.setTimeout(function(){
											window.location.reload();
										},2600)
                      					/*if(ZUEL_proFlowInfo.data.length == 23){
                      						$.cookie('ctype','project_handling');
                      						window.location.reload();
                      					}else{
                      						getProFlowDetail();
                          					putSomeProFlowDetail((ZUEL_proFlowInfo.data.length-1)%5);
                          					putProFlow();
                      					}*/

                      					
                      					//window.location.reload();
                      					//putSomeProFlowDetail(ZUEL_proFlowInfo.data.length-1);
                      					//$.cookie("ZUEL_prostep",0)     	
                      				}else {
                      					layer.msg(jsonobj.resultDesc+"！", {
                    			 			  time: 1000
                    			 		});
                      			}
                      		}, 
                          	});
                      	}, function(index, layero){
                      		layer.close(index);
                      	  });
                               
                });
        	}	
        }
//退回按钮！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
        if(this.opt.showpreBtn){
//            this.preBtn = $("#preBtn");
//            this.preBtn.on('click',function() {
//                //alert("hhh")
//                  //输入信息层
//                layer.open({
//                    type: 1 ,//Page层类型,
//                    area: ['400px', '310px'],
//                    btn:["确定","取消"],
//                    title: '确认退回此流程？',
//                    skin: 'layui-layer-prompt',
//                    content: "<div class='form-auth-small'><div class='form-group'><select class='form-control' id='step_back' style='width:80%;margin:0px auto;margin-top:20px;overflow: scroll;max-height:100px;'><option value ='' disabled style='display:none;'>请选择退回到哪一步</option></select></div><div class='form-group'><textarea rows='6' class='form-control' id = 'reason_back'  style = 'width:80%;border-color:#d8d5d5;margin:0px auto;' placeholder='请输入退回理由'></textarea></div></div>",
//                    success: function(layero, index){
//                    	for(var i =1;i</*JSON.parse($.cookie("ZUEL_proFlowInfo"))*/ZUEL_proFlowInfo.data.length;i++){  		
//                    		var curProFlowInfo = (/*JSON.parse($.cookie("ZUEL_proFlowInfo"))*/ZUEL_proFlowInfo).data[i].link.columns;
//                    		$("select#step_back").append("<option value='"+curProFlowInfo.l_taskname+"'>"+curProFlowInfo.l_taskname+"</option>");
//
//                    	}                 	
//                    	//alert("success")
//                      },
//                    yes: function(index, layero){
//                      //按钮【确认】的回调
//                    	alert($("select#step_back option:selected").val());
//                    	/*$.ajax({
//                    		type: "POST",
//                    		url:"/profession/wkflow/rollBack",
//                    		datatype:'json',
//                    		async:false,
//                    		data:{
//                    			"id":$.cookie("ZUEL_proid"),
//                    			 "linkname":$("select#step_back option:selected").val(),
//                    			 "reason":$("#reason_back").val()
//                    				}, // 发送数据 
//                    		error:function(jsonobj){ 
//                    			layer.msg("请求失败！", {
//          			 			  time: 1000
//                    			});
//                    		},
//                    		beforeSend:function(){
//                      	        index = layer.load(1, {
//                      	            shade: [0.5,'#fff'] //0.1透明度的白色背景
//                      	        });
//                      	    },
//                      	    complete: function () {
//                      	        layer.close(index);
//                      	    },
//                    		success : function(jsonobj) {
//                    			//alert(JSON.stringify(jsonobj));
//                				if (jsonobj.resultCode == "50000") {
//                					//alert(JSON.stringify(jsonobj.data[0].link.columns));
//                					
//                					//$.cookie("ZUEL_prostep",0)
//                					layer.msg(jsonobj.resultDesc+"！", {
//              			 			  time: 1000
//                					});	
//                					window.location.reload();
//                				}else {
//                					layer.msg(jsonobj.resultDesc+"！", {
//              			 			  time: 1000
//                					});
//                				}
//                			} 
//                    	});*/
//                    }
//                 });
//             });
                
        }
        //判断时候可点击进度条 并绑定点击事件
        if (this.opt.clickAble) {
        	var i;
        	/*for(i = 0;i<stepsHtml.find('li').length;i++)
    		{*/
    		$(".ystep-container-steps li").each(function(index,item)
    		{
    		    $(item).attr('id', index);
    		});
    		//alert(this.opt.pageId*this.opt.stepCounts+","+ZUEL_proFlowInfo.data.length);
    		for(i=0;i<$(".ystep-container-steps li").length;i++)
    		{
    			if(this.opt.pageId*5/*this.opt.stepCounts*/+i</*JSON.parse($.cookie("ZUEL_proFlowInfo"))*/ZUEL_proFlowInfo.data.length)
    			{
    				$(".ystep-container-steps li").eq(i).on('click',function()
						{
	    					index = layer.load(1, {
	              	            shade: [0.5,'#fff'], //0.1透明度的白色背景
	              	            time:500
	              	        });
							putSomeProFlowDetail(this.id); 
							
						});
    			}else if(this.opt.pageId*5/*this.opt.stepCounts*/+i==/*JSON.parse($.cookie("ZUEL_proFlowInfo"))*/ZUEL_proFlowInfo.data.length)
				{
    				if($.cookie("ZUEL_oprtid")==1){
					$(".ystep-container-steps li").eq(i).on('click',function()
						{
							submiteFlowDetail(this.id);
							
						});
    				}else{	
    				}
				}else{
					
				}
        	}
    		
        }
		$(window).resize(function() 
		{
		    var w_con = $(_that.content).width();
		    var w_li = w_con / _that.opt.stepCounts / 2;
		    stepP.css('width', w_li * 2 * (_that.opt.stepCounts - 1));
		    stepP.find('.ystep-progress-bar').css('width', w_li * 2 * (_that.opt.stepCounts - 1))
		    stepsHtml.find('li').css('width', '40px').css('marginRight', w_li * 2 - 40)
		    stepContainer.css('left', (w_con - stepP.width() - _that.opt.imgWidth - 10 - _that.opt.stepContainerMar * 2) / 2)
		})
}	
        		//alert(this.opt.pageId*this.opt.stepCounts+i);
        		/*if(this.opt.pageId*this.opt.stepCounts+i<JSON.parse($.cookie("ZUEL_proFlowInfo")).data.length)
        		{
        			var curProFlowInfo = (JSON.parse($.cookie("ZUEL_proFlowInfo"))).data[this.opt.pageId*this.opt.stepCounts+i].link.columns;
        			stepsHtml.find('li').eq(i).on('click',function(){
        				alert(s);
        				putSomeProFlowDetail(this.opt.pageId*this.opt.stepCounts+i); 
        			});
        		}*/
        		/*stepsHtml.find('li').eq(i).on('click', function() {
                    //_that.opt.curStep = $(this).index() + 1;
    -----------------------------------------------------------------------------------------待编辑
                    _that.setProgress(_that.stepContainer, _that.opt.curStep, _that.opt.stepCounts)
                    //var curProFlowInfo = (JSON.parse($.cookie("ZUEL_proFlowInfo")))[];
        			
                })*/
    		//}           



$("body").on("click","#preBtn",function(){
	layer.open({
        type: 1 ,//Page层类型,
        area: ['400px', '310px'],
        btn:["确定","取消"],
        title: '确认退回此流程？',
        skin: 'layui-layer-prompt',
        content: "<div class='form-auth-small'><div class='form-group'><select class='selectpicker form-control' id='step_back' style='width:80%;margin:0px auto;' data-live-search='true' style='color: #999999;' data-size='8'><option value ='' disabled style='display:none;'>请选择退回到哪一步</option></select></div><div class='form-group'><textarea rows='6' class='form-control' id = 'reason_back'  style = 'width:80%;border-color:#d8d5d5;margin:0px auto;' placeholder='请输入退回理由'></textarea></div></div>",
        success: function(layero, index){
        	for(var i =1;i</*JSON.parse($.cookie("ZUEL_proFlowInfo"))*/ZUEL_proFlowInfo.data.length;i++){  		
        		var curProFlowInfo = (/*JSON.parse($.cookie("ZUEL_proFlowInfo"))*/ZUEL_proFlowInfo).data[i].link.columns;
        		
        		$("#step_back").append("<option value='"+curProFlowInfo.l_taskname+"'>"+curProFlowInfo.l_taskname+"</option>");
        	} 
        	$('#step_back').selectpicker('refresh');
        	$('.dropdown.bootstrap-select.form-control').attr('style','width:80%;margin-left:10%;margin-top:4%;');
        	//alert("success")
          },
        yes: function(index, layero){
          //按钮【确认】的回调
        	//alert($("select#step_back option:selected").val());
        	$.ajax({
        		type: "POST",
        		url:"/profession/wkflow/rollBack",
        		datatype:'json',
        		async:false,
        		data:{
        			"id":$.cookie("ZUEL_proid"),
        			 "linkname":$("select#step_back option:selected").val(),
        			 "reason":$("#reason_back").val()
        				}, // 发送数据 
        		error:function(jsonobj){ 
        			layer.msg("请求失败！", {
			 			  time: 1000
        			});
        		},
        		beforeSend:function(){
          	        index = layer.load(1, {
          	            shade: [0.5,'#fff'] //0.1透明度的白色背景
          	        });
          	    },
          	    complete: function () {
          	        layer.close(index);
          	    },
        		success : function(jsonobj) {
        			//alert(JSON.stringify(jsonobj));
    				if (jsonobj.resultCode == "50000") {
    					//alert(JSON.stringify(jsonobj.data[0].link.columns));
    					
    					//$.cookie("ZUEL_prostep",0)
    					layer.msg(jsonobj.resultDesc+"！", {
  			 			  time: 1000
    					});	
    					window.location.reload();
    				}else {
    					layer.msg(jsonobj.resultDesc+"！", {
  			 			  time: 1000
    					});
    				}
    			} 
        	});
        }
     });
});        
        
 
    //设置进度条
SetStep.prototype.setProgress = function(n, curIndex, stepsLen) {
        var _that = this;
        //获取当前容器下所有的步骤
        var $steps = $(n).find("li");
        var $progress = $(n).find(".ystep-progress-highlight");
        //判断当前步骤是否在范围内
        if (1 <= curIndex && curIndex <= $steps.length) {
            //更新进度
            var scale = "%";
            scale = Math.round((curIndex - 1) * 100 / ($steps.length - 1)) + scale;
            $progress.animate({
                width: scale
            }, {
                speed: 1000,
                done: function() {
                    //移动节点
                    $steps.each(function(j, m) {
                        var _$m = $(m);
                        var _j = j + 1;
                        if (_j < curIndex) {
                            _$m.attr("class", "ystep-step-done");
                        } else if (_j === curIndex) {
                            _$m.attr("class", "ystep-step-active");
                        } else if (_j > curIndex) {
                            _$m.attr("class", "ystep-step-undone");
                        }
                    })
                    if (_that.opt.showBtn) {
                        if (curIndex == 1) {
                            _that.prevBtn.attr('disabled', 'true');
                            _that.nextBtn.removeAttr('disabled');
                        } else if (curIndex == stepsLen) {
                            _that.prevBtn.removeAttr('disabled');
                            _that.nextBtn.attr('disabled', 'true');
                        } else if (1 < curIndex < stepsLen) {
                            _that.prevBtn.removeAttr('disabled');
                            _that.nextBtn.removeAttr('disabled');
                        }
                    }
                    _that.checkPage(_that.pageCont, _that.opt.curStep, _that.opt.stepCounts)
                    _that.opt.animating = false;
                }
            });
        } else {
            return false;
        }
    }
    //改变 分页显示
SetStep.prototype.checkPage = function(pageCont, curStep, steps) {
    for (var i = 1; i <= steps; i++) {
        if (i === curStep) {
            pageCont.find('#page' + i).css("display", "block");
        } else {
            pageCont.find('#page' + i).css("display", "none");
        }
    }
}

