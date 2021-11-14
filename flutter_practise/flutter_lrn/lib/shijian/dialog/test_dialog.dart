import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_lrn/log.dart';
import 'package:flutter_lrn/shijian/dialog/test_dialog_state_manager.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class TestDialog extends TemplateRoute {
  TestDialog(String name) : super(title: name);


  @override
  Widget getBody(BuildContext context) {
    return Column(
      children: [
        ElevatedButton(
          child: Text("普通对话框"),
          onPressed: () async {
            //弹出对话框并等待其关闭
            bool? delete = await showDeleteConfirmDialog1(context);
            if (delete == null) {
              print("取消删除");
            } else {
              print("已确认删除");
              //... 删除文件
            }
          },
        ),
        ElevatedButton(
          child: Text("simple列表框"),
          onPressed: () => changeLanguage(context),
        ),

        ElevatedButton(
            onPressed: () => showListDialog(context),
            child: Text("listView对话框")
        ),

        ElevatedButton(
            onPressed: () => showListDialog2(context),
            child: Text("listView对话框2")
        ),

        ElevatedButton(
            onPressed: (){
              showCustomDialog(context: context, builder: (context){
                return buildAlertDialog(context);
              });
            },
            child: Text('customDialog自定义')
        ),

        ElevatedButton(
          child: Text("话框3（复选框可点击）"),
          onPressed: () async {
            //弹出删除确认对话框，等待用户确认
            bool? deleteTree = await showDeleteConfirmDialog3(context);
            if (deleteTree == null) {
              print("取消删除");
            } else {
              print("同时删除子目录: $deleteTree");
            }
          },
        ),

        ElevatedButton(
            onPressed: (){showCheckBoxDialogWithSimpleWay(context);},
            child: Text("复选框-精妙方式")),
      ],
    );
  }

  Future<void> showCheckBoxDialogWithSimpleWay(BuildContext context) async {
    bool _withTree = false; //记录复选框是否选中
    bool? res = await showDialog<bool>(
        context: context,
        builder: (ctx){
          return AlertDialog(
            title: Text("提示"),
            content: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              mainAxisSize: MainAxisSize.min,
              children: [
                Text("您确定要删除当前文件吗?"),
                Row(
                  children: [
                    Text("同时删除子目录？"),
                    Builder(
                        builder: (ctx) {
                          return Checkbox(
                              value: _withTree,
                              onChanged: (bool? value){
                                (ctx as Element).markNeedsBuild();
                                _withTree = !_withTree;
                              });
                        }
                    ),
                  ],
                ),

                TextButton(
                  child: Text("退出"),
                  onPressed: () {
                    // 将选中状态返回
                    Navigator.of(context).pop(_withTree);
                  },
                ),
              ],
            ),
          );
        }
    );
    LogUtils.dd("showCheckBoxDialogWithSimpleWay res: $res");
  }


  Future<bool?> showDeleteConfirmDialog3(BuildContext context) {
    bool _withTree = false; //记录复选框是否选中
    return showDialog<bool>(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: Text("提示"),
          content: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisSize: MainAxisSize.min,
            children: <Widget>[
              Text("您确定要删除当前文件吗?"),
              Row(
                children: <Widget>[
                  Text("同时删除子目录？"),
                  DialogCheckbox(
                    value: _withTree, //默认不选中
                    onChanged: (bool? value) {
                      //更新选中状态
                      _withTree = !_withTree;
                    },
                  ),
                ],
              ),
            ],
          ),
          actions: <Widget>[
            TextButton(
              child: Text("取消"),
              onPressed: () => Navigator.of(context).pop(),
            ),
            TextButton(
              child: Text("删除"),
              onPressed: () {
                // 将选中状态返回
                Navigator.of(context).pop(_withTree);
              },
            ),
          ],
        );
      },
    );
  }

  Widget buildAlertDialog(BuildContext context) {
    return AlertDialog(
      title: Text("提示"),
      content: Text("您确定要删除当前文件吗?"),
      actions: <Widget>[
        TextButton(
          child: Text("取消"),
          onPressed: () => Navigator.of(context).pop(),
        ),
        TextButton(
          child: Text("删除"),
          onPressed: () {
            // 执行删除操作
            Navigator.of(context).pop(true);
          },
        ),
      ],
    );
  }

  Future<T?> showCustomDialog<T>({
    required BuildContext context,
    bool barrierDismissible = true,
    required WidgetBuilder builder,
    ThemeData? theme,
  }) {
    final ThemeData theme = Theme.of(context,/* shadowThemeOnly: true*/);
    return showGeneralDialog(
      context: context,
      pageBuilder: (BuildContext buildContext, Animation<double> animation,
          Animation<double> secondaryAnimation) {
        final Widget pageChild = Builder(builder: builder);
        return SafeArea(
          child: Builder(builder: (BuildContext context) {
            return theme != null
                ? Theme(data: theme, child: pageChild)
                : pageChild;
          }),
        );
      },
      barrierDismissible: barrierDismissible,
      barrierLabel: MaterialLocalizations.of(context).modalBarrierDismissLabel,
      barrierColor: Colors.black87, // 自定义遮罩颜色
      transitionDuration: const Duration(milliseconds: 150),
      transitionBuilder: _buildMaterialDialogTransitions,
    );
  }

  Widget _buildMaterialDialogTransitions(
      BuildContext context,
      Animation<double> animation,
      Animation<double> secondaryAnimation,
      Widget child) {
    // 使用缩放动画
    return ScaleTransition(
      scale: CurvedAnimation(
        parent: animation,
        curve: Curves.easeOut,
      ),
      child: child,
    );
  }

  Future<void> showListDialog2(BuildContext context) async {
    int? index = await showDialog<int>(
      context: context,
      builder: (context) {
        var child = getDialogChild();
        return UnconstrainedBox(
          constrainedAxis: Axis.vertical,
          child: ConstrainedBox(
            constraints: BoxConstraints(maxWidth: 280),
            child: Material(
              child: child,
              type: MaterialType.card,
            ),
          ),
        );
      },
    );
    LogUtils.dd("showListDialog : $index");
  }

  Widget getDialogChild() {
    return Column(
      children: [
        ListTile(title: Text("请选择")),
        Expanded(
            child: ListView.builder(
                itemBuilder: (context,index) {
                  return ListTile(
                    title: Text("item $index"),
                    onTap: (){
                      Navigator.pop(context, index);
                    },
                  );
                }
            )
        ),
      ],
    );
  }

  Future<void> showListDialog(BuildContext context) async {
    int? index = await showDialog<int>(
      context: context,
      builder: (context) {
        var child = getDialogChild();
        return Dialog(child: child);
      },
    );
    LogUtils.dd("showListDialog : $index");
  }

  Future<void> changeLanguage(BuildContext context) async {
    int? i = await showDialog<int>(
      context: context,
      builder: (context) {
        return SimpleDialog(
          title: const Text("请选择语言"),
          children: [
            SimpleDialogOption(
              onPressed: () {
                Navigator.pop(context, 1);
              },
              child: Padding(
                padding: const EdgeInsets.symmetric(vertical: 6),
                child: const Text('中文简体'),
              ),
            ),

            SimpleDialogOption(
              onPressed: () {
                Navigator.pop(context, 2);
              },
              child: Padding(
                padding: const EdgeInsets.symmetric(vertical: 6),
                child: const Text('美国英语'),
              ),
            ),

          ],
        );
      },
    );
    if (i != null) {
      print("选择了：${i == 1 ? "中文简体" : "美国英语"}");
    }
  }

  // 弹出对话框
  Future<bool?> showDeleteConfirmDialog1(BuildContext context) {
    return showDialog<bool>(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: Text("提示"),
          content: Text("您确定要删除当前文件吗?"),
          actions: <Widget>[
            TextButton(
              child: Text("取消"),
              onPressed: () => Navigator.of(context).pop(), // 关闭对话框
            ),
            TextButton(
              child: Text("删除"),
              onPressed: () {
                //关闭对话框并返回true
                Navigator.of(context).pop(true);
              },
            ),
          ],
        );
      },
    );
  }

}