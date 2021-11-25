import 'package:flutter/material.dart';

//Flutter中正是通过这种方式封装了很多动画，
// 如：FadeTransition、ScaleTransition、SizeTransition等，很多时候都可以复用这些预置的过渡类。
class GrowTransition extends StatelessWidget {
  final Widget? child;
  final Animation<double> animation;

  GrowTransition({required this.animation,
    this.child});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: AnimatedBuilder(
        animation: animation,
        builder: (BuildContext context, child) {
          return Container(
            height: animation.value,
            width: animation.value,
            child: child,
          );
        },
        child: child,
      ),
    );
  }
}