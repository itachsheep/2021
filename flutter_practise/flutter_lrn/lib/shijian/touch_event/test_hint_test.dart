
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter_lrn/log.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class HintTestWidget extends TemplateRoute {
  HintTestWidget(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return Stack(
      children: [
        // wChild(1, 200),
        // wChild(2, 200),
        HitTestBlocker(child: wChild(1, 100),),
        HitTestBlocker(child: wChild(2, 100),)
      ],
    );
  }

  Widget wChild(int index, double size) {
    return Listener(
      // behavior: HitTestBehavior.opaque, // 放开此行，点击只会输出 2
      // behavior: HitTestBehavior.translucent, // 放开此行，点击会同时输出 2 和 1
      onPointerDown: (e) => print(index),
      child: Container(
        width: size,
        height: size,
        color: Colors.grey,
      ),
    );
  }

}

/// 点击后，控制台会同时输出 2 和 1，原理也很简单 ：
//
/// HitTestBlocker 的 hitTest 会返回 false，这可以保证 Stack 的所有的子节点都能参与命中测试；
/// HitTestBlocker 的 hitTest 中又会调用 hitTestChildren，
/// 所以 HitTestBlocker 的后代节点是有机会参与命中测试，所以Container上的事件会被正常触发。
class HitTestBlocker extends SingleChildRenderObjectWidget {
  HitTestBlocker({
    Key? key,
    this.up = true,
    this.down = false,
    this.self = false,
    Widget? child,
  }) : super(key: key, child: child);

  /// up 为 true 时 , `hitTest()` 将会一直返回 false.
  final bool up;

  /// down 为 true 时, 将不会调用 `hitTestChildren()`.
  final bool down;

  /// `hitTestSelf` 的返回值
  final bool self;

  @override
  RenderObject createRenderObject(BuildContext context) {
    return RenderHitTestBlocker(up: up, down: down, self: self);
  }

  @override
  void updateRenderObject(
      BuildContext context, RenderHitTestBlocker renderObject) {
    renderObject
      ..up = up
      ..down = down
      ..self = self;
  }
}

class RenderHitTestBlocker extends RenderProxyBox {
  RenderHitTestBlocker({this.up = true, this.down = true, this.self = true});

  bool up;
  bool down;
  bool self;

  @override
  bool hitTest(BoxHitTestResult result, {required Offset position}) {
    
    bool hitTestDownResult = false;

    if (!down) {
      hitTestDownResult = hitTestChildren(result, position: position);
    }

    bool pass =
        hitTestSelf(position) || (hitTestDownResult && size.contains(position));

    if (pass) {
      result.add(BoxHitTestEntry(this, position));
    }

    bool res = !up && pass;
    LogUtils.dd("hitTest: res = $res, pass = $pass, up = $up");
    return res;
  }

  @override
  bool hitTestSelf(Offset position) => self;
}



