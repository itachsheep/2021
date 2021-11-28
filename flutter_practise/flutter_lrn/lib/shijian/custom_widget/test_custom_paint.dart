

import 'package:flutter/material.dart';
import 'package:flutter_lrn/shijian/custom_widget/cheker_boarder.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class CustomPaintRoute extends TemplateRoute {
  CustomPaintRoute(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return CheckerBoarder();
  }

}