

import 'package:flutter/material.dart';
import 'package:flutter_lrn/template/template_widget.dart';
import 'cheker_boarder.dart';

class CustomPaintRoute extends TemplateRoute {
  CustomPaintRoute(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return Center(
      child: Column(
        children: [
          CheckerBoarder(),

          Padding(padding: EdgeInsets.only(top: 10.0)),

          //添加一个刷新button
          ElevatedButton(onPressed: () {}, child: Text("刷新"))
        ],
      ),
    );
  }

}