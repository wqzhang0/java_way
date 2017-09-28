<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wqzhang
  Date: 2017/9/27
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>

    <base href="<%=basePath%>">
    <title>BootStrap Tree Demo</title>
    <script type="text/javascript" src="plugins/jquery.js"></script>
    <script type="text/javascript" src="plugins/bootstrap/bootstrap-treeview.js"></script>
    <%--http://www.runoob.com/try/demo_source/bootstrap3-glyph-icons.htm 小图标示例--%>
    <link href="css/bootstrap.css" rel="stylesheet">

    <script type="text/javascript">
        function getTree() {
            var tree = [
                {

                    id: '1',
                    text: "系统配置",
//                    icon:"glyphicon glyphicon-circle-arrow-up",
//                    selectedIcon:"glyphicon glyphicon-cog",
                    nodes: [
                        {
                            id: '11',
                            text: "系统配置",
                            nodes: [
                                {
                                    text: "查看"
                                },
                                {
                                    text: "新增"
                                },
                                {
                                    text: "修改"
                                },
                                {
                                    text: "删除"
                                }
                            ]

                        },
                        {
                            id: '12',
                            text: "角色管理"

                        },
                        {
                            id: '13',
                            text: "用户管理"
                        },
                        {
                            id: '14',
                            text: "权限分配"
                        }
                    ]
                },
                {
                    id: '2',
                    text: "Parent 2"
                },
                {
                    id: '3',
                    text: "Parent 3"
                },
                {
                    id: '4',
                    text: "Parent 4"
                },
                {
                    id: '5',
                    text: "Parent 5"
                }
            ];
            return tree;
        }


        function getTree2() {
            <%--var tmpData = "${menuList}";--%>
            <%--//            alert(tmpData);--%>

            <%--return tmpData;--%>
        }

        //        参数名称	参数类型	默认值	描述
        //        data	Array of Objects	无	列表树上显示的数据。
        //        backColor	String	所有合法的颜色值，Default: inherits from Bootstrap.css。	设置所有列表树节点的背景颜色。
        //        borderColor	String	所有合法的颜色值，Default: inherits from Bootstrap.css。	设置列表树容器的边框颜色，如果不想要边框可以设置showBorder属性为false。
        //        checkedIcon	String：class名称	Bootstrap Glyphicons定义的 "glyphicon glyphicon-check"	设置处于checked状态的复选框图标。
        //        collapseIcon	String：class名称	Bootstrap Glyphicons定义的 "glyphicon glyphicon-minus"	设置列表树可收缩节点的图标。
        //        color	String	所有合法的颜色值，Default: inherits from Bootstrap.css。	设置列表树所有节点的前景色。
        //        emptyIcon	String：class名称	Bootstrap Glyphicons定义的"glyphicon"。	设置列表树中没有子节点的节点的图标。
        //        enableLinks	Boolean	false	是否使用当前节点的文本作为超链接。超链接的href值必须在每个节点的data结构中给出。
        //        expandIcon	String：class名称	Bootstrap Glyphicons定义的 "glyphicon glyphicon-plus"	设置列表树可展开节点的图标。
        //        highlightSearchResults	Boolean	true	是否高亮搜索结果。
        //        highlightSelected	Boolean	true	当选择节点时是否高亮显示。
        //        onhoverColor	String	所有合法的颜色值， Default: '#F5F5F5'。	设置列表树的节点在用户鼠标滑过时的背景颜色。
        //        levels	Integer	Default: 2	设置继承树默认展开的级别。
        //        multiSelect	Boolean	false	是否可以同时选择多个节点。
        //        nodeIcon	String：class名称	Bootstrap Glyphicons定义的 "glyphicon glyphicon-stop"	设置所有列表树节点上的默认图标。
        //        selectedIcon	String：class名称	Bootstrap Glyphicons定义的 "glyphicon glyphicon-stop"	设置所有被选择的节点上的默认图标。
        //        searchResultBackColor	String	所有合法的颜色值， Default: undefined, inherits。	设置搜索结果节点的背景颜色。
        //        searchResultColor	String	所有合法的颜色值， Default: '#D9534F'	设置搜索结果节点的前景颜色。
        //        selectedBackColor	String	所有合法的颜色值， Default: '#428bca'	设置被选择节点的背景颜色。
        //        selectedColor	String	所有合法的颜色值， Default: '#FFFFFF'。	设置列表树选择节点的背景颜色。
        //        showBorder	Boolean	true	是否在节点上显示边框。
        //        showCheckbox	Boolean	false	是否在节点上显示checkboxes。
        //        showIcon	Boolean	true	是否显示节点图标。
        //        showTags	Boolean	false	是否在每个节点右边显示tags标签。tag值必须在每个列表树的data结构中给出。
        //        uncheckedIcon	String：class名称	Bootstrap Glyphicons定义的 "glyphicon glyphicon-unchecked"	设置图标为未选择状态的checkbox图标。
        $(function () {
            $('#tree').treeview({
                data: getTree(),
                levels: 5,
                //背景颜色
                backColor: '#000000',
                //字体颜色
                color: '#6CCAB8',
                onhoverColor: "#F3976C",
                //是否展示边框
                showBorder: true,
                //是否展示选择框
                showCheckbox: true,
                //是否展示图标
                showIcon: true,
                state: {
                    checked: true,
                    disabled: true,
                    expanded: true,
                    selected: true
                },

                onNodeChecked: function (event, node) {
                    var childrens = node.nodes; //是否有子节点
                    if (childrens) {//有后代节点
                        checkedChild(childrens);//选中所有的后代节点
                        $('#tree').treeview('expandNode', [node.nodeId, {levels: 4, silent: true}]);
                    }
                    //选中展开
                    var parentNode = $('#tree').treeview('getParent', node); //父节点
                    if (parentNode.state) {//选中一个节点其父节点以上均被选中
                        checkedParent(parentNode);
                    }
                },
                onNodeUnchecked: function (event, node) {
                    var childrens = node.nodes;
                    if (childrens) {//父节点被取消选中子节点默认取消
                        delChildrens(childrens);
                    } //兄弟节点都被取消则取消父节点
                    var siblingsNode = $('#tree').treeview('getSiblings', node);
                    delSiblings(siblingsNode);
                },
                onNodeUnselected: function (event, data) {
//                    alert("onNodeUnselected");

//                    if (data.state.selected == false) {
//                        expandByNodeId(data.nodeId);
//                    } else {
//                        collapseByNodeId(data.nodeId);
//                        data.state.checked= false;
//                    }
                },
                onNodeSelected: function (event, data) {
//                    alert("onNodeSelected");
//                    if (data.state.selected == false) {
//                        expandByNodeId(data.nodeId);
//                    } else {
//                        collapseByNodeId(data.nodeId);
////                        data.state.checked= false;
//                    }
                }
//                    //                    alert(data.text + "|" + data.nodeId + "|" + data.id);
//                    //判断是否是父类节点 如果是父类节点，  动作为全选或者 全不选
//                    //判断是否被选择  如果没被选择，赋值选择
//                    //并且检查父节点  如果子节点无被选择，取消父类的选择 递归查找到顶点
//                    alert(data.state.checked);
//                    if (data.state.checked == false) {
//                        chooseByNodeId(data.nodeId);
//                    } else {
//                        collapseByNodeId(data.nodeId);
//                          data.state.checked= false;
//                    }
//                      $("#tree").treeview("unselectNode",[data.nodeId,{slient:true}]);
//                }
            });


            $('#tree2').treeview({
                data: ${menuList},
                levels: 5,
                //背景颜色
                backColor: '#000000',
                //字体颜色
                color: '#6CCAB8',
                onhoverColor: "#F3976C",
                //是否展示边框
                showBorder: true,
                //是否展示选择框
                showCheckbox: true,
                //是否展示图标
                showIcon: true,
                state: {
                    checked: true,
                    disabled: true,
                    expanded: true,
                    selected: true
                },

                onNodeChecked: function (event, node) {
                    var childrens = node.nodes; //是否有子节点
                    if (childrens) {//有后代节点
                        checkedChild(childrens);//选中所有的后代节点
                        $('#tree').treeview('expandNode', [node.nodeId, {levels: 4, silent: true}]);
                    }
                    //选中展开
                    var parentNode = $('#tree').treeview('getParent', node); //父节点
                    if (parentNode.state) {//选中一个节点其父节点以上均被选中
                        checkedParent(parentNode);
                    }
                },
                onNodeUnchecked: function (event, node) {
                    var childrens = node.nodes;
                    if (childrens) {//父节点被取消选中子节点默认取消
                        delChildrens(childrens);
                    } //兄弟节点都被取消则取消父节点
                    var siblingsNode = $('#tree').treeview('getSiblings', node);
                    delSiblings(siblingsNode);
                },
                onNodeUnselected: function (event, data) {
                },
                onNodeSelected: function (event, data) {
                }
            });

        });

        //选择节点
        function expandByNodeId(_nodeId) {
            $('#tree').treeview('expandNode', [_nodeId, {silent: true}]);
//            $("#tree").treeview("checkNode", [_nodeId, {slient: true}]);
        }
        //取消选择节点
        function collapseByNodeId(_nodeId) {
            $("#tree").treeview("collapseNode", [_nodeId, {slient: true, ignoreChildren: false}]);
        }
        //检查是否有子节点被选择
        function havChildChecked(_nodeId) {
            return false;
        }

        function checkedChild(childrens) {
            childrens.forEach(function (el) {
                $('#tree').treeview('checkNode', [el.nodeId, {silent: true}]);
                if (el.nodes) {
                    checkedChild(el.nodes)
                }
            })
        }
        function checkedParent(parentNode) {
            parentNode.state.checked = true;
            var selfNode = parentNode;
            var parentNode = $('#tree').treeview('getParent', selfNode); //父节点
            if (parentNode.state) {
                checkedParent(parentNode)
            }
        }
        function delChildrens(childrens) {
            childrens.forEach(function (el) {
                $('#tree').treeview('uncheckNode', [el.nodeId, {silent: true}]);
                if (el.nodes) {
                    delChildrens(el.nodes)
                }
            })
        }
        function delSiblings(siblingsNode) {
            var isAllChecked = false;
            siblingsNode.forEach(function (el) {
                if (el.state.checked) {
                    isAllChecked = true;
                }
            })
            if (!isAllChecked) {
                var parentNode = $('#tree').treeview('getParent', siblingsNode); //父节点
                if (parentNode.state) {
                    parentNode.state.checked = false;
                    var siblingsNode = $('#tree').treeview('getSiblings', parentNode);
                    delSiblings(siblingsNode);
                }
            }
        }

    </script>
</head>
<body>
<center>
    <table>

        <tr>
            <td><h3>固定数据菜单 用来比较格式</h3></td>
            <td style="padding-left: 50px;"><h3>数据库中菜单Demo</h3></td>
        </tr>
        <tr>
            <td>
                <div id="tree" style="width:300px;padding-left: 20px;padding-top: 10px; float:left;"></div>
            </td>
            <td style="padding-left: 50px;">
                <div id="tree2" style="width:300px;padding-left: 20px;padding-top: 10px;float:left;"></div>
            </td>

        </tr>
    </table>
</center>
</body>
</html>
