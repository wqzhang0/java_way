/*!
 * @file: uploadImgPlugin.js
 * @author: wqzhang
 * @email:wqzhang0@163.com
 * --------------------------------------------------------------------------
 * 功能描述:图片上传插件,
 * 1.使用一键引用,自动生成上传样式模板
 * 2.支持自定义配置
 * --------------------------------------------------------------------------
 * 使用方法:
 * 注意:在引用本js之前,先引用jquery插件
 * 1.引用文件
 * <script type="text/javascript" src="js/jquery-x.js"></script>
 * <script type="text/javascript" src="js/uploadImgPlugin.js"></script>
 * <link rel="stylesheet" type="text/css" href="css/uploadImgPlugin.css"/>
 * 2.为需要使用上传功能的input 设置类
 *  <input type="hidden" class="wqImgUpLoad"/>
 * 3.如果需要更多自定义属性自行添加
 * --------------------------------------------------------------------------
 * 可配置参数说明:
 * uploadType : 可自定义该属性 用于业务区分图片类型 默认填充0 (如需要可自定更改 DEFAULT_UPLOAD_TYPE)
 * defaultPath : 无图片时显示用于显示的默认图片 需要传入路径
 * fileTypeScope : 用户自定义上传文件类型限制 对文件名使用正则表达式判断默认/.+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$/ (如需要可自定更改 DEFAULT_FILE_TYPE_SCOPE)
 * maxSize : 限制图片上传的大小 单位为M(兆) 默认填充20 (如需要可自定更改 DEFAULT_UPLOAD_TYPE)
 * showModel : 用来限定照片显示的方式 分三种
 *          MODEL_DEFAULT = "DEFAULT"; 默认大小控制方式,使用默认图片的宽度(DEFAULT_IMG_WIDTH) 高度(DEFAULT_IMG_HEIGHT)
 *          MODEL_ADAPTIVE = "ADAPTIVE"; 使用图片本身大小
 *          MODEL_CUSTOMIZE = "CUSTOMIZE";  自定义图片大小.需要显示指定图片尺寸 宽度(imgWidth) 高度(imgHeight)
 *          eg:<input type="text" showModel="CUSTOMIZE" imgWidth = "124px;" imgHeight = "68px;">
 * imgPrefix : 如需要自动添加图片前缀,设置此属性 图片显示将自动填充设置值 默认为 "" (如需要可自定更改 DEFAULT_IMG_PREFIX)
 * imgSuffix : 如需要自动添加图片后缀,设置此属性 图片显示将自动填充设置值 默认为 "" (如需要可自定更改 DEFAULT_IMG_SUFFIX) *
 * --------------------------------------------------------------------------
 * 重置图片 直接调用方法resetUploadPluginImg();
 * 获取文件值 直接对原input进行 $("#youId").val();即可
 */
function UploadImgPlugin() {

    var imgModels = Array();
    var basePath = $("base").attr("href");
    //用户自定义参数
    var UPLOAD_TYPE = "uploadType";
    //默认路径参数
    var DEFAULT_PATH = "defaultPath";
    //文件类型限制
    var FILE_TYPE_SCOPE = "fileTypeScope";
    //最大尺寸
    var MAX_SIZE = "maxSize";
    var SHOW_MODEL = "showModel";
    var IMG_WIDTH = "imgWidth"
    var IMG_HEIGHT = "imgHeight"
    var IMG_PREFIX = "imgPrefix";
    var IMG_SUFFIX = "imgSuffix";

    //默认参数
    var MODEL_DEFAULT = "DEFAULT";
    var MODEL_ADAPTIVE = "ADAPTIVE";
    var MODEL_CUSTOMIZE = "CUSTOMIZE";
    //上传类型
    var DEFAULT_UPLOAD_TYPE = "0";
    //默认路径
    var DEFAULT_DEFAULT_PATH = "defaultPic/default.png";
    //文件类型限制
    var DEFAULT_FILE_TYPE_SCOPE = /.+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$/;
    //最大尺寸
    var DEFAULT_MAX_SIZE = "20";

    var DEFAULT_IMG_WIDTH = "142px;"
    var DEFAULT_IMG_HEIGHT = "108px;"

    var DEFAULT_IMG_PREFIX = "";
    var DEFAULT_IMG_SUFFIX = "";


    //图片显示后缀
    var suffix_img = "_img";
    //文件选择框后缀
    var suffix_file = "_file";
    //上传按钮区域 后缀
    var suffix_btnUpLoadArea = "_btnUpLoadArea";
    //上传按钮后缀
    var suffix_btnUpLoad = "_btnUpLoad";
    //删除 预览 区域后缀
    var suffix_btnDelArea = "_btnDelArea";
    //预览按钮后缀
    var suffix_btnPreview = "_btnPreview";
    //预览按钮后缀
    var suffix_btnProcessArea = "_btnProcessArea";
    

    //预览按钮后缀
    var previewInnerdiv = "_previewInnerdiv";

    //预览按钮后缀
    var previewOutterdiv = "_previewOutterdiv";
    
    var previewImgDom = "_previewImgDom";
    
    //删除按钮后缀
    var suffix_btnDel = "_btnDel";

    imgModels.add = function (_imgModel) {
        imgModels[imgModels.length] = _imgModel;
    }

    // this.initUploadPlugin = function () {
    //     initUploadPlugin();
    // }

    this.initUploadPlugin = initUploadPlugin();

    this.resetUpLoad = function () {
        // imgModels.each(function (imgModel) {
        //     switchBtnArea(imgModel,4);
        // })
        $.each(imgModels, function (index, imgModel) {
            switchBtnArea(imgModel, 4);
        })
    }


    function initUploadPlugin() {
        var imgsSpace = $(".wqImgUpLoad");
        //需要定义的方法
        imgsSpace.each(function () {
            $_init($(this));
        });
    }

    function $_init(_originalDom) {
        //隐藏原本元素
//        _originalDom.hide();
        //创建随机Id
        var _scopeId = createRandomId();
        //先获取基本设置元素
        var imgModel = setSelfAttribute(_originalDom, _scopeId);
        //增加节点
        addBrotherDom(_originalDom, imgModel);
        //绑定元素
        var imgModel = bindDom(imgModel);
        //绑定事件
        bindEvent(imgModel);

        switchBtnArea(imgModel, 4);
    }

    function setSelfAttribute($originalDom, _scopeId) {
        var _uploadType = $originalDom.attr(UPLOAD_TYPE);
        if (_uploadType == null || _uploadType == '') {
            _uploadType = DEFAULT_UPLOAD_TYPE;
        }
        var _defaultPath = $originalDom.attr(DEFAULT_PATH);

        if (_defaultPath == null || _defaultPath == '') {
            _defaultPath = DEFAULT_DEFAULT_PATH;
        }
        var _maxSize = $originalDom.attr(MAX_SIZE);

        if (_maxSize == null || _maxSize == '') {
            _maxSize = DEFAULT_MAX_SIZE;
        }
        var _fileTypeScope = $originalDom.attr(FILE_TYPE_SCOPE);
        if (_fileTypeScope == null || _fileTypeScope == '') {
            _fileTypeScope = DEFAULT_FILE_TYPE_SCOPE;
        }
        var _showModel = $originalDom.attr(SHOW_MODEL);
        if (_showModel == null || _showModel == '') {
            _showModel = MODEL_DEFAULT;
        }
        var _imgPrefix = $originalDom.attr(IMG_PREFIX);
        var _imgSuffix = $originalDom.attr(IMG_SUFFIX);

        var imgModel = new ImgModel();
        imgModel.scopeId = _scopeId;
        imgModel.showModel = _showModel;
        if (_showModel == MODEL_CUSTOMIZE) {
            imgModel.imgWidth = $originalDom.attr(IMG_WIDTH);
            imgModel.imgHeight = $originalDom.attr(IMG_HEIGHT);
        }
        if (_imgPrefix != null && _imgPrefix != '') {
            imgModel.imgPrefix = _imgPrefix;
        }
        if (_imgSuffix != null && _imgSuffix != '') {
            imgModel.imgSuffix = _imgSuffix;
        }
        imgModel.uploadType = _uploadType;
        imgModel.status = 0;
        imgModel.maxSize = _maxSize;
        imgModel.fileTypeScope = _fileTypeScope;
        imgModel.defaultPath = _defaultPath;
        imgModel.oldPath = $originalDom.val();
        imgModel.inputDom = $originalDom;
        return imgModel;
    }


    //获取dom节点 添加兄弟节点,
    function addBrotherDom(_dom, imgModel) {
        var _scopeId = imgModel.scopeId;
        var _showModel = imgModel.showModel;
        var _showPath = "";
        if (imgModel.oldPath != null && imgModel.oldPath != '') {
            _showPath = imgModel.imgPrefix + imgModel.oldPath + imgModel.imgSuffix;
        } else {
            _showPath = imgModel.imgPrefix + imgModel.defaultPath + imgModel.imgSuffix;
        }
        console.log(_showPath);

        var domStr = "<div style=\"display:inline-block;\">" +
            "<div style=\" position: relative;\">";
        //区分是什么显示模式
        if (_showModel == MODEL_DEFAULT) {

            domStr += "<img id=\"" + _scopeId + suffix_img + "\" src= \"" + _showPath + "\" style=\"width:" + DEFAULT_IMG_WIDTH + " height:" + DEFAULT_IMG_HEIGHT + "\" >";

        } else if (_showModel == MODEL_CUSTOMIZE) {
            domStr += "<img id=\"" + _scopeId + suffix_img + "\" src= \"" + _showPath + "\" style=\"width:" + imgModel.imgWidth + " height:" + imgModel.imgHeight + "\" >";

        } else if (_showModel == MODEL_ADAPTIVE) {
            domStr += "<img id=\"" + _scopeId + suffix_img + "\" src= \"" + _showPath + "\" >";
        }
        domStr +=
            "<input id=\"" + _scopeId + suffix_file + "\" type=\"file\" style=\"width: 100%;height: 100%; top: 0px;left: 0px;position: absolute; opacity: 0;\" multiple=\"multiple\"  >" +
            "</div>" +
            "<div id=\"" + _scopeId + suffix_btnUpLoadArea + "\" style=\"text-align: center;\"  >" +             
            "<a id=\"" + _scopeId + suffix_btnUpLoad + "\" class=\"btn btn-mini btn-warning\" >上传</a>" +
            "</div>" +
            "<div id=\"" + _scopeId + suffix_btnDelArea + "\" style=\"display: none;  text-align: center;\" >" +
            "<a id=\"" + _scopeId + suffix_btnDel + "\"    class=\"btn btn-mini btn-danger\"  >删除</a>" +
            "<a id=\"" + _scopeId + suffix_btnPreview + "\"  class=\"btn btn-mini btn-purple\" >预览</a>" +
            "</div> " +
            "<div id=\"" + _scopeId + suffix_btnProcessArea + "\" class='UPLOAD_PROCESS_IMG' style=\"display: none;\">" +
            // "<img   class='UPLOAD_PROCESS_IMG' style='height: 26px;'>"
            "</div> " +
            "<div id=\""+_scopeId + previewOutterdiv +"\" style=\"position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:999;width:100%;height:100%;display:none;\">"+
                "<div id=\""+_scopeId + previewInnerdiv +"\" style=\"position:absolute;\">" +
                    "<img id=\""+_scopeId+previewImgDom + "\"  style=\"border:5px solid #fff; position:absolute;  \" src='' /> " +
                "</div>"+
            "</div>"+
            "</div>"; 
        _dom.after(domStr);
    }

    function bindDom(imgModel) {
        var _scopeId = imgModel.scopeId;
        imgModel.fileDom = $("#" + _scopeId + suffix_file);
        imgModel.btnUploadArea = $("#" + _scopeId + suffix_btnUpLoadArea);
        imgModel.btnDelArea = $("#" + _scopeId + suffix_btnDelArea);
        imgModel.btnProcessArea = $("#" + _scopeId + suffix_btnProcessArea);
        imgModel.imageDom = $("#" + _scopeId + suffix_img);
        imgModel.btnUpLoadDom = $("#" + _scopeId + suffix_btnUpLoad);
        imgModel.btnPreviewDom = $("#" + _scopeId + suffix_btnPreview);
        imgModel.btnDelDom = $("#" + _scopeId + suffix_btnDel);
        imgModel.previewOutterdiv = $("#" + _scopeId + previewOutterdiv);
        imgModel.previewInnerdiv = $("#" + _scopeId + previewInnerdiv);        
        imgModel.previewImgDom = $("#" + _scopeId + previewImgDom);
        console.log(imgModel); 
        
        imgModel.btnProcessArea.find("img").css("width", imgModel.imageDom.css("width"));
        imgModels.add(imgModel);
        return imgModel;
    }

    //绑定事件
    function bindEvent(_imgModel) {
        //预览事件
        _imgModel.imageDom.on("click", _imgModel, function (__imgModel) {
            if (__imgModel.data.status == 1) {
                preview(__imgModel.data);
            }
        });
        //预览事件
        _imgModel.btnPreviewDom.on("click", _imgModel, function (__imgModel) {
            preview(__imgModel.data);
        });

        //上传事件
        _imgModel.btnUpLoadDom.on("click", function () {
            uploadImg(_imgModel);
        });

        //文件选择事件
        _imgModel.fileDom.on("change", _imgModel, function (_imgModel) {
            //如果是规定类型,更改图片显示
            var fileObj = _imgModel.data.fileDom[0];
            var windowURL = window.URL || window.webkitURL;
            var dataURL;
            if (fileObj && fileObj.files && fileObj.files[0]) {
                var fileName = fileObj.files[0].name;
                // console.log(_imgModel);
                if (_imgModel.data.fileTypeScope.test(fileName)) {
                    dataURL = windowURL.createObjectURL(fileObj.files[0]);
                    _imgModel.data.imageDom.attr('src', dataURL);
                } else {
                    console.log("图片格式不正确");
                }
            } else {
            }
        });

        //删除事件
        _imgModel.btnDelDom.on("click", _imgModel, function (_imgModel) {
            switchBtnArea(_imgModel.data, 0);
        });
    }


    //返回随机Id
    function createRandomId() {
        var len = 32;
        var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
        /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
        var maxPos = $chars.length;
        var pwd = '';
        for (var i = 0; i < len; i++) {
            pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
        }
        return pwd;
    }

    //定义一个对象 管理状态和基本参数
    function ImgModel() {
        this.primeryKey = "";
        //唯一标识 原dom标识
        this.id = "";
        //唯一标识 新dom 前缀标识
        this.scopeId = "";
        this.showModel = MODEL_DEFAULT;
        this.imgWidth = DEFAULT_IMG_WIDTH;
        this.imgHeight = DEFAULT_IMG_HEIGHT;
        //图片类型
        this.uploadType = DEFAULT_UPLOAD_TYPE;
        //状态 0 无图片 显示默认图片
        //     1 有图片,显示图片并且更改下面按钮群
        //     2 上传中
        this.status = 0;
        //最大上传大小
        this.maxSize = DEFAULT_MAX_SIZE;
        //可以上传的类型
        this.fileTypeScope = DEFAULT_FILE_TYPE_SCOPE;
        //默认地址
        this.defaultPath = "";
        //原地址
        this.oldPath = "";
        //返回地址
        this.returnPath = "";
        //文件前缀
        this.imgPrefix = DEFAULT_IMG_PREFIX;
        //后缀
        this.imgSuffix = DEFAULT_IMG_SUFFIX;
        //文件上传元素
        this.fileDom = "";
        //上传按钮区域
        this.btnUploadArea = '';
        //删除 预览按钮区域
        this.btnDelArea = '';
        //上传按钮
        this.btnProcessArea = '';
        //图片元素
        this.imageDom = '';
        //上传按钮
        this.btnUpLoadDom = '';
        //预览按钮
        this.btnPreviewDom = '';
        //删除按钮
        this.btnDelDom = '';
        //原本元素
        this.inputDom = '';
        //预览外框
        this.previewOutterdiv = '';
      //预览内框
        this.previewInnerdiv='';
        //预览图片区域
        this.previewImgDom = '';
        
        
    }


    //-----------------需要绑定的操作事件管理----start--------------------
    //改变状态
    function switchBtnArea(imgModel, currentStatus) {
        imgModel.btnProcessArea.css("background-size", imgModel.imageDom.css("width") + " 26px");
        imgModel.btnProcessArea.css("width", imgModel.imageDom.css("width"));

        console.log(" 切换状态" + currentStatus);
        if (currentStatus == 0) {
            //无照片状态
            imgModel.fileDom.val("");
            imgModel.imageDom.attr("src", imgModel.imgPrefix + imgModel.defaultPath + imgModel.imgSuffix);
            imgModel.inputDom.val("");

            imgModel.btnDelArea.hide();
            imgModel.btnUploadArea.show();
            imgModel.fileDom.show();
            imgModel.btnProcessArea.hide();
            imgModel.status = currentStatus;
        } else if (currentStatus == 1) {
            //有照片状态
            //用old还是return 
            if (imgModel.returnPath != null && imgModel.returnPath != '') { 
                imgModel.inputDom.val(imgModel.returnPath);
            } else {
                imgModel.inputDom.val(imgModel.oldPath);
            }
            imgModel.btnUploadArea.hide();
            imgModel.btnDelArea.show();
            imgModel.fileDom.hide();
            imgModel.btnProcessArea.hide();
            imgModel.status = currentStatus;
        } else if (currentStatus == 2) {
            //照片上传中状态
            imgModel.btnUploadArea.hide();
            imgModel.btnDelArea.hide();
            imgModel.fileDom.hide();
            imgModel.btnProcessArea.show();
            imgModel.status = currentStatus;
        } else if (currentStatus == 3) {
            //照片上传 失败 状态
            imgModel.btnDelArea.hide();
            imgModel.btnUploadArea.show();
            imgModel.fileDom.show();
            imgModel.btnProcessArea.hide();
            imgModel.status = currentStatus;
        } else if (currentStatus == 4) {
            //切换到重置状态
            if (imgModel.oldPath != null && imgModel.oldPath != "") {
                //有照片状态
                imgModel.inputDom.val(imgModel.oldPath);
                imgModel.imageDom.attr("src", imgModel.imgPrefix + imgModel.oldPath + imgModel.imgSuffix);
                switchBtnArea(imgModel, 1);
            } else {
                switchBtnArea(imgModel, 0);
            }
        }
    };
 

    //-------------------操作事件管理--end---------------------

    function uploadImg(_imgModel) {
        if (checkUploadParam(_imgModel)) {
            doUploadImg(_imgModel);
        }
    }

    // 上传前检验参数是否完整
    function checkUploadParam(_imgModel) {
        if (_imgModel.fileDom.val() != null && _imgModel.fileDom.val() != "") {
//            alert(_imgModel.fileDom.val());
            //检验格式是否正确
            var fileObj = _imgModel.fileDom[0].files[0];
            var fileSize = fileObj.size;
            var fileName = fileObj.name;
            var reg = /.+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$/;
            if (reg.test(fileName)) {
                if (_imgModel.maxSize * 1024 * 1024 < fileSize) {
                    console.log("尺寸过大");
                    return false;
                }
            } else {
                console.log("文件类型错误");
                return false;
            }
        } else {
            _imgModel.fileDom.tips({
                side: 3,
                msg: "请选择图片！",
                bg: '#FF5080',
                time: 3
            });
            return false;
        }
        return true;
    }

    //显示原图
    function preview(_imgModel) {
//        _imgModel.previewImgDom.attr("src", _imgModel.imageDom.attr("src"));//设置#bigimg元素的src属性
        /*获取当前点击图片的真实大小，并显示弹出层及大图*/
        _imgModel.previewImgDom.attr("src", _imgModel.imageDom.attr("src")).load(function () {
            var windowW = $(window).width();//获取当前窗口宽度
            var windowH = $(window).height();//获取当前窗口高度
            var realWidth = this.width * 1.2;//获取图片真实宽度
            var realHeight = this.height * 1.2;//获取图片真实高度
            // alert(windowW+","+windowH+","+realWidth+","+realHeight);
            var imgWidth, imgHeight;
            var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放
            if (realHeight > windowH * scale) {//判断图片高度
                imgHeight = windowH * scale;//如大于窗口高度，图片高度进行缩放
                imgWidth = imgHeight / realHeight * realWidth;//等比例缩放宽度
                if (imgWidth > windowW * scale) {//如宽度扔大于窗口宽度
                    imgWidth = windowW * scale;//再对宽度进行缩放
                }
            } else if (realWidth > windowW * scale) {//如图片高度合适，判断图片宽度
                imgWidth = windowW * scale;//如大于窗口宽度，图片宽度进行缩放
                imgHeight = imgWidth / realWidth * realHeight;//等比例缩放高度
            } else {//如果图片真实高度和宽度都符合要求，高宽不变
                imgWidth = realWidth;
                imgHeight = realHeight;
            }
            _imgModel.previewImgDom.css("width", imgWidth);//以最终的宽度对图片缩放 
            var w = (windowW - imgWidth) / 2;//计算图片与窗口左边距
            var h = (windowH - imgHeight) / 2;//计算图片与窗口上边距
            _imgModel.previewImgDom.css({"top": h, "left": w});//设置#innerdiv的top和left属性
            _imgModel.previewOutterdiv.fadeIn("fast");//淡入显示#outerdiv及.pimg
        });
        _imgModel.previewOutterdiv.click(function () {//再次点击淡出消失弹出层
            $(this).fadeOut("fast");
        });
    }
    
    
    /**
     * 图片上传
     * @param scopeId 保存ID
     * @param type    1:COLUMN_FILEDIR;2:WEEK_BILL_PHOTO; 3:SCHOOL_FILEDIR;4:SCHOOLPRODUCT_FILEDIR;7:BUSINESS_LICENSE_FILEDIR;
     *                   11:VIDEO_FILE;12:COVER_PHOTO;13:TRANSFER_FILEDIR; 14:CLASS_PHOTO;15:FEATURES_FILE;
     */
    function doUploadImg(_imgModel) {
        var formData = new FormData();
        formData.append('uploadType', _imgModel.uploadType);
//        console.log(_imgModel);
        formData.append('file', _imgModel.fileDom[0].files[0]);

        if (formData != null) {
            var url = basePath  + "yourAction";
            switchBtnArea(_imgModel, 2);
            $.ajax({
                url: url,
                type: 'POST',
                cache: false,
                data: formData,
                dataType:"json",
                processData: false,
                contentType: false,
                success: function (data) {
                    var filename = data.filename;//获取图片名称
                    if (filename != null && filename != "") {
                        //上传成功,设置图片路径
                        _imgModel.imageDom.attr("src", _imgModel.imgPrefix + filename + _imgModel.imgSuffix);
                        _imgModel.returnPath = filename; 
                        _imgModel.inputDom.val(filename);
                        switchBtnArea(_imgModel, 1);
                        //并且切换状态
                    } else {
                        if (data.msg == "FORMAT") {
                            layer.alert("上传失败格式不正确，请检查！");
                        } else {
                            layer.alert("上传失败，请重试！");
                        }
                        switchBtnArea(_imgModel, 3);
                    }
                },
                error: function () {
                    switchBtnArea(_imgModel, 3);
                    alert("图片上传异常");
                    layer.msg("图片上传异常");
                }
            }); 
        }
    }
}

var uploadImgPlugin = new UploadImgPlugin();
function resetUploadPluginImg() {
    uploadImgPlugin.resetUpLoad();
}