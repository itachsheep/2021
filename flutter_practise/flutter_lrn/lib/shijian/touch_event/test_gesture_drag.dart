import 'package:flutter/material.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class TestDragWidget extends TemplateRoute {
  TestDragWidget(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) => _Drag();
}

class _Drag extends StatefulWidget {
  @override
  _DragState createState() => _DragState();
}

class _DragState extends State<_Drag> with SingleTickerProviderStateMixin {
  double _top = 0.0; //距顶部的偏移
  double _left = 0.0; //距左边的偏移
  double _width = 200.0;

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: <Widget>[
        const Positioned(top: 20, left: 40, child: Text("哈哈")),

        Positioned(
          top: _top + 50,
          left: 100,
          child: GestureDetector(
            child: CircleAvatar(child: Text("B")),
            //垂直方向拖动事件
            onVerticalDragUpdate: (DragUpdateDetails details) {
              setState(() {
                _top += details.delta.dy;
              });
            },
          ),
        ),

        Positioned(
          top: _top,
          left: _left,
          child: GestureDetector(
            child: CircleAvatar(child: Text("A")),
            //手指按下时会触发此回调
            onPanDown: (DragDownDetails e) {
              //打印手指按下的位置(相对于屏幕)
              print("用户手指按下：${e.globalPosition}");
            },
            //手指滑动时会触发此回调
            onPanUpdate: (DragUpdateDetails e) {
              //用户手指滑动时，更新偏移，重新构建
              setState(() {
                _left += e.delta.dx;
                _top += e.delta.dy;
              });
            },
            onPanEnd: (DragEndDetails e) {
              //打印滑动结束时在x、y轴上的速度
              print("用户手指松开: + ${e.velocity}");
            },
          ),
        ),

        Center(
          child: GestureDetector(
            //指定宽度，高度自适应
            child: Image.asset("pics/avatar.png", width: _width),
            onScaleUpdate: (ScaleUpdateDetails details) {
              setState(() {
                //缩放倍数在0.8到10倍之间
                _width=200*details.scale.clamp(.8, 10.0);
              });
            },
          ),
        ),
      ],
    );
  }
}
