//折叠菜单效果
$(function () {
    var Accordion = function (el, multiple) {
        this.el = el || {};
        this.multiple = multiple || false;

        var links = this.el.find('.link');

        links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
    }

    Accordion.prototype.dropdown = function (e) {
        var $el = e.data.el;
        $this = $(this),
            $next = $this.next();

        $next.slideToggle();
        $this.parent().toggleClass('open');

        if (!e.data.multiple) {
            $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
        }
        ;
    }

    var accordion = new Accordion($('#accordion'), false);
});
/*
 *  	 查询所有信息
 */
function query_all(object) {
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET", "/StudentManagement/GuestDao?action=query_all_" + object, true);
    xmlhttp.send();
}
/*
 * 	展示修改界面
 */
function show_alter(object) {
    var result = document.getElementById("result");
    var show = null;
    if (object == "user") {
        show = "<div id='alter_user'  class='d_form'>"
            + "<h3>更新个人信息</h3>"
            + "<p>修改属性</p>"
            + "<input type='text' name='after_sname' placeholder='姓名'>"
            + "<p>性别</p>"
            + "<div id='radio' class='radio'>"
            + "<input type='radio' name='after_ssex' value='男' checked='checked'><span>男</span>"
            + "<input type='radio' name='after_ssex' value='女'><span>女</span>"
            + "</div>"
            + "<input type='number' name='after_sage' placeholder='年龄'>"
            + "<input id='submit' onclick='alter_user()' type='button' name='submit' value='修改'>"
            + "</div>";
    }
    else if (object == "password") {
        show = "<div id='alter_password'  class='d_form'>"
            + "<h3>修改密码</h3>"
            + "<p>旧密码</p>"
            + "<input type='password' autofocus='autofocus' name='old_password' value placeholder='旧密码' required>"
            + "<p>新密码</p>"
            + "<p id='text'></p>"
            + "<input type='password' name='new_password0' value placeholder='新密码'  required>"
            + "<input type='password' name='new_password1' value placeholder='确认密码'>"
            + "<input id='submit' onclick='alter_password()' type='button' name='submit' value='修改'>"
            + "</div>";
    }
    else if (object == "course") {
        show = "<div class='d_form'>"
            + "<h3>请输入删除的选修课</h3>"
            + "<p>退课的ID</p>"
            + "<input id= 'alter_course' type='number' autofocus='autofocus' name='clno' value placeholder='选修课编号' required>"
            + "<input id='submit' onclick='alter_course()' type='button' name='submit' value='删除'>"
            + "</div>";
    }
    result.innerHTML = show;
}
/*
 * 修改操作
 */
//修改个人信息
function alter_user() {
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    var user = document.getElementById("alter_user").getElementsByTagName("input");
    var after_sname = user[0].value.toString();
    var after_ssex;
    if (user[1].checked == true)
    	after_ssex = user[1].value.toString();
    else
    	after_ssex = user[2].value.toString();
    var after_sage = user[3].value;
    var url = "/StudentManagement/GuestDao?action=alter_user&after_sname=" + encodeURI(encodeURI(after_sname)) 
    + "&after_ssex=" + encodeURI(encodeURI(after_ssex)) + "&after_sage=" + after_sage;
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}
//修改密码
function alter_password(){
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    var all = document.getElementById("alter_password").getElementsByTagName("input");
    var old_password = all[0].value.toString().trim();
    var new_password0 = all[1].value.toString().trim();
    var new_password1 = all[2].value.toString().trim();
    if(new_password0==new_password1){
    	var url = "/StudentManagement/GuestDao?action=alter_password&old_password=" +old_password+ "&new_password=" +new_password0;
    	xmlhttp.open("GET", url, true);
    	xmlhttp.send();
    }
    else{
    	document.getElementById("text").innerText="两次输入密码不一致";
    }
}
//退课
function alter_course() {
    var xmlhttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }
    else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    var cno = document.getElementById("alter_course").value;
    var url = "/StudentManagement/GuestDao?action=alter_course&cno=" + cno;
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}
function select_course(){
    var xmlhttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }
    else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    var select;
    var obj = document.getElementsByName("select");
    for(var i=0; i<obj.length; i++){
        if(obj[i].checked){
            select=obj[i].value;
            break;
        }
    }
    var url = "/StudentManagement/GuestDao?action=alter_select&select=" +select;
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}