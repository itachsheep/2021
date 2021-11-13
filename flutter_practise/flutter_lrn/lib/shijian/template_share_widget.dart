

import 'package:flutter/material.dart';

class ShareDataWidget extends InheritedWidget {
  final int data;//需要在子树中共享的数据，保存点击次数

  const ShareDataWidget(this.data,Widget child, {Key? key,}) : super(key: key,child: child);

  //定义一个便捷方法，方便子树中的widget获取共享数据
  static ShareDataWidget? of(BuildContext ctx) {
    return ctx.dependOnInheritedWidgetOfExactType<ShareDataWidget>();
  }

  @override
  bool updateShouldNotify(ShareDataWidget oldWidget) {
    return oldWidget.data != data;
  }
}