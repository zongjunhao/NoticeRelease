let base_url = "http://localhost:8080"
let file_ids = []
//更新用户名
$('#welcome-text').html('欢迎您，' + $.cookie('userName'));

showUnit();
showLabels();
showReception();
$(document).ready(function () {
    $('.selectpicker').selectpicker({});
});

function showUnit() {
    $.ajax({
        type: "POST",
        url: "/notice/getUnits",
        data: {
            userId: $.cookie('userId')
        },
        success: function (json) {
            if (json.code === "2006") {
                for (var i = 0; i < json.data.length; i++) {
                    $("#unit").append("<option style='font-size: 14px;' value='" + json.data[i].unitId + "'>" + json.data[i].unitName + "</option>");
                    $('.selectpicker').selectpicker('refresh');
                    $('.selectpicker').selectpicker('show');
                }
            } else {
                layer.msg(json.desc + "!", {
                    time: 1000
                });
            }
        },
        error: function (jqXHR) {
            layer.msg('请求失败！', {
                time: 1000
            });
        }
    })
}

function showLabels() {
    $.ajax({
        type: "POST",
        url: "/notice/getLabels",
        success: function (json) {
            let label = "";
            if (json.code === "2006") {
                for (let i = 0; i < json.data.length; i++) {
                    label += "<label><input type='checkbox' name='check' id='" + json.data[i].labelId + "'/>" + json.data[i].labelName + "</label>&nbsp;&nbsp;"
                }
                $("#label").append(label)
            } else {
                layer.msg(json.desc + "!", {
                    time: 1000
                });
            }
        },
        error: function (jqXHR) {
            layer.msg('请求失败！', {
                time: 1000
            });
        }
    })
}

//受理人显示，从之前的传多个自己选变为传过来一个默认的显示
function showReception() {
    $.ajax({
        type: "POST",
        url: "/profession/project/getReceptionList",
        datatype: 'json',
        data: {}, // 发送数据
        // error: function (msg) {
        //     layer.msg('请求失败！', {
        //         time: 1000
        //     });
        // },
        success: function (jsonobj) {
            if (jsonobj.code == "50000") {
                //这里设置项目受理人
                $("#reception").append(jsonobj.data[0].columns.ur_name);
            } else {
                layer.msg(jsonobj.desc + "!", {
                    time: 1000
                });
            }
        },
    });
}

function test(element) {
    switch (element) {
        case "title": {
            if ($("#title").val() == null || $("#title").val() === "") {
                $("#msgInfo_title").text("通知标题不能为空！")
                return false;
            } else {
                $("#msgInfo_title").text("")
                return true;
            }
        }
        case "content": {
            if ($("#content").val() == null || $("#content").val() === "") {
                $("#msgInfo_content").text("通知内容不能为空！")
                return false;
            } else {
                $("#msgInfo_content").text("")
                return true;
            }
        }
    }
}

function getLabels() {
    var l_id = "["
    var length = $("input:checkbox[name='check']:checked").length;
    if (length !== 0) {
        $("input:checkbox[name='check']:checked").each(function (i, index) {
            //操作
            console.log($(this).attr("id"))
            if (i < length - 1) {
                l_id += $(this).attr("id") + ","
            } else {
                l_id += $(this).attr("id") + "]"
            }
        });
    }
    return $.parseJSON(l_id)
}

// $('#data-picker input').val()

//已删除受理人，需要获得申请人的单位数据
function submit() {
    if (test('title') && test('content')) {
        let index = layer.load(1, {
            shade: [0.5, '#fff'], //0.1透明度的白色背景
        });
        let data = {
            unitId: $("#unit").val(),
            title: $("#title").val(),
            content: $("#content").val(),
            level: $("#level").val(),
            endtime: $('#data-picker input').val(),
            labels: getLabels().join(','),
            fileIds: file_ids.join(',')
        };
        $.ajax({
            url: "/notice/addNotice",
            data: data,
            async: true,
            type: "POST",
            // contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            success: function (re) {
                if (re.code === "2002") {
                    layer.close(index)
                    layer.msg('通知发布成功', {
                        time: 2000
                    });


                } else {
                    layer.msg(re.desc, {
                        time: 1000
                    });
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.msg('操作失败 ', {
                    time: 1000
                });
            }
        });
    }
}


//上传文件
var uploader_file;
let max_size = 100;//单个文件最大大小
upload_file();
//当上传文件按钮初始状态是隐藏时需要refresh才能初始化成功
//uploader_file.refresh ();

function upload_file() {
    uploader_file = WebUploader.create(
        {
            auto: false, // 自动上传
            fileSizeLimit: max_size * 1024 * 1024,
            swf: './../assets/plugins/webuploader-0.1.5/Uploader.swf', // swf文件路径
            server: '/notice/uploadFile', // 文件接收服务端。
            pick:
                {
                    id: '#picker',
                    multiple: true
                }, // 选择文件的按钮。可选。
            accept:
                {
                    title: 'Files',
                    extensions: 'jpg,png,pdf,doc,docx,zip,rar',
                    mimeTypes: 'application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document' + ',application/pdf,application/x-zip-compressed,application/octet-stream'
                },
            resize: false,            // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
            fileSingleSizeLimit: max_size * 1024 * 1024,   //设定单个文件大小 100M

        });

    // 上传前的判断处理
    uploader_file.on('error', function (type) {
        if (type === 'F_DUPLICATE') {
            layer.msg('文件重复，不能上传！');
        }
        if (type === "Q_TYPE_DENIED") {
            layer.msg("文件格式有误");
        }
        if (type === "Q_EXCEED_SIZE_LIMIT") {
            layer.msg(`文件大小不能超过${max_size}M`);
        }
    });

    // 当有文件被添加进队列的时候
    var $list = $("#uploader_file");
    uploader_file.on('fileQueued', function (file) {
        // $list.html('<div id="' + file.id + '" class="item">' + '<h4 class="info">' + file.name +
        //     '<a type="button" class="btn btn-danger btn-xs webuploadDelbtn">删除</a></h4>'
        //     + '<p class="state">等待上传...</p></div>');
        $list.append('<div id="' + file.id + '" class="item">' + '<h4 class="info">' + file.name +
            '<a type="button" class="btn btn-danger btn-xs webuploadDelbtn">删除</a></h4>' +
            '<p class="state">等待上传...</p></div>');
    });

    // 点击开始上传
    $("#ctlBtn").on('click', function () {
        uploader_file.options.formData.guid = Math.random(); // 随机数生成文件唯一标识
        uploader_file.upload();
    });

    // 文件上传过程中创建进度条实时显示。
    uploader_file.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id), $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped active">' + '<div class="progress-bar" role="progressbar" style="width: 0%">' + '</div>' + '</div>').appendTo($li).find('.progress-bar');
        }
        $li.find('p.state').text('上传中');

        $percent.css('width', percentage * 100 + '%');
    });

    // 文件成功、失败处理
    uploader_file.on('uploadSuccess', function (file, response) {
        if (response.code != null && response.code === "2011") {
            $('#' + file.id).find('p.state').text('上传成功');
            $('#' + file.id).find('.delete_this_upload').remove();
            file_ids.push(response.data)
            console.log(file_ids)
        } else {
            if (response.desc == null) {
                $('#' + file.id).find('p.state').text("未知服务器错误，上传失败");
            } else {
                $('#' + file.id).find('p.state').text(response.desc);
            }
            $('#' + file.id).find('p.state').addClass("text-danger");
            $('#' + file.id).find('.delete_this_upload').remove();
        }
        $('#' + file.id).find('a.webuploadDelbtn').remove();
    });

    // 上传错误
    uploader_file.on('uploadError', function (file) {
        $('#' + file.id).find('p.state').text('上传出错');
    });
    // 上传完成
    uploader_file.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').fadeOut();
    });

    // 删除
    $list.on("click", ".webuploadDelbtn", function () {
        var $ele = $(this);
        var id = $ele.parent().parent().attr("id");
        var file = uploader_file.getFile(id);
        uploader_file.removeFile(file, true);
    });

    // 删除时执行的方法
    uploader_file.on('fileDequeued', function (file) {
        $(file.id).remove();
        $('#' + file.id).find('span.state').text('已经取消');
        $('#' + file.id).hide();
        console.log("remove");
    });
}
