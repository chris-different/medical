$.func = {
//页面初始化
    index_init: function() {
        
        $(".forum").hide();
        $(".register").hide();
        $(".login").hide();
        $(".mine").hide();
        $(".reg").hide();
        $(".detail").hide();
        $(".depart").hide();
        $(".blank").hide();

        $(".footer_icon_1").addClass("footer_icon_1_blue");
        $("#xintie").addClass("xintie_clicked");
        $.func.get_doctor_list("心血管内科");
        $.func.get_department_list();
        
    },
    
//footer按钮变色方法
    footer_icon_1: function() {
        $(".footer_icon_1").addClass("footer_icon_1_blue");
        $(".footer_icon_2").removeClass("footer_icon_2_blue");
        $(".footer_icon_3").removeClass("footer_icon_3_blue");
        $(".footer_icon_4").removeClass("footer_icon_4_blue");
    },
    footer_icon_2: function() {
        $(".footer_icon_2").addClass("footer_icon_2_blue");
        $(".footer_icon_1").removeClass("footer_icon_1_blue");
        $(".footer_icon_3").removeClass("footer_icon_3_blue");
        $(".footer_icon_4").removeClass("footer_icon_4_blue");
    },
    footer_icon_3: function() {
        $(".footer_icon_3").addClass("footer_icon_3_blue");
        $(".footer_icon_2").removeClass("footer_icon_2_blue");
        $(".footer_icon_1").removeClass("footer_icon_1_blue");
        $(".footer_icon_4").removeClass("footer_icon_4_blue");
    },
    footer_icon_4: function() {
        $(".footer_icon_4").addClass("footer_icon_4_blue");
        $(".footer_icon_2").removeClass("footer_icon_2_blue");
        $(".footer_icon_3").removeClass("footer_icon_3_blue");
        $(".footer_icon_1").removeClass("footer_icon_1_blue");
    },

    swiper_init : function() {
        var mySwiper = new Swiper ('.swiper-container', {
            direction: 'horizontal', // 垂直切换选项
          //   loop: true, // 循环模式选项
            autoplay:{
              delay:2000,
              disableOnInteraction: false,
            },
            // 如果需要分页器
            pagination: {
              el: '.swiper-pagination',
            },
            
            // 如果需要前进后退按钮
            navigation: {
              nextEl: '.swiper-button-next',
              prevEl: '.swiper-button-prev',
            },
            
          // 如果需要滚动条
          //   scrollbar: {
          //     el: '.swiper-scrollbar',
          //   },
          })    
    },

   
//模拟单页路由的具体方法
    route_index : function() {
        $(".main").show();
        $(".forum").hide();
        $(".register").hide();
        $(".login").hide();
        $(".reg").hide();
        $(".mine").hide();
        $(".detail").hide();
        $(".depart").hide();
        $(".blank").hide();


    },
    route_forum : function() {
        $(".main").hide();
        $(".forum").show();
        $(".register").hide();
        $(".login").hide();
        $(".reg").hide();
        $(".mine").hide();
        $(".detail").hide();
        $(".depart").hide();
        $(".blank").hide();


        $.func.get_questions();
    },
    route_register : function() {
        $(".main").hide();
        $(".forum").hide();
        $(".register").hide();
        $(".depart").show();

        $(".login").hide();
        $(".reg").hide();
        $(".mine").hide();
        $(".detail").hide();
        $(".blank").hide();

        $.func.get_parent_children();
    },
    route_depart : function(id) {
        $(".main").hide();
        $(".forum").hide();
        $(".register").show();
        $(".depart").hide();

        $(".login").hide();
        $(".reg").hide();
        $(".mine").hide();
        $(".detail").hide();
        $(".blank").hide();

        $.func.get_depart_detail(id);
    },
    route_mine : function() {
        $(".main").hide();
        $(".forum").hide();
        $(".register").hide();
        $(".login").hide();
        $(".reg").hide();
        $(".mine").show();
        $(".detail").hide();
        $(".depart").hide();
        $(".blank").hide();
        $.func.get_mine();


    },
    route_detail : function(id){
        $(".main").hide();
        $(".forum").hide();
        $(".register").hide();
        $(".login").hide();
        $(".reg").hide();
        $(".mine").hide();
        $(".detail").show();
        $(".depart").hide();
        $(".blank").hide();


        $.func.get_detail(id);
    },
//弹出挂号页面的路由方法
    route_blank_1 : function(id){
        $(".main").hide();
        $(".forum").hide();
        $(".register").hide();
        $(".login").hide();
        $(".reg").hide();
        $(".mine").hide();
        $(".detail").hide();
        $(".depart").hide();
        $(".blank").show();
        $.func.get_blank_1(id);
    },

//动态添加热门科室
    create_department_list_li : function (data){
        var divString = '<li class="doctor-header-item" id="department_list_li_'+data.id+'">'+data.name+'</li>';
        $div=$(divString);
         // 将div插入到<body>的尾部（插入到页面中）
         $div.appendTo( $('div.doctor_department').children()[0] );
         console.log('div插入成功')
    },

//首页获得热门科室
    get_department_list : function(){
        $.ajax({
            url: "/get_department_index",
            // data: {
            //     department_name : name
            // },
            type: "GET",
            dataType: "json",
            success: function(data) {
                console.log(data);
                $(".doctor_department ul").html("");
                $.each(data,function(i,n){
                    $.func.create_department_list_li(n);
                    //绑定首页按钮切换科室医生
                    $(document).on('click',"#department_list_li_"+n.id,function(){
                        $.func.get_doctor_list(n.name);
                        $(".doctor_department").children().children().removeClass("doctor-header-item-active");
                        
                        $("#department_list_li_"+n.id).addClass("doctor-header-item-active");
                    });
                });
                $(".doctor_department").children().children().eq(0).addClass("doctor-header-item-active");
                                
            }
        })
    },
//动态添加医生
    create_doctor_list : function (data){
        var divString = '<div class="side_bar"></div>'+
        '<div class="content_bar">'+
                '<div class="doctor_list">'+
                        '<div class="doctor clearfix">'+
                            '<div class="doctor_img"></div>'+
                            '<div class="doctor_content">'+
                                '<ul>'+
                                    '<li id="doctor_name">'+data.name+'</li>'+
                                    '<li id="doctor_department">'+data.departmentName+'</li>'+
                                '</ul>'+
                            '</div>'+
                        '</div>'+
                '</div>'+
        '</div>';
        $div=$(divString);
         // 将div插入到<body>的尾部（插入到页面中）
         $div.appendTo( $('div.all_doctor_content'));
         console.log('div插入成功')
    },
//获得对应科室的所有医生
    get_doctor_list : function(name){
        $.ajax({
            url: "/get_doctor_list",
            data: {
                department_name : name,
            },
            type: "POST",
            dataType: "json",
            success: function(data) {
                console.log(data);
                $('div.all_doctor_content').html("");
                $.each(data,function(i,n){
                    $.func.create_doctor_list(n);
                })
                                
            }
        })
    },
//请求问题细节
    get_detail : function(id) {
        $.ajax({
            url: "/question/"+id,
            type: "GET",
            dataType: "json",
            success: function(data) {
                console.log(data);
                $(".detail_com").remove();
                $(".detail_question_title").html(data.question.title);
                $(".detail_question_content").html(data.question.content);
                $(".detail_question_createdDate").html("日期: "+data.question.createdDateString);
                $(".detail_comment_count").html("评论数量: "+data.question.commentCount);
                $("#q_id").val(data.question.id);
                
                $.each(data.comments,function(i,n){
                    
                    $.func.create_question_div(n);
                })
                                
            }
        })
    },
//动态生成评论div盒子
    create_question_div : function (data){
        console.log(data);
        var divString = '<div class="detail_com clearfix">'+
        '<div class="detail_user_img"></div>' +
        '<div class="detail_user_box">' +
            '<div class="detail_user_name clearfix">'+
                '<div class="detail_like"></div>'+
            '</div>'+
            '<div class="detail_user_content"></div>' +
        '</div>' +
    '</div>';
        $div=$(divString);
         $div.find('.detail_user_name').html(data.user.username);// 标题
         $div.find('.detail_user_content').html(data.content);// 工作时间
         
         // 将div插入到<body>的尾部（插入到页面中）
         $div.appendTo( $('div.detail') );
         console.log('div插入成功')
    },



    // login_pass : function() {
    //     #.ajax({

    //     })
    // },





//创建depart盒子
create_depart_div : function (data,teshuId){
    $.each(data,function(i,n){
        var divMiddle = '<li><a id="dep_'+n.id+'" href="#depart/'+n.id+'">'+n.name+'</a></li>'
        $divMiddle = $(divMiddle);
        $divMiddle.appendTo($('ul#teshu_'+teshuId));
        // $("#"+n.id).click(function(){
        //     $.func.route_depart(n.id);
        // })
        $(document).on('click',"#dep_"+n.id,function(){
            $.func.route_depart(n.id);
        })
    })
},
//创建divtitle
create_depart_title : function (data){
    
    var divString = '<div class="depart_title">'+data+'</div>'+
    '<div class="depart_content_bar">'+
        '<ul id="teshu_'+data +'" class="clearfix"> '+
        '</ul>'+
    '</div>';
    $div=$(divString);
    // 将div插入到<body>的尾部（插入到页面中）
    $div.appendTo( $('div.depart_content') );
    console.log('div插入成功')
},
//depart科目页面渲染
    get_parent_children : function(){
        $.ajax({
            url: "/get_department_parent_children",
            // data: {
            //     department_id : 1
            // },
            type: "POST",
            dataType: "json",
            success: function(data) {
                console.log(data);
                $(".depart_content").html("");
                $.each(data,function(i,n){
                    $.func.create_depart_title(i);
                    // $.each(n,function(j,o){
                        
                    //     $.create_depart_div(o);
                    // });
                    $.func.create_depart_div(n,i);
                    
                })
                                
            }
        })
    },
    
//动态生成具体depart盒子
    createDepartDiv : function (data){
    var divString = '<div id="question'+ data.id+ '" class="question_object">'+
    '<a href="#'+'/question/'+data.id+'"'+
    '<input type="hidden" name="question_id" value="'+ data.id +'">'+
    '<div class="question_title">1</div>'+
    '<div class="answer_summary">2</div>'+
    '<div class="question_bottom">'+
    '<div class="like"></div>'+
    '</div>'+
    '</div>'+
    '</a>';
    $div=$(divString);
     $div.find('.question_title').html(data.title);// 标题
     $div.find('.answer_summary').html(data.content);// 工作时间
     
     // 将div插入到<body>的尾部（插入到页面中）
     $div.appendTo( $('div#questions') );
     console.log('div插入成功');
     $("#question"+data.id).click(function(){
        $.func.route_detail(data.id);
     });
},

//具体depart页面方法
    get_depart_detail : function (id) {
        $(".register_doctor_list").html("");
        $.func.get_register_record(0,4,id);
    },
















//获取挂号订单信息
    get_register_record : function(a,b,departmentId) {
        $.ajax({
            url: "/register_record",
            data: {
                department_id : departmentId
            },
            type: "POST",
            dataType: "json",
            success: function(data) {
                console.log(data);
                window.data = data;
                $.func.create_schedule_div(a,b,data);
                                
            }
        })
    },
//添加时间表盒子
create_schedule_div : function(a,b,data){
    $('div.m_table').html("");
    var dic = {
        0:"星期一",
        1:"星期二",
        2:"星期三",
        3:"星期四",
        4:"星期五",
        5:"星期六",
        6:"星期七",
    };
    $.each(data,function(i,n){
        if(i>=a&&i<=b){
            var divSchedule = '<div id="weekday_'+n.id+'" class="weekday">'+
            '<div class="weekday_title">'+dic[n.realSchedule]+'<p id="p_'+n.id+'">'+n.realScheduleString+'</p>'+'</div>'+
                '<ul id="'+n.departmentId+'">'+
                    '<li id="m_'+n.id+'">'+n.morning+'</li>'+
                    '<li id="a_'+n.id+'">'+n.afternoon+'</li>'+
                    '<li id="n_'+n.id+'">'+n.night+'</li>'+
                '</ul>'+
            '</div>';
            $divSchedule = $(divSchedule);
            $divSchedule.appendTo($('div.m_table'));


            $("#m_"+n.id).click(function(){

                $(".m_table li").removeClass("register_click");
                $(this).addClass("register_click");
                console.log($(this))
                console.log($(this).text())

                $.func.get_doctor_record(n.departmentId,$("p#p_"+n.id).text(),"morning");

            });


            $("#a_"+n.id).click(function(){

                $(".m_table li").removeClass("register_click");
                $(this).addClass("register_click");
                $.func.get_doctor_record(n.departmentId,$("p#p_"+n.id).text(),"afternoon");

            });


            $("#n_"+n.id).click(function(){

                $(".m_table li").removeClass("register_click");
                $(this).addClass("register_click");
                $.func.get_doctor_record(n.departmentId,$("p#p_"+n.id).text(),"night");
                

            });
        }else{
            // var divSchedule = '<div id="weekday_'+n.id+'" class="weekday">'+
            // '<div class="weekday_title">'+dic[n.realSchedule]+'</div>'+
            //     '<ul>'+
            //         '<li>'+n.morning+'</li>'+
            //         '<li>'+n.afternoon+'</li>'+
            //         '<li>'+n.night+'</li>'+
            //     '</ul>'+
            // '</div>';
            // $divSchedule = $(divSchedule);
            // $divSchedule.appendTo($('div.m_table'));
        }
        
        // $("#"+n.id).click(function(){
        //     $.func.route_depart(n.id);
        // })
    });
    
},
//点击时间表后切出对应医生

create_schedule_doctor_div : function(data) {
    var divString = '<div class="register_doctor_list_bar clearfix">'+
    '<div class="register_doctor_list_bar_one"></div>'+
    '<div class="register_doctor_list_bar_two">'+
        data.name+
    '</div>'+
    '<div class="register_doctor_list_bar_three">'+
        '<span>医师服务费:</span>'+ 
        '<span>剩余号:'+$(".register_click").text()+'</span>'+
    '</div>'+
    '<div class="register_doctor_list_bar_four">'+
        '<a id="re_a_'+data.id+'" href="#final_register/'+data.id+'">预约挂号</a>'+
    '</div>'+
'</div>';
    $div=$(divString);
    // 将div插入到<body>的尾部（插入到页面中）
    $div.appendTo( $('div.register_doctor_list'));
    $("a#re_a_"+data.id).click(function(){
        dicc = {
            "m" : "morning",
            "a" : "afternoon",
            "n" : "night"
        }
        console.log(data.id);
        // $.func.route_blank_1(data.id);
        var departmentId = $(".register_click").parent()[0].id
        var date = $(".register_click").parent().parent().children().eq(0).children().eq(0).text()
        var doctorId = data.id
        var scheduleName = dicc[$(".register_click").attr("id").substring(0,1)]
        layer.open({
            content: '您确定要预约'+' '+date+"的挂号吗？"
            ,btn: ['预约', '取消']
            ,yes: function(index){
                console.log(departmentId+doctorId+date+scheduleName)
                $.func.add_order_record(departmentId,doctorId,date,scheduleName);
            //  location.reload();
                
                layer.close(index);
            }
          });
    })
    
    console.log('div插入成功')
},
//进行挂号order_record
add_order_record : function(departmentId,doctorId,date,scheduleName){
    $.ajax({
        url: "/add_order_record",
        data: {
            department_id : departmentId,
            doctor_id : doctorId,
            schedule_date : date,
            schedule_order : scheduleName
        },
        type: "POST",
        dataType: "json",
        success: function(data) {
            if(data==0){
                layer.open({
                    content: '已经预约过了'
                    ,skin: 'msg'
                    ,time: 2 //2秒后自动关闭
                  });
            }else{
                layer.open({
                    content: '预约成功'
                    ,skin: 'msg'
                    ,time: 2 //2秒后自动关闭
                  });
                  $.func.get_depart_detail($(".register_click").parent()[0].id);
            }
            
            // $.func.create_schedule_doctor_div(data);
                            
        }
    })
},
//得到医生排班记录
get_doctor_record : function(departmentId,scheduleDate,scheduleName){
    $.ajax({
        url: "/get_doctor_record",
        data: {
            department_id : departmentId,
            schedule_date : scheduleDate,
            schedule_name : scheduleName
        },
        type: "POST",
        dataType: "json",
        success: function(data) {
            console.log(data);
            $('div.register_doctor_list').html("");
            $.each(data,function(i,n){
                $.func.create_schedule_doctor_div(n);
            })
            // $.func.create_schedule_doctor_div(data);
                            
        }
    })
},



//点击弹出订单挂号页面
get_blank_1 : function(){
    $(".blank_content").html("")
},



//请求根据id查询个人名下订单
get_my_order : function(id){
    $.ajax({
        url: "/get_order_list",
        data: {
            user_id : id
        },
        type: "POST",
        // dataType: "json",
        success: function(data) {
            console.log(data);
            if(data.length==0){
                $("div.mine_1").html("");
                var bl = "&nbsp&nbsp&nbsp&nbsp";
                var  divfd = '<div class="mine_side_bar_wu">'+bl+'您当前没有预约'+'</div>';
                $divfd = $(divfd)
                $divfd.appendTo("div.mine_1")
            }else{
                $("div.mine_1").html("");
                $.each(data,function(i,n){
                    $.func.create_mine_order_div(i,n);
                })
                
            }
            
            // $.func.create_schedule_doctor_div(data);
                            
        },
        error: function() {
            alert("none")
        }
        
    })
},
//
create_mine_order_div : function(i,data){
    dic = {
        "morning" : "上午",
        "afternoon" : "中午",
        "night" : "晚上"
    }
    var bl = "&nbsp&nbsp&nbsp&nbsp";
    var  divfd = '<div id="ty_'+data.orderId+'" class="mine_side_bar">'+bl+i+'&nbsp:'+'日期:&nbsp'+data.scheduleDate+" &nbsp&nbsp"+dic[data.scheduleOrder]+'&nbsp'+
        '<input class="ty_button" type="button" value="取消预约">'+'</div>';
        $divfd = $(divfd)
        $divfd.appendTo("div.mine_1");

        $("#ty_"+data.orderId).click(function(){
            console.log(data.orderId);
            layer.open({
                content: '您确定要取消本次预约吗？'
                ,btn: ['确定', '不要']
                ,yes: function(index){
                    $.func.ty_method(data.orderId)
                    layer.close(index);
                }
              });
        })
    
},



//

//请求根据id查询个人名下失约订单
get_my_u_order : function(id){
    $.ajax({
        url: "/get_u_order_list",
        data: {
            user_id : id
        },
        type: "POST",
        // dataType: "json",
        success: function(data) {
            console.log(data);
            if(data.length==0){
                $("div.mine_2").html("");
                var bl = "&nbsp&nbsp&nbsp&nbsp";
                var  divfd = '<div class="mine_side_bar_wu">'+bl+'信用良好，无爽约'+'</div>';
                $divfd = $(divfd)
                $divfd.appendTo("div.mine_2")
            }else{
                $("div.mine_2").html("");
                $.each(data,function(i,n){
                    $.func.create_u_mine_order_div(i,n);
                })
                
            }
            
            // $.func.create_schedule_doctor_div(data);
                            
        },
        
    })
},
//创建失约
create_u_mine_order_div : function(i,data){
    dic = {
        "morning" : "上午",
        "afternoon" : "中午",
        "night" : "晚上"
    }
    var bl = "&nbsp&nbsp&nbsp&nbsp";
    var  divfd = '<div id="ty_'+data.orderId+'" class="mine_side_bar">'+bl+i+'&nbsp:'+'日期:&nbsp'+data.scheduleDate+" &nbsp&nbsp"+dic[data.scheduleOrder]+'&nbsp';
        // '<input class="ty_button" type="button" value="取消预约">'+'</div>';
        $divfd = $(divfd)
        $divfd.appendTo("div.mine_2");

        // $("#ty_"+data.orderId).click(function(){
        //     console.log(data.orderId);
        //     layer.open({
        //         content: '您确定要取消本次预约吗？'
        //         ,btn: ['确定', '不要']
        //         ,yes: function(index){
        //             $.func.ty_method(data.orderId)
        //             layer.close(index);
        //         }
        //       });
        // })
    
},
//请求根据orderId退约
ty_method : function(orderId){
    $.ajax({
        url: "/delete_order",
        data: {
            order_id : orderId
        },
        type: "POST",
        dataType: "json",
        success: function(data) {
            var my_id = $(".unique").attr("id");
            $.func.get_my_order(my_id);  
            
            // $.func.create_schedule_doctor_div(data);
                            
        }
    })
},
//个人首页的路由具体方法
get_mine : function(){
    $(".header_name").html($(".unique_name").attr("id"));
    var my_id = $(".unique").attr("id");
    $.func.get_my_order(my_id);   
    $.func.get_my_u_order(my_id);   
  
},
























//提问按钮
    ask_question : function(que,con,index){
        $.ajax({
            url: "/question/add",
            data: {
                title: que,
                content:con
            },
            type: "POST",
            // dataType: "json",
            success: function(data) {
                console.log(data);
                layer.close(index);
                $.func.get_questions();

                // $.each(data,function(i,n){
                //     $.func.createDiv(n);

                // })
                // data = jQuery.parseJSON(data);  //dataType指明了返回数据为json类型，故不需要再反序列化

            }
        });
    },

//发表按钮
submit_comment : function(index,entityId,data){
    $.ajax({
        url: "/comment/add",
        data: {
            entity_id: entityId,
            context: data
        },
        type: "POST",
        // dataType: "json",
        success: function(data) {
            console.log(data);
            layer.close(index);
            $.func.get_detail(entityId);


            // $.each(data,function(i,n){
            //     $.func.createDiv(n);

            // })
            // data = jQuery.parseJSON(data);  //dataType指明了返回数据为json类型，故不需要再反序列化

        }
    });
},

//获取讨论问题API
    get_questions : function() {
        $("#questions").html("");
        $.ajax({
            url: "/get_questions",
            // data: {},
            type: "GET",
            dataType: "json",
            success: function(data) {
                console.log(data)
                $.each(data,function(i,n){
                    $.func.createDiv(n);

                })
                // data = jQuery.parseJSON(data);  //dataType指明了返回数据为json类型，故不需要再反序列化

            }
        });
    },

//获取讨论问题API
get_questions_re : function() {
    $("#questions").html("");
    $.ajax({
        url: "/get_questions_re",
        // data: {},
        type: "GET",
        dataType: "json",
        success: function(data) {
            console.log(data)
            $.each(data,function(i,n){
                $.func.createDiv(n);

            })
            // data = jQuery.parseJSON(data);  //dataType指明了返回数据为json类型，故不需要再反序列化

        }
    });
},


//动态生成div盒子
    createDiv : function (data){
        var divString = '<div id="question'+ data.id+ '" class="question_object">'+
        '<a href="#'+'/question/'+data.id+'"'+
        '<input type="hidden" name="question_id" value="'+ data.id +'">'+
        '<div class="question_title">1</div>'+
        '<div class="answer_summary">2</div>'+
        '<div class="question_bottom">'+
        '<div class="like"></div>'+
        '</div>'+
        '</div>'+
        '</a>';
        $div=$(divString);
         $div.find('.question_title').html(data.title);// 标题
         $div.find('.answer_summary').html(data.content);// 工作时间
         
         // 将div插入到<body>的尾部（插入到页面中）
         $div.appendTo( $('div#questions') );
         console.log('div插入成功');
         $("#question"+data.id).click(function(){
            $.func.route_detail(data.id);
         });
    },
//切换注册页面
    register_route : function() {
        $(".login").hide();
        $(".reg").show();
    },
//切换登录页面
    log_route : function() {
        $(".reg").hide();
        $(".login").show();
    }

}






// 页面准备好后要绑定的事件
$(document).ready(function(){
    $.func.index_init();
    window.my_url = window.location.href;
    window.offset = 0;
    window.left = 0;
    window.right = 4;
//绑定路由

    Router.route('/index', function () {
        $.func.footer_icon_1();
        $.func.route_index();
    });
    Router.route('/forum', function () {
        $.func.footer_icon_2();
        $.func.route_forum();
    });
    Router.route('/register', function () {
        $.func.footer_icon_3();
        $.func.route_register();
    });
    Router.route('/depart_detail', function () {
        $.func.footer_icon_3();
        $.func.route_depart();
    });
    Router.route('/mine', function () {
        $.func.footer_icon_4();
        $.func.route_mine();
    });
    Router.route('/reg', function () {
        $.func.register_route();
    });
    Router.route('/log', function () {
        $.func.log_route();
    });
    

//footer按钮路由绑定
    $(".footer_icon_1").click(function(){
        location.href=(window.my_url+"#/index");
    });
    $(".footer_icon_2").click(function(){
        location.href=(window.my_url+"#/forum");
    });
    $(".footer_icon_3").click(function(){
        location.href=(window.my_url+"#/register");
    });
    $(".footer_icon_4").click(function(){
        location.href=(window.my_url+"#/mine");
    });

    //按钮绑定路由
    // $(".register_route").click(function(){
    //     location.href=(window.location.href+"#/reg");
    // });
    // $(".log_route").click(function(){
    //     location.href=(window.location.href+"#/log");
    // });
    // $(".log_button").click(function(){
        
    // });
    // $(".reg_button").click(function(){
    //     var pass = $("#passwd").val();
    //     var dbpass = $("#dbpasswd").val();
    //     if(pass==""||dbpass==""){
    //         layer.msg('不能为空');
    //     };
    //     if(pass!=dbpass){
    //         layer.msg('密码不一致');
    //     }else{

    //     }
    // });

//登出按钮
    $('.nav_button').on('click', function(){
        layer.open({
            content: '您确定要登出吗？'
            ,btn: ['登出', '不要']
            ,yes: function(index){
                $.ajax({
                    url: "/logout",
                    // data: {},
                    type: "GET",
                    success: function(data) {
                        console.log(data)
                        location.replace("/reglogin");
                        // data = jQuery.parseJSON(data);  //dataType指明了返回数据为json类型，故不需要再反序列化
                    }
                });
            //   location.reload();
              layer.close(index);
            }
        });
      });




    $(".question_object").on('click', function(){
        
    });





//写留言

$(".detail_write_comment").on('click', function(){
    // layer.
    layer.open({
        type: 1
        ,content: '<form id="ask_form" action="">'+
        '<textarea name="ques" id="ques_text" cols="21" placeholder="请准确叙述看法，少于20字" rows="2"></textarea>'+
        '<input value="发表评论" id="com_ask" type="button">'+
    '</form>'
        ,anim: 'up'
        ,style: 'position:fixed; bottom:5rem; left:0.7rem; width: 6rem;margin: 0 auto; height: 3rem; padding:0 0; border:none;'
        ,success: function(){
            $("#ques_text").focus();
        }
        
      });
})


//    //绑定留言弹出页面层的发表评论按钮
$(document).on('click',"#com_ask",function(){
    var index = $("[class='layui-m-layer layui-m-layer1']").attr("index")
    // console.log(index);
    var com = $("#ques_text").val();
    console.log(com);
    var sub_id = $("#q_id").val();
    $.func.submit_comment(index,sub_id,com);
});








//绑定时间表左右键
    $(".left_button").click(function(){
        if(window.left>0){
            window.left = window.left - 1;
            window.right = window.right -1;
            $.func.create_schedule_div(window.left,window.right,window.data);
        }else{
            layer.open({
                content: '只能提前7天挂号'
                ,skin: 'msg'
                ,time: 2 //2秒后自动关闭
              });
        }
    });
    $(".right_button").click(function(){
        if(window.right<6){
            window.left = window.left + 1;
            window.right = window.right + 1;
            $.func.create_schedule_div(window.left,window.right,window.data);
        }else{
            layer.open({
                content: '只能提前7天挂号'
                ,skin: 'msg'
                ,time: 2 //2秒后自动关闭
              });
        }
    });


// 提问按钮绑定事件ajax
    $("#ask").on('click',function(){
        // layer.
        layer.open({
            type: 1
            ,content: '<form id="ask_form" action="">'+
            '<textarea name="ques" id="ques_text" cols="21" placeholder="请准确叙述问题，少于20字" rows="2"></textarea>'+
            '<textarea name="con" id="con_text" cols="21" placeholder="描述具体内容" rows="3"></textarea>'+
            '<input value="提问" id="ask_button" type="button">'+
        '</form>'
            ,anim: 'up'
            ,style: 'position:fixed; bottom:5rem; left:0.7rem; width: 6rem;margin: 0 auto; height: 5rem; padding:0 0; border:none;'
            ,success: function(){
                $("#ques_text").focus();
            }
            
          });
    });

//回退
    $(".back_button").on('click',function(){
        history.back(-1);
    });

    

    //绑定弹出页面层的提问按钮
    $(document).on('click',"#ask_button",function(){
        var index = $("[class='layui-m-layer layui-m-layer1']").attr("index")
        // console.log(index);
        var que = $("#ques_text").val();
        var con = $("#con_text").val();
        // console.log(que);

        $.func.ask_question(que,con,index);
    });

    $(document).on('click','#xintie',function(){
        layer.open({
            type: 2,
            time: 1
        });
        $.func.get_questions();
        $("#xintie").addClass("xintie_clicked");
        $("#jingpin").removeClass("xintie_clicked");


    })

    $(document).on('click','#jingpin',function(){
        layer.open({
            type: 2,
            time: 0.5
        });
        $.func.get_questions_re();
        $("#xintie").removeClass("xintie_clicked");
        $("#jingpin").addClass("xintie_clicked");
    })


    

});



//   $("button").click(function(){
//     $("li").each(function(){
//     alert($(this).text())
//     });
//     });




