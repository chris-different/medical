$.func = {
    //页面初始化
    index_init: function() {
        
        $(".login").show();
        $(".reg").hide();

    },

    //模拟单页路由
    register_route : function() {
        $(".login").hide();
        $(".reg").show();
    }

    


}


    // login_pass : function() {
    //     #.ajax({

    //     })
    // },


// 页面准备好后要绑定的事件
$(document).ready(function(){
    $.func.index_init();
    
    $(".register_route").click(function(){
        $.func.register_route();
    });
    $(".log_route").click(function(){
        $(".reg").hide();
        $(".login").show();
    });
    $(".log_button").click(function(){
        $("#login_form").submit();

    });
    
    $(".reg_button").click(function(){
        var pass = $("#passwd").val();
        var dbpass = $("#dbpasswd").val();
        if(pass==""||dbpass==""){
            layer.msg('不能为空');
        }else{
            if(pass!=dbpass){
                layer.msg('密码不一致');
            }else{
                $("#reg_form").submit();
                layer.open({
                    content: '登陆成功'
                    ,skin: 'footer'
                });
            }
        }
        
    })


});


