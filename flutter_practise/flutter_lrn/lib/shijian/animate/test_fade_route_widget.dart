import 'package:flutter/material.dart';
import 'package:flutter_lrn/shijian/animate/test_fade_route.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class TestFadeRouteWidget extends TemplateRoute {
  TestFadeRouteWidget(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return Center(
        child: Column(
      children: [
        ElevatedButton(
            onPressed: () {
              Navigator.push(context, MaterialPageRoute(builder: (ctx) {
                return NextRoutePage("普通路由动画");
              }));
            },
            child: Text("普通路由动画")),
        ElevatedButton(
            onPressed: () {
              Navigator.push(
                  context,
                  MyFadeRoute(
                      builder: (ctx) {
                        return NextRoutePage("自定义路由动画");
                      },
                      barrierColor: Colors.red,
                      barrierLabel: "barrierLabel"));
            },
            child: Text("自定义路由动画")),
      ],
    ));
  }
}

class NextRoutePage extends TemplateRoute {
  NextRoutePage(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return Center(
      child: Text("目标测试页面"),
    );
  }
}
