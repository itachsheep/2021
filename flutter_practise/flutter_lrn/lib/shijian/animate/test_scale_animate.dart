
import 'package:flutter/material.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class TestScaleAnimate extends TemplateRoute {
  TestScaleAnimate(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) => ScaleAnimationRoute();

}

class ScaleAnimationRoute extends StatefulWidget {
  @override
  _ScaleAnimationRouteState createState() => _ScaleAnimationRouteState();
}

class _ScaleAnimationRouteState extends State<ScaleAnimationRoute>
    with SingleTickerProviderStateMixin {

  late Animation<double> animation;
  late AnimationController animationController;

  @override
  void initState() {
    super.initState();
    animationController = AnimationController(
        duration: Duration(seconds:  2),
        vsync: this
    );

    //匀速
    //图片宽高从0变到300
    animation = Tween(begin:  0.0, end: 300.0).animate(animationController)
        ..addListener(() {
          setState(() {

          });
    });

    animationController.forward();
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Image.asset(
          "pics/bg.jpeg",
        width: animation.value,
        height: animation.value,
      ),
    );
  }



}