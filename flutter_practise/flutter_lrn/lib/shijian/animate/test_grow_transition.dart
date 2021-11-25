
import 'package:flutter/material.dart';
import 'package:flutter_lrn/log.dart';
import 'package:flutter_lrn/shijian/animate/growth_transition.dart';
import 'package:flutter_lrn/template/template_widget.dart';

String tag = "TestGrowTransition";
class TestGrowTransition extends TemplateRoute {
  //String tag = "TestGrowTransition";
  TestGrowTransition(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return TestBody();
  }

}

class TestBody extends StatefulWidget {
   TestBody({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() => TestBodyState();
}

class TestBodyState extends State<TestBody>  with SingleTickerProviderStateMixin {
  late Animation<double> animation;
  late AnimationController animationController;

  @override
  void initState() {
    super.initState();
    animationController =
        AnimationController(duration: Duration(seconds: 4), vsync: this);
    animation =
        Tween(begin: 0.0, end: 300.0).animate(animationController);
    animation.addStatusListener((status) {
      LogUtils.d(tag, "addStatusListener: $status");
      if (status == AnimationStatus.completed) {
        //动画执行结束时反向执行动画
        animationController.reverse();

      } else if (status == AnimationStatus.dismissed) {
        //动画恢复到初始状态时执行动画（正向）
        animationController.forward();
      }
    });
    animationController.forward();
  }

  @override
  void dispose() {
    super.dispose();
    animationController.dispose();
  }
  @override
  Widget build(BuildContext context) {
    return GrowTransition(
      animation:animation,
      child:  Image.asset("pics/bg.jpeg"));
  }

}

