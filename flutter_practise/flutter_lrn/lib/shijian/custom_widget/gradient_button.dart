import 'package:flutter/material.dart';

//1，背景支持渐变色
//2，手指按下时有涟漪效果
//3，可以支持圆角
class GradientButton extends StatelessWidget {
  // 渐变色数组
  final List<Color> colors;

  // 按钮宽高
  final double width;
  final double height;

  final Widget child;
  final BorderRadius borderRadius;

  //点击回调
  final GestureTapCallback onPressed;

  GradientButton({
    required this.colors,
    required this.width,
    required this.height,
    required this.onPressed,
    required this.borderRadius,
    required this.child,
  });

  @override
  Widget build(BuildContext context) {
   // ThemeData themeData = Theme.of(context);

    //1，我们DecoratedBox可以支持背景色渐变和圆角，
    // InkWell在手指按下有涟漪效果，所以我们可以通过组合DecoratedBox和InkWell
    // 来实现GradientButton
    return DecoratedBox(
      decoration: BoxDecoration(
          gradient: LinearGradient(colors: colors), borderRadius: borderRadius),

      child: Material(
        type: MaterialType.transparency,
        child: InkWell(
          splashColor: colors.last,
          highlightColor: Colors.transparent,
          borderRadius: borderRadius,
          onTap: onPressed,
          child: ConstrainedBox(
            constraints: BoxConstraints.tightFor(height: height, width: width),
            child: Center(
              child: Padding(
                padding: const EdgeInsets.all(8.0),
                child:  DefaultTextStyle(
                  style: TextStyle(fontWeight: FontWeight.bold),
                  child: child,
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
