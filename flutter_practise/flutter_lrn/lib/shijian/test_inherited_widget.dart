

import 'package:flutter/material.dart';
import 'package:flutter_lrn/shijian/template_widget.dart';

class TestInheritedWidget extends TemplateRoute {
  @override
  Widget getBody() {
    return Text("数据共享");
  }

  @override
  Widget getTitle() {
    return Center(
      child: Text("body----"),
    );
  }

}