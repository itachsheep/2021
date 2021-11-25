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

  late Animation<double> animationNoRefresh;
  late AnimationController animationControllerNoRefresh;

  @override
  void initState() {
    super.initState();
    /*animationController = AnimationController(
        duration: Duration(seconds:  2),
        vsync: this
    );
    animation = CurvedAnimation(parent: animationController, curve: Curves.bounceIn);
    //匀速
    //图片宽高从0变到300
    animation = Tween(begin:  0.0, end: 300.0).animate(animation)
        ..addListener(() {
          setState(() {
            //调用了setState()，所以每次动画生成一个新的数字时，当前帧被标记为脏(dirty)，
            // 这会导致widget的build()方法再次被调用，而在build()中，
            // 改变Image的宽高，因为它的高度和宽度现在使用的是animation.value ，
            // 所以就会逐渐放大。
          });
    });
    animationController.forward();

*/
    animationControllerNoRefresh =
        AnimationController(duration: Duration(seconds: 4), vsync: this);
    animationNoRefresh =
        Tween(begin: 0.0, end: 300.0).animate(animationControllerNoRefresh);
    animationControllerNoRefresh.forward();
  }

  @override
  void dispose() {
    animationController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          /*Image.asset(
            "pics/bg.jpeg",
            width: animation.value,
            height: animation.value,
          ),*/

          // const Padding(padding: EdgeInsets.only(top: 10.0)),
          // AnimatedImage(animation: animationNoRefresh),

          AnimatedBuilder(
              animation: animationNoRefresh,
              child: Image.asset("pics/bg.jpeg"),
              builder: (ctx,child) {
                return Center(
                    child: Container(
                    height: animationNoRefresh.value,
                    width: animationNoRefresh.value,
                    child: child,)
                );
              }
          ),

        ],
      ),
    );
  }
}

class AnimatedImage extends AnimatedWidget {
  AnimatedImage({
    Key? key,
    required Animation<double> animation,
  }) : super(key: key, listenable: animation);

  Widget build(BuildContext context) {
    //todo:hahah
    final animation = listenable as Animation<double>;
    return Image.asset(
      "pics/bg.jpeg",
      width: animation.value,
      height: animation.value,
    );
  }
}
