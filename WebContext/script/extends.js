
/**
 * 把jQuery<Form>对象转换为json对象
 * @returns {JSON}
 */
jQuery.prototype.serializeJson= function (){
        var $ = jQuery;  
        var serializeObj={};  
        var array=this.serializeArray();  
        $(array).each(function(){  
            if(serializeObj[this.name]){  
                if($.isArray(serializeObj[this.name])){  
                    serializeObj[this.name].push(this.value);  
                }else{  
                    serializeObj[this.name]=[serializeObj[this.name],this.value];  
                }  
            }else{  
                serializeObj[this.name]=this.value; 
            }  
        });  
        return serializeObj;
    };

//注册一个工具类，所有的工具函数全部是这个类的属性
var Utils = {
  /**
    * 处理向服务器发送json数据的函数，内部采用jquery的ajax方法
    * @param {String} url 服务器URL
    * @param {String} formId HTML表单元素
    * @param {String} method 向服务发送数据使用的方法get/post两种
    */
        regestForm : function (url,formId,method,successFn,errorFn) { 
        if(url == undefined || formId == undefined ||method ==undefined) return null;
        //序列化表单对象
        var reg = new RegExp(/^"#"/);
        if(!reg.test(formId)) formId = "#"+formId;
        var formJson = jQuery(formId).serializeJson();                             
        if(typeof(successFn)!= "function"||typeof(errorFn)!= "function") throw new Error("sccusse或error参数非函数类型");
        //jquery Ajax函数发送jsonp到服务器
        console.log(JSON.stringify(formJson));
        jQuery.ajax({
            type: method,
            url:  url,
            data: JSON.stringify(formJson),
            dataType: "json",
            success: successFn,
            error:errorFn
            });
     },
     /**
     * 设置cookie的方法
     * @param {String} name 
     * @param {String} value 
     * @param {int} age 
     * @param {String} domain 
     * @param {String} path 
     * @param {boolean} secure 
     */
     setCookie : function (name, value, age, domain, path, secure) { 
        if (name == undefined || value == undefined) return;
        var cookie = encodeURIComponent(name) + "=" + encodeURIComponent(value);
        if (age != undefined) {
            cookie += ';expires=' + setCookieAge(age).toUTCString();
        }
        if (path != undefined) {
            cookie += ';path=' + path;
        }
        if (domain != undefined) {
            cookie += ';domain=' + domain;
        }
        if (secure) {
            cookie += ';secure';
        }
       document.cookie = cookie;
        /**
         * 内部函数
         * 设置cookie的到期时间，参数是一个数字类型，单位是天
         * @param {number} day 
         */
        function setCookieAge(day) {
            var date;
            if (typeof day == 'number' && day > 0) {
                date = new Date();
                date.setDate(date.getDate() + day);
            } else {
                throw new Error('输入时间错误');
            }
            return date;
        }
    
    },

      /**
       * 序列化cookie字符串为js键值对对象
       * @return {{username:value}}
       */
      parseCookie:function () { 
        var cookie = document.cookie.replace(/\s/g, "");
        var cookieArray = cookie.split(";");
        var box = {};
        var name, value;
        cookieArray.forEach(function (item) {
            var entries = item.split("=");
            name = decodeURIComponent(entries[0]);
            value = decodeURIComponent(entries[1]);
            box[name] = value;
        });
        return box;
       }
};
