
import 'package:flutter/cupertino.dart';
import 'package:flutter_lrn/log.dart';


class MyFadeRoute extends PageRoute {
  final WidgetBuilder builder;
  final String tag = "FadeRoute";
  @override
  final Duration transitionDuration;

  @override
  final bool opaque;
  @override
  final bool barrierDismissible;

  @override
  final Color barrierColor;

  @override
  final String barrierLabel;

  @override
  final bool maintainState;


  MyFadeRoute({
    required this.builder,
    this.transitionDuration = const Duration(milliseconds: 300),
    this.opaque = true,
    this.barrierDismissible = false,
    required this.barrierColor,
    required this.barrierLabel,
    this.maintainState = true,
});

  @override
  Widget buildPage(BuildContext context, Animation<double> animation,
      Animation<double> secondaryAnimation) {
    LogUtils.d(tag,"buildPage ---->");
    return builder(context);
  }

  @override
  Widget buildTransitions(BuildContext context, Animation<double> animation, Animation<double> secondaryAnimation, Widget child) {

    LogUtils.d(tag,"buildTransitions ---->");
    return FadeTransition(opacity: animation,
    child: builder(context),);
  }
}