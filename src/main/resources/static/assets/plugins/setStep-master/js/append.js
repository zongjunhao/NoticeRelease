function getlist(){
	for(var i=0;i<2;i++)
	{
		var all[i].incidentId = 1;
		var all[i].incidentdescribe = 2;
		var container = $("#all-bd");
		container.append(
			"<div class='panel' style='display: block;'>"+
			  "<table class='table'  style='border:1px solid #DDDDDD;border-radius: 25px;'>"+
			    "<tbody style='border-radius: 25%;'>"+
			      "<tr>"+
			        "<td style='width: 30%;'>工单编号：</td>"+
			        "<td style='width: 40%;'> <p style='color: #80C657;margin:0;'><a >Q</a>"+all[i].incidentId+"</p></td> "+
			        "<td style='width: 30%;text-align: right;'> <button class='btn btn-mini'  type='button' style='background-color:#7AC857;color:#fff;border-radius: 25px;' ><i class='iconfont' style='font-size: 12px;'>&#xe627;</i>&nbsp;&nbsp;王梁</td></tr>"+
			      "<tr style='display:none ;'>"+
			        "<td colspan='3'><div><a href=''>" +1+ "</a></div></td>"+
			      "</tr>"+
			      "<tr>"+
			        "<td style='width: 30%;'>服务描述：</td>"+
			        "<td style='width: 70%;'> <p style='color: #646464;margin:0;'>"all[i].incidentdescribe"</p></td>"+
			        "<td></td>"+
			      "</tr>"+
			    "</tbody>"+
			  "</table>"+
			  "<div style='border-bottom: 1px dashed #DDDDDD;'>"+
			    "<div class='container-fluid' style='padding-left: 0;padding-right: 0;'>"+
			        "<div id=stepBar'"+ i +"' class='ui-stepBar-wrap'>"+
			          "<div class='ui-stepBar'>"+
			            "<div class='ui-stepProcess'></div>"+
			          "</div>"+
			          "<div class='ui-stepInfo-wrap'>"+
			            "<table class='ui-stepLayout' border='0' cellpadding='0' cellspacing='0'>"+
			              "<tr>"+
			                "<td class='ui-stepInfo'>"+
			                  "<a class='ui-stepSequence'>1</a>"+
			                  "<p class='ui-stepName'>提报</p>"+
			                "</td>"+
			                "<td class='ui-stepInfo'>"+
			                  "<a class='ui-stepSequence'>2</a>"+
			                  "<p class='ui-stepName'>派工</p>"+
			                "</td>"+
			                "<td class='ui-stepInfo'>"+
			                  "<a class='ui-stepSequence'>3</a>"+
			                  "<p class='ui-stepName'>接受</p>"+
			                "</td>"+
			                "<td class='ui-stepInfo'>"+
			                  "<a class='ui-stepSequence'>4</a>"+
			                  "<p class='ui-stepName'>确认</p>"+
			                "</td>"+
			                "<td class='ui-stepInfo'>"+
			                  "<a class='ui-stepSequence'>5</a>"+
			                  "<p class='ui-stepName'>完成</p>"+
			                "</td>"+
			                "<td class='ui-stepInfo'>"+
			                  "<a class='ui-stepSequence'>6</a>"+
			                  "<p class='ui-stepName'>评价</p>"+
			                "</td>"+
			              "</tr>"+
			            "</table>"+
			          "</div>"+
			        "</div>"+
			    "</div>"+
			  "</div>"+
			  "<div class='container-fluid' style='text-align: right;padding: 5px 10px;'>"+
			    "<a style='color: #7AC857;font-size: 14px;'><i class='iconfont'>&#xe60a;</i>点赞</a>"+
			  "</div>"+
			"</div>"
		);
		// stepBar.init("stepBar"+i, {
  //         step: 5,
  //       });
        console.log("stepBar"+i);
	}
}