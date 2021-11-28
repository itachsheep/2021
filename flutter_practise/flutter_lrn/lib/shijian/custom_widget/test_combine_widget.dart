
import 'package:flutter/material.dart';
import 'package:flutter_lrn/log.dart';
import 'package:flutter_lrn/shijian/custom_widget/gradient_button.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class CustomCombineWidgetRoute extends TemplateRoute {
  CustomCombineWidgetRoute(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return GradientButtonRoute();
  }
}

class GradientButtonRoute extends StatefulWidget {
  @override
  _GradientButtonRouteState createState() => _GradientButtonRouteState();
}

class _GradientButtonRouteState extends State<GradientButtonRoute> {

  onTap() {
    LogUtils.dd("onTap: ");
  }


  @override
  Widget build(BuildContext context) {
    return Container(
      alignment: Alignment.center,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          GradientButton(
              colors: [Colors.orange,Colors.red],
              width: 300,
              height: 50,
              onPressed: (){},
              borderRadius: BorderRadius.all(Radius.circular(15.0)),
              child: Text("Submit")),

          const Padding(padding: EdgeInsets.only(top: 20)),

          GradientButton(
              colors: [Colors.lightGreen, Colors.green.shade700],
              width: 200,
              height: 70,
              onPressed: (){},
              borderRadius: BorderRadius.all(Radius.circular(10.0)),
              child: Text("Submit")),
        ],
      ),
    );
  }
}