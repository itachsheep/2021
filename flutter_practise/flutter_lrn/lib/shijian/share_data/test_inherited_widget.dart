

import 'package:flutter/material.dart';
import 'package:flutter_lrn/log.dart';
import 'package:flutter_lrn/template/template_share_widget.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class TestInheritedWidget extends TemplateRoute {
  TestInheritedWidget(String name,{Key? key}) : super(key: key,title:name);

  @override
  Widget getBody(BuildContext context) {
    return InheritedWidgetTestRoute();
  }

  // @override
  // Widget getTitle() {
  //   return Text(this.title);
  // }
}

class InheritedWidgetTestRoute extends StatefulWidget {
    @override
  State<StatefulWidget> createState() => _InheritedWidgetTestRouteState();
}

class _InheritedWidgetTestRouteState extends State<InheritedWidgetTestRoute> {
  int count = 0;

  @override
  Widget build(BuildContext context) {
    return  Center(
      child: ShareDataWidget( //使用ShareDataWidget
        count,
        Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.only(bottom: 20.0),
              child: _TestWidget(),//子widget中依赖ShareDataWidget
            ),
            RaisedButton(
              child: Text("Increment"),
              //每点击一次，将count自增，然后重新build,ShareDataWidget的data将被更新
              onPressed: () => setState(() => ++count),
            )
          ],
        ),
      ),
    );
  }
}

class _TestWidget extends StatefulWidget {
  @override
  __TestWidgetState createState() => __TestWidgetState();
}

class __TestWidgetState extends State<_TestWidget> {
  @override
  Widget build(BuildContext context) {
    //使用InheritedWidget中的共享数据
    return Text(ShareDataWidget.of(context)!.data.toString());
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    //父或祖先widget中的InheritedWidget改变(updateShouldNotify返回true)时会被调用。
    //如果build中没有依赖InheritedWidget，则此回调不会被调用。
    LogUtils.d("","Dependencies change");
  }
}