import 'package:flutter/material.dart';
import 'package:flutter_lrn/shijian/animate/stragger_animation.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class TestStraggerRoute extends TemplateRoute {
  TestStraggerRoute(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return StaggerRoute();
  }
}

class StaggerRoute extends StatefulWidget {
  @override
  _StaggerRouteState createState() => _StaggerRouteState();
}

class _StaggerRouteState extends State<StaggerRoute>
    with TickerProviderStateMixin {
  late AnimationController _controller;

  @override
  void initState() {
    super.initState();
    _controller = AnimationController(
        duration: Duration(milliseconds: 2000), vsync: this);
  }

  _playAnimation() async {
    try {
      //先正向执行动画
      await _controller.forward().orCancel;
      //再反向执行动画
      await _controller.reverse().orCancel;
    } on TickerCanceled {
      // the animation got canceled, probably because we were disposed
    }
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        children: [
          ElevatedButton(
            onPressed: _playAnimation,
            child: Text("start animation"),
          ),
          Container(
            width: 300,
            height: 300,
            /*decoration: BoxDecoration(
              color: Colors.black.withOpacity(0.1),
              border: Border.all(
                color: Colors.black.withOpacity(0.5),
              ),
            ),*/
            child: StaggerAnimation(
              controller: _controller,
            ),
          ),
        ],
      ),
    );
  }
}
